package com.ethan.iospicker;

import java.util.ArrayList;

import iospicker.lib.CityWheelModel;

/**
 * Created by HUITI on 16/10/24.
 */

public class Province implements CityWheelModel {

    private String name;

    private int id;

    public Province(String name, int id) {
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
        ArrayList<CityWheelModel> citys = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            City city = new City("济南" + i, i);
            citys.add(city);
        }

        return citys;
    }
}
