package commands;

import java.awt.IllegalComponentStateException;
import java.nio.channels.IllegalBlockingModeException;
import java.nio.channels.IllegalSelectorException;
import java.util.regex.Pattern;

import constants.TipoDeEvento;


public class JugarCommand implements Command {

	public static final String CADENA_COMANDO = "jugar";


	private int id;
	private int x;
	private int y;
	private int idCarta;
	private Command cartaComando;
	
	
	
	public JugarCommand(String str) {
		Pattern p = Pattern.compile(" ");

		String[] partes = p.split(str);

		if (partes.length != 12) {
			throw new IllegalBlockingModeException();
		}

		if (!partes[0].equals(CADENA_COMANDO)) {
			throw new IllegalStateException();
		}
		if (!partes[5].equals(CartaCommand.CADENA_COMANDO)) {
			throw new IllegalComponentStateException();	
			}
		
		try {
			id = Integer.parseInt(partes[1]);
			idCarta = Integer.parseInt(partes[2]);
			x = Integer.parseInt(partes[3]);
			y = Integer.parseInt(partes[4]);
			int idrepetido = Integer.parseInt(partes[6]);
			int fn = Integer.parseInt(partes[7]);
			int  fs = Integer.parseInt(partes[8]);
			int fe = Integer.parseInt(partes[9]);
			int fo = Integer.parseInt(partes[10]);
			int elemento = Integer.parseInt( partes[11]);
			cartaComando = new CartaCommand(idrepetido, fn, fs, fe, fo, elemento);
			} catch (NumberFormatException e) {
			throw new IllegalSelectorException();
		}
	}

	// --------------------------------------------------------------------------------
	public JugarCommand(int id, int idCarta, int x, int y) {
		
		this.id=id;
		this.idCarta = idCarta;
		this.x = x;
		this.y = y;
		this.cartaComando = new CartaCommand(0, 0, 0, 0, 0, 0);
	}
	
	// --------------------------------------------------------------------------------

	public JugarCommand(int id, int idCarta, int x, int y, Command cartaComando) {
		
		this.id=id;
		this.idCarta = idCarta;
		this.x = x;
		this.y = y;
		this.cartaComando = cartaComando;

	}
	// --------------------------------------------------------------------------------

	public int getIdCarta() {
		return idCarta;
	}

	public void setIdCarta(int idCarta) {
		this.idCarta = idCarta;
	}

	public Command getCartaComando(){
		return cartaComando;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	
	// --------------------------------------------------------------------------------

	@Override
	public TipoDeEvento getTipoDeEvento() {
		return null;
	}

	@Override
	public String convertirAString() {
		return CADENA_COMANDO + " " + //
	
		id + " " + idCarta +" "+x + " " + y + " " + cartaComando.convertirAString();
		
	}
	
	@Override
	public int getId() {
		
		return id;
	}

}