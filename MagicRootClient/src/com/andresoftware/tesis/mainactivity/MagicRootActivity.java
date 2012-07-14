package com.andresoftware.tesis.mainactivity;
import com.andresoftware.tesis.chat.ChatView;
import com.andresoftware.tesis.commands.CommandCreateUserAnswer;
import com.andresoftware.tesis.commands.CommandLoginAnswer;
import com.andresoftware.tesis.commands.CommandPlayCards;
import com.andresoftware.tesis.commands.CommandUserInformation;
import com.andresoftware.tesis.constants.CurrentWindow;
import com.andresoftware.tesis.login.CreateAccountView;
import com.andresoftware.tesis.login.LoginView;
import com.andresoftware.tesis.login.PrincipalView;
import com.andresoftware.tesis.menu.MenuView;
import com.andresoftware.tesis.selectcardsview.SelectCardsViewBluePlayer;
import com.andresoftware.tesis.selectcardsview.SelectCardsViewRedPlayer;
import com.andresoftware.tesis.selectgameview.SelectGameView;
import com.andresoftware.tesis.tablero.MagicRootGame;
import com.andresoftware.tesis.user.UserInformation;
import commands.CrearCommand;
import commands.DesconectarInvitadoCommand;
import commands.DesconexionJugandoCommand;
import commands.EliminarCommand;
import commands.InvitadoDesconectadoCommand;
import commands.JugDesconectadoRoomCommand;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Display;
import android.view.Window;

public class MagicRootActivity extends Activity {
	private MagicRootConnection magicRootConnection;
	private ChatView chatView=null;
	private MenuView menuView = null;
	private LoginView loginView = null;
	private PrincipalView principalView = null;
	private CreateAccountView createAccountView = null;
	private SelectGameView selectGameView = null;
	private Handler myUpdateHandler = null;
	private CurrentWindow currentView = null;
	private UserInformation usrInformation;
	private SelectCardsViewBluePlayer selectCardsBlueView;
	private SelectCardsViewRedPlayer selectCardsRedView;
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
		}else if(command.startsWith(CommandUserInformation.CADENA_COMANDO)){
			startUsrInformation(command);
		}else if(currentView.equals(CurrentWindow.CHAT_VIEW)){
			chatView.addTextToChat(command);
		}else if(currentView.equals(CurrentWindow.SELECT_GAME_VIEW)&&command.startsWith(EliminarCommand.CADENA_COMANDO)){
			selectGameView.removeGameButton(command);
		}else if(currentView.equals(CurrentWindow.SELECT_GAME_VIEW)&&command.startsWith(CrearCommand.CADENA_COMANDO)){
			selectGameView.createNewGameButton(command);
		}else if(currentView.equals(CurrentWindow.CREATE_GAME_BLUE_VIEW) && command.startsWith("Te_llego_un_invidato")){
			selectCardsBlueView.processInvited();
		}else if(currentView.equals(CurrentWindow.JOIN_GAME_RED_VIEW)&&
				command.startsWith(JugDesconectadoRoomCommand.CADENA_COMANDO)){
			selectCardsRedView.backToSelectGame();
		}else if(currentView.equals(CurrentWindow.JOIN_GAME_RED_VIEW)&&
				command.startsWith("Se_desconecto_Creador")){
			selectCardsRedView.backToSelectGame2();
		}
		Log.d("MagicRoot Commands", command);

	}
	//----------------------------------------------------------------------------------
	private void startUsrInformation(String command) {
		CommandUserInformation commandUserInformation = new CommandUserInformation(command);
		Display display = getWindowManager().getDefaultDisplay();
		int width = display.getWidth();
		int height = display.getHeight();
		usrInformation.setUsrId(commandUserInformation.getId());
		usrInformation.setUsrName(commandUserInformation.getUserName());
		for(int i=0; i<commandUserInformation.getUserCards().getCardsList().size();i++){
			usrInformation.addCard(commandUserInformation.getUserCards().getCardsList().get(i), 
					width, height, this);
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
		usrInformation.setEnablesCards();
		currentView = CurrentWindow.TESTROOM_VIEW;
		setContentView(new SelectCardsViewBluePlayer(this, this, usrInformation));     
	}
	//----------------------------------------------------------------------------------
	public void changeViewToSelectCardsView() {
		CrearCommand createGame = new CrearCommand(usrInformation.getUsrId());
		getMagicRootConnection().sendCommandToServer(createGame.convertirAString());
		currentView = CurrentWindow.CREATE_GAME_BLUE_VIEW;
		usrInformation.setEnablesCards();
		selectCardsBlueView = new SelectCardsViewBluePlayer(this, this, usrInformation); 
		setContentView(selectCardsBlueView);     
	}
	//----------------------------------------------------------------------------------
	public void changeViewToSelectGameView() {
		currentView = CurrentWindow.SELECT_GAME_VIEW;
		selectGameView = new SelectGameView(this);

	}
	//----------------------------------------------------------------------------------
	public void changeViewToJoinGameView() {
		currentView = CurrentWindow.JOIN_GAME_RED_VIEW;
		usrInformation.setEnablesCards();
		selectCardsRedView = new SelectCardsViewRedPlayer(this, this, usrInformation);
		setContentView(selectCardsRedView);
		
	}
	//----------------------------------------------------------------------------------
	@Override
	public void onBackPressed() {
		if(currentView.equals(CurrentWindow.MENU_VIEW)){
			finish();
			System.exit(0);
		}
		else if(currentView.equals(CurrentWindow.LOGIN_VIEW) || currentView.equals(CurrentWindow.CREATEACCOUNT_VIEW)){
			changeViewToPrincipalView();
		}
		else if(currentView.equals(CurrentWindow.CREATE_GAME_BLUE_VIEW)){
			getMagicRootConnection().sendCommandToServer(JugDesconectadoRoomCommand.CADENA_COMANDO);
			changeViewToMenu();
		}
		else if(currentView.equals(CurrentWindow.PRINCIPAL_VIEW)){
			finish();
			System.exit(0);
		}
		else if(currentView.equals(CurrentWindow.SELECT_GAME_VIEW)){
			changeViewToMenu();
		}
		else{
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

}