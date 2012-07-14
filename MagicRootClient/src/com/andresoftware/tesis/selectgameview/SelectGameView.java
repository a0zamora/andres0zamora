package com.andresoftware.tesis.selectgameview;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.andresoftware.tesis.mainactivity.MagicRootActivity;

import com.andresoftware.tesis.commands.CommandUserInformation;
import com.andresoftware.tesis.gen.*;

import commands.Command;
import commands.CrearCommand;
import commands.EliminarCommand;
import commands.UnirCommand;

public class SelectGameView{

	private MagicRootActivity mgrt;
	private List<Button> buttonList = new ArrayList<Button>();
	//----------------------------------------------------------------------------------
	public SelectGameView(MagicRootActivity mgrt) {
		mgrt.setContentView(R.layout.joingameview);
		this.mgrt = mgrt;
		mgrt.getMagicRootConnection().sendCommandToServer("mostrar_partidas");
	}
	//----------------------------------------------------------------------------------
	public void createNewGameButton(String command) {
		CrearCommand createGame = new CrearCommand(command);
		for(Button but : buttonList){
			if(but.getId()==createGame.getId())
				return;
		}
		LinearLayout linearLayout = (LinearLayout) mgrt.findViewById(R.id.linearLayout1);
		final Button button = new Button(mgrt.getBaseContext());
		button.setText(createGame.getUsrName());
		button.setId(createGame.getId());
		button.setGravity(20);
		linearLayout.addView(button);
		buttonList.add(button);
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				mgrt.getMagicRootConnection().sendCommandToServer(UnirCommand.CADENA_COMANDO+" "+button.getId());
				mgrt.changeViewToJoinGameView();
			}
		});
		
	}
	//----------------------------------------------------------------------------------
	public void removeGameButton(String command) {
		
		EliminarCommand deleteGame = new EliminarCommand(command);
		LinearLayout linearLayout = (LinearLayout) mgrt.findViewById(R.id.linearLayout1);
		for(Button button : buttonList){
			if(button.getId() == deleteGame.getId()){
				linearLayout.removeView(button);
				buttonList.remove(button);
				break;
			}
		}
	}
}
