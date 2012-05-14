package com.andresoftware.tesis.tablero;

import com.andresoftware.tesis.gen.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.widget.LinearLayout;

public class PlayCard {
	private Bitmap img;
	private Bitmap center; // the image of the ball
	private Bitmap northPower; 
	private Bitmap southPower; 
	private Bitmap eastPower; 
	private Bitmap westPower; 
	private int initialPosX = 0;
	private int initialPosY = 0;
	private int coordX = 0; // the x coordinate at the canvas
	private int coordY = 0; // the y coordinate at the canvas
	private int id; // gives every ball his own id, for now not necessary
	private static int count = 1;
	private Thread thread;
	Context context=null;
	private boolean enable = true;
	//----------------------------------------------------------------------------------
	public PlayCard(Context context, int drawable, Point point) {
		this.context= context;
		//		BitmapFactory.Options opts = new BitmapFactory.Options();
		//		opts.inJustDecodeBounds = true;
		img = BitmapFactory.decodeResource(context.getResources(), drawable); 
		center = BitmapFactory.decodeResource(context.getResources(), R.drawable.earthicon); 
//		center = BitmapFactory.decodeResource(context.getResources(), R.drawable.watericon); 
//		center = BitmapFactory.decodeResource(context.getResources(), R.drawable.windicon); 
		northPower = BitmapFactory.decodeResource(context.getResources(), R.drawable.testnumber); 
		southPower = BitmapFactory.decodeResource(context.getResources(), R.drawable.testnumber); 
		eastPower = BitmapFactory.decodeResource(context.getResources(), R.drawable.testnumber); 
		westPower = BitmapFactory.decodeResource(context.getResources(), R.drawable.testnumber); 

		id=count;
		count++;
		initialPosX = point.x;
		initialPosY = point.y;
		coordX= point.x;
		coordY = point.y;



	}
	//----------------------------------------------------------------------------------
		public void drawBall(Canvas canvas) {

			canvas.drawBitmap(this.getBitmap(), this.getX(), this.getY(), null);
			canvas.drawBitmap(this.getBitmapCenter(), this.getX()+19, this.getY()+29, null);
			canvas.drawBitmap(this.getBitmapNorth(), this.getX()+24, this.getY()+5, null);
			canvas.drawBitmap(this.getBitmapSouth(), this.getX()+24, this.getY()+65, null);
			canvas.drawBitmap(this.getBitmapEast(), this.getX()+2, this.getY()+35, null);
			canvas.drawBitmap(this.getBitmapWest(), this.getX()+43, this.getY()+35, null);
		}
	//----------------------------------------------------------------------------------
	public static int getCount() {
		return count;
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
	public int getID() {
		return id;
	}
	//----------------------------------------------------------------------------------
	public Bitmap getBitmap() {
		return img;
	}
	//----------------------------------------------------------------------------------
	public int getInitialPosX() {
		return initialPosX;
	}
	//----------------------------------------------------------------------------------
	public void setInitialPosX(int initialPosX) {
		this.initialPosX = initialPosX;
	}
	//----------------------------------------------------------------------------------
	public int getInitialPosY() {
		return initialPosY;
	}
	//----------------------------------------------------------------------------------
	public void setInitialPosY(int initialPosY) {
		this.initialPosY = initialPosY;
	}
	//----------------------------------------------------------------------------------
	public Bitmap getBitmapNorth() {
		return northPower;
	}
	//----------------------------------------------------------------------------------
	public Bitmap getBitmapSouth() {
		return southPower;
	}
	//----------------------------------------------------------------------------------
	public Bitmap getBitmapEast() {
		return eastPower;
	}
	//----------------------------------------------------------------------------------
	public Bitmap getBitmapWest() {
		return westPower;
	}
	//----------------------------------------------------------------------------------
	public Bitmap getBitmapCenter() {
		return center;
	}
	//----------------------------------------------------------------------------------
	public void setId(int id) {
		this.id = id;
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
	public void flipCard(){

		Runnable runnable = new Runnable() {

			@Override
			public void run() {

				//				img=BitmapFactory.decodeResource(context.getResources(), R.drawable.bol_geel);	
				//				
				//				sleepThread();
				//
				//				img=BitmapFactory.decodeResource(context.getResources(), R.drawable.bol_blauw);	
				//				sleepThread();
				//				
				//
				//				img=BitmapFactory.decodeResource(context.getResources(), R.drawable.bol_groen);	
				//				sleepThread();
				//
				//				img=BitmapFactory.decodeResource(context.getResources(), R.drawable.bol_paars);	
				//				sleepThread();
				//
				//				img=BitmapFactory.decodeResource(context.getResources(), R.drawable.bol_rood);	
				//				sleepThread();

			}

		};	
		thread = new Thread(runnable);
		thread.start();
	}
	//----------------------------------------------------------------------------------
	private void sleepThread() {
		try {
			Thread.sleep(40);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
