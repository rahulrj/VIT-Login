package com.googlecode.android.widgets.DateSlider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Quiz 
{
	public static final String KEY_ROWID="SNo";
	public static final String KEY_SUBJECT = "Subject";
	public static final String KEY_CATEGORY ="Slot";
	public static final String KEY_DETAILS ="AttendancePercentage";
	public static final String KEY_YEAR = "Quiz1";
	public static final String KEY_MONTH = "Quiz2";
	public static final String KEY_DATE = "Quiz3";
	public static final String KEY_HOURS = "CAT1";
	public static final String KEY_MINUTE= "CAT2";
	public static final String KEY_NOTES= "notes";

	private static final String TAG = "DBAdapter";
	private static final String DATABASE_NAME ="Quiz";
	private static final String DATABASE_TABLE = "Quiz";
	private static final int DATABASE_VERSION = 1;

	private final Context context;
	private DbHelper DBHelper;
	private SQLiteDatabase db;
	
	public Quiz(Context ctx)
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
					KEY_SUBJECT + " TEXT NOT NULL, " +
					KEY_CATEGORY + " TEXT NOT NULL, " +
					KEY_DETAILS + " TEXT, " +
					KEY_YEAR + " INTEGER, " +
					KEY_MONTH + " INTEGER, " +
					KEY_DATE + " INTEGER, " +
					KEY_HOURS + " INTEGER, " + KEY_MINUTE + " INTEGER, " + KEY_NOTES + " TEXT )");
	
		System.out.println("In oncreate inner");
	
		} catch (SQLException e) {
	e.printStackTrace();
	}
	
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

	public Quiz open() throws SQLException
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
	
	//This function takes in the slot number and slot of a subject and returns the timing of the class as a String
	
	
	 
	public Cursor getAllContacts2()
	{
		System.out.println("in adapter");
	//System.out.println(user_category);
	
		String q="Select * from Quiz";
		return db.rawQuery(q,null);
		
	}

	public void createEntry(String sub, String cat, String det, int year,
			int month, int day, int hours, int minutes,String notes) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		cv.put(KEY_SUBJECT, sub);
		cv.put(KEY_CATEGORY,cat);
		cv.put(KEY_DETAILS, det);
		cv.put(KEY_YEAR, year);
		cv.put(KEY_MONTH, month);
		cv.put(KEY_DATE, day);
		cv.put(KEY_HOURS, hours);
		cv.put(KEY_MINUTE, minutes);
		cv.put(KEY_NOTES, notes);
		
		 db.insert(DATABASE_TABLE, null, cv);
	}
	public void updateEntry(String sub, String cat, String det, int year,
			int month, int day, int hours, int minutes,String notes,int rowid) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		cv.put(KEY_SUBJECT, sub);
		cv.put(KEY_CATEGORY,cat);
		cv.put(KEY_DETAILS, det);
		cv.put(KEY_YEAR, year);
		cv.put(KEY_MONTH, month);
		cv.put(KEY_DATE, day);
		cv.put(KEY_HOURS, hours);
		cv.put(KEY_MINUTE, minutes);
		cv.put(KEY_NOTES, notes);
		
		db.update(DATABASE_TABLE, cv, KEY_ROWID +"="+rowid, null);
		
	}

public int delete(int rowid)
{
	System.out.println("in delete");
	System.out.println(rowid);
	return db.delete(DATABASE_TABLE, KEY_ROWID+"="+rowid,null);
}
	
	
}