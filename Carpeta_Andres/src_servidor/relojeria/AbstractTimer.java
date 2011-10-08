package relojeria;

import union.Partida;

public abstract class AbstractTimer implements Runnable {

	private boolean armed;

	private Thread hilo;
	
	private Object syncObject;
	
	private long time;

	public AbstractTimer(Object syncObject, Partida partida, long time) {
		this.syncObject = syncObject;
		this.time = time;

		armed = true;

		hilo = new Thread(AbstractTimer.this);
		hilo.start();
	}
	@Override
	public void run() {
		synchronized (syncObject) {
			System.out.println("AbstractTimer: arranco el hilo, " + this);
			try {
				syncObject.wait(time);
			} catch (InterruptedException e) {
				System.out.println("AbstractTimer: revento el hilo, " + this);
			}
			if (armed) {
				System.out.println("AbstractTimer: Antes de explotar, " + this);
				fireTimer();
				System.out.println("AbstractTimer: luego de explotar, " + this);	
			}
		}
	}

	public void stopTimer() {
		synchronized (syncObject) {
			System.out.println("AbstractTimer: antes de parar, " + this);
			armed = false;
			syncObject.notify();
			System.out.println("AbstractTimer: luego de parar, " + this);
		}
	}

	abstract protected void fireTimer();
}
