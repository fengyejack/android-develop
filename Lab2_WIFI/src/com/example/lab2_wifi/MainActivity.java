package com.example.lab2_wifi;


import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiConfiguration.KeyMgmt;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

	WifiManager WM;
	ListView LV1;
	TextView TV1;
	TextView TV01;
	Button btn1;
	Button btn2;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btn1 = (Button) findViewById(R.id.button1);
		btn2 = (Button) findViewById(R.id.button2);
		TV1 = (TextView) findViewById(R.id.textView1);
		TV01 = (TextView) findViewById(R.id.TextView01);
		LV1 = (ListView) findViewById(R.id.listView1);
		WM = (WifiManager) getSystemService(WIFI_SERVICE);

		btn1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				// TODO Auto-generated method stub

				WifiStatis(v);
				WifiScanner(v);
			}
		});
		btn2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				WifiStatis(v);
				WifiSort(v);
				
			}
		});
	}
	
	public void WifiSort(View v){
		WM.startScan();
		final List<ScanResult> SR = WM.getScanResults();
		ArrayList<String> RESULT = new ArrayList<String>();		
		
		String temp = new String();
		int level = Integer.MIN_VALUE;
		int[] levels = new int[4];
		String[] APs = new String[4];
		String AP = new String();
		
		for(int j=0; j<4; j++){
			level = Integer.MIN_VALUE;
			for(int i=0; i<SR.size(); i++){
				
				if(SR.get(i).level >= level){
					temp = SR.get(i).SSID;
					if(!temp.equals(APs[0]) && !temp.equals(APs[1])&&!temp.equals(APs[2])&&!temp.equals(APs[3])){
						level = SR.get(i).level;
						AP = temp;
					}
					//level = SR.get(i).level;
					
				}
				
			}
			APs[j] = AP;
			levels[j] = level;
		}
		
		for(int i=0; i<4; i++){
			RESULT.add(String.valueOf(i+1)+" "+APs[i]+" "+levels[i]);
		}
		
		ArrayAdapter<String> AD = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1,RESULT);
		LV1.setAdapter(AD);
		
		//click
		LV1.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
				String ConnectSSID = SR.get(arg2).SSID;
				ConnectWifi(ConnectSSID);
				 //startActivityForResult(new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS), 1);
			}
			
		});
	}

	public void WifiStatis(View V){
		TV1.setText("WIFI Enabled");
		if (!WM.isWifiEnabled())
			if (WM.getWifiState() != WifiManager.WIFI_STATE_ENABLING) 
				WM.setWifiEnabled(true);
	}

	public void WifiScanner(View V){
		WM.startScan();
		final List<ScanResult> SR = WM.getScanResults();
		ArrayList<String> RESULT = new ArrayList<String>();
		
		for(int i=0; i<SR.size(); i++){
			RESULT.add(String.valueOf(i+1)+" "+SR.get(i).SSID+" "+String.valueOf(SR.get(i).level));
		}
		
		ArrayAdapter<String> AD = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1,RESULT);
		LV1.setAdapter(AD);
		
		//Click
		LV1.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
				String ConnectSSID = SR.get(arg2).SSID;
				ConnectWifi(ConnectSSID);
				 //startActivityForResult(new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS), 1);
			}
			
		});
		

	}
	
	public void ConnectWifi(String SSID){
		WifiConfiguration config = new WifiConfiguration();
		config.SSID = "\""+SSID+"\"";
		config.allowedKeyManagement.set(KeyMgmt.NONE);
		config.status=WifiConfiguration.Status.ENABLED;
		WM.saveConfiguration();
		WM.disconnect();
		
		int netId=WM.addNetwork(config);
		WM.enableNetwork(netId, true);
		WM.reconnect();
		
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
