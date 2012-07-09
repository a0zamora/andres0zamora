package commands;

import java.util.regex.Pattern;

import constants.TipoDeEvento;


public class TimeOutCommand implements Command {

	public static final String CADENA_COMANDO = "timeout";

	private int id;

	public TimeOutCommand(String str) {

		Pattern p = Pattern.compile(" ");

		String[] partes = p.split(str);

		if (partes.length != 2) {
			throw new IllegalArgumentException();
		}

		if (!partes[0].equals("timeout")) {
			throw new IllegalArgumentException();
		}

		try {
			id = Integer.parseInt(partes[1]);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	
	public TimeOutCommand(int id ) {

		this.id = id;

	}

	@Override
	public TipoDeEvento getTipoDeEvento() {
		return null;
	}

	@Override
	public int getId() {
		return id;
	}
	@Override
	public String convertirAString() {
		return CADENA_COMANDO + " " + id;
	}

}
