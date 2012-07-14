package com.andresoftware.tesis.selectcardsview;

import com.andresoftware.tesis.buttons.MagicRootButton;
import com.andresoftware.tesis.constants.MagicRootColors;
import com.andresoftware.tesis.mainactivity.MagicRootActivity;
import com.andresoftware.tesis.user.UserInformation;
import com.andresoftware.tesis.utils.DialogMagicroot;
import com.andresoftware.tesis.gen.R;

import commands.DesconectarInvitadoCommand;
import commands.InvitadoDesconectadoCommand;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup.LayoutParams;
import android.widget.Toast;

public class SelectCardsViewBluePlayer extends SurfaceView{

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
	private boolean move = false;
	public MagicRootButton startGameButton;
	public MagicRootButton deletePlayerButton;

	//----------------------------------------------------------------------------------
	public SelectCardsViewBluePlayer(Context context, MagicRootActivity mgrt, 
			UserInformation usrInformation) {
		super(context);
		
		this.mgr = mgrt;
		
		Display display = mgrt.getWindowManager().getDefaultDisplay();
		width = display.getWidth();
		height = display.getHeight();
		
		startGameButton = new MagicRootButton(width, height, context, 
				BitmapFactory.decodeResource(context.getResources(), R.drawable.startgame));
		startGameButton.setPosY(0.82);
		
		deletePlayerButton = new MagicRootButton(width, height, context, 
				BitmapFactory.decodeResource(context.getResources(), R.drawable.deleteplayer));
		deletePlayerButton.setPosY(0.7);
		
		selectCardsMannager = new SelectCardsMannager(context, width, height, usrInformation, MagicRootColors.BLUE);
		gameCardsMannager = new GameCardsMannager(context, width, height);
		
		initGameLoop();
	}
	//----------------------------------------------------------------------------------
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(Color.BLACK);
		selectCardsMannager.drawCards(canvas);
		gameCardsMannager.drawCards(canvas);
		deletePlayerButton.draw(canvas, 0.7);
		startGameButton.draw(canvas, 0.82);
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
			move = true;
			break; 
		case MotionEvent.ACTION_UP: 
			if(touch){
				gameCardsMannager.asignCard(selectCardsMannager.onCard(X,Y));
				
			}else if(!startAnimation){
				gameCardsMannager.onCard(X,Y);
			}
			if(!move){
				if(deletePlayerButton.touch(X,Y)){
					mgr.getMagicRootConnection().sendCommandToServer(DesconectarInvitadoCommand.CADENA_COMANDO);
					deletePlayerButton.setEnable(false);
				}
				move = false;
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
	public void processInvited() {
		DialogMagicroot.dialogInfo(mgr, "New User", "A user has challenged you to Play");
		deletePlayerButton.setEnable(true);
	}
	public void setEnableDeletePlayerButton(boolean value) {
		deletePlayerButton.setEnable(value);
	}

}
