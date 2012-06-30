package com.andresoftware.tesis.commands;

import java.util.regex.Pattern;

public class CommandUserInformation implements CommandInterface{
	public static final String CADENA_COMANDO = "UserInformationCommand";
	int id;
	String userName;
	CommandPlayCards userCards;
	
	public CommandUserInformation(String text) {
		Pattern p = Pattern.compile(" ");
		String[] partes = p.split(text);
		id = Integer.parseInt(partes[1]);
		userName = partes[2];
		CommandPlayCards userCardsAux = new CommandPlayCards(text);
		this.userCards = userCardsAux;
	}
	public CommandUserInformation() {/*Empty*/}
	@Override
	public String convertirAString() {
		return CADENA_COMANDO+" "+id+" "+userName+userCards.convertirAString();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public CommandPlayCards getUserCards() {
		return userCards;
	}

	public void setUserCards(CommandPlayCards userCards) {
		this.userCards = userCards;
	}

}
