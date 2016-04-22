package edu.lewisu.cs.alexlarson.fragmentlab;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		Configuration config = getResources().getConfiguration();
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		if (config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
			LMActivity lm_fragment = new LMActivity();
			fragmentTransaction.replace(android.R.id.content, lm_fragment);
		}else{
			PMActivity pm_fragment = new PMActivity();
			fragmentTransaction.replace(android.R.id.content, pm_fragment);
		}
		fragmentTransaction.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
