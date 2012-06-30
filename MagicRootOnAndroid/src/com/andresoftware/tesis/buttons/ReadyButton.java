package com.andresoftware.tesis.buttons;

import com.andresoftware.tesis.gen.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class ReadyButton {
	
	boolean enable = true;
	private Bitmap image;
	private int width;
	private int height;
	//----------------------------------------------------------------------------------
	public ReadyButton(int width, int height, Context context) {
		this.width = width;
		this.height = height;
		
		image = Bitmap.createScaledBitmap(
				BitmapFactory.decodeResource(context.getResources(), R.drawable.readybutton)
				,(int) (width*0.10),(int) (height*0.18), false);
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
	public void draw(Canvas canvas) {
		if(this.enable){
			canvas.drawBitmap(this.image, 
					((int)(this.width*0.5))-((int)(this.width*0.05)), ((int)(this.height*0.70)), null);
		}
	}
	//----------------------------------------------------------------------------------
	

}
