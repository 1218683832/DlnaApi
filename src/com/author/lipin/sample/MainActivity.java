package com.author.lipin.sample;

import com.author.lipin.dlna.R;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity{
	
	protected static final String TAG = MainActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_main);
	}
}
