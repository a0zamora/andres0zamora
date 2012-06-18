package com.andresoftware.tesis.selectcardsview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.Display;
import android.widget.Toast;

import com.andresoftware.tesis.gen.R;
import com.andresoftware.tesis.tablero.PlayCard;
import com.andresoftware.tesis.user.UserInformation;

public class SelectCardsMannager {
	int numberCards = 0;
	private PlayCard[] cardsToSelect = null;
	private Point[] points = null;
	private int panelHeight;
	private Context context;

	//----------------------------------------------------------------------------------
	public SelectCardsMannager(Context context, int width, int height, UserInformation usrInformation) {
		this.context = context;
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
	public PlayCard onCard(int x, int y) {


		PlayCard cardReturn = null;
		for (PlayCard card : cardsToSelect) {

				if (card.isEnable()){
					// check if inside the bounds of the card
					// get the center for the ball
					int centerX = card.getX() + (card.getWidth()/2);
					int centerY = card.getY() + (card.getHeight()/2);

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
						//					cardReturn = userCards.remove(i);

//						Toast.makeText(context, "MagicRoot", 
//								Toast.LENGTH_SHORT).show();
						card.setEnable(false);
						cardReturn = card;
						break;
					}
				}
		}
		return cardReturn;		
	}
}
