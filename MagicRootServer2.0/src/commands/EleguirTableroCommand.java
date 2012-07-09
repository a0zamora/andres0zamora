package commands;

import java.util.regex.Pattern;

import constants.Elemento;
import constants.TipoColor;
import constants.TipoDeEvento;


/**
 * Clase Comando para notificar cambio de tablero entre cliente-servidor y servidor-cliente
 * @author Rafael
 * 
 */



public class EleguirTableroCommand implements Command {

	public static final String CADENA_COMANDO = "Eleguir_tablero";
	public static final TipoDeEvento TIPO_DE_EVENTO = TipoDeEvento.TableroElegido;
	
	private int id=0;
	private int elemento;
	private int color=0;
	boolean random = false;
	
public EleguirTableroCommand(String str) {
		
		Pattern p = Pattern.compile(" ");

		String[] partes = p.split(str);

		if (partes.length != 5) {
			throw new IllegalArgumentException();
		}

		if (!partes[0].equals(CADENA_COMANDO)) {
			throw new IllegalArgumentException();
		}

		try { 
			id = Integer.parseInt( partes[1]);
			elemento = Integer.parseInt( partes[2]);
			color = Integer.parseInt( partes[3]);
			 
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(e);
		}
		
			if(partes[4].startsWith("true")){
				random = Boolean.parseBoolean(partes[4]);
			}else if ( partes[4].startsWith("false") ){
				random = Boolean.parseBoolean(partes[4]);
			}else {
				throw new IllegalArgumentException();
			}
	}
	// --------------------------------------------------------------------------------

	
	/**
	 * Constructor parametrico 
	 * @author Rafael
	 * @param elemento 
	 * 			enum Elemento, 
	 * @param color
	 * 			enum TipoColor el color de el jugador que cambio el tablero
	 * @param ramdon 
	 * 			booleano vale true si se envia desde el servidor al cliente en en ramdon select del elemento,
	 * 			vale false si es el cambio o cambios de tablero por una linea o diagonal
	 */

	
	public EleguirTableroCommand( Elemento elemento ,TipoColor color, boolean ramdon ) {
		
		this.random = ramdon;
		switch (elemento) {
		
		case agua:
			System.out.println("agua");
			this.elemento = 0;
			break;
		case fuego:
			System.out.println("fuego");
			this.elemento = 1;
			break;
		case aire:
			System.out.println("aire");
			this.elemento = 2;
			break;
		case tierra:
			System.out.println("tierra");
			this.elemento = 3;
			break;
		default:
			throw new IllegalArgumentException();
		}
		
		if (color.equals(TipoColor.AZUL)) {
			this.color = 1;
		} else if ( color.equals(TipoColor.ROJA) ) {
			this.color = 2;
		}
	}
	
	/**
	 * Constructor parametrico 
	 * @author Rafael
	 * @param elemento 
	 * 			de Tipo enum Elemento, 
	 * @param ramdon 
	 * 			booleano vale true si se envia desde el servidor al cliente en en ramdon select del elemento,
	 * 			vale false si es el cambio o cambios de tablero por una linea o diagonal
	 */	
public EleguirTableroCommand( Elemento elemento ,boolean ramdon ) {

	this.random = ramdon;
		
		switch (elemento) {
		
		case agua:
			System.out.println("agua");
			this.elemento = 0;
			break;
		case fuego:
			System.out.println("fuego");
			this.elemento = 1;
			break;
		case aire:
			System.out.println("aire");
			this.elemento = 2;
			break;
		case tierra:
			System.out.println("tierra");
			this.elemento = 3;
			break;
		default:
			throw new IllegalArgumentException();
		}
}
	
	public void setRamdonTrue(){
		random = true;
	}
	
	public boolean getRamdon(){
		return random;
	}
	
	/**
	 *returna un valor int de 0 a 3 correspondiente a los elementos de los tableros 
	 * @author rafael
	 * @return 0 = agua, 1 = fuego, 2 = aire , 3 = tierra
	 */
	public int getElemento(){
		return elemento;
	}
	
	public int getColor(){
		return color;
	}
	
	@Override
	public TipoDeEvento getTipoDeEvento() {

		return TIPO_DE_EVENTO;
	}

	@Override
	public int getId() {

		return id;
	}
	
	@Override
	public String convertirAString() {
		return CADENA_COMANDO + " "
				+ //
				id+ " " + elemento + " " + color + " " + random; 
	}

}
