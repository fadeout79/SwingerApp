package swinger.app;


import android.content.Context; 
import android.content.Intent;
import android.location.Criteria; 
import android.location.Location; 
import android.location.LocationListener; 
import android.location.LocationManager; 
import android.os.Bundle; 
import android.os.IBinder;
import android.app.Activity;
import android.app.Service;





/*
 * 
 * package com.example.helloandroid;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

public class HelloAndroid extends Activity {

    private TextView mytext;
    private LocationManager locmgr = null;
    
    // Called when the activity is first created. 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mytext = (TextView) findViewById(R.id.mytext);
        
        //grab the location manager service
        locmgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
  
        mytext.setText("waiting for location");
    }
    
    //Start a location listener
    LocationListener onLocationChange=new LocationListener() {
        public void onLocationChanged(Location loc) {
            //sets and displays the lat/long when a location is provided
            String latlong = "Lat: " + loc.getLatitude() + " Long: " + loc.getLongitude();   
            mytext.setText(latlong);
        }
         
        public void onProviderDisabled(String provider) {
        // required for interface, not used
        }
         
        public void onProviderEnabled(String provider) {
        // required for interface, not used
        }
         
        public void onStatusChanged(String provider, int status,
        Bundle extras) {
        // required for interface, not used
        }
    };
    
    //pauses listener while app is inactive
    @Override
    public void onPause() {
        super.onPause();
        locmgr.removeUpdates(onLocationChange);
    }
    
    //reactivates listener when app is resumed
    @Override
    public void onResume() {
        super.onResume();
        locmgr.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,10000.0f,onLocationChange);
    }
}





 * 
 * 
 * 
 * 
 */






public class userInfo extends Service implements LocationListener
{ 
	Location mLocation;
	
	String returnValue;
	public userInfo() 
	{
		try 
		{
			returnValue = "Ok";
		}
		catch (Error e)
		{
			returnValue = e.getMessage();
			
		}
	} 
	

	@Override 
	public void onLocationChanged(Location location) 
	{ 
		mLocation = location; 
	} 
	
	@Override 
	public void onProviderDisabled(String provider) 
	{ 
		mLocation = null; 
	} 

	@Override 
	public void onProviderEnabled(String provider) 
	{ 
	} 

	@Override 
	public void onStatusChanged(String provider, int status, Bundle extras) 
	{ 
	} 

	public double getUserLatitude()
	{
		double wLatitude = 0;
		
		if (mLocation != null)
			wLatitude = mLocation.getLatitude();
	
		return wLatitude;
	}
	
	public double getUserLongitude()
	{
		double wLongitude = 0;
		
		if (mLocation != null)
			wLongitude = mLocation.getLongitude();
	
		return wLongitude;
	}


	public String getReturnValue()
	{
		
		return returnValue;
	}

	@Override 
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	} 
	
	public void testFunction()
	{

	}
} 

