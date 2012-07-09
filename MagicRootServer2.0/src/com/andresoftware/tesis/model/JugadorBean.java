package com.andresoftware.tesis.model;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Proxy;


@Entity
@javax.persistence.Table(name ="t_jugador")
@Proxy(lazy = false)
public class JugadorBean {
	private String nombreusr;
	private String correo;
	private String clave;
	private	int idjugador;
	private int puntos;
	private int nivel;
	private PartidaBean partidaRef;
	private String nombreAvatar;
	private List<CartasDisponiblesBean> cartasdisponiblesRef = new ArrayList<CartasDisponiblesBean>();
	private List<PartidasJugadas> partidasPerdidas = new ArrayList<PartidasJugadas>();
	private List<PartidasJugadas> partidasGanadas = new ArrayList<PartidasJugadas>();
	private List<PartidasJugadas> partidasEmpatadasParticipante1 = new ArrayList<PartidasJugadas>();
	private List<PartidasJugadas> partidasEmpatadasParticipante2 = new ArrayList<PartidasJugadas>();

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId(){
		return idjugador;
	}
	public void setId(int id){
		this.idjugador=id;
	}
	
	public String getNombreUsr(){
		return nombreusr;
		
	}
	public void setNombreUsr(String nombre){
		nombreusr = nombre;
		
	}
	public String getCorreo(){
		return correo;
	}
	public void setCorreo(String correo){
		this.correo=correo;
	}
	public String getClave(){
		return clave;
	}
	public void setClave(String clave){
		this.clave = clave;
	}

	@ManyToOne
	public PartidaBean getPartidaRef(){
		return partidaRef;
	}
	
	public void setPartidaRef(PartidaBean partidaRef){
		this.partidaRef=partidaRef;
	}
	@OneToMany (mappedBy = "jugadorRef")
	@LazyCollection(LazyCollectionOption.TRUE)
	@Cascade({CascadeType.ALL, CascadeType.DELETE_ORPHAN})
	public List<CartasDisponiblesBean> getCartasDisponiblesRef(){
		return cartasdisponiblesRef;
	}
	public void setCartasDisponiblesRef(List<CartasDisponiblesBean> jugadorcarta){
		cartasdisponiblesRef=jugadorcarta;
	}
	
	

	/**
	 * @param puntos the puntos to set
	 */
	public void setPuntosGanador() {
		puntos = puntos +3;
	}
	
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	
	
	public void setPuntosEmpate() {
		puntos = puntos +1;
	}
	/**
	 * @return the puntos
	 */
	public int getPuntos() {
		return puntos;
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
	public void setPartidasPerdidas(List<PartidasJugadas> partidasPerdidas) {
		this.partidasPerdidas = partidasPerdidas;
	}
	@OneToMany(mappedBy = "jugadorPerdedor")
	@LazyCollection(LazyCollectionOption.TRUE)
	@Cascade({CascadeType.ALL,CascadeType.DELETE_ORPHAN})
	public List<PartidasJugadas> getPartidasPerdidas() {
		return partidasPerdidas;
	}
	public void setPartidasGanadas(List<PartidasJugadas> partidasGanadas) {
		this.partidasGanadas = partidasGanadas;
	}
	@OneToMany(mappedBy = "jugadorGanador")
	@LazyCollection(LazyCollectionOption.TRUE)
	@Cascade({CascadeType.ALL,CascadeType.DELETE_ORPHAN})
	public List<PartidasJugadas> getPartidasGanadas() {
		return partidasGanadas;
	}

	public void setPartidasEmpatadasParticipante1(
			List<PartidasJugadas> partidasEmpatadasParticipante1) {
		this.partidasEmpatadasParticipante1 = partidasEmpatadasParticipante1;
	}
	@OneToMany(mappedBy = "jugadorEmpate1")
	@LazyCollection(LazyCollectionOption.TRUE)
	@Cascade({CascadeType.ALL,CascadeType.DELETE_ORPHAN})
	public List<PartidasJugadas> getPartidasEmpatadasParticipante1() {
		return partidasEmpatadasParticipante1;
	}
	public void setPartidasEmpatadasParticipante2(
			List<PartidasJugadas> partidasEmpatadasParticipante2) {
		this.partidasEmpatadasParticipante2 = partidasEmpatadasParticipante2;
	}
	@OneToMany(mappedBy = "jugadorEmpate2")
	@LazyCollection(LazyCollectionOption.TRUE)
	@Cascade({CascadeType.ALL,CascadeType.DELETE_ORPHAN})
	public List<PartidasJugadas> getPartidasEmpatadasParticipante2() {
		return partidasEmpatadasParticipante2;
	}
	
	

	
	
	
	
	
	
	

}