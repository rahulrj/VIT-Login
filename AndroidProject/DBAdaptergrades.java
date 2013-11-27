
package in.ac.vit.vitdroid;
import android.content.ContentValues;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DBAdaptergrades
{
	public static final String KEY_SUBJECT="subject";
	public static final String KEY_MARKS="marks";
	public static final String KEY_GRADE = "grade";
	public static final String KEY_SGRADE = "sgrade";
	public static final String KEY_AGRADE = "agrade";
	public static final String KEY_BGRADE = "bgrade";
	public static final String KEY_CGRADE = "cgrade";
	public static final String KEY_DGRADE = "dgrade";
	public static final String KEY_EGRADE = "egrade";
	public static final String KEY_FGRADE = "fgrade";
	
	

	private static final String TAG = "DBAdaptergradres";
	private static final String DATABASE_NAME = "subject_grades";
	private static final String DATABASE_TABLE = "grades";
	private static final int DATABASE_VERSION = 1;
	
	
	private final Context context;
	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;
	
	public DBAdaptergrades(Context ctx)
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
	
	public static final String DATABASE_CREATE="Create Table grades(subject varchar2(200),grade varchar2(10), marks varchar2(10),sgrade varchar2(50),agrade varchar2(50),bgrade varchar2(50),cgrade varchar2(50),dgrade varchar2(50),egrade varchar2(50),fgrade varchar2(50))";
		
	//public static final String DATABASE_Query1="insert into Details values("anshul","","","","","","","","","","","","","","","","","");";

	
	
	@Override
	public void onCreate(SQLiteDatabase db)
	{
	System.out.println("in oncreate outer");	
	try {
		db.execSQL(DATABASE_CREATE);
		
		System.out.println("In oncreate inner");
	
		} catch (SQLException e) {
	e.printStackTrace();
	}
		global.grades=1;
	
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
	
	
	public  void insertContact(String subject,String marks,String grade,String s,String a,String b,String c,String d,String e,String f)
			
	{
		
	ContentValues initialValues = new ContentValues();
	initialValues.put(KEY_SUBJECT,subject);
	initialValues.put(KEY_MARKS,marks);
	initialValues.put(KEY_GRADE,grade);
	initialValues.put(KEY_SGRADE,s);
	initialValues.put(KEY_AGRADE,a);
	initialValues.put(KEY_BGRADE,b);
	initialValues.put(KEY_CGRADE,c);
	initialValues.put(KEY_DGRADE,d);
	initialValues.put(KEY_EGRADE,e);
	
	initialValues.put(KEY_FGRADE,f);
	
	
	 db.insert(DATABASE_TABLE, null, initialValues);
	}
	public DBAdaptergrades open() throws SQLException
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
	
		String q="Select * from grades";
		return db.rawQuery(q,null);
		
	}
	public Cursor getAllContacts2(String user_category)
	{
		System.out.println("in adapter");
	System.out.println(user_category);
	
		String q="Select description from calendar_info where dateto = ?";
		return db.rawQuery(q, new String[]{user_category});
		
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
