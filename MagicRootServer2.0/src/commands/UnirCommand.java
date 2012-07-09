package commands;

import java.util.regex.Pattern;

import constants.TipoDeEvento;

public class UnirCommand implements Command {

	public static final String CADENA_COMANDO = "unir";
	

	public static final TipoDeEvento TIPO_DE_EVENTO = TipoDeEvento.llegoInvitado;
	// el id de la partida es el mismo de creador de la partida

	private int IdInvitado;

	
	
	public UnirCommand(String str) {

		Pattern p = Pattern.compile(" ");

		String[] partes = p.split(str);

		if (partes.length != 2) {
			throw new IllegalArgumentException();
		}

		if (!partes[0].equals("unir")) {
			throw new IllegalArgumentException();
		}

		try {
			IdInvitado = Integer.parseInt(partes[1]);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(e);
		}
	}

	// --------------------------------------------------------------------------------

	public UnirCommand(int idInvitado) {
		this.IdInvitado = idInvitado;

	}
	@Override
	public TipoDeEvento getTipoDeEvento() {
		return TIPO_DE_EVENTO;
	}

	@Override
	public int getId() {
		return IdInvitado;
	}

	public void setIdInvitado(int idInvitado) {
		IdInvitado = idInvitado;
	}

	@Override
	public String convertirAString() {
		return CADENA_COMANDO + " " + IdInvitado;
	}


}