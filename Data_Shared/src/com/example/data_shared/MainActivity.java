package com.example.data_shared;


import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	String str2;
	String str1;
	public boolean isExternalStorageWritable() { 
	   	 String state = Environment.getExternalStorageState(); 
	   	 if (Environment.MEDIA_MOUNTED.equals(state)) { 
	   	 return true; 
	   	 } 
	   	 return false; 
	   	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//str1="";  
        final EditText editText1 =(EditText)findViewById(R.id.editText1);  
        
        
       
        


       // TextView txCounter2 = (TextView) findViewById(R.id.textView2);
       // txCounter2.setText(str2);
        
        
        editText1.setOnEditorActionListener(new OnEditorActionListener() {  
 

			@Override
			public boolean onEditorAction(TextView arg0, int arg1, KeyEvent arg2) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, String.valueOf(arg1), Toast.LENGTH_SHORT).show(); 
				return false;
				
			}  
        });  
        
 
        Button getValue = (Button) findViewById(R.id.button1);
        getValue.setOnClickListener(new OnClickListener() {  

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				 str1=editText1.getText().toString();
					SharedPreferences preferences=getSharedPreferences("user",Context.MODE_PRIVATE);
			        Editor editor=preferences.edit();
			        editor.putString("str1", str1);
			        editor.commit();
			       
			}  
        });  
        
        Button getValue2 = (Button) findViewById(R.id.button2);
        getValue2.setOnClickListener(new OnClickListener() {  

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

					SharedPreferences preferences=getSharedPreferences("user",Context.MODE_PRIVATE);

			       str2=preferences.getString("str1", "defaultname");
				//Toast.makeText(MainActivity.this, str2, Toast.LENGTH_SHORT).show();
				TextView txCounter2 = (TextView) findViewById(R.id.textView2);
		        txCounter2.setText(str2);
			}  
        });  
        
        
        StatFs stat = new 
        		StatFs(Environment.getExternalStorageDirectory().getPath()); 
        		long bytesAvailable = bytesAvailable = (long)stat.getBlockSize() *(long)stat.getBlockCount();
        		long megAvailable = bytesAvailable / 1048576; 

        		TextView txCounter = (TextView) findViewById(R.id.textView1);
        if (isExternalStorageWritable()){
        	String size = "capacity of the storage :" + megAvailable + "MB";
        	
            txCounter.setText(size);
        } else{
        	String size = "No storage available. ";
        	
            txCounter.setText(size);
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
}
