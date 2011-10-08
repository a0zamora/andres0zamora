package commands;

import java.util.regex.Pattern;

import constants.TipoDeEvento;

public class ReadyPlayerCommand implements Command{

	public static final String CADENA_COMANDO = "Te_llego_un_invidato";

	private int idBD;
	
	 public ReadyPlayerCommand(String str) {
		Pattern p = Pattern.compile(" ");

		String[] partes = p.split(str);

		if (partes.length != 2) {
			throw new IllegalArgumentException();
		}

		if (!partes[0].equals(CADENA_COMANDO)) {
			throw new IllegalArgumentException();
		}

		try {
			
			idBD = Integer.parseInt(partes[1]);	
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(e);
		}
	}

	// --------------------------------------------------------------------------------

	public ReadyPlayerCommand(int idBD) {
				this.idBD = idBD;
			}
	@Override
	public TipoDeEvento getTipoDeEvento() {
		
		return null;
	}

	@Override
	public int getId() {
		
		return idBD;
	}

	@Override
	public String convertirAString() {
		return CADENA_COMANDO + " " + //
	     + idBD;
		
	}

}

