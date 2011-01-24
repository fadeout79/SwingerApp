package swinger.app;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

public class StatusBar extends RelativeLayout 
{

	/**
	 * Constructor for this instance
	 * 
	 * @param context
	 */
	public StatusBar(Context context) 
	{
		this(context, null);
	}
	
	/**
	 * Constructor for this instance
	 * 
	 * @param context
	 * @param attr
	 */
	public StatusBar(Context context, AttributeSet attr) 
	{
		super(context, attr);
		
		LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		layoutInflater.inflate(R.layout.statusbar_layout, this, true);
		
		
		// TODO Auto-generated constructor stub
	}

	//@Override
	//protected void onFinishInflate()
	//{
		//super.onFinishInflate();
	    //((Activity)getContext()).getLayoutInflater().inflate(R.layout.myhomebrewedComp, this);
	//}
	
	
//    public ImageView setImage(String imagePath) 
//    {
//    	File imgFile = new File(imagePath);
//    	if(imgFile.exists())
//    	{      
////    		setContentView(R.layout.statusbar);
////    		Bitmap myBitmap = BitmapFactory.decodeFile(imgFile);
//    		ImageView myImage = (ImageView) findViewById(R.id.userThumb);     
////    		myImage.setImageBitmap(myBitmap);  
//    		
//    	}
////        ImageView i = new ImageView(this.getContext());
////              i.setImageURI(uri)
////              imagecursor.moveToPosition(position);
////              int id = imagecursor.getInt(image_column_index);
////              i.setImageURI(Uri.withAppendedPath(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, "" + id));
////              i.setScaleType(ImageView.ScaleType.CENTER_CROP);
////              i.setLayoutParams(new GridView.LayoutParams(92, 92));
////        }
////        else {
////              i = (ImageView) convertView;
////        }
//        return i;
//  }
	
	
	
}
