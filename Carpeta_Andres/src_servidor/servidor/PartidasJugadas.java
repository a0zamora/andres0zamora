package servidor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name = "t_partidasJugadas")
@Proxy(lazy = false)
public class PartidasJugadas {
	JugadorBean jugadorGanador;
	JugadorBean jugadorPerdedor;
	JugadorBean jugadorEmpate1;	
	JugadorBean jugadorEmpate2;
	int puntosGanador;
	int puntosPerdedor;
	int id;

	@ManyToOne
	public JugadorBean getJugadorGanador() {
		return jugadorGanador;
	}

	public void setJugadorGanador(JugadorBean jugadorGanador) {
		this.jugadorGanador = jugadorGanador;
	}

	@ManyToOne
	public JugadorBean getJugadorPerdedor() {
		return jugadorPerdedor;
	}

	public void setJugadorPerdedor(JugadorBean jugadorPerdedor) {
		this.jugadorPerdedor = jugadorPerdedor;
	}

	public int getPuntosGanador() {
		return puntosGanador;
	}

	public void setPuntosGanador(int puntosGanador) {
		this.puntosGanador = puntosGanador;
	}

	public int getPuntosPerdedor() {
		return puntosPerdedor;
	}

	public void setPuntosPerdedor(int puntosPerdedor) {
		this.puntosPerdedor = puntosPerdedor;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@ManyToOne
	public JugadorBean getJugadorEmpate1() {
		return jugadorEmpate1;
	}

	public void setJugadorEmpate1(JugadorBean jugadorEmpate1) {
		this.jugadorEmpate1 = jugadorEmpate1;
	}
	@ManyToOne
	public JugadorBean getJugadorEmpate2() {
		return jugadorEmpate2;
	}

	public void setJugadorEmpate2(JugadorBean jugadorEmpate2) {
		this.jugadorEmpate2 = jugadorEmpate2;
	}


}