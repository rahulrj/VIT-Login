
package com.googlecode.android.widgets.DateSlider;
import android.content.ContentValues;


import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DBAdapter2
{
	public static final String KEY_DATES="dates";
	public static final String KEY_S1 = "s1";
	public static final String KEY_S2 = "s2";
	public static final String KEY_S3 = "s3";
	public static final String KEY_S4 = "s4";
	public static final String KEY_S5 = "s5";
	public static final String KEY_S6 = "s6";
	public static final String KEY_S7 = "s7";
	public static final String KEY_S8 = "s8";
	public static final String KEY_S9 = "s9";
	public static final String KEY_S10 = "s10";
	public static final String KEY_S11 = "s11";
	public static final String KEY_S12 = "s12";
	public static final String KEY_S13 = "s13";
	public static final String KEY_S14 = "s14";
	public static final String KEY_S15 = "s15";
	public static final String KEY_S16 = "s16";
	

	private static final String TAG = "DBAdapter";
	private static final String DATABASE_NAME = "student_att";
	private static final String DATABASE_TABLE = "attendance";
	private static final int DATABASE_VERSION = 1;
	static String date="dates";
	static String querystring;
	static String DATABASE_CREATE;
	
	
	private final Context context;
	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;
	
	public DBAdapter2(Context ctx)
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
		func();
	System.out.println("in oncreate outer");	
	try {
		db.execSQL(DATABASE_CREATE);
		
		System.out.println("In oncreate inner");
	
		} catch (SQLException e) {
	e.printStackTrace();
	}
	//global.i=1;
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
	
	
	public  void insertContact(String dates,String s1,String s2,String s3,String s4,String s5,String s6,String s7,String s8,String s9,String s10,String s11,String s12,String s13,String s14,String s15,String s16)
			
	{
		String s[]=new String[10];
	ContentValues initialValues = new ContentValues();
	/*initialValues.put(KEY_DATES, dates);
	initialValues.put(KEY_S1, s1);
	initialValues.put(KEY_S2, s2);
	initialValues.put(KEY_S3, s3);
	initialValues.put(KEY_S4, s4);
	initialValues.put(KEY_S5, s5);
	initialValues.put(KEY_S6, s6);
	initialValues.put(KEY_S7, s7);
	initialValues.put(KEY_S8, s8);
	initialValues.put(KEY_S9, s9);
	initialValues.put(KEY_S10, s10);
	initialValues.put(KEY_S11, s11);
	initialValues.put(KEY_S12, s12);
	initialValues.put(KEY_S13, s13);
	initialValues.put(KEY_S14, s14);
	initialValues.put(KEY_S15, s15);
	initialValues.put(KEY_S16, s16);*/
	for(int i=0;i<10;i++)
	{
		initialValues.put(s[i], s[i]);
	}
	
	
	 db.insert(DATABASE_TABLE, null, initialValues);
	}
	public DBAdapter2 open() throws SQLException
	{
		System.out.println("Get Writable Database");
	db = DBHelper.getWritableDatabase();
	return this;
	}
	
	//---closes the database---
	public void close()
	{
		System.out.println("In close");
	DBHelper.close();
	}

	/*public boolean deleteContact(long rowId)
	{
	return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
	}*/
	//---retrieves all the contacts---
	/*public Cursor getAllContacts()
	{
	//return db.query(DATABASE_TABLE, new String[] { KEY_SLOT,KEY_ROWID,
		//	KEY_SUBJECT,KEY_FACULTY,KEY_FACULTYID,KEY_STUDENT,KEY_REGISTRATION,KEY_DATE1,KEY_DATE2,KEY_DATE3,KEY_DATE4,KEY_DATE5,KEY_DATE6,KEY_DATE7,KEY_DATE8,KEY_DATE9,KEY_DATE10,KEY_DATE11,KEY_DATE12,KEY_DATE13,KEY_DATE14,KEY_DATE15,KEY_DATE16,KEY_DATE17,KEY_DATE18,KEY_DATE19,KEY_DATE20,KEY_DATE21,KEY_DATE22,KEY_DATE23,KEY_DATE24,KEY_DATE25,KEY_DATE26,KEY_DATE27,KEY_DATE28,KEY_DATE29,KEY_DATE30}, null, null, null, null, null);
	/*return db.query(DATABASE_TABLE, new String[] {KEY_PASSWORD,KEY_ROWID, KEY_SLOT,KEY_SUBJECT,
			KEY_FACULTY,KEY_FACULTYID,KEY_STUDENT,KEY_REGISTRATION,KEY_DATE1,KEY_DATE2,
			KEY_DATE3,KEY_DATE4,KEY_DATE5,KEY_DATE6,KEY_DATE7,KEY_DATE8,KEY_DATE9,
			KEY_DATE10,KEY_DATE11,KEY_DATE12,KEY_DATE13,KEY_DATE14,KEY_DATE15,KEY_DATE16,
			KEY_DATE17,KEY_DATE18,KEY_DATE19,KEY_DATE20,KEY_DATE21,KEY_DATE22,KEY_DATE23,
			KEY_DATE24,KEY_DATE25,KEY_DATE26,KEY_DATE27,KEY_DATE28,KEY_DATE29,KEY_DATE30,KEY_MESSAGE}, null, null, null, null, null);
	*/
	
	//}*/
	
	//---retrieves a particular contact---
	/*public Cursor getContact(long rowId) throws SQLException
	{
	Cursor mCursor =
	db.query(true, DATABASE_TABLE, new String[] {KEY_ROWID,
	KEY_NAME, KEY_EMAIL}, KEY_ROWID + "=" + rowId, null,
	null, null, null, null);
	if (mCursor != null) {
	mCursor.moveToFirst();
	}
	return mCursor;
	}*/
	public boolean updateContact(long rowId, String name, String email)
	{
		ContentValues args = new ContentValues();
		args.put(name, email);
		return false;
		
	}
	
	public Cursor getAllContacts()
	{
		System.out.println("in adapter");
	//System.out.println(user_category);
	
		String q="Select datefrom,dateto from calendar_info";
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
	 DATABASE_CREATE="Create Table attendance" + "("+querystring+");";
		String subs[]=new String[7];
		querystring =date+" VARCHAR(30),";
		for(int k=0;k<6;k++)
		{
			querystring += subs[k];
            querystring +=" VARCHAR(200)";
            querystring +=",";
		}
		 querystring+= subs[6] +" VARCHAR(200)";
	}
	
	/*public Cursor getrules(String name)
	{
		System.out.println("in adapter");
	System.out.println(name);
	
		String q="Select Description, Rule1, Rule2, Rule3 from Event_Information where Event = ?";
		return db.rawQuery(q, new String[]{name});
		
	}
	
	public Cursor getContact(String name)
	{
		System.out.println("in adapter");
	System.out.println(name);
	
		String q="Select Contact1, Contact2, Name1, Name2  Event_from Information where Event = ?";
		return db.rawQuery(q, new String[]{name});
		
	}
	
	public Cursor check(String name)
	{
		System.out.println("in adapter");
	System.out.println(name);
	
		String q="Select Contact1  from Event_Information where Event = ?";
		return db.rawQuery(q, new String[]{name});
		
	}*/
	
	 
}
