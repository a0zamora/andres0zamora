package commands;

import java.util.regex.Pattern;

import constants.TipoDeEvento;


public class ServerKick implements Command{
	

	public static final String CADENA_COMANDO = "kick";
	
	private int id;	
	
	
	//public static final TipoDeEvento TIPO_DE_EVENTO = TipoDeEvento.jugadorListo;

	public ServerKick(String str) {
		
		Pattern p = Pattern.compile(" ");

		String[] partes = p.split(str);

		if (partes.length != 2) {
			throw new IllegalArgumentException();
		}

		if (!partes[0].equals(CADENA_COMANDO)) {
			throw new IllegalArgumentException();
		}

		try {
			id = Integer.parseInt( partes[1]);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public ServerKick(int id){
		this.id = id;
		
	};
	
	private void setId(int id) {
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
