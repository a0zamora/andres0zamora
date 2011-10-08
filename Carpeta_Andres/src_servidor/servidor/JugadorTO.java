package servidor;

import java.util.List;



public class JugadorTO {
	private String nombreusr;
	private String correo;
	private String clave;
	private	int idjugador;
	private int nivel;
	private int puntos;
	private String nombreAvatar;
	private List<CartasDisponiblesTO> cartas;
	private List<Byte[]> imagenes;
	private int partidasGanadas;
	private int partidasPerdidas;
	private int partidasEmpatas;
	
	
	public JugadorTO(String nombreusr, String correo, String clave,
			int idjugador, int nivel, int puntos, String nombreAvatar,
			List<CartasDisponiblesTO> cartas) {
		this.nombreusr = nombreusr;
		this.correo = correo;
		this.clave = clave;
		this.idjugador = idjugador;
		this.nivel = nivel;
		this.puntos = puntos;
		this.nombreAvatar = nombreAvatar;
		this.cartas = cartas;
	}
	public JugadorTO(){
		nombreusr = "";
		correo = "";
		clave = "";
		idjugador = 0;
		nivel = 0;
		puntos = 0;
		nombreAvatar = "";
		cartas = null;
	}

	public JugadorTO(JugadorBean jugador) {
		this.clave = jugador.getClave();
		this.correo = jugador.getCorreo();
		this.idjugador = jugador.getId();
		this.nivel = jugador.getNivel();
		this.nombreusr = jugador.getNombreUsr();
		this.puntos = jugador.getPuntos();
		for (int i = 0; i < jugador.getCartasDisponiblesRef().size(); i++) {
			CartasDisponiblesTO carta = new CartasDisponiblesTO(jugador
					.getCartasDisponiblesRef().get(i).getId(), jugador
					.getCartasDisponiblesRef().get(i).getCartaRef().getId(),
					jugador.getCartasDisponiblesRef().get(i).getCartaRef()
							.getFuerzasur(), jugador.getCartasDisponiblesRef()
							.get(i).getCartaRef().getFuerzaeste(), jugador
							.getCartasDisponiblesRef().get(i).getCartaRef()
							.getFuerzaoeste(), jugador
							.getCartasDisponiblesRef().get(i).getCartaRef()
							.getElemento(), jugador.getCartasDisponiblesRef()
							.get(i).getCartaRef().getSumafuerzas(), jugador
							.getCartasDisponiblesRef().get(i).getCartaRef()
							.getCosto(), 0);

			this.getCartas().add(carta);
		}
	}
	
	
	
	
	public void setNombreusr(String nombreusr) {
		this.nombreusr = nombreusr;
	}
	public String getNombreusr() {
		return nombreusr;
	}
	
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getCorreo() {
		return correo;
	}
	/**
	 * @param clave the clave to set
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}
	/**
	 * @return the clave
	 */
	public String getClave() {
		return clave;
	}
	/**
	 * @param idjugador the idjugador to set
	 */
	public void setIdjugador(int idjugador) {
		this.idjugador = idjugador;
	}
	/**
	 * @return the idjugador
	 */
	public int getIdjugador() {
		return idjugador;
	}
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	public int getNivel() {
		return nivel;
	}
	public void setNombreAvatar(String nombreAvatar) {
		this.nombreAvatar = nombreAvatar;
	}
	public String getNombreAvatar() {
		return nombreAvatar;
	}
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	public int getPuntos() {
		return puntos;
	}
	public void setCartas(List<CartasDisponiblesTO> cartas) {
		this.cartas = cartas;
	}
	public List<CartasDisponiblesTO> getCartas() {
		return cartas;
	}
	public void setImagenes(List<Byte[]> imagenes) {
		this.imagenes = imagenes;
	}
	public List<Byte[]> getImagenes() {
		return imagenes;
	}
	public void setPartidasGanadas(int partidasGanadas) {
		this.partidasGanadas = partidasGanadas;
	}
	public int getPartidasGanadas() {
		return partidasGanadas;
	}
	public void setPartidasPerdidas(int partidasPerdidas) {
		this.partidasPerdidas = partidasPerdidas;
	}
	public int getPartidasPerdidas() {
		return partidasPerdidas;
	}
	public void setPartidasEmpatas(int partidasEmpatas) {
		this.partidasEmpatas = partidasEmpatas;
	}
	public int getPartidasEmpatas() {
		return partidasEmpatas;
	}

}
