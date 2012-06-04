package com.andresoftware.tesis.server;


import com.andresoftware.tesis.server.MaquinaEstados.TipoDeEstado;

import constants.TipoDeEvento;

public class UnknowEventForState extends Exception {
	
	public UnknowEventForState(TipoDeEvento event, TipoDeEstado state ) {
	
		super("Estado " + state +   " no Admite : "+ event);
	}
}
