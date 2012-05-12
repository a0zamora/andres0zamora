package com.examples.dragdrop;



import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class DrawView extends SurfaceView {
	
	private PlayCard[] cards = new PlayCard[10]; // array that holds the cards
	private TableGame tableGame;
	private Bitmap fondo;
	//private Bitmap table;
	private int balID = 0; // variable to know what ball is being dragged
	private Another_Example_DragDropActivity context;
	
	// Necessary for animations
    private SurfaceHolder holder;
    private GameLoopThread gameLoopThread;
	

	public DrawView(Context context , Context baseContext, Another_Example_DragDropActivity cntx) {
		super(context);
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
		// setting the start point for the balls
		initGUI();
		


	}

	private void initGUI() {
		tableGame = new TableGame();
		fondo = BitmapFactory.decodeResource(context.getResources(), R.drawable.firetable); 
		//able = BitmapFactory.decodeResource(context.getResources(), R.drawable.tablero);
		Point point1 = new Point();
		point1.x = 5;
		point1.y = 10;
		Point point2 = new Point();
		point2.x = 5;
		point2.y = 105;
		Point point3 = new Point();
		point3.x = 5;
		point3.y = 200;
		Point point4 = new Point();
		point4.x = 5;
		point4.y = 295;
		Point point5= new Point();
		point5.x = 74;
		point5.y = 10;
		Point point6= new Point();
		point6.x = 74;
		point6.y = 105;
		Point point7= new Point();
		point7.x = 74;
		point7.y = 200;
		Point point8= new Point();
		point8.x = 74;
		point8.y = 295;
		Point point9= new Point();
		point9.x = 730;
		point9.y = 295;
		Point point10= new Point();
		point10.x = 661;
		point10.y = 295;


		// declare each card with the color
		cards[0] = new PlayCard(context,R.drawable.yellowcard, point1);
		cards[1] = new PlayCard(context,R.drawable.yellowcard, point2);
		cards[2] = new PlayCard(context,R.drawable.yellowcard, point3);
		cards[3] = new PlayCard(context,R.drawable.yellowcard, point4);
		cards[4] = new PlayCard(context,R.drawable.yellowcard, point5);
		cards[5] = new PlayCard(context,R.drawable.yellowcard, point6);
		cards[6] = new PlayCard(context,R.drawable.yellowcard, point7);
		cards[7] = new PlayCard(context,R.drawable.yellowcard, point8);
		cards[8] = new PlayCard(context,R.drawable.yellowcard, point9);
		cards[9] = new PlayCard(context,R.drawable.yellowcard, point10);
		
	}

	// the method that draws the balls
	@Override protected void onDraw(Canvas canvas) {
		//canvas.drawColor(0xFFCCCCCC);     //if you want another background color      
		canvas.drawBitmap(fondo, 0, 0, null);
		//canvas.drawBitmap(table, 215,10 , null);
		//draw the balls on the canvas
		for (PlayCard ball : cards) {
			ball.drawBall(canvas);
		}

	}

	// events when touching the screen
	public boolean onTouchEvent(MotionEvent event) {
		int eventaction = event.getAction(); 

		int X = (int)event.getX(); 
		int Y = (int)event.getY(); 

		switch (eventaction ) { 

		case MotionEvent.ACTION_DOWN: // touch down so check if the finger is on a ball
			balID = 0;
			for (PlayCard ball : cards) {
				// check if inside the bounds of the ball (circle)
				// get the center for the ball
				int centerX = ball.getX() + 32;
				int centerY = ball.getY() + 42;

				// calculate the radius from the touch to the center of the ball
				int radCardX  = (centerX-X);
				int radCardY  = (centerY-Y);
				if(radCardX<0){
					radCardX = radCardX*(-1);
				}
				if(radCardY<0){
					radCardY = radCardY*(-1);
				}
				// if the radius is smaller then 23 (radius of a ball is 22), then it must be on the ball
				if (radCardX <= 32 && radCardY <= 42){
					balID = ball.getID();
					break;
				}
			}

			break; 


		case MotionEvent.ACTION_MOVE:   // touch drag with the ball
			// move the balls the same as the finger
			if (balID > 0) {
				cards[balID-1].setX(X-32);
				cards[balID-1].setY(Y-42);
			}

			break; 

		case MotionEvent.ACTION_UP: 
			if (balID > 0) {
				cards[balID-1].flipCard();
			}
			break; 
		} 
		return true; 
		

	}
}
