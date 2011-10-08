package commands;

import java.util.regex.Pattern;

import constants.TipoDeEvento;

public class RevanchaRojoCommand implements Command{
	
	
	public static final String CADENA_COMANDO = "Revancha_Rojo";
	
	int id;
	
	boolean boolRevanchaRojo;
	

	public RevanchaRojoCommand(String str) {
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
			boolRevanchaRojo = true;
			
		}else if(partes[2].startsWith("false")){
			boolRevanchaRojo = false;
		}else{
			throw new IllegalArgumentException();
		}
		
	}
	
	public RevanchaRojoCommand(int id, boolean booleanRevanchaRojo){
		this.id=id;
		this.boolRevanchaRojo = booleanRevanchaRojo;
		
		
	}
	
	public boolean getRevancha(){
		return boolRevanchaRojo; 
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
		return CADENA_COMANDO + " " + id + " " + boolRevanchaRojo;
	}


}
