package servidor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Table (name = "t_mensajes")
@Proxy (lazy = false)

public class MensajesBean {
	private int id;
	private String mensaje;
	private JugadorBean jugadorRef;
	private String remitente;
	private CartaBean cartaOfertada;
	private CartaBean cartaPropuesta;
	
	public void setId(int id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getMensaje() {
		return mensaje;
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

	public void setCartaOfertada(CartaBean cartaOfertada) {
		this.cartaOfertada = cartaOfertada;
	}
	@ManyToOne
	public CartaBean getCartaOfertada() {
		return cartaOfertada;
	}

	public void setCartaPropuesta(CartaBean cartaPropuesta) {
		this.cartaPropuesta = cartaPropuesta;
	}
	@ManyToOne
	public CartaBean getCartaPropuesta() {
		return cartaPropuesta;
	}



}