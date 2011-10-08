package servidor;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Proxy;

@Entity
@Table(name = "t_respuesta")
@Proxy(lazy = false)
public class RespuestaBean {
	private String descripcion;
	private JugadorBean jugadorRef;
	private CartasOfertadasBean cartasPropuesta;
	private CartasOfertadasBean cartasOfertada;
	private String remitente;
	private int id;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
	public void setCartasPropuesta(CartasOfertadasBean cartasPropuesta) {
		this.cartasPropuesta = cartasPropuesta;
	}

	@ManyToOne
	public CartasOfertadasBean getCartasPropuesta() {
		return cartasPropuesta;
	}

	public void setCartasOfertada(CartasOfertadasBean cartasOfertada) {
		this.cartasOfertada = cartasOfertada;
	}

	@ManyToOne
	public CartasOfertadasBean getCartasOfertada() {
		return cartasOfertada;
	}

	public void setJugadorRef(JugadorBean jugadorRef) {
		this.jugadorRef = jugadorRef;
	}

	@ManyToOne
	public JugadorBean getJugadorRef() {
		return jugadorRef;
	}

	public void setRemitente(String remitente) {
		this.remitente = remitente;
	}

	public String getRemitente() {
		return remitente;
	}



}