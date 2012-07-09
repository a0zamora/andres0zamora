package com.andresoftware.tesis.model;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Proxy;



@Entity
@Table(name = "t_partida")
@Proxy(lazy = false)
public class PartidaBean {
	private String ganador;
	private String perdedor;
	private int empate;
	private int idpartida;
	private List<JugadorBean> jugadores = new ArrayList<JugadorBean>();
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId(){
		return idpartida;
	}
	public void setId(int id){
		this.idpartida=id;
	}
		
	public String getGanador(){
		return ganador;
	}
	public void setGanador(String win){
		ganador=win;
	}
	
	public String getPerdedor(){
		return perdedor;
		
	}
	public void setPerdedor(String lose){
		perdedor=lose;
		
	}
	public int getEmpate(){
		return empate;
	}
	public void setEmpate(int empate){
		this.empate=empate;
	}
		
	@OneToMany(mappedBy = "partidaRef")
	@LazyCollection(LazyCollectionOption.TRUE)
	@Cascade({ CascadeType.ALL, CascadeType.DELETE_ORPHAN })
	public List<JugadorBean> getJugadores(){
		return jugadores;
	}
	
	public void setJugadores(List<JugadorBean> jugadores){
		this.jugadores=jugadores;
	}
	
	

}
