package commands;

import java.util.regex.Pattern;

import constants.TipoDeEvento;


public class RojoJugarCommand implements Command {
	
	public static final String CADENA_COMANDO = "jugar";

	public static final TipoDeEvento TIPO_DE_EVENTO = TipoDeEvento.juega_r;

	private int id;
	private int idCarta;
	private int x;
	private int y;
	private Command cartaComando;
	
	public RojoJugarCommand(String str) {
		Pattern p = Pattern.compile(" ");

		String[] partes = p.split(str);

		if (partes.length != 12) {
			System.out.println("------------");
			System.out.println(1);
			System.out.println("------------");
			throw new IllegalArgumentException();
		}

		if (!partes[0].equals(CADENA_COMANDO)) {
			System.out.println("------------");
			System.out.println(2);
			System.out.println("------------");
			throw new IllegalArgumentException();
		}

		if (!partes[5].equals(CartaCommand.CADENA_COMANDO)) {
			System.out.println("------------");
			System.out.println(3);
			System.out.println("------------");
			throw new IllegalArgumentException();
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
			System.out.println("------------");
			System.out.println(4);
			System.out.println("------------");
			throw new IllegalArgumentException(e);
		}
	}

	// --------------------------------------------------------------------------------

	public RojoJugarCommand(int id, int idCarta, int x, int y) {
		this.id=id;
		this.idCarta = idCarta;
		this.x = x;
		this.y = y;
		
	}

	// --------------------------------------------------------------------------------

		public int getIdCarta() {
		return idCarta;
	}

	public void setIdCarta(int idCarta) {
		this.idCarta = idCarta;
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
		return TIPO_DE_EVENTO;
	}

	@Override
	public String convertirAString() {
		return CADENA_COMANDO + " " + //
				id + " " + idCarta + x + " " + y + " " + cartaComando.convertirAString() ;
		
	}
	
	@Override
	public int getId() {
		
		return id;
	}	
	
}