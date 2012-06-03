package com.andresoftware.tesis.login;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.andresoftware.tesis.commands.CommandLogin;
import com.andresoftware.tesis.commands.CommandLoginAnswer;
import com.andresoftware.tesis.gen.R;
import com.andresoftware.tesis.mainactivity.MagicRootActivity;

public class CreateAccountView {
	MagicRootActivity main = null;
	public CreateAccountView(final MagicRootActivity magicRootActivity) {
		magicRootActivity.setContentView(R.layout.createaccountview);
		main = magicRootActivity;
		Button buttonGamerRoom = (Button) magicRootActivity.findViewById(R.id.button1);
		buttonGamerRoom.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				EditText name = (EditText) magicRootActivity.findViewById(R.id.editText1);
				EditText pass = (EditText) magicRootActivity.findViewById(R.id.editText2);
				if(!name.getText().toString().equals("") && !pass.getText().toString().equals("")){
					CommandLogin login = new CommandLogin(CommandLogin.CADENA_COMANDO+" "+name.getText().toString()
							+" "+pass.getText().toString());
					magicRootActivity.getMagicRootConnection().sendCommandToServer(login.convertirAString());
				}else{
					Toast.makeText(main.getBaseContext(), "both fields can not be empty", 
							Toast.LENGTH_LONG).show();
				}
			}
		});
		final EditText name = (EditText) magicRootActivity.findViewById(R.id.editText1);
		name.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				name.setText("");
			}
		});
		
		final EditText pass = (EditText) magicRootActivity.findViewById(R.id.editText2);
		pass.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				pass.setText("");
			}
		});
		
		
	}
	
	public void processLogin(String command) {
		CommandLoginAnswer login = new CommandLoginAnswer(command);
		if(login.isMember()){
			Toast.makeText(main.getBaseContext(), "Es Miembro", 
					Toast.LENGTH_LONG).show();
		}else{
			Toast.makeText(main.getBaseContext(), "No es Miembro", 
					Toast.LENGTH_LONG).show();
		}
	}
}
