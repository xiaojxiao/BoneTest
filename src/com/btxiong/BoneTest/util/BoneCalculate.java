package com.btxiong.BoneTest.util;

import android.content.Context;

import com.btxiong.BoneTest.R;

public class BoneCalculate
{
	//甲子年
	static int firstYear = 4;
	
	static int year_bone[] = {12,9,6,7,12,5,9,8,7,8,15,9,
					   16,8,8,19,12,6,8,7,5,15,6,16,
			           15,7,9,12,10,7,15,6,5,14,14,9,
			           7,7,9,12,8,7,13,5,14,5,9,17,
			           5,7,12,8,8,6,19,6,8,16,10,6};
	
	static int month_bone[]= {6,7,18,9,5,16,9,15,18,8,9,5};
	
	static int day_bone[]= {5,10,8,15,16,15,8,16,9,17,8,17,10,8,9,18,5,15,10,9,8,9,15,18,7,8,16,6};
	
	static int time_bone[] = {16,6,7,10,9,16,10,8,8,9,6,6};
	
	static int month_total_days[] = {0,31,59,90,120,151,181,212,243,273,304,334};//每个月1日之前的天数，2月只算28天

	static int LunarCalendarTable[] ={ 
	          2635,333387,  1701,  1748,267701,   694,  2391,133423,  1175,396438, /*1921-1930*/

	          3402,  3749,331177,  1453,   694,201326,  2350,465197,  3221,  3402, /*1931-1940*/

	        400202,  2901,  1386,267611,   605,  2349,137515,  2709,464533,  1738, /*1941-1950*/

	          2901,330421,  1242,  2651,199255,  1323,529706,  3733,  1706,398762, /*1951-1960*/

	          2741,  1206,267438,  2647,  1318,204070,  3477,461653,  1386,  2413, /*1961-1970*/

	        330077,  1197,  2637,268877,  3365,531109,  2900,  2922,398042,  2395, /*1971-1980*/

	          1179,267415,  2635,661067,  1701,  1748,398772,  2742,  2391,330031, /*1981-1990*/

	          1175,  1611,200010,  3749,527717,  1452,  2742,332397,  2350,  3222, /*1991-2000*/

	        268949,  3402,  3493,133973,  1386,464219,   605,  2349,334123,  2709, /*2001-2010*/

	          2890,267946,  2773,592565,  1210,  2651,395863,  1323,  2707,265877};/*2011-2020*/
	
	
	/**
	 * 根据称骨获得结果
	 * @param context
	 * @param bone
	 * @param sex
	 * @return
	 */
	public static String getResultString(Context context, int bone, int sex)
	{
		int base = 22;
		int id = Constant.STR_MALE_ID[bone - base];

		//女性
		if (sex == 1)
		{
			id = Constant.STR_FEMALE_ID[bone - base];
		}
		
		return context.getString(id);
	}
	
	/**
	 * 根据农历计算称骨
	 * @param year	年
	 * @param month	月	从1开始
	 * @param day	日	从1开始
	 * @param hour	小时
	 * @param minute	分钟
	 * @return
	 */
	public static int calculate(int year, int month, int day, int hour, int minute)
	{
		MyDebug.print(year + " - " + month + " - " + day + " - " + hour + " - " + minute);
		
		int bone = 0;
		
		if(day >= 29)
		{
			day = 28;
		}
		
		bone = year_bone[(year-firstYear)%60] + 
			   month_bone[month - 1] +
			   day_bone[day - 1] + 
			   time_bone[getLunarTimeIndex(hour)];
		
		return bone;
	}
	
	/**
	 * 根据公历计算称骨
	 * @param year	年
	 * @param month	月	从1开始
	 * @param day	日	从1开始
	 * @param hour	小时
	 * @param minute	分钟
	 * @return
	 */
	public static int calculateAD(int year, int month, int day, int hour, int minute)
	{	
		int[] lunar = getLunar(year, month, day);
		
		return calculate(lunar[0], lunar[1], lunar[2], hour, minute);
	}
	
	/**
	 * 公历转农历
	 * @param year	年
	 * @param month	月
	 * @param day	日
	 * @return int[0]:年  int[1]:月  int[2]:日
	 */
	public static int[] getLunar(int year, int month, int day)
	{
		//lunar[0]:年	lunar[1]:月		lunar[2]:日
		int[] lunar = new int[3];
		
		 //计算从1921年2月8日(正月初一)到现在所经历的天数。
	    int DaysCount	= (year - 1921) * 365  //本年到1921年之间的年数 * 每年365天
	    				  + (year - 1921) / 4    //本年到1921年之间的润年数，一个一天
	    				  + month_total_days[month - 1] //本月之前的天数
	    				  - 38                 //2月8日之前有38天
	    				  + day;                  //本日天数
	    
	    //判断今年是否是闰年
	    if((year%4==0) && (month>2))
	    {
	    	DaysCount++;
	    }
	    
	    boolean isRun = true;
	    int i = 0;
	    int n = 11;
	    int N = 11;
	    
	    //从1921年开始一个月一个月的递减，减到天数小于当月天数为止
	    while(isRun)
	    {
	    	if(LunarCalendarTable[i] >= 4095)	/*4095=0xFFF*/ /*低16位表示每个月的大小，闰月顺序排列，高16位表示闰月序数*/
	    	{
	    		n = 12;
	    		N = 12;
	    	}
	    	else
	    	{
	    		n = 11;
	    		N = 11;
	    	}
	    	
	    	while(n >= 0)
	    	{
	    		int bit = (LunarCalendarTable[i]>>n) & 1;	 //1-月大30天 0-月小29天
	    		
	    		if(DaysCount <= (29 + bit))
	    		{
	    			isRun = false;
	    			break;
	    		}
	    		
	    		DaysCount -= (29 + bit);
	    		
	    		n--;
	    	}
	    	
	    	if(!isRun)
	    	{
	    		break;
	    	}
	    	
	    	i++;
	    }
	    
	    lunar[0] = 1921 + i;
	    lunar[1] = N - n + 1;
	    lunar[2] = DaysCount;
	    
	    if(N == 12 && lunar[1] > (LunarCalendarTable[i] / 65536 + 1))
	    {
	    	lunar[1]--;
	    }
		
		return lunar;
	}
	
	/**
	 * 计算时辰
	 * @param hour 公历时间（24小时制）
	 * @return
	 */
	public static int getLunarTimeIndex(int hour)
	{
		int i = 0;
		
		i = (hour + 1)/2;
		
		if(i > 11)
		{
			i = 0;
		}
		
		return i;
	}
	
	/**
	 * 获得当前月的天数
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getDaysByMonth(int year, int month)
	{
		int[] month_day = {31,28,31,30,31,30,31,31,30,31,30,31};
		
		if(year < 0 || month < 1 || month > 12)
		{
			return 30;
		}
		
		int days = month_day[month-1];
		
		if(month==2 && year%4==0)
		{
			days = 29;
		}
		
		return days;
	}
}
