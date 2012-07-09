package com.andresoftware.tesis.utils;

import android.app.AlertDialog;
import android.content.DialogInterface;

import com.andresoftware.tesis.gen.R;
import com.andresoftware.tesis.mainactivity.MagicRootActivity;
import com.andresoftware.tesis.selectcardsview.SelectCardsViewBluePlayer;
import com.andresoftware.tesis.selectgameview.SelectGameView;

public class DialogMagicroot {
	MagicRootActivity mgrt;
	SelectCardsViewBluePlayer selectCards;
	public DialogMagicroot(MagicRootActivity mgrt, SelectCardsViewBluePlayer selectCards) {
		this.mgrt = mgrt;
		this.selectCards = this.selectCards;
	}
	
	public void dialogNewUser() {
		AlertDialog alertDialog = new AlertDialog.Builder(mgrt).create();

		// Setting Dialog Title
		alertDialog.setTitle("New User");

		// Setting Dialog Message
		alertDialog.setMessage("A user has challenged you to Play");

		// Setting Icon to Dialog
		alertDialog.setIcon(R.drawable.newuser);

		// Setting OK Button
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
			}
		});

		// Showing Alert Message
		alertDialog.show();
	}
}
