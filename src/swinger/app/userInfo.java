package swinger.app;

//import android.content.Intent;
import android.location.Location;
//import android.os.IBinder;
//import android.app.Service;
import android.util.Log;

// public class userInfo extends Service
public class userInfo
{ 
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

/*	@Override 
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	} 
*/	
	public void testFunction()
	{

	}
} 

