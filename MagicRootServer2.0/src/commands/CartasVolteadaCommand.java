package commands;

import java.util.regex.Pattern;

import constants.TipoColor;
import constants.TipoDeEvento;


public class CartasVolteadaCommand implements Command {

	public static final String CADENA_COMANDO = "Carta_Volteada";

	private int idCarta;
	private int x;
	private int y;
	private int color;
	private CartaCommand cartaComando;

	public CartasVolteadaCommand(String str) {
		Pattern p = Pattern.compile(" ");
		String[] partes = p.split(str);

		if (partes.length != 12 ) {
			throw new IllegalArgumentException();
		}

		if (!partes[0].equals(CADENA_COMANDO)) {
			throw new IllegalArgumentException();
		}

		if (!partes[5].equals(CartaCommand.CADENA_COMANDO)) {
			throw new IllegalArgumentException();
		}
		
		try {
			idCarta = Integer.parseInt(partes[1]);
			x = Integer.parseInt(partes[2]);
			y = Integer.parseInt(partes[3]);
			color = Integer.parseInt(partes[4]);
			
			int id = Integer.parseInt(partes[6]);
			int fn = Integer.parseInt(partes[7]);
			int fs = Integer.parseInt(partes[8]);
			int fe = Integer.parseInt(partes[9]);
			int fo = Integer.parseInt(partes[10]);
			int elemento = Integer.parseInt( partes[11]);
			cartaComando = new CartaCommand(id, fn, fs, fe, fo, elemento);
				
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/***
	 * ------------------------------------------------------ elemento falta
	 * validar estoo!!! } catch (NumberFormatException e) { throw new
	 * IllegalArgumentException(e); } }
	 */
	// --------------------------------------------------------------------------------

	public CartasVolteadaCommand(int id, int x, int y, TipoColor color, Command cartaComando) {

		this.idCarta = id;
		this.x = x;
		this.y = y;
		if (color.equals(TipoColor.AZUL)) {
			this.color = 1;
		} else {
			this.color = 2;
		}
		
		this.cartaComando = (CartaCommand) cartaComando;
	}

	@Override
	public TipoDeEvento getTipoDeEvento() {

		return null;
	}

	@Override
	public int getId() {

		return idCarta;
	}
	
	public int getIdCarta() {
		return idCarta;
	}

	public void setIdCarta(int idCarta) {
		this.idCarta = idCarta;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public Command getCartaCommand(){
		return cartaComando;
	}
	
	public int getColor() {
		return this.color;
	}

	@Override
	public String convertirAString() {
		return CADENA_COMANDO + " " + //
				idCarta + " " + x + " " + y + " " + color + " " + cartaComando.convertirAString();
	}

}
