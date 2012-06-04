package com.andresoftware.tesis.commands;

import java.util.regex.Pattern;

public class CommandCreateUserAnswer implements CommandInterface {

	public static final String CADENA_COMANDO = "CreateUserAnswer";
	private String answer;
	
	public CommandCreateUserAnswer(String str) {
		Pattern p = Pattern.compile(" ");
		String[] partes = p.split(str);
		setAnswer(partes[1]);
	}
	
	@Override
	public String convertirAString() {
		return CADENA_COMANDO+" "+answer;
	}


	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
}