package edu.lewisu.cs.laurenbonano.writersblock;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class TipsActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tips);
		setTitle("Tips");
		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
	}
	
	public void openQuoteLink(View v){
		Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://flavorwire.com" +
				"/343207/13-famous-writers-on-overcoming-writers-block"));
		startActivity(myIntent);
	}

	public void openTypeLink(View v){
		Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://io9.com/5844988/" +
				"the-10-types-of-writers-block-and-how-to-overcome-them"));
		startActivity(myIntent);
	}
	
}
