package com.andresoftware.tesis.selectcardsview;

import com.andresoftware.tesis.gen.R;
import com.andresoftware.tesis.mainactivity.MagicRootActivity;
import com.andresoftware.tesis.tablero.PlayCard;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

public class SelectCardsView extends SurfaceView{

	private boolean startAnimation = false;
	private int coordX = 0;
	private Bitmap fondo; //private Bitmap table;
	private SelectCardsLoopThread selectCardsLoopThread;	// Necessary for animations
	private SurfaceHolder holder;	// Necessary for animations
	private SelectCardsMannager selectCardsMannager;  // Cards to select
	private MagicRootActivity mgr = null;
	private int width;
	private int height;

	//----------------------------------------------------------------------------------
	public SelectCardsView(Context context, MagicRootActivity mgrt) {
		super(context);
		Display display = mgrt.getWindowManager().getDefaultDisplay();
		width = display.getWidth();
		height = display.getHeight();
		this.mgr = mgrt;
		fondo = BitmapFactory.decodeResource(context.getResources(), R.drawable.firetable); 
		selectCardsMannager = new SelectCardsMannager(context, width, height);
		initGameLoop();
	}
	//----------------------------------------------------------------------------------
	@Override
	protected void onDraw(Canvas canvas) {
//		canvas.drawBitmap(fondo, 0, 0, null);
		canvas.drawColor(Color.BLACK);
		selectCardsMannager.drawCards(canvas);
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
				coordX = X;
			}
			break; 
		case MotionEvent.ACTION_MOVE:
			if(startAnimation){
				selectCardsMannager.movePanel((coordX-X)*(-1));
				coordX = X;
			}
			break; 
		case MotionEvent.ACTION_UP: 
			Display display = mgr.getWindowManager().getDefaultDisplay();
			startAnimation = false;
			Toast.makeText(mgr.getBaseContext(), "MagicRoot width= "+display.getWidth()
					+" height= "+display.getHeight(), 
					Toast.LENGTH_LONG).show();
			break; 
		}
		invalidate();
		return true; 
	}
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
