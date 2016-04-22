package edu.lewisu.cs.laurenbonano.writersblock;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void braindumpOnClick(View v){  
		Intent launchSecond = new Intent (this, BraindumpActivity.class);
		startActivity (launchSecond);
	}	
	
	public void storyStarterOnClick(View v){  
		Intent launchThird = new Intent (this, StoryStarterActivity.class);
		startActivity (launchThird);		
	}	
	
	public void pictureStoryOnClick(View v){  
		Intent launchForth = new Intent (this, PictureStoryActivity.class);
		startActivity (launchForth);
	}	
	
	public void tipsOnClick(View v){  
		Intent launchFifth = new Intent (this, TipsActivity.class);
		startActivity (launchFifth);		
	}	

}
