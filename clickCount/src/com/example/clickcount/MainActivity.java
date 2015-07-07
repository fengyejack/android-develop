package com.example.clickcount;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import android.app.Activity; 
import android.view.View; 
import android.widget.Button; 
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
	private long counter; 

	public void myClick(View v) { 
		 TextView txCounter = (TextView) findViewById(R.id.textView1); 
		 txCounter.setText("yourtext"); 
		 } 

	
    @Override
    
    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        counter = 0; 
        TextView txCounter = (TextView) findViewById(R.id.textView1); 
        txCounter.setText(String.valueOf(counter)); 
        Button clickMeBtn = (Button) findViewById(R.id.button1); 
        clickMeBtn.setOnClickListener(new View.OnClickListener() { 
        public void onClick(View v) { 
        myClick(v); /* my method to call new intent or activity 
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
}
