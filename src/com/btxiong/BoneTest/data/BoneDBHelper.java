package com.btxiong.BoneTest.data;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import com.btxiong.BoneTest.util.MyDebug;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BoneDBHelper extends SQLiteOpenHelper
{
	public static final int DATABASE_VERSION = 1;
	
	public static final String DATABASE_NAME = "bone_test";
	
	public static final String TABLE_BONE = "bone_record";
    
	//所有日期均为农历
    private static final String SQL_CREATE_TABLE_BONE = "CREATE TABLE IF NOT EXISTS " + TABLE_BONE +  
    													   " ( id INTEGER PRIMARY KEY AUTOINCREMENT, " + 
    													   "name VARCHAR, " +
    													   "sex INTEGER, " + 
    													   "year INTEGER, " +
    													   "month INTEGER, " +
    													   "day INTEGER," +
    													   "hour INTEGER," +
    													   "minute INTEGER," +
    													   "type INTEGER," +	//0: 普通 1: 名人
    													   "created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP )";
    
    private static final String SQL_DROP_TABLE_BONE = "DROP TABLE IF EXISTS " + TABLE_BONE;
    
    public static Context dbContext;
	
	
	public BoneDBHelper(Context context)
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.dbContext = context;
	}

	/**
	 * Trigger when create
	 */
	public void onCreate(SQLiteDatabase db)
	{
		MyDebug.print("Enter DB onCreate...........");
		db.execSQL(SQL_CREATE_TABLE_BONE);
	}

	/**
	 * Trigger when upgrade
	 */
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		MyDebug.print("Enter onUpgrade...........");
		
		/*
		if(newVersion > oldVersion)
		{
			dropTable(db);
			onCreate(db);
		}
		*/
	}
	
	/**
	 * Drop all tables
	 */
	public void dropTable(SQLiteDatabase db)
	{
		db.execSQL(SQL_DROP_TABLE_BONE);
	}
}
