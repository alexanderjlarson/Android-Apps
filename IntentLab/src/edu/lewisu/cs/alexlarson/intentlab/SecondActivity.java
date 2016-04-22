package edu.lewisu.cs.alexlarson.intentlab;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;


public class SecondActivity extends Activity {

	TextView text;
	RatingBar bar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_two);
		text = (TextView) findViewById(R.id.textView1);
		Intent sender = getIntent();
		String name = sender.getStringExtra("Name");
		if(name!=null && name!=""){
		text.setText(name);
		}
		bar = (RatingBar) findViewById(R.id.ratingBar1);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public void submit(View v){
		int rating = (int)bar.getRating();
		Intent returnIntent = new Intent();
		returnIntent.putExtra("rating", rating);
		setResult(RESULT_OK,returnIntent);
		finish();
	}

}
