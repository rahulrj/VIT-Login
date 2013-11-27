
package com.googlecode.android.widgets.DateSlider;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

public class pbl extends Activity {
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.pbl);

        WebView wv = (WebView) findViewById(R.id.webtest);   
        Intent intent = getIntent();
       // String html = intent.getExtras().getString("pbl");

        final String mimeType = "text/html";
        final String encoding = "UTF-8";
        String value = null;
       // String html = "xx";
        try {
			value=read("pbl_file",this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


        wv.loadDataWithBaseURL("", value, mimeType, encoding, "");
    }

    
    
    public static String read (String filename,Context c) throws IOException{
    	 
    	String line;
        StringBuffer buffer = new StringBuffer();

        FileInputStream fis = c.openFileInput(filename);
        BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
        if (fis!=null) {                            
            while ((line = reader.readLine()) != null) {    
                buffer.append(line + "\n" );
            }               
        }       
        fis.close();
        return buffer.toString();
    }
}