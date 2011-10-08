package union;

import union.MaquinaEstados.TipoDeEstado;
import constants.TipoDeEvento;

public class UnknowEventForState extends Exception {
	
	public UnknowEventForState(TipoDeEvento event, TipoDeEstado state ) {
	
		super("Estado " + state +   " no Admite : "+ event);
	}
}
