package swinger.app;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ImageLevel extends LinearLayout {

	private static final String LOG_TAG = "ImageLevel";

	private static final int cmMaxWidth = 50;
	private static final int cmMaxHeight = 50;
	private static final int cmColorPadding = 4;
	
	private String mImagePath;
	private MemberLevel.MemberLevels mMemberLevel;
	private ImageView mCurrentImage;
	
	public MemberLevel.MemberLevels getMemberlLevel() {
		return mMemberLevel;
	}

	public void setMemberlLevel(MemberLevel.MemberLevels mMemberlLevel) {
		this.mMemberLevel = mMemberlLevel;
	}

	public String getImagePath() {
		return mImagePath;
	}

	public void setImagePath(String imagePath) {
		this.mImagePath = imagePath;
	  try
	   {
		  Context context = this.getContext();
		  if (context != null)
		  {
			  FileInputStream wFile = new FileInputStream(imagePath);
			  BufferedInputStream wBuffer = new BufferedInputStream(wFile);
			  Bitmap wBitmap = BitmapFactory.decodeStream(wBuffer);
			  
			  int wCurrentWidth = wBitmap.getWidth();
			  int wCurrentHeight = wBitmap.getHeight();
			  
	          if (wFile != null) 
	          {
	        	  wFile.close();
	          }
	          if (wBuffer != null) 
	          {
	        	  wBuffer.close();
	          }
	          
	          float scaleWidth = ((float) cmMaxWidth) / wCurrentWidth;
	          float scaleHeight = ((float) cmMaxHeight) / wCurrentHeight;
	          Matrix matrix = new Matrix();
	          matrix.postScale(scaleWidth, scaleHeight);
	          // create the new Bitmap object
	          Bitmap resizedBitmap = Bitmap.createBitmap(wBitmap, 0, 0, wCurrentWidth, wCurrentHeight, matrix, true);
	          
	          Bitmap output = Bitmap.createBitmap(resizedBitmap.getWidth()+(cmColorPadding), resizedBitmap.getHeight()+(cmColorPadding), Config.ARGB_8888);
	          Canvas canvas = new Canvas(output);
	          	
	          final int color = 0xff424242;
	          final Paint paint = new Paint();
	          final Rect rect = new Rect(0+cmColorPadding, 0+cmColorPadding, resizedBitmap.getWidth(), resizedBitmap.getHeight());
	          final Rect colorRect = new Rect(0, 0, resizedBitmap.getWidth()+(cmColorPadding), resizedBitmap.getHeight()+(cmColorPadding));
	          final RectF rectF = new RectF(rect);
	          final RectF colorRectF = new RectF(colorRect);
	          final float roundPx = 3.0f;

	          
	          paint.setAntiAlias(true);
	          canvas.drawARGB(0, 0, 0, 0);
	          paint.setColor(0xFF00FF66);
	          canvas.drawRoundRect(colorRectF, roundPx, roundPx, paint);
	          paint.setColor(color);
	          canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

	          paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
	          canvas.drawBitmap(resizedBitmap, rect, rect, paint);
	          
	          BitmapDrawable bmd = new BitmapDrawable(output);
	          
			  mCurrentImage.setImageDrawable(bmd);
		  }
	   }
	   catch(FileNotFoundException  e)
	   {  
		   Log.e(LOG_TAG, "The file '" + imagePath + "' does not exists.");
	   }
	   catch(Exception e)
	   {
		   Log.e(LOG_TAG, e.toString());
	   }
	}
	
	/**
	 * Constructor for this instance
	 * 
	 * @param context the context in which this View is set.
	 * 
	 */
	public ImageLevel(Context context) {
		super(context);
		this.setOrientation(VERTICAL);
		mMemberLevel = MemberLevel.MemberLevels.eMemberLevelBase;
		mImagePath = "";
		mCurrentImage = new ImageView(getContext());
		mCurrentImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
		mCurrentImage.setMaxWidth(cmMaxWidth);
		mCurrentImage.setMaxHeight(cmMaxHeight);
		addView(mCurrentImage, new LinearLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));		
		
	}

	
	
}
