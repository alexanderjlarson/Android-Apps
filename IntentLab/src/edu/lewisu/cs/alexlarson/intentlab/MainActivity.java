package edu.lewisu.cs.alexlarson.intentlab;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class MainActivity extends Activity {

	final int SECOND_ID = 1;
	EditText enterName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		enterName = (EditText) findViewById(R.id.editText1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void buttonClick(View v){
		String name = enterName.getText().toString();
		Intent launchSecond =
				new Intent(this, SecondActivity.class);
		launchSecond.putExtra("Name", name);
		startActivityForResult(launchSecond, SECOND_ID);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode == RESULT_OK && requestCode == SECOND_ID){
			int rating = data.getIntExtra("rating", 0);
			Toast.makeText(getApplicationContext(),"returned " + rating,
					Toast.LENGTH_SHORT).show();
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	

}
