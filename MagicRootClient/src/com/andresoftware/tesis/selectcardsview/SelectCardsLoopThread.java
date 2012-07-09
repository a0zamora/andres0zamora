package com.andresoftware.tesis.selectcardsview;

import android.graphics.Canvas;

public class SelectCardsLoopThread extends Thread {
	static final long FPS = 10;
	private SelectCardsViewBluePlayer blueView = null;
	private SelectCardsViewRedPlayer redView = null;
	private boolean running = false;

	//---------------------------------------------------------------------------------------
	public SelectCardsLoopThread(SelectCardsViewBluePlayer selectCardsViewBluePlayer) {
		this.blueView = selectCardsViewBluePlayer;
	}
	//---------------------------------------------------------------------------------------
	public SelectCardsLoopThread(SelectCardsViewRedPlayer selectCardsViewRedPlayer) {
		this.redView = selectCardsViewRedPlayer;
	}
	//---------------------------------------------------------------------------------------
	public void setRunning(boolean run) {
		running = run;
	}
	//---------------------------------------------------------------------------------------
	@Override
	public void run() {
		long ticksPS = 1000 / FPS;
		long startTime;
		long sleepTime;
		while (running) {
			Canvas c = null;
			startTime = System.currentTimeMillis();
			try {
				if(blueView != null){
					c = blueView.getHolder().lockCanvas();
					synchronized (blueView.getHolder()) {
						blueView.onDraw(c);
					}
				}else{
					c = redView.getHolder().lockCanvas();
					synchronized (redView.getHolder()) {
						redView.onDraw(c);
					}
				}
			}
			finally {
				if (c != null && blueView != null) {
					blueView.getHolder().unlockCanvasAndPost(c);
				}else{
					redView.getHolder().unlockCanvasAndPost(c);
				}
			}
			sleepTime = ticksPS - (System.currentTimeMillis() - startTime);

			if (sleepTime > 0) {
				SelectCardsLoopThread.sleep(1); // TODO Revisar el 1 porque en
				// juegos de swing
				// suele ser peligroso
				// usar numeros tan pequeños
			} else {
				SelectCardsLoopThread.sleep(1);
			}
		}
	}
	//---------------------------------------------------------------------------------------
	public static void sleep(long ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
	//---------------------------------------------------------------------------------------
}
