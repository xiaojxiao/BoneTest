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

public class SelectSex extends Activity 
{
	public static Context context;
	
	public static ImageButton btn_back1;
	public static ImageButton btn_male;
	public static ImageButton btn_female;
	public static TextView txt_hint_sex;
	
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.select_sex);
        
        context = this;
        
        btn_back1 = (ImageButton) findViewById(R.id.btn_back1);
        btn_male = (ImageButton) findViewById(R.id.btn_male);
        btn_female = (ImageButton) findViewById(R.id.btn_female);
        txt_hint_sex = (TextView) findViewById(R.id.txt_hint_sex);
        
        btn_back1.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(SelectSex.this, Menu.class);
				startActivity(intent);
			}
		});
        
        //使用自定义字体
        Typeface tf = Typeface.createFromAsset(getAssets(),"KAIU.TTF");
        txt_hint_sex.setTypeface(tf);
        
        btn_male.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				Toast.makeText(context, "This is male", 1000).show();
				
				Intent intent = new Intent(SelectSex.this, SelectDateType.class);
				intent.putExtra("sex", 0);
				startActivity(intent);
			}
		});
        
        btn_female.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				Toast.makeText(context, "This is female", 1000).show();
				
				Intent intent = new Intent(SelectSex.this, SelectDateType.class);
				intent.putExtra("sex", 1);
				startActivity(intent);
			}
		});
    }
}