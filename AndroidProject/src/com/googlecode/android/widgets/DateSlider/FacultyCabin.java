package com.googlecode.android.widgets.DateSlider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class FacultyCabin 
{
	public static final String KEY_ROWID="SNo";
	public static final String KEY_NAME = "name";
	public static final String KEY_CABIN ="cabin";
	
	private static final String TAG = "DBAdapter";
	private static final String DATABASE_NAME = "Cabin";
	private static final String DATABASE_TABLE = "FacultyCabin";
	private static final int DATABASE_VERSION = 1;

	private final Context context;
	private DbHelper2 DBHelper;
	private SQLiteDatabase db;
	
	public FacultyCabin(Context ctx)
	{ 
		System.out.println("in Context");
	this.context = ctx;
	DBHelper = new DbHelper2(context);
	}
	
	private static class DbHelper2 extends SQLiteOpenHelper
	{
	DbHelper2(Context context)
	{
		
	super(context, DATABASE_NAME, null, DATABASE_VERSION);
	System.out.println("IN DataHelper");
	}
	
	//"CourseTitle varchar2(100), CourseType varchar2(4), Slot varchar2(20), Attendance" Tuesday varchar2(4), Wednesday varchar2(4)," +
	//" Thursday varchar2(4), Friday varchar2(4)));

	
	@Override
	public void onCreate(SQLiteDatabase db)
	{
	System.out.println("in oncreate outer");	
	try {
		db.execSQL("Create Table " + DATABASE_TABLE + "(" +
					KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
					KEY_NAME + " TEXT NOT NULL, " +
					KEY_CABIN + " TEXT )");
	
		System.out.println("In oncreate inner");
	
		} catch (SQLException e) {
	e.printStackTrace();
	}
		global.cabin=1;
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		System.out.println("in onupgrade");
	Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
	+ newVersion + ", which will destroy all old data");
	
	db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE );
	onCreate(db);
	
	}
	}

	public FacultyCabin open() throws SQLException
	{
		System.out.println("Get Writable Database");
	db = DBHelper.getWritableDatabase();
	return this;
	}

	public void close()
	{
		System.out.println("In close");
	DBHelper.close();
	}
	public long createEntry(String name, String cabin)
	{
		System.out.println("inside insert");
	
	ContentValues cv = new ContentValues();
	cv.put(KEY_NAME, name);
	cv.put(KEY_CABIN, cabin);
	
		long x= db.insert(DATABASE_TABLE, null, cv);
		System.out.println(x);
		return x;
	}
	
	//This function takes in the slot number and slot of a subject and returns the timing of the class as a String
	
	public Cursor getFaculties() {
		// TODO Auto-generated method stub

		String q="Select * from FacultyCabin";
		return db.rawQuery(q, null);
	
	}
	
	 
	

		
}