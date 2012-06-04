package com.andresoftware.tesis.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;
@Table(name = "t_verficarlogin")
@Proxy(lazy = false)
@Entity
public class VerificarLogin {
	private String nombreUsr;
	private int id;
	private int idJugador;


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombreUsr(){
		return nombreUsr;
	}
	public void setNombreUsr(String nombre){
		nombreUsr=nombre;
	}
	/**
	 * @param idJugador the idJugador to set
	 */
	public void setIdJugador(int idJugador) {
		this.idJugador = idJugador;
	}
	/**
	 * @return the idJugador
	 */
	public int getIdJugador() {
		return idJugador;
	}
	
	
	
	
	
	
	
	
	
	
}
