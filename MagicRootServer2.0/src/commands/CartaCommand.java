package commands;

import java.util.regex.Pattern;

import constants.TipoDeEvento;

public class CartaCommand implements Command  {

	public static final String CADENA_COMANDO = "carta";

	private int id;
	private int fn;
	private int fs;
	private int fe;
	private int fo;
	private int elemento;

	public CartaCommand(String str) {
		Pattern p = Pattern.compile(" ");

		String[] partes = p.split(str);

		if (partes.length != 7) {			
			throw new IllegalArgumentException();
		}

		if (!partes[0].equals(CADENA_COMANDO)) {
			System.out.println("------------");
			System.out.println(22222);
			System.out.println("------------");
			
			throw new IllegalArgumentException();
		}

		try {
			id = Integer.parseInt(partes[1]);
			fn = Integer.parseInt(partes[2]);
			fs = Integer.parseInt(partes[3]);
			fe = Integer.parseInt(partes[4]);
			fo = Integer.parseInt(partes[5]);
			elemento = Integer.parseInt( partes[6]);
			
		} catch (NumberFormatException e) {
			System.out.println("------------");
			System.out.println(333333);
			System.out.println("------------");
			
			throw new IllegalArgumentException(e);
		}
	}

	// --------------------------------------------------------------------------------

	public  CartaCommand(int id, int fn, int fs, int fe, int fo, int elemento) {
		this.id=id;
		this.fn = fn;
		this.fs = fs;
		this.fe = fe;
		this.fo = fo;
		this.elemento = elemento;
	}

//	public  CartaCommand( Card carta ) {
//		this.id=carta.getIdCarta();
//		this.fn = carta.getFuerzaN();
//		this.fs = carta.getFuerzaS();
//		this.fe = carta.getFuerzaE();
//		this.fo = carta.getFuerzaO();
//		
//		switch (carta.getElemento()) {
//
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
//			System.out.println("------------");
//			System.out.println(444444444);
//			System.out.println("------------");
//			
//			throw new IllegalArgumentException();
//		}
//	}
	
	// --------------------------------------------------------------------------------

	// --------------------------------------------------------------------------------

	public void setId(int id) {
		this.id = id;
	}

	public int getFn() {
		return fn;
	}

	public void setFn(int fn) {
		this.fn = fn;
	}

	public int getFs() {
		return fs;
	}

	public void setFs(int fs) {
		this.fs = fs;
	}

	public int getFe() {
		return fe;
	}

	public void setFe(int fe) {
		this.fe = fe;
	}

	public int getFo() {
		return fo;
	}

	public void setFo(int fo) {
		this.fo = fo;
	}

	public int getElemento() {
		return elemento;
	}

	public void setElemento(int elemento) {
		this.elemento = elemento;
	}

	@Override
	public String convertirAString() {
		return CADENA_COMANDO + " " + //
			+ id +" " + fn + " " + fs + " " + fe + " " + fo + " "+ elemento ;
	}


	@Override
	public TipoDeEvento getTipoDeEvento() {
		
		return null; 
	}

	@Override
	public int getId() {
		
		return id;
	}

}
