package com.andresoftware.tesis.model;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

@WebService
public class ServiciosWeb {

	public boolean guardar(JugadorTO r) {

		JugadorBean jugador = new JugadorBean();
		jugador.setNombreUsr(r.getNombreusr());
		jugador.setCorreo(r.getCorreo());
		jugador.setClave(r.getClave());
		jugador.setPuntos(6);
		jugador.setNivel(3);
		jugador.setNombreAvatar("CardsJPG/Heroe.jpeg");

		Session session = SessionHibernate.getInstance().getSession();
		session.beginTransaction();
		List<JugadorBean> lista1;
		List<CartaBean> cartasAgua = new ArrayList<CartaBean>();
		List<CartaBean> cartasFuego = new ArrayList<CartaBean>();
		JugadorTO aux = new JugadorTO();
		lista1 = new ArrayList<JugadorBean>();
		List<JugadorBean> lista2 = new ArrayList<JugadorBean>();

		lista1 = session.createCriteria(JugadorBean.class)
				.add(Restrictions.eq("nombreUsr", r.getNombreusr())).list();
		lista2 = session.createCriteria(JugadorBean.class)
				.add(Restrictions.eq("correo", r.getCorreo())).list();
		cartasAgua = session.createCriteria(CartaBean.class).add(Restrictions.eq("elemento", "agua")).list();
		cartasFuego = session.createCriteria(CartaBean.class).add(Restrictions.eq("elemento", "fuego")).list();
		for (int i = 0; i < 4; i++) {
			CartasDisponiblesBean disponibles = new CartasDisponiblesBean();
			disponibles.setJugadorRef(jugador);
			disponibles.setCartaRef(cartasAgua.get(i));
			jugador.getCartasDisponiblesRef().add(disponibles);
		}
		for(int i=0;i<4;i++){
			CartasDisponiblesBean disponibles = new CartasDisponiblesBean();
			disponibles.setJugadorRef(jugador);
			disponibles.setCartaRef(cartasFuego.get(i));
			jugador.getCartasDisponiblesRef().add(disponibles);
		}
		if (lista1.isEmpty() && lista2.isEmpty()) {
			session.save(jugador);
			session.getTransaction().commit();
			session.close();
			return true;

		} else {
			session.getTransaction().commit();
			session.close();
			return false;
		}

	}

	public JugadorTO consultar(JugadorBean regis) {

		Session session = SessionHibernate.getInstance().getSession();

		session.beginTransaction();

		List<JugadorBean> lista;
		lista = new ArrayList<JugadorBean>();
		JugadorTO jugador = new JugadorTO();
		List<VerificarLogin> aux = new ArrayList<VerificarLogin>();
		lista = session.createCriteria(JugadorBean.class)
				.add(Restrictions.eq("nombreUsr", regis.getNombreUsr()))
				.add(Restrictions.eq("clave", regis.getClave())).list();
		aux = session.createCriteria(VerificarLogin.class)
				.add(Restrictions.eq("nombreUsr", regis.getNombreUsr())).list();

		if (!lista.isEmpty()) {

			if (aux.isEmpty()) {
				VerificarLogin verificar = new VerificarLogin();
				verificar.setNombreUsr(regis.getNombreUsr());
				verificar.setIdJugador(lista.get(0).getId());
				session.save(verificar);
				jugador.setNombreusr(lista.get(0).getNombreUsr());
				jugador.setCorreo(lista.get(0).getCorreo());
				jugador.setClave(lista.get(0).getClave());
				jugador.setIdjugador(lista.get(0).getId());
				jugador.setNivel(lista.get(0).getNivel());
				jugador.setNombreAvatar(lista.get(0).getNombreAvatar());
				jugador.setPuntos(lista.get(0).getPuntos());
				jugador.setPartidasGanadas(lista.get(0).getPartidasGanadas().size());
				jugador.setPartidasPerdidas(lista.get(0).getPartidasPerdidas().size());
				jugador.setPartidasEmpatas(lista.get(0).getPartidasEmpatadasParticipante1().size()+lista.get(0).getPartidasEmpatadasParticipante2().size());
				
				session.getTransaction().commit();
				session.close();
				return jugador;

			} else {
				jugador.setNombreusr(lista.get(0).getNombreUsr());
				jugador.setCorreo(lista.get(0).getCorreo());
				jugador.setClave(lista.get(0).getClave());
				jugador.setIdjugador(-1);
				session.getTransaction().commit();
				session.close();
				return jugador;
			}
		} else {
			jugador.setIdjugador(0);
			session.getTransaction().commit();
			session.close();
			return jugador;
		}
	}

