package com.andresoftware.tesis.statemachine;

public class IdInvitatedInvalid extends Exception {
	
	public IdInvitatedInvalid( int idInvitado) {
		
		super("Id Invitado no coincide: "+ idInvitado);
	
	}
	
	

	

}
