package com.btxiong.BoneTest.data;

public class BoneObject
{
	private int id;
	private String name;
	private int sex;
	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
	private int type;
	private String created_date;
	
	public static String COL_ID = "id";
	public static String COL_NAME = "name";
	public static String COL_SEX = "sex";
	public static String COL_YEAR = "year";
	public static String COL_MONTH = "month";
	public static String COL_DAY = "day";
	public static String COL_HOUR = "hour";
	public static String COL_MINUTE = "minute";
	public static String COL_TYPE = "type";
	public static String COL_CREATED_DATE = "created_date";
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public int getSex()
	{
		return sex;
	}
	public void setSex(int sex)
	{
		this.sex = sex;
	}
	public int getYear()
	{
		return year;
	}
	public void setYear(int year)
	{
		this.year = year;
	}
	public int getMonth()
	{
		return month;
	}
	public void setMonth(int month)
	{
		this.month = month;
	}
	public int getDay()
	{
		return day;
	}
	public void setDay(int day)
	{
		this.day = day;
	}
	public int getHour()
	{
		return hour;
	}
	public void setHour(int hour)
	{
		this.hour = hour;
	}
	public int getMinute()
	{
		return minute;
	}
	public void setMinute(int minute)
	{
		this.minute = minute;
	}
	public int getType()
	{
		return type;
	}
	public void setType(int type)
	{
		this.type = type;
	}
	public String getCreated_date()
	{
		return created_date;
	}
	public void setCreated_date(String created_date)
	{
		this.created_date = created_date;
	}
}