	public JugadorTO consultarPerfil(String nombre) {
		CartasDisponiblesTO carta = new CartasDisponiblesTO();
		List<CartaBean> cartasdisponibles = new ArrayList<CartaBean>();
		List<JugadorBean> jugadorAux = new ArrayList<JugadorBean>();
		cartasdisponibles = carta.getCartas(nombre);
		Session session = SessionHibernate.getInstance().getSession();
		session.beginTransaction();
		jugadorAux = session.createCriteria(JugadorBean.class)
				.add(Restrictions.eq("nombreUsr", nombre)).list();
		session.getTransaction().commit();
		session.close();
		List<CartasDisponiblesTO> lista = new ArrayList<CartasDisponiblesTO>();

		for (int i = 0; i < cartasdisponibles.size(); i++) {
			CartasDisponiblesTO aux = new CartasDisponiblesTO();
			aux.setFuerzanorte(cartasdisponibles.get(i).getFuerzanorte());
			aux.setFuerzasur(cartasdisponibles.get(i).getFuerzasur());
			aux.setFuerzaeste(cartasdisponibles.get(i).getFuerzaeste());
			aux.setFuerzaoeste(cartasdisponibles.get(i).getFuerzaoeste());
			aux.setId(cartasdisponibles.get(i).getId());
			aux.setElemento(cartasdisponibles.get(i).getElemento());
			lista.add(i, aux);

		}
		JugadorTO jugador = new JugadorTO(jugadorAux.get(0).getNombreUsr(),
				jugadorAux.get(0).getCorreo(), jugadorAux.get(0).getClave(),
				jugadorAux.get(0).getId(), jugadorAux.get(0).getNivel(),
				jugadorAux.get(0).getPuntos(), jugadorAux.get(0)
						.getNombreAvatar(), lista);
		return jugador;

	}

	public void desconectar(String nombre) {
		Session session = SessionHibernate.getInstance().getSession();

		session.beginTransaction();

		List<VerificarLogin> lista = new ArrayList<VerificarLogin>();
		lista = session.createCriteria(VerificarLogin.class)
				.add(Restrictions.eq("nombreUsr", nombre)).list();
		if (!lista.isEmpty()) {
			session.delete(lista.get(0));
			session.getTransaction().commit();
			session.close();
		} else {
			session.getTransaction().commit();
			session.close();
		}

	}

	public List<CartasDisponiblesTO> filtrarCartas(String elemento, int filtrado) {
		Session session = SessionHibernate.getInstance().getSession();

		session.beginTransaction();
		// filtar por elemento
		List<CartaBean> cartas = new ArrayList<CartaBean>();
		List<CartasDisponiblesTO> lista = new ArrayList<CartasDisponiblesTO>();
		int sumafuerzas;

		if (filtrado == 1) {
			cartas = session.createCriteria(CartaBean.class)
					.add(Restrictions.eq("elemento", elemento)).list();
			for (int i = 0; i < cartas.size(); i++) {
				CartasDisponiblesTO aux = new CartasDisponiblesTO();
				aux.setFuerzanorte(cartas.get(i).getFuerzanorte());

				aux.setFuerzasur(cartas.get(i).getFuerzasur());
				aux.setFuerzaeste(cartas.get(i).getFuerzaeste());
				aux.setFuerzaoeste(cartas.get(i).getFuerzaoeste());
				aux.setElemento(cartas.get(i).getElemento());
				lista.add(aux);
			}
			session.getTransaction().commit();
			session.close();
			return lista;

		} else if (filtrado == 2) {
			cartas = session.createCriteria(CartaBean.class)
					.add(Restrictions.between("sumafuerzas", 0, 9)).list();
			for (int i = 0; i < cartas.size(); i++) {
				CartasDisponiblesTO aux = new CartasDisponiblesTO();
				aux.setFuerzanorte(cartas.get(i).getFuerzanorte());
				aux.setFuerzasur(cartas.get(i).getFuerzasur());
				aux.setFuerzaeste(cartas.get(i).getFuerzaeste());
				aux.setFuerzaoeste(cartas.get(i).getFuerzaoeste());
				aux.setElemento(cartas.get(i).getElemento());
				lista.add(aux);
			}
			session.getTransaction().commit();
			session.close();
			return lista;

		} else if (filtrado == 3) {
			cartas = session.createCriteria(CartaBean.class)
					.add(Restrictions.between("sumafuerzas", 10, 19)).list();
			for (int i = 0; i < cartas.size(); i++) {
				CartasDisponiblesTO aux = new CartasDisponiblesTO();
				aux.setFuerzanorte(cartas.get(i).getFuerzanorte());
				aux.setFuerzasur(cartas.get(i).getFuerzasur());
				aux.setFuerzaeste(cartas.get(i).getFuerzaeste());
				aux.setFuerzaoeste(cartas.get(i).getFuerzaoeste());
				aux.setElemento(cartas.get(i).getElemento());
				lista.add(aux);
			}
			session.getTransaction().commit();
			session.close();
			return lista;
		} else if (filtrado == 4) {
			cartas = session.createCriteria(CartaBean.class)
					.add(Restrictions.between("sumafuerzas", 20, 29)).list();
			for (int i = 0; i < cartas.size(); i++) {
				CartasDisponiblesTO aux = new CartasDisponiblesTO();
				aux.setFuerzanorte(cartas.get(i).getFuerzanorte());
				aux.setFuerzasur(cartas.get(i).getFuerzasur());
				aux.setFuerzaeste(cartas.get(i).getFuerzaeste());
				aux.setFuerzaoeste(cartas.get(i).getFuerzaoeste());
				aux.setElemento(cartas.get(i).getElemento());
				lista.add(aux);
			}
			session.getTransaction().commit();
			session.close();
			return lista;
		} else {
			cartas = session.createCriteria(CartaBean.class)
					.add(Restrictions.between("sumafuerzas", 30, 36)).list();
			for (int i = 0; i < cartas.size(); i++) {
				CartasDisponiblesTO aux = new CartasDisponiblesTO();
				aux.setFuerzanorte(cartas.get(i).getFuerzanorte());
				aux.setFuerzasur(cartas.get(i).getFuerzasur());
				aux.setFuerzaeste(cartas.get(i).getFuerzaeste());
				aux.setFuerzaoeste(cartas.get(i).getFuerzaoeste());
				aux.setElemento(cartas.get(i).getElemento());
				lista.add(aux);
			}
			session.getTransaction().commit();
			session.close();
			return lista;
		}

	}

