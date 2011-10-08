package commands;

import java.util.regex.Pattern;

import constants.TipoDeEvento;


public class VersionCommand implements Command {

public static final String CADENA_COMANDO = "version";
	private int id;
	private String version;
	
	public VersionCommand(String str) {
		
		Pattern p = Pattern.compile(" ");

		String[] partes = p.split(str);

		if (partes.length != 3) {
			throw new IllegalArgumentException();
		}

		if (!partes[0].equals(CADENA_COMANDO)) {
			throw new IllegalArgumentException();
		}

		try {
			id =Integer.parseInt(partes[1]);
			version = (partes[2]);
			} catch (NumberFormatException e) {
			throw new IllegalArgumentException(e);
		}
	}

	// --------------------------------------------------------------------------------
	//constructor parametrico
	public VersionCommand ( int id,String version) {
		this.version = version;
	}
	
		
//--------------------------Interfaz------------------------------
	public  String getVersion(){
		return version;
	}
	
	
	//------------------------Sobre Escribiendo metodos--------
	
	@Override
	public TipoDeEvento getTipoDeEvento() {
		return null;
	}

	@Override
	public int getId() {
		return 0;
	}

	@Override
	public String convertirAString() {
		return CADENA_COMANDO //
				+  " " + id + " " + version ;}
	
}
