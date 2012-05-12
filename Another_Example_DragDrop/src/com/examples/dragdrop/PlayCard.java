package com.examples.dragdrop;

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
	
	private int coordX = 0; // the x coordinate at the canvas
	private int coordY = 0; // the y coordinate at the canvas
	private int id; // gives every ball his own id, for now not necessary
	private static int count = 1;
	private Thread thread;
	Context context=null;
	public PlayCard(Context context, int drawable, Point point) {
		this.context= context;
//		BitmapFactory.Options opts = new BitmapFactory.Options();
//		opts.inJustDecodeBounds = true;
		img = BitmapFactory.decodeResource(context.getResources(), drawable); 
		center = BitmapFactory.decodeResource(context.getResources(), R.drawable.earthicon); 
		northPower = BitmapFactory.decodeResource(context.getResources(), R.drawable.testnumber); 
		southPower = BitmapFactory.decodeResource(context.getResources(), R.drawable.testnumber); 
		eastPower = BitmapFactory.decodeResource(context.getResources(), R.drawable.testnumber); 
		westPower = BitmapFactory.decodeResource(context.getResources(), R.drawable.testnumber); 

		id=count;
		count++;
		coordX= point.x;
		coordY = point.y;

		
		
	}
	public static int getCount() {
		return count;
	}

	void setX(int newValue) {
		coordX = newValue;


	}

	public int getX() {
		return coordX;
	}

	void setY(int newValue) {
		coordY = newValue;
	}

	public int getY() {
		return coordY;
	}

	public int getID() {
		return id;
	}

	public Bitmap getBitmap() {
		return img;
	}

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
	private void sleepThread() {
		try {
			Thread.sleep(40);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Bitmap getBitmapNorth() {
		return northPower;
	}
	public Bitmap getBitmapSouth() {
		return southPower;
	}
	public Bitmap getBitmapEast() {
		return eastPower;
	}
	public Bitmap getBitmapWest() {
		return westPower;
	}
	public Bitmap getBitmapCenter() {
		return center;
	}
	public void drawBall(Canvas canvas) {

		canvas.drawBitmap(this.getBitmap(), this.getX(), this.getY(), null);
		canvas.drawBitmap(this.getBitmapCenter(), this.getX()+19, this.getY()+29, null);
		canvas.drawBitmap(this.getBitmapNorth(), this.getX()+24, this.getY()+5, null);
		canvas.drawBitmap(this.getBitmapSouth(), this.getX()+24, this.getY()+65, null);
		canvas.drawBitmap(this.getBitmapEast(), this.getX()+2, this.getY()+38, null);
		canvas.drawBitmap(this.getBitmapWest(), this.getX()+43, this.getY()+35, null);
	}

}
