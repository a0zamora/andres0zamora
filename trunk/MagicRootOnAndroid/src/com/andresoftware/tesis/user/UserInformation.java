package com.andresoftware.tesis.user;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.andresoftware.tesis.commands.CardTO;
import com.andresoftware.tesis.tablero.PlayCard;

public class UserInformation {
	
	private List<PlayCard> cardsList = new ArrayList<PlayCard>();
	private String usrName;
	private int usrId;
	public List<PlayCard> getCardsList() {
		return cardsList;
	}
	public void setCardsList(List<PlayCard> cardsList) {
		this.cardsList = cardsList;
	}
	public String getUsrName() {
		return usrName;
	}
	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}
	public int getUsrId() {
		return usrId;
	}
	public void setUsrId(int usrId) {
		this.usrId = usrId;
	}
	public void addCard(CardTO cardTO, int width, int height, Context context) {
		PlayCard cardAux= new PlayCard(width, height, context);
		cardAux.setNorthPower(cardTO.getFnorth());
		cardAux.setSouthPower(cardTO.getFsouth());
		cardAux.setEastPower(cardTO.getFeast());
		cardAux.setWestPower(cardTO.getFwest());
		cardAux.setCenter(cardTO.getElement());
		
		cardsList.add(cardAux);
		
	}

}
