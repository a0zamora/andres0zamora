package commands;

import java.util.regex.Pattern;

import constants.TipoDeEvento;


public class CrearCommand implements Command {
	
	public static final String CADENA_COMANDO = "crear" ;

	public static final TipoDeEvento TIPO_DE_EVENTO = TipoDeEvento.crearPartida;

	private int IdHiloCliente;
	
	private String usrName;

	public CrearCommand(String str) {
		
		Pattern p = Pattern.compile(" ");

		String[] partes = p.split(str);

		if (partes.length != 3) {
			throw new IllegalArgumentException();
		}

		if (!partes[0].equals(CADENA_COMANDO)) {
			throw new IllegalArgumentException();
		}

		try {
			IdHiloCliente = Integer.parseInt( partes[1]);
			usrName = partes[2];
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(e);
		}
	}

	// --------------------------------------------------------------------------------

	public CrearCommand( int idHiloCliente ) {
		this.IdHiloCliente = idHiloCliente;
	}

	// --------------------------------------------------------------------------------

	public int getId() {
		return IdHiloCliente;
	}

	public void setIdHiloCliente(int idHiloCliente) {
		IdHiloCliente = idHiloCliente;
	}

	// --------------------------------------------------------------------------------

	@Override
	public TipoDeEvento getTipoDeEvento() {
		return TIPO_DE_EVENTO;
	}

	@Override
	public String convertirAString() {
		return CADENA_COMANDO + " " + IdHiloCliente;
	}
	

	public static void main(String[] args) {

//		//// ----------cliente osea Cesar construye el comando y lo envia----------
//		Command comando = new CrearCommand(555);
//	
//		String str = comando.convertirAString();
//		System.err.println(str);
//		// --------------------
//
//		// hilo pregunta por el tipo de comando si es jugar se lo mando a
//		
//		if (str.startsWith(CrearCommand.CADENA_COMANDO)) {
//
//			// partida
//			// y en partida:
//			// Configurar contexto (tabla)
//			// Configurar paràmetros (comando)
//
//			Command jc3 = new CrearCommand(str);
//			CrearCommand casteo= (CrearCommand) jc3;
//			
//			System.err.println(casteo.getIdHiloCliente());
//
//	
//			}

	}

	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}

	public String getUsrName() {
		return usrName;
	}
	
}