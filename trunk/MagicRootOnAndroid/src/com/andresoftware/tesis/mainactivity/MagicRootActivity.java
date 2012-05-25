package com.andresoftware.tesis.mainactivity;
import com.andresoftware.tesis.chat.ChatView;
import com.andresoftware.tesis.tablero.MagicRootGame;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;

public class MagicRootActivity extends Activity {
	private MagicRootConnection magicRootConnection;
	private Thread thread;
	private ChatView chatView;
	protected static final int MSG_ID = 01337;
	String message="";
	private Handler myUpdateHandler = null;
	/** Called when the activity is first created. */
	//----------------------------------------------------------------------------------
	@Override
	public void onCreate(Bundle savedInstanceState) {        
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		changeViewToChat();
		initiHandler();
		connect();
		//		changeViewToMenu();
	}
	//----------------------------------------------------------------------------------
	private void initiHandler() {
		myUpdateHandler = new Handler() {
	        public void handleMessage(Message msg) {
	                switch (msg.what) {
	                case MSG_ID:
	                	chatView.addTextToChat(message);
	                        break;
	                default:
	                        break;
	                }
	                super.handleMessage(msg);
	        }
	   };
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
		chatView = new ChatView(this);
	}
	//		public void changeViewToMenu() {
	//			MenuView.initMenu(this);          
	//		}
	//----------------------------------------------------------------------------------
	public void changeViewToGamerRoom() {                           
		setContentView(new MagicRootGame(this, getBaseContext(), this));             
	}
	//----------------------------------------------------------------------------------
	@Override
	public void onBackPressed() {
		//			changeViewToMenu();
		finish();
		System.exit(0);
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
	public String getMessage() {
		return message;
	}
	//----------------------------------------------------------------------------------
	public void setMessage(String message) {
		this.message = message;
	}
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