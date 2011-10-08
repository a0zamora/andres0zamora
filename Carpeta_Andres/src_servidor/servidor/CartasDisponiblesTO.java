package servidor;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class CartasDisponiblesTO {

	private int fuerzanorte;
	private int fuerzasur;
	private int fuerzaeste;
	private int fuerzaoeste;
	private String elemento;
	private int idcarta;
	private int sumafuerzas;
	private int costo;
	private int idOferta;

	public CartasDisponiblesTO(int id, int fuerzanorte, int fuerzasur,
			int fuerzaeste, int fuerzaoeste, String elemento, int sumafuerza,
			int costo, int idOferta) {
		this.idcarta = id;
		this.fuerzanorte = fuerzanorte;
		this.fuerzasur = fuerzasur;
		this.fuerzaeste = fuerzaeste;
		this.fuerzaoeste = fuerzaoeste;
		this.sumafuerzas = sumafuerza;
		this.costo = costo;
		this.elemento = elemento;
		this.setIdOferta(idOferta);
	}

	public CartasDisponiblesTO() {

	}

	public List<servidor.CartaBean> getCartas(String nombre) {

		Session sesion = SessionHibernate.getInstance().getSession();
		sesion.beginTransaction();
		List<servidor.JugadorBean> jugadores = new ArrayList<servidor.JugadorBean>();
		List<servidor.CartaBean> cartas = new ArrayList<servidor.CartaBean>();

		jugadores = sesion.createCriteria(servidor.JugadorBean.class)
				.add(Restrictions.eq("nombreUsr", nombre)).list();
		for (int i = 0; i < jugadores.get(0).getCartasDisponiblesRef().size(); i++) {
			cartas.add(i, jugadores.get(0).getCartasDisponiblesRef().get(i)
					.getCartaRef());

		}
		sesion.beginTransaction().commit();
		sesion.close();
		return cartas;

	}

	public static void main(String[] args) {
		List<servidor.CartaBean> lista = new ArrayList<servidor.CartaBean>();

		CartasDisponiblesTO cartas = new CartasDisponiblesTO();
		List<CartasDisponiblesTO> listaaux = new ArrayList<CartasDisponiblesTO>();
		CartasDisponiblesTO aux = new CartasDisponiblesTO();

		lista = cartas.getCartas("jesus");
		for (int i = 0; i < lista.size(); i++) {
			aux.setFuerzanorte(lista.get(i).getFuerzanorte());
			aux.setFuerzasur(lista.get(i).getFuerzasur());
			aux.setFuerzaeste(lista.get(i).getFuerzaeste());
			aux.setFuerzaoeste(lista.get(i).getFuerzaoeste());
			listaaux.add(i, aux);

		}
		for (int i = 0; i < listaaux.size(); i++) {
			System.out.println(lista.get(i).getFuerzanorte());
		}
	}

	public int getId() {
		return idcarta;

	}

	public void setId(int id) {
		this.idcarta = id;

	}

	public int getFuerzanorte() {
		return fuerzanorte;
	}

	public void setFuerzanorte(int fn) {
		fuerzanorte = fn;
	}

	public int getFuerzasur() {
		return fuerzasur;
	}

	public void setFuerzasur(int fs) {
		fuerzasur = fs;
	}

	public int getFuerzaeste() {
		return fuerzaeste;
	}

	public void setFuerzaeste(int fe) {
		this.fuerzaeste = fe;
	}

	public int getFuerzaoeste() {
		return fuerzaoeste;
	}

	public void setFuerzaoeste(int fo) {
		fuerzaoeste = fo;
	}

	public String getElemento() {
		return elemento;
	}

	public void setElemento(String elemento) {
		this.elemento = elemento;
	}

	public void setSumafuerzas(int sumafuerzas) {
		this.sumafuerzas = sumafuerzas;
	}

	public int getSumafuerzas() {
		return sumafuerzas;
	}

	public void setCosto(int costo) {
		this.costo = costo;
	}

	public int getCosto() {
		return costo;
	}

	public void setIdOferta(int idOferta) {
		this.idOferta = idOferta;
	}

	public int getIdOferta() {
		return idOferta;
	}

}
