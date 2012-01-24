package com.btxiong.BoneTest.util;

import java.util.List;

import android.content.Context;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class ServerUtil
{
	public final static String OBJECT_NAME = "boneObject";
	public static int count;
	
	public ServerUtil(Context context)
	{
		Parse.initialize(context, Constant.PARSE_APP_ID, Constant.PARSE_CLIENT_KEY);
	}
	
	public void addBone(int bone, int new_count)
	{
		ParseObject parseObject = new ParseObject(OBJECT_NAME);
		parseObject.put("bone", bone);
		parseObject.put("count", new_count);
		parseObject.saveInBackground();
	}
	
	public void getBoneCount(int bone, FindCallback callback)
	{
		ParseQuery query = new ParseQuery(OBJECT_NAME);
		query.whereEqualTo("bone", bone);
		query.findInBackground(callback);
	}
	
	//发布前调用
	public void initializeData()
	{
		try
		{
			for(int i=22;i<=71;i++)
			{
				ParseObject parseObject = new ParseObject(OBJECT_NAME);
				parseObject.put("bone", i);
				parseObject.put("count", (71-i) * 5);
				parseObject.save();
			}
		}
		catch (ParseException e)
		{
			MyDebug.print(e.getLocalizedMessage());
		}
	}
}
