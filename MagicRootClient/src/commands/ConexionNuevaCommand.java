package commands;


import java.util.regex.Pattern;

import constants.TipoDeEvento;

public class ConexionNuevaCommand implements Command{
	public static final String CADENA_COMANDO = "Conexion_Nueva";
	private String nombreUsr;
	private String nivel;
public ConexionNuevaCommand(String str) {
	
		Pattern p = Pattern.compile(" ");
		String[] partes = p.split(str);
		nivel = partes[1];
		nombreUsr = partes[2];	
		
	}

public ConexionNuevaCommand(String nivel ,String nombreUsr) {
	this.nivel = nivel;
	this.nombreUsr = nombreUsr;
}


	public TipoDeEvento getTipoDeEvento() {
		return null;
	}
	public int getId() {
		return 0;
	}
	public String convertirAString() {
		
		return CADENA_COMANDO +" "+nivel+" "+nombreUsr;
		
	}

	public String getNombreUsr() {
		return nombreUsr;
	}

	public void setNombreUsr(String nombreUsr) {
		this.nombreUsr = nombreUsr;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

}
