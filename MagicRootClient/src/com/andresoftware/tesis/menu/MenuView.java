package com.andresoftware.tesis.menu;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.andresoftware.tesis.gen.R;
import com.andresoftware.tesis.mainactivity.MagicRootActivity;

public class MenuView {
public MenuView(final MagicRootActivity main) {
	main.setContentView(R.layout.menuview);
	Button buttonGamerRoom = (Button) main.findViewById(R.id.button1);
	buttonGamerRoom.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			main.changeViewToGamerRoom();
		}
	});
	Button buttonChat = (Button) main.findViewById(R.id.button4);
	buttonChat.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			main.changeViewToChat();
		}
	});
	Button buttonTestRoom = (Button) main.findViewById(R.id.testRoomButton);
	buttonTestRoom.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			main.changeViewToTestRoom();
		}
	});
	ImageView img = (ImageView) main.findViewById(R.id.imageView1);
	img.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Toast.makeText(main.getBaseContext(), "MagicRoot", 
					Toast.LENGTH_LONG).show();
		}
	});
	Button buttonCreateGame = (Button) main.findViewById(R.id.create_game);
	buttonCreateGame.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			main.changeViewToSelectCardsView();
		}
	});
	Button buttonJoinGame = (Button) main.findViewById(R.id.joingame);
	buttonJoinGame.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			main.changeViewToSelectGameView();
		}
	});
	
	
//	LinearLayout linearLayout = (LinearLayout) main.findViewById(R.id.LinearLayout1);
//	linearLayout.addView(new MagicRootTable(main, main.getBaseContext(), main));
}
}
