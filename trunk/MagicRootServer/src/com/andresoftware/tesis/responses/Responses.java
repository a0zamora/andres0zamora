package com.andresoftware.tesis.responses;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.andresoftware.tesis.commands.CardTO;
import com.andresoftware.tesis.commands.CommandCreateUser;
import com.andresoftware.tesis.commands.CommandCreateUserAnswer;
import com.andresoftware.tesis.commands.CommandLogin;
import com.andresoftware.tesis.commands.CommandLoginAnswer;
import com.andresoftware.tesis.commands.CommandPlayCards;
import com.andresoftware.tesis.commands.CommandUserInformation;
import com.andresoftware.tesis.model.CartaBean;
import com.andresoftware.tesis.model.CartasDisponiblesBean;
import com.andresoftware.tesis.model.JugadorBean;
import com.andresoftware.tesis.model.SessionHibernate;
import com.andresoftware.tesis.model.VerificarLogin;
import com.andresoftware.tesis.server.ClientThread;

public class Responses {
	ClientThread hilo;
	//--------------------------------------------------------------------------------------
	public Responses(ClientThread hiloDeCliente) {
		this.hilo = hiloDeCliente;
	}
	//--------------------------------------------------------------------------------------
	@SuppressWarnings("unchecked")
	public void createNewUser(String text) {
		CommandCreateUser createUser = new CommandCreateUser(text);
		String answer="";
		JugadorBean jugador = new JugadorBean();
		jugador.setNombreUsr(createUser.getName());
		jugador.setCorreo("ninguno");
		jugador.setClave(createUser.getPassword());
		jugador.setPuntos(6);
		jugador.setNivel(3);
		jugador.setNombreAvatar("CardsJPG/Heroe.jpeg");

		Session session = SessionHibernate.getInstance().getSession();
		session.beginTransaction();
		List<JugadorBean> lista1;
		List<CartaBean> cartasAgua = new ArrayList<CartaBean>();
		List<CartaBean> cartasFuego = new ArrayList<CartaBean>();
		lista1 = new ArrayList<JugadorBean>();
		lista1 = session.createCriteria(JugadorBean.class)
				.add(Restrictions.eq("nombreUsr", createUser.getName())).list();
		cartasAgua = session.createCriteria(CartaBean.class).add(Restrictions.eq("elemento", "agua")).list();
		cartasFuego = session.createCriteria(CartaBean.class).add(Restrictions.eq("elemento", "fuego")).list();
		for (int i = 0; i < 5; i++) {
			CartasDisponiblesBean disponibles = new CartasDisponiblesBean();
			disponibles.setJugadorRef(jugador);
			disponibles.setCartaRef(cartasAgua.get(i));
			jugador.getCartasDisponiblesRef().add(disponibles);
		}
		for(int i=0;i<6;i++){
			CartasDisponiblesBean disponibles = new CartasDisponiblesBean();
			disponibles.setJugadorRef(jugador);
			disponibles.setCartaRef(cartasFuego.get(i));
			jugador.getCartasDisponiblesRef().add(disponibles);
		}
		if (lista1.isEmpty()) {
			session.save(jugador);
			session.getTransaction().commit();
			session.close();
			answer="true";

		} else {
			session.getTransaction().commit();
			session.close();
			answer="false";
		}
		CommandCreateUserAnswer commandAnswer = new CommandCreateUserAnswer(
				CommandCreateUserAnswer.CADENA_COMANDO
				+" "+answer);
		hilo.enviarMensaje(commandAnswer.convertirAString());
	}
	//--------------------------------------------------------------------------------------
	@SuppressWarnings("unchecked")
	public void loginVerification(String entrante) {
		String answer="";
		CommandLogin login = new CommandLogin(entrante);
		Session session = SessionHibernate.getInstance().getSession();

		session.beginTransaction();

		List<JugadorBean> user;
		user = new ArrayList<JugadorBean>();
		List<VerificarLogin> aux = new ArrayList<VerificarLogin>();
		user = session.createCriteria(JugadorBean.class)
				.add(Restrictions.eq("nombreUsr", login.getName()))
				.add(Restrictions.eq("clave", login.getPassword())).list();
		aux = session.createCriteria(VerificarLogin.class)
				.add(Restrictions.eq("nombreUsr", login.getName())).list();
		if(!user.isEmpty()){
			if(aux.isEmpty()){
				answer = "true";
				hilo.userName = login.getName();
				CommandLoginAnswer loginAnswer = new CommandLoginAnswer(CommandLoginAnswer.CADENA_COMANDO+" "+answer);
				hilo.enviarMensaje(loginAnswer.convertirAString());
				sendCards();
			}else{
				answer = "false";
			}	
		}else{
			answer = "false";
		}
	}
	//--------------------------------------------------------------------------------------
	@SuppressWarnings("unchecked")
	public void sendCards() {
		Session sesion = SessionHibernate.getInstance().getSession();
		sesion.beginTransaction();
		List<JugadorBean> jugadores = new ArrayList<JugadorBean>();
		List<CartaBean> cartas = new ArrayList<CartaBean>();

		jugadores = sesion.createCriteria(JugadorBean.class)
				.add(Restrictions.eq("nombreUsr", hilo.userName)).list();
		for (int i = 0; i < jugadores.get(0).getCartasDisponiblesRef().size(); i++) {
			cartas.add(i, jugadores.get(0).getCartasDisponiblesRef().get(i)
					.getCartaRef());

		}
		String userName = jugadores.get(0).getNombreUsr();
		int userId = jugadores.get(0).getId();
		sesion.beginTransaction().commit();
		sesion.close();
		CommandPlayCards cardsList = new CommandPlayCards();
		for(int i=0; i<cartas.size(); i++){
			CardTO cardAux = new CardTO();
			cardAux.setFnorth(cartas.get(i).getFuerzanorte());	
			cardAux.setFsouth(cartas.get(i).getFuerzasur());	
			cardAux.setFeast(cartas.get(i).getFuerzaeste());	
			cardAux.setFwest(cartas.get(i).getFuerzaoeste());
			cardAux.setElement(cartas.get(i).getElemento());
			cardAux.setId(cartas.get(i).getId());
			
			cardsList.getCardsList().add(cardAux);
		}
		cardsList.setNumberOfCards(cartas.size());
		CommandUserInformation userInformation = new CommandUserInformation();
		userInformation.setUserCards(cardsList);
		userInformation.setId(userId);
		userInformation.setUserName(userName);
		
		hilo.enviarMensaje(userInformation.convertirAString());
		
		
	}


}
