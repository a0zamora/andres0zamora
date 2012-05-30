package com.andresoftware.tesis.selectcardsview;

import android.graphics.Canvas;

public class SelectCardsLoopThread extends Thread {
	static final long FPS = 10;
	private SelectCardsView view;
	private boolean running = false;

	public SelectCardsLoopThread(SelectCardsView view) {
		this.view = view;
	}

	public void setRunning(boolean run) {
		running = run;
	}

	@Override
	public void run() {
		long ticksPS = 1000 / FPS;
		long startTime;
		long sleepTime;
		while (running) {
			Canvas c = null;
			startTime = System.currentTimeMillis();
			try {
				c = view.getHolder().lockCanvas();
				synchronized (view.getHolder()) {
					view.onDraw(c);
				}
			} finally {
				if (c != null) {
					view.getHolder().unlockCanvasAndPost(c);
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

	public static void sleep(long ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
