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
	public static int date_type;
	public static int year;
	public static int month;
	public static int day;
	public static int hour;
	public static int minute;
	public static int bone;
	
	public static TextView txt_result;
	
	public static ImageButton btn_back4;
	
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.result);
        
        context = this;
        
        Bundle extras = getIntent().getExtras();
        sex = extras.getInt("sex");
        date_type = extras.getInt("date_type");
        year = extras.getInt("year");
        month = extras.getInt("month");
        day = extras.getInt("day");
        hour = extras.getInt("hour");
        minute = extras.getInt("minute");
        
        btn_back4 = (ImageButton) findViewById(R.id.btn_back4);
        txt_result = (TextView) findViewById(R.id.txt_result);
        
        btn_back4.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(Result.this, SelectDate.class);
				startActivity(intent);
			}
		});
        
        if(date_type == 1)
        {
        	bone = BoneCalculate.calculate(year, month, day, hour, minute);
        }
        else 
        {
        	bone = BoneCalculate.calculateAD(year, month, day, hour, minute);
		}
        
        String bone_str = bone / 10 + "兩" + bone % 10 + "錢";
		String bone_result = BoneCalculate.getResultString(context, bone, sex);
		bone_result.replace(",", ",\n");
		
        txt_result.setText(bone_str + "\n\n" + bone_result);
    }
}