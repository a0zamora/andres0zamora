package commands;

import java.util.regex.Pattern;

import constants.TipoDeEvento;

public class EliminarCommand implements Command{
	public static final String CADENA_COMANDO = "Eliminar";
	private int id;

	public EliminarCommand(String text) {

		Pattern p = Pattern.compile(" ");
		String[] partes = p.split(text);
		id = Integer.parseInt( partes[1]);
	}

	@Override
	public String convertirAString() {
		return CADENA_COMANDO+" "+id;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public TipoDeEvento getTipoDeEvento() {
		return null;
	}

}
