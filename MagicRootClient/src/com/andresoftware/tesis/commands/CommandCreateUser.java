package com.andresoftware.tesis.commands;

import java.util.regex.Pattern;

public class CommandCreateUser implements CommandInterface {

	public static final String CADENA_COMANDO = "CreateUser";
	private String name;
	private String password;
	
	public CommandCreateUser(String str) {
		Pattern p = Pattern.compile(" ");
		String[] partes = p.split(str);
		setName(partes[1]);
		setPassword(partes[2]);	
	}
	
	@Override
	public String convertirAString() {
		return CADENA_COMANDO+" "+name+" "+password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
