package com.andresoftware.tesis.statemachine;

import com.andresoftware.tesis.statemachine.StateMachine.TipoDeEstado;

import constants.TipoDeEvento;

public class UnknowEventForState extends Exception {
	
	public UnknowEventForState(TipoDeEvento event, TipoDeEstado state ) {
	
		super("Estado " + state +   " no Admite : "+ event);
	}
}
