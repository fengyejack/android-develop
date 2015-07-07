package com.example.bluetooth_1;



import android.support.v7.app.ActionBarActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
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
	
/*	private final BroadcastReceiver mReceiver=new BroadcastReceiver(){
        public void onReceive (Context context,Intent intent) {
        String action =intent.getAction();
        // When discovery finds a device
        if(BluetoothDevice.ACTION_FOUND.equals(action))
        {
        // Get the BluetoothDevice object from the Intent
        BluetoothDevice device=intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
       
        mArrayAdapter.add(device.getName()+"\n"+device.getAddress());
        }
        }
        };
          IntentFilter filter =new IntentFilter(BluetoothDevice.ACTION_FOUND);
	        registerReceiver(mReceiver,filter);
        */
        // Register the BroadcastReceiver
	 

    // registerReceiver(mReceiver,filter);
       
        
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		BluetoothAdapter btAdapter= BluetoothAdapter.getDefaultAdapter();
		if(btAdapter==null) {
		/*Inform user that device does not support Bluetooth
		*/
		}
		 Button clickMeBtn = (Button) findViewById(R.id.button1);
	        clickMeBtn.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View v) {
	        myClick(v);
			
	        }
	       });
	       
	      
		//if(!btAdapter.isEnabled()) {
		//	btAdapter.enable(); 
		//}
	        
	        
		
		
		
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
	
		 BluetoothAdapter btAdapter= BluetoothAdapter.getDefaultAdapter();
		 String result = null;
		 if(!btAdapter.isEnabled()){
			 result ="BLUETOOTH IS NOT ENABLED";
		 }
		 else{
			 result ="BLUETOOTH IS ENABLED";
		 }
			
			 TextView txCounter = (TextView) findViewById(R.id.textView1);
			
		        txCounter.setText(result);
		    }
	
	 
	
	 
	
}