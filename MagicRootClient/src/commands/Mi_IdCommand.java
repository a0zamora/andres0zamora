package commands;

import java.util.regex.Pattern;

import constants.TipoDeEvento;


public class Mi_IdCommand implements Command{
	
	public static final String CADENA_COMANDO = "Mi_Id";
	private String nombreUsr;
	private int id;

	
	public Mi_IdCommand(int id,String nombreUsr ) {
		this.id = id;
		this.nombreUsr = nombreUsr;
	    
	}
	
	public Mi_IdCommand(String str) {
		Pattern p = Pattern.compile(" ");
		String[] partes = p.split(str);
		id = Integer.parseInt(partes[1]);
		nombreUsr = partes[2];
	}
	public String getNombreUsr() {
		return nombreUsr;
	}
	public void setNombreUsr(String nombreUsr) {
		this.nombreUsr = nombreUsr;
	}
	public TipoDeEvento getTipoDeEvento() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public int getId() {
		// TODO Auto-generated method stub
		return id;
	}

	
	public String convertirAString() {
		// TODO Auto-generated method stub
		return CADENA_COMANDO+" "+Integer.toString(id)+" "+nombreUsr;
	}

}
