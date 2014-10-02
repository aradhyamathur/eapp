package com.example.eapp;



import java.text.DecimalFormat;

import com.example.eapp.R;

import android.app.Activity;
import android.content.Context;
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
import android.widget.Toast;

public class MainActivity extends Activity {

	Button b1;
	TextView t1,t2;
	//double lat=0,lng=0;
	String lt,ln;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//Intent intent=new Intent("android.location.GPS_ENABLED_CHANGE");
		//intent.putExtra("enabled", true);
		//sendBroadcast(intent);
		LocationManager managep=(LocationManager) getSystemService(LOCATION_SERVICE);
		
		if(!managep.isProviderEnabled(LocationManager.GPS_PROVIDER)){
		
		Intent i = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
		startActivity(i);
		
		Toast.makeText(getApplicationContext(), "turn on location services first and press back", Toast.LENGTH_LONG).show();
		try {
		    Thread.sleep(2000);                 //1000 milliseconds is one second.
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
		}
		
		
	t1=(TextView) findViewById(R.id.textView1);	
	t2=(TextView) findViewById(R.id.textView2);	
	b1=(Button) findViewById(R.id.button1);	
		b1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				Intent intent=new Intent(MainActivity.this,cam2.class);
				intent.putExtra("lati", lt);
				intent.putExtra("long",ln);
				intent.putExtra("shake","hello");
				startActivity(intent);
			}
		});
		
		


	

		LocationManager lm= (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		LocationListener ll=new mylocation();
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, ll);	
		
	}
	
	class mylocation implements LocationListener {

		@Override
		public void onLocationChanged(Location location) {
			if(location!=null){
				 double lng=location.getLongitude();
				 double lat=location.getLatitude();
				 lt=Double.toString(lat);
				 ln=Double.toString(lng);
				//lon=(float)lng;
				 DecimalFormat REAL_FORMATTER = new DecimalFormat("0.####");
				t1.setText("lat:"+REAL_FORMATTER.format(lat));
				t2.setText("long:"+REAL_FORMATTER.format(lng));
			}
			
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			
			
		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub
			
		}
		
	}

}
