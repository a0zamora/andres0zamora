package com.andresoftware.tesis.tablero;

import java.util.ArrayList;
import java.util.List;

import com.andresoftware.tesis.gen.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;

public class TableCardsManager {
	private PlayCard[] tableCards = new PlayCard[16];
	private Context context;

	//----------------------------------------------------------------------------------
	public TableCardsManager(Context context, int width, int height) {
		this.context=context;
		int xAux=258;
		Point point1 = new Point();
		point1.x = (int) (width/2) - ((int)(width*0.16)+ (int)(width*0.015)) ;
		point1.y = (int) (height * 0.02);
		Point point2 = new Point();
		point2.x = (int) (width/2) - ((int)(width*0.08)+ (int)(width*0.005));
		point2.y = (int) (height * 0.02);
		Point point3 = new Point();
		point3.x = (int) (width/2) + (int)(width*0.005);
		point3.y = (int) (height * 0.02);
		Point point4 = new Point();
		point4.x = (int) (width/2) + ((int)(width*0.015)+(int)(width*0.08));
		point4.y = (int) (height * 0.02);
		Point point5 = new Point();
		point5.x = (int) (width/2) - ((int)(width*0.16)+ (int)(width*0.015));
		point5.y = (int) (height * 0.22);
		Point point6 = new Point();
		point6.x = (int) (width/2) - ((int)(width*0.08)+ (int)(width*0.005));
		point6.y = (int) (height * 0.22);
		Point point7 = new Point();
		point7.x = (int) (width/2) + (int)(width*0.005);
		point7.y = (int) (height * 0.22);
		Point point8 = new Point();
		point8.x = (int) (width/2) + ((int)(width*0.015)+(int)(width*0.08));
		point8.y = (int) (height * 0.22);
		Point point9 = new Point();
		point9.x = (int) (width/2) - ((int)(width*0.16)+ (int)(width*0.015));
		point9.y = (int) (height * 0.42);
		Point point10 = new Point();
		point10.x = (int) (width/2) - ((int)(width*0.08)+ (int)(width*0.005));
		point10.y = (int) (height * 0.42);
		Point point11 = new Point();
		point11.x = (int) (width/2) + (int)(width*0.005);
		point11.y = (int) (height * 0.42);
		Point point12 = new Point();
		point12.x = (int) (width/2) + ((int)(width*0.015)+(int)(width*0.08));
		point12.y = (int) (height * 0.42);
		Point point13 = new Point();
		point13.x = (int) (width/2) - ((int)(width*0.16)+ (int)(width*0.015));
		point13.y = (int) (height * 0.62);
		Point point14 = new Point();
		point14.x = (int) (width/2) - ((int)(width*0.08)+ (int)(width*0.005));
		point14.y = (int) (height * 0.62);
		Point point15 = new Point();
		point15.x = (int) (width/2) + (int)(width*0.005);
		point15.y = (int) (height * 0.62);
		Point point16 = new Point();
		point16.x = (int) (width/2) + ((int)(width*0.015)+(int)(width*0.08));
		point16.y = (int) (height * 0.62);
		// declare each card with the color
		tableCards[0] = new PlayCard(context,R.drawable.yellowcard, point1, width, height);
		tableCards[1] = new PlayCard(context,R.drawable.yellowcard, point2, width, height);
		tableCards[2] = new PlayCard(context,R.drawable.yellowcard, point3, width, height);
		tableCards[3] = new PlayCard(context,R.drawable.yellowcard, point4, width, height);
		tableCards[4] = new PlayCard(context,R.drawable.yellowcard, point5, width, height);
		tableCards[5] = new PlayCard(context,R.drawable.yellowcard, point6, width, height);
		tableCards[6] = new PlayCard(context,R.drawable.yellowcard, point7, width, height);
		tableCards[7] = new PlayCard(context,R.drawable.yellowcard, point8, width, height);
		tableCards[8] = new PlayCard(context,R.drawable.yellowcard, point9, width, height);
		tableCards[9] = new PlayCard(context,R.drawable.yellowcard, point10, width, height);
		tableCards[10] = new PlayCard(context,R.drawable.yellowcard, point11, width, height);
		tableCards[11] = new PlayCard(context,R.drawable.yellowcard, point12, width, height);
		tableCards[12] = new PlayCard(context,R.drawable.yellowcard, point13, width, height);
		tableCards[13] = new PlayCard(context,R.drawable.yellowcard, point14, width, height);
		tableCards[14] = new PlayCard(context,R.drawable.yellowcard, point15, width, height);
		tableCards[15] = new PlayCard(context,R.drawable.yellowcard, point16, width, height);		
		initIds();
		initTableCards();
	}
	//----------------------------------------------------------------------------------
	public void drawCards(Canvas canvas) {
		for (PlayCard card : tableCards) {
			card.drawCard(canvas);	
		}
	}
	//----------------------------------------------------------------------------------
	public PlayCard getCard(int x, int y) {

		PlayCard returnCard=null;
		int i=0;
		for (PlayCard tableCard : tableCards) {
			if(tableCard.isEnable()){
				// check if inside the bounds of the card 
				// get the center for the card
				int centerX = tableCard.getX() + (tableCard.getWidth()/2);
				int centerY = tableCard.getY() + (tableCard.getHeight()/2);

				// calculate the radius from the touch to the center of the card
				int radCardX  = (centerX-x);
				int radCardY  = (centerY-y);
				if(radCardX<0){
					radCardX = radCardX*(-1);
				}
				if(radCardY<0){
					radCardY = radCardY*(-1);
				}
				//  then it must be on the card
				if (radCardX <= (tableCard.getWidth()/2) && radCardY <= (tableCard.getHeight()/2)){
					returnCard = tableCard;
					break;
				}
			}
			i++;
		}
		return returnCard;
	}
	//----------------------------------------------------------------------------------
	public void initIds() {
		int id=0;
		for(PlayCard card: tableCards){
			card.setId(id);
			id++;
		}
	}
	//----------------------------------------------------------------------------------
	public void initTableCards() {
		for(PlayCard card:tableCards){
			card.setTableCard(true);
		}
	}
	//----------------------------------------------------------------------------------
	public void setCard(int x, int y, PlayCard cardAux) {
		int i=0;
		for (PlayCard tableCard : tableCards) {
			if(tableCard.isEnable()){
				// check if inside the bounds of the card 
				// get the center for the card
				int centerX = tableCard.getX() + (tableCard.getWidth()/2);
				int centerY = tableCard.getY() + (tableCard.getHeight()/2);

				// calculate the radius from the touch to the center of the card
				int radCardX  = (centerX-x);
				int radCardY  = (centerY-y);
				if(radCardX<0){
					radCardX = radCardX*(-1);
				}
				if(radCardY<0){
					radCardY = radCardY*(-1);
				}
				//  then it must be on the card
				if (radCardX <= (tableCard.getWidth()/2) && radCardY <= (tableCard.getHeight()/2)){
					tableCards[i]=cardAux;
					break;
				}
			}
			i++;
		}
	}
}
