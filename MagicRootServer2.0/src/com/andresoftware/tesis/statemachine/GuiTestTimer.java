package com.andresoftware.tesis.statemachine;


import commands.Command;
import constants.TipoDeEvento;

public class GuiTestTimer extends AbstractTimer {

	//private GuiTimer guiTimer;
	private StateMachine maquinaEstados;
	private GameInterface partida;
	private final static int TIME = 52000;
	private Command comando;
	private int idHilo;
	
	public GuiTestTimer(StateMachine maquinaEstados,GameInterface partida, int idHilo) {
		super(maquinaEstados, partida, TIME);
		
		// TODO: ARRANCAR EL TIMER DESDE AFUERA
		this.maquinaEstados = maquinaEstados;
		this.partida = partida;	
	}
	
	
	public GuiTestTimer(StateMachine maquinaEstados,GameInterface partida, int idHilo,int time) {
		super(maquinaEstados, partida, time);
		
		this.maquinaEstados = maquinaEstados;
		this.partida = partida;	
	}

	@Override
	protected void fireTimer() {
		System.out.println("GuiTestTimer: ----------------------, " + this);
		System.out.println("GuiTestTimer: FiredTimerrrrr, " + this);
		System.out.println("GuiTestTimer: ---------------------, " + this);
		try {
			maquinaEstados.disparaEvento(TipoDeEvento.timeOut, null, null, idHilo);
		} catch (UnknowEventForState e) {
			e.printStackTrace();
		} catch (IdCreatorInvalid e) {
			e.printStackTrace();
		}
//		comando = new TimeOutCommand(idHilo);
//		//disparar evento time out y disparar el comando desde la transcicion del evento nuevo time oiut 
//		partida.enviarMsjAmbosJug(comando);
	}
}
