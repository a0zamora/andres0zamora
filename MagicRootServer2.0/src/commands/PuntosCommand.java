package commands;



import java.util.regex.Pattern;

import constants.TipoDeEvento;




public class PuntosCommand implements Command {

	public static final String CADENA_COMANDO = "puntos";
	
	private int id=0;
	private int azulPts;
	private int rojoPts;
	
	public PuntosCommand(String str) {
		Pattern p = Pattern.compile(" ");

		String[] partes = p.split(str);

		if (partes.length != 4) {
			throw new IllegalArgumentException();
		}

		if (!partes[0].equals(CADENA_COMANDO)) {
			throw new IllegalArgumentException();
		}

		try {
			id = Integer.parseInt(partes[1]);
			azulPts = Integer.parseInt(partes[2]);
			rojoPts = Integer.parseInt(partes[3]);
			
			} catch (NumberFormatException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	
	
	
	
	public PuntosCommand( int azulPts , int rojoPts) {
	
		this.azulPts = azulPts;
		this.rojoPts = rojoPts;
		
	}

	public int getAzulPts() {
		return azulPts;
	}

	public int getRojoPts() {
		return rojoPts;
	}

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
				+ " " +id+ " " + azulPts + " " + rojoPts;
	}

}
