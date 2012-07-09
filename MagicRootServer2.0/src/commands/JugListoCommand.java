package commands;

import java.util.regex.Pattern;

import constants.TipoDeEvento;


public class JugListoCommand implements Command{
	
	public static final String CADENA_COMANDO = "listo";
	
	private int id;
	private int idCarta1;  
	private int idCarta2; 
	private int idCarta3;
	private int idCarta4; 
	private int idCarta5;  
	private int idCarta6;  
	private int idCarta7;  
	private int idCarta8; 
	
	
	
	//public static final TipoDeEvento TIPO_DE_EVENTO = TipoDeEvento.jugadorListo;

	public JugListoCommand(String str) {
		
		Pattern p = Pattern.compile(" ");

		String[] partes = p.split(str);

		if (partes.length != 10) {
			throw new IllegalArgumentException();
		}

		if (!partes[0].equals("listo")) {
			throw new IllegalArgumentException();
		}

		try {
			id = Integer.parseInt( partes[1]);
			idCarta1 = Integer.parseInt( partes[2]);
			idCarta2 = Integer.parseInt( partes[3]);
			idCarta3 = Integer.parseInt( partes[4]);
			idCarta4 = Integer.parseInt( partes[5]);
			idCarta5 = Integer.parseInt( partes[6]);
			idCarta6 = Integer.parseInt( partes[7]);
			idCarta7 = Integer.parseInt( partes[8]);
			idCarta8 = Integer.parseInt( partes[9]);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	public JugListoCommand( int id, int idCarta1, int idCarta2,int idCarta3, int idCarta4, int idCarta5, int idCarta6,
			int idCarta7, int idCarta8){
		this.id=id;
		this.idCarta7 = idCarta7;
		this.idCarta6 = idCarta6;
		this.idCarta5 = idCarta5;
		this.idCarta4 = idCarta4;
		this.idCarta3 = idCarta3;
		this.idCarta2 = idCarta2;
		this.idCarta1 = idCarta1;
		this.idCarta8 = idCarta8;
	}
	
	public int getIdCarta1() {
		return idCarta1;
	}

	public void setIdCarta1(int idCarta1) {
		this.idCarta1 = idCarta1;
	}

	public int getIdCarta2() {
		return idCarta2;
	}

	public void setIdCarta2(int idCarta2) {
		this.idCarta2 = idCarta2;
	}

	public int getIdCarta3() {
		return idCarta3;
	}

	public void setIdCarta3(int idCarta3) {
		this.idCarta3 = idCarta3;
	}

	public int getIdCarta4() {
		return idCarta4;
	}

	public void setIdCarta4(int idCarta4) {
		this.idCarta4 = idCarta4;
	}

	public int getIdCarta5() {
		return idCarta5;
	}

	public void setIdCarta5(int idCarta5) {
		this.idCarta5 = idCarta5;
	}

	public int getIdCarta6() {
		return idCarta6;
	}

	public void setIdCarta6(int idCarta6) {
		this.idCarta6 = idCarta6;
	}

	public int getIdCarta7() {
		return idCarta7;
	}

	public void setIdCarta7(int idCarta7) {
		this.idCarta7 = idCarta7;
	}

	public int getIdCarta8() {
		return idCarta8;
	}

	public void setIdCarta8(int idCarta8) {
		this.idCarta8 = idCarta8;
	}

	public void setId(int id) {
		this.id = id;
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
		return CADENA_COMANDO + " " + id + " " + idCarta1 + " " + idCarta2 + " " + idCarta3 + " " + idCarta4 + " " + idCarta5+ " " + idCarta6
		+ " " + idCarta7 + " " +  idCarta8 ;
	}
}