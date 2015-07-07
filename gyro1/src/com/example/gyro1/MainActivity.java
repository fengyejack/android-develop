package com.example.gyro1;



import java.util.List;



import android.content.Context;
import android.hardware.GeomagneticField;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements SensorEventListener{
	List<Sensor> sensorList;
	private Sensor mLight;
	private Sensor mg;
	private double gravity[] = {0,0,0};
	private double linear_acceleration[] = {0,0,0};

	private static final float NS2S = 1.0f / 1000000000.0f;
    private final float[] deltaRotationVector = new float[4];
    private float timestamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
       /* LocationManager locm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        Location loc=locm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        GeomagneticField geoField;
        geoField = new GeomagneticField(
        Double.valueOf(loc.getLatitude()).floatValue(),
        Double.valueOf(loc.getLongitude()).floatValue(),
        Double.valueOf(loc.getAltitude()).floatValue(),
        System.currentTimeMillis()
        );*/
        
        
        SensorManager sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        //sensorList = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
        mLight = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        mg = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        sensorManager.registerListener(this, mLight, SensorManager.SENSOR_DELAY_NORMAL);
        //sensorManager.registerListener(this, mg. SensorManager.SENSOR_DELAY_NORMAL);
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
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		double x = event.values[0];
	    double y = event.values[1];
	    double z = event.values[2];
	    
	    final float alpha = (float) 0.8;

        gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0];
        gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
        gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];

        linear_acceleration[0] = event.values[0] - gravity[0];
        linear_acceleration[1] = event.values[1] - gravity[1];
        linear_acceleration[2] = event.values[2] - gravity[2];

        //double n = Math.atan(-y/x);
        double Direction = 0.0;
        if(y>0){
        	Direction= 90 - Math.atan(-y/x)*180;
        }
        else if(y<0){
        	Direction = 270 - Math.atan(-y/x)*180;
        }
        else if(y==0 && x<0){
        	Direction = 180.0;
        }
        else if(y==0 && x>0){
        	Direction = 0.0;
        }
        
        
        
	    
	    StringBuilder scanBuilder = new StringBuilder();
	    scanBuilder.append("\nAcceleration force including gravity:");
	    scanBuilder.append("\nx:"+x);
	    scanBuilder.append("\ny:"+y);
	    scanBuilder.append("\nz:"+z);
	    scanBuilder.append("\nDirection:"+Direction);
	    scanBuilder.append("\nAcceleration force without gravity:");
	    scanBuilder.append("\nx:"+linear_acceleration[0]);
	    scanBuilder.append("\ny:"+linear_acceleration[1]);
	    scanBuilder.append("\nz:"+linear_acceleration[2]);
	    TextView txCounter = (TextView) findViewById(R.id.textView1);
	    //txCounter.setText(scanBuilder);
	    
	    
	    if (timestamp != 0) {
            final float dT = 1000*(event.timestamp - timestamp) * NS2S;
            // Axis of the rotation sample, not normalized yet.
            float axisX = event.values[0];
            float axisY = event.values[1];
            float axisZ = event.values[2];

            // Calculate the angular speed of the sample
            float omegaMagnitude = (float)Math.sqrt(axisX*axisX + axisY*axisY + axisZ*axisZ);

            // Normalize the rotation vector if it's big enough to get the axis
            
                axisX /= omegaMagnitude;
                axisY /= omegaMagnitude;
                axisZ /= omegaMagnitude;
            

            // Integrate around this axis with the angular speed by the timestep
            // in order to get a delta rotation from this sample over the timestep
            // We will convert this axis-angle representation of the delta rotation
            // into a quaternion before turning it into the rotation matrix.
            float thetaOverTwo = omegaMagnitude * dT / 2.0f;
            float sinThetaOverTwo = (float) Math.sin(thetaOverTwo);
            float cosThetaOverTwo = (float) Math.cos(thetaOverTwo);
            deltaRotationVector[0] = sinThetaOverTwo * axisX;
            deltaRotationVector[1] = sinThetaOverTwo * axisY;
            deltaRotationVector[2] = sinThetaOverTwo * axisZ;
            deltaRotationVector[3] = cosThetaOverTwo;
        }
        timestamp = event.timestamp;

        StringBuilder scanBuilder1 = new StringBuilder();
	   
	    scanBuilder1.append("\nx:"+deltaRotationVector[0]);
	    scanBuilder1.append("\ny:"+deltaRotationVector[1]);
	    scanBuilder1.append("\nz:"+deltaRotationVector[2]);
	    scanBuilder1.append("\nz:"+deltaRotationVector[3]);
	    
	    
	   
	    txCounter.setText(scanBuilder1);
	    
	    
	    
	}
	 

}
