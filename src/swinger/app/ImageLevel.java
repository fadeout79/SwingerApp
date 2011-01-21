package swinger.app;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import android.content.Context;
import android.content.res.Resources;
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
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ImageLevel extends LinearLayout {

	private static final String LOG_TAG = "ImageLevel";

	private int mThumbnailWidth;
	private int mThumbnailHeight;
	private static final int cmColorPadding = 4;
	private static final int cmBorderPadding = 1;
	
	private Bitmap mBackImage;
	private MemberLevel.MemberLevels mMemberLevel;
	private ImageView mCurrentImage;
	

	/**

	 * Constructor for this instance
	 * 
	 * @param context the context in which this View is set.
	 * 
	 */
	public ImageLevel(Context context, AttributeSet attr) 
	{
		super(context, attr);
		
		Resources res = getResources();
		
		// Acquiring image dimension from resources
		mThumbnailWidth = (int)res.getDimension(R.dimen.thumbnailWidth);
		mThumbnailHeight = (int)res.getDimension(R.dimen.thumbnailHeight);
		
		mMemberLevel = MemberLevel.MemberLevels.eMemberLevelBase;
		mCurrentImage = new ImageView(getContext());
		mCurrentImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
		mCurrentImage.setMaxWidth(mThumbnailWidth);
		mCurrentImage.setMaxHeight(mThumbnailHeight);
		
		// Acquiring first image from resource
		mBackImage = ((BitmapDrawable)res.getDrawable(R.drawable.smiley)).getBitmap();
		
		if (mBackImage != null)
		{
			Log.v(LOG_TAG, "Drawing image.");
			drawThumbnail();
		}
		else
		{
			Log.e(LOG_TAG, "Initial bitmap not found");
		}
		
		addView(mCurrentImage
			  , new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT)
			  );		
		
	}	
	
	@Override
	protected void onFinishInflate()
	{
		super.onFinishInflate();
	    //((Activity)getContext()).getLayoutInflater().inflate(R.layout.myhomebrewedComp, this);
	}
	
	public MemberLevel.MemberLevels getMemberlLevel() 
	{
		return mMemberLevel;
	}

	/**
	 * Sets the member level and redraw the thumbnail
	 * 
	 * @param mMemberlLevel
	 */
	public void setMemberlLevel(MemberLevel.MemberLevels mMemberlLevel) 
	{
		this.mMemberLevel = mMemberlLevel;
		drawThumbnail();
	}

	/**
	 * Sets the background image and call redraw
	 * 
	 * @param imagePath: the path to the image on the phone
	 */
	public void setBackgroundImage(String imagePath) 
	{
	  try
	   {
		  Context context = this.getContext();
		  if (context != null)
		  {
			  FileInputStream wFile = new FileInputStream(imagePath);
			  BufferedInputStream wBuffer = new BufferedInputStream(wFile);
			  mBackImage = BitmapFactory.decodeStream(wBuffer);
	          if (wFile != null) 
	          {
	        	  wFile.close();
	          }
	          if (wBuffer != null) 
	          {
	        	  wBuffer.close();
	          }
		  }
		  if (mBackImage != null)
		  {
			  drawThumbnail();
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
	 *	Draws the image with the border.
	 *	
	 */
	private void drawThumbnail()
	{
		  int wCurrentWidth = mBackImage.getWidth();
		  int wCurrentHeight = mBackImage.getHeight();
		  
          
          float scaleWidth = ((float) mThumbnailWidth) / wCurrentWidth;
          float scaleHeight = ((float) mThumbnailHeight) / wCurrentHeight;
          Matrix matrix = new Matrix();
          matrix.postScale(scaleWidth, scaleHeight);
          // create the new Bitmap object
          Bitmap resizedBitmap = Bitmap.createBitmap(mBackImage, 0, 0 , wCurrentWidth, wCurrentHeight, matrix, true);
          
          Bitmap output = Bitmap.createBitmap(resizedBitmap.getWidth()+(2*cmColorPadding+2*cmBorderPadding), resizedBitmap.getHeight()+(2*cmColorPadding+2*cmBorderPadding), Config.ARGB_8888);
          Canvas canvas = new Canvas(output);
          
          final int wRectBorderLeft = 0;
          final int wRectColorLeft = wRectBorderLeft + cmBorderPadding;
          final int wRectThumbnailLeft = wRectColorLeft + cmColorPadding;
          final int wRectBorderTop = 0;
          final int wRectColorTop = wRectBorderTop + cmBorderPadding;
          final int wRectThumbnailTop = wRectColorTop + cmColorPadding;

          final int wRectThumbnailRight = wRectThumbnailLeft + resizedBitmap.getWidth();	          
          final int wRectColorRight = wRectThumbnailRight + cmColorPadding;
          final int wRectBorderRight = wRectColorRight + cmBorderPadding;
          final int wRectThumbnailBottom = wRectThumbnailTop + resizedBitmap.getHeight();	          
          final int wRectColorBottom = wRectThumbnailBottom + cmColorPadding;
          final int wRectBorderBottom = wRectColorBottom + cmBorderPadding;
          
          
          final int wThumbColor = 0xff424242;
          final int wColorColor = 0xFF00FF66;
          final int wBorderColor = 0xffBBBBBB;
          final Paint paint = new Paint();
          final Rect wThumbRectangle = new Rect(wRectThumbnailLeft, wRectThumbnailTop, wRectThumbnailRight, wRectThumbnailBottom);
          final Rect wColorRectangle = new Rect(wRectColorLeft, wRectColorTop, wRectColorRight, wRectColorBottom);
          final Rect wBorderRectangle = new Rect(wRectBorderLeft, wRectBorderTop, wRectBorderRight, wRectBorderBottom);
          final RectF wThumbRoundRectangle = new RectF(wThumbRectangle);
          final RectF wColorRoundRectangle = new RectF(wColorRectangle);
          final RectF wBorderRoundRectangle = new RectF(wBorderRectangle);
          final float roundPx = 3.0f;

          paint.setAntiAlias(true);
          canvas.drawARGB(0, 0, 0, 0);
          paint.setColor(wBorderColor);
          canvas.drawRoundRect(wBorderRoundRectangle, roundPx, roundPx, paint);
          paint.setColor(getLevelColor());
          canvas.drawRoundRect(wColorRoundRectangle, roundPx, roundPx, paint);
          paint.setColor(wThumbColor);
          canvas.drawRoundRect(wThumbRoundRectangle, roundPx, roundPx, paint);

          paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
          canvas.drawBitmap(resizedBitmap, null, wThumbRectangle, paint);
          
          BitmapDrawable bmd = new BitmapDrawable(output);
          
		  mCurrentImage.setImageDrawable(bmd);
	}
	
	private int getLevelColor()
	{
		int wColor = 0;
		Resources res = getResources();
		
		
		switch (mMemberLevel)
		{
		case eMemberLevelBase:
			wColor = res.getColor(R.color.levelBase);
			break;
		case eMemberLevelBronze:
			wColor = res.getColor(R.color.levelBronze);
			break;
		case eMemberLevelSilver:
			wColor = res.getColor(R.color.levelSilver);
			break;
		case eMemberLevelGold:
			wColor = res.getColor(R.color.levelGold);
			break;
		case eMemberLevelPlatinum:
			wColor = res.getColor(R.color.levelPlatinum);
			break;
		case eMemberLevelDiamond:
			wColor = res.getColor(R.color.levelDiamond);
			break;
		default:
			Log.e(LOG_TAG, "Unknown member level");
		}
		
		return wColor;
	}
	
}
