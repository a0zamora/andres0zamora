package constants;

import constants.TipoDeEvento;

public class UnknowEventForState extends Exception {
	public enum TipoDeEstado {
		ESTADO_INI, 
		RANDOM_SELECT, 
		TURNA, TURNR, 
		VALIDAJUGADA_A, 
		VALIDAJUGADA_R, 
		ESTADO_FINAL, 
		PARTIDA_CREADA, 
		ELIGIENDO_CARTAS, 
		JUGADOR_PRINCIPAL_RDY, 
		JUGADOR_INVITADO_RDY, 
		ROJO_GANADOR, AZUL_GANADOR, 
		ELIGUIENDO_TABLERO_AZUL, 
		ELIGUIENDO_TABLERO_ROJO, 
		PROCESAR_REVANCHA_ROJO, 
		PROCESAR_REVANCHA_AZUL
	};
	public UnknowEventForState(TipoDeEvento event, TipoDeEstado state ) {
	
		super("Estado " + state +   " no Admite : "+ event);
	}
}
