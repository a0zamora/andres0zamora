package com.andresoftware.tesis.buttons;

import com.andresoftware.tesis.gen.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class MagicRootButton {
	
	boolean enable = false;
	private Bitmap image;
	private int width;
	private int height;
	//----------------------------------------------------------------------------------
	public MagicRootButton(int width, int height, Context context, Bitmap bitmap) {
		this.width = width;
		this.height = height;
		
		image = Bitmap.createScaledBitmap(bitmap
				,(int) (width*0.22),(int) (height*0.11), false);
	}
	//----------------------------------------------------------------------------------
	public boolean isEnable() {
		return enable;
	}
	//----------------------------------------------------------------------------------
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	//----------------------------------------------------------------------------------
	public Bitmap getImage() {
		return image;
	}
	//----------------------------------------------------------------------------------
	public void setImage(Bitmap image) {
		this.image = image;
	}
	//----------------------------------------------------------------------------------
	public void draw(Canvas canvas, double y) {
		if(this.enable){
			canvas.drawBitmap(this.image, 
					((int)(this.width*0.5))-((int)(this.width*0.11)), ((int)(this.height*y)), null);
		}
	}
	//----------------------------------------------------------------------------------
	

}
