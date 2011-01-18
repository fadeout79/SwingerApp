package swinger.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseManager
{
    public static final String KEY_USERNAME = "userName";
    public static final String KEY_PASSWORD = "passWord";
    public static final String KEY_IMAGEPATH = "imagePath";
    private static final String TAG = "DatabaseManager";
    
    private static final String DATABASE_NAME = "swingerApp";
    private static final String DATABASE_TABLE = "preferences";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE =
        "create table preferences (userName text primary key, "
        + "passWord text not null, imagePath text not null);"; 
        
    private final Context context; 
    
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public DatabaseManager(Context ctx) 
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }
        
    private static class DatabaseHelper extends SQLiteOpenHelper 
    {
        DatabaseHelper(Context context) 
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) 
        {
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, 
        int newVersion) 
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion 
                    + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS preferences");
            onCreate(db);
        }
    }    
    
    //---opens the database---
    public DatabaseManager open() throws SQLException 
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //---closes the database---    
    public void close() 
    {
        DBHelper.close();
    }
    
    //---insert a title into the database---
    public long insertPreferences(String userName, String passWord, String imagePath) 
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_USERNAME, userName);
        initialValues.put(KEY_PASSWORD, passWord);
        initialValues.put(KEY_IMAGEPATH, imagePath);
        return db.insert(DATABASE_TABLE, null, initialValues);
    }

    //---deletes a particular preferences---
    public boolean deletePreferences(String userName) 
    {
        return db.delete(DATABASE_TABLE, KEY_USERNAME + 
        		"='" + userName + "'", null) > 0;
    }

    //---retrieves all the preferences---
    public Cursor getAllPreferences() 
    {
        return db.query(DATABASE_TABLE, new String[] {
        		KEY_USERNAME, 
        		KEY_PASSWORD,
        		KEY_IMAGEPATH}, 
                null, 
                null, 
                null, 
                null, 
                null);
    }

    //---retrieves a particular title---
    public Cursor getTitle(long rowId) throws SQLException 
    {
        Cursor mCursor =
                db.query(true, DATABASE_TABLE, new String[] {
                		KEY_USERNAME, 
                		KEY_PASSWORD,
                		KEY_IMAGEPATH}, 
                		KEY_USERNAME + "= '" + rowId + "'", 
                		null,
                		null, 
                		null, 
                		null, 
                		null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //---updates a title---
    public boolean updateTitle(String userName, String passWord, 
    String imagePath) 
    {
        ContentValues args = new ContentValues();
        args.put(KEY_USERNAME, userName);
        args.put(KEY_PASSWORD, passWord);
        args.put(KEY_IMAGEPATH, imagePath);
        return db.update(DATABASE_TABLE, args, 
                         KEY_USERNAME + "= '" + userName + "'", null) > 0;
    }
}
	 