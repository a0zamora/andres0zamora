package com.andresoftware.tesis.tablero;

import java.util.ArrayList;
import java.util.List;

import com.andresoftware.tesis.gen.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;

public class UserCardsManager {
	private Context context;
	private int width;
	private int height;
	private PlayCard[] userCards = new PlayCard[8];
	//----------------------------------------------------------------------------------
	public UserCardsManager(Context context, int width, int height) {
		this.context=context;
		this.width = width;
		this.height = height;
		Point point1 = new Point();
		point1.x = (int) (width * 0.0175);
		point1.y = (int) (height * 0.02);
		Point point2 = new Point();
		point2.x = (int) (width * 0.0175);
		point2.y = (int) (height * 0.22);
		Point point3 = new Point();
		point3.x = (int) (width * 0.0175);
		point3.y = (int) (height * 0.42);
		Point point4 = new Point();
		point4.x = (int) (width * 0.0175);
		point4.y = (int) (height * 0.62);
		Point point5= new Point();
		point5.x = (int) (width * 0.08) + (int) (width * 0.025);
		point5.y = (int) (height * 0.02);
		Point point6= new Point();
		point6.x = (int) (width * 0.08) + (int) (width * 0.025);
		point6.y = (int) (height * 0.22);
		Point point7= new Point();
		point7.x = (int) (width * 0.08) + (int) (width * 0.025);
		point7.y = (int) (height * 0.42);
		Point point8= new Point();
		point8.x = (int) (width * 0.08) + (int) (width * 0.025);
		point8.y = (int) (height * 0.62);
		// declare each card with the color
		userCards[0] = new PlayCard(context,R.drawable.yellowcard, point1, width, height);
		userCards[1] = new PlayCard(context,R.drawable.yellowcard, point2, width, height);
		userCards[2] = new PlayCard(context,R.drawable.yellowcard, point3, width, height);
		userCards[3] = new PlayCard(context,R.drawable.yellowcard, point4, width, height);
		userCards[4] = new PlayCard(context,R.drawable.yellowcard, point5, width, height);
		userCards[5] = new PlayCard(context,R.drawable.yellowcard, point6, width, height);
		userCards[6] = new PlayCard(context,R.drawable.yellowcard, point7, width, height);
		userCards[7] = new PlayCard(context,R.drawable.yellowcard, point8, width, height);
		
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