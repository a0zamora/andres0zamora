package com.andresoftware.tesis.chat;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;

import com.andresoftware.tesis.gen.R;
import com.andresoftware.tesis.mainactivity.MagicRootActivity;

public class ChatView {

	public static void initChat(final MagicRootActivity menu) {
		menu.setContentView(R.layout.chatview);

		Button btnOpen = (Button) menu.findViewById(R.id.button1);
		final EditText editText = (EditText) menu.findViewById(R.id.editText1);
		editText.append("Welcome to the MagicRoot Chat!!!\n\n\n\n\n");
		EditText editTextAux = (EditText) menu.findViewById(R.id.editText1);

		editTextAux.setFocusable(false);
		editTextAux.setClickable(false);
		
		final ScrollView scrollView = (ScrollView) menu.findViewById(R.id.ScrollView01);
		
		btnOpen.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

				EditText editText2 = (EditText) menu.findViewById(R.id.editText2);
				if(!editText2.getText().equals("")){

					editText.append(editText2.getText()+"\n");
					editText2.setText("");
					scrollView.post(new Runnable() { 
			            public void run() { 
			                scrollView.smoothScrollTo(0, editText.getBottom());
			            } 
			        }); 	
				}
				
			}
		});
		
		Button buttonExit = (Button) menu.findViewById(R.id.button2);
		buttonExit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

				menu.changeViewToMenu();
			}
		});
	}
	
}
