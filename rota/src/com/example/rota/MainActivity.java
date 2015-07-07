package com.example.rota;


import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener {

	private SensorManager mSensorManager;
	private Sensor gyroSensor;
	TextView textView1;

	double timeStamp;
	double sum;
	
	boolean started = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		gyroSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
		
		textView1 = (TextView) findViewById(R.id.textView1);
		Button button1 = (Button) findViewById(R.id.button1);
		Button button2 = (Button) findViewById(R.id.button2);
		
		
		
		mSensorManager.registerListener( this, gyroSensor,SensorManager.SENSOR_DELAY_NORMAL);
		
		
		button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if (started)
					return;
				timeStamp = System.currentTimeMillis();
				sum = 0;
				started = true;
			}
		});
		
		button2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (!started)
					return;
				
				double angle = sum * 180 / Math.PI;
				
				textView1.setText(String.valueOf(angle));
				started = false;
			}
		});
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent arg0) {
		if (!started)
			return;
		double z = arg0.values[2];
		
		double currentTime = System.currentTimeMillis();
		sum += z * ((currentTime - timeStamp) / 1000);
		timeStamp = currentTime;
		textView1.setText(String.valueOf(-sum * 180 / Math.PI));
	}

}
