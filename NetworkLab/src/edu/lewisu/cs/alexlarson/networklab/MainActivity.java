package edu.lewisu.cs.alexlarson.networklab;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	EditText editText;
	TextView textView;
	//Button btn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		editText = (EditText) findViewById(R.id.editText1);
		textView = (TextView) findViewById(R.id.textView1);
		//btn = (Button) findViewById(R.id.button1);

		
	}
	
	public void buttonClick(View v){
		String url = editText.getText().toString();
		new DownloadSite().execute(url);
		editText.setText("");
	}
	
	private class DownloadSite extends AsyncTask<String, Void, String>{

		@Override
		protected void onPostExecute(String result) {
			textView.setText(Html.fromHtml(result));
		}

		@Override
		protected String doInBackground(String... urls) {
			try{
				URL url = new URL(urls[0]);
				URLConnection conn = url.openConnection();
				BufferedReader rd =
						new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String line = "";
				String html = "";
				while((line = rd.readLine()) != null){
					html = html + line;
				}
				return html;
			}
			catch(Exception e){
				Log.e("error", e.toString());
			}
			return null;
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
