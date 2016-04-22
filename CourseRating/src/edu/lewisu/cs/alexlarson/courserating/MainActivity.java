package edu.lewisu.cs.alexlarson.courserating;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;

public class MainActivity extends Activity {
	
	DataHelper dbHelper;
	EditText CourseName;
	EditText TeacherName;
	EditText Comments;
	RatingBar bar;
	RadioButton genEd;
	RadioButton mjr;
	RadioButton elect;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CourseName = (EditText) findViewById(R.id.crsName);
        TeacherName = (EditText) findViewById(R.id.comments);
        Comments = (EditText) findViewById(R.id.teachName);
        bar = (RatingBar) findViewById(R.id.crsRating);
        genEd = (RadioButton) findViewById(R.id.genEdRadio);
        mjr = (RadioButton) findViewById(R.id.majorRadio);
        elect = (RadioButton) findViewById(R.id.electiveRadio);
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void submitClick(View v){
    	String major = "";
    	AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
    	String course = CourseName.getText().toString();
    	String teacher = TeacherName.getText().toString();
    	String comment = Comments.getText().toString();
    	if (genEd.isChecked()){
    		major = "General Education Course";
    	}
    	else if (mjr.isChecked()){
    		major = "Major Course";
    	}
    	else if (elect.isChecked()){
    		major = "Elective Course";
    	}
    	float rating = (float)bar.getRating();
    	ContentValues values = new ContentValues();
		
		values.put(ReviewTable.COL_COURSE, course);
		values.put(ReviewTable.COL_COMMENTS, comment);
		values.put(ReviewTable.COL_INSTRUCTOR, teacher);
		values.put(ReviewTable.COL_COURSE_TYPE, major);
		values.put(ReviewTable.COL_RATING, rating);
		Uri result = getContentResolver().insert(DBProvider.CONTENT_URI, values);
    	//String message = ("Course Name: " + course + "\nTeacher Name: " + teacher + "\nComments: " + comment + "\n" + major + "\nRating: " + rating);
		//String message = (result.toString());
		builder.setMessage(result.toString());
    	builder.setCancelable(false);
    	builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
    		public void onClick(DialogInterface dialog, int id){
    		CourseName.setText("");
    		TeacherName.setText("");
    		Comments.setText("");
    		bar.setRating(0);
    		genEd.setChecked(false);
    		mjr.setChecked(false);
    		elect.setChecked(false);
    		finish();
    		}
    	});
    	AlertDialog alert = builder.create();
		alert.show();
    }
    
    
}
