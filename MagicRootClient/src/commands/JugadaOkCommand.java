package commands;

import java.util.regex.Pattern;

import constants.TipoDeEvento;

public class JugadaOkCommand implements Command {
	
	public static final String CADENA_COMANDO = "jugadaOk";

	private int id;
	private int x;
	private int y;
	private int fn;
	private int fs;
	private int fe;
	private int fo;
	private int elemento;

	public JugadaOkCommand(String str) {
		Pattern p = Pattern.compile(" ");

		String[] partes = p.split(str);

		if (partes.length != 9) {
			throw new IllegalArgumentException();
		}

		if (!partes[0].equals("jugadaOK")) {
			throw new IllegalArgumentException();
		}

		try {
			id = Integer.parseInt(partes[1]);
			x = Integer.parseInt(partes[2]);
			y = Integer.parseInt(partes[3]);
			fn = Integer.parseInt(partes[4]);
			fs = Integer.parseInt(partes[5]);
			fe = Integer.parseInt(partes[6]);
			fo = Integer.parseInt(partes[7]);
			elemento = Integer.parseInt(partes[8]);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(e);
		}
	}

	// --------------------------------------------------------------------------------

	public JugadaOkCommand(int id, int x, int y, int fn, int fs, int fe,
			int fo, int elemento) {
		
		this.id = id;
		this.x = x;
		this.y = y;
		this.fn = fn;
		this.fs = fs;
		this.fe = fe;
		this.fo = fo;
		this.elemento = elemento;

	}

//	public JugadaOkCommand(int id, Card carta, int x, int y) {
//		this.id = id;
//		this.x = x;
//		this.y = y;
//		this.fn = carta.getFuerzaN();
//		this.fs = carta.getFuerzaS();
//		this.fe = carta.getFuerzaE();
//		this.fo = carta.getFuerzaO();
//
//		Elemento elemento = carta.getElemento();
//
//		switch (elemento) {
//		case agua:
//			System.out.println("agua");
//			this.elemento = 0;
//			break;
//		case fuego:
//			System.out.println("fuego");
//			this.elemento = 1;
//			break;
//		case aire:
//			System.out.println("aire");
//			this.elemento = 2;
//			break;
//		case tierra:
//			System.out.println("tierra");
//			this.elemento = 3;
//			break;
//		default:
//			throw new IllegalArgumentException();
//		}
//
//	}

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
		return CADENA_COMANDO + " "
				+ //
				id + " " + x + " " + y + " " + fn + " " + fs + " " + fe + " "
				+ fo + " " + elemento;

	}


	
}
