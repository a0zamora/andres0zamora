package com.andresoftware.tesis.mainactivity;
import com.andresoftware.tesis.chat.ChatView;
import com.andresoftware.tesis.menu.MenuView;
import com.andresoftware.tesis.selectcardsview.SelectCardsView;
import com.andresoftware.tesis.tablero.MagicRootGame;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;

public class MagicRootActivity extends Activity {
	private MagicRootConnection magicRootConnection;
	private ChatView chatView=null;
//	String command="";
	private Handler myUpdateHandler = null;
	private CurrentWindow currentView = null;
	/** Called when the activity is first created. */
	//----------------------------------------------------------------------------------
	@Override
	public void onCreate(Bundle savedInstanceState) {        
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		initiHandler();
		connect();
		changeViewToMenu();	
	}
	//----------------------------------------------------------------------------------
	private void initiHandler() {
		myUpdateHandler = new Handler() {
			public void handleMessage(Message msg) {
				if (msg.what == CommandsIDs.MSG_ID) {
					handleCommandMessage(msg.obj);
				}
				super.handleMessage(msg);
			}
		};
	}
	//----------------------------------------------------------------------------------	
	private void handleCommandMessage(Object obj) {
		String command = (String) obj;
		
		// Interpretas el comando, lo desempacas y transformas en un objeto y lo procesas

		if(currentView.equals(CurrentWindow.CHAT_VIEW)){
			chatView.addTextToChat(command);
		}
	}
	//----------------------------------------------------------------------------------
	public void connect() {
		magicRootConnection = new MagicRootConnection(this);
		Thread cThread = new Thread(magicRootConnection);
		cThread.start();
	}
	//----------------------------------------------------------------------------------
	public Handler getHandler() {
		return myUpdateHandler;
	}
	//----------------------------------------------------------------------------------
	public void changeViewToChat() { 
		currentView = CurrentWindow.CHAT_VIEW;
		chatView = new ChatView(this);
	}
	//----------------------------------------------------------------------------------
	public void changeViewToMenu() {
		currentView = CurrentWindow.menuView;
		MenuView.initMenu(this);          
	}
	//----------------------------------------------------------------------------------
	public void changeViewToGamerRoom() {   
		currentView = CurrentWindow.playNowView;
		setContentView(new MagicRootGame(this, getBaseContext(), this));             
	}
	//----------------------------------------------------------------------------------
	public void changeViewToTestRoom() {
		currentView = CurrentWindow.testRoom;
		setContentView(new SelectCardsView(this, this));     
	}
	//----------------------------------------------------------------------------------
	@Override
	public void onBackPressed() {
		if(currentView.equals(CurrentWindow.menuView)){
			finish();
			System.exit(0);
		}else{
			changeViewToMenu();
		}
	}               
	//----------------------------------------------------------------------------------                    
	//		@Override
	//		protected void onResume() {
	//			changeViewToMenu();
	//			super.onResume();
	//		}
	//----------------------------------------------------------------------------------
	public void updateChat(String data) {
		chatView.addTextToChat(data);
	}
	//----------------------------------------------------------------------------------
//	public String getMessage() {
//		return command;
//	}
	//----------------------------------------------------------------------------------
//	public void setMessage(String message) {
//		this.command = message;
//	}
	//----------------------------------------------------------------------------------
	public MagicRootConnection getMagicRootConnection() {
		return magicRootConnection;
	}
	//----------------------------------------------------------------------------------
	public void setMagicRootConnection(MagicRootConnection magicRootConnection) {
		this.magicRootConnection = magicRootConnection;
	}
	//----------------------------------------------------------------------------------
}