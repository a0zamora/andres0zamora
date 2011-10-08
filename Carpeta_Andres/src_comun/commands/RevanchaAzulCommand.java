package commands;

import java.util.regex.Pattern;

import javax.swing.text.StyledEditorKit.BoldAction;

import constants.TipoDeEvento;

public class RevanchaAzulCommand implements Command{

	public static final String CADENA_COMANDO = "Revancha_Azul";
	
	int id;
	
	boolean boolRevanchaAzul;
	

	public RevanchaAzulCommand(String str) {
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
			boolRevanchaAzul = true;
			
		}else if(partes[2].startsWith("false")){
			boolRevanchaAzul = false;
		}else{
			throw new IllegalArgumentException();
		}
		
	}
	
	public RevanchaAzulCommand(int id, boolean boolRevanchaAzul){
		this.id=id;
		this.boolRevanchaAzul = boolRevanchaAzul;
	}
	
	public boolean getRevancha(){
		return boolRevanchaAzul; 
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
		return CADENA_COMANDO + " " + id + " " + boolRevanchaAzul;
	}

	public static void main(String[] args) {
		RevanchaAzulCommand comando = new RevanchaAzulCommand(0, false);
		System.out.println(comando.convertirAString());
	}
	
}
