
package com.googlecode.android.widgets.DateSlider;
import android.content.ContentValues;


import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Faculty
{
	public static final String KEY_DATES="dates";
	

	private static final String TAG = "DBAdapter";
	private static final String DATABASE_NAME = "Faculty_database";
	private static final String DATABASE_TABLE = "attend2";
	private static final int DATABASE_VERSION = 1;
	static String date="dates";
	static String querystring;
	static String DATABASE_CREATE;
	static String subs[]={"rahul","raja","hass","awesome"};
	static String tablename="attend2";
	
	
	private final Context context;
	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;
	
	public Faculty(Context ctx)
	{
		System.out.println("in Context");
	this.context = ctx;
	DBHelper = new DatabaseHelper(context);
	}
	
	
	private static class DatabaseHelper extends SQLiteOpenHelper
	{
	DatabaseHelper(Context context)
	{
		
	super(context, DATABASE_NAME, null, DATABASE_VERSION);
	System.out.println("IN DataHelper");
	}
	
	
	
	
	
	
	/*public static final String DATABASE_CREATE="Create Table attendance(dates varchar2(50), s1 varchar2(200)," +
	"s2 varchar2(200), s3 varchar2(200), s4 varchar2(200),s5 varchar2(200),s6 varchar2(200)," +
	" s7 varchar2(200), s8 varchar2(200)," +
	"s9 varchar2(200),s10 varchar2(200),s11 varchar2(200),s12 varchar2(200),s13 varchar2(200),s14 varchar2(200)," +
	"s15 varchar2(200),s16 varchar2(200))";*/
		
	//public static final String DATABASE_Query1="insert into Details values("anshul","","","","","","","","","","","","","","","","","");";

	
	
	@Override
	public void onCreate(SQLiteDatabase db)
	{
		
		Log.d("rahul","on create of attndnc");
		func();
	System.out.println("in oncreate outer");	
	try {
		Log.d("i am thr quey",DATABASE_CREATE);
		db.execSQL(DATABASE_CREATE);
		
		System.out.println("In oncreate inner");
	
		} catch (SQLException e) {
	e.printStackTrace();
	}
	global.i=1;
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		System.out.println("in onupgrade");
	Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
	+ newVersion + ", which will destroy all old data");
	db.execSQL("DROP TABLE IF EXISTS contacts");
	
	
	onCreate(db);
	}
	}
	
	public long updatecontact(String date, String subject, String status) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		cv.put(subject,status );
		return db.update(DATABASE_TABLE, cv, KEY_DATES +" = '"+date+"'", null);
	
	}

	public  int insertContact(String dates,String subjectname,String value)
			
	{
		System.out.println("inside checking");
		System.out.println(dates);
		System.out.println(subjectname);
		System.out.println(value);
	ContentValues initialValues = new ContentValues();
	initialValues.put(KEY_DATES,dates);
	initialValues.put(subjectname,value);
	
	
	int x= (int) db.insert(tablename, null, initialValues);
	System.out.println("error is"+x);
	return x;
	}
	
		public Faculty open() throws SQLException
	{
		System.out.println("Get Writable Database");
	db = DBHelper.getWritableDatabase();
	return this;
	}
		public Cursor get()
		{
			return db.query(DATABASE_TABLE, new String[] {KEY_DATES}, null, null, null, null, KEY_DATES + " ASC");
		}
	
	//---closes the database---
	public void close()
	{
		System.out.println("In close");
	DBHelper.close();
	}

	
	
	public Cursor getAllContacts()
	{
		System.out.println("in adapter");
	//System.out.println(user_category);
	
		String q="Select * from attend2";
		return db.rawQuery(q,null);
		
	}
	public Cursor getAllContacts2(String user_category)
	{
		System.out.println("in adapter");
	System.out.println(user_category);
	
		String q="Select description from calendar_info where dateto = ?";
		return db.rawQuery(q, new String[]{user_category});
		
	}
	
	static void func()
	{
		//int length=global.subjects.length;
		//System.out.println("length yr"+length); 
	
	querystring =date+" VARCHAR(30) unique ,";
		Log.d("dat is",date);
		int k;
		int length=global.subjects.length;
		for( k=0;k<length-1;k++)
		{
			querystring += global.subjects[k];
            querystring +=" VARCHAR(200)";
            querystring +=",";
		}
		 querystring+= global.subjects[length-1] +" VARCHAR(200)";
		 
		 
		 DATABASE_CREATE="Create Table "+ tablename + "("+querystring+");";
		 Log.d("qyery is",DATABASE_CREATE);
		 
		 Log.d("rhul","table created");
	}
	
		 
}