	public String comercioJugadores(int idJugador1, String nombreJugador2,
			int idCartaOfertada, int idCartaPropuesta) {
		Boolean transaccion = false;
		Boolean verificar = false;
		Session session = SessionHibernate.getInstance().getSession();
		session.beginTransaction();
		JugadorBean jugador1 = new JugadorBean();
		List<JugadorBean> jugador2 = new ArrayList<JugadorBean>();
		CartasOfertadasBean oferta = new CartasOfertadasBean();
		CartasOfertadasBean propuesta = new CartasOfertadasBean();
		CartaBean carta1 = new CartaBean();
		CartaBean carta2 = new CartaBean();
		RespuestaBean respuesta = new RespuestaBean();

		jugador1 = (JugadorBean) session.load(JugadorBean.class, idJugador1);
		jugador2 = session.createCriteria(JugadorBean.class)
				.add(Restrictions.eq("nombreUsr", nombreJugador2)).list();

		for (int i = 0; i < jugador1.getCartasDisponiblesRef().size(); i++) {
			if (jugador1.getCartasDisponiblesRef().get(i).getCartaRef().getId() == idCartaPropuesta) {
				session.getTransaction().commit();
				session.close();
				return "Ya tienes la Carta Propuesta";
			}
		}
		for (int i = 0; i < jugador2.get(0).getCartasDisponiblesRef().size(); i++) {
			if (jugador2.get(0).getCartasDisponiblesRef().get(i).getCartaRef()
					.getId() == idCartaOfertada) {
				session.getTransaction().commit();
				session.close();
				return "El remitente ya tiene esta Carta";
			}
		}

		for (int i = 0; i < jugador2.get(0).getCartasOfertadasRef().size(); i++) {
			if (jugador2.get(0).getCartasOfertadasRef().get(i).getCartaRef()
					.getId() == idCartaPropuesta) {
				verificar = true;

			}
		}
		if (!verificar) {
			session.getTransaction().commit();
			session.close();
			return "La carta ya fue retirada del sistema";
		}

		CartasDisponiblesBean aux = new CartasDisponiblesBean();
		CartasOfertadasBean ofertada = new CartasOfertadasBean();

		for (int i = 0; i < jugador1.getCartasOfertadasRef().size(); i++) {
			if (jugador1.getCartasOfertadasRef().get(i).getCartaRef().getId() == idCartaOfertada) {
				ofertada = jugador1.getCartasOfertadasRef().get(i);
				carta1 = jugador1.getCartasOfertadasRef().get(i).getCartaRef();
				aux.setJugadorRef(jugador2.get(0));
				aux.setCartaRef(jugador1.getCartasOfertadasRef().get(i)
						.getCartaRef());
				jugador2.get(0).getCartasDisponiblesRef().add(aux);
				jugador1.getCartasOfertadasRef().remove(i);
				session.update(jugador1);
				session.update(jugador2.get(0));
				session.save(aux);
				session.delete(ofertada);
				transaccion = true;
				break;
			}

		}
		if (!transaccion) {
			session.getTransaction().commit();
			session.close();
			return "No se completo la transaccion";
		}
		transaccion = false;
		CartasDisponiblesBean aux2 = new CartasDisponiblesBean();
		CartasOfertadasBean cartaPropuesta = new CartasOfertadasBean();

		for (int i = 0; i < jugador2.get(0).getCartasOfertadasRef().size(); i++) {
			if (jugador2.get(0).getCartasOfertadasRef().get(i).getCartaRef()
					.getId() == idCartaPropuesta) {
				cartaPropuesta = jugador2.get(0).getCartasOfertadasRef().get(i);
				carta2 = jugador2.get(0).getCartasOfertadasRef().get(i)
						.getCartaRef();
				aux2.setJugadorRef(jugador1);
				aux2.setCartaRef(jugador2.get(0).getCartasOfertadasRef().get(i)
						.getCartaRef());
				jugador1.getCartasDisponiblesRef().add(aux2);
				jugador2.get(0).getCartasOfertadasRef().remove(i);
				session.update(jugador1);
				session.update(jugador2.get(0));
				session.save(aux2);
				session.delete(cartaPropuesta);
				transaccion = true;
				break;

			}
		}
		if (!transaccion) {
			session.getTransaction().commit();
			session.close();
			return "No se completo la transaccion";
		}
		MensajesBean mensaje = new MensajesBean();
		mensaje.setJugadorRef(jugador2.get(0));
		mensaje.setMensaje("Verificacion");
		mensaje.setRemitente(jugador1.getNombreUsr());
		mensaje.setCartaOfertada(carta1);
		mensaje.setCartaPropuesta(carta2);
		carta1.getMensajesOferta().add(mensaje);
		carta2.getMensajesPropuesta().add(mensaje);
		jugador2.get(0).getMensajes().add(mensaje);
		session.save(mensaje);
		session.update(jugador2.get(0));
		session.update(carta1);
		session.update(carta2);

		session.getTransaction().commit();
		session.close();
		return "Transaccion Completa";

	}

	public boolean consultarCartasSeleccionadas(int id1, int id2, int id3,
			int id4, int id5, int id6, int id7, int id8, int idJugador) {
		Session session = SessionHibernate.getInstance().getSession();
		session.beginTransaction();
		int count = 0;
		JugadorBean jugador = new JugadorBean();
		List<CartaBean> cartas = new ArrayList<CartaBean>();
		jugador = (JugadorBean) session.load(JugadorBean.class, idJugador);
		for (int i = 0; i < jugador.getCartasDisponiblesRef().size(); i++) {
			cartas.add(jugador.getCartasDisponiblesRef().get(i).getCartaRef());
		}
		for (int i = 0; i < cartas.size(); i++) {
			if (cartas.get(i).getId() == id1 || cartas.get(i).getId() == id2
					|| cartas.get(i).getId() == id3
					|| cartas.get(i).getId() == id4
					|| cartas.get(i).getId() == id5
					|| cartas.get(i).getId() == id6
					|| cartas.get(i).getId() == id7
					|| cartas.get(i).getId() == id8) {
				count++;
			}

		}
		if (count == 8) {
			session.getTransaction().commit();
			session.close();
			return true;
		} else
			session.getTransaction().commit();
			session.close();
			return false;

	}

	public String comercioComputadora(String nombre, int idcarta) {
		int costo;
		int puntosJugador;
		List<JugadorBean> comprador = new ArrayList<JugadorBean>();
		CartaBean cartavendida = new CartaBean();
		CartasDisponiblesBean disponibles = new CartasDisponiblesBean();
		Session session = SessionHibernate.getInstance().getSession();
		session.beginTransaction();
		comprador = session.createCriteria(JugadorBean.class)
				.add(Restrictions.eq("nombreUsr", nombre)).list();
		for (int i = 0; i < comprador.get(0).getCartasOfertadasRef().size(); i++) {
			if (comprador.get(0).getCartasOfertadasRef().get(i).getCartaRef()
					.getId() == idcarta) {
				session.getTransaction().commit();
				session.close();
				return "Tienes esa Carta en Oferta";
			}
		}
		for (int i = 0; i < comprador.get(0).getCartasDisponiblesRef().size(); i++) {
			if ((comprador.get(0).getCartasDisponiblesRef().get(i)
					.getCartaRef().getId() == idcarta)) {
				session.getTransaction().commit();
				session.close();
				return "tiene_carta";

			}
		}
		cartavendida = (CartaBean) session.load(CartaBean.class, idcarta);
		if (comprador.get(0).getPuntos() >= cartavendida.getCosto()) {
			disponibles.setCartaRef(cartavendida);
			disponibles.setJugadorRef(comprador.get(0));
			costo = cartavendida.getCosto();
			puntosJugador = comprador.get(0).getPuntos();
			comprador.get(0).setPuntos(puntosJugador - costo);
			comprador.get(0).getCartasDisponiblesRef().add(disponibles);
			session.save(disponibles);
			session.update(comprador.get(0));
			session.getTransaction().commit();
			session.close();
			return "compra_exitosa";

		} else {
			session.getTransaction().commit();
			session.close();
			return "faltan_puntos";
		}

	}

	public List<CartasDisponiblesTO> retornarCartas() {
		Session session = SessionHibernate.getInstance().getSession();
		session.beginTransaction();
		List<CartasDisponiblesTO> cartas = new ArrayList<CartasDisponiblesTO>();
		List<CartaBean> lista = new ArrayList<CartaBean>();
		lista = session.createCriteria(CartaBean.class).list();
		for (int i = 0; i < lista.size(); i++) {
			CartasDisponiblesTO aux = new CartasDisponiblesTO();
			aux.setFuerzanorte(lista.get(i).getFuerzanorte());
			aux.setFuerzasur(lista.get(i).getFuerzasur());
			aux.setFuerzaeste(lista.get(i).getFuerzaeste());
			aux.setFuerzaoeste(lista.get(i).getFuerzaoeste());
			aux.setElemento(lista.get(i).getElemento());
			aux.setSumafuerzas(lista.get(i).getSumafuerzas());
			aux.setCosto(lista.get(i).getCosto());
			aux.setId(lista.get(i).getId());
			cartas.add(aux);
		}
		session.getTransaction().commit();
		session.close();
		return cartas;

	}

	public CartaTO retornaCarta(int idCarta) {
		Session session = SessionHibernate.getInstance().getSession();
		session.beginTransaction();
		CartaTO carta = new CartaTO();
		CartaBean aux = new CartaBean();
		aux = (CartaBean) session.load(CartaBean.class, idCarta);
		carta.setFuerzanorte(aux.getFuerzanorte());
		carta.setFuerzasur(aux.getFuerzasur());
		carta.setFuerzaeste(aux.getFuerzaeste());
		carta.setFuerzaoeste(aux.getFuerzaoeste());
		carta.setElemento(aux.getElemento());
		carta.setSumafuerzas(aux.getSumafuerzas());
		session.getTransaction().commit();
		session.close();
		return carta;

	}

	public String mandarSolicitudTrueque(int idJugador, int idcarta1,
			int idCartaOferta) {
		Session session = SessionHibernate.getInstance().getSession();
		session.beginTransaction();
		JugadorBean aux = new JugadorBean();
		CartasOfertadasBean cartaPropuesta = new CartasOfertadasBean();
		CartasOfertadasBean oferta = new CartasOfertadasBean();
		JugadorBean jugador = new JugadorBean();
		CartaBean verificarOferta = new CartaBean();
		jugador = (JugadorBean) session.load(JugadorBean.class, idJugador);
		try {
			oferta = (CartasOfertadasBean) session.load(
					CartasOfertadasBean.class, idCartaOferta);

		} catch (Exception e) {
			session.getTransaction().commit();
			session.close();
			return "Esta carta fue retirada hace poco del sistema";
		}
		verificarOferta = oferta.getCartaRef();

		for (int i = 0; i < oferta.getJugadorRef().getCartasDisponiblesRef()
				.size(); i++) {
			if (oferta.getJugadorRef().getCartasDisponiblesRef().get(i)
					.getCartaRef().getId() == idcarta1) {
				session.getTransaction().commit();
				session.close();
				return "Esta carta ya la tiene el otro jugador como disponible";
			}
		}
		for (int i = 0; i < oferta.getJugadorRef().getCartasOfertadasRef()
				.size(); i++) {
			if (oferta.getJugadorRef().getCartasOfertadasRef().get(i)
					.getCartaRef().getId() == idcarta1) {
				session.getTransaction().commit();
				session.close();
				return "Esta carta ya la tiene el otro jugador como ofertada";
			}
		}

		for (int i = 0; i < jugador.getCartasOfertadasRef().size(); i++) {
			if (jugador.getCartasOfertadasRef().get(i).getCartaRef()
					.equals(oferta.getCartaRef())) {
				session.getTransaction().commit();
				session.close();
				return "La carta que quieres la tienes como Ofertada";
			}
		}
		for (int i = 0; i < jugador.getCartasDisponiblesRef().size(); i++) {
			if (jugador.getCartasDisponiblesRef().get(i).getCartaRef()
					.equals(oferta.getCartaRef())) {
				session.getTransaction().commit();
				session.close();
				return "La carta que quieres ya la tienes y esta Disponible para usar";
			}
		}
		for (int i = 0; i < oferta.getJugadorRef().getRespuesta().size(); i++) {
			if (oferta.getJugadorRef().getRespuesta().get(i)
					.getCartasPropuesta().getCartaRef().getId() == idcarta1
					&& oferta.getJugadorRef().getRespuesta().get(i)
							.getRemitente().equals(jugador.getNombreUsr())
					&& oferta.getJugadorRef().getRespuesta().get(i)
							.getCartasOfertada().getCartaRef()
							.equals(verificarOferta)) {
				session.getTransaction().commit();
				session.close();
				return "La solicitud ya fue enviada";

			}

		}
		for (int i = 0; i < jugador.getCartasOfertadasRef().size(); i++) {
			if (jugador.getCartasOfertadasRef().get(i).getCartaRef().getId() == idcarta1) {
				cartaPropuesta = jugador.getCartasOfertadasRef().get(i);
			}
		}

		aux = oferta.getJugadorRef();

		RespuestaBean respuesta = new RespuestaBean();
		respuesta.setCartasOfertada(oferta);
		respuesta.setCartasPropuesta(cartaPropuesta);
		respuesta.setJugadorRef(aux);
		respuesta.setRemitente(jugador.getNombreUsr());
		aux.getRespuesta().add(respuesta);
		session.save(respuesta);
		session.update(aux);
		session.getTransaction().commit();
		session.close();
		return "Solicitud Enviada Satisfactoriamente";
	}

