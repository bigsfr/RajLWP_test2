package com.mydomain.wallpaper.mywallpaper;


import java.util.ArrayList;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import rajawali.materials.Material;
import rajawali.materials.textures.ATexture;
import rajawali.materials.textures.ATexture.TextureException;
import rajawali.materials.textures.Texture;
import rajawali.primitives.Plane;
import rajawali.renderer.RajawaliRenderer;
import rajawali.util.RajLog;
import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;


public class Renderer extends RajawaliRenderer {

	private Plane p = null;
	private Material material = null;

	public Renderer(Context context) {
		super(context);
	}

	public void initScene()
	{
		Log.e("MY", "initScene");
		RajLog.enableDebug(false);

		getCurrentCamera().setPosition(0, 0, -7);
		getCurrentCamera().setLookAt(0, 0, 0);

		try {
			p = new Plane(5,5,1,1);
			material = new Material();
			material.enableLighting(false);

			material.addTexture(new Texture("rajawaliTex", R.drawable.rajawali_tex));
			p.setMaterial(material);
			getCurrentScene().addChild(p);

		} catch (TextureException e) {
			e.printStackTrace();
		}
	}

	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		Log.e("MY", "onSurfaceCreated");
		super.onSurfaceCreated(gl, config);	
	}

	public void onDrawFrame(GL10 glUnused) {
		super.onDrawFrame(glUnused);
	}

	@Override
	public void onTouchEvent(MotionEvent event) {
		Log.e("MY", "touched");
		
		ArrayList<ATexture> l = material.getTextureList();

		if (l.size()>0) 
		{
			
			ATexture t = l.get(0);
			material.removeTexture(t);
			getTextureManager().removeTexture(t);

		}
		else 
		{

			try {

				material.addTexture(new Texture("rajawaliTex", R.drawable.rajawali_tex));
				p.setMaterial(material);
				material.enableLighting(false);

			} catch (TextureException e) {
				e.printStackTrace();
			}
			
		}


		Log.e("MY", "TextureManager texture count is now "+ getTextureManager().getNumTextures());
		Log.e("MY", " material texture count is now "+ material.getTextureList().size());
	}
}



