package com.example.battery;




import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		
	        Button clickMeBtn = (Button) findViewById(R.id.button1);
	        clickMeBtn.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View v) {
	        myClick(v);
	        /* my method to call new intent or activity
	        */
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
	 public void myClick(View v) {
		 Intent batteryStatus;
			IntentFilter ifilter =new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
			
		 batteryStatus=this.registerReceiver(null, ifilter);
		 int level =batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL,-1);
			int scale =batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE,-1);
			float batteryPct= level / (float)scale;
			int plugged = batteryStatus.getIntExtra("plugged",0);
			String plug = null;
			if(plugged == 2){
				plug = "USB";
			}
			if(plugged == 1){
				plug = "AC";
			}
			if(plugged == 3){
				plug = "wireless";
			}
			
			
			 TextView txCounter = (TextView) findViewById(R.id.textView1);
			 String result = "Current level of battery is" + batteryPct*100 + "%\n"+
					 		"Mobile is charginf via " + plug;
			 
		        txCounter.setText(result);
		    }
}