	public List<CartasDisponiblesTO> ofertarCartas(int idJugador, int idCarta) {
		Session session = SessionHibernate.getInstance().getSession();
		session.beginTransaction();
		List<CartasDisponiblesTO> cartas = new ArrayList<CartasDisponiblesTO>();
		JugadorBean jugador = new JugadorBean();
		CartaBean carta = new CartaBean();
		jugador = (JugadorBean) session.load(JugadorBean.class, idJugador);
		carta = (CartaBean) session.load(CartaBean.class, idCarta);
		CartasOfertadasBean oferta = new CartasOfertadasBean();
		if (jugador.getCartasDisponiblesRef().size() == 8) {
			session.getTransaction().commit();
			session.close();
			return null;
		}
		for (int i = 0; i < jugador.getCartasOfertadasRef().size(); i++) {
			CartasDisponiblesTO cartaAux = new CartasDisponiblesTO(jugador
					.getCartasOfertadasRef().get(i).getCartaRef().getId(),
					jugador.getCartasOfertadasRef().get(i).getCartaRef()
							.getFuerzanorte(), jugador.getCartasOfertadasRef()
							.get(i).getCartaRef().getFuerzasur(), jugador
							.getCartasOfertadasRef().get(i).getCartaRef()
							.getFuerzaeste(), jugador.getCartasOfertadasRef()
							.get(i).getCartaRef().getFuerzaoeste(), jugador
							.getCartasOfertadasRef().get(i).getCartaRef()
							.getElemento(), jugador.getCartasOfertadasRef()
							.get(i).getCartaRef().getSumafuerzas(), jugador
							.getCartasOfertadasRef().get(i).getCartaRef()
							.getCosto(), jugador.getCartasOfertadasRef().get(i)
							.getId());

			cartas.add(cartaAux);

		}
		CartasDisponiblesBean disponibles = new CartasDisponiblesBean();

		for (int i = 0; i < jugador.getCartasDisponiblesRef().size(); i++) {
			if (jugador.getCartasDisponiblesRef().get(i).getCartaRef() == carta) {
				oferta.setCartaRef(carta);
				oferta.setJugadorRef(jugador);
				disponibles.setCartaRef(carta);
				disponibles.setJugadorRef(jugador);
				jugador.getCartasOfertadasRef().add(oferta);
				jugador.getCartasDisponiblesRef().remove(i);
				session.save(oferta);
				session.update(jugador);
				session.delete(disponibles);
				session.getTransaction().commit();

				CartasDisponiblesTO aux = new CartasDisponiblesTO(
						carta.getId(), carta.getFuerzanorte(),
						carta.getFuerzasur(), carta.getFuerzaeste(),
						carta.getFuerzaoeste(), carta.getElemento(),
						carta.getSumafuerzas(), carta.getCosto(), 0);
				cartas.add(aux);
				session.close();
				return cartas;
			}
		}
		session.getTransaction().commit();
		session.close();
		return null;
	}

	public List<CartasDisponiblesTO> retornarCartasOfertadas(int idJugador) {
		Session session = SessionHibernate.getInstance().getSession();
		session.beginTransaction();
		JugadorBean jugador = new JugadorBean();
		List<CartasDisponiblesTO> cartas = new ArrayList<CartasDisponiblesTO>();
		jugador = (JugadorBean) session.load(JugadorBean.class, idJugador);
		for (int i = 0; i < jugador.getCartasOfertadasRef().size(); i++) {
			CartasDisponiblesTO cartaAux = new CartasDisponiblesTO(jugador
					.getCartasOfertadasRef().get(i).getCartaRef().getId(),
					jugador.getCartasOfertadasRef().get(i).getCartaRef()
							.getFuerzanorte(), jugador.getCartasOfertadasRef()
							.get(i).getCartaRef().getFuerzasur(), jugador
							.getCartasOfertadasRef().get(i).getCartaRef()
							.getFuerzaeste(), jugador.getCartasOfertadasRef()
							.get(i).getCartaRef().getFuerzaoeste(), jugador
							.getCartasOfertadasRef().get(i).getCartaRef()
							.getElemento(), jugador.getCartasOfertadasRef()
							.get(i).getCartaRef().getSumafuerzas(), jugador
							.getCartasOfertadasRef().get(i).getCartaRef()
							.getCosto(), jugador.getCartasOfertadasRef().get(i)
							.getId());
			cartas.add(cartaAux);

		}
		session.getTransaction().commit();
		session.close();
		return cartas;

	}

