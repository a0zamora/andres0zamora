package com.andresoftware.tesis.tablero;

import java.util.ArrayList;
import java.util.List;

import com.andresoftware.tesis.gen.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;

public class UserCardsManager {
	private Context context;
	private PlayCard[] userCards = new PlayCard[8];
	//----------------------------------------------------------------------------------
	public UserCardsManager(Context context) {
		this.context=context;
		Point point1 = new Point();
		point1.x = 5;
		point1.y = 10;
		Point point2 = new Point();
		point2.x = 5;
		point2.y = 105;
		Point point3 = new Point();
		point3.x = 5;
		point3.y = 200;
		Point point4 = new Point();
		point4.x = 5;
		point4.y = 295;
		Point point5= new Point();
		point5.x = 74;
		point5.y = 10;
		Point point6= new Point();
		point6.x = 74;
		point6.y = 105;
		Point point7= new Point();
		point7.x = 74;
		point7.y = 200;
		Point point8= new Point();
		point8.x = 74;
		point8.y = 295;
		// declare each card with the color
		userCards[0] = new PlayCard(context,R.drawable.yellowcard, point1);
		userCards[1] = new PlayCard(context,R.drawable.yellowcard, point2);
		userCards[2] = new PlayCard(context,R.drawable.yellowcard, point3);
		userCards[3] = new PlayCard(context,R.drawable.yellowcard, point4);
		userCards[4] = new PlayCard(context,R.drawable.yellowcard, point5);
		userCards[5] = new PlayCard(context,R.drawable.yellowcard, point6);
		userCards[6] = new PlayCard(context,R.drawable.yellowcard, point7);
		userCards[7] = new PlayCard(context,R.drawable.yellowcard, point8);
		
		initIds();
	}
	//----------------------------------------------------------------------------------
	public void drawCards(Canvas canvas) {
		for (PlayCard card : userCards) {
			if(card!=null){
				card.drawCard(canvas);
			}
		}
	}
	//----------------------------------------------------------------------------------
	public PlayCard getId(int x, int y) {

		PlayCard cardReturn = null;
		int i=0;
		for (PlayCard card : userCards) {
			if(card!=null){

				if (card.isEnable()){
					// check if inside the bounds of the card
					// get the center for the ball
					int centerX = card.getX() + 32;
					int centerY = card.getY() + 42;

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
					if (radCardX <= 32 && radCardY <= 42){
						//					cardReturn = userCards.remove(i);
						cardReturn = card;
						break;
					}
				}
			}
			i++;
		}
		return cardReturn;
	}
	//----------------------------------------------------------------------------------
	public PlayCard getCard(int x, int y) {

		PlayCard cardReturn = null;
		for (PlayCard card : userCards) {
			if(card!=null){
				if (card.isEnable()){
					// check if inside the bounds of the card
					// get the center for the ball
					int centerX = card.getX() + 32;
					int centerY = card.getY() + 42;

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
					if (radCardX <= 32 && radCardY <= 42){
						cardReturn = card;
						break;
					}
				}
			}
			
		}
		return cardReturn;
	}
	//----------------------------------------------------------------------------------
	public void setCardNull(int x, int y) {
		int i=0;
		for (PlayCard card : userCards) {
			if(card!=null){

				if (card.isEnable()){
					// check if inside the bounds of the card
					// get the center for the ball
					int centerX = card.getX() + 32;
					int centerY = card.getY() + 42;

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
					if (radCardX <= 32 && radCardY <= 42){
						userCards[i]=null;
						break;
					}
				}
			}
			i++;
		}
	}
	//----------------------------------------------------------------------------------
	public void initIds() {
		int id=0;
		for(PlayCard ball: userCards){
			ball.setId(id);
			id++;
		}
	}
	//----------------------------------------------------------------------------------
	public void setCard(PlayCard cardAux) {
		int i=0;
		for (PlayCard card : userCards) {
			if(card==null){
				userCards[i]=cardAux;
				break;
			}
			i++;
		}
	}
}