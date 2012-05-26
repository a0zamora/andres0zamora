package com.andresoftware.tesis.selectcardsview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;

import com.andresoftware.tesis.gen.R;
import com.andresoftware.tesis.tablero.PlayCard;

public class SelectCardsMannager {
	int numberCards = 20;
	private PlayCard[] cardsToSelect = new PlayCard[numberCards];
	private Point[] points = new Point[numberCards];

	//----------------------------------------------------------------------------------
	public SelectCardsMannager(Context context) {
		int aux=73;
		for(int i=0; i<numberCards; i++){
			points[i] = new Point();
			points[i].x = 5 +( i * aux );
			points[i].y = 10;
			cardsToSelect[i] = new PlayCard(context,R.drawable.yellowcard, points[i]);
			cardsToSelect[i].setId(i);
		}
		movePanel(-2);
	}
	//----------------------------------------------------------------------------------
	public void drawCards(Canvas canvas) {
		for (PlayCard card : cardsToSelect) {
			card.drawCard(canvas);
		}
	}
	//----------------------------------------------------------------------------------
	public boolean touchSelectCardsPanel(int y) {
		if(y<95 && y > 10){
			return true;
		}else{
			return false;
		}
	}
	//----------------------------------------------------------------------------------
	public void movePanel(int move) {
		if(move>0 && cardsToSelect[0].getX()<cardsToSelect[0].getInitialPosX()){
			for(int i = 0 ; i < numberCards ; i++){
				cardsToSelect[i].setX(cardsToSelect[i].getX()+ move);
			}
		}else if(move<0 && cardsToSelect[numberCards-1].getX() > cardsToSelect[10].getInitialPosX()){
			for(int i = 0 ; i < numberCards ; i++){
				cardsToSelect[i].setX(cardsToSelect[i].getX()+ move);
			}
		}if(cardsToSelect[0].getX()> 3){
			for(int i = 0 ; i < numberCards ; i++){
				cardsToSelect[i].setX(cardsToSelect[i].getInitialPosX());
			}
		}if(cardsToSelect[numberCards-1].getX() < cardsToSelect[10].getInitialPosX()){
			for(int i = 0 ; i < numberCards ; i++){
				cardsToSelect[i].setX(cardsToSelect[i].getX()+(cardsToSelect[10].getInitialPosX()-cardsToSelect[numberCards-1].getX()));
			}
		}
	}
}
