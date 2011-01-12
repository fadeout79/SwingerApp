package swinger.app;

import java.net.URLEncoder;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.content.Context;
import android.content.Intent;

//import android.content.ActivityNotFoundException;
import android.location.LocationManager;
import android.net.Uri;


import android.util.Log; 

public class swinger extends Activity {
	
	ServerCommunication serverComm;
    /** Called when the activity is first created. */
    
	public static final String LOG_TAG = "SwingerApp";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //serverComm = new ServerCommunication();
        
        //serverComm.init("POST", "Content-Type","application/x-www-form-urlencoded" );
        //serverComm.sendData(getConnectionInfo());
        TextView tv = new TextView(this);
        
        try
        {
        
	        userInfo unUser = new userInfo();
	        
	        
	        unUser.testFunction();
	        
	        
			LocationManager locationManager; 
			String context = Context.LOCATION_SERVICE;
			
			Log.v(LOG_TAG, "Context : " + context);
			
			locationManager = (LocationManager)getSystemService(context); 
			
		/*	Criteria crta = new Criteria(); 
			crta.setAccuracy(Criteria.ACCURACY_FINE); 
			crta.setAltitudeRequired(false); 
			crta.setBearingRequired(false); 
			crta.setCostAllowed(true); 
			crta.setPowerRequirement(Criteria.POWER_LOW); 
			String provider = locationManager.getBestProvider(crta, true); 
			*/
			// String provider = LocationManager.GPS_PROVIDER; 
			//mLocation = locationManager.getLastKnownLocation(provider);
	        
	    //    Double test = unUser.getUserLatitude();
	        
	      //  tv.setText(unUser.getReturnValue());
	        //serverComm.closeConnection();
			
			Log.v(LOG_TAG, "Longitude : " + unUser.getUserLongitude());
        	tv.setText("123");
        }
        catch (Error e)
        {
            tv.setText(e.getMessage());
        	
        }
        setContentView(tv);
        
    }

    @Override 
    public void onActivityResult(int requestCode, int resultCode, Intent data) { 
      super.onActivityResult(requestCode, resultCode, data); 
      if (requestCode == 0) 
        if (resultCode == Activity.RESULT_OK) { 
          Uri selectedImage = data.getData();
          
          
          serverComm.sendPicture(selectedImage.getPath());
          // TODO Do something with the select image URI 
          TextView tv = new TextView(this);
          tv.setText(serverComm.result);
          setContentView(tv);

        
        }  
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