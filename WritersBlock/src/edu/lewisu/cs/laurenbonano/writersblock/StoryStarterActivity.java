package edu.lewisu.cs.laurenbonano.writersblock;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class StoryStarterActivity extends Activity {

	Button subject;
	Button timeDate;
	Button verb;
	Button adjective;
	TextView viewSubject;
	TextView viewTimeDate;
	TextView viewVerb;
	TextView viewAdjective;
	EditText story;
	DbHelper dbHelper;
	CProvider cProvider;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.story_starter);
		setTitle("Story Starter");
		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		subject = (Button)findViewById(R.id.btnSubject);
		timeDate = (Button)findViewById(R.id.btnTimeDate);
		verb = (Button)findViewById(R.id.btnVerb);
		adjective = (Button)findViewById(R.id.btnAdjective);
		viewSubject = (TextView)findViewById(R.id.viewSubject);
		viewTimeDate = (TextView)findViewById(R.id.viewTimeDate);
		viewVerb = (TextView)findViewById(R.id.viewVerb);
		viewAdjective = (TextView)findViewById(R.id.viewAdjective);
		story = (EditText)findViewById(R.id.txtStory);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case R.id.menu_email:
			Intent i = new Intent(Intent.ACTION_SEND);
			i.setType("message/rfc822");
			i.putExtra(Intent.EXTRA_SUBJECT, "Story Starter");
			i.putExtra(Intent.EXTRA_TEXT, story.getText().toString());
			Intent mailer = Intent.createChooser(i, null);
			startActivity(mailer);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.email, menu);
		return true;
	}
	
	

	public void verbClick (View v){
		
		String[] verbsArray = new String[7];
		verbsArray[0] = "Sliced";
		verbsArray[1] = "Cross";
		verbsArray[2] = "Claim";
		verbsArray[3] = "Force";
		verbsArray[4] = "Identify";
		verbsArray[5] = "Hate";
		verbsArray[6] = "Judge";
		Random rnd = new Random();
		int i = rnd.nextInt(7);
		viewVerb.setText(verbsArray[i]);
		
	}
	
	public void adjectiveClick (View v){
		
		String[] adjectiveArray = new String[7];
		adjectiveArray[0] = "Shrill" ;
		adjectiveArray[1] = "Abandoned" ;
		adjectiveArray[2] = "Aggressive" ;
		adjectiveArray[3] = "Awkward" ;
		adjectiveArray[4] = "Deserted" ;
		adjectiveArray[5] = "Evil" ;
		adjectiveArray[6] = "Hallow" ;
		Random rnd = new Random();
		int i = rnd.nextInt(7);
		viewAdjective.setText(adjectiveArray[i]);
	}
	
	public void timedateClick (View v){
		
		String[] timedateArray = new String[7];
		timedateArray[0] = "Noon/Halloween";
		timedateArray[1] = "Midnight/New Years Eve";
		timedateArray[2] = "Early Afternoon/Summer";
		timedateArray[3] = "Sunrise/Spring";
		timedateArray[4] = "Dusk/4th of July";
		timedateArray[5] = "Morning/1st Day of School";
		timedateArray[6] = "Night/Birthday";
		Random rnd = new Random();
		int i = rnd.nextInt(7);
		viewTimeDate.setText(timedateArray[i]);
	}
	
	public void subjectClick (View v){
		
		String[] subjectArray = new String[7];
		subjectArray[0] = "Video Game";
		subjectArray[1] = "When I was lost";
		subjectArray[2] = "Apocalypse";
		subjectArray[3] = "Broken bone";
		subjectArray[4] = "Vacation";
		subjectArray[5] = "Graduation";
		subjectArray[6] = "Murder";
		Random rnd = new Random();
		int i = rnd.nextInt(7);
		viewSubject.setText(subjectArray[i]);
	}
}
