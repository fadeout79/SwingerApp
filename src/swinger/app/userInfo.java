package swinger.app;

import android.location.Location;
import android.util.Log;

// public class userInfo extends Service
public class userInfo
{ 
	
	public final String LOG_TAG = "userInfo";
	
	Location mUserLocation;
	
	String returnValue;
	
	public userInfo() 
	{
		try 
		{
			mUserLocation = null;
			returnValue = "Ok";
		}
		catch (Error e)
		{
			returnValue = e.getMessage();
			
		}
	} 
	


	public double getUserLatitude()
	{
		double wLatitude = 0;
		
		if (mUserLocation != null)
			wLatitude = mUserLocation.getLatitude();
	
		return wLatitude;
	}
	
	public double getUserLongitude()
	{
		double wLongitude = 0;
		
		if (mUserLocation != null)
			wLongitude = mUserLocation.getLongitude();
	
		return wLongitude;
	}


	public String getReturnValue()
	{
		
		return returnValue;
	}

	public void testFunction()
	{
		Log.v(LOG_TAG, "Test function called");
	}
} 

