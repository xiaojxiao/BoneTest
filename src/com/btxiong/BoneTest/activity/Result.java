package com.btxiong.BoneTest.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.btxiong.BoneTest.R;
import com.btxiong.BoneTest.util.BoneCalculate;
import com.btxiong.BoneTest.util.MyDebug;

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
	
	public static TextView txt_result1;
	public static TextView txt_result2;
	public static TextView txt_result3;
	
	public static ImageButton btn_back4;
	public static Button btn_save;
	public static Button btn_replay;
	
	public static View name_input_layout;
	
	public static int INPUT_DIALOG_ID = 3;
	
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
        txt_result1 = (TextView) findViewById(R.id.txt_result1);
        txt_result2 = (TextView) findViewById(R.id.txt_result2);
        txt_result3 = (TextView) findViewById(R.id.txt_result3);
        btn_save = (Button) findViewById(R.id.btn_save);
        btn_replay = (Button) findViewById(R.id.btn_replay);
        
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        name_input_layout = inflater.inflate(R.layout.name_input, null);
        Button btn_name_confirm = (Button) name_input_layout.findViewById(R.id.btn_name_confirm);
        
        btn_back4.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(Result.this, SelectDate.class);
				startActivity(intent);
			}
		});
        
        btn_save.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				MyDebug.print("btn_save.....................");
				
				LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
				View layout = inflater.inflate(R.layout.name_input, null);
				
				AlertDialog.Builder builder = new AlertDialog.Builder(context);
				builder.setView(layout);
				AlertDialog alertDialog = builder.create();
				alertDialog.setTitle("请输入名字");
				alertDialog.show();
				
				/*
				Dialog dialog = new Dialog(context);
				
				dialog.setContentView(R.layout.name_input);
				dialog.setTitle("请输入名字");
				showDialog(INPUT_DIALOG_ID);
				*/
			}
		});
        
        btn_replay.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(Result.this, SelectSex.class);
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
		bone_result = bone_result.replace("，", "，\n");
		
        txt_result1.setText(bone_str + "\n\n" + bone_result);
    }
}