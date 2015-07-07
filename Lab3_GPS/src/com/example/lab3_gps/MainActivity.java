package com.example.lab3_gps;

import java.sql.Date;
import java.text.SimpleDateFormat;

import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements LocationListener {
	
	Button bt1;
	Button bt2;
	TextView tv1;
	LocationManager LM;
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        bt1 = (Button) findViewById(R.id.button1);
        bt2 = (Button) findViewById(R.id.button2);
        tv1 = (TextView) findViewById(R.id.textView1);
        LM = (LocationManager)this.getSystemService(LOCATION_SERVICE);	
        final boolean isGPSEnabled = LM.isProviderEnabled(LocationManager.GPS_PROVIDER);
		//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);	

        
        if(isGPSEnabled){
        	tv1.setText("GPS Enabled");
        }else{
        	tv1.setText("GPS is not enabled");
        }

        
        bt1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GPSStatus(v, isGPSEnabled);
			}
		});
        bt2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GetLocation(v);
			}
		});
    }
    
    public void GPSStatus (View V, boolean enabled){
    	enabled= LM.isProviderEnabled(LocationManager.GPS_PROVIDER);
    	if(!enabled){
    		AlertDialog alertDialog = new AlertDialog.Builder(this)
    		.setTitle("GPS SETTING")
    		.setMessage("GPS is not enable. Press SETTING to open GPS.")
    		.setPositiveButton("BACK", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub

				}
			}).setNegativeButton("SETTING", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);	
					startActivityForResult(intent, 0);
				}
			}).create();
    		alertDialog.show();
    	}
    	else
    		tv1.setText("GPS Enabled Now");
    }
    
    public void GetLocation(View V){
		//Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);	
    	LM.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500, 1, this);
    	
    	Location location = LM.getLastKnownLocation(LocationManager.GPS_PROVIDER);
    	
    	
    	double Alt = location.getAltitude();
    	double Lgt = location.getLatitude();
    	float speed = location.getSpeed();
    	float accuracy = location.getAccuracy();
    	long time = location.getTime();
    	Date date = new Date(time);
    	SimpleDateFormat sdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String sdf = sdate.format(date);
    	
    	tv1.setText("Provider:GPS\n"+"Altitude: "+ Alt+"\n" + "Latitude: "+Lgt+"\n"+"Speed "+speed+"\n"+"Accuracy: "+accuracy+"\n"+"Time: "+sdf);
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

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
}
