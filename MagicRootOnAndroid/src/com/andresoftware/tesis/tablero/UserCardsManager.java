package com.andresoftware.tesis.tablero;

import java.util.ArrayList;
import java.util.List;

import com.andresoftware.tesis.gen.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;

public class UserCardsManager {
	private Context context;
	private List<PlayCard> userCards = new ArrayList<PlayCard>();
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
		PlayCard userCards0 = new PlayCard(context,R.drawable.yellowcard, point1);
		PlayCard userCards1 = new PlayCard(context,R.drawable.yellowcard, point2);
		PlayCard userCards2 = new PlayCard(context,R.drawable.yellowcard, point3);
		PlayCard userCards3 = new PlayCard(context,R.drawable.yellowcard, point4);
		PlayCard userCards4 = new PlayCard(context,R.drawable.yellowcard, point5);
		PlayCard userCards5 = new PlayCard(context,R.drawable.yellowcard, point6);
		PlayCard userCards6 = new PlayCard(context,R.drawable.yellowcard, point7);
		PlayCard userCards7 = new PlayCard(context,R.drawable.yellowcard, point8);
		userCards.add(userCards0);
		userCards.add(userCards1);
		userCards.add(userCards2);
		userCards.add(userCards3);
		userCards.add(userCards4);
		userCards.add(userCards5);
		userCards.add(userCards6);
		userCards.add(userCards7);
		initIds();
	}
	//----------------------------------------------------------------------------------
	public void drawCards(Canvas canvas) {
		for (PlayCard card : userCards) {
			if(card.isEnable()){
				card.drawCard(canvas);
			}
		}
	}
	//----------------------------------------------------------------------------------
	public PlayCard getId(int x, int y) {

		PlayCard cardReturn = null;
		int i=0;
		for (PlayCard card : userCards) {
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
			i++;
		}
		return cardReturn;
	}
	//----------------------------------------------------------------------------------
	public PlayCard getCard(int x, int y) {

		PlayCard cardReturn = null;
		int i=0;
		for (PlayCard card : userCards) {
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
			i++;
		}
		return cardReturn;
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
	public void setCard(PlayCard card) {
		userCards.add(card);
	}
}