package commands;

import java.util.regex.Pattern;

public class BorrarConexionCommand {
	public static final String CADENA_COMANDO = "Borrar_Conexion";
	private String nivel;
	private String nombreUsr;
	
	public BorrarConexionCommand(String str) {
		Pattern p = Pattern.compile(" ");
		String[] partes = p.split(str);
		nivel = partes[1];
		nombreUsr = partes[2];
	}

	public BorrarConexionCommand(String nivel ,String nombreUsr) {
		this.nivel = nivel;
		this.nombreUsr = nombreUsr;
	}
	
	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public String getNombreUsr() {
		return nombreUsr;
	}

	public void setNombreUsr(String nombreUsr) {
		this.nombreUsr = nombreUsr;
	}
	public String convertirString() {
		return CADENA_COMANDO+" "+nivel+" "+nombreUsr;
	}
}
