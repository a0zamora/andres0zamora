package com.andresoftware.tesis.selectcardsview;

import com.andresoftware.tesis.gen.R;
import com.andresoftware.tesis.tablero.PlayCard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;

public class GameCardsMannager {
	private PlayCard[] gameCards =  new PlayCard[8];
	//----------------------------------------------------------------------------------
	public GameCardsMannager(Context context, int width, int height) {
		Point point1 = new Point();
		point1.x = (int) (width/2) - ((int)(width*0.16)+ (int)(width*0.015)) ;
		point1.y = (int) (height * 0.30);
		Point point2 = new Point();
		point2.x = (int) (width/2) - ((int)(width*0.08)+ (int)(width*0.005));
		point2.y = (int) (height * 0.30);
		Point point3 = new Point();
		point3.x = (int) (width/2) + (int)(width*0.005);
		point3.y = (int) (height * 0.30);
		Point point4 = new Point();
		point4.x = (int) (width/2) + ((int)(width*0.015)+(int)(width*0.08));
		point4.y = (int) (height * 0.30);
		Point point5 = new Point();
		point5.x = (int) (width/2) - ((int)(width*0.16)+ (int)(width*0.015));
		point5.y = (int) (height * 0.5);
		Point point6 = new Point();
		point6.x = (int) (width/2) - ((int)(width*0.08)+ (int)(width*0.005));
		point6.y = (int) (height * 0.5);
		Point point7 = new Point();
		point7.x = (int) (width/2) + (int)(width*0.005);
		point7.y = (int) (height * 0.5);
		Point point8 = new Point();
		point8.x = (int) (width/2) + ((int)(width*0.015)+(int)(width*0.08));
		point8.y = (int) (height * 0.5);
		gameCards[0] = new PlayCard(context,R.drawable.yellowcard, point1, width, height);
		gameCards[1] = new PlayCard(context,R.drawable.yellowcard, point2, width, height);
		gameCards[2] = new PlayCard(context,R.drawable.yellowcard, point3, width, height);
		gameCards[3] = new PlayCard(context,R.drawable.yellowcard, point4, width, height);
		gameCards[4] = new PlayCard(context,R.drawable.yellowcard, point5, width, height);
		gameCards[5] = new PlayCard(context,R.drawable.yellowcard, point6, width, height);
		gameCards[6] = new PlayCard(context,R.drawable.yellowcard, point7, width, height);
		gameCards[7] = new PlayCard(context,R.drawable.yellowcard, point8, width, height);
	}
	//----------------------------------------------------------------------------------
	public void drawCards(Canvas canvas) {
		for (PlayCard card : gameCards) {
			card.drawCard(canvas);
		}
	}
	//----------------------------------------------------------------------------------

}
