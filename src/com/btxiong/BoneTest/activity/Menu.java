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
        
        /*
        InsertCelebrity insertCelebrity = new InsertCelebrity(context);
        insertCelebrity.Execute();
        
        //check version code
		int newVersionCode = getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;

		SharedPreferences settings = getSharedPreferences(STORE_NAME, MODE_PRIVATE);
		int oldVersionCode = settings.getInt("version_code", -1);
		
		//Log.i("Menu", newVersionCode + "   " + oldVersionCode);
		//if the version has been changed, copy the database again
		if(newVersionCode != oldVersionCode)
		{
			SharedPreferences.Editor editor = settings.edit();  
			editor.putInt("version_code", newVersionCode); 
			editor.commit(); 
		}
		*/
        
        /*
        BoneDAO boneDAO = new BoneDAO(context);
        boneDAO.insertBone("Test1", 0, 1981, 2, 2, 11, 21, 0);
        boneDAO.insertBone("测试", 0, 1983, 4, 25, 1, 0, 0);
        
        List<BoneObject> historyList = boneDAO.getHistoryList();
        
        for(BoneObject boneObject : historyList)
        {
        	MyDebug.print(boneObject.getName());
        }
        */
        
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