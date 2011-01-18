package swinger.app;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;
import android.util.Log;

public class imageManipulation extends Activity
{  
	private static final int SELECT_PICTURE = 1;
	public static final String LOG_TAG = "SwingerApp:imageManipulation";
	public static final String IMAGE_PATH = "ImagePath";
	
    private String selectedImagePath;  
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		Log.v(LOG_TAG, "PASSED HERE.");
		super.onCreate(savedInstanceState);
		Intent intent = new Intent();                     
		intent.setType("image/*");                     
		intent.setAction(Intent.ACTION_GET_CONTENT);
		startActivityForResult(Intent.createChooser(intent,
				"Select Picture"), SELECT_PICTURE);
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) 
	{     
		if (resultCode == RESULT_OK) 
		{         
			if (requestCode == SELECT_PICTURE) 
			{             
				Uri selectedImageUri = data.getData();             
				selectedImagePath = getPath(selectedImageUri);  
			
//				ServerCommunication server = new ServerCommunication();
				try {
					Intent resultIntent = new Intent();
					resultIntent.putExtra(IMAGE_PATH, selectedImagePath);
					setResult(RESULT_OK, resultIntent);
					finish();
					//server.sendPicture(selectedImagePath);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					Log.e(LOG_TAG, e.toString());
				}
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
