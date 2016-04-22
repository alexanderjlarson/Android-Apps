package edu.lewisu.cs.laurenbonano.writersblock;

import java.util.Random;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class PictureStoryActivity extends Activity {
	ImageView image;
	int choice = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.picture_story);
		setTitle("Picture Story");
		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		image = (ImageView)findViewById(R.id.randomImage);
		Cursor allImages = getContentResolver().query(CProvider.PICS_URI,null,null,null,null);
		allImages.moveToFirst();
		String pictureName = allImages.getString(0);
		int drawableResourceld = this.getResources().getIdentifier(pictureName, "drawable", this.getPackageName());
		image.setImageResource(drawableResourceld);
		
	}
	
	public void newOnClick(View v){	
		Cursor allImages = getContentResolver().query(CProvider.PICS_URI,null,null,null,null);
		int numPictures = allImages.getCount();
		int i = 0;
		String[] picture = new String[numPictures];
		
		while(allImages.moveToNext()){
			picture[i] = allImages.getString(0);			
			i++;			
		}
		if(choice > 4){
			choice = 0;
		}
		int drawableResourceld = this.getResources().getIdentifier(picture[choice], "drawable", this.getPackageName());
		image.setImageResource(drawableResourceld);
		choice++;
				
	}
}
