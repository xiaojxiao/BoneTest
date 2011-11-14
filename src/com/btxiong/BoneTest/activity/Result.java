package com.btxiong.BoneTest.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.btxiong.BoneTest.R;
import com.btxiong.BoneTest.util.BoneCalculate;

public class Result extends Activity 
{
	public static Context context;
	public static String name;
	public static int sex;
	public static int year;
	public static int month;
	public static int day;
	public static int hour;
	public static int minute;
	public static int bone;
	
	public static TextView txt_result;
	
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.result);
        
        context = this;
        
        name =getIntent().getExtras().getString("name");
        sex =getIntent().getExtras().getInt("sex");
        bone =getIntent().getExtras().getInt("bone");
        
        String bone_str = bone / 10 + "兩" + bone % 10 + "錢";
		String bone_result = BoneCalculate.getResultString(context, bone, sex);
        
        txt_result = (TextView) findViewById(R.id.txt_result);
        txt_result.setText(bone_str + "\n\n" + bone_result);
    }
}