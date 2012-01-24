package com.btxiong.BoneTest.data;

import java.util.ArrayList;
import java.util.List;

import com.btxiong.BoneTest.util.BoneCalculate;
import com.btxiong.BoneTest.util.Constant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class BoneDAO
{
	private static BoneDBHelper boneDBHelper;
	
	public BoneDAO(Context context)
	{
		boneDBHelper = new BoneDBHelper(context);
	}
	
	/**
	 * refresh the database
	 */
	public void refreshDB()
	{
		boneDBHelper.dropTable(boneDBHelper.getWritableDatabase());
		boneDBHelper.onCreate(boneDBHelper.getWritableDatabase());
	}
	
	//获得历史列表
	public List<BoneObject> getHistoryList()
	{
		List<BoneObject> historyList = new ArrayList<BoneObject>();
		
		Cursor cursor = boneDBHelper.getReadableDatabase().query(BoneDBHelper.TABLE_BONE, 
														new String[]{BoneObject.COL_ID, BoneObject.COL_NAME, BoneObject.COL_SEX, BoneObject.COL_YEAR, 
																	 BoneObject.COL_MONTH, BoneObject.COL_DAY, BoneObject.COL_HOUR, BoneObject.COL_MINUTE, BoneObject.COL_TYPE, BoneObject.COL_CREATED_DATE}, 
																	 BoneObject.COL_TYPE + " = " + Constant.TYPE_RECORD_NORMAL, null, null, null, BoneObject.COL_CREATED_DATE + " DESC");
		
		if(cursor != null)
		{
			cursor.moveToFirst();
			
			while (!cursor.isAfterLast())
			{
				BoneObject boneObject = new BoneObject();
				
				boneObject.setId(cursor.getInt(0));
				boneObject.setName(cursor.getString(1));
				boneObject.setSex(cursor.getInt(2));
				boneObject.setYear(cursor.getInt(3));
				boneObject.setMonth(cursor.getInt(4));
				boneObject.setDay(cursor.getInt(5));
				boneObject.setHour(cursor.getInt(6));
				boneObject.setMinute(cursor.getInt(7));
				boneObject.setType(cursor.getInt(8));
				boneObject.setCreated_date(cursor.getString(9));
				
				historyList.add(boneObject);
				
				cursor.moveToNext();
			}
		}
		
		cursor.close();
		boneDBHelper.close();
		
		return historyList;
	}
	
	//获得名人列表
	public List<BoneObject> getCelebrityList()
	{
		List<BoneObject> celebrityList = new ArrayList<BoneObject>();
		
		Cursor cursor = boneDBHelper.getReadableDatabase().query(BoneDBHelper.TABLE_BONE, 
														new String[]{BoneObject.COL_ID, BoneObject.COL_NAME, BoneObject.COL_SEX, BoneObject.COL_YEAR, 
																	 BoneObject.COL_MONTH, BoneObject.COL_DAY, BoneObject.COL_HOUR, BoneObject.COL_MINUTE, BoneObject.COL_TYPE, BoneObject.COL_CREATED_DATE}, 
																	 BoneObject.COL_TYPE + " = " + Constant.TYPE_RECORD_CELEBRITY, null, null, null, BoneObject.COL_NAME + " ASC");
		
		if(cursor != null)
		{
			cursor.moveToFirst();
			
			while (!cursor.isAfterLast())
			{
				BoneObject boneObject = new BoneObject();
				
				boneObject.setId(cursor.getInt(0));
				boneObject.setName(cursor.getString(1));
				boneObject.setSex(cursor.getInt(2));
				boneObject.setYear(cursor.getInt(3));
				boneObject.setMonth(cursor.getInt(4));
				boneObject.setDay(cursor.getInt(5));
				boneObject.setHour(cursor.getInt(6));
				boneObject.setMinute(cursor.getInt(7));
				boneObject.setType(cursor.getInt(8));
				boneObject.setCreated_date(cursor.getString(9));
				
				celebrityList.add(boneObject);
				
				cursor.moveToNext();
			}
		}
		
		cursor.close();
		boneDBHelper.close();
		
		return celebrityList;
	}
	
	//输入个人信息， 农历
	public void insertBone(String _name, int _sex, int _year, int _month, int _day, int _hour, int _minute, int _type)
	{
		ContentValues contentValues = new ContentValues();
		contentValues.put(BoneObject.COL_NAME, _name);
		contentValues.put(BoneObject.COL_SEX, _sex);
		contentValues.put(BoneObject.COL_YEAR, _year);
		contentValues.put(BoneObject.COL_MONTH, _month);
		contentValues.put(BoneObject.COL_DAY, _day);
		contentValues.put(BoneObject.COL_HOUR, _hour);
		contentValues.put(BoneObject.COL_MINUTE, _minute);
		contentValues.put(BoneObject.COL_TYPE, _type);
		
		boneDBHelper.getWritableDatabase().insert(BoneDBHelper.TABLE_BONE, null, contentValues);
		
		boneDBHelper.close();
	}
	
	//输入个人信息， 公历
	public void insertBoneAD(String _name, int _sex, int _year, int _month, int _day, int _hour, int _minute, int _type)
	{
		int[] lunar = BoneCalculate.getLunar(_year, _month, _day);
		
		ContentValues contentValues = new ContentValues();
		contentValues.put(BoneObject.COL_NAME, _name);
		contentValues.put(BoneObject.COL_SEX, _sex);
		contentValues.put(BoneObject.COL_YEAR, lunar[0]);
		contentValues.put(BoneObject.COL_MONTH, lunar[1]);
		contentValues.put(BoneObject.COL_DAY, lunar[2]);
		contentValues.put(BoneObject.COL_HOUR, _hour);
		contentValues.put(BoneObject.COL_MINUTE, _minute);
		contentValues.put(BoneObject.COL_TYPE, _type);
		
		boneDBHelper.getWritableDatabase().insert(BoneDBHelper.TABLE_BONE, null, contentValues);
		
		boneDBHelper.close();
	}
	
	public int deleteAllCelebrity()
	{
		return boneDBHelper.getWritableDatabase().delete(BoneDBHelper.TABLE_BONE, BoneObject.COL_TYPE + " = " + Constant.TYPE_RECORD_CELEBRITY, null);
	}
}
