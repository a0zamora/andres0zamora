package commands;

import java.util.regex.Pattern;

import constants.TipoDeEvento;


public class PerdedorCommand implements Command {
	
	public static final String CADENA_COMANDO = "perdedor";
	
	private int idJugador;
	private int pts;
	private int numeroLineas;
	private int numeroDiagonales;
	
	
	public PerdedorCommand(String str) {
		Pattern p = Pattern.compile(" ");
		String[] partes = p.split(str);

		if (partes.length != 5) {

			throw new IllegalArgumentException();
		}

		if (!partes[0].equals(CADENA_COMANDO)) {

			throw new IllegalArgumentException();
		}

		try {
			idJugador = Integer.parseInt(partes[1]);
			pts = Integer.parseInt(partes[2]);
			numeroLineas = Integer.parseInt(partes[3]);
			numeroDiagonales =  Integer.parseInt(partes[4]);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	public PerdedorCommand(int idJugador, int pts , int numeroLineas, int numeroDiagonales) {
		
		this.idJugador = idJugador;
		this.numeroDiagonales =  numeroDiagonales;
		this.numeroLineas = numeroLineas;
		this.pts = pts;
		
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
				+ " " + idJugador + " " + pts + " " +numeroLineas + " " + numeroDiagonales ;
	}

	
}
