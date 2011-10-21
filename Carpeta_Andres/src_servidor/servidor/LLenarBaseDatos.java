package servidor;

import org.hibernate.Session;

public class LLenarBaseDatos {
	public LLenarBaseDatos() throws Exception {
		int costoCarta;
		
		Session sesion = SessionHibernate.getInstance().getSession();
		sesion.beginTransaction();
		
		//Jugadores:
		
			JugadorBean jesus = new JugadorBean();
			jesus.setNombreUsr("jesus");
			jesus.setCorreo("jesusdaniel@ula.ve");
			jesus.setClave("1234");
			jesus.setPuntos(99);
			jesus.setNombreAvatar("CardsJPG/Heroe.jpeg");
			jesus.setNivel(3);
			sesion.save(jesus);
		
			JugadorBean andres = new JugadorBean();
			andres.setNombreUsr("andres");
			andres.setCorreo("rafael@ula.ve");
			andres.setClave("1234");
			andres.setPuntos(33);
			andres.setNombreAvatar("CardsJPG/Heroe.jpeg");
			andres.setNivel(3);
			sesion.save(andres);
				
		//Cartas:
			
			//Agua:
			CartaBean carta1 = new CartaBean();
			carta1.setFuerzanorte(1);
			carta1.setFuerzasur(1);
			carta1.setFuerzaeste(4);
			carta1.setFuerzaoeste(5);
			carta1.setElemento("agua");
			costoCarta = establecerCosto(carta1.getFuerzanorte()+carta1.getFuerzasur()+carta1.getFuerzaeste()+carta1.getFuerzaoeste());
			carta1.setCosto(costoCarta);
			carta1.setSumafuerzas(carta1.getFuerzanorte()+carta1.getFuerzasur()+carta1.getFuerzaeste()+carta1.getFuerzaoeste());
			
			CartaBean carta2 = new CartaBean();
			carta2.setFuerzanorte(2);
			carta2.setFuerzasur(1);
			carta2.setFuerzaeste(3);
			carta2.setFuerzaoeste(5);
			carta2.setElemento("agua");
			costoCarta = establecerCosto(carta2.getFuerzanorte()+carta2.getFuerzasur()+carta2.getFuerzaeste()+carta2.getFuerzaoeste());
			carta2.setCosto(costoCarta);
			carta2.setSumafuerzas(carta2.getFuerzanorte()+carta2.getFuerzasur()+carta2.getFuerzaeste()+carta2.getFuerzaoeste());
			
			CartaBean carta3 = new CartaBean();
			carta3.setFuerzanorte(6);
			carta3.setFuerzasur(2);
			carta3.setFuerzaeste(2);
			carta3.setFuerzaoeste(3);
			carta3.setElemento("agua");
			costoCarta = establecerCosto(carta3.getFuerzanorte()+carta3.getFuerzasur()+carta3.getFuerzaeste()+carta3.getFuerzaoeste());
			carta3.setCosto(costoCarta);
			carta3.setSumafuerzas(carta3.getFuerzanorte()+carta3.getFuerzasur()+carta3.getFuerzaeste()+carta3.getFuerzaoeste());
	
			CartaBean carta4 = new CartaBean();
			carta4.setFuerzanorte(2);
			carta4.setFuerzasur(2);
			carta4.setFuerzaeste(1);
			carta4.setFuerzaoeste(6);
			carta4.setElemento("agua");
			costoCarta = establecerCosto(carta4.getFuerzanorte()+carta4.getFuerzasur()+carta4.getFuerzaeste()+carta4.getFuerzaoeste());
			carta4.setCosto(costoCarta);
			carta4.setSumafuerzas(carta4.getFuerzanorte()+carta4.getFuerzasur()+carta4.getFuerzaeste()+carta4.getFuerzaoeste());
			
			CartaBean carta5 = new CartaBean();
			carta5.setFuerzanorte(3);
			carta5.setFuerzasur(2);
			carta5.setFuerzaeste(5);
			carta5.setFuerzaoeste(1);
			carta5.setElemento("agua");
			costoCarta = establecerCosto(carta5.getFuerzanorte()+carta5.getFuerzasur()+carta5.getFuerzaeste()+carta5.getFuerzaoeste());
			carta5.setCosto(costoCarta);
			carta5.setSumafuerzas(carta5.getFuerzanorte()+carta5.getFuerzasur()+carta5.getFuerzaeste()+carta5.getFuerzaoeste());

			CartaBean carta6 = new CartaBean();
			carta6.setFuerzanorte(5);
			carta6.setFuerzasur(1);
			carta6.setFuerzaeste(1);
			carta6.setFuerzaoeste(3);
			carta6.setElemento("agua");
			costoCarta = establecerCosto(carta6.getFuerzanorte()+carta6.getFuerzasur()+carta6.getFuerzaeste()+carta6.getFuerzaoeste());
			carta6.setCosto(costoCarta);
			carta6.setSumafuerzas(carta6.getFuerzanorte()+carta6.getFuerzasur()+carta6.getFuerzaeste()+carta6.getFuerzaoeste());

			CartaBean carta7 = new CartaBean();
			carta7.setFuerzanorte(2);
			carta7.setFuerzasur(4);
			carta7.setFuerzaeste(1);
			carta7.setFuerzaoeste(4);
			carta7.setElemento("agua");
			costoCarta = establecerCosto(carta7.getFuerzanorte()+carta7.getFuerzasur()+carta7.getFuerzaeste()+carta7.getFuerzaoeste());
			carta7.setCosto(costoCarta);
			carta7.setSumafuerzas(carta7.getFuerzanorte()+carta7.getFuerzasur()+carta7.getFuerzaeste()+carta7.getFuerzaoeste());
			
			CartaBean carta8 = new CartaBean();
			carta8.setFuerzanorte(1);
			carta8.setFuerzasur(4);
			carta8.setFuerzaeste(5);
			carta8.setFuerzaoeste(1);
			carta8.setElemento("agua");
			costoCarta = establecerCosto(carta8.getFuerzanorte()+carta8.getFuerzasur()+carta8.getFuerzaeste()+carta8.getFuerzaoeste());
			carta8.setCosto(costoCarta);
			carta8.setSumafuerzas(carta8.getFuerzanorte()+carta8.getFuerzasur()+carta8.getFuerzaeste()+carta8.getFuerzaoeste());
			
			CartaBean carta9 = new CartaBean();
			carta9.setFuerzanorte(4);
			carta9.setFuerzasur(4);
			carta9.setFuerzaeste(2);
			carta9.setFuerzaoeste(3);
			carta9.setElemento("agua");
			costoCarta = establecerCosto(carta9.getFuerzanorte()+carta9.getFuerzasur()+carta9.getFuerzaeste()+carta9.getFuerzaoeste());
			carta9.setCosto(costoCarta);
			carta9.setSumafuerzas(carta9.getFuerzanorte()+carta9.getFuerzasur()+carta9.getFuerzaeste()+carta9.getFuerzaoeste());
			
			CartaBean carta10 = new CartaBean();
			carta10.setFuerzanorte(1);
			carta10.setFuerzasur(3);
			carta10.setFuerzaeste(3);
			carta10.setFuerzaoeste(5);
			carta10.setElemento("agua");
			costoCarta = establecerCosto(carta10.getFuerzanorte()+carta10.getFuerzasur()+carta10.getFuerzaeste()+carta10.getFuerzaoeste());
			carta10.setCosto(costoCarta);
			carta10.setSumafuerzas(carta10.getFuerzanorte()+carta10.getFuerzasur()+carta10.getFuerzaeste()+carta10.getFuerzaoeste());
			
			CartaBean carta11 = new CartaBean();
			carta11.setFuerzanorte(4);
			carta11.setFuerzasur(8);
			carta11.setFuerzaeste(2);
			carta11.setFuerzaoeste(6);
			carta11.setElemento("agua");
			costoCarta = establecerCosto(carta11.getFuerzanorte()+carta11.getFuerzasur()+carta11.getFuerzaeste()+carta11.getFuerzaoeste());
			carta11.setCosto(costoCarta);
			carta11.setSumafuerzas(carta11.getFuerzanorte()+carta11.getFuerzasur()+carta11.getFuerzaeste()+carta11.getFuerzaoeste());
			
			CartaBean carta12 = new CartaBean();
			carta12.setFuerzanorte(9);
			carta12.setFuerzasur(2);
			carta12.setFuerzaeste(5);
			carta12.setFuerzaoeste(1);
			carta12.setElemento("agua");
			costoCarta = establecerCosto(carta12.getFuerzanorte()+carta12.getFuerzasur()+carta12.getFuerzaeste()+carta12.getFuerzaoeste());
			carta12.setCosto(costoCarta);
			carta12.setSumafuerzas(carta12.getFuerzanorte()+carta12.getFuerzasur()+carta12.getFuerzaeste()+carta12.getFuerzaoeste());
			
			CartaBean carta13 = new CartaBean();
			carta13.setFuerzanorte(2);
			carta13.setFuerzasur(2);
			carta13.setFuerzaeste(6);
			carta13.setFuerzaoeste(4);
			carta13.setElemento("agua");
			costoCarta = establecerCosto(carta13.getFuerzanorte()+carta13.getFuerzasur()+carta13.getFuerzaeste()+carta13.getFuerzaoeste());
			carta13.setCosto(costoCarta);
			carta13.setSumafuerzas(carta13.getFuerzanorte()+carta13.getFuerzasur()+carta13.getFuerzaeste()+carta13.getFuerzaoeste());
			
			CartaBean carta14 = new CartaBean();
			carta14.setFuerzanorte(2);
			carta14.setFuerzasur(5);
			carta14.setFuerzaeste(1);
			carta14.setFuerzaoeste(6);
			carta14.setElemento("agua");
			costoCarta = establecerCosto(carta14.getFuerzanorte()+carta14.getFuerzasur()+carta14.getFuerzaeste()+carta14.getFuerzaoeste());
			carta14.setCosto(costoCarta);
			carta14.setSumafuerzas(carta14.getFuerzanorte()+carta14.getFuerzasur()+carta14.getFuerzaeste()+carta14.getFuerzaoeste());
			
			CartaBean carta15 = new CartaBean();
			carta15.setFuerzanorte(7);
			carta15.setFuerzasur(1);
			carta15.setFuerzaeste(1);
			carta15.setFuerzaoeste(3);
			carta15.setElemento("agua");
			costoCarta = establecerCosto(carta15.getFuerzanorte()+carta15.getFuerzasur()+carta15.getFuerzaeste()+carta15.getFuerzaoeste());
			carta15.setCosto(costoCarta);
			carta15.setSumafuerzas(carta15.getFuerzanorte()+carta15.getFuerzasur()+carta15.getFuerzaeste()+carta15.getFuerzaoeste());
			
			//tierra:
			CartaBean carta16 = new CartaBean();
			carta16.setFuerzanorte(7);
			carta16.setFuerzasur(3);
			carta16.setFuerzaeste(9);
			carta16.setFuerzaoeste(1);
			carta16.setElemento("tierra");
			costoCarta = establecerCosto(carta16.getFuerzanorte()+carta16.getFuerzasur()+carta16.getFuerzaeste()+carta16.getFuerzaoeste());
			carta16.setCosto(costoCarta);
			carta16.setSumafuerzas(carta16.getFuerzanorte()+carta16.getFuerzasur()+carta16.getFuerzaeste()+carta16.getFuerzaoeste());
			
			CartaBean carta17 = new CartaBean();
			carta17.setFuerzanorte(4);
			carta17.setFuerzasur(3);
			carta17.setFuerzaeste(6);
			carta17.setFuerzaoeste(3);
			carta17.setElemento("tierra");
			costoCarta = establecerCosto(carta17.getFuerzanorte()+carta17.getFuerzasur()+carta17.getFuerzaeste()+carta17.getFuerzaoeste());
			carta17.setCosto(costoCarta);
			carta17.setSumafuerzas(carta17.getFuerzanorte()+carta17.getFuerzasur()+carta17.getFuerzaeste()+carta17.getFuerzaoeste());
			
			CartaBean carta18 = new CartaBean();
			CartasDisponiblesBean disponibles = new CartasDisponiblesBean();
			carta18.setFuerzanorte(6);
			carta18.setFuerzasur(4);
			carta18.setFuerzaeste(1);
			carta18.setFuerzaoeste(1);
			carta18.setElemento("tierra");
			costoCarta = establecerCosto(carta18.getFuerzanorte()+carta18.getFuerzasur()+carta18.getFuerzaeste()+carta18.getFuerzaoeste());
			carta18.setCosto(costoCarta);
			carta18.setSumafuerzas(carta18.getFuerzanorte()+carta18.getFuerzasur()+carta18.getFuerzaeste()+carta18.getFuerzaoeste());
			disponibles.setJugadorRef(jesus);
			disponibles.setCartaRef(carta18);
			
			CartaBean carta19 = new CartaBean();
			carta19.setFuerzanorte(5);
			carta19.setFuerzasur(5);
			carta19.setFuerzaeste(2);
			carta19.setFuerzaoeste(2);
			carta19.setElemento("tierra");
			costoCarta = establecerCosto(carta19.getFuerzanorte()+carta19.getFuerzasur()+carta19.getFuerzaeste()+carta19.getFuerzaoeste());
			carta19.setCosto(costoCarta);
			carta19.setSumafuerzas(carta19.getFuerzanorte()+carta19.getFuerzasur()+carta19.getFuerzaeste()+carta19.getFuerzaoeste());
			
			CartaBean carta20 = new CartaBean();
			carta20.setFuerzanorte(6);
			carta20.setFuerzasur(1);
			carta20.setFuerzaeste(9);
			carta20.setFuerzaoeste(4);
			carta20.setElemento("tierra");
			costoCarta = establecerCosto(carta20.getFuerzanorte()+carta20.getFuerzasur()+carta20.getFuerzaeste()+carta20.getFuerzaoeste());
			carta20.setCosto(costoCarta);
			carta20.setSumafuerzas(carta20.getFuerzanorte()+carta20.getFuerzasur()+carta20.getFuerzaeste()+carta20.getFuerzaoeste());
			
			CartaBean carta21 = new CartaBean();
			carta21.setFuerzanorte(2);
			carta21.setFuerzasur(8);
			carta21.setFuerzaeste(4);
			carta21.setFuerzaoeste(7);
			carta21.setElemento("tierra");
			costoCarta = establecerCosto(carta21.getFuerzanorte()+carta21.getFuerzasur()+carta21.getFuerzaeste()+carta21.getFuerzaoeste());
			carta21.setCosto(costoCarta);
			carta21.setSumafuerzas(carta21.getFuerzanorte()+carta21.getFuerzasur()+carta21.getFuerzaeste()+carta21.getFuerzaoeste());
			
			CartaBean carta22 = new CartaBean();
			carta22.setFuerzanorte(7);
			carta22.setFuerzasur(3);
			carta22.setFuerzaeste(9);
			carta22.setFuerzaoeste(1);
			carta22.setElemento("tierra");
			costoCarta = establecerCosto(carta22.getFuerzanorte()+carta22.getFuerzasur()+carta22.getFuerzaeste()+carta22.getFuerzaoeste());
			carta22.setCosto(costoCarta);
			carta22.setSumafuerzas(carta22.getFuerzanorte()+carta22.getFuerzasur()+carta22.getFuerzaeste()+carta22.getFuerzaoeste());
			
			CartaBean carta23 = new CartaBean();
			carta23.setFuerzanorte(8);
			carta23.setFuerzasur(8);
			carta23.setFuerzaeste(1);
			carta23.setFuerzaoeste(4);
			carta23.setElemento("tierra");
			costoCarta = establecerCosto(carta23.getFuerzanorte()+carta23.getFuerzasur()+carta23.getFuerzaeste()+carta23.getFuerzaoeste());
			carta23.setCosto(costoCarta);
			carta23.setSumafuerzas(carta23.getFuerzanorte()+carta23.getFuerzasur()+carta23.getFuerzaeste()+carta23.getFuerzaoeste());
			
			CartaBean carta24 = new CartaBean();
			carta24.setFuerzanorte(7);
			carta24.setFuerzasur(3);
			carta24.setFuerzaeste(9);
			carta24.setFuerzaoeste(1);
			carta24.setElemento("tierra");
			costoCarta = establecerCosto(carta24.getFuerzanorte()+carta24.getFuerzasur()+carta24.getFuerzaeste()+carta24.getFuerzaoeste());
			carta24.setCosto(costoCarta);
			carta24.setSumafuerzas(carta24.getFuerzanorte()+carta24.getFuerzasur()+carta24.getFuerzaeste()+carta24.getFuerzaoeste());
			
			CartaBean carta25 = new CartaBean();
			carta25.setFuerzanorte(3);
			carta25.setFuerzasur(3);
			carta25.setFuerzaeste(6);
			carta25.setFuerzaoeste(9);
			carta25.setElemento("tierra");
			costoCarta = establecerCosto(carta25.getFuerzanorte()+carta25.getFuerzasur()+carta25.getFuerzaeste()+carta25.getFuerzaoeste());
			carta25.setCosto(costoCarta);
			carta25.setSumafuerzas(carta25.getFuerzanorte()+carta25.getFuerzasur()+carta25.getFuerzaeste()+carta25.getFuerzaoeste());

			CartaBean carta26 = new CartaBean();
			carta26.setFuerzanorte(4);
			carta26.setFuerzasur(5);
			carta26.setFuerzaeste(4);
			carta26.setFuerzaoeste(5);
			carta26.setElemento("tierra");
			costoCarta = establecerCosto(carta26.getFuerzanorte()+carta26.getFuerzasur()+carta26.getFuerzaeste()+carta26.getFuerzaoeste());
			carta26.setCosto(costoCarta);
			carta26.setSumafuerzas(carta26.getFuerzanorte()+carta26.getFuerzasur()+carta26.getFuerzaeste()+carta26.getFuerzaoeste());
			
			CartaBean carta27 = new CartaBean();
			carta27.setFuerzanorte(2);
			carta27.setFuerzasur(3);
			carta27.setFuerzaeste(6);
			carta27.setFuerzaoeste(6);
			carta27.setElemento("tierra");
			costoCarta = establecerCosto(carta27.getFuerzanorte()+carta27.getFuerzasur()+carta27.getFuerzaeste()+carta27.getFuerzaoeste());
			carta27.setCosto(costoCarta);
			carta27.setSumafuerzas(carta27.getFuerzanorte()+carta27.getFuerzasur()+carta27.getFuerzaeste()+carta27.getFuerzaoeste());
			
			CartaBean carta28 = new CartaBean();
			carta28.setFuerzanorte(5);
			carta28.setFuerzasur(7);
			carta28.setFuerzaeste(7);
			carta28.setFuerzaoeste(3);
			carta28.setElemento("tierra");
			costoCarta = establecerCosto(carta28.getFuerzanorte()+carta28.getFuerzasur()+carta28.getFuerzaeste()+carta28.getFuerzaoeste());
			carta28.setCosto(costoCarta);
			carta28.setSumafuerzas(carta28.getFuerzanorte()+carta28.getFuerzasur()+carta28.getFuerzaeste()+carta28.getFuerzaoeste());
			
			CartaBean carta29 = new CartaBean();
			carta29.setFuerzanorte(2);
			carta29.setFuerzasur(2);
			carta29.setFuerzaeste(1);
			carta29.setFuerzaoeste(5);
			carta29.setElemento("tierra");
			costoCarta = establecerCosto(carta29.getFuerzanorte()+carta29.getFuerzasur()+carta29.getFuerzaeste()+carta29.getFuerzaoeste());
			carta29.setCosto(costoCarta);
			carta29.setSumafuerzas(carta29.getFuerzanorte()+carta29.getFuerzasur()+carta29.getFuerzaeste()+carta29.getFuerzaoeste());
			
			CartaBean carta30 = new CartaBean();
			carta30.setFuerzanorte(3);
			carta30.setFuerzasur(4);
			carta30.setFuerzaeste(4);
			carta30.setFuerzaoeste(7);
			carta30.setElemento("tierra");
			costoCarta = establecerCosto(carta30.getFuerzanorte()+carta30.getFuerzasur()+carta30.getFuerzaeste()+carta30.getFuerzaoeste());
			carta30.setCosto(costoCarta);
			carta30.setSumafuerzas(carta30.getFuerzanorte()+carta30.getFuerzasur()+carta30.getFuerzaeste()+carta30.getFuerzaoeste());
			
			//fuego:
			CartaBean carta31 = new CartaBean();
			carta31.setFuerzanorte(1);
			carta31.setFuerzasur(6);
			carta31.setFuerzaeste(6);
			carta31.setFuerzaoeste(6);
			carta31.setElemento("fuego");
			costoCarta = establecerCosto(carta31.getFuerzanorte()+carta31.getFuerzasur()+carta31.getFuerzaeste()+carta31.getFuerzaoeste());
			carta31.setCosto(costoCarta);
			carta31.setSumafuerzas(carta31.getFuerzanorte()+carta31.getFuerzasur()+carta31.getFuerzaeste()+carta31.getFuerzaoeste());
			
			CartaBean carta32 = new CartaBean();
			carta32.setFuerzanorte(5);
			carta32.setFuerzasur(1);
			carta32.setFuerzaeste(2);
			carta32.setFuerzaoeste(1);
			carta32.setElemento("fuego");
			costoCarta = establecerCosto(carta32.getFuerzanorte()+carta32.getFuerzasur()+carta32.getFuerzaeste()+carta32.getFuerzaoeste());
			carta32.setCosto(costoCarta);
			carta32.setSumafuerzas(carta32.getFuerzanorte()+carta32.getFuerzasur()+carta32.getFuerzaeste()+carta32.getFuerzaoeste());
			
			CartaBean carta33 = new CartaBean();
			carta33.setFuerzanorte(7);
			carta33.setFuerzasur(3);
			carta33.setFuerzaeste(3);
			carta33.setFuerzaoeste(2);
			carta33.setElemento("fuego");
			costoCarta = establecerCosto(carta33.getFuerzanorte()+carta33.getFuerzasur()+carta33.getFuerzaeste()+carta33.getFuerzaoeste());
			carta33.setCosto(costoCarta);
			carta33.setSumafuerzas(carta33.getFuerzanorte()+carta33.getFuerzasur()+carta33.getFuerzaeste()+carta33.getFuerzaoeste());
			
			CartaBean carta34 = new CartaBean();
			carta34.setFuerzanorte(2);
			carta34.setFuerzasur(1);
			carta34.setFuerzaeste(1);
			carta34.setFuerzaoeste(3);
			carta34.setElemento("fuego");
			costoCarta = establecerCosto(carta34.getFuerzanorte()+carta34.getFuerzasur()+carta34.getFuerzaeste()+carta34.getFuerzaoeste());
			carta34.setCosto(costoCarta);
			carta34.setSumafuerzas(carta34.getFuerzanorte()+carta34.getFuerzasur()+carta34.getFuerzaeste()+carta34.getFuerzaoeste());
			
			CartaBean carta35 = new CartaBean();
			carta35.setFuerzanorte(9);
			carta35.setFuerzasur(1);
			carta35.setFuerzaeste(2);
			carta35.setFuerzaoeste(2);
			carta35.setElemento("fuego");
			costoCarta = establecerCosto(carta35.getFuerzanorte()+carta35.getFuerzasur()+carta35.getFuerzaeste()+carta35.getFuerzaoeste());
			carta35.setCosto(costoCarta);
			carta35.setSumafuerzas(carta35.getFuerzanorte()+carta35.getFuerzasur()+carta35.getFuerzaeste()+carta35.getFuerzaoeste());
			
			CartaBean carta36 = new CartaBean();
			carta36.setFuerzanorte(1);
			carta36.setFuerzasur(2);
			carta36.setFuerzaeste(3);
			carta36.setFuerzaoeste(6);
			carta36.setElemento("fuego");
			costoCarta = establecerCosto(carta36.getFuerzanorte()+carta36.getFuerzasur()+carta36.getFuerzaeste()+carta36.getFuerzaoeste());
			carta36.setCosto(costoCarta);
			carta36.setSumafuerzas(carta36.getFuerzanorte()+carta36.getFuerzasur()+carta36.getFuerzaeste()+carta36.getFuerzaoeste());
			
			CartaBean carta37 = new CartaBean();
			carta37.setFuerzanorte(2);
			carta37.setFuerzasur(9);
			carta37.setFuerzaeste(1);
			carta37.setFuerzaoeste(7);
			carta37.setElemento("fuego");
			costoCarta = establecerCosto(carta37.getFuerzanorte()+carta37.getFuerzasur()+carta37.getFuerzaeste()+carta37.getFuerzaoeste());
			carta37.setCosto(costoCarta);
			carta37.setSumafuerzas(carta37.getFuerzanorte()+carta37.getFuerzasur()+carta37.getFuerzaeste()+carta37.getFuerzaoeste());
			
			CartaBean carta38 = new CartaBean();
			carta38.setFuerzanorte(7);
			carta38.setFuerzasur(8);
			carta38.setFuerzaeste(5);
			carta38.setFuerzaoeste(3);
			carta38.setElemento("fuego");
			costoCarta = establecerCosto(carta38.getFuerzanorte()+carta38.getFuerzasur()+carta38.getFuerzaeste()+carta38.getFuerzaoeste());
			carta38.setCosto(costoCarta);
			carta38.setSumafuerzas(carta38.getFuerzanorte()+carta38.getFuerzasur()+carta38.getFuerzaeste()+carta38.getFuerzaoeste());
			
			CartaBean carta39 = new CartaBean();
			carta39.setFuerzanorte(3);
			carta39.setFuerzasur(5);
			carta39.setFuerzaeste(7);
			carta39.setFuerzaoeste(8);
			carta39.setElemento("fuego");
			costoCarta = establecerCosto(carta39.getFuerzanorte()+carta39.getFuerzasur()+carta39.getFuerzaeste()+carta39.getFuerzaoeste());
			carta39.setCosto(costoCarta);
			carta39.setSumafuerzas(carta39.getFuerzanorte()+carta39.getFuerzasur()+carta39.getFuerzaeste()+carta39.getFuerzaoeste());
			
			CartaBean carta40 = new CartaBean();
			carta40.setFuerzanorte(8);
			carta40.setFuerzasur(6);
			carta40.setFuerzaeste(8);
			carta40.setFuerzaoeste(1);
			carta40.setElemento("fuego");
			costoCarta = establecerCosto(carta40.getFuerzanorte()+carta40.getFuerzasur()+carta40.getFuerzaeste()+carta40.getFuerzaoeste());
			carta40.setCosto(costoCarta);
			carta40.setSumafuerzas(carta40.getFuerzanorte()+carta40.getFuerzasur()+carta40.getFuerzaeste()+carta40.getFuerzaoeste());
			
			CartaBean carta41 = new CartaBean();
			carta41.setFuerzanorte(1);
			carta41.setFuerzasur(5);
			carta41.setFuerzaeste(1);
			carta41.setFuerzaoeste(9);
			carta41.setElemento("fuego");
			costoCarta = establecerCosto(carta41.getFuerzanorte()+carta41.getFuerzasur()+carta41.getFuerzaeste()+carta41.getFuerzaoeste());
			carta41.setCosto(costoCarta);
			carta41.setSumafuerzas(carta41.getFuerzanorte()+carta41.getFuerzasur()+carta41.getFuerzaeste()+carta41.getFuerzaoeste());
			
			CartaBean carta42 = new CartaBean();
			carta42.setFuerzanorte(5);
			carta42.setFuerzasur(5);
			carta42.setFuerzaeste(3);
			carta42.setFuerzaoeste(3);
			carta42.setElemento("fuego");
			costoCarta = establecerCosto(carta42.getFuerzanorte()+carta42.getFuerzasur()+carta42.getFuerzaeste()+carta42.getFuerzaoeste());
			carta42.setCosto(costoCarta);
			carta42.setSumafuerzas(carta42.getFuerzanorte()+carta42.getFuerzasur()+carta42.getFuerzaeste()+carta42.getFuerzaoeste());
			
			CartaBean carta43 = new CartaBean();
			carta43.setFuerzanorte(2);
			carta43.setFuerzasur(2);
			carta43.setFuerzaeste(6);
			carta43.setFuerzaoeste(6);
			carta43.setElemento("fuego");
			costoCarta = establecerCosto(carta43.getFuerzanorte()+carta43.getFuerzasur()+carta43.getFuerzaeste()+carta43.getFuerzaoeste());
			carta43.setCosto(costoCarta);
			carta43.setSumafuerzas(carta43.getFuerzanorte()+carta43.getFuerzasur()+carta43.getFuerzaeste()+carta43.getFuerzaoeste());
			
			CartaBean carta44 = new CartaBean();
			carta44.setFuerzanorte(6);
			carta44.setFuerzasur(3);
			carta44.setFuerzaeste(7);
			carta44.setFuerzaoeste(1);
			carta44.setElemento("fuego");
			costoCarta = establecerCosto(carta44.getFuerzanorte()+carta44.getFuerzasur()+carta44.getFuerzaeste()+carta44.getFuerzaoeste());
			carta44.setCosto(costoCarta);
			carta44.setSumafuerzas(carta44.getFuerzanorte()+carta44.getFuerzasur()+carta44.getFuerzaeste()+carta44.getFuerzaoeste());
			
			CartaBean carta45 = new CartaBean();
			carta45.setFuerzanorte(5);
			carta45.setFuerzasur(5);
			carta45.setFuerzaeste(6);
			carta45.setFuerzaoeste(9);
			carta45.setElemento("fuego");
			costoCarta = establecerCosto(carta45.getFuerzanorte()+carta45.getFuerzasur()+carta45.getFuerzaeste()+carta45.getFuerzaoeste());
			carta45.setCosto(costoCarta);
			carta45.setSumafuerzas(carta45.getFuerzanorte()+carta45.getFuerzasur()+carta45.getFuerzaeste()+carta45.getFuerzaoeste());
			
			//aire
			CartaBean carta46 = new CartaBean();
			carta46.setFuerzanorte(1);
			carta46.setFuerzasur(8);
			carta46.setFuerzaeste(8);
			carta46.setFuerzaoeste(8);
			carta46.setElemento("aire");
			costoCarta = establecerCosto(carta46.getFuerzanorte()+carta46.getFuerzasur()+carta46.getFuerzaeste()+carta46.getFuerzaoeste());
			carta46.setCosto(costoCarta);
			carta46.setSumafuerzas(carta46.getFuerzanorte()+carta46.getFuerzasur()+carta46.getFuerzaeste()+carta46.getFuerzaoeste());
			
			CartaBean carta47 = new CartaBean();
			carta47.setFuerzanorte(6);
			carta47.setFuerzasur(8);
			carta47.setFuerzaeste(6);
			carta47.setFuerzaoeste(8);
			carta47.setElemento("aire");
			costoCarta = establecerCosto(carta47.getFuerzanorte()+carta47.getFuerzasur()+carta47.getFuerzaeste()+carta47.getFuerzaoeste());
			carta47.setCosto(costoCarta);
			carta47.setSumafuerzas(carta47.getFuerzanorte()+carta47.getFuerzasur()+carta47.getFuerzaeste()+carta47.getFuerzaoeste());
			
			CartaBean carta48 = new CartaBean();
			carta48.setFuerzanorte(4);
			carta48.setFuerzasur(1);
			carta48.setFuerzaeste(4);
			carta48.setFuerzaoeste(1);
			carta48.setElemento("aire");
			costoCarta = establecerCosto(carta48.getFuerzanorte()+carta48.getFuerzasur()+carta48.getFuerzaeste()+carta48.getFuerzaoeste());
			carta48.setCosto(costoCarta);
			carta48.setSumafuerzas(carta48.getFuerzanorte()+carta48.getFuerzasur()+carta48.getFuerzaeste()+carta48.getFuerzaoeste());
			
			CartaBean carta49 = new CartaBean();
			carta49.setFuerzanorte(2);
			carta49.setFuerzasur(1);
			carta49.setFuerzaeste(3);
			carta49.setFuerzaoeste(2);
			carta49.setElemento("aire");
			costoCarta = establecerCosto(carta49.getFuerzanorte()+carta49.getFuerzasur()+carta49.getFuerzaeste()+carta49.getFuerzaoeste());
			carta49.setCosto(costoCarta);
			carta49.setSumafuerzas(carta49.getFuerzanorte()+carta49.getFuerzasur()+carta49.getFuerzaeste()+carta49.getFuerzaoeste());
			
			CartaBean carta50 = new CartaBean();
			carta50.setFuerzanorte(5);
			carta50.setFuerzasur(3);
			carta50.setFuerzaeste(4);
			carta50.setFuerzaoeste(9);
			carta50.setElemento("aire");
			costoCarta = establecerCosto(carta50.getFuerzanorte()+carta50.getFuerzasur()+carta50.getFuerzaeste()+carta50.getFuerzaoeste());
			carta50.setCosto(costoCarta);
			carta50.setSumafuerzas(carta50.getFuerzanorte()+carta50.getFuerzasur()+carta50.getFuerzaeste()+carta50.getFuerzaoeste());
			
			CartaBean carta51 = new CartaBean();
			carta51.setFuerzanorte(2);
			carta51.setFuerzasur(5);
			carta51.setFuerzaeste(6);
			carta51.setFuerzaoeste(1);
			carta51.setElemento("aire");
			costoCarta = establecerCosto(carta51.getFuerzanorte()+carta51.getFuerzasur()+carta51.getFuerzaeste()+carta51.getFuerzaoeste());
			carta51.setCosto(costoCarta);
			carta51.setSumafuerzas(carta51.getFuerzanorte()+carta51.getFuerzasur()+carta51.getFuerzaeste()+carta51.getFuerzaoeste());
			
			CartaBean carta52 = new CartaBean();
			carta52.setFuerzanorte(5);
			carta52.setFuerzasur(3);
			carta52.setFuerzaeste(4);
			carta52.setFuerzaoeste(6);
			carta52.setElemento("aire");
			costoCarta = establecerCosto(carta52.getFuerzanorte()+carta52.getFuerzasur()+carta52.getFuerzaeste()+carta52.getFuerzaoeste());
			carta52.setCosto(costoCarta);
			carta52.setSumafuerzas(carta52.getFuerzanorte()+carta52.getFuerzasur()+carta52.getFuerzaeste()+carta52.getFuerzaoeste());
			
			CartaBean carta53 = new CartaBean();
			carta53.setFuerzanorte(6);
			carta53.setFuerzasur(1);
			carta53.setFuerzaeste(4);
			carta53.setFuerzaoeste(2);
			carta53.setElemento("aire");
			costoCarta = establecerCosto(carta53.getFuerzanorte()+carta53.getFuerzasur()+carta53.getFuerzaeste()+carta53.getFuerzaoeste());
			carta53.setCosto(costoCarta);
			carta53.setSumafuerzas(carta53.getFuerzanorte()+carta53.getFuerzasur()+carta53.getFuerzaeste()+carta53.getFuerzaoeste());
			
			CartaBean carta54 = new CartaBean();
			carta54.setFuerzanorte(3);
			carta54.setFuerzasur(3);
			carta54.setFuerzaeste(5);
			carta54.setFuerzaoeste(6);
			carta54.setElemento("aire");
			costoCarta = establecerCosto(carta54.getFuerzanorte()+carta54.getFuerzasur()+carta54.getFuerzaeste()+carta54.getFuerzaoeste());
			carta54.setCosto(costoCarta);
			carta54.setSumafuerzas(carta54.getFuerzanorte()+carta54.getFuerzasur()+carta54.getFuerzaeste()+carta54.getFuerzaoeste());
			
			CartaBean carta55 = new CartaBean();
			carta55.setFuerzanorte(7);
			carta55.setFuerzasur(7);
			carta55.setFuerzaeste(2);
			carta55.setFuerzaoeste(5);
			carta55.setElemento("aire");
			costoCarta = establecerCosto(carta55.getFuerzanorte()+carta55.getFuerzasur()+carta55.getFuerzaeste()+carta55.getFuerzaoeste());
			carta55.setCosto(costoCarta);
			carta55.setSumafuerzas(carta55.getFuerzanorte()+carta55.getFuerzasur()+carta55.getFuerzaeste()+carta55.getFuerzaoeste());
			
			CartaBean carta56 = new CartaBean();
			carta56.setFuerzanorte(3);
			carta56.setFuerzasur(3);
			carta56.setFuerzaeste(3);
			carta56.setFuerzaoeste(6);
			carta56.setElemento("aire");
			costoCarta = establecerCosto(carta56.getFuerzanorte()+carta56.getFuerzasur()+carta56.getFuerzaeste()+carta56.getFuerzaoeste());
			carta56.setCosto(costoCarta);
			carta56.setSumafuerzas(carta56.getFuerzanorte()+carta56.getFuerzasur()+carta56.getFuerzaeste()+carta56.getFuerzaoeste());
			
			CartaBean carta57 = new CartaBean();
			carta57.setFuerzanorte(1);
			carta57.setFuerzasur(8);
			carta57.setFuerzaeste(8);
			carta57.setFuerzaoeste(8);
			carta57.setElemento("aire");
			costoCarta = establecerCosto(carta57.getFuerzanorte()+carta57.getFuerzasur()+carta57.getFuerzaeste()+carta57.getFuerzaoeste());
			carta57.setCosto(costoCarta);
			carta57.setSumafuerzas(carta57.getFuerzanorte()+carta57.getFuerzasur()+carta57.getFuerzaeste()+carta57.getFuerzaoeste());
			
			CartaBean carta58 = new CartaBean();
			carta58.setFuerzanorte(2);
			carta58.setFuerzasur(3);
			carta58.setFuerzaeste(9);
			carta58.setFuerzaoeste(1);
			carta58.setElemento("aire");
			costoCarta = establecerCosto(carta58.getFuerzanorte()+carta58.getFuerzasur()+carta58.getFuerzaeste()+carta58.getFuerzaoeste());
			carta58.setCosto(costoCarta);
			carta58.setSumafuerzas(carta58.getFuerzanorte()+carta58.getFuerzasur()+carta58.getFuerzaeste()+carta58.getFuerzaoeste());
			
			CartaBean carta59 = new CartaBean();
			carta59.setFuerzanorte(1);
			carta59.setFuerzasur(9);
			carta59.setFuerzaeste(1);
			carta59.setFuerzaoeste(9);
			carta59.setElemento("aire");
			costoCarta = establecerCosto(carta59.getFuerzanorte()+carta59.getFuerzasur()+carta59.getFuerzaeste()+carta59.getFuerzaoeste());
			carta59.setCosto(costoCarta);
			carta59.setSumafuerzas(carta59.getFuerzanorte()+carta59.getFuerzasur()+carta59.getFuerzaeste()+carta59.getFuerzaoeste());
			
			CartaBean carta60 = new CartaBean();
			carta60.setFuerzanorte(3);
			carta60.setFuerzasur(8);
			carta60.setFuerzaeste(7);
			carta60.setFuerzaoeste(3);
			carta60.setElemento("aire");
			costoCarta = establecerCosto(carta60.getFuerzanorte()+carta60.getFuerzasur()+carta60.getFuerzaeste()+carta60.getFuerzaoeste());
			carta60.setCosto(costoCarta);
			carta60.setSumafuerzas(carta60.getFuerzanorte()+carta60.getFuerzasur()+carta60.getFuerzaeste()+carta60.getFuerzaoeste());
			
			//Cartas Dispobiles:
			
			CartasDisponiblesBean disponiblesJ1 = new CartasDisponiblesBean();
			CartasDisponiblesBean disponiblesJ2 = new CartasDisponiblesBean();
			CartasDisponiblesBean disponiblesJ3 = new CartasDisponiblesBean();
			CartasDisponiblesBean disponiblesJ4 = new CartasDisponiblesBean();
			CartasDisponiblesBean disponiblesJ5 = new CartasDisponiblesBean();
			CartasDisponiblesBean disponiblesJ6 = new CartasDisponiblesBean();
			CartasDisponiblesBean disponiblesJ7 = new CartasDisponiblesBean();
			CartasDisponiblesBean disponiblesJ8 = new CartasDisponiblesBean();
			
			CartasDisponiblesBean disponiblesA1 = new CartasDisponiblesBean();
			CartasDisponiblesBean disponiblesA2 = new CartasDisponiblesBean();
			CartasDisponiblesBean disponiblesA3 = new CartasDisponiblesBean();
			CartasDisponiblesBean disponiblesA4 = new CartasDisponiblesBean();
			CartasDisponiblesBean disponiblesA5 = new CartasDisponiblesBean();
			CartasDisponiblesBean disponiblesA6 = new CartasDisponiblesBean();
			CartasDisponiblesBean disponiblesA7 = new CartasDisponiblesBean();
			CartasDisponiblesBean disponiblesA8 = new CartasDisponiblesBean();
			CartasDisponiblesBean disponiblesA9 = new CartasDisponiblesBean();
			CartasDisponiblesBean disponiblesA10 = new CartasDisponiblesBean();
			
			disponiblesJ1.setJugadorRef(jesus);
			disponiblesA1.setJugadorRef(andres);
			disponiblesJ1.setCartaRef(carta1);
			disponiblesA1.setCartaRef(carta1);
			jesus.getCartasDisponiblesRef().add(disponiblesJ1);
			andres.getCartasDisponiblesRef().add(disponiblesA1);
			
			disponiblesJ2.setJugadorRef(jesus);
			disponiblesA2.setJugadorRef(andres);
			disponiblesJ2.setCartaRef(carta2);
			disponiblesA2.setCartaRef(carta2);
			jesus.getCartasDisponiblesRef().add(disponiblesJ2);
			andres.getCartasDisponiblesRef().add(disponiblesA2);

			disponiblesJ3.setJugadorRef(jesus);
			disponiblesA3.setJugadorRef(andres);
			disponiblesJ3.setCartaRef(carta3);
			disponiblesA3.setCartaRef(carta3);
			jesus.getCartasDisponiblesRef().add(disponiblesJ3);
			andres.getCartasDisponiblesRef().add(disponiblesA3);

			disponiblesJ4.setJugadorRef(jesus);
			disponiblesA4.setJugadorRef(andres);
			disponiblesJ4.setCartaRef(carta4);
			disponiblesA4.setCartaRef(carta4);
			jesus.getCartasDisponiblesRef().add(disponiblesJ4);
			andres.getCartasDisponiblesRef().add(disponiblesA4);
			
			disponiblesJ5.setJugadorRef(jesus);
			disponiblesA5.setJugadorRef(andres);
			disponiblesJ5.setCartaRef(carta5);
			disponiblesA5.setCartaRef(carta5);
			jesus.getCartasDisponiblesRef().add(disponiblesJ5);
			andres.getCartasDisponiblesRef().add(disponiblesA5);
			
			disponiblesJ6.setJugadorRef(jesus);
			disponiblesA6.setJugadorRef(andres);
			disponiblesJ6.setCartaRef(carta6);
			disponiblesA6.setCartaRef(carta6);
			jesus.getCartasDisponiblesRef().add(disponiblesJ6);
			andres.getCartasDisponiblesRef().add(disponiblesA6);

			disponiblesJ7.setJugadorRef(jesus);
			disponiblesA7.setJugadorRef(andres);
			disponiblesJ7.setCartaRef(carta7);
			disponiblesA7.setCartaRef(carta7);
			jesus.getCartasDisponiblesRef().add(disponiblesJ7);
			andres.getCartasDisponiblesRef().add(disponiblesA7);

			disponiblesJ8.setJugadorRef(jesus);
			disponiblesA8.setJugadorRef(andres);
			disponiblesJ8.setCartaRef(carta8);
			disponiblesA8.setCartaRef(carta8);
			jesus.getCartasDisponiblesRef().add(disponiblesJ8);
			andres.getCartasDisponiblesRef().add(disponiblesA8);

			disponiblesA9.setJugadorRef(andres);
			disponiblesA9.setCartaRef(carta9);
			andres.getCartasDisponiblesRef().add(disponiblesA9);
			
			disponiblesA10.setJugadorRef(andres);
			disponiblesA10.setCartaRef(carta10);
			andres.getCartasDisponiblesRef().add(disponiblesA10);

			//***********************************************************************************

			sesion.save(carta1);
			sesion.save(carta2);
			sesion.save(carta3);
			sesion.save(carta4);
			sesion.save(carta5);
			sesion.save(carta6);
			sesion.save(carta7);
			sesion.save(carta8);
			sesion.save(carta9);
			sesion.save(carta10);
			sesion.save(carta11);
			sesion.save(carta12);
			sesion.save(carta13);
			sesion.save(carta14);
			sesion.save(carta15);
			sesion.save(carta16);
			sesion.save(carta17);
			sesion.save(carta18);
			sesion.save(carta19);
			sesion.save(carta20);
			sesion.save(carta21);
			sesion.save(carta22);
			sesion.save(carta23);
			sesion.save(carta24);
			sesion.save(carta25);
			sesion.save(carta26);
			sesion.save(carta27);
			sesion.save(carta28);
			sesion.save(carta29);
			sesion.save(carta30);
			sesion.save(carta31);
			sesion.save(carta32);
			sesion.save(carta33);
			sesion.save(carta34);
			sesion.save(carta35);
			sesion.save(carta36);
			sesion.save(carta37);
			sesion.save(carta38);
			sesion.save(carta39);
			sesion.save(carta40);
			sesion.save(carta41);
			sesion.save(carta42);
			sesion.save(carta43);
			sesion.save(carta44);
			sesion.save(carta45);
			sesion.save(carta46);
			sesion.save(carta47);
			sesion.save(carta48);
			sesion.save(carta49);
			sesion.save(carta50);
			sesion.save(carta51);
			sesion.save(carta52);
			sesion.save(carta53);
			sesion.save(carta54);
			sesion.save(carta55);
			sesion.save(carta56);
			sesion.save(carta57);
			sesion.save(carta58);
			sesion.save(carta59);
			sesion.save(carta60);
			
			sesion.save(disponiblesJ1);
			sesion.save(disponiblesJ2);
			sesion.save(disponiblesJ3);
			sesion.save(disponiblesJ4);
			sesion.save(disponiblesJ5);
			sesion.save(disponiblesJ6);
			sesion.save(disponiblesJ7);
			sesion.save(disponiblesJ8);
			sesion.save(disponibles);
			
			sesion.save(disponiblesA1);
			sesion.save(disponiblesA2);
			sesion.save(disponiblesA3);
			sesion.save(disponiblesA4);
			sesion.save(disponiblesA5);
			sesion.save(disponiblesA6);
			sesion.save(disponiblesA7);
			sesion.save(disponiblesA8);
			sesion.save(disponiblesA9);
			sesion.save(disponiblesA10);
			
		sesion.getTransaction().commit();
		sesion.close();		
		
	}
	
	public static int establecerCosto(int sumafuerza) {
		int costo;
		if(sumafuerza >=0 && sumafuerza <=9  ){
			costo=1;
			return costo;
		}
		if(sumafuerza >=10 && sumafuerza <=15){
			costo=3;
			return costo;
		}
		if(sumafuerza >=16 && sumafuerza <=19){
			costo=5;
			return costo;
		}
		if(sumafuerza >=20 && sumafuerza <=25){
			costo=10;
			return costo;
		}
		if(sumafuerza >=26 && sumafuerza <=29){
			costo=20;
			return costo;
		}
		if(sumafuerza >=30 && sumafuerza <=36){
			costo=99;
			return costo;
		}
		return 0;
		
	}
	
	
	public static void main(String[] args) {
		try {
			LLenarBaseDatos BD = new LLenarBaseDatos();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}