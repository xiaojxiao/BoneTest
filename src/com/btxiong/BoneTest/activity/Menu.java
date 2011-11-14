package com.btxiong.BoneTest.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;

import com.btxiong.BoneTest.R;
import com.btxiong.BoneTest.util.Constant;
import com.btxiong.BoneTest.util.MyDebug;
import com.parse.CountCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class Menu extends Activity 
{
	public static ImageButton btn_form;
	public static ImageButton btn_list;
	public static ImageButton btn_celebrity;
	
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.menu);
        
        btn_form = (ImageButton)findViewById(R.id.btn_form);
        btn_list = (ImageButton)findViewById(R.id.btn_list);
        btn_celebrity = (ImageButton)findViewById(R.id.btn_celebrity);
        
        btn_form.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(Menu.this, SelectSex.class);
				startActivity(intent);
			}
		});
        
        /*
        Parse.initialize(this, Constant.PARSE_APP_ID, Constant.PARSE_CLIENT_KEY);
        
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("value", 25);
        testObject.saveInBackground();
        
        ParseQuery query = new ParseQuery("TestObject");
        query.whereGreaterThan("value", 30);
        
        query.countInBackground(new CountCallback() {
            public void done(int count, ParseException e) {
                if (e == null) {
                    MyDebug.print(count + "");
                } else {
                    // The request failed
                }
            }
        });
        */
        
    }
}