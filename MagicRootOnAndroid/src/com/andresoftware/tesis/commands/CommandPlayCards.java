package com.andresoftware.tesis.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CommandPlayCards implements CommandInterface {

	public static final String CADENA_COMANDO = "CardsListUsr";
	private List<CardTO> cardsList = new ArrayList<CardTO>();
	private int numberOfCards = 0;
	
	public List<CardTO> getCardsList() {
		return cardsList;
	}

	public void setCardsList(List<CardTO> cardsList) {
		this.cardsList = cardsList;
	}

	public int getNumberOfCards() {
		return numberOfCards;
	}

	public void setNumberOfCards(int numberOfCards) {
		this.numberOfCards = numberOfCards;
	}

	public CommandPlayCards(String str) {
		Pattern p = Pattern.compile(" ");
		String[] partes = p.split(str);
		numberOfCards = Integer.parseInt(partes[1]);
		int countAux=2;
		for(int i = 0; i < numberOfCards; i++){
			CardTO cardAux = new CardTO();
			cardAux.setFnorth(Integer.parseInt(partes[countAux]));
			countAux++;
			cardAux.setFsouth(Integer.parseInt(partes[countAux]));
			countAux++;
			cardAux.setFeast(Integer.parseInt(partes[countAux]));
			countAux++;
			cardAux.setFwest(Integer.parseInt(partes[countAux]));
			countAux++;
			cardAux.setElement(partes[countAux]);
			countAux++;
			cardsList.add(cardAux);
		}
	}
	
	@Override
	public String convertirAString() {
		String information= CADENA_COMANDO+" "+numberOfCards;
		for(int i=0; i<cardsList.size() ; i++){
			information+= " "+ cardsList.get(i).getFnorth()+" "+
					cardsList.get(i).getFsouth()+" "+
					cardsList.get(i).getFeast()+" "+
					cardsList.get(i).getFwest()+" "+
					cardsList.get(i).getElement();
		}
		return information;
	}
	public CommandPlayCards() {}

	
}
