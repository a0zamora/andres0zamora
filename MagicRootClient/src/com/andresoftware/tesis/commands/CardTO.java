package com.andresoftware.tesis.commands;

public class CardTO {
	private int Fnorth;
	private int Fsouth;
	private int Feast;
	private int Fwest;
	private String element;
	private int id;
	
	public int getFnorth() {
		return Fnorth;
	}

	public void setFnorth(int fnorth) {
		Fnorth = fnorth;
	}

	public int getFsouth() {
		return Fsouth;
	}

	public void setFsouth(int fsouth) {
		Fsouth = fsouth;
	}

	public int getFeast() {
		return Feast;
	}

	public void setFeast(int feast) {
		Feast = feast;
	}

	public int getFwest() {
		return Fwest;
	}

	public void setFwest(int fwest) {
		Fwest = fwest;
	}

	public String getElement() {
		return element;
	}

	public void setElement(String element) {
		this.element = element;
	}

	public CardTO() {
	
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
	
}
