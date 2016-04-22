package edu.lewisu.cs.android.alexlarson.viewtester;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends Activity{
	
	TextView label;
	EditText input;
	Button mainButton;
	ToggleButton toggle;
	CheckBox checkbox1;
	CheckBox checkbox2;
	CheckBox checkbox3;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		checkbox1 = (CheckBox) findViewById(R.id.checkBox1);
		checkbox2 = (CheckBox) findViewById(R.id.checkBox2);
		checkbox3 = (CheckBox) findViewById(R.id.checkBox3);
		label = (TextView) findViewById(R.id.tv);
		input = (EditText) findViewById(R.id.input);
		mainButton = (Button) findViewById(R.id.main_button);
		toggle = (ToggleButton) findViewById(R.id.toggleButton1);
		ClickListener buttonListener = new ClickListener();
		mainButton.setOnClickListener(buttonListener);
		toggle.setOnClickListener(toggleListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private class ClickListener implements OnClickListener{
		
		public void onClick(View v){
			String text = input.getText().toString();
			label.setText(text);
		}
		
	}
	
	OnClickListener toggleListener =
			new OnClickListener(){
				public void onClick(View v){
					String text;
					Context context = getApplicationContext();
					
					if(toggle.isChecked()){
						text = "Ringer is on";
					}else{
						text = "Ringer is off";
					}
					Toast t = Toast.makeText(context, text, Toast.LENGTH_SHORT);
					t.show();
				}
	};
	public void radioHandler(View v){
		RadioButton b = (RadioButton)(v);
		String text = b.getText().toString();
		Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
	}
	
	public void isChecked(View v){
		
		String text = "";
		if(checkbox1.isChecked()){
			if(checkbox2.isChecked()){
				if(checkbox3.isChecked()){
					text = "Box 1, Box 2, and Box 3 are checked";
				}
				else{
					text = "Box 1 and Box 2 are checked";
				}
			}
			else if(checkbox3.isChecked()){
				text = "Box 1 and Box 3 are checked";
			}
			else{
				text = "Box 1 is checked";
			}
		}else if(checkbox2.isChecked()){
			if(checkbox3.isChecked()){
				text = "Box 2 and Box 3 are checked";
			}
			else{
				text = "Box 2 is checked";
			}
		}else if(checkbox3.isChecked()){
				text = "Box 3 is checked";
			
		}
		else{
			text = "No boxes are checked";
		}
		Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
	}

}
