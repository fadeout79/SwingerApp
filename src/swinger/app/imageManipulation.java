package swinger.app;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.util.Log;

public class imageManipulation extends Activity
{  
	private static final int SELECT_PICTURE = 1;
	public static final String LOG_TAG = "SwingerApp:imageManipulation";
	
    private String selectedImagePath;  
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		Log.v("imageManipulation", "Passed here.");
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent data) 
	{     
		if (resultCode == RESULT_OK) 
		{         
			if (requestCode == SELECT_PICTURE) 
			{             
				Uri selectedImageUri = data.getData();             
				selectedImagePath = getPath(selectedImageUri);      
				
				Log.v(LOG_TAG, selectedImagePath);
			}     
		} 
	}  

	public String getPath(Uri uri) 
	{     
		String[] projection = { MediaStore.Images.Media.DATA };     
		Cursor cursor = managedQuery(uri, projection, null, null, null);     
		int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);     
		cursor.moveToFirst();     
		return cursor.getString(column_index); 
	} 

}
