package com.andresoftware.tesis.selectcardsview;

import android.graphics.Canvas;

import com.andresoftware.tesis.tablero.MagicRootGame;

public class SelectCardsLoopThread extends Thread{
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
                 sleepTime = ticksPS-(System.currentTimeMillis() - startTime);
                 try {
                        if (sleepTime > 0)
                               sleep(1);
                        else
                               sleep(1);
                 } catch (Exception e) {}
          }
    }
}
