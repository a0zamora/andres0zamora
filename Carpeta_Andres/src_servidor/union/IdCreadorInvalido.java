package union;

public class IdCreadorInvalido extends Exception {
	
	public IdCreadorInvalido( int idCreador) {
		
		super("Id creador no coincide: "+ idCreador);
	
	}

}
