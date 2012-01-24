package com.btxiong.BoneTest.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

import com.btxiong.BoneTest.R;
import com.btxiong.BoneTest.data.BoneDAO;
import com.btxiong.BoneTest.data.BoneObject;
import com.btxiong.BoneTest.util.BoneCalculate;
import com.btxiong.BoneTest.util.Constant;
import com.btxiong.BoneTest.util.MyDebug;

public class CelebrityList extends Activity 
{
	public static Context context;
	
	public static ListView listview_celebrity;
	public static ImageButton btn_back6;
	public static ImageButton btn_menu6;
	
	public static List<BoneObject> celebrity_list;
	
	public static Typeface font_type_face;
	
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.celebrity_list);

        context = this;
        
        btn_back6 = (ImageButton) findViewById(R.id.btn_back6);
        btn_menu6 = (ImageButton) findViewById(R.id.btn_menu6);
        listview_celebrity = (ListView) findViewById(R.id.listview_celebrity);
        
        btn_back6.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(CelebrityList.this, Menu.class);
				startActivity(intent);
			}
		});
        
        btn_menu6.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(CelebrityList.this, Menu.class);
				startActivity(intent);
			}
		});
        
        BoneDAO boneDAO = new BoneDAO(context);
        celebrity_list = boneDAO.getCelebrityList();
        
        List<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();

		for(BoneObject bone : celebrity_list)
		{
			HashMap<String, Object> map = new HashMap<String, Object>();
			
			map.put("name", bone.getName());
			
			listItem.add(map);
		}
		
		SimpleAdapter listItemAdapter = new SimpleAdapter(this, listItem, R.layout.list_item, 
				new String[]{"name"}, 
				new int[]{R.id.list_item_name});
		
		listview_celebrity.setAdapter(listItemAdapter);
		listview_celebrity.setOnItemClickListener(new OnItemClickListener()
		{
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				Intent intent = new Intent(CelebrityList.this, Result.class);
				
				BoneObject bone = celebrity_list.get(position);
				
				intent.putExtra("sex", bone.getSex());
				intent.putExtra("date_type", Constant.TYPE_DATE_LUNAR);
				intent.putExtra("year", bone.getYear());
				intent.putExtra("month", bone.getMonth());
				intent.putExtra("day", bone.getDay());
				intent.putExtra("hour", bone.getHour());
				intent.putExtra("minute", bone.getMinute());
				intent.putExtra("activity_type", Constant.TYPE_ACTIVITY_CELEBRITY);
				
				startActivity(intent);
			}
		});
		
		listview_celebrity.setDividerHeight(1);
        
    }
}