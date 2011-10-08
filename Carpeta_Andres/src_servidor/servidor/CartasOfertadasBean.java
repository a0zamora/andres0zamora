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
@Table (name = "t_cartasofertadas")
@Proxy (lazy = false)
public class CartasOfertadasBean {
	private int cantidad;
	private JugadorBean jugadorRef;
	private CartaBean cartaRef;
	private List<RespuestaBean> respuestasPropuesta = new ArrayList<RespuestaBean>();
	private List<RespuestaBean> respuestaOferta = new ArrayList<RespuestaBean>();
	private int id;
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
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
	
	@OneToMany(mappedBy = "cartasPropuesta")
	@LazyCollection(LazyCollectionOption.TRUE)
	@Cascade({CascadeType.ALL,CascadeType.DELETE_ORPHAN} )
	public List<RespuestaBean> getRespuestas(){
		return respuestasPropuesta;
	}
	
	public void setRespuestas(List<RespuestaBean> lista){
		respuestasPropuesta=lista;
	}

	
	@ManyToOne
	public CartaBean getCartaRef(){
		return cartaRef;
		
	}
	public void setCartaRef(CartaBean carta){
		cartaRef=carta;
	}
	public void setRespuestaOferta(List<RespuestaBean> respuestaOferta) {
		this.respuestaOferta = respuestaOferta;
	}
	@OneToMany(mappedBy = "cartasOfertada")
	@LazyCollection(LazyCollectionOption.TRUE)
	@Cascade({CascadeType.ALL,CascadeType.DELETE_ORPHAN} )
	public List<RespuestaBean> getRespuestaOferta() {
		return respuestaOferta;
	}
	
	
		
	
	
	
	
	
	

}
