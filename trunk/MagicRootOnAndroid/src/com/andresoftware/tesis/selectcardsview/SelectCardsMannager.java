package com.andresoftware.tesis.selectcardsview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.Display;

import com.andresoftware.tesis.gen.R;
import com.andresoftware.tesis.tablero.PlayCard;
import com.andresoftware.tesis.user.UserInformation;

public class SelectCardsMannager {
	int numberCards = 0;
	private PlayCard[] cardsToSelect = null;
	private Point[] points = null;
	private int panelHeight;

	//----------------------------------------------------------------------------------
	public SelectCardsMannager(Context context, int width, int height, UserInformation usrInformation) {
		points = new Point[usrInformation.getCardsList().size()];
		cardsToSelect = new PlayCard[usrInformation.getCardsList().size()];
		numberCards = usrInformation.getCardsList().size();
		panelHeight = (int)(height*0.05);
		int aux= (int)(width*0.09);
		for(int i=0; i<usrInformation.getCardsList().size(); i++){
			points[i] = new Point();
			points[i].x = (int)(width*0.01) +( i * aux );
			points[i].y = (int)(height*0.05);
//			cardsToSelect[i] = new PlayCard(context,R.drawable.yellowcard, points[i], width, height);
			cardsToSelect[i] = usrInformation.getCardsList().get(i);
			cardsToSelect[i].startCard(points[i], width, height);
			cardsToSelect[i].setId(i);
		}
//		movePanel(-2);
	}
	//----------------------------------------------------------------------------------
	public void drawCards(Canvas canvas) {
		for (PlayCard card : cardsToSelect) {
			card.drawCard(canvas);
		}
	}
	//----------------------------------------------------------------------------------
	public boolean touchSelectCardsPanel(int y) {
		if(y<(cardsToSelect[0].getHeight()+panelHeight) && y > panelHeight){
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
		}if(cardsToSelect[0].getX()> cardsToSelect[0].getInitialPosX()){
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
