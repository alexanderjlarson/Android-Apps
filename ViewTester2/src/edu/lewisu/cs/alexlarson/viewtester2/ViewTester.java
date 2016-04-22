package edu.lewisu.cs.alexlarson.viewtester2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

public class ViewTester extends Activity {

	RatingBar bar;
	TextView label;
	EditText edit;
	Spinner spin;
	int defaultRating;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_tester);
		bar = (RatingBar) findViewById(R.id.ratings);
		RatingHandler rHandler = new RatingHandler();
		bar.setOnRatingBarChangeListener(rHandler);
		label = (TextView) findViewById(R.id.input);
		edit = (EditText) findViewById(R.id.textView1);
		edit.setOnEditorActionListener(new TextHandler());
		spin = (Spinner) findViewById(R.id.spinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.colors, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spin.setAdapter(adapter);
		spin.setOnItemSelectedListener(new SpinnerHandler());
		SharedPreferences preferences = getPreferences (MODE_PRIVATE);
		bar.setRating(preferences.getInt("stars", 0));
		
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		//This method handles the selection on the menu
		if(item.getItemId()== R.id.defaultRating) {
			AlertDialog.Builder builder = new //Creates a new alert dialog
					AlertDialog.Builder(this);
			builder.setTitle(R.string.default_choice); //Sets the menu title to what is in default_choices
			builder.setItems(R.array.ratings, //Sets the items on the list to the items in the ratings array
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int item) { //Does this when an item is clicked on
							defaultRating= item+1;
							bar.setRating(defaultRating);
						}
			});
			
			AlertDialog alert = builder.create();
			alert.show();
			return true;
		}else {
			return super.onOptionsItemSelected(item);
		}
		
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		SharedPreferences prefs = 
				getPreferences(MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putInt("stars", defaultRating);
		editor.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.options_menu, menu);
		return true;
	}
	
	private class TextHandler implements OnEditorActionListener{

		@Override
		public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
			boolean handled = false;
			if (actionId == EditorInfo.IME_ACTION_SEND){
				String labelText = v.getText().toString();
				label.setText(labelText);
				handled = true;
			}
			return handled;
		}
		
	}
	
	private class RatingHandler implements OnRatingBarChangeListener{

		@Override
		public void onRatingChanged(RatingBar bar, float rating, boolean fromUser) {
			String text = rating + " stars";
			Toast t = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
			t.show();
			
		}
		
	}
	
	private class SpinnerHandler implements OnItemSelectedListener{

		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int pos,
				long id) {
			String color = (String) parent.getItemAtPosition(pos);
			Toast.makeText(getApplicationContext(), color,
					Toast.LENGTH_SHORT).show();
			
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			
			
		}
		
	}

}
