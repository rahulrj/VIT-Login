package com.googlecode.android.widgets.DateSlider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter 
{
	public static final String KEY_ROWID="SNo";
	public static final String KEY_TITLE = "CourseTitle";
	public static final String KEY_SLOT ="Slot";
	public static final String KEY_PERCENT ="AttendancePercentage";
	public static final String KEY_QUIZ1 = "Quiz1";
	public static final String KEY_QUIZ2 = "Quiz2";
	public static final String KEY_QUIZ3 = "Quiz3";
	public static final String KEY_CAT1 = "CAT1";
	public static final String KEY_CAT2 = "CAT2";
	public static final String KEY_ASSIGN = "Assignment";
	public static final String KEY_MONDAY = "Monday";
	public static final String KEY_TUESDAY = "Tuesday";
	public static final String KEY_WEDNESSDAY = "Wednessday";
	public static final String KEY_THURSDAY = "Thursday";
	public static final String KEY_FRIDAY = "Friday";
	public static final String KEY_ATTENDED = "Attended";
	public static final String KEY_TOTAL = "Total";


	private static final String TAG = "DBAdapter";
	private static final String DATABASE_NAME = "Record2222";
	private static final String DATABASE_TABLE = "Subjects";
	private static final int DATABASE_VERSION = 1;

	private final Context context;
	private DbHelper2 DBHelper;
	private SQLiteDatabase db;
	
	public DBAdapter(Context ctx)
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
					KEY_TITLE + " TEXT NOT NULL, " +
					KEY_SLOT + " TEXT NOT NULL, " +
					KEY_PERCENT + " TEXT, " +
					KEY_QUIZ1 + " TEXT, " +
					KEY_QUIZ2 + " TEXT, " +
					KEY_QUIZ3 + " TEXT, " +
					KEY_CAT1 + " TEXT, " +
					KEY_CAT2 + " TEXT, " +
					KEY_ASSIGN + " TEXT, " +
					KEY_MONDAY + " INTEGER, " +
					KEY_TUESDAY + " INTEGER, " +
					KEY_WEDNESSDAY + " INTEGER, " +
					KEY_THURSDAY + " INTEGER, " +
					KEY_FRIDAY + " INTEGER, " +
					KEY_ATTENDED + " TEXT, " +
					KEY_TOTAL + " TEXT " +
				" )");
	
		System.out.println("In oncreate inner");
	
		} catch (SQLException e) {
	e.printStackTrace();
	}
		global.attn_i=1;
		global.sync=1;
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

	public DBAdapter open() throws SQLException
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
	public long createEntry(String title, String slot, String percent, String quiz1, String quiz2, String quiz3, String cat1,
								String cat2, String assign)
	{
	
	ContentValues cv = new ContentValues();
	cv.put(KEY_TITLE, title);
	cv.put(KEY_SLOT, slot);
	cv.put(KEY_PERCENT, percent);
	cv.put(KEY_QUIZ1, quiz1);
	cv.put(KEY_QUIZ2, quiz2);
	cv.put(KEY_QUIZ3, quiz3);
	cv.put(KEY_CAT1, cat1);
	cv.put(KEY_CAT2, cat2);
	cv.put(KEY_ASSIGN, assign);
	cv.put(KEY_MONDAY, 0);
	cv.put(KEY_TUESDAY, 0);
	cv.put(KEY_WEDNESSDAY, 0);
	cv.put(KEY_THURSDAY, 0);
	cv.put(KEY_FRIDAY, 0);
	System.out.println(slot);

	//This is to take care of Theory slots ignoring TA, TB, TC etc...
	String test=slot.substring(0, 2);
	
	System.out.println(test);
	if (test.equalsIgnoreCase("A1")){
		cv.put(KEY_MONDAY, 1);
		cv.put(KEY_THURSDAY, 20);
	}
	else if (test.equalsIgnoreCase("B1")){
		cv.put(KEY_TUESDAY, 7);
		cv.put(KEY_FRIDAY, 26);
	}
	else if (test.equalsIgnoreCase("C1")){
		cv.put(KEY_MONDAY, 3);
		cv.put(KEY_WEDNESSDAY, 13);
		cv.put(KEY_THURSDAY,22);
	}
	else if (test.equalsIgnoreCase("D1")){
		cv.put(KEY_TUESDAY, 9);
		cv.put(KEY_THURSDAY, 19);
		cv.put(KEY_FRIDAY, 28);
	}
	else if (test.equalsIgnoreCase("E1")){
		cv.put(KEY_MONDAY, 4);
		cv.put(KEY_WEDNESSDAY, 15);
		cv.put(KEY_FRIDAY, 25);
	}
	else if (test.equalsIgnoreCase("F1")){
		cv.put(KEY_MONDAY, 2);
		cv.put(KEY_WEDNESSDAY, 14);
		cv.put(KEY_THURSDAY, 21);
	}
	else if (test.equalsIgnoreCase("G1")){
		cv.put(KEY_TUESDAY, 8);
		cv.put(KEY_FRIDAY, 27);
	}
	else if (test.equalsIgnoreCase("A2")){
		cv.put(KEY_MONDAY, 31);
		cv.put(KEY_THURSDAY, 50);
	}
	else if (test.equalsIgnoreCase("B2")){
		cv.put(KEY_TUESDAY, 37);
		cv.put(KEY_FRIDAY, 56);
	}
	else if (test.equalsIgnoreCase("C2")){
		cv.put(KEY_MONDAY, 33);
		cv.put(KEY_WEDNESSDAY, 43);
		cv.put(KEY_THURSDAY,52);
	}
	else if (test.equalsIgnoreCase("D2")){
		cv.put(KEY_TUESDAY, 39);
		cv.put(KEY_THURSDAY, 49);
		cv.put(KEY_FRIDAY, 58);
	}
	else if (test.equalsIgnoreCase("E2")){
		cv.put(KEY_MONDAY, 34);
		cv.put(KEY_WEDNESSDAY, 45);
		cv.put(KEY_FRIDAY, 55);
	}
	else if (test.equalsIgnoreCase("F2")){
		cv.put(KEY_MONDAY, 32);
		cv.put(KEY_WEDNESSDAY, 44);
		cv.put(KEY_THURSDAY, 51);
	}
	else if (test.equalsIgnoreCase("G2")){
		cv.put(KEY_TUESDAY, 38);
		cv.put(KEY_FRIDAY, 57);
	}
	
	//This is to take care of the TA, TB, etc.. part of the theory slots
	if((slot.charAt(0) != 'L')&&(slot.length() > 2))
	{
		test = slot.substring(3);
		if (test.equalsIgnoreCase("TA1"))
			cv.put(KEY_TUESDAY, 10);
		else if (test.equalsIgnoreCase("TB1"))
			cv.put(KEY_WEDNESSDAY, 16);
		else if (test.equalsIgnoreCase("TC1"))
			cv.put(KEY_FRIDAY, 29);
		else if (test.equalsIgnoreCase("TD1"))
			cv.put(KEY_MONDAY, 5);
		else if (test.equalsIgnoreCase("TE1"))
			cv.put(KEY_THURSDAY, 23);
		else if (test.equalsIgnoreCase("TF1"))
			cv.put(KEY_TUESDAY, 11);
		else if (test.equalsIgnoreCase("TG1"))
			cv.put(KEY_WEDNESSDAY, 17);
		else if (test.equalsIgnoreCase("TA2"))
			cv.put(KEY_TUESDAY, 40);
		else if (test.equalsIgnoreCase("TB2"))
			cv.put(KEY_WEDNESSDAY, 46);
		else if (test.equalsIgnoreCase("TC2"))
			cv.put(KEY_FRIDAY, 59);
		else if (test.equalsIgnoreCase("TD2"))
			cv.put(KEY_MONDAY, 35);
		else if (test.equalsIgnoreCase("TE2"))
			cv.put(KEY_THURSDAY, 53);
		else if (test.equalsIgnoreCase("TF2"))
			cv.put(KEY_TUESDAY, 41);
		else if (test.equalsIgnoreCase("TG2"))
			cv.put(KEY_WEDNESSDAY, 47);
	}
	
	//This is to take care of the Lab Slots
	int beginIndex = 0;
	if (slot.charAt(0)=='L'){
		System.out.println("Entered the LAB AREA");
		int i;
		for(i=0; slot.charAt(i)!='+'; i++);
		test = slot.substring(1,i);
		System.out.println("The First num in lab slot is"+test);
		int num = (int) Integer.parseInt(slot.substring(beginIndex+1, i));
		System.out.println("Value of num is "+num);
		if (((num<=6)||((num>=31)&&(num<=36))))
			cv.put(KEY_MONDAY, num);
		else if (((num>=7)&&(num<=12))||((num>=37)&&(num<=42)))
			cv.put(KEY_TUESDAY, num);
		else if (((num>=13)&&(num<=18))||((num>=43)&&(num<=48)))
			cv.put(KEY_WEDNESSDAY, num);
		else if ((((num>=19)&&(num<=24))||((num>=49)&&(num<=54))))
			cv.put(KEY_THURSDAY, num);
		else if ((((num>=25)&&(num<=30))||((num>=55)&&(num<=60))))
			cv.put(KEY_FRIDAY, num);
		if (slot.length()>11){
		int num2 = (int) Integer.parseInt(slot.substring(9, 11));
		if (num2 != num+2){
			if (((num2<=6)||((num2>=31)&&(num2<=36))))
				cv.put(KEY_MONDAY, num);
			else if (((num2>=7)&&(num2<=12))||((num2>=37)&&(num2<=42)))
				cv.put(KEY_TUESDAY, num);
			else if (((num2>=13)&&(num2<=18))||((num2>=43)&&(num2<=48)))
				cv.put(KEY_WEDNESSDAY, num);
			else if ((((num2>=19)&&(num2<=24))||((num2>=49)&&(num2<=54))))
				cv.put(KEY_THURSDAY, num);
			else if ((((num2>=25)&&(num2<=30))||((num2>=55)&&(num2<=60))))
				cv.put(KEY_FRIDAY, num);
		}
		}
	}
			
			
	/*for(i=0; i<slot.length();i++){
		if (slot.charAt(i)=='+')
		{
			System.out.println("Value of i is "+i);
			test=slot.substring(beginIndex, i+1);
			System.out.println("Vlue of Test is "+test);
			System.out.println("Value of Begin Index is "+beginIndex);
			int num = (int) Integer.parseInt(slot.substring(beginIndex+1, i));
			System.out.println("Value of num is "+num);
			if (num > previousNum+1){
				if (((num<=6)||((num>=31)&&(num<=36))))
					cv.put(KEY_MONDAY, num);
				else if (((num>=7)&&(num<=12))||((num>=37)&&(num<=42)))
					cv.put(KEY_TUESDAY, num);
				else if (((num>=13)&&(num<=18))||((num>=43)&&(num<=48)))
					cv.put(KEY_WEDNESSDAY, num);
				else if ((((num>=19)&&(num<=24))||((num>=49)&&(num<=54))))
					cv.put(KEY_THURSDAY, num);
				else if ((((num>=25)&&(num<=30))||((num>=55)&&(num<=60))))
					cv.put(KEY_FRIDAY, num);
			}
			previousNum = num;	
		}
		beginIndex=i+1;
	}*/
	return db.insert(DATABASE_TABLE, null, cv);
	}
	
	//This function takes in the slot number and slot of a subject and returns the timing of the class as a String
	
	public Cursor getData() {
		// TODO Auto-generated method stub

		String q="Select * from Subjects ";
		return db.rawQuery(q, null);
	
	}
	
	 
	public Cursor getAllContacts()
	{
		System.out.println("in adapter");
	//System.out.println(user_category);
	
		String q="Select Friday from Subjects";
		return db.rawQuery(q,null);
		
	}
	public Cursor getAllContacts2()
	{
		System.out.println("in adapter");
	//System.out.println(user_category);
	
		String q="Select * from Subjects";
		return db.rawQuery(q,null);
		
	}

	public long update2(String column, String value, String coursetitle) {
		// TODO Auto-generated method stub"
		ContentValues cv = new ContentValues();
		cv.put(column,value );
		return db.update(DATABASE_TABLE, cv, KEY_TITLE +" = '"+coursetitle+"'", null);
		}
	

	public long update3(String value, String coursetitle) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		cv.put(KEY_PERCENT,value );
		return db.update(DATABASE_TABLE, cv, KEY_TITLE +" = '"+coursetitle+"'", null);
	
	}
	public long updateattn(String attended, String total, String coursetitle) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		cv.put(KEY_ATTENDED,attended);
		cv.put(KEY_TOTAL,total);
		
		return db.update(DATABASE_TABLE, cv, KEY_TITLE +" = '"+coursetitle+"'", null);
	
	}
	
}