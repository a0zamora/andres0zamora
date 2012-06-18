package com.andresoftware.tesis.selectcardsview;

import com.andresoftware.tesis.tablero.PlayCard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.widget.Toast;

public class GameCardsMannager {
	private PlayCard[] gameCards =  new PlayCard[8];
	private Point[] points = new Point[8];
	private Context context;
	//----------------------------------------------------------------------------------
	public GameCardsMannager(Context context, int width, int height) {
		this.context = context;
		points[0]= new Point();
		points[0].x = (int) (width/2) - ((int)(width*0.16)+ (int)(width*0.015)) ;
		points[0].y = (int) (height * 0.30);
		points[1]= new Point();
		points[1].x = (int) (width/2) - ((int)(width*0.08)+ (int)(width*0.005));
		points[1].y = (int) (height * 0.30);
		points[2] = new Point();
		points[2].x = (int) (width/2) + (int)(width*0.005);
		points[2].y = (int) (height * 0.30);
		points[3]= new Point();
		points[3].x = (int) (width/2) + ((int)(width*0.015)+(int)(width*0.08));
		points[3].y = (int) (height * 0.30);
		points[4] = new Point();
		points[4].x = (int) (width/2) - ((int)(width*0.16)+ (int)(width*0.015));
		points[4].y = (int) (height * 0.5);
		points[5] = new Point();
		points[5].x = (int) (width/2) - ((int)(width*0.08)+ (int)(width*0.005));
		points[5].y = (int) (height * 0.5);
		points[6] = new Point();
		points[6].x = (int) (width/2) + (int)(width*0.005);
		points[6].y = (int) (height * 0.5);
		points[7] = new Point();
		points[7].x = (int) (width/2) + ((int)(width*0.015)+(int)(width*0.08));
		points[7].y = (int) (height * 0.5);
		gameCards[0] = null;
		gameCards[1] = null;
		gameCards[2] = null;
		gameCards[3] = null;
		gameCards[4] = null;
		gameCards[5] = null;
		gameCards[6] = null;
		gameCards[7] = null;
	}
	//----------------------------------------------------------------------------------
	public void drawCards(Canvas canvas) {
		int i=0;
		for (PlayCard card : gameCards) {
			if(card!=null){
				canvas.drawBitmap(card.getBitmap(), points[i].x, points[i].y, null);
				canvas.drawBitmap(card.getBitmapCenter(), 
						points[i].x+((int)(card.width*0.33)), points[i].y+((int)(card.height*0.37)), null);
				canvas.drawBitmap(card.getBitmapNorth(), 
						points[i].x+((int)(card.width*0.38)), points[i].y+((int)(card.height*0.05)), null);
				canvas.drawBitmap(card.getBitmapSouth(), 
						points[i].x+((int)(card.width*0.38)), points[i].y+((int)(card.height*0.77)), null);
				canvas.drawBitmap(card.getBitmapEast(), 
						points[i].x+((int)(card.width*0.73)), points[i].y+((int)(card.height*0.4)), null);
				canvas.drawBitmap(card.getBitmapWest(), 
						points[i].x+((int)(card.width*0.03)), points[i].y+((int)(card.height*0.4)), null);


			}
			i++;
		}
	}
	//----------------------------------------------------------------------------------
	public void asignCard(PlayCard cardIn) {
		int i=0;
		for(PlayCard card: gameCards){
			if(card==null){
				gameCards[i]=cardIn;
				break;
			}
			i++;
		}

	}
	public void onCard(int x, int y) {
		int i=0;
		for (PlayCard card : gameCards) {
			if(card!=null){
				// check if inside the bounds of the card
				// get the center for the ball
				int centerX = points[i].x + (card.getWidth()/2);
				int centerY = points[i].y + (card.getHeight()/2);

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
				if (radCardX <= (card.getWidth()/2) && radCardY <= (card.getHeight()/2)){
//					Toast.makeText(context, "MagicRoot", 
//							Toast.LENGTH_SHORT).show();
					card.setEnable(true);
					gameCards[i] = null;
					break;
				}
			}
			i++;
		}
	}

}
