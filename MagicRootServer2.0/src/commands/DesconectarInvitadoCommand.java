package commands;

import constants.TipoDeEvento;


//estado eligiendo cartas los jugadores estan en un room y nadie pierde lA partida

public class DesconectarInvitadoCommand implements Command{

	
	public static final String CADENA_COMANDO = "desconectarInvitado";

	
	public DesconectarInvitadoCommand() {

	}
	

	@Override
	public TipoDeEvento getTipoDeEvento() {
		return null;
	}

		@Override
	public String convertirAString() {
		return CADENA_COMANDO ;
	}


		@Override
		public int getId() {
			// TODO Auto-generated method stub
			return 0;
		}
	
}
