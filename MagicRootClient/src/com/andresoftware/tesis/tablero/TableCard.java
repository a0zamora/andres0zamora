package com.andresoftware.tesis.tablero;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;

public class TableCard {
	private Bitmap img;
	private int coordX = 0; // the x coordinate at the canvas
	private int coordY = 0; // the y coordinate at the canvas
	private int id;
	Context context=null;
	private boolean enable = true;
	//----------------------------------------------------------------------------------
	public TableCard(Context context, int drawable, Point point) {
		this.context= context;
		img = BitmapFactory.decodeResource(context.getResources(), drawable); 
		coordX= point.x;
		coordY = point.y;
	}
	//----------------------------------------------------------------------------------
	void setX(int newValue) {
		coordX = newValue;
	}
	//----------------------------------------------------------------------------------
	public int getX() {
		return coordX;
	}
	//----------------------------------------------------------------------------------
	void setY(int newValue) {
		coordY = newValue;
	}
	//----------------------------------------------------------------------------------
	public int getY() {    
		
		return coordY;
	}
	//----------------------------------------------------------------------------------
	public Bitmap getBitmap() {
		return img;
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
	public void drawBall(Canvas canvas) {
		canvas.drawBitmap(this.getBitmap(), this.getX(), this.getY(), null);
	}
	//----------------------------------------------------------------------------------
	public int getId() {
		return id;
	}
	//----------------------------------------------------------------------------------
	public void setId(int id) {
		this.id = id;
	}
	//----------------------------------------------------------------------------------
}
