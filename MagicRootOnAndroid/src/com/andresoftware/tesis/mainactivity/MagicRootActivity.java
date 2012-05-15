package com.andresoftware.tesis.mainactivity;

import com.andresoftware.tesis.chat.ChatView;
import com.andresoftware.tesis.menu.MenuView;
import com.andresoftware.tesis.tablero.MagicRootGame;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class MagicRootActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {        
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        changeViewToMenu();
    }
    public void changeViewToChat() {
		ChatView.initChat(this);
	}
    public void changeViewToMenu() {
    	MenuView.initMenu(this);          
	}
    public void chageViewToGamerRoom() {                           
    	setContentView(new MagicRootGame(this, getBaseContext(), this));             
	}
}