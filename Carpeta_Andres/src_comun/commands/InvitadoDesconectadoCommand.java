package commands;

import java.util.regex.Pattern;

import constants.TipoDeEvento;


//estado eligiendo cartas los jugadores estan en un room y nadie pierde lA partida

public class InvitadoDesconectadoCommand implements Command{

	
	public static final String CADENA_COMANDO = "Se_desconecto_Invitado";

	
	//el id de la partida es el mismo de creador de la partida
	
	private int IdInvitado;

	public InvitadoDesconectadoCommand(String str) {
		
		Pattern p = Pattern.compile(" ");

		String[] partes = p.split(str);

		if ( partes.length != 2 ) {
			throw new IllegalArgumentException();
		}

		if ( !partes[0].equals("desconectarRoom") ) {
			throw new IllegalArgumentException();
		}

		try {
			IdInvitado = Integer.parseInt( partes[1]);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(e);
		}
	}

	// --------------------------------------------------------------------------------

	public InvitadoDesconectadoCommand(int idInvitado){
		this.IdInvitado = idInvitado;
		
	}
	// --------------------------------------------------------------------------------


	// --------------------------------------------------------------------------------

	@Override
	public TipoDeEvento getTipoDeEvento() {
		return null;
	}

		@Override
	public String convertirAString() {
		return CADENA_COMANDO + " " + Integer.toString(IdInvitado);
	}
	
	


	@Override
	public int getId() {
		
		return IdInvitado;
	}	

}
