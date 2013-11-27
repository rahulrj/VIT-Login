package com.googlecode.android.widgets.DateSlider;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.DialogInterface.OnCancelListener;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class GuestLogin extends Activity {
	String response,image;
	Bitmap bmp;
	ImageView captcha;
	EditText regno,password,putcaptcha;
	String[] faculty,course_title,course_slot,quiz1,quiz2,quiz3,cat1,cat2,assn,attndnc,classno,attended,total,course_mode;
	GDBAdapter5 dba;
	DBAdaptergrades grade;
	int cbl=0;
	String tag="anshul";
	Button ssrv,stsrv;
	String notification,subnoti;
	String[][] sub_grades;
	JSONObject json_grades;
	int ll,lk=0;
	ArrayList list1,list2;
	private ArrayAdapter<String> listAdapter ;
	int globallength;
	JSONObject json,details,marks,pbl,attendance,schedule,grades,attn_details,dates,json_marks,json_details,json_attn,json_schedule,json_dates,json_attn_details;
	int result=1;
	ListView optionslist;
	Dialog options_dialog;
	SharedPreferences pref;
	GFaculty fac;
	GStudent stud;
	Button options,refresh;
	AndroidHttpClient client,client2,client3,client4;
	int cap,acad,logu,lnks;
	int catcap,catacad,catlogu,catlnks,fetchat;
	int a,b,c,d,e,f,g,h,rrr;
	DBAdapterlinks lnk;
	
	DBAdaptergrades dbg;
	 protected void onCreate(Bundle savedInstanceState) {
		    //TODO Auto-generated method stub
		 	super.onCreate(savedInstanceState);
		 	 requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		 	setContentView(R.layout.login);
		 	cap=acad=logu=lnks=0;
		 	catcap=catacad=catlogu=catlnks=fetchat=0;

	     
	       
	       
	 
	        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.windowtitle);
	         options=(Button)findViewById(R.id.options);
	         refresh=(Button)findViewById(R.id.refresh);
	      //  Button glogin=(Button)findViewById(R.id.glogin);
	      options.setVisibility(View.INVISIBLE);
	     refresh.setVisibility(View.INVISIBLE);
	         dbg=new DBAdaptergrades(this);
	       
	      
	       show1();
	  
			 fac=new GFaculty(getApplicationContext());
   		   
	      
		 	
		
	 }
	 
		
	 
	 
	  class login extends AsyncTask<String, String, Void>
		 {
		 	
		 	
		  private ProgressDialog progressDialog = new ProgressDialog(GuestLogin.this);
		     InputStream is = null ;
		     String result = "";
		     protected void onPreExecute() {
		        progressDialog.setMessage("Fetching...");
		        progressDialog.show();
		        progressDialog.setOnCancelListener(new OnCancelListener() {
		 	public void onCancel(DialogInterface arg0) {
		 		
		 		login.this.cancel(true);
		 	   }
		 	});
		      }
		        @Override
		 	protected Void doInBackground(String... params) {
		 	  String url_select = "http://adgvit.in/ansh5.php";
		 	  HttpClient httpClient = new DefaultHttpClient();
		 	  
		 	  
		 	  
		 	// client = AndroidHttpClient.newInstance(null);
		 	  //  HttpParams myParams = client.getParams();
		 	   // HttpConnectionParams.setConnectionTimeout(client.getParams(), 60000);
		 	    //HttpConnectionParams.setSoTimeout(client.getParams(), 60000);
		 	    
		 	    
		 	    
		 	  HttpPost httpPost = new HttpPost(url_select);
		 	  
		 	 //httpPost.setParams(myParams);
		 	 
		 	  ArrayList<NameValuePair> nameValuePairs3 = new ArrayList<NameValuePair>();
	          nameValuePairs3.add(new BasicNameValuePair("regno",regno.getText().toString()));
	          nameValuePairs3.add(new BasicNameValuePair("password",password.getText().toString()));
	          nameValuePairs3.add(new BasicNameValuePair("captcha",putcaptcha.getText().toString()));
	          nameValuePairs3.add(new BasicNameValuePair("submit","true"));
	          
		 	    try {
		 		httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs3));
		 		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		 	    response = httpClient.execute(httpPost, responseHandler);
		 	  //  System.out.println(response);
		 			} 
		 	    catch (Exception e) 
		 	    {

		 		Log.e("log_tag", "Error in http connection "+e.toString());
		 		catlogu=1;
		 a=catlogu;
	
		 		}
		 
		 			return null;

		 		}
		 	
		 		

		 	
		 protected void onPostExecute(Void v) {

			 
				 progressDialog.dismiss();
				 //System.out.println("before"+response);
				 
				 if(response==null || response.equalsIgnoreCase("Not successful"))
				 {
					 Toast.makeText(getApplicationContext(), "Error.Try Again", Toast.LENGTH_SHORT).show();
				 }
				
	         else
	         {
	        	 try{
	        			
	        			final GDBAdapter db=new GDBAdapter(getApplicationContext());
	    	        	db.open();
	    	         json= new JSONObject(response);
	        		  details=new JSONObject();
	        		  json_details=new JSONObject();
		        		try
		        		{
		        			details=json.getJSONObject("det");	
		        			globallength=details.length()-1;
		        			 faculty=new String[details.length()-1];
				        	 course_title=new String[details.length()-1];
				        	 global.guestsubjects=new String[details.length()-1];
				        	 course_slot=new String[details.length()-1];
				        	 classno=new String[details.length()-1];
				        	 course_mode=new String[details.length()-1];
						    
		        			 for(int i=0;i<(details.length()-1);i++)
			        		 {
			        			 
			        		json_details=details.getJSONObject(Integer.toString(i+1));
			        		faculty[i]=json_details.getString("faculty");
		        			 course_title[i]=json_details.getString("coursetitle");
		        			 global.guestsubjects[i]=course_title[i].replaceAll("\\s","");
		         			 course_slot[i]=json_details.getString("slot");
		        			 classno[i]=json_details.getString("classno");
		        			 course_mode[i]=json_details.getString("coursemode");
		        			
			        		 }
		        			 
		        			 
		        			 
		        			 grades=new JSONObject();
			        		  grades=json.getJSONObject("grades"); 
			        		  Log.d("the value of gradesssssssssssssssss are",grades.toString());
			        		   json_grades=new JSONObject();
			        		   json_grades=null;
			        		  int rahul;
			        		  sub_grades=new String[details.length()-1][9];
			        		 
				        		 
				        		 //DBAdaptergrades dbg=new DBAdaptergrades(getApplicationContext());
				        		//dbg.open();
			        		  try
			        		  {
				        		 for(int i=0;i<(details.length()-1);i++)
				        		 {
				        			 Log.d("its  coming here","rahul");
					        	json_grades=grades.getJSONObject(course_title[i]);
					        	Log.d("its not coming here","rahul");
					        	Log.d("the value of jsongrades is",json_grades.toString());
					        	if(json_grades==null)
					        	{
					        		rahul=1;
					        		Log.d("value of rahul",String.valueOf(rahul));
					        	}
					        	else
					        	{
					        	
					        	sub_grades[i][0]=json_grades.getString("sgrade");
					        	sub_grades[i][1]=json_grades.getString("agrade");
					         	sub_grades[i][2]=json_grades.getString("bgrade");
					         	sub_grades[i][3]=json_grades.getString("cgrade");
					         	sub_grades[i][4]=json_grades.getString("dgrade");
					         	sub_grades[i][5]=json_grades.getString("egrade");
					         	sub_grades[i][6]=json_grades.getString("fgrade");
					         	sub_grades[i][7]=json_grades.getString("marks");
					         	sub_grades[i][8]=json_grades.getString("grade");
					        	
					         	
					         		
					         	for(int k=0;k<9;k++)
					         	{
					         		 if(!sub_grades[i][0].equalsIgnoreCase(""))
					         		 {
					         			 ll=1;
					         			 Log.d("the value of llllllllllllllllllllllllllllllllllllllllllllllllll",String.valueOf(ll));
					         			 lk=1;
					         			Log.d("the value of lllllllllllllllllllllllllllllllllllllllllllllllllk",String.valueOf(lk));
					         		 }
					         	}
					         	if(lk==1)
					         		dbopen();
					         	
					         	if(ll==1 && global.grades==1)
					         	{
					         	dbg.insertContact(course_title[i], sub_grades[i][7], sub_grades[i][8], sub_grades[i][0], sub_grades[i][1], sub_grades[i][2], sub_grades[i][3], sub_grades[i][4], sub_grades[i][5], sub_grades[i][6]);
					         	global.grades=0;
					         	Log.d("ite herebbbbbbbbbbbbbbbbbbbbbbb","rahulllllllllllllllllll");
					         	}
					         	
					         	
					         	dbg.close();
					         //	System.out.println(sub_grades[0][1].replaceAll("&ge;",">"));
					         	Log.d("the new grades are as gfollows","rahul");
					         	Log.d("the subject",course_title[i]);
					         	/*for(int i1=0;i1<9;i1++)
					         	{
					         		Log.d("the subject",sub_grades[i][i1]);

					         	}*/
							       
					        	 }	
				        		 
				        		 }
			        		  }
			        		  catch(Exception e)
			        		  {
			        			  
			        		  }
				        		 

		        			 
		        		
			    	        		for(int i=0;i<(details.length()-1);i++)
			    	        		 {
			    	        	 db.createEntry(course_title[i], course_slot[i], "", "", "", "", "", "", ""); 		
			    	        		 }
			    	        
			     
		        		}
		        		catch(JSONException e)
		        		{
		     	           Log.e("log_tag", "Error parsing data "+e.toString());
		     	  		catlogu=1;
		     	  		b=catlogu;
		        		}
	        		  
	        		  
	        		  
	        		  
	        		  
	        		  
	        		  
	        		  
	        		  
	        		  
	        		  marks=new JSONObject();
	        		  json_marks=new JSONObject();
		        		
	        			 quiz1=new String[globallength];
			        	 quiz2=new String[globallength];
			        	 quiz3=new String[globallength];
			        	 cat1=new String[globallength];
			        	 cat2=new String[globallength];
			        	 assn=new String[globallength];
			        
	        		  try
	        		  {
	        			 marks=json.getJSONObject("marks");
	        			 for(int i=0;i<globallength;i++)
	        			 {
	        			 if(course_mode[i].equalsIgnoreCase("CBL"))
		        			{
		        				json_marks=marks.getJSONObject(course_title[i]);
		        				 quiz1[i]=json_marks.getString("quiz1");
		    	        		 quiz2[i]=json_marks.getString("quiz2");
		    	        		 quiz3[i]=json_marks.getString("quiz3");
		    	        		 cat1[i]=json_marks.getString("cat1");
		    	        		 cat2[i]=json_marks.getString("cat2");
		    	        		 assn[i]=json_marks.getString("ass");
		    	        		 cbl++;
		    	        			
		        			}
		        			else
		        			{
		        				quiz1[i]="";
		        				quiz2[i]="";
		        				quiz3[i]="";
		        				cat1[i]="";
		        				cat2[i]="";
		        				assn[i]="";
		                		
		        			}
	        			 }
	        				for(int i=0;i<globallength;i++)
	               		 	{
	        	      
	        	        		if(!(quiz1[i].equalsIgnoreCase("")))
	        	        				{
	        	        	db.update2("Quiz1", quiz1[i], course_title[i]);
	        	        				}
	        	        		
	        	        		
	        	        		if(!(quiz2[i].equalsIgnoreCase("")))
	        	        				{
	        	        	db.update2("Quiz2", quiz2[i], course_title[i]);
	        	        				}
	        	        		
	        	        		if(!(quiz3[i].equalsIgnoreCase("")))
	        	        				{
	        	        	db.update2("Quiz3", quiz3[i], course_title[i]);
	        	        				}
	        	        		
	        	        		if(!(cat1[i].equalsIgnoreCase("")))
	        	        				{
	        	        	db.update2("CAT1", cat1[i], course_title[i]);
	        	        				}
	        	        		
	        	        		if(!(cat2[i].equalsIgnoreCase("")))
	        	        				{
	        	        	db.update2("CAT2", cat2[i], course_title[i]);
	        	        				}
	        	        		
	        	        		if(!(assn[i].equalsIgnoreCase("")))
	        	        				{
	        	        	db.update2("Assignment", assn[i], course_title[i]);
	        	        				}
	               		//   db.update3(attndnc[i], course_title[i]) ;
	               		    	 }
	        	        
			        		
	        		  }
	        		  catch(JSONException e)
	        		  {
	        		Log.d("rahul","marks");	 
	        		catlogu=1;
	        		c=catlogu;
	        		  }
	        		  
	        		  	        		  
	        		  
	        		  
	        		  
	        		  
	        		  
	        		  attendance=new JSONObject();
	        		  attndnc=new String[globallength];
			        	 attended=new String[globallength];
			        	 total=new String[globallength];
			        	
	        		try
	        		{
	        			 attendance=json.getJSONObject("attn");
	 	        		
	        			 for(int i=0;i<globallength;i++)
		        		 {
			        	json_attn=attendance.getJSONObject(Integer.toString(i+1));
			        	attended[i]=json_attn.getString("attended");
			        	total[i]=json_attn.getString("total");
			        	String per=json_attn.getString("per");
			        	attndnc[i]=per.substring(0, 2);
			        	db.updateattn(attended[i], total[i], course_title[i]);
			        	 db.update3(attndnc[i], course_title[i]) ;			    		 }	
			        		
		
	        		}
	        		catch(JSONException e)
	        		{
	        			Log.d("rahul","attendance");
	        			catlogu=1;
	        			f=catlogu;
	        		}
	        		  
	        		  
	        		  
	        		schedule=new JSONObject();
	        		json_schedule=new JSONObject();
	        		try
	        		{
	        			 schedule=json.getJSONObject("sch"); 
	        	    GDBAdapter6 db6=new GDBAdapter6(getApplicationContext());
	    	        db6.open();
	    	       
	    	       for(int i=0;i<globallength;i++)
	    	       {
	    	    	db6.insertval(course_title[i], "", "", "", "") ;  
	    	       }
	    	       
	    	      	 
	    	       	 for(int i=0;i<(schedule.length()-1);i++)
	        		 {
	        		 json_schedule=schedule.getJSONObject(Integer.toString(i+1));
	        		 if(i<cbl)
	        		 {
	        			 System.out.println("Cat 1 schedule");
	        			 String subject=json_schedule.getString("title");
	        			 String date=json_schedule.getString("date");
	        			 String time=json_schedule.getString("time");
	        			 String venue=json_schedule.getString("venue");
	        			 System.out.println(subject+date+time);
	        			 db6.updateval(subject, date, time, venue, "Cat1");
	        			                                                                                                                                          
	        		 }
	        		 else if(i>=cbl && i<(2*cbl))
	        		 {
	        			 System.out.println("Cat 2 schedule");
	        			 String subject=json_schedule.getString("title");
	        			 String date=json_schedule.getString("date");
	        			 String time=json_schedule.getString("time");
	        		//	 System.out.println(subject+date+time);
	        			 String venue=json_schedule.getString("venue");
	        			 System.out.println(subject+date+time);
	        			 db6.updateval(subject, date, time, venue, "Cat2");
	        		 }
	        		 else 
	        		 {
	        			
	            			 System.out.println("Term End schedule");
	            			 String subject=json_schedule.getString("title");
	            			 String date=json_schedule.getString("date");
	            			 String time=json_schedule.getString("time");
	            			 String venue=json_schedule.getString("venue");
	            			 System.out.println(subject+date+time);
	            	 db6.updateval(subject, date, time, venue, "termend");
	            		
	        		 }
	        		
	        		 }
	    	    	 
	    	     	
	    	         db6.close();
	        		}
	        		catch(JSONException e)
	        		{
	        			Log.d("rahul","schedule");	
	        			catlogu=1;
	        			g=catlogu;
	        		}
	    	        
	        	       
	        	       
	        	       
	        	       
	        	       
	        	       
	        	       
	        	       
	        	       
	        		 pbl=new JSONObject();
	        		 try
	        		 {
	        			 pbl=json.getJSONObject("pbl");
	        			 JSONObject json_pbl=pbl.getJSONObject("anshul");
	        			 String pbl_dis=json_pbl.getString("anshul");
	        			 System.out.println(pbl_dis);
	        			 
	        					writ(pbl_dis);
	        				
	        			    
	 
	        		 }
	        		 catch(JSONException e)
	        		 {
	        				catlogu=1; 
	        				h=catlogu;
	        		 }
	        	       
	        	       
	        	       
	        	       
	        	       
	        	       
	        		 	        		 
	        		 System.out.println(json);
	        		 System.out.println(details);
	        		 System.out.println(marks);
	        		 System.out.println(attendance);
	        		 System.out.println(schedule);
	        
	        		// System.out.println(pbl);
	        		
	        		 

	        		 
	 	        	
	        		 
	         db.close();
	         logu=1;
	        		new fetchattn().execute(); 
	        		 
	        	
			
		      
		   }
				catch(JSONException e){
		           Log.e("log_tag", "Error parsing data "+e.toString());
		   		catlogu=1;
		   		rrr=catlogu;
		   }
	    	
	     	
	     		         }

	  
				 
	    
	      
	       
		 	
		 }


		 
	}
	  
	  void dbopen()
	  {
		  dbg.open();
		  lk=0;
	  }
	  class fetchattn extends AsyncTask<String, String, Void>
		 {
		 	
		 	
		  private ProgressDialog progressDialog = new ProgressDialog(GuestLogin.this);
		     InputStream is = null ;
		     String result = "";
		     protected void onPreExecute() {
		        progressDialog.setMessage("Fetching...");
		        progressDialog.show();
		        progressDialog.setOnCancelListener(new OnCancelListener() {
		 	public void onCancel(DialogInterface arg0) {
		 		fetchattn.this.cancel(true);
		 	   }
		 	});
		      }
		        @Override
		 	protected Void doInBackground(String... params) {
		 
		       
		 	    try {
		 	    	attn_details=json.getJSONObject("attndet");
		 	    	System.out.println("attendan");
		 	    	System.out.println(attn_details);
		 	    	json_attn=new JSONObject();
	        		    json_attn_details=new JSONObject();
	        		json_dates=new JSONObject();
		 	    	dates=new JSONObject();
	        		 dates=json.getJSONObject("dates");
	        		 GFaculty fac=new GFaculty(getApplicationContext());
	        		 GStudent stud=new GStudent(getApplicationContext());
	        		 System.out.println("everything");
	        		 fac.open();
	        		System.out.println(attn_details.toString());
	        		System.out.println(attn_details.length()+"aman");
	        		 for(int i=0;i<attn_details.length();i++)
	        		 {
	        		 json_attn_details=attn_details.getJSONObject(classno[i]);
	        		 System.out.println(json_attn_details);
	        		 System.out.println(classno[i]);
	        	 json_dates= dates.getJSONObject(classno[i]);
	        		 System.out.println(json_dates.toString());
	        		System.out.println("printing the global subjects");
	        		
	        		for(int j=0;j<json_dates.length();j++)
	        		 {
	        			 String date=json_dates.getString(Integer.toString(j+1));
	        			 System.out.println(date);
	        			 String result=json_attn_details.getString(date);
	        			 System.out.println(result);
	        			 int x;
	        			x=fac.insertContact(date, global.guestsubjects[i], result);
	        			 if(x==-1)
	        			 {
	        				 fac.updatecontact(date, global.guestsubjects[i], result);
	        			 }
	 	    		 }
	        		 }
	        		fac.close();
	           	
		 	    	
		 		} catch (Exception e) {

		 		Log.e("log_tag", "Error in http connection "+e.toString());
		 		fetchat=1;
		 		}
		 
		 			return null;

		 		}
		 	
		 		

		 	
		 protected void onPostExecute(Void v) {

			 
				 progressDialog.dismiss();
				 
				 
				 Log.d("the values are","catlogu"+String.valueOf(catlogu));
				 Log.d("the values are","catacad"+String.valueOf(catacad));
				 Log.d("the values are","catlnks"+String.valueOf(catlnks));
				 Log.d("the values are","catcap"+String.valueOf(catcap));
				 Log.d("the values are","fertchat"+String.valueOf(fetchat));
				 
				 

				 Log.d("the values are","catlogu  a"+String.valueOf(a));
				 Log.d("the values are","catlogu  b"+String.valueOf(b));
				 
				 Log.d("the values are","catlogu  c"+String.valueOf(c));
				 
				 Log.d("the values are","catlogu  d"+String.valueOf(d));
				 
				 Log.d("the values are","catlogu  f"+String.valueOf(f));
				 
				 Log.d("the values are","catlogu  g"+String.valueOf(g));
				 
				 Log.d("the values are","catlogu  h"+String.valueOf(h));
				 Log.d("the values are","catlogu  rrr"+String.valueOf(rrr));
				 
				 if(catlogu==1 || catcap==1 || catlnks==1 || catacad==1|| fetchat==1)
			        {
			        Toast.makeText(getApplicationContext(), "Error try again", Toast.LENGTH_SHORT).show();
			        }
			        else
			        {
			        	logu=0;
				        acad=0;
				        
				        
				        Intent i= new Intent(getApplicationContext(), GuestMainscreen.class);
						 Bundle b=new Bundle();
			 			b.putStringArray("attended", attended);
			 			b.putStringArray("total", total);
			 			i.putExtras(b);
					       	startActivity(i);
					       	finish();
			        }
			
	  	   	
		  
	  	 
		
}
}

	  
	  class retrivecaptcha extends AsyncTask<String, String, Void>
		 {
		 	
		 	
		  private ProgressDialog progressDialog = new ProgressDialog(GuestLogin.this);
		     InputStream is = null ;
		     String result = "";
		     protected void onPreExecute() {
		        progressDialog.setMessage("Fetching...");
		        progressDialog.show();
		        
		        progressDialog.setOnCancelListener(new OnCancelListener() {
		 	public void onCancel(DialogInterface arg0) {
		 		retrivecaptcha.this.cancel(true);
		 	   }
		 	});
		      }
		        @Override
		 	protected Void doInBackground(String... params) {
		 	  String url_select = "http://adgvit.in/ansh5.php";
		       //  System.out.println("hello");
		 	  HttpClient httpClient = new DefaultHttpClient();
		 	  
		 	  
		 	 //client2 = AndroidHttpClient.newInstance(null);
		 	   // HttpParams myParams = client2.getParams();
		 	    //HttpConnectionParams.setConnectionTimeout(client2.getParams(), 60000);
		 	    //HttpConnectionParams.setSoTimeout(client2.getParams(), 60000);
		 	    
		 	    
		 	  HttpPost httpPost = new HttpPost(url_select);
		 	  
		  	// httpPost.setParams(myParams);

		 	  

		           ArrayList<NameValuePair> nameValuePairs3 = new ArrayList<NameValuePair>();
		           
		         //  System.out.println("hello2");

		       
		 	    try {
		 			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs3));
		 		 ResponseHandler<String> responseHandler = new BasicResponseHandler();
		 		response = httpClient.execute(httpPost, responseHandler);
		 		Log.d("rahul",response);
		 		} catch (Exception e) {

		 		Log.e("log_tag", "Error in http connection "+e.toString());
		 		catcap=1;
		 		}
		 
		 			return null;

		 		}
		 	
		 		

		 	
		 protected void onPostExecute(Void v) {

			 
				 progressDialog.dismiss();
				 
				 if(response==null || response.equalsIgnoreCase("Not successful"))
				 {
					 Toast.makeText(getApplicationContext(), "ERror try again", Toast.LENGTH_SHORT).show();
				 }
				
	         else
	         {
	        	 try{
					 JSONObject json_data = new JSONObject(response);
					 image=json_data.getString("image");
					 byte[] rawImage = null;
					 try {
						 rawImage = Base64.decode(image);
					 } catch (IOException e) {
						 catcap=1;
		// TODO Auto-generated catch block
						e.printStackTrace();
					 }
					 bmp = BitmapFactory.decodeByteArray(rawImage, 0, rawImage.length); 
          
					 captcha.setImageBitmap(bmp);		      
		   }
				catch(JSONException e){
		           Log.e("log_tag", "Error parsing data "+e.toString());
		           catcap=1;
		           
		   }
	    	  }
				 //client2.close();
				 cap=1;
}
}

	
	  private boolean haveNetworkConnection() {
	        boolean haveConnectedWifi = false;
	        boolean haveConnectedMobile = false;

	        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
	        for (NetworkInfo ni : netInfo) {
	            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
	                if (ni.isConnected())
	                    haveConnectedWifi = true;
	            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
	                if (ni.isConnected())
	                    haveConnectedMobile = true;
	        }
	        return haveConnectedWifi || haveConnectedMobile;
	    }
	  
	  public void onResume()
	  {
		 Log.d("rahul","now here");
		
		 //if(global.service==1)
			//  finish();
		  super.onResume();
		 
		
		  Log.d(tag,"In the onResume() event");
	  }
	 public void onStop()
	  {
		  super.onStop();
		  Log.d(tag,"In the onStop() event");
	  }
	 public void onPause()
	  {
		  super.onPause();
		  Log.d(tag,"In the onPause() event");
	  }
	 public void onRestart()
	  {
		 Log.d("values of global",String.valueOf(global.service));
		 if(global.service==1)
		  finish();
		  super.onRestart();
		  Log.d(tag,"In the onRestart() event");
	  }
	 void show1()
	  {
		  regno=(EditText)findViewById(R.id.regno); 
			 password=(EditText)findViewById(R.id.password);
			 putcaptcha=(EditText)findViewById(R.id.putcaptcha); 	
				
			 captcha=(ImageView)findViewById(R.id.captcha); 	
			Button click=(Button)findViewById(R.id.clic); 
			FacultyCabin fc=new FacultyCabin(this);
			fc.open();
			fc.close();
			
		
			
			//startService(new Intent(getBaseContext(),MyService.class));
			
			click.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v)
				{
					 Intent i= new Intent(getApplicationContext(), Mainscreen.class);
	    			
					if(haveNetworkConnection()==false) 
				      {
				    	  Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
				    	  Log.d("rahul","internet");
				      }
					  else
					  {
						 
							  
					new login().execute();
					
					
					  }
				
				}
			});
			
			
			if(haveNetworkConnection()==false) 
		      {
		    	  Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
		    	  Log.d("rahul","internet");
		      }
			  else
			  {
				  new retrivecaptcha().execute();
					new retriveacad().execute();
				
					  
			
			
			  }
			
		}

	class retriveacad extends AsyncTask<String, String, Void>
	 {
	 	
	 	
	  private ProgressDialog progressDialog = new ProgressDialog(GuestLogin.this);
	     InputStream is = null ;
	     String result = "";
	     protected void onPreExecute() {
	        progressDialog.setMessage("Fetching...");
	        progressDialog.show();
	        progressDialog.setOnCancelListener(new OnCancelListener() {
	 	public void onCancel(DialogInterface arg0) {
	 		retriveacad.this.cancel(true);
	 	   }
	 	});
	      }
	        @Override
	 	protected Void doInBackground(String... params) {
	 	  String url_select = "http://adgvit.in/retriveacad.php";
	       //  System.out.println("hello");
	 	  HttpClient httpClient = new DefaultHttpClient();
	 	  //HttpPost httpPost = new HttpPost(url_select);
	 	  
	 	  
	 	  /*client4 = AndroidHttpClient.newInstance(null);
	 	    HttpParams myParams = client4.getParams();
	 	    HttpConnectionParams.setConnectionTimeout(client4.getParams(), 60000);
	 	    HttpConnectionParams.setSoTimeout(client4.getParams(), 60000);*/
	 	    
	 	    
	 	  HttpPost httpPost = new HttpPost(url_select);
	 	  
	  	// httpPost.setParams(myParams);

	           ArrayList<NameValuePair> nameValuePairs3 = new ArrayList<NameValuePair>();
	           
	         //  System.out.println("hello2");

	       
	 	    try {
	 			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs3));
	 		 ResponseHandler<String> responseHandler = new BasicResponseHandler();
	 		response = httpClient.execute(httpPost, responseHandler);
	 		Log.d("rahul",response);
	 		} catch (Exception e) {
	 			catacad=1;

	 		Log.e("log_tag", "Error in http connection "+e.toString());
	 		}
	 
	 			return null;

	 		}
	 	
	 		

	 	
	 protected void onPostExecute(Void v) {

		 
			 progressDialog.dismiss();
			// System.out.println(response);
			 
			 if(response==null || response.equalsIgnoreCase("Not successful"))
			 {
				Toast.makeText(getApplicationContext(), "error try again",Toast.LENGTH_SHORT).show();
			 }
			
        else
        {
       	 try{
       		 
       		 Log.d("rahul","got here");
       		 //System.out.println("in try boss");
       		 GDBAdapter5 x =new GDBAdapter5(getApplicationContext());
       		 x.open();
       		 x.deleteDatabse();
       		 x.close();
       		 x.open();
       		
       		 
       		 
				 JSONArray json_data = new JSONArray(response);
				
				 //System.out.println(response);
				 for(int i=0; i<json_data.length();i++)
				 {
					 //JSONObject w=json_data.getJSONObject(i);
					 //String ww=w.getString("dateto");
					//Log.d("rahul",ww);
					 JSONObject a=json_data.getJSONObject(i);
         x.insertContact(a.getString("dateto"), a.getString("datefrom"), a.getString("description"), a.getString("holiday"));
				 }
				 x.close();
				
			   }
			catch(JSONException e){
	           Log.e("log_tag", "Error parsing data "+e.toString());
	           catacad=1;
	   }
   	  }
			 
			// client4.close();
			 acad=1;
}
}
	
	
	
	void notif(int selnot)
	{
		
		
		
		Intent i=new Intent(this,notif2.class);
		i.putExtra("notificationID", selnot);
	////	System.out.println(subcontext);
		//i.putExtra("context", subcontext);
		

        

        // The PendingIntent will launch activity if the user selects this
        // notification
        PendingIntent pi = PendingIntent.getActivity(this,
                0, i, 0);
        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        
        Notification notification2 = new Notification(R.drawable.ic_launcher,
                notification, System
                        .currentTimeMillis());
        notification2.setLatestEventInfo(this, notification, subnoti,
               pi);
        notification2.flags=Notification.FLAG_AUTO_CANCEL;
        notification2.vibrate=new long[]{100,250,100,500};
      nm.notify(selnot, notification2);
		
	}


	
	
	
	
	
	void writ(String pb)
	{
		try {
			write("pbl_file",this,pb);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void write (String filename,Context c,String string) throws IOException{
        try {
            FileOutputStream fos =  c.openFileOutput(filename, Context.MODE_PRIVATE);
            fos.write(string.getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    
  
	
	
	
	
	
	
	    
}
