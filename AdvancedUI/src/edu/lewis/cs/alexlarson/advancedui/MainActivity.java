package edu.lewis.cs.alexlarson.advancedui;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;

public class MainActivity extends Activity {

	Fragment mainfrag;
	Fragment backfrag;
	Fragment tablefrag;
	
	Tab helloTab;
	Tab backgroundTab;
	Tab tableTab;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_holder);
		mainfrag = new MainFragment();
		backfrag = new BackgroundFragment();
		tablefrag = new TableFragment();
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		tabListener listener = new tabListener();
		
		helloTab = actionBar.newTab();
		helloTab.setText("Main");
		helloTab.setTabListener(listener);
		actionBar.addTab(helloTab);
		
		backgroundTab = actionBar.newTab();
		backgroundTab.setText("Background");
		backgroundTab.setTabListener(listener);
		actionBar.addTab(backgroundTab);
		
		tableTab = actionBar.newTab();
		tableTab.setText("Table");
		tableTab.setTabListener(listener);
		actionBar.addTab(tableTab);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	private class tabListener implements TabListener{
		
		@Override
		public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			if(tab==helloTab){
				ft.replace(R.id.fragment_container, mainfrag);
			}
			else if(tab==backgroundTab){
				ft.replace(R.id.fragment_container, backfrag);
			}
			else {
				ft.replace(R.id.fragment_container, tablefrag);
			}
			
		}

		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			if(tab==helloTab){
				ft.remove(mainfrag);
			}
			else if(tab==backgroundTab){
				ft.remove(backfrag);
			}
			else{
				ft.remove(tablefrag);
			}
			
		}
		
	}

}
