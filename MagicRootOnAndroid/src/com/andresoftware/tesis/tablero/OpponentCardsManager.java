package com.andresoftware.tesis.tablero;

import com.andresoftware.tesis.gen.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;

public class OpponentCardsManager {
	private Context context;
	private PlayCard[] opponentCards = new PlayCard[8]; // array that holds the cards
	//----------------------------------------------------------------------------------
	public OpponentCardsManager(Context context) {
		this.context=context;
		Point point9= new Point();
		point9.x = 730;
		point9.y = 295;
		Point point10= new Point();
		point10.x = 661;
		point10.y = 295;
		Point point11= new Point();
		point11.x = 730;
		point11.y = 200;
		Point point12= new Point();
		point12.x = 661;
		point12.y = 200;
		Point point13= new Point();
		point13.x = 730;
		point13.y = 105;
		Point point14= new Point();
		point14.x = 661;
		point14.y = 105;
		Point point15= new Point();
		point15.x = 730;
		point15.y = 10;
		Point point16= new Point();
		point16.x = 661;
		point16.y = 10;


		// declare each card with the color
		opponentCards[0] = new PlayCard(context,R.drawable.yellowcard, point9);
		opponentCards[1] = new PlayCard(context,R.drawable.yellowcard, point10);
		opponentCards[2] = new PlayCard(context,R.drawable.yellowcard, point11);
		opponentCards[3] = new PlayCard(context,R.drawable.yellowcard, point12);
		opponentCards[4] = new PlayCard(context,R.drawable.yellowcard, point13);
		opponentCards[5] = new PlayCard(context,R.drawable.yellowcard, point14);
		opponentCards[6] = new PlayCard(context,R.drawable.yellowcard, point15);
		opponentCards[7] = new PlayCard(context,R.drawable.yellowcard, point16);
		initIds();
		
	}
	//----------------------------------------------------------------------------------
	public void drawCards(Canvas canvas) {
		for (PlayCard ball : opponentCards) {
				ball.drawCard(canvas);
				
			
		}
	}
	//----------------------------------------------------------------------------------
	public void initIds() {
		int id=0;
		for(PlayCard ball: opponentCards){
			ball.setId(id);
			id++;
		}
	}
	//----------------------------------------------------------------------------------
	public PlayCard getCard(int id) {
		return opponentCards[id];
	}
	//----------------------------------------------------------------------------------
	public void moveCard(int id, int x, int y) {
		opponentCards[id].setX(x);
		opponentCards[id].setY(y);
	}
	//----------------------------------------------------------------------------------
}