	public boolean retirarCartaOfertada(int idJugador, int idCarta) {
		Session session = SessionHibernate.getInstance().getSession();
		session.beginTransaction();
		JugadorBean jugador = new JugadorBean();
		CartaBean carta = new CartaBean();
		CartasOfertadasBean oferta = new CartasOfertadasBean();
		CartasDisponiblesBean disponibles = new CartasDisponiblesBean();
		jugador = (JugadorBean) session.load(JugadorBean.class, idJugador);
		carta = (CartaBean) session.load(CartaBean.class, idCarta);
		for (int i = 0; i < jugador.getCartasOfertadasRef().size(); i++) {
			if (jugador.getCartasOfertadasRef().get(i).getCartaRef() == carta) {
				oferta.setCartaRef(carta);
				oferta.setJugadorRef(jugador);
				disponibles.setJugadorRef(jugador);
				disponibles.setCartaRef(carta);
				jugador.getCartasOfertadasRef().remove(i);
				jugador.getCartasDisponiblesRef().add(disponibles);
				session.save(disponibles);
				session.delete(oferta);
				session.update(jugador);
				session.getTransaction().commit();
				session.close();
				return true;

			}
		}
		session.getTransaction().commit();
		session.close();
		return false;

	}

	public List<CartasDisponiblesTO> retornarCartasOfertadasSistemas(
			int idJugador) {
		boolean resultado = true;
		Session session = SessionHibernate.getInstance().getSession();
		session.beginTransaction();
		List<CartasDisponiblesTO> cartas = new ArrayList<CartasDisponiblesTO>();
		List<CartasOfertadasBean> cartasOfertadas = new ArrayList<CartasOfertadasBean>();
		List<CartasOfertadasBean> cartasAux = new ArrayList<CartasOfertadasBean>();
		JugadorBean jugador = new JugadorBean();
		jugador = (JugadorBean) session.load(JugadorBean.class, idJugador);
		cartasOfertadas = session.createCriteria(CartasOfertadasBean.class)
				.list();
		for (int i = 0; i < cartasOfertadas.size(); i++) {
			for (int j = 0; j < jugador.getCartasOfertadasRef().size(); j++) {
				if (jugador.getCartasOfertadasRef().get(j).getId() == cartasOfertadas
						.get(i).getId()) {
					resultado = false;
				}
			}
			if (resultado) {
				cartasAux.add(cartasOfertadas.get(i));
			}
			resultado = true;
		}
		for (int i = 0; i < cartasAux.size(); i++) {
			CartasDisponiblesTO aux = new CartasDisponiblesTO(cartasAux.get(i)
					.getCartaRef().getId(), cartasAux.get(i).getCartaRef()
					.getFuerzanorte(), cartasAux.get(i).getCartaRef()
					.getFuerzasur(), cartasAux.get(i).getCartaRef()
					.getFuerzaeste(), cartasAux.get(i).getCartaRef()
					.getFuerzaoeste(), cartasAux.get(i).getCartaRef()
					.getElemento(), cartasAux.get(i).getCartaRef()
					.getSumafuerzas(), cartasAux.get(i).getCartaRef()
					.getCosto(), cartasAux.get(i).getId());
			cartas.add(aux);
		}
		session.getTransaction().commit();
		session.close();
		return cartas;
	}

