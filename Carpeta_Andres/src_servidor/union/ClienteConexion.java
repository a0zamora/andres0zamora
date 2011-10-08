package union;

public class ClienteConexion {
	
		private String nivel;
		private String nombreUsr;
		
		
	public ClienteConexion(String nivel, String nombreUsr) {
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
	
	
}
