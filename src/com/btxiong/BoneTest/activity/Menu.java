package com.btxiong.BoneTest.activity;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;

import com.btxiong.BoneTest.R;
import com.btxiong.BoneTest.data.BoneDAO;
import com.btxiong.BoneTest.data.BoneDBHelper;
import com.btxiong.BoneTest.data.BoneObject;
import com.btxiong.BoneTest.data.InsertCelebrity;
import com.btxiong.BoneTest.util.Constant;
import com.btxiong.BoneTest.util.MyDebug;
import com.btxiong.BoneTest.util.ServerUtil;
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
	
	public static Context context;
	
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.menu);
        
        context = this;
        
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
        
        btn_list.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(Menu.this, HistoryList.class);
				startActivity(intent);
			}
		});
        
        btn_celebrity.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(Menu.this, CelebrityList.class);
				startActivity(intent);
			}
		});
        
        try
		{
        	//check version code
    		int newVersionCode = getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;

    		SharedPreferences settings = getSharedPreferences(Constant.STORE_NAME, MODE_PRIVATE);
    		int oldVersionCode = settings.getInt("version_code", -1);
    		
    		//Log.i("Menu", newVersionCode + "   " + oldVersionCode);
    		//if the version has been changed, copy the database again
    		if(newVersionCode != oldVersionCode)
    		{
    			InsertCelebrity insertCelebrity = new InsertCelebrity(context);
    		    insertCelebrity.Execute();
    			
    			SharedPreferences.Editor editor = settings.edit();  
    			editor.putInt("version_code", newVersionCode); 
    			editor.commit(); 
    		}
		}
		catch (Exception e)
		{
			// TODO: handle exception
		}
    }
}