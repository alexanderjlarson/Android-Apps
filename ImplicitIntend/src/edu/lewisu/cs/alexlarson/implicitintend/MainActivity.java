package edu.lewisu.cs.alexlarson.implicitintend;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void openWebsite(View v){
		Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://cs.lewisu.edu"));
		startActivity(myIntent);
	}
	
	public void openContact(View v){
		Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people"));
		startActivity(myIntent);
	}
	
	public void openSearch(View v){
		Intent myIntent = new Intent(Intent.ACTION_WEB_SEARCH);
		myIntent.putExtra(SearchManager.QUERY, "android");
		startActivity(myIntent);
	}
	
	public void openCall(View v){
		Intent myIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:18158365145"));
		startActivity(myIntent);
	}

}
