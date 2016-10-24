package iospicker.lib;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.Calendar;
import java.util.Date;


/**
 * Created by Sai on 15/11/22.
 */
public class TimePickerView extends BasePickerView
{
	public enum Type
	{
		ALL, YEAR_MONTH_DAY, HOURS_MINS, MONTH_DAY_HOUR_MIN, YEAR_MONTH
	}// 四种选择模式，年月日时分，年月日，时分，月日时分

	WheelTime wheelTime;

	public TimePickerView(Context context, ViewGroup parent, Type type)
	{
		super(context, parent);

		LayoutInflater.from(context).inflate(R.layout.pickerview_time, contentContainer);

		final View timepickerview = findViewById(R.id.timepicker);
		wheelTime = new WheelTime(timepickerview, type);

//		//默认当前时间
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTimeInMillis(System.currentTimeMillis());
//		int year = calendar.get(Calendar.YEAR);
//		int month = calendar.get(Calendar.MONTH);
//		int day = calendar.get(Calendar.DAY_OF_MONTH);
//		int hours = calendar.get(Calendar.HOUR_OF_DAY);
//		int minute = calendar.get(Calendar.MINUTE);
//
//		wheelTime.setPicker(year, month, day, hours, minute);

	}

	public void setPicker(int year, int month,int day){
		wheelTime.setPicker(year, month, day);
	}

	/**
	 * 设置可以选择的时间范围
	 * 
	 * @param startYear
	 * @param endYear
	 */
	public void setRange(int startYear, int endYear)
	{
		wheelTime.setStartYear(startYear);
		wheelTime.setEndYear(endYear);
	}

	/**
	 * 设置选中时间
	 * 
	 * @param date
	 */
	public void setTime(Date date)
	{
		Calendar calendar = Calendar.getInstance();
		if (date == null)
			calendar.setTimeInMillis(System.currentTimeMillis());
		else
			calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int hours = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		wheelTime.setPicker(year, month, day, hours, minute);
	}

	public void setCyclic(boolean cyclic)
	{
		wheelTime.setCyclic(cyclic);
	}


	public interface PickerListener{
		void onGetCurrent(int year, int month, int day);
	}


	public void setPickerListener(PickerListener listener){
		wheelTime.setWheelListener(listener);
	}
}
