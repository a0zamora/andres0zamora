package com.andresoftware.tesis.login;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.opengl.Visibility;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.andresoftware.tesis.commands.CommandLogin;
import com.andresoftware.tesis.commands.CommandLoginAnswer;
import com.andresoftware.tesis.gen.R;
import com.andresoftware.tesis.mainactivity.MagicRootActivity;

public class LoginView {
	MagicRootActivity main = null;
	public LoginView(final MagicRootActivity magicRootActivity) {
		magicRootActivity.setContentView(R.layout.loginview);
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
			AlertDialog alertDialog = new AlertDialog.Builder(
					main).create();

			// Setting Dialog Title
			alertDialog.setTitle("Success!");

			// Setting Dialog Message
			alertDialog.setMessage("Welcome to MagicRoot!!");

			// Setting Icon to Dialog
			alertDialog.setIcon(R.drawable.success_icon);

			// Setting OK Button
			alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					// Write your code here to execute after dialog closed
				}
			});

			// Showing Alert Message
			alertDialog.show();
			main.changeViewToMenu();
		}else{

			AlertDialog alertDialog = new AlertDialog.Builder(
					main).create();

			// Setting Dialog Title
			alertDialog.setTitle("Error!");

			// Setting Dialog Message
			alertDialog.setMessage("username or password incorrect");

			// Setting Icon to Dialog
			alertDialog.setIcon(R.drawable.error);

			// Setting OK Button
			alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					// Write your code here to execute after dialog closed
				}
			});

			// Showing Alert Message
			alertDialog.show();
		}
		
	}

}
