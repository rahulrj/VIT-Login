package com.googlecode.android.widgets.DateSlider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class GDBAdapter6
{
	public static final String KEY_ROWID="SNo";
	public static final String KEY_TITLE = "CourseTitle";
	public static final String KEY_DATE ="Date";
	public static final String KEY_TIME ="Time";
	public static final String KEY_VENUE ="Venue";
	public static final String KEY_DATE1 ="Date1";
	public static final String KEY_TIME1 ="Time1";
	public static final String KEY_VENUE1 ="Venue1";
	public static final String KEY_DATE2 ="Date2";
	public static final String KEY_TIME2 ="Time2";
	public static final String KEY_VENUE2 ="Venue2";
	
	
	private static final String TAG = "DBAdapter";
	private static final String DATABASE_NAME = "gRecordsche";
	private static final String DATABASE_TABLE = "Schedule";
	private static final int DATABASE_VERSION = 1;

	private final Context context;
	private DbHelper DBHelper;
	private SQLiteDatabase db;
	
	public GDBAdapter6(Context ctx)
	{ 
		System.out.println("in Context");
	this.context = ctx;
	DBHelper = new DbHelper(context);
	}
	
	private static class DbHelper extends SQLiteOpenHelper
	{
	DbHelper(Context context)
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
					KEY_TITLE + " TEXT , " +
					KEY_DATE + " TEXT , " +
					KEY_TIME + " TEXT, " +
					KEY_VENUE + " TEXT , " +
					KEY_DATE1 + " TEXT , " +
					KEY_TIME1 + " TEXT, " +
					KEY_VENUE1 + " TEXT , " +
					KEY_DATE2 + " TEXT , " +
					KEY_TIME2 + " TEXT, " +
					KEY_VENUE2 + " TEXT )");
	
		System.out.println("In oncreate inner");
	
		} catch (SQLException e) {
	e.printStackTrace();
	}
		global.schedule_i=1;
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

	public GDBAdapter6 open() throws SQLException
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
	public long insertval(String title, String date, String time, String venue,String type)
	{
	
	ContentValues cv = new ContentValues();
	cv.put(KEY_DATE, "");
	cv.put(KEY_TIME, "");
	cv.put(KEY_VENUE, "");
	cv.put(KEY_TITLE, title);
	cv.put(KEY_DATE1, "");
	cv.put(KEY_TIME1, "");
	cv.put(KEY_VENUE1, "");
	cv.put(KEY_DATE2, "");
	cv.put(KEY_TIME2, "");
	cv.put(KEY_VENUE2, "" );


	/*if(type.equalsIgnoreCase("Cat1"))
	{
	cv.put(KEY_DATE, date);
	cv.put(KEY_TIME, time);
	cv.put(KEY_VENUE, venue);
	
	}
	else if(type.equalsIgnoreCase("Cat2"))
	{
		cv.put(KEY_DATE, "");
		cv.put(KEY_TIME, "");
		cv.put(KEY_VENUE, "");
		
		cv.put(KEY_DATE1, date);
		cv.put(KEY_TIME1, time);
		cv.put(KEY_VENUE1, venue);
		cv.put(KEY_DATE2,"");
		cv.put(KEY_TIME2, "");
		cv.put(KEY_VENUE2,"" );
		
	
			
	}
	else if(type.equalsIgnoreCase("termend"))
	{
		cv.put(KEY_DATE1, "");
		cv.put(KEY_TIME1, "");
		cv.put(KEY_VENUE1, "");
	
		cv.put(KEY_DATE2, date);
		cv.put(KEY_TIME2, time);
		cv.put(KEY_VENUE2, venue);
		
	}*/
	return db.insert(DATABASE_TABLE, null, cv);
	}
	
	
	public long updateval(String title, String date, String time, String venue,String type)
	{
	
	ContentValues cv = new ContentValues();

	if(type.equalsIgnoreCase("Cat1"))
	{
		System.out.println("in cat1");
	cv.put(KEY_DATE, date);
	cv.put(KEY_TIME, time);
	cv.put(KEY_VENUE, venue);
	}
	else if(type.equalsIgnoreCase("Cat2"))
	{
		System.out.println("in cat2");
		cv.put(KEY_DATE1, date);
		cv.put(KEY_TIME1, time);
		cv.put(KEY_VENUE1, venue);
			
	}
	else if(type.equalsIgnoreCase("termend"))
	{
		System.out.println("in term end");
		cv.put(KEY_DATE2, date);
		cv.put(KEY_TIME2, time);
		cv.put(KEY_VENUE2, venue);
		
	}
	return db.update(DATABASE_TABLE, cv, KEY_TITLE +" = '"+title+"'", null);
	}

	
	//This function takes in the slot number and slot of a subject and returns the timing of the class as a String
	
	public Cursor getData() {
		// TODO Auto-generated method stub

		String q="Select * from Schedule ";
		return db.rawQuery(q, null);
	
	}
	
	public void deleteDatabse() {
	     
		  context.deleteDatabase(DATABASE_NAME);
		     //db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE );
		} 

	


}