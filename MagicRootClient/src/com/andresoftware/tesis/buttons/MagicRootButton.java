package com.andresoftware.tesis.buttons;

import com.andresoftware.tesis.gen.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.widget.Toast;

public class MagicRootButton {
	
	boolean enable = false;
	private Bitmap image;
	private int width;
	private int height;
	private int widthButton;
	private int heightButton;
	private int posX;
	private int posY;
	private Context context;
	//----------------------------------------------------------------------------------
	public MagicRootButton(int width, int height, Context context, Bitmap bitmap) {
		this.context = context;
		this.width = width;
		this.height = height;
		widthButton = (int) (width*0.22);
		heightButton = (int) (height*0.11);
		image = Bitmap.createScaledBitmap(bitmap
				,widthButton,heightButton, false);
		posX = ((int)(this.width*0.5))-((int)(this.width*0.11));
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
					posX, posY, null);
		}
	}
	//----------------------------------------------------------------------------------
	public boolean touch(int x, int y) {
		
		if (enable){
			// check if inside the bounds of the card
			// get the center for the ball
			int centerX = (int)(posX + (widthButton/2));
			int centerY = (int)(posY + (heightButton/2));

			// calculate the radius from the touch to the center of the card
			int radCardX  = (centerX-x);
			int radCardY  = (centerY-y);
			if(radCardX<0){
				radCardX = radCardX*(-1);
			}
			if(radCardY<0){
				radCardY = radCardY*(-1);
			}
			// then it must be on the card
			if (radCardX <= (widthButton/2) && radCardY <= (heightButton/2)){
				return true;
			}
		}
		return false;
	}
	//----------------------------------------------------------------------------------
	public void setPosX(int posX) {
		this.posX = posX;
	}
	//----------------------------------------------------------------------------------
	public int getPosX() {
		return posX;
	}
	//----------------------------------------------------------------------------------
	public void setPosY(double posY) {
		this.posY = ((int)(this.height*posY));
	}
	//----------------------------------------------------------------------------------
	public int getPosY() {
		return posY;
	}
	//----------------------------------------------------------------------------------
	

}
