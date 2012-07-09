package com.andresoftware.tesis.statemachine;

public class IdCreatorInvalid extends Exception {
	
	public IdCreatorInvalid( int idCreador) {
		
		super("Id creador no coincide: "+ idCreador);
	
	}

}
