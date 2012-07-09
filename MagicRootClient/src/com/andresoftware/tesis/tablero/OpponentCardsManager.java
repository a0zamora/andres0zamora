package com.andresoftware.tesis.tablero;

import com.andresoftware.tesis.gen.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;

public class OpponentCardsManager {
	private Context context;
	private PlayCard[] opponentCards = new PlayCard[8]; // array that holds the cards
	//----------------------------------------------------------------------------------
	public OpponentCardsManager(Context context, int width, int height) {
		this.context=context;
		Point point9= new Point();
		point9.x = width - ((int) (width * 0.0175)+(int) (width * 0.08));
		point9.y = (int) (height * 0.62);
		Point point10= new Point();
		point10.x = width - ((int) (width * 0.16) + (int) (width * 0.025));
		point10.y = (int) (height * 0.62);
		Point point11= new Point();
		point11.x = width - ((int) (width * 0.0175)+(int) (width * 0.08));
		point11.y = (int) (height * 0.42);
		Point point12= new Point();
		point12.x = width - ((int) (width * 0.16) + (int) (width * 0.025));
		point12.y = (int) (height * 0.42);
		Point point13= new Point();
		point13.x = width - ((int) (width * 0.0175)+(int) (width * 0.08));
		point13.y = (int) (height * 0.22);
		Point point14= new Point();
		point14.x = width - ((int) (width * 0.16) + (int) (width * 0.025));
		point14.y = (int) (height * 0.22);
		Point point15= new Point();
		point15.x = width - ((int) (width * 0.0175)+(int) (width * 0.08));
		point15.y = (int) (height * 0.02);
		Point point16= new Point();
		point16.x = width - ((int) (width * 0.16) + (int) (width * 0.025));
		point16.y = (int) (height * 0.02);


		// declare each card with the color
		opponentCards[0] = new PlayCard(context,R.drawable.yellowcard, point9, width, height);
		opponentCards[1] = new PlayCard(context,R.drawable.yellowcard, point10, width, height);
		opponentCards[2] = new PlayCard(context,R.drawable.yellowcard, point11, width, height);
		opponentCards[3] = new PlayCard(context,R.drawable.yellowcard, point12, width, height);
		opponentCards[4] = new PlayCard(context,R.drawable.yellowcard, point13, width, height);
		opponentCards[5] = new PlayCard(context,R.drawable.yellowcard, point14, width, height);
		opponentCards[6] = new PlayCard(context,R.drawable.yellowcard, point15, width, height);
		opponentCards[7] = new PlayCard(context,R.drawable.yellowcard, point16, width, height);
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
		for(PlayCard card: opponentCards){
			card.setId(id);
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
