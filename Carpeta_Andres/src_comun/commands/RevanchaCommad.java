package commands;

import java.util.regex.Pattern;

import constants.TipoDeEvento;

public class RevanchaCommad implements Command{

	public static final String CADENA_COMANDO = "Revancha";
	
	int id;
	
	boolean boolrevancha;
	

	public RevanchaCommad(String str) {
		Pattern p = Pattern.compile(" ");

		String[] partes = p.split(str);

		if (partes.length != 3) {
			throw new IllegalArgumentException();
		}

		if (!partes[0].equals(CADENA_COMANDO)) {
			throw new IllegalArgumentException();
		}
		try{
			id = Integer.parseInt(partes[1]);
		}catch (NumberFormatException e) {
			throw new IllegalArgumentException(e);
			
		}
		if(partes[2].startsWith("true")){
			boolrevancha = true;
			
		}else if(partes[2].startsWith("false")){
			boolrevancha = false;
		}else{
			throw new IllegalArgumentException();
		}
		
	}
	
	public boolean getRevancha(){
		return boolrevancha; 
	}
	
	@Override
	public TipoDeEvento getTipoDeEvento() {
		return null;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public String convertirAString() {
		return CADENA_COMANDO + " " + id + " " + boolrevancha;
	}

}
