package com.examples.dragdrop;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class Another_Example_DragDropActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // draw the view
        setContentView(new DrawView(this, getBaseContext(), this));
    }
    public void changeView() {
		setContentView(R.layout.main);
	}
}