	public List<MensajeTrueque> retornarMensajes(int idJugador) {
		Session session = SessionHibernate.getInstance().getSession();
		session.beginTransaction();
		JugadorBean jugador = new JugadorBean();
		List<MensajesBean> confirmacion = new ArrayList<MensajesBean>();
		jugador = (JugadorBean) session.load(JugadorBean.class, idJugador);

		confirmacion = jugador.getMensajes();
		List<RespuestaBean> respuestas = new ArrayList<RespuestaBean>();
		List<MensajeTrueque> mensajes = new ArrayList<MensajeTrueque>();
		List<MensajesBean> mensajesConfirmacion = new ArrayList<MensajesBean>();
		respuestas = jugador.getRespuesta();
		mensajesConfirmacion = jugador.getMensajes();

		for (int i = 0; i < respuestas.size(); i++) {

			MensajeTrueque mensaje = new MensajeTrueque();
			CartasDisponiblesTO miCarta = new CartasDisponiblesTO(respuestas
					.get(i).getCartasOfertada().getCartaRef().getId(),
					respuestas.get(i).getCartasOfertada().getCartaRef()
							.getFuerzanorte(), respuestas.get(i)
							.getCartasOfertada().getCartaRef().getFuerzasur(),
					respuestas.get(i).getCartasOfertada().getCartaRef()
							.getFuerzaeste(),
					respuestas.get(i).getCartasOfertada().getCartaRef()
							.getFuerzaoeste(), respuestas.get(i)
							.getCartasOfertada().getCartaRef().getElemento(),
					respuestas.get(i).getCartasOfertada().getCartaRef()
							.getSumafuerzas(), respuestas.get(i)
							.getCartasOfertada().getCartaRef().getCosto(), 0);

			CartasDisponiblesTO laCarta = new CartasDisponiblesTO(respuestas
					.get(i).getCartasPropuesta().getCartaRef().getId(),
					respuestas.get(i).getCartasPropuesta().getCartaRef()
							.getFuerzanorte(), respuestas.get(i)
							.getCartasPropuesta().getCartaRef().getFuerzasur(),
					respuestas.get(i).getCartasPropuesta().getCartaRef()
							.getFuerzaoeste(), respuestas.get(i)
							.getCartasPropuesta().getCartaRef()
							.getFuerzaoeste(), respuestas.get(i)
							.getCartasPropuesta().getCartaRef().getElemento(),
					respuestas.get(i).getCartasPropuesta().getCartaRef()
							.getSumafuerzas(), respuestas.get(i)
							.getCartasPropuesta().getCartaRef().getCosto(), 0);

			mensaje.setNombreUsr(respuestas.get(i).getRemitente());
			mensaje.setIdRespuesta(respuestas.get(i).getId());
			mensaje.setMiCarta(miCarta);
			mensaje.setLaCarta(laCarta);
			mensaje.setTipo("Trueque");
			mensajes.add(mensaje);

		}
		for (int i = 0; i < mensajesConfirmacion.size(); i++) {
			MensajeTrueque mensaje = new MensajeTrueque();
			CartasDisponiblesTO miCarta = new CartasDisponiblesTO(
					mensajesConfirmacion.get(i).getCartaOfertada().getId(),
					mensajesConfirmacion.get(i).getCartaOfertada()
							.getFuerzanorte(), mensajesConfirmacion.get(i)
							.getCartaOfertada().getFuerzasur(),
					mensajesConfirmacion.get(i).getCartaOfertada()
							.getFuerzaeste(), mensajesConfirmacion.get(i)
							.getCartaOfertada().getFuerzaoeste(),
					mensajesConfirmacion.get(i).getCartaOfertada()
							.getElemento(), mensajesConfirmacion.get(i)
							.getCartaOfertada().getSumafuerzas(),
					mensajesConfirmacion.get(i).getCartaOfertada().getCosto(),
					0);
			CartasDisponiblesTO laCarta = new CartasDisponiblesTO(
					mensajesConfirmacion.get(i).getCartaPropuesta().getId(),
					mensajesConfirmacion.get(i).getCartaPropuesta()
							.getFuerzanorte(), mensajesConfirmacion.get(i)
							.getCartaPropuesta().getFuerzasur(),
					mensajesConfirmacion.get(i).getCartaPropuesta()
							.getFuerzaeste(), mensajesConfirmacion.get(i)
							.getCartaPropuesta().getFuerzaoeste(),
					mensajesConfirmacion.get(i).getCartaPropuesta()
							.getElemento(), mensajesConfirmacion.get(i)
							.getCartaPropuesta().getSumafuerzas(),
					mensajesConfirmacion.get(i).getCartaPropuesta().getCosto(),
					0);
			mensaje.setLaCarta(laCarta);
			mensaje.setMiCarta(miCarta);
			mensaje.setNombreUsr(mensajesConfirmacion.get(i).getRemitente());
			mensaje.setIdRespuesta(mensajesConfirmacion.get(i).getId());
			mensaje.setTipo("Confirmacion");
			mensajes.add(mensaje);

		}
		session.getTransaction().commit();
		session.close();
		return mensajes;

	}

	public boolean eliminarRespuesta(int idRespuesta) {
		Session session = SessionHibernate.getInstance().getSession();
		session.beginTransaction();
		RespuestaBean respuestaEliminada = new RespuestaBean();
		try {
			respuestaEliminada = (RespuestaBean) session.load(
					RespuestaBean.class, idRespuesta);
			session.delete(respuestaEliminada);
			session.getTransaction().commit();
			session.close();
			return true;

		} catch (Exception e) {
			session.getTransaction().commit();
			session.close();
			return false;
		}

	}

	public boolean eliminarMensajeConfirmacion(int idMensaje) {
		Session session = SessionHibernate.getInstance().getSession();
		session.beginTransaction();
		MensajesBean mensajeEliminado = new MensajesBean();
		try {
			mensajeEliminado = (MensajesBean) session.load(MensajesBean.class,
					idMensaje);
			session.delete(mensajeEliminado);
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (Exception e) {
			session.getTransaction().commit();
			session.close();
			return false;
			// TODO: handle exception
		}

		// strbuf.append("SELECT imagen FROM Jugador ");
	}

	public byte[][] publicidad() {
		List<byte[]> lista = new ArrayList<byte[]>();

		Session session = SessionHibernate.getInstance().getSession();
		session.beginTransaction();

		StringBuffer strbuf = new StringBuffer();

		strbuf.append("SELECT p.imagen FROM Publicidad AS p ");
		strbuf.append(" ORDER BY RAND()");

		Query consulta = session.createQuery(strbuf.toString());
		consulta.setMaxResults(5);

		lista = consulta.list();
		// for (int i = 0; i < 5; i++) {
		// aleatorio = (int) Math.floor(Math.random() * (aux.size() - 0 + 1)
		// + 0);
		// lista.add(aux.get(aleatorio));
		//
		// }
		//
		// byte[][] x = new byte[5][];
		//
		// x[i] = aux.get(aleatorio);
		session.getTransaction().commit();
		session.close();

		return lista.toArray(new byte[5][]);
	}

}
