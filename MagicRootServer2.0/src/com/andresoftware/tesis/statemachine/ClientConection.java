package com.andresoftware.tesis.statemachine;

public class ClientConection {
	
		private String nivel;
		private String userName;
		
		
	public ClientConection(String nivel, int i) {
		this.nivel = nivel;
		this.userName = i+"";
	}
	
	
	
	public ClientConection(String userName) {
		this.userName = userName;
	}



	public String getNivel() {
		return nivel;
	}
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	public String getNombreUsr() {
		return userName;
	}
	public void setNombreUsr(String nombreUsr) {
		this.userName = nombreUsr;
	}
	
	
}
