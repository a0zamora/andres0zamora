package com.andresoftware.tesis.commands;

import java.util.regex.Pattern;

public class CommandLoginAnswer implements CommandInterface{

	public static final String CADENA_COMANDO = "LoginInformationAnswer";
	private String enter;
	
	public CommandLoginAnswer(String str) {
		Pattern p = Pattern.compile(" ");
		String[] partes = p.split(str);
		enter = partes[1];
			
	}
	@Override
	public String convertirAString() {
		// TODO Auto-generated method stub
		return CADENA_COMANDO+" "+enter;
	}
	
	public boolean isMember() {
		if(enter.equals("true")){
			return true;
		}else{
			return false;
		}
	}

}
