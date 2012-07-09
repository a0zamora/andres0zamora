package com.andresoftware.tesis.login;

import java.security.spec.MGF1ParameterSpec;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.andresoftware.tesis.commands.CommandCreateUser;
import com.andresoftware.tesis.commands.CommandCreateUserAnswer;
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
					CommandCreateUser newUser = new CommandCreateUser(CommandCreateUser.CADENA_COMANDO+" "+name.getText().toString()
							+" "+pass.getText().toString());
					magicRootActivity.getMagicRootConnection().sendCommandToServer(newUser.convertirAString());
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
		CommandCreateUserAnswer answer = new CommandCreateUserAnswer(command);
		
		if(answer.getAnswer()){
			AlertDialog alertDialog = new AlertDialog.Builder(
					main).create();

			// Setting Dialog Title
			alertDialog.setTitle("Success!");

			// Setting Dialog Message
			alertDialog.setMessage("new user registration was successful");

			// Setting Icon to Dialog
			alertDialog.setIcon(R.drawable.success_icon);

			// Setting OK Button
			alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					main.changeViewToPrincipalView();
				}
			});

			// Showing Alert Message
			alertDialog.show();
		}else{
			AlertDialog alertDialog = new AlertDialog.Builder(
					main).create();

			// Setting Dialog Title
			alertDialog.setTitle("Error!");

			// Setting Dialog Message
			alertDialog.setMessage("The user name already exists");

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
