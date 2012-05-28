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
	private int width;
	private int height;
	private Thread thread;
	Context context=null;
	private boolean enable = true;
	private boolean tableCard = false;

	//----------------------------------------------------------------------------------
	public PlayCard() {}
	//----------------------------------------------------------------------------------
	public PlayCard(Context context, int drawable, Point point, int width, int height) {
		this.context= context;
		this.width = (int) (width*0.08);
		this.height = (int) (height*0.18);
		//		çcenter = BitmapFactory.decodeResource(context.getResources(), R.drawable.earthicon); 
		//		center = BitmapFactory.decodeResource(context.getResources(), R.drawable.watericon); 
		//		center = BitmapFactory.decodeResource(context.getResources(), R.drawable.windicon); 
		Bitmap imgAux = BitmapFactory.decodeResource(context.getResources(), drawable); 
		img = Bitmap.createScaledBitmap(imgAux,this.width, this.height, false);
		Bitmap centerAux = BitmapFactory.decodeResource(context.getResources(), R.drawable.fireicon); 
		center = Bitmap.createScaledBitmap(centerAux,(int) (this.width*0.39),(int) (this.height*0.31), false); 
		Bitmap northAux = BitmapFactory.decodeResource(context.getResources(), R.drawable.testnumber); 
		northPower = Bitmap.createScaledBitmap(northAux,(int) (this.width*0.25),(int) (this.height*0.22), false);
		Bitmap southAux = BitmapFactory.decodeResource(context.getResources(), R.drawable.testnumber); 
		southPower = Bitmap.createScaledBitmap(southAux,(int) (this.width*0.25),(int) (this.height*0.22), false);  
		Bitmap eastAux = BitmapFactory.decodeResource(context.getResources(), R.drawable.testnumber); 
		eastPower = Bitmap.createScaledBitmap(eastAux,(int) (this.width*0.25),(int) (this.height*0.22), false);  
		Bitmap westAux = BitmapFactory.decodeResource(context.getResources(), R.drawable.testnumber); 
		westPower = Bitmap.createScaledBitmap(westAux,(int) (this.width*0.25),(int) (this.height*0.22), false);  
		initialPosX = point.x;
		initialPosY = point.y;
		coordX= point.x;
		coordY = point.y;



	}
	//----------------------------------------------------------------------------------
	public void drawCard(Canvas canvas) {

		canvas.drawBitmap(this.getBitmap(), this.getX(), this.getY(), null);
		if(!tableCard){
			canvas.drawBitmap(this.getBitmapCenter(), 
					this.getX()+((int)(this.width*0.33)), this.getY()+((int)(this.height*0.37)), null);
			canvas.drawBitmap(this.getBitmapNorth(), 
					this.getX()+((int)(this.width*0.38)), this.getY()+((int)(this.height*0.05)), null);
			canvas.drawBitmap(this.getBitmapSouth(), 
					this.getX()+((int)(this.width*0.38)), this.getY()+((int)(this.height*0.77)), null);
			canvas.drawBitmap(this.getBitmapEast(), 
					this.getX()+((int)(this.width*0.73)), this.getY()+((int)(this.height*0.4)), null);
			canvas.drawBitmap(this.getBitmapWest(), 
					this.getX()+((int)(this.width*0.03)), this.getY()+((int)(this.height*0.4)), null);
		}
	}
	//----------------------------------------------------------------------------------
	public void setContext(Context context) {
		this.context = context;
	}
	//----------------------------------------------------------------------------------
	public void setImg(Bitmap img) {
		this.img = img;
	}
	//----------------------------------------------------------------------------------
	public void setCenter(Bitmap center) {
		this.center = center;
	}
	//----------------------------------------------------------------------------------
	public void setNorthPower(Bitmap northPower) {
		this.northPower = northPower;
	}
	//----------------------------------------------------------------------------------
	public void setSouthPower(Bitmap southPower) {
		this.southPower = southPower;
	}
	//----------------------------------------------------------------------------------
	public void setEastPower(Bitmap eastPower) {
		this.eastPower = eastPower;
	}
	//----------------------------------------------------------------------------------
	public void setWestPower(Bitmap westPower) {
		this.westPower = westPower;
	}
	//----------------------------------------------------------------------------------
	public void setX(int newValue) {
		coordX = newValue;
	}
	//----------------------------------------------------------------------------------
	public int getX() {
		return coordX;
	}
	//----------------------------------------------------------------------------------
	public void setY(int newValue) {
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
	public boolean isTableCard() {
		return tableCard;
	}
	//----------------------------------------------------------------------------------
	public void setTableCard(boolean tableCard) {
		this.tableCard = tableCard;
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
	//----------------------------------------------------------------------------------
	public PlayCard clone() {
		PlayCard card = new PlayCard();
		card.setId(id);
		card.setInitialPosX(initialPosX);
		card.setInitialPosY(initialPosY);
		card.setX(coordX);
		card.setY(coordY);
		card.setEnable(enable);
		card.setCenter(center);
		card.setNorthPower(northPower);
		card.setSouthPower(southPower);
		card.setEastPower(eastPower);
		card.setWestPower(westPower);
		card.setImg(img);
		card.setContext(context);
		card.setWidth(width);
		card.setHeight(height);
		return card;
	}
	//----------------------------------------------------------------------------------
	public int getWidth() {
		return width;
	}
	//----------------------------------------------------------------------------------
	public void setWidth(int width) {
		this.width = width;
	}
	//----------------------------------------------------------------------------------
	public int getHeight() {
		return height;
	}
	//----------------------------------------------------------------------------------
	public void setHeight(int height) {
		this.height = height;
	}
	//----------------------------------------------------------------------------------
}
