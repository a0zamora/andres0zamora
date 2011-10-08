package commands;

import java.util.regex.Pattern;

import constants.TipoDeEvento;

/**
 * Clase que contiene el id y las pocisiones x & y de la carta que no posee el jugador  
 * @author Rafael
 * 
 */
public class CartaInvalidaCommand implements Command {
	
	public static final String CADENA_COMANDO = "CartaInvalida";

	private int id;
	private int x;
	private int y;
	
	
	public CartaInvalidaCommand(String str) {
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
			x = Integer.parseInt(partes[2]);
			y = Integer.parseInt(partes[3]);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(e);
		}
	}

	// --------------------------------------------------------------------------------

	public CartaInvalidaCommand(int id, int x, int y) {
		
		this.id = id;
		this.x = x;
		this.y = y;
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
		
		return CADENA_COMANDO + " " + id  + " " + x + " " + y;
	}


}
