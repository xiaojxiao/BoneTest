package com.btxiong.BoneTest.activity;

import kankan.wheel.widget.OnWheelChangedListener;
import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.NumericWheelAdapter;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.btxiong.BoneTest.R;
import com.btxiong.BoneTest.util.BoneCalculate;
import com.btxiong.BoneTest.util.Constant;
import com.btxiong.BoneTest.util.MyDebug;

public class SelectDate extends Activity 
{
	public static Context context;
	
	public static ImageButton btn_back3;
	public static ImageButton btn_menu3;
	public static TextView txt_hint_date;
	public static Button btn_select_date_ok;
	
	public static WheelView wheel_year;
	public static WheelView wheel_month;
	public static WheelView wheel_day;
	public static WheelView wheel_hour;
	public static WheelView wheel_minute;
	
	public static int w_year;
	public static int w_month;
	public static int w_day;
	public static int w_hour;
	public static int w_minute;
	
	public static int sex;
	public static int date_type;
	
	public static Typeface font_type_face;
	
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.select_date);
        
        w_year = 1980;
    	w_month = 1;
    	w_day = 1;
    	w_hour = 12;
    	w_minute = 0;
        
        context = this;
        Bundle extras = getIntent().getExtras();
        sex = extras.getInt("sex");
        date_type = extras.getInt("date_type");
        
        btn_back3 = (ImageButton) findViewById(R.id.btn_back3);
        btn_menu3 = (ImageButton) findViewById(R.id.btn_menu3);
        txt_hint_date = (TextView) findViewById(R.id.txt_hint_date);
        btn_select_date_ok = (Button) findViewById(R.id.btn_select_date_ok);
        
        btn_back3.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(SelectDate.this, SelectDateType.class);
				startActivity(intent);
			}
		});
        
        btn_menu3.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(SelectDate.this, Menu.class);
				startActivity(intent);
			}
		});
        
        btn_select_date_ok.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				/*MyDebug.print((1900 + wheel_year.getCurrentItem()) + " -- " + 
							  (1 + wheel_month.getCurrentItem()) + " -- " +
							  (1 + wheel_day.getCurrentItem()) + " -- " +
							  wheel_hour.getCurrentItem() + " -- " +
							  wheel_minute.getCurrentItem());*/
				
				Intent intent = new Intent(SelectDate.this, Result.class);
				intent.putExtra("sex", sex);
				intent.putExtra("date_type", date_type);
				intent.putExtra("year", 1900 + wheel_year.getCurrentItem());
				intent.putExtra("month", 1 + wheel_month.getCurrentItem());
				intent.putExtra("day", 1 + wheel_day.getCurrentItem());
				intent.putExtra("hour", wheel_hour.getCurrentItem());
				intent.putExtra("minute", wheel_minute.getCurrentItem());
				intent.putExtra("activity_type", Constant.TYPE_ACTIVITY_NORMAL);
				startActivity(intent);
			}
		});
        
        //使用自定义字体
        //font_type_face = Typeface.createFromAsset(getAssets(),"font.ttf");
        //txt_hint_date.setTypeface(font_type_face);
        
        //绑定时间控件
		wheel_year = (WheelView) findViewById(R.id.wheel_year);
		wheel_month = (WheelView) findViewById(R.id.wheel_month);
		wheel_day = (WheelView) findViewById(R.id.wheel_day);
		wheel_hour = (WheelView) findViewById(R.id.wheel_hour);
		wheel_minute = (WheelView) findViewById(R.id.wheel_minute);
		
		OnWheelChangedListener listener = new OnWheelChangedListener() {
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                updateDays();
            }
        };
        
		wheel_year.setViewAdapter(new ChineseDateNumericAdapter(this, 1900, 2031, "%d" + getString(R.string.form_year_str), 2011));
		wheel_year.addChangingListener(listener);
		wheel_year.setCyclic(true);
		wheel_year.setCurrentItem(w_year - 1900);
		
		wheel_month.setViewAdapter(new ChineseDateNumericAdapter(this, 1, 12, "%d" + getString(R.string.form_month_str), 11));
		wheel_month.addChangingListener(listener);
		wheel_month.setCyclic(true);
		wheel_month.setCurrentItem(w_month - 1);
		
		wheel_day.setViewAdapter(new ChineseDateNumericAdapter(this, 1, 30, "%d" + getString(R.string.form_day_str), 11));
		//wheel_day.addChangingListener(listener);
		wheel_day.setCyclic(true);
		wheel_day.setCurrentItem(w_day - 1);
        
		wheel_hour.setViewAdapter(new ChineseDateNumericAdapter(this, 0, 23, "%02d" + getString(R.string.form_hour_str), 11));
		wheel_hour.setCyclic(true);
		wheel_hour.setCurrentItem(w_hour);

		wheel_minute.setViewAdapter(new ChineseDateNumericAdapter(this, 0, 59, "%02d" + getString(R.string.form_minute_str), 11));
		wheel_minute.setCyclic(true);
		wheel_minute.setCurrentItem(w_minute);
		
		updateDays();
    }
    
    private void updateDays()
    {
    	if(date_type == Constant.TYPE_DATE_AD)
    	{
	    	int numOfDays = BoneCalculate.getDaysByMonth(wheel_year.getCurrentItem(), wheel_month.getCurrentItem() + 1);
	    	int curDay = wheel_day.getCurrentItem();
	    	wheel_day.setViewAdapter(new ChineseDateNumericAdapter(this, 1, numOfDays, "%d" + getString(R.string.form_day_str), 11));
	    	
	    	if(curDay+1 > numOfDays)
	    	{
	    		curDay = numOfDays - 1;
	    	}
	    	
	    	wheel_day.setCurrentItem(curDay);
    	}
    }
    
    /**
     * Adapter for numeric wheels. Highlights the current value.
     */
    private class ChineseDateNumericAdapter extends NumericWheelAdapter {
        // Index of current item
        int currentItem;
        // Index of item to be highlighted
        int currentValue;
        
        /**
         * Constructor
         */
        public ChineseDateNumericAdapter(Context context, int minValue, int maxValue, String format, int current) {
            super(context, minValue, maxValue, format);
            this.currentValue = current;
            setTextSize(14);
        }
        
        @Override
        protected void configureTextView(TextView view) {
            super.configureTextView(view);
            if (currentItem == currentValue) {
                view.setTextColor(0xFF0000F0);
            }
            view.setTypeface(Typeface.SANS_SERIF);
        }
    }
}