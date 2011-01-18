package swinger.app;

import java.net.URLEncoder;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location; 
import android.location.LocationManager; 
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.Button;
import android.widget.TextView;

import android.net.Uri;


 

public class swinger extends Activity {
	
	ServerCommunication serverComm;
    /** Called when the activity is first created. */
    
	public static final String LOG_TAG = "SwingerApp";
	private static final int PICK_IMAGE = 101;
    private LocationManager locmgr = null;
    private TextView tv;
    
    // Called when the activity is first created. 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        tv = (TextView) findViewById(R.id.tv);
        
        userInfo unUser = new userInfo();
        
        
        unUser.testFunction();
        
        //grab the location manager service
        locmgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        
        tv.setText("waiting for location");
        
        
		((Button) findViewById(R.id.getImage)).setOnClickListener(new OnClickListener() 
		{                  
			public void onClick(View arg0) 
			{    
				// in onCreate or any event where your want the user to                     
				// select a file                     
				Intent intent = new Intent(swinger.this, imageManipulation.class);                     
				startActivityForResult(intent, PICK_IMAGE);
			}             
		}
		); 


    }
    
	
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        switch (reqCode) {
          case (PICK_IMAGE) :
            if (resultCode == Activity.RESULT_OK) 
            {
              String wImagePath = data.getStringExtra(imageManipulation.IMAGE_PATH);
              
              tv.setText(wImagePath);
              
              LinearLayout ll = (LinearLayout)findViewById(R.id.mainPage);

              ImageLevel wImageLevel = new ImageLevel(ll.getContext());
              wImageLevel.setImagePath(wImagePath);
              ll.addView(wImageLevel);
            }    
          break;
        }
      }
	
        
    //Start a location listener
    LocationListener onLocationChange=new LocationListener() {
        public void onLocationChanged(Location loc) {
            //sets and displays the lat/long when a location is provided
            String latlong = "Lat: " + loc.getLatitude() + " Long: " + loc.getLongitude();   
            tv.setText(latlong);
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
    
    
    public String getConnectionInfo()
    {
    	String data = "";
    	try
    	{
			data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode("fadeout79", "UTF-8");
			data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode("30i07r79l", "UTF-8");
			
			startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), 0);
    	}
    	catch (Exception e)
    	{
    		
    	}
    	
    	return data;
    }
}