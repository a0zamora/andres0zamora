package commands;

import java.util.regex.Pattern;

import constants.TipoDeEvento;


public class FinJuegoPorDesconexion implements Command {
	
	public static final String CADENA_COMANDO = "FinJuegoPorDesconexion";
	
	private int idGanador;
	private int ptsGandor;
	private int ptsPerdedor;

	public FinJuegoPorDesconexion(String str) {
		
		Pattern p = Pattern.compile(" ");

		String[] partes = p.split(str);

		if (partes.length != 4) {
			throw new IllegalArgumentException();
		}

		if (!partes[0].equals(CADENA_COMANDO)) {
			throw new IllegalArgumentException();
		}

		try {
			idGanador = Integer.parseInt(partes[1]);
			ptsGandor = Integer.parseInt(partes[2]);
			ptsPerdedor = Integer.parseInt(partes[3]);
			
			} catch (NumberFormatException e) {
			throw new IllegalArgumentException(e);
		}
	}

	// --------------------------------------------------------------------------------

	
	
	//constructor parametrico
	public FinJuegoPorDesconexion( int idGanador , int ptsGanador, int ptsPerdedor) {
		
		this.idGanador = idGanador;
		this.ptsGandor = ptsGanador;
		this.ptsPerdedor = ptsPerdedor;

		
	}
	
	
	//constructor solo se una en casop de empates
public FinJuegoPorDesconexion( int ptsEmpate, int ptsEmpate1) {
		
		this.ptsGandor = ptsEmpate;
		this.ptsPerdedor = ptsEmpate1;
		this.idGanador = 0;
		
	}
	
//--------------------------Interfaz------------------------------
	
	public int getIdGanador() {
		return idGanador;
	}

	public int getPtsGandor() {
		return ptsGandor;
	}

	public int getPtsPerdedor() {
		return ptsPerdedor;
	}

	//------------------------Sobre Escribiendo metodos--------
	
	@Override
	public TipoDeEvento getTipoDeEvento() {
		return null;
	}

	@Override
	public int getId() {
		return idGanador;
	}

	@Override
	public String convertirAString() {
		return CADENA_COMANDO //
				+ " " + idGanador + " " + ptsGandor + " " + ptsPerdedor;	}

}//fin del la clase comando
