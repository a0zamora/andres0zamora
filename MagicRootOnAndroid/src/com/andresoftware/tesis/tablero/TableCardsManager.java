package com.andresoftware.tesis.tablero;

import java.util.ArrayList;
import java.util.List;

import com.andresoftware.tesis.gen.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;

public class TableCardsManager {
	private List<PlayCard> tableCards = new ArrayList<PlayCard>();
	private Context context;

	//----------------------------------------------------------------------------------
	public TableCardsManager(Context context) {
		this.context=context;
		int xAux=258;
		Point point1 = new Point();
		point1.x = xAux;
		point1.y = 10;
		Point point2 = new Point();
		point2.x = xAux+69;
		point2.y = 10;
		Point point3 = new Point();
		point3.x = xAux+(2*69);
		point3.y = 10;
		Point point4 = new Point();
		point4.x = xAux+(3*69);
		point4.y = 10;
		Point point5 = new Point();
		point5.x = xAux;
		point5.y = 105;
		Point point6 = new Point();
		point6.x = xAux+69;
		point6.y = 105;
		Point point7 = new Point();
		point7.x = xAux+(2*69);
		point7.y = 105;
		Point point8 = new Point();
		point8.x = xAux+(3*69);
		point8.y = 105;
		Point point9 = new Point();
		point9.x = xAux;
		point9.y = 200;
		Point point10 = new Point();
		point10.x = xAux+69;
		point10.y = 200;
		Point point11 = new Point();
		point11.x = xAux+(2*69);
		point11.y = 200;
		Point point12 = new Point();
		point12.x = xAux+(3*69);
		point12.y = 200;
		Point point13 = new Point();
		point13.x = xAux;
		point13.y = 295;
		Point point14 = new Point();
		point14.x = xAux+69;
		point14.y = 295;
		Point point15 = new Point();
		point15.x = xAux+(2*69);
		point15.y = 295;
		Point point16 = new Point();
		point16.x = xAux+(3*69);
		point16.y = 295;
		// declare each card with the color
		PlayCard tableCards0 = new PlayCard(context,R.drawable.yellowcard, point1);
		PlayCard tableCards1 = new PlayCard(context,R.drawable.yellowcard, point2);
		PlayCard tableCards2 = new PlayCard(context,R.drawable.yellowcard, point3);
		PlayCard tableCards3 = new PlayCard(context,R.drawable.yellowcard, point4);
		PlayCard tableCards4 = new PlayCard(context,R.drawable.yellowcard, point5);
		PlayCard tableCards5 = new PlayCard(context,R.drawable.yellowcard, point6);
		PlayCard tableCards6 = new PlayCard(context,R.drawable.yellowcard, point7);
		PlayCard tableCards7 = new PlayCard(context,R.drawable.yellowcard, point8);
		PlayCard tableCards8 = new PlayCard(context,R.drawable.yellowcard, point9);
		PlayCard tableCards9 = new PlayCard(context,R.drawable.yellowcard, point10);
		PlayCard tableCards10 = new PlayCard(context,R.drawable.yellowcard, point11);
		PlayCard tableCards11 = new PlayCard(context,R.drawable.yellowcard, point12);
		PlayCard tableCards12 = new PlayCard(context,R.drawable.yellowcard, point13);
		PlayCard tableCards13 = new PlayCard(context,R.drawable.yellowcard, point14);
		PlayCard tableCards14 = new PlayCard(context,R.drawable.yellowcard, point15);
		PlayCard tableCards15 = new PlayCard(context,R.drawable.yellowcard, point16);		
		tableCards.add(tableCards0);
		tableCards.add(tableCards1);
		tableCards.add(tableCards2);
		tableCards.add(tableCards3);
		tableCards.add(tableCards4);
		tableCards.add(tableCards5);
		tableCards.add(tableCards6);
		tableCards.add(tableCards7);
		tableCards.add(tableCards8);
		tableCards.add(tableCards9);
		tableCards.add(tableCards10);
		tableCards.add(tableCards11);
		tableCards.add(tableCards12);
		tableCards.add(tableCards13);
		tableCards.add(tableCards14);
		tableCards.add(tableCards15);
		initIds();
		initTableCards();
	}
	//----------------------------------------------------------------------------------
	public void drawCards(Canvas canvas) {
		for (PlayCard card : tableCards) {
			if(card.isDraw()){
				card.drawCard(canvas);	
			}
			
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
					int centerX = tableCard.getX() + 32;
					int centerY = tableCard.getY() + 42;

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
					if (radCardX <= 32 && radCardY <= 42){
						returnCard = tableCard;
						tableCard.setDraw(false);
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
		public void setCard(PlayCard card) {
			tableCards.add(card);
		}
		//----------------------------------------------------------------------------------
		public void initTableCards() {
			for(PlayCard card:tableCards){
				card.setTableCard(true);
			}
		}
}
