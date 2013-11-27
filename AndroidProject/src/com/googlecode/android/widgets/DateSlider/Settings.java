package com.googlecode.android.widgets.DateSlider;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;

public class Settings extends PreferenceActivity {

	Boolean markk,markkk;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		addPreferencesFromResource(R.xml.settings);
		
		 SharedPreferences sharedPrefs = PreferenceManager
			.getDefaultSharedPreferences(getBaseContext());
		 markk=sharedPrefs.getBoolean("mark",false);
		 Log.d("the markis",String.valueOf(markk));
			
		

	}
	
	
	 @Override
	 public void onBackPressed() {
		System.out.println("all your data is erased");
		
		 SharedPreferences sharedPrefs = PreferenceManager
			.getDefaultSharedPreferences(getBaseContext());
			 markkk=sharedPrefs.getBoolean("mark",false);
			 
			 Log.d("the markkkkk is",String.valueOf(markk));
			 if(markk!=markkk)
			 {
				 if(markkk==true)
				 {
					 Intent i=new Intent(this,SMainScreen.class);
					 startActivity(i);
				 }
				 else
				 {
					 Intent i=new Intent(this,Mainscreen.class);
					 startActivity(i);
				 }
			 }
			
		
	     super.onBackPressed();   
	     //    finish();

	 }
}
