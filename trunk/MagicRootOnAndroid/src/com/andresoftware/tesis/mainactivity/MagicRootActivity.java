package com.andresoftware.tesis.mainactivity;
import com.andresoftware.tesis.chat.ChatView;
import com.andresoftware.tesis.commands.CommandCreateUserAnswer;
import com.andresoftware.tesis.commands.CommandLoginAnswer;
import com.andresoftware.tesis.commands.CommandPlayCards;
import com.andresoftware.tesis.login.CreateAccountView;
import com.andresoftware.tesis.login.LoginView;
import com.andresoftware.tesis.login.PrincipalView;
import com.andresoftware.tesis.menu.MenuView;
import com.andresoftware.tesis.selectcardsview.SelectCardsView;
import com.andresoftware.tesis.tablero.MagicRootGame;
import com.andresoftware.tesis.user.UserInformation;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.Window;

public class MagicRootActivity extends Activity {
	private MagicRootConnection magicRootConnection;
	private ChatView chatView=null;
	private MenuView menuView = null;
	private LoginView loginView = null;
	private PrincipalView principalView = null;
	private CreateAccountView createAccountView = null;
	private Handler myUpdateHandler = null;
	private CurrentWindow currentView = null;
	private UserInformation usrInformation;
	/** Called when the activity is first created. */
	//----------------------------------------------------------------------------------
	@Override
	public void onCreate(Bundle savedInstanceState) {        
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);	
		usrInformation = new UserInformation();
		initiHandler();
		connect();
		changeViewToPrincipalView();
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
		
		// Interpreto el comando y lo proceso
		if(command.startsWith(CommandLoginAnswer.CADENA_COMANDO)
				&& currentView.equals(CurrentWindow.LOGIN_VIEW)){
			loginView.processLogin(command);
		}else if(command.startsWith(CommandCreateUserAnswer.CADENA_COMANDO) 
				&& currentView.equals(CurrentWindow.CREATEACCOUNT_VIEW)){
			createAccountView.processLogin(command);
		}else if(command.startsWith(CommandPlayCards.CADENA_COMANDO)){
			startUsrInformation(command);
		}else if(currentView.equals(CurrentWindow.CHAT_VIEW)){
			chatView.addTextToChat(command);
		}
	}
	//----------------------------------------------------------------------------------
	private void startUsrInformation(String command) {
		CommandPlayCards commandPlayCard = new CommandPlayCards(command);
		for(int i=0; i<commandPlayCard.getCardsList().size();i++){
			Display display = getWindowManager().getDefaultDisplay();
			int width = display.getWidth();
			int height = display.getHeight();
			usrInformation.addCard(commandPlayCard.getCardsList().get(i), width, height, this);
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
		currentView = CurrentWindow.MENU_VIEW;
		menuView = new MenuView(this);          
	}
	//----------------------------------------------------------------------------------
	public void changeViewToGamerRoom() {   
		currentView = CurrentWindow.PLAYNOW_VIEW;
		setContentView(new MagicRootGame(this, getBaseContext(), this));             
	}
	//----------------------------------------------------------------------------------
	public void changeViewToLoginView() {
		currentView = CurrentWindow.LOGIN_VIEW;
		loginView = new LoginView(this);
	}
	//----------------------------------------------------------------------------------
	public void changeViewToCreateAccountView() {
		currentView = CurrentWindow.CREATEACCOUNT_VIEW;
		createAccountView = new CreateAccountView(this);
	}
	//----------------------------------------------------------------------------------
	public void changeViewToPrincipalView() {
		currentView = CurrentWindow.PRINCIPAL_VIEW;
		principalView = new PrincipalView(this);
	}
	//----------------------------------------------------------------------------------
	public void changeViewToTestRoom() {
		currentView = CurrentWindow.TESTROOM_VIEW;
		setContentView(new SelectCardsView(this, this, usrInformation));     
	}
	//----------------------------------------------------------------------------------
	@Override
	public void onBackPressed() {
		if(currentView.equals(CurrentWindow.MENU_VIEW)){
			finish();
			System.exit(0);
		}else if(currentView.equals(CurrentWindow.LOGIN_VIEW) || currentView.equals(CurrentWindow.CREATEACCOUNT_VIEW)){
			changeViewToPrincipalView();
		}else if(currentView.equals(CurrentWindow.PRINCIPAL_VIEW)){
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
	public UserInformation getUsrInformation() {
		return usrInformation;
	}
	//----------------------------------------------------------------------------------
	public void setUsrInformation(UserInformation usrInformation) {
		this.usrInformation = usrInformation;
	}
	//----------------------------------------------------------------------------------
	
}