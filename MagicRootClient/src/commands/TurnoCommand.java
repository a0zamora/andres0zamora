package commands;

import java.util.regex.Pattern;

import constants.TipoDeEvento;


public class TurnoCommand implements Command {
	
	
	public static final String CADENA_COMANDO = "turno";


	private int idHiloEnTurno;
	
	public TurnoCommand(String str) {
		Pattern p = Pattern.compile(" ");

		String[] partes = p.split(str);

		if (partes.length != 2) {
			throw new IllegalArgumentException();
		}

		if (!partes[0].equals("turno")) {
			throw new IllegalArgumentException();
		}

		try {
			idHiloEnTurno = Integer.parseInt(partes[1]);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(e);
		}
	}

	// --------------------------------------------------------------------------------
//
//	public TurnoCommand(int idHiloaEnTurno, int idHiloEnEspera) {
//		this.idHiloEnTurno = idHiloaEnTurno;
//		this.idHiloEnEspera = idHiloEnEspera;
//			}

	public TurnoCommand(int idHiloaEnTurno) {
		this.idHiloEnTurno = idHiloaEnTurno;
		
			}


	
	
	// --------------------------------------------------------------------------------

		// --------------------------------------------------------------------------------

	public int getIdHiloEnTurno() {
		return idHiloEnTurno;
	}

	public void setIdHiloEnTurno(int idHiloEnTurno) {
		this.idHiloEnTurno = idHiloEnTurno;
	}
	

	@Override
	public TipoDeEvento getTipoDeEvento() {
		return null;
	}

	@Override
	public String convertirAString() {
		return CADENA_COMANDO + " " + //
				idHiloEnTurno;
	}

		@Override
	public int getId() {
	
		return idHiloEnTurno;
	}

	
	
	

}
