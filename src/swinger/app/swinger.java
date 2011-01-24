package swinger.app;

import java.net.URLEncoder;

import swinger.app.MemberLevel.MemberLevels;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location; 
import android.location.LocationManager; 
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;   
import android.view.MenuInflater;
import android.view.MenuItem;   
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;



public class swinger extends Activity {

	ServerCommunication serverComm;
	/** Called when the activity is first created. */

	public static final String LOG_TAG = "SwingerApp";
	private static final int PICK_IMAGE = 101;
	private static final int USER_PREFERENCES = 102;
	private LocationManager mLocationManager = null;

	// Called when the activity is first created. 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);
		
		// Remove title bar of the application
		//requestWindowFeature(Window.FEATURE_NO_TITLE); 
		
		userInfo unUser = new userInfo();
		unUser.testFunction();

		//grab the location manager service
		mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);


		// Remove this button for testing purpose only
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
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{   
		MenuInflater inflater = getMenuInflater();    
		inflater.inflate(R.menu.main_menu, menu);    
		return true;
	}


    @Override  
    public boolean onOptionsItemSelected(MenuItem item)    
    {   
        super.onOptionsItemSelected(item);
        
        try 
        {
			switch (item.getItemId())    
			{   
			    case R.id.preferences:     
					Intent intent = new Intent(swinger.this, UserPreferences.class);                     
					startActivityForResult(intent, USER_PREFERENCES);
			        break;   
			    case R.id.quit:
			    	finish();
			    	break;
			    default:
			    	Log.e(LOG_TAG, "Unknown menu: " + item.toString());
			}
		} 
        catch (Exception e) 
		{
			// TODO Auto-generated catch block
			Log.e(LOG_TAG, e.toString());
		}   
        return true;   
    }	

	public void onActivityResult(int reqCode, int resultCode, Intent data) {
		super.onActivityResult(reqCode, resultCode, data);


		try 
		{
			switch (reqCode) {
			case (PICK_IMAGE) :
				if (resultCode == Activity.RESULT_OK) 
				{
					String wImagePath = data.getStringExtra(imageManipulation.IMAGE_PATH);
					ImageLevel wImageLevel = (ImageLevel)findViewById(R.id.imageLevel1);
					if (wImageLevel != null)
					{
						wImageLevel.setBackgroundImage(wImagePath);
						wImageLevel.setMemberlLevel(MemberLevels.eMemberLevelGold);
					}
					else
					{
						Log.e(LOG_TAG, "Cannot access ImageLevel1 resource");
					}
				}    
			break;
			}
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			Log.e(LOG_TAG, e.toString());
		}
	}


	//Start a location listener
	LocationListener onLocationChange=new LocationListener() {
		public void onLocationChanged(Location loc) {
			//sets and displays the lat/long when a location is provided
			String latlong = "Lat: " + loc.getLatitude() + " Long: " + loc.getLongitude();   
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
		mLocationManager.removeUpdates(onLocationChange);
	}

	//reactivates listener when app is resumed
	@Override
	public void onResume() {
		super.onResume();
		mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,10000.0f,onLocationChange);
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