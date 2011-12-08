package com.btxiong.BoneTest.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.btxiong.BoneTest.R;

public class SelectDateType extends Activity 
{
	public static Context context;
	
	public static ImageButton btn_back2;
	public static ImageButton btn_new_cal;
	public static ImageButton btn_old_cal;
	public static TextView txt_hint_date_type;
	
	public static int sex;
	
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.select_date_type);
        
        context = this;
        sex = getIntent().getExtras().getInt("sex");
        
        btn_back2 = (ImageButton) findViewById(R.id.btn_back2);
        btn_new_cal = (ImageButton) findViewById(R.id.btn_new_cal);
        btn_old_cal = (ImageButton) findViewById(R.id.btn_old_cal);
        txt_hint_date_type = (TextView) findViewById(R.id.txt_hint_date_type);
        
        btn_back2.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(SelectDateType.this, SelectSex.class);
				startActivity(intent);
			}
		});
        
        //使用自定义字体
        //Typeface tf = Typeface.createFromAsset(getAssets(),"font.ttf");
        //txt_hint_date_type.setTypeface(tf);
        
        btn_new_cal.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				//Toast.makeText(context, "This is btn_new_cal", 1000).show();
				
				Intent intent = new Intent(SelectDateType.this, SelectDate.class);
				intent.putExtra("sex", sex);
				intent.putExtra("date_type", 0);
				startActivity(intent);
			}
		});
        
        btn_old_cal.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				//Toast.makeText(context, "This is btn_old_cal", 1000).show();
				
				Intent intent = new Intent(SelectDateType.this, SelectDate.class);
				intent.putExtra("sex", sex);
				intent.putExtra("date_type", 1);
				startActivity(intent);
			}
		});
    }
}