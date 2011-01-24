package swinger.app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class UserPreferences extends Activity
{
	
	
	private static final String LOG_TAG = "UserPreferences";

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		try
		{
		setContentView(R.layout.userpreferences_layout);
		}
		catch(Error e)
		{
			Log.e(LOG_TAG, e.toString());
		}
		
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) 
	{     
		Intent resultIntent = new Intent();
		setResult(RESULT_OK, resultIntent);
		finish();
	}  	
	
	
	
}
