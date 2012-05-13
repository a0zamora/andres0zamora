package com.andresoftware.tesis.tablero;



import com.examples.dragdrop.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MagicRootTable extends SurfaceView {
	private OpponentCards opponentCards;
	private UserCards userCards;
	private TableGame tableGame;
	private Bitmap fondo; //private Bitmap table;
	private int balID = 10; // variable to know what ball is being dragged
	private Another_Example_DragDropActivity context;
	private SurfaceHolder holder;	// Necessary for animations
	private GameLoopThread gameLoopThread;	// Necessary for animations
	//----------------------------------------------------------------------------------
	public MagicRootTable(Context context , 
			Context baseContext, 
			Another_Example_DragDropActivity cntx) {
		super(context);
		userCards = new UserCards(context);
		opponentCards = new OpponentCards(context);

		gameLoopThread = new GameLoopThread(this);
		holder = getHolder();
		holder.addCallback(new SurfaceHolder.Callback() {

			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				boolean retry = true;
				gameLoopThread.setRunning(false);
				while (retry) {
					try {
						gameLoopThread.join();
						retry = false;
					} catch (InterruptedException e) {
					}
				}
			}

			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				gameLoopThread.setRunning(true);
				gameLoopThread.start();
			}

			@Override
			public void surfaceChanged(SurfaceHolder holder, int format,
					int width, int height) {
			}
		});
		this.context=cntx;
		setFocusable(true); //necessary for getting the touch events
		initGUI();
	}
	//----------------------------------------------------------------------------------
	// the method that draws the game
	@Override protected void onDraw(Canvas canvas) {
		canvas.drawBitmap(fondo, 0, 0, null);
		tableGame.drawCards(canvas);
		//draw the user cards on the canvas
		userCards.drawCards(canvas, balID);
		//draw the opponent cards on the canvas
		opponentCards.drawCards(canvas);
		//this is for draw the card that is moving on the canvas
		if(balID!=10){
			userCards.getCard(balID).drawBall(canvas);
		}
	}
	//----------------------------------------------------------------------------------
	private void initGUI() {
		tableGame = new TableGame(context);
		fondo = BitmapFactory.decodeResource(context.getResources(), R.drawable.firetable); 
	}
	//----------------------------------------------------------------------------------
	// events when touching the screen
	public boolean onTouchEvent(MotionEvent event) {
		int eventaction = event.getAction(); 
		int X = (int)event.getX(); 
		int Y = (int)event.getY(); 
		switch (eventaction ) { 
		case MotionEvent.ACTION_DOWN: // touch down so check if the finger is on a ball
			balID=userCards.getId(X, Y);
			break; 
		case MotionEvent.ACTION_MOVE:   // touch drag with the ball
			// move the card the same as the finger
			if (balID != 10) {
				userCards.moveCard(balID, X-32, Y-42);
			}
			break; 
		case MotionEvent.ACTION_UP: 
			if (balID != 10) {
				int idAux=tableGame.getId(X, Y);
				if(idAux!=-1){
					userCards.moveCard(balID, tableGame.getCoorX(idAux),tableGame.getCoorY(idAux));
					userCards.getCard(balID).setEnable(false);
				}else{
					userCards.moveCard(balID, 
							userCards.getCard(balID).getInitialPosX(), 
							userCards.getCard(balID).getInitialPosY());
				}
			}
			break; 
		}
		invalidate();
		return true; 
	}
}