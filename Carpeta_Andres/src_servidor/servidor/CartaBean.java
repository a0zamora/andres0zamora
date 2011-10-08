package servidor;

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
@Table(name = "t_carta")
@Proxy(lazy = false)
public class CartaBean {
	private int fuerzanorte;
	private int fuerzasur;
	private int fuerzaeste;
	private int fuerzaoeste;
	private String elemento;
	private int sumafuerzas;
	private int idcarta;
	private int costo;
	private List<CartasDisponiblesBean> cartasdisponiblesRef = new ArrayList<CartasDisponiblesBean>();
	private List<CartasOfertadasBean> cartasofertadasRef = new ArrayList<CartasOfertadasBean>();
	private List<MensajesBean> mensajesOferta = new ArrayList<MensajesBean>();
	private List<MensajesBean> mensajesPropuesta = new ArrayList<MensajesBean>();
	
	
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	public int getId(){
		return idcarta;
		
	}
	public void setId(int id){
		this.idcarta=id;
		
	}
	
	public int getFuerzanorte(){
		return fuerzanorte;
	}
	public void setFuerzanorte(int fn){
		fuerzanorte=fn;
	}
	
	
	public int getFuerzasur(){
		return fuerzasur;
	}
	public void setFuerzasur(int fs){
		fuerzasur= fs;
	}
	
	
	public int getFuerzaeste(){
		return fuerzaeste;
	}
	public void setFuerzaeste(int fe){
		this.fuerzaeste= fe;
	}
	
	public String getElemento(){
		return elemento;
	}
	public void setElemento(String elemento){
		this.elemento=elemento;
	}
	
	
	public int getFuerzaoeste(){
		return fuerzaoeste;
	}
	public void setFuerzaoeste(int fo){
		fuerzaoeste= fo;
	}
	
	@OneToMany(mappedBy = "cartaRef")
	@LazyCollection(LazyCollectionOption.TRUE)
	@Cascade({CascadeType.ALL , CascadeType.DELETE_ORPHAN})
	public List<CartasDisponiblesBean> getCartasDisponiblesRef(){
		return cartasdisponiblesRef;
		
	}
	
	public void setCartasDisponiblesRef(List<CartasDisponiblesBean> lista){
		cartasdisponiblesRef=lista;
	}
	@OneToMany (mappedBy = "cartaRef")
	@LazyCollection(LazyCollectionOption.TRUE)
	@Cascade({CascadeType.ALL})
	public List<CartasOfertadasBean> getCartasOfertadasRef(){
		return cartasofertadasRef;
	}
	
	public void setCartasOfertadasRef(List<CartasOfertadasBean> lista){
		cartasofertadasRef=lista;
	}
	public void setSumafuerzas(int suma) {
		this.sumafuerzas = suma;
	}
	public int getSumafuerzas() {
		return sumafuerzas;
	}
	/**
	 * @param costo the costo to set
	 */
	public void setCosto(int costo) {
		this.costo = costo;
	}
	/**
	 * @return the costo
	 */
	public int getCosto() {
		return costo;
	}

	public void setMensajesPropuesta(List<MensajesBean> mensajesPropuesta) {
		this.mensajesPropuesta = mensajesPropuesta;
	}
	@OneToMany(mappedBy = "cartaPropuesta")
	@LazyCollection(LazyCollectionOption.TRUE)
	@Cascade({CascadeType.ALL,CascadeType.DELETE_ORPHAN})
	public List<MensajesBean> getMensajesPropuesta() {
		return mensajesPropuesta;
	}
	public void setMensajesOferta(List<MensajesBean> mensajesOferta) {
		this.mensajesOferta = mensajesOferta;
	}
	@OneToMany(mappedBy = "cartaOfertada")
	@LazyCollection(LazyCollectionOption.TRUE)
	@Cascade({CascadeType.ALL,CascadeType.DELETE_ORPHAN})
	public List<MensajesBean> getMensajesOferta() {
		return mensajesOferta;
	}

	
	
	
	
	
	
	
	
	

}
