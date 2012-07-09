package commands;

import java.util.regex.Pattern;

import constants.TipoDeEvento;


public class HandCheck implements Command{
	
	public static final String CADENA_COMANDO = "HandCheck";
	private int id;
	
	public HandCheck(String str) {	
		Pattern p = Pattern.compile(" ");

		String[] partes = p.split(str);

		if (partes.length != 2) {
			throw new IllegalArgumentException();
		}

		if (!partes[0].equals(CADENA_COMANDO)) {
			throw new IllegalArgumentException();
		}

		try {
			id =Integer.parseInt(partes[1]);
			} catch (NumberFormatException e) {
			throw new IllegalArgumentException(e);
		}
	}

	// --------------------------------------------------------------------------------
	//constructor parametrico
	public HandCheck( int id) {
		this.id = id;
	}
	
	//------------------------Sobre Escribiendo metodos--------
	
	@Override
	public TipoDeEvento getTipoDeEvento() {
		return null;
	}

	@Override
	public int getId() {
		return 0;
	}

	@Override
	public String convertirAString() {
		return CADENA_COMANDO //
				+  " " + id ;}
	
}
