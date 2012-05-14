package com.andresoftware.tesis.menu;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.andresoftware.tesis.gen.R;
import com.andresoftware.tesis.mainactivity.MagicRootActivity;
import com.andresoftware.tesis.tablero.MagicRootGame;

public class MenuView {
public static void initMenu(final MagicRootActivity main) {
	main.setContentView(R.layout.menuview);
	Button buttonGamerRoom = (Button) main.findViewById(R.id.button1);
	buttonGamerRoom.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			main.chageViewToGamerRoom();
		}
	});
	Button buttonChat = (Button) main.findViewById(R.id.button4);
	buttonChat.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			main.changeViewToChat();
		}
	});
//	LinearLayout linearLayout = (LinearLayout) main.findViewById(R.id.LinearLayout1);
//	linearLayout.addView(new MagicRootTable(main, main.getBaseContext(), main));
}
}
