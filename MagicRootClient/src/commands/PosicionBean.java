package commands;

import constants.TipoDeEvento;

public class PosicionBean implements Command {

	private int id;
	private int x;
	private int y;
	boolean sameElement;

	// --------------------------------------------------------------------------------

	public PosicionBean(int id, int x, int y, boolean sameElement) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.sameElement = sameElement;

	}

	// --------------------------------------------------------------------------------
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean getSameElement(){
		return sameElement;
	}
	
	// --------------------------------------------------------------------------------
	@Override
	public TipoDeEvento getTipoDeEvento() {
		return null;
	}
	// --------------------------------------------------------------------------------
	@Override
	public String convertirAString() {
		return id + " " + x + " " + y + " " + sameElement;

	}
	// --------------------------------------------------------------------------------
	@Override
	public int getId() {

		return id;
	}

}
