package com.andresoftware.tesis.tablero;

import com.andresoftware.tesis.constants.MagicRootColors;
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
	public int width;
	public int height;
	private Thread thread;
	Context context=null;
	private boolean enable = true;
	private boolean tableCard = false;
	private MagicRootColors color = MagicRootColors.YELLOW;

	//----------------------------------------------------------------------------------
	public PlayCard(int width2, int height2, Context context2) {
		this.width = (int) (width2*0.08);
		this.height = (int) (height2*0.18);
		context = context2;
	}
	//----------------------------------------------------------------------------------
	public PlayCard(Context context, int drawable, Point point, int width, int height) {
		this.context= context;
		this.width = (int) (width*0.08);
		this.height = (int) (height*0.18);
		//		center = BitmapFactory.decodeResource(context.getResources(), R.drawable.earthicon); 
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
	public PlayCard() {
		// TODO Auto-generated constructor stub
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
	private void setCardColor() {
		if(color.equals(MagicRootColors.YELLOW)){
			Bitmap imgAux = BitmapFactory.decodeResource(context.getResources(), R.drawable.yellowcard); 
			img = Bitmap.createScaledBitmap(imgAux,this.width, this.height, false);
		}else if(color.equals(MagicRootColors.BLUE)){
			Bitmap imgAux = BitmapFactory.decodeResource(context.getResources(), R.drawable.bluecard); 
			img = Bitmap.createScaledBitmap(imgAux,this.width, this.height, false);
		}else{
			Bitmap imgAux = BitmapFactory.decodeResource(context.getResources(), R.drawable.redcard); 
			img = Bitmap.createScaledBitmap(imgAux,this.width, this.height, false);			
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
	public void setNorthPower(int northPower) {
		if(northPower==1){
			Bitmap northAux = BitmapFactory.decodeResource(context.getResources(), R.drawable.one); 
			this.northPower = Bitmap.createScaledBitmap(northAux,(int) (this.width*0.25),(int) (this.height*0.22), false);
		}else if(northPower==2){
			Bitmap northAux = BitmapFactory.decodeResource(context.getResources(), R.drawable.two); 
			this.northPower = Bitmap.createScaledBitmap(northAux,(int) (this.width*0.25),(int) (this.height*0.22), false);
			
		}else if(northPower==3){
			Bitmap northAux = BitmapFactory.decodeResource(context.getResources(), R.drawable.three); 
			this.northPower = Bitmap.createScaledBitmap(northAux,(int) (this.width*0.25),(int) (this.height*0.22), false);
			
		}else if(northPower==4){
			Bitmap northAux = BitmapFactory.decodeResource(context.getResources(), R.drawable.four); 
			this.northPower = Bitmap.createScaledBitmap(northAux,(int) (this.width*0.25),(int) (this.height*0.22), false);
			
		}else if(northPower==5){
			Bitmap northAux = BitmapFactory.decodeResource(context.getResources(), R.drawable.five); 
			this.northPower = Bitmap.createScaledBitmap(northAux,(int) (this.width*0.25),(int) (this.height*0.22), false);
			
		}else if(northPower==6){
			Bitmap northAux = BitmapFactory.decodeResource(context.getResources(), R.drawable.six); 
			this.northPower = Bitmap.createScaledBitmap(northAux,(int) (this.width*0.25),(int) (this.height*0.22), false);
			
		}else if(northPower==7){
			Bitmap northAux = BitmapFactory.decodeResource(context.getResources(), R.drawable.seven); 
			this.northPower = Bitmap.createScaledBitmap(northAux,(int) (this.width*0.25),(int) (this.height*0.22), false);
			
		}else if(northPower==8){
			Bitmap northAux = BitmapFactory.decodeResource(context.getResources(), R.drawable.eight); 
			this.northPower = Bitmap.createScaledBitmap(northAux,(int) (this.width*0.25),(int) (this.height*0.22), false);
			
		}else if(northPower==9){
			Bitmap northAux = BitmapFactory.decodeResource(context.getResources(), R.drawable.nine); 
			this.northPower = Bitmap.createScaledBitmap(northAux,(int) (this.width*0.25),(int) (this.height*0.22), false);
			
		}
	}
	//----------------------------------------------------------------------------------
	public void setSouthPower(int southPower) {
		if(southPower==1){
		Bitmap northAux = BitmapFactory.decodeResource(context.getResources(), R.drawable.one); 
		this.southPower = Bitmap.createScaledBitmap(northAux,(int) (this.width*0.25),(int) (this.height*0.22), false);
	}else if(southPower==2){
		Bitmap northAux = BitmapFactory.decodeResource(context.getResources(), R.drawable.two); 
		this.southPower = Bitmap.createScaledBitmap(northAux,(int) (this.width*0.25),(int) (this.height*0.22), false);
		
	}else if(southPower==3){
		Bitmap northAux = BitmapFactory.decodeResource(context.getResources(), R.drawable.three); 
		this.southPower = Bitmap.createScaledBitmap(northAux,(int) (this.width*0.25),(int) (this.height*0.22), false);
		
	}else if(southPower==4){
		Bitmap northAux = BitmapFactory.decodeResource(context.getResources(), R.drawable.four); 
		this.southPower = Bitmap.createScaledBitmap(northAux,(int) (this.width*0.25),(int) (this.height*0.22), false);
		
	}else if(southPower==5){
		Bitmap northAux = BitmapFactory.decodeResource(context.getResources(), R.drawable.five); 
		this.southPower = Bitmap.createScaledBitmap(northAux,(int) (this.width*0.25),(int) (this.height*0.22), false);
		
	}else if(southPower==6){
		Bitmap northAux = BitmapFactory.decodeResource(context.getResources(), R.drawable.six); 
		this.southPower = Bitmap.createScaledBitmap(northAux,(int) (this.width*0.25),(int) (this.height*0.22), false);
		
	}else if(southPower==7){
		Bitmap northAux = BitmapFactory.decodeResource(context.getResources(), R.drawable.seven); 
		this.southPower = Bitmap.createScaledBitmap(northAux,(int) (this.width*0.25),(int) (this.height*0.22), false);
		
	}else if(southPower==8){
		Bitmap northAux = BitmapFactory.decodeResource(context.getResources(), R.drawable.eight); 
		this.southPower = Bitmap.createScaledBitmap(northAux,(int) (this.width*0.25),(int) (this.height*0.22), false);
		
	}else if(southPower==9){
		Bitmap northAux = BitmapFactory.decodeResource(context.getResources(), R.drawable.nine); 
		this.southPower = Bitmap.createScaledBitmap(northAux,(int) (this.width*0.25),(int) (this.height*0.22), false);
	}
	}
	//----------------------------------------------------------------------------------
	public void setEastPower(int eastPower) {
		if(eastPower==1){
			Bitmap northAux = BitmapFactory.decodeResource(context.getResources(), R.drawable.one); 
			this.eastPower = Bitmap.createScaledBitmap(northAux,(int) (this.width*0.25),(int) (this.height*0.22), false);
		}else if(eastPower==2){
			Bitmap northAux = BitmapFactory.decodeResource(context.getResources(), R.drawable.two); 
			this.eastPower = Bitmap.createScaledBitmap(northAux,(int) (this.width*0.25),(int) (this.height*0.22), false);
			
		}else if(eastPower==3){
			Bitmap northAux = BitmapFactory.decodeResource(context.getResources(), R.drawable.three); 
			this.eastPower = Bitmap.createScaledBitmap(northAux,(int) (this.width*0.25),(int) (this.height*0.22), false);
			
		}else if(eastPower==4){
			Bitmap northAux = BitmapFactory.decodeResource(context.getResources(), R.drawable.four); 
			this.eastPower = Bitmap.createScaledBitmap(northAux,(int) (this.width*0.25),(int) (this.height*0.22), false);
			
		}else if(eastPower==5){
			Bitmap northAux = BitmapFactory.decodeResource(context.getResources(), R.drawable.five); 
			this.eastPower = Bitmap.createScaledBitmap(northAux,(int) (this.width*0.25),(int) (this.height*0.22), false);
			
		}else if(eastPower==6){
			Bitmap northAux = BitmapFactory.decodeResource(context.getResources(), R.drawable.six); 
			this.eastPower = Bitmap.createScaledBitmap(northAux,(int) (this.width*0.25),(int) (this.height*0.22), false);
			
		}else if(eastPower==7){
			Bitmap northAux = BitmapFactory.decodeResource(context.getResources(), R.drawable.seven); 
			this.eastPower = Bitmap.createScaledBitmap(northAux,(int) (this.width*0.25),(int) (this.height*0.22), false);
			
		}else if(eastPower==8){
			Bitmap northAux = BitmapFactory.decodeResource(context.getResources(), R.drawable.eight); 
			this.eastPower = Bitmap.createScaledBitmap(northAux,(int) (this.width*0.25),(int) (this.height*0.22), false);
			
		}else if(eastPower==9){
			Bitmap northAux = BitmapFactory.decodeResource(context.getResources(), R.drawable.nine); 
			this.eastPower = Bitmap.createScaledBitmap(northAux,(int) (this.width*0.25),(int) (this.height*0.22), false);
		}
	}
	//----------------------------------------------------------------------------------
	public void setWestPower(int westPower) {
		if(westPower==1){
			Bitmap northAux = BitmapFactory.decodeResource(context.getResources(), R.drawable.one); 
			this.westPower= Bitmap.createScaledBitmap(northAux,(int) (this.width*0.25),(int) (this.height*0.22), false);
		}else if(westPower==2){
			Bitmap northAux = BitmapFactory.decodeResource(context.getResources(), R.drawable.two); 
			this.westPower = Bitmap.createScaledBitmap(northAux,(int) (this.width*0.25),(int) (this.height*0.22), false);
			
		}else if(westPower==3){
			Bitmap northAux = BitmapFactory.decodeResource(context.getResources(), R.drawable.three); 
			this.westPower = Bitmap.createScaledBitmap(northAux,(int) (this.width*0.25),(int) (this.height*0.22), false);
			
		}else if(westPower==4){
			Bitmap northAux = BitmapFactory.decodeResource(context.getResources(), R.drawable.four); 
			this.westPower = Bitmap.createScaledBitmap(northAux,(int) (this.width*0.25),(int) (this.height*0.22), false);
			
		}else if(westPower==5){
			Bitmap northAux = BitmapFactory.decodeResource(context.getResources(), R.drawable.five); 
			this.westPower = Bitmap.createScaledBitmap(northAux,(int) (this.width*0.25),(int) (this.height*0.22), false);
			
		}else if(westPower==6){
			Bitmap northAux = BitmapFactory.decodeResource(context.getResources(), R.drawable.six); 
			this.westPower = Bitmap.createScaledBitmap(northAux,(int) (this.width*0.25),(int) (this.height*0.22), false);
			
		}else if(westPower==7){
			Bitmap northAux = BitmapFactory.decodeResource(context.getResources(), R.drawable.seven); 
			this.westPower = Bitmap.createScaledBitmap(northAux,(int) (this.width*0.25),(int) (this.height*0.22), false);
			
		}else if(westPower==8){
			Bitmap northAux = BitmapFactory.decodeResource(context.getResources(), R.drawable.eight); 
			this.westPower = Bitmap.createScaledBitmap(northAux,(int) (this.width*0.25),(int) (this.height*0.22), false);
			
		}else if(westPower==9){
			Bitmap northAux = BitmapFactory.decodeResource(context.getResources(), R.drawable.nine); 
			this.westPower = Bitmap.createScaledBitmap(northAux,(int) (this.width*0.25),(int) (this.height*0.22), false);
		}
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
	private void setWestPower(Bitmap westPower2) {
		westPower = westPower2;
		
	}
	//----------------------------------------------------------------------------------
	private void setEastPower(Bitmap eastPower2) {
		eastPower = eastPower2;
		
	}
	//----------------------------------------------------------------------------------
	private void setSouthPower(Bitmap southPower2) {
		southPower = southPower2;
		
	}
	//----------------------------------------------------------------------------------
	private void setNorthPower(Bitmap northPower2) {
		northPower = northPower2;
		
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
	public void setCenter(String element) {
		if(element.equals("agua")){
			Bitmap centerAux = BitmapFactory.decodeResource(context.getResources(), R.drawable.watericon); 
			center = Bitmap.createScaledBitmap(centerAux,(int) (this.width*0.39),(int) (this.height*0.31), false);
		}else if(element.equals("fuego")){
			Bitmap centerAux = BitmapFactory.decodeResource(context.getResources(), R.drawable.fireicon); 
			center = Bitmap.createScaledBitmap(centerAux,(int) (this.width*0.39),(int) (this.height*0.31), false);
			
		}else if(element.equals("aire")){
			Bitmap centerAux = BitmapFactory.decodeResource(context.getResources(), R.drawable.windicon); 
			center = Bitmap.createScaledBitmap(centerAux,(int) (this.width*0.39),(int) (this.height*0.31), false);
			
		}else if(element.equals("tierra")){
			Bitmap centerAux = BitmapFactory.decodeResource(context.getResources(), R.drawable.earthicon); 
			center = Bitmap.createScaledBitmap(centerAux,(int) (this.width*0.39),(int) (this.height*0.31), false);
			
		}
		Bitmap imgAux = BitmapFactory.decodeResource(context.getResources(), R.drawable.yellowcard); 
		img = Bitmap.createScaledBitmap(imgAux,this.width, this.height, false);
		
	}
	//----------------------------------------------------------------------------------
	public void startCard(Point point, int width2, int height2) {
		this.width = (int) (width2*0.08);
		this.height = (int) (height2*0.18);
		initialPosX = point.x;
		initialPosY = point.y;
		coordX= point.x;
		coordY = point.y;
		
	}
	//----------------------------------------------------------------------------------
	public boolean isEqualTo(PlayCard card) {
		return true;
		
	}
	//----------------------------------------------------------------------------------
	public void setColor(MagicRootColors color) {
		this.color = color;
		setCardColor();
	}
	//----------------------------------------------------------------------------------
	public MagicRootColors getColor() {
		return color;
	}
	//----------------------------------------------------------------------------------
}
