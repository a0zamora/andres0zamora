package com.andresoftware.tesis.tablero;

import java.util.ArrayList;
import java.util.List;

import com.examples.dragdrop.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;

public class TableGame {
	private TableCard[] tableCards = new TableCard[16];
	private Context context;

	//----------------------------------------------------------------------------------
	public TableGame(Context context) {
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
		tableCards[0] = new TableCard(context,R.drawable.yellowcard, point1);
		tableCards[1] = new TableCard(context,R.drawable.yellowcard, point2);
		tableCards[2] = new TableCard(context,R.drawable.yellowcard, point3);
		tableCards[3] = new TableCard(context,R.drawable.yellowcard, point4);
		tableCards[4] = new TableCard(context,R.drawable.yellowcard, point5);
		tableCards[5] = new TableCard(context,R.drawable.yellowcard, point6);
		tableCards[6] = new TableCard(context,R.drawable.yellowcard, point7);
		tableCards[7] = new TableCard(context,R.drawable.yellowcard, point8);
		tableCards[8] = new TableCard(context,R.drawable.yellowcard, point9);
		tableCards[9] = new TableCard(context,R.drawable.yellowcard, point10);
		tableCards[10] = new TableCard(context,R.drawable.yellowcard, point11);
		tableCards[11] = new TableCard(context,R.drawable.yellowcard, point12);
		tableCards[12] = new TableCard(context,R.drawable.yellowcard, point13);
		tableCards[13] = new TableCard(context,R.drawable.yellowcard, point14);
		tableCards[14] = new TableCard(context,R.drawable.yellowcard, point15);
		tableCards[15] = new TableCard(context,R.drawable.yellowcard, point16);
		initIds();
	}
	//----------------------------------------------------------------------------------
	public TableCard[] getTableCards() {
		return tableCards;
	}
	//----------------------------------------------------------------------------------
	public void setTableCards(TableCard[] tableCards) {
		this.tableCards = tableCards;
	}
	//----------------------------------------------------------------------------------
	public void drawCards(Canvas canvas) {
		for (TableCard card : tableCards) {
			if(card.isEnable()){
				card.drawBall(canvas);	
			}
			
		}
	}
	//----------------------------------------------------------------------------------
		public int getId(int x, int y) {
			
			int returnId=-1;
			for (TableCard tableCard : tableCards) {
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
						returnId = tableCard.getId();
						tableCard.setEnable(false);
						break;
					}
				}
			}
			return returnId;
		}
		//----------------------------------------------------------------------------------
		public void initIds() {
			int id=0;
			for(TableCard card: tableCards){
				card.setId(id);
				id++;
			}
		}
		//----------------------------------------------------------------------------------
		public int getCoorX(int id) {
			return tableCards[id].getX();
		}
		//----------------------------------------------------------------------------------
		public int getCoorY(int id) {
			return tableCards[id].getY();
		}
		//----------------------------------------------------------------------------------
		public void setUnableCardTable(int id) {
			tableCards[id].setEnable(false);
		}
		//----------------------------------------------------------------------------------
}
