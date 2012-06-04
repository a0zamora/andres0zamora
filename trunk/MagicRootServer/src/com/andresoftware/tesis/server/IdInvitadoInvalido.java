package com.andresoftware.tesis.server;

public class IdInvitadoInvalido extends Exception {
	
	public IdInvitadoInvalido( int idInvitado) {
		
		super("Id Invitado no coincide: "+ idInvitado);
	
	}
	
	

	

}
