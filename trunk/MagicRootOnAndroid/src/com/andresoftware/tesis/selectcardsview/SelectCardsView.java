package com.andresoftware.tesis.selectcardsview;

import com.andresoftware.tesis.buttons.ReadyButton;
import com.andresoftware.tesis.mainactivity.MagicRootActivity;
import com.andresoftware.tesis.user.UserInformation;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

public class SelectCardsView extends SurfaceView{

	private boolean startAnimation = false;
	private int coordX = 0;
	private SelectCardsLoopThread selectCardsLoopThread;	// Necessary for animations
	private SurfaceHolder holder;	// Necessary for animations
	private SelectCardsMannager selectCardsMannager;  // Cards to select
	private GameCardsMannager gameCardsMannager;
	private MagicRootActivity mgr = null;
	private int width;
	private int height;
	private boolean touch;
	private ReadyButton readyButton;

	//----------------------------------------------------------------------------------
	public SelectCardsView(Context context, MagicRootActivity mgrt, UserInformation usrInformation) {
		super(context);
		this.mgr = mgrt;
		Display display = mgrt.getWindowManager().getDefaultDisplay();
		width = display.getWidth();
		height = display.getHeight();
		readyButton = new ReadyButton(width, height, context);
		selectCardsMannager = new SelectCardsMannager(context, width, height, usrInformation);
		gameCardsMannager = new GameCardsMannager(context, width, height);
		initGameLoop();
	}
	//----------------------------------------------------------------------------------
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(Color.BLACK);
		selectCardsMannager.drawCards(canvas);
		gameCardsMannager.drawCards(canvas);
		readyButton.draw(canvas);
	}
	//----------------------------------------------------------------------------------
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int eventaction = event.getAction(); 
		int X = (int)event.getX(); 
		int Y = (int)event.getY(); 
		switch (eventaction ) { 
		case MotionEvent.ACTION_DOWN:
			if(selectCardsMannager.touchSelectCardsPanel(Y)){
				startAnimation=true;
				touch = true;
				coordX = X;
			}
			break; 
		case MotionEvent.ACTION_MOVE:
			if(startAnimation){
				touch=false;
				selectCardsMannager.movePanel((coordX-X)*(-1));
				coordX = X;
			}
			break; 
		case MotionEvent.ACTION_UP: 
			if(touch){
				gameCardsMannager.asignCard(selectCardsMannager.onCard(X,Y));
				
			}else if(!startAnimation){
				gameCardsMannager.onCard(X,Y);
			}
			touch = false;
			startAnimation = false;
			break; 
		}
		invalidate();
		return true; 
	}
	//----------------------------------------------------------------------------------
	private void initGameLoop() {
		selectCardsLoopThread = new SelectCardsLoopThread(this);
		holder = getHolder();
		holder.addCallback(new SurfaceHolder.Callback() {

			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				boolean retry = true;
				selectCardsLoopThread.setRunning(false);
				while (retry) {
					try {
						selectCardsLoopThread.join();
						retry = false;
					} catch (InterruptedException e) {
					}
				}
			}

			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				selectCardsLoopThread.setRunning(true);
				selectCardsLoopThread.start();
			}

			@Override
			public void surfaceChanged(SurfaceHolder holder, int format,
					int width, int height) {
			}
		});
	}
	//----------------------------------------------------------------------------------

}
