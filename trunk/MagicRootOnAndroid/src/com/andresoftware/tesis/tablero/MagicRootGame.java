package com.andresoftware.tesis.tablero;
import com.andresoftware.tesis.gen.R;
import com.andresoftware.tesis.mainactivity.MagicRootActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

public class MagicRootGame extends SurfaceView {
	private OpponentCardsManager opponentCards;
	private UserCardsManager userCards;
	private TableCardsManager tableGame;
	private Bitmap fondo; //private Bitmap table;
	private PlayCard movingCard = null; // variable to know what card is moving
	private MagicRootActivity context;
	private SurfaceHolder holder;	// Necessary for animations
	private GameLoopThread gameLoopThread;	// Necessary for animations
	private MagicRootActivity mgrt;
	private int width;
	private int height;
	//----------------------------------------------------------------------------------
	public MagicRootGame(Context context , 
			Context baseContext, 
			MagicRootActivity cntx) {
		super(context);
		this.context=cntx;
		mgrt=cntx;
		Display display = mgrt.getWindowManager().getDefaultDisplay();
		width = display.getWidth();
		height = display.getHeight();
		userCards = new UserCardsManager(context, width, height);
		opponentCards = new OpponentCardsManager(context, width, height);
		tableGame = new TableCardsManager(context, width, height);
		initGUI();
		initGameLoop();
		setFocusable(true); //necessary for getting the touch events
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
		if(movingCard!=null){
			movingCard.drawCard(canvas);
		}
	}
	//----------------------------------------------------------------------------------
	private void initGUI() {
		Display display = mgrt.getWindowManager().getDefaultDisplay();
		Bitmap fondoAux = BitmapFactory.decodeResource(context.getResources(), R.drawable.firetable); 
		fondo = Bitmap.createScaledBitmap(fondoAux, display.getWidth(), display.getHeight(), false);
//		fondo =BitmapFactory.decodeResource(context.getResources(), R.drawable.firetable);
	}
	//----------------------------------------------------------------------------------
	public boolean onTouchEvent(MotionEvent event) {
		int eventaction = event.getAction(); 
		int X = (int)event.getX(); 
		int Y = (int)event.getY(); 
		switch (eventaction ) { 
		case MotionEvent.ACTION_DOWN: // touch down so check if the finger is on a ball
			if(userCards.getId(X, Y)!=null){
				movingCard=userCards.getCard(X, Y).clone();
				userCards.setCardNull(X, Y);
			}            
			break; 
		case MotionEvent.ACTION_MOVE:   // touch drag with the ball
			// move the card the same as the finger
			if (movingCard!=null) {
				movingCard.setX(X- (int)(movingCard.getWidth()/2));
				movingCard.setY(Y- (int) (movingCard.getHeight()/2));
			}
			break; 
		case MotionEvent.ACTION_UP: 
			if (movingCard != null) {
				PlayCard auxCard = tableGame.getCard(X, Y);
				if(auxCard!=null){
					movingCard.setX(auxCard.getX());
					movingCard.setY(auxCard.getY());
					movingCard.setEnable(false);
					tableGame.setCard(X, Y, movingCard.clone());
					movingCard=null;
				}else{
					movingCard.setX(movingCard.getInitialPosX());
					movingCard.setY(movingCard.getInitialPosY());
					movingCard.setEnable(true);
					userCards.setCard(movingCard.clone());
					movingCard=null;
//					Toast.makeText(mgrt.getBaseContext(), "MagicRoot", 
//							Toast.LENGTH_LONG).show();
				}
			}
			break; 
		}
		invalidate();
		return true; 
	}
	//----------------------------------------------------------------------------------
	private void initGameLoop() {
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
	}
	//----------------------------------------------------------------------------------
}