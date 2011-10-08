package commands;

import constants.TipoDeEvento;

public class EmpateCommand  implements Command {
	

	public static final String CADENA_COMANDO = "Empate";
	
	private int id=0;
	private int rojoPts;
	private int rojoNumeroLineas;
	private byte rojoNumeroDiagonales;
	
	private int azulPts;
	private int azulNumeroLineas;
	private byte azulNumeroDiagonales;
	
	
	public EmpateCommand( int rojoPts , int azulPts) {
		
		this.rojoPts = rojoPts;
		this.azulPts =  azulPts;
		
	}
	
	public EmpateCommand( int  azulPts, int azulNumeroLineas, int  azulNumeroDiagonales , 
						int rojoPts,  int rojoNumeroLineas, int rojoNumeroDiagonales  ) {
		
		this.rojoPts = rojoPts;
		this.azulPts =  azulPts;
		
		this.rojoNumeroDiagonales = (byte ) rojoNumeroDiagonales;
		this.rojoNumeroLineas = rojoNumeroLineas;
		
		this.azulNumeroDiagonales = (byte ) azulNumeroDiagonales;
		this.azulNumeroLineas = azulNumeroLineas;
	
		
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
				+ " " + id + " " + azulPts + " " + azulNumeroLineas + " " + azulNumeroDiagonales +
				rojoPts + " " + rojoNumeroLineas + " " + rojoNumeroDiagonales ;
	}


	
	

}
