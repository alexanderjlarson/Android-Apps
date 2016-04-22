package edu.lewisu.cs.alexlarson.courserating;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Entry extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.entry);
	}
	
	public void addReviewClick(View v){
		Intent launchSecond = new Intent(this, MainActivity.class);
		startActivity(launchSecond);
	}
	
	public void showReviewClick(View v){
		Intent launchThird = new Intent(this, ReviewActivity.class);
		startActivity(launchThird);
	}

}
