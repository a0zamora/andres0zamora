package servidor;


public class MensajeTrueque {
	private String tipo;
	private String nombreUsr;
	private CartasDisponiblesTO miCarta;
	private CartasDisponiblesTO laCarta;
	private  int idRespuesta;
	
	
	
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getTipo() {
		return tipo;
	}
	public void setNombreUsr(String nombreUsr) {
		this.nombreUsr = nombreUsr;
	}
	public String getNombreUsr() {
		return nombreUsr;
	}
	public void setMiCarta(CartasDisponiblesTO miCarta) {
		this.miCarta = miCarta;
	}
	public CartasDisponiblesTO getMiCarta() {
		return miCarta;
	}
	public void setLaCarta(CartasDisponiblesTO laCarta) {
		this.laCarta = laCarta;
	}
	public CartasDisponiblesTO getLaCarta() {
		return laCarta;
	}
	public void setIdRespuesta(int idRespuesta) {
		this.idRespuesta = idRespuesta;
	}
	public int getIdRespuesta() {
		return idRespuesta;
	}
	
	

}
