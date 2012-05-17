package com.andresoftware.tesis.tablero;



import java.util.ArrayList;
import java.util.List;

import com.andresoftware.tesis.gen.R;
import com.andresoftware.tesis.mainactivity.MagicRootActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MagicRootGame extends SurfaceView {
	private OpponentCardsManager opponentCards;
	private UserCardsManager userCards;
	private TableCardsManager tableGame;
	private Bitmap fondo; //private Bitmap table;
	private List<PlayCard> movingCard = new ArrayList<PlayCard>(); // variable to know what card is moving
	private MagicRootActivity context;
	private SurfaceHolder holder;	// Necessary for animations
	private GameLoopThread gameLoopThread;	// Necessary for animations
	//----------------------------------------------------------------------------------
	public MagicRootGame(Context context , 
			Context baseContext, 
			MagicRootActivity cntx) {
		super(context);
		userCards = new UserCardsManager(context);
		opponentCards = new OpponentCardsManager(context);

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
		userCards.drawCards(canvas);
		//draw the opponent cards on the canvas
		opponentCards.drawCards(canvas);
		//this is for draw the card that is moving on the canvas
		if(movingCard.size()!=0){
			movingCard.get(0).drawCard(canvas);
		}
	}
	//----------------------------------------------------------------------------------
	private void initGUI() {
		tableGame = new TableCardsManager(context);
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
			if(userCards.getId(X, Y)!=null){
				movingCard.add(userCards.getCard(X, Y));
				movingCard.get(0).setEnable(false);
			}            
			break; 
		case MotionEvent.ACTION_MOVE:   // touch drag with the ball
			// move the card the same as the finger
			if (movingCard.size() != 0) {
				movingCard.get(0).setX(X-32);
				movingCard.get(0).setY(Y-42);
			}
			break; 
		case MotionEvent.ACTION_UP: 
			if (movingCard.size() != 0) {
				PlayCard auxCard= tableGame.getCard(X, Y);
				if(auxCard!=null){
					movingCard.get(0).setX(auxCard.getX());
					movingCard.get(0).setY(auxCard.getY());
					movingCard.get(0).setEnable(false);
					tableGame.setCard(movingCard.remove(0));
				}else{
					movingCard.get(0).setX(movingCard.get(0).getInitialPosX());
					movingCard.get(0).setY(movingCard.get(0).getInitialPosY());
					movingCard.get(0).setEnable(true);
					userCards.setCard(movingCard.remove(0));
				}
			}
			break; 
		}
		invalidate();
		return true; 
	}
}