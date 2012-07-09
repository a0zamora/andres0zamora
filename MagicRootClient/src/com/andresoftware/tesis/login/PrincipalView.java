package com.andresoftware.tesis.login;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.andresoftware.tesis.commands.CommandLogin;
import com.andresoftware.tesis.commands.CommandLoginAnswer;
import com.andresoftware.tesis.gen.R;
import com.andresoftware.tesis.mainactivity.MagicRootActivity;

public class PrincipalView {
	MagicRootActivity main = null;
	public PrincipalView(final MagicRootActivity magicRootActivity) {
		magicRootActivity.setContentView(R.layout.principalview);
		main = magicRootActivity;
		Button buttonToLogin = (Button) magicRootActivity.findViewById(R.id.button1);
		buttonToLogin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				magicRootActivity.changeViewToLoginView();
			}
		});
		
		Button buttonToCreateAccount = (Button) magicRootActivity.findViewById(R.id.button2);
		buttonToCreateAccount.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				magicRootActivity.changeViewToCreateAccountView();
			}
		});
		
		
	}
	
}
