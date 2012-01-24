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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.btxiong.BoneTest.R;
import com.btxiong.BoneTest.data.BoneDAO;
import com.btxiong.BoneTest.data.BoneObject;
import com.btxiong.BoneTest.util.Constant;

public class HistoryList extends Activity 
{
	public static Context context;
	
	public static ListView listview_history;
	public static ImageButton btn_back5;
	public static ImageButton btn_menu5;
	
	public static List<BoneObject> history_list;
	
	public static Typeface font_type_face;
	
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.history_list);

        context = this;
        
        btn_back5 = (ImageButton) findViewById(R.id.btn_back5);
        btn_menu5 = (ImageButton) findViewById(R.id.btn_menu5);
        listview_history = (ListView) findViewById(R.id.listview_history);
        
        btn_back5.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(HistoryList.this, Menu.class);
				startActivity(intent);
			}
		});
        
        btn_menu5.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(HistoryList.this, Menu.class);
				startActivity(intent);
			}
		});
        
        BoneDAO boneDAO = new BoneDAO(context);
        history_list = boneDAO.getHistoryList();
        
        List<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();

		for(BoneObject bone : history_list)
		{
			HashMap<String, Object> map = new HashMap<String, Object>();
			
			map.put("name", bone.getName());
			
			listItem.add(map);
		}
		
		SimpleAdapter listItemAdapter = new SimpleAdapter(this, listItem, R.layout.list_item, 
				new String[]{"name"}, 
				new int[]{R.id.list_item_name});
		
		listview_history.setAdapter(listItemAdapter);
		listview_history.setOnItemClickListener(new OnItemClickListener()
		{
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				Intent intent = new Intent(HistoryList.this, Result.class);
				
				BoneObject bone = history_list.get(position);
				
				intent.putExtra("sex", bone.getSex());
				intent.putExtra("date_type", Constant.TYPE_DATE_LUNAR);
				intent.putExtra("year", bone.getYear());
				intent.putExtra("month", bone.getMonth());
				intent.putExtra("day", bone.getDay());
				intent.putExtra("hour", bone.getHour());
				intent.putExtra("minute", bone.getMinute());
				intent.putExtra("activity_type", Constant.TYPE_ACTIVITY_HISTORY);
				
				startActivity(intent);
			}
		});
		
		listview_history.setDividerHeight(1);
    }
}