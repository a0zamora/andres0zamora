package commands;

import java.util.regex.Pattern;

import constants.TipoDeEvento;


public class GanadorCommand implements Command {


		public static final String CADENA_COMANDO = "Ganador";
		
		private int idJugador;
		private int pts;
		private int numeroLineas;
		private int numeroDiagonales;
		
		
		public GanadorCommand(String str) {
			Pattern p = Pattern.compile(" ");
			String[] partes = p.split(str);

			if (partes.length != 5) {

				throw new IllegalArgumentException();
			}

			if (!partes[0].equals(CADENA_COMANDO)) {

				throw new IllegalArgumentException();
			}

			try {
				idJugador = Integer.parseInt(partes[1]);
				pts = Integer.parseInt(partes[2]);
				numeroLineas = Integer.parseInt(partes[3]);
				numeroDiagonales =  Integer.parseInt(partes[4]);
			} catch (NumberFormatException e) {
				throw new IllegalArgumentException(e);
			}
		}

		public GanadorCommand(int idJugador, int pts , int numeroLineas, int numeroDiagonales) {
			
			this.idJugador = idJugador;
			this.numeroLineas = numeroLineas;
			this.numeroDiagonales = numeroDiagonales ;
			this.pts = pts;
			
		}


		
		@Override
		public TipoDeEvento getTipoDeEvento() {
			return null;
		}

		@Override
		public int getId() {
			return 0;
		}

		@Override
		public String convertirAString() {
			return CADENA_COMANDO //
					+ " " + idJugador + " " + pts + " " +numeroLineas + " " + numeroDiagonales ;
		}
	
}
