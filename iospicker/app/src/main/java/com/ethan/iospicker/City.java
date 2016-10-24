package com.ethan.iospicker;

import java.util.ArrayList;

import iospicker.lib.CityWheelModel;

/**
 * Created by HUITI on 16/10/24.
 */

public class City implements CityWheelModel {

    private String name;

    private int id;

    public City(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public ArrayList<CityWheelModel> getSecondModel() {
        return null;
    }
}
