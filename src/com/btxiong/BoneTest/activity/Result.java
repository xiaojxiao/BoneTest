package com.btxiong.BoneTest.activity;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
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
import android.widget.Toast;

import com.btxiong.BoneTest.R;
import com.btxiong.BoneTest.data.BoneDAO;
import com.btxiong.BoneTest.util.BoneCalculate;
import com.btxiong.BoneTest.util.Constant;
import com.btxiong.BoneTest.util.MyDebug;
import com.btxiong.BoneTest.util.ServerUtil;
import com.parse.CountCallback;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

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
	public static int activity_type;
	public static String bone_str;
	public static String bone_result;
	
	public static TextView txt_result1;
	public static TextView txt_result2;
	public static TextView txt_result3;
	
	public static ImageButton btn_back4;
	public static ImageButton btn_menu4;
	public static Button btn_save;
	public static Button btn_replay;
	public static Button btn_share;
	
	public static View name_input_layout;
	public static AlertDialog name_input_dialog;
	
	public static int INPUT_DIALOG_ID = 3;
	
	public static BoneDAO boneDAO;
	
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
        activity_type = extras.getInt("activity_type");
        
        btn_back4 = (ImageButton) findViewById(R.id.btn_back4);
        btn_menu4 = (ImageButton) findViewById(R.id.btn_menu4);
        txt_result1 = (TextView) findViewById(R.id.txt_result1);
        txt_result2 = (TextView) findViewById(R.id.txt_result2);
        txt_result3 = (TextView) findViewById(R.id.txt_result3);
        btn_save = (Button) findViewById(R.id.btn_save);
        btn_replay = (Button) findViewById(R.id.btn_replay);
        btn_share = (Button) findViewById(R.id.btn_share);
        
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        name_input_layout = inflater.inflate(R.layout.name_input, null);
        Button btn_name_confirm = (Button) name_input_layout.findViewById(R.id.btn_name_confirm);
        Button btn_name_cancel = (Button) name_input_layout.findViewById(R.id.btn_name_cancel);
        
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setView(name_input_layout);
		name_input_dialog = builder.create();
		name_input_dialog.setTitle(getString(R.string.txt_name_input_title));
		
		boneDAO = new BoneDAO(context);
		
		btn_name_confirm.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
		        TextView textView = (TextView) name_input_layout.findViewById(R.id.edit_text_name);
		        
		        if(date_type == Constant.TYPE_DATE_LUNAR)
		        {
		        	boneDAO.insertBone(textView.getText().toString(), sex, year, month, day, hour, minute, Constant.TYPE_RECORD_NORMAL);
		        }
		        else 
		        {
		        	boneDAO.insertBoneAD(textView.getText().toString(), sex, year, month, day, hour, minute, Constant.TYPE_RECORD_NORMAL);
				}
				
				name_input_dialog.dismiss();
				
				Toast.makeText(context, context.getString(R.string.txt_name_input_success), 1000).show();
			}
		});
		
		btn_name_cancel.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				name_input_dialog.cancel();
			}
		});
        
        btn_back4.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(Result.this, SelectDate.class);
				
				if(activity_type == Constant.TYPE_ACTIVITY_HISTORY)
				{
					intent = new Intent(Result.this, HistoryList.class);
				}
				else if(activity_type == Constant.TYPE_ACTIVITY_CELEBRITY)
				{
					intent = new Intent(Result.this, CelebrityList.class);
				}
				
				startActivity(intent);
			}
		});
        
        btn_menu4.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(Result.this, Menu.class);
				startActivity(intent);
			}
		});
        
        btn_save.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				MyDebug.print("btn_save.....................");

				name_input_dialog.show();
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
        
        btn_share.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(Intent.ACTION_SEND);
			    intent.setType("text/plain");
			    intent.putExtra(Intent.EXTRA_TEXT, bone_str);  
			    startActivity(Intent.createChooser(intent, "Share with"));
			
			}
		});
        
        if(activity_type != Constant.TYPE_ACTIVITY_NORMAL)
        {
        	btn_save.setVisibility(View.INVISIBLE);
        	btn_replay.setVisibility(View.INVISIBLE);
        }
        else
        {
        	btn_save.setVisibility(View.VISIBLE);
        	btn_replay.setVisibility(View.VISIBLE);
        }
        
        if(date_type == Constant.TYPE_DATE_LUNAR)
        {
        	bone = BoneCalculate.calculate(year, month, day, hour, minute);
        }
        else 
        {
        	bone = BoneCalculate.calculateAD(year, month, day, hour, minute);
		}
        
        bone_str = bone / 10 + "兩" + bone % 10 + "錢";
		bone_result = BoneCalculate.getResultString(context, bone, sex);
		bone_result = bone_result.replace("，", "，\n");
		
        txt_result1.setText(bone_str + "\n\n" + bone_result);
        
        txt_result2.setText(getString(Constant.STR_DETAIL_ID[bone - 22]));
        
        ServerUtil serverUtil = new ServerUtil(this);
        serverUtil.getBoneCount(bone, new FindCallback()
		{
			public void done(List<ParseObject> objects, ParseException e)
			{
				
				
			}
		});
    }
}