package commands;

import constants.TipoDeEvento;

public interface Command {

	public TipoDeEvento getTipoDeEvento();
	
	public int getId();
	
	public String convertirAString();
}
