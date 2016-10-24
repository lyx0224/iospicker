package com.ethan.iospicker;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Calendar;

import iospicker.lib.CityPickerView;
import iospicker.lib.CityWheelModel;
import iospicker.lib.TimePickerView;

/***
 * qq : 806997347
 * ethan
 * */

public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //日期

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        TimePickerView pickerView = new TimePickerView(this, (ViewGroup) findViewById(R.id.date_picker), TimePickerView.Type.YEAR_MONTH_DAY);
        pickerView.setRange(1950, 2016);
        pickerView.setPicker(year, month, day);
        pickerView.setPickerListener(new TimePickerView.PickerListener() {
            @Override
            public void onGetCurrent(int year, int month, int day) {

            }
        });
        pickerView.show();

        //城市
        CityPickerView cityPickerView = new CityPickerView(this, (ViewGroup) findViewById(R.id.city_picker));
        cityPickerView.setPicker(mockData());
        cityPickerView.setCyclic(false, false);
        cityPickerView.setOptPickerListener(new CityPickerView.OptPickerListener() {
            @Override
            public void onGetCurrent(String provinceName, int provinceId, String cityName, int cityId) {

            }
        });

        cityPickerView.show();


    }

    private ArrayList<CityWheelModel> mockData() {
        ArrayList<CityWheelModel> provinces = new ArrayList<>();

        Province p = new Province("山东", 0);
        provinces.add(p);

        return provinces;
    }
}
