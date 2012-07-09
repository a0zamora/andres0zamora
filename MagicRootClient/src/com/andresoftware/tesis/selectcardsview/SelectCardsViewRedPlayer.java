package com.andresoftware.tesis.selectcardsview;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.andresoftware.tesis.buttons.MagicRootButton;
import com.andresoftware.tesis.constants.MagicRootColors;
import com.andresoftware.tesis.gen.R;
import com.andresoftware.tesis.mainactivity.MagicRootActivity;
import com.andresoftware.tesis.user.UserInformation;
import com.andresoftware.tesis.utils.DialogMagicroot;

public class SelectCardsViewRedPlayer extends SurfaceView{
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

	//----------------------------------------------------------------------------------
	public SelectCardsViewRedPlayer(Context context, MagicRootActivity mgrt, UserInformation usrInformation) {
		super(context);
		
		this.mgr = mgrt;
		
		Display display = mgrt.getWindowManager().getDefaultDisplay();
		width = display.getWidth();
		height = display.getHeight();
		
		selectCardsMannager = new SelectCardsMannager(context, width, height, usrInformation, MagicRootColors.RED);
		gameCardsMannager = new GameCardsMannager(context, width, height);
		
		initGameLoop();
	}
	//----------------------------------------------------------------------------------
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(Color.BLACK);
		selectCardsMannager.drawCards(canvas);
		gameCardsMannager.drawCards(canvas);
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
