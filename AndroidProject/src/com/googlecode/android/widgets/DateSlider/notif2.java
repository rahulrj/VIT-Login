package com.googlecode.android.widgets.DateSlider;

import android.app.Activity;
import android.app.NotificationManager;
import android.os.Bundle;
import android.widget.TextView;

public class notif2 extends Activity{
	
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notif2);
	//	Bundle b=getIntent().getExtras();
		//String x=b.getString("context");
	//	System.out.println(x);
	//	TextView notidetails=(TextView)findViewById(R.id.notidetails);
		//notidetails.setText(x);
		
		
		NotificationManager nm=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
nm.cancel(getIntent().getExtras().getInt("notificationID"));
		
	}
}
