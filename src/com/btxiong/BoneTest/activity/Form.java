package com.btxiong.BoneTest.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.provider.Contacts.ContactMethods;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.btxiong.BoneTest.R;
import com.btxiong.BoneTest.util.BoneCalculate;
import com.btxiong.BoneTest.util.MyDebug;

public class Form extends Activity 
{
	Context context;
	
	public static EditText textView_name;
	public static RadioButton RadioButton_sex1;
	public static RadioButton RadioButton_sex2;
	public static RadioButton RadioButton_dateType1;
	public static RadioButton RadioButton_dateType2;
	public static Spinner spinner_year;
	public static Spinner spinner_month;
	public static Spinner spinner_days;
	public static Spinner spinner_hour;
	public static Spinner spinner_minute;
	public static Button button_submit;
	
	static int start_year = 1921;

	static String name = "";
	static int year_pos = 60;
	static int month_pos = 0;
	static int day_pos = 0;
	static int hour_pos = 11;
	static int minute_pos = 0;
	static int sex = 0;
	static int dateType = 0;
	
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.form);
        
        context = this;
        
        textView_name = (EditText) findViewById(R.id.EditText_name);
        RadioButton_sex1 = (RadioButton) findViewById(R.id.RadioButton_sex1);
		RadioButton_sex2 = (RadioButton) findViewById(R.id.RadioButton_sex2);
		RadioButton_dateType1 = (RadioButton) findViewById(R.id.RadioButton_dateType1);
		RadioButton_dateType2 = (RadioButton) findViewById(R.id.RadioButton_dateType2);
		spinner_year = (Spinner) findViewById(R.id.Spinner_year);
		spinner_month = (Spinner) findViewById(R.id.Spinner_month);
		spinner_days = (Spinner) findViewById(R.id.Spinner_day);
		spinner_hour = (Spinner) findViewById(R.id.Spinner_hour);
		spinner_minute = (Spinner) findViewById(R.id.Spinner_minute);
		button_submit = (Button) findViewById(R.id.Button_submit);
		
		generateYearList(year_pos);
		generateMonthList(month_pos);
		generateDayList(start_year+year_pos, 1+month_pos, day_pos);
		generateHourList(hour_pos);
		generateMinutesList(minute_pos);
		
		setNameAction();
		setSexRadioAction();
		setDateRadioAction();
		setQueryButtonAction();
    }
    
    /**
	 * 生成年份列表 1921-2020
	 * 
	 * @return
	 */
	public void generateYearList(int pos)
	{
		List<String> years = new ArrayList<String>();

		for (int i = 1921; i <= 2020; i++)
		{
			years.add(i + context.getString(R.string.form_year_str));
		}

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, years);
		spinner_year.setAdapter(adapter);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spinner_year.setSelection(pos);
		spinner_year.setOnItemSelectedListener(new OnItemSelectedListener()
		{

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3)
			{
				year_pos = arg0.getSelectedItemPosition();
				//generateDayList(start_year+year_pos, 1+month_pos, day_pos);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0)
			{
				
				
			}
		});
	}

	/**
	 * 生成月份列表 1-12
	 * 
	 * @return
	 */
	public void generateMonthList(int pos)
	{
		List<String> month = new ArrayList<String>();

		for (int i = 1; i <= 12; i++)
		{
			String str = i + context.getString(R.string.form_month_str);
			if (i < 10)
			{
				str = "0" + str;
			}
			month.add(str);
		}

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, month);
		spinner_month.setAdapter(adapter);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spinner_month.setSelection(pos);
		spinner_month.setOnItemSelectedListener(new OnItemSelectedListener()
		{

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3)
			{
				month_pos = arg0.getSelectedItemPosition();
				generateDayList(start_year+year_pos, 1+month_pos, day_pos);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0)
			{
				
				
			}
		});
	}

	/**
	 * 生成天数列表
	 * 
	 * @param year
	 * @param month
	 * @param pos
	 */
	public void generateDayList(int year, int month, int pos)
	{
		List<String> days = new ArrayList<String>();

		int max_days = 30;

		// 判断是否为农历
		if (dateType == 0)
		{
			max_days = BoneCalculate.getDaysByMonth(year, month);
			//max_days = 31;
		}

		for (int i = 1; i <= max_days; i++)
		{
			String str = i + context.getString(R.string.form_day_str);
			if (i < 10)
			{
				str = "0" + str;
			}
			days.add(str);
		}
		
		if(pos > max_days)
		{
			pos = 0;
		}

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, days);
		spinner_days.setAdapter(adapter);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		spinner_days.setSelection(pos, true);
		
		spinner_days.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3)
			{
				day_pos = arg0.getSelectedItemPosition();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0)
			{
				
				
			}
		});
	}

	/**
	 * 生成小时列表 0-23
	 * 
	 * @return
	 */
	public void generateHourList(int pos)
	{
		List<String> hours = new ArrayList<String>();

		for (int i = 0; i <= 23; i++)
		{
			String str = i + context.getString(R.string.form_hour_str);
			if (i < 10)
			{
				str = "0" + str;
			}
			hours.add(str);
		}

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, hours);
		spinner_hour.setAdapter(adapter);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spinner_hour.setSelection(pos);
		spinner_hour.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3)
			{
				hour_pos = arg0.getSelectedItemPosition();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0)
			{
				
				
			}
		});
	}

	/**
	 * 生成分钟列表 0-59
	 * 
	 * @return
	 */
	public void generateMinutesList(int pos)
	{
		List<String> minutes = new ArrayList<String>();

		for (int i = 0; i <= 59; i++)
		{
			String str = i + context.getString(R.string.form_minute_str);
			if (i < 10)
			{
				str = "0" + str;
			}
			minutes.add(str);
		}

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, minutes);
		spinner_minute.setAdapter(adapter);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spinner_minute.setSelection(pos);
		spinner_minute.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3)
			{
				minute_pos = arg0.getSelectedItemPosition();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0)
			{
				
				
			}
		});
	}
	
	/**
	 * 设置姓名输入框事件
	 */
	public void setNameAction()
	{
		if(!"".equals(name))
		{
			textView_name.setText(name);
		}
	}
	
	/**
	 * 设置性别选项事件
	 */
	public void setSexRadioAction()
	{
		if(sex == 1)
		{
			RadioButton_sex2.setChecked(true);
		}
		else
		{
			RadioButton_sex1.setChecked(true);
		}
			
		
		RadioButton_sex1.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{	
				sex = 0;
			}
		});
		
		RadioButton_sex2.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{	
				sex = 1;
			}
		});
	}
	
	/**
	 * 设置公历，农历选项的事件
	 */
	public void setDateRadioAction()
	{
		if(dateType == 1)
		{
			RadioButton_dateType2.setChecked(true);
		}
		else
		{
			RadioButton_dateType1.setChecked(true);
		}
		
		RadioButton_dateType1.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{	
				dateType = 0;
				//generateDayList(start_year+year_pos, 1+month_pos, day_pos);
			}
		});
		
		RadioButton_dateType2.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				dateType = 1;
				
				if(day_pos > 29)
				{
					day_pos = 0;
				}
				//generateDayList(start_year+year_pos, 1+month_pos, day_pos);
			}
		});
	}
	
	/**
	 * 设置查询按钮事件
	 */
	public void setQueryButtonAction()
	{
		final Button button_submit = (Button) findViewById(R.id.Button_submit);

		button_submit.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				Query();
			}
		});
	}
	

	/**
	 * 执行查询
	 */
	public void Query()
	{
		name = ((EditText) findViewById(R.id.EditText_name)).getText().toString();
		
		int year = start_year + year_pos;
		int month = 1 + month_pos;
		int day = 1 + day_pos;
		int hour = hour_pos;
		int minute = minute_pos;
		
		int bone = 0;

		if (dateType == 0)
		{
			bone = BoneCalculate.calculateAD(year, month, day, hour, minute);
		}
		else
		{
			bone = BoneCalculate.calculate(year, month, day, hour, minute);
		}
		
		//MyDebug.print(bone_result);
		
		Intent intent = new Intent(Form.this, Result.class);
		intent.putExtra("name", name);
		intent.putExtra("sex", sex);
		intent.putExtra("year", year);
		intent.putExtra("month", year);
		intent.putExtra("day", year);
		intent.putExtra("hour", year);
		intent.putExtra("minute", year);
		intent.putExtra("bone", bone);
		
		startActivity(intent);
	}
}
