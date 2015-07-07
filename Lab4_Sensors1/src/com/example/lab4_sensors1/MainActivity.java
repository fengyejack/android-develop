package com.example.lab4_sensors1;

import java.util.ArrayList;
import java.util.List;


import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

@SuppressLint("NewApi") public class MainActivity extends ActionBarActivity {

	Button bt1;
	TextView tv1;
	ListView lv1;
	SensorManager sm;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bt1 = (Button) findViewById(R.id.button1);
		tv1 = (TextView) findViewById(R.id.textView1);
		lv1 = (ListView) findViewById(R.id.listView1);

		sm= (SensorManager)getSystemService	(SENSOR_SERVICE);
		final List<Sensor> sensor_list = sm.getSensorList(Sensor.TYPE_ALL);

		bt1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ShowSensors(sensor_list);
			}
		});
	}

	private void ShowSensors(List<Sensor> sensor_list) {
		// TODO Auto-generated method stub

		ArrayList<String> SensorsList = new ArrayList<String>();	
		for(int i=0; i<sensor_list.size();i++){
			SensorsList.add(i+") Name: "+sensor_list.get(i).getName()+"\n"
					+"Vendor: "+sensor_list.get(i).getVendor()+"\n"
					+"Version: "+sensor_list.get(i).getVersion() +"\n"
					+"MaximumRange: "+sensor_list.get(i).getMaximumRange() +"\n"
					+"MinDelay: "+ sensor_list.get(i).getMinDelay() );
		}
		ArrayAdapter<String> AD = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, SensorsList); 
		lv1.setAdapter(AD);
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
}
