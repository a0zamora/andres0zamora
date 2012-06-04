package com.andresoftware.tesis.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name = "t_cartadisponible")
@Proxy(lazy = false)

public class CartasDisponiblesBean {
	private int cantidad;
	private int id;
	private JugadorBean jugadorRef;
	private CartaBean cartaRef;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id=id;
	}
	
	public int getCantidad(){
		return cantidad;
	}
	public void setCantidad(int cant){
		cantidad=cant;
		
	}
	@ManyToOne
	public JugadorBean getJugadorRef(){
		return jugadorRef;
	}
	public void setJugadorRef(JugadorBean jugador){
		jugadorRef=jugador;
	}
	@ManyToOne
	public CartaBean getCartaRef(){
		return cartaRef;
		
	}
	public void setCartaRef(CartaBean carta){
		cartaRef=carta;
	}

}
