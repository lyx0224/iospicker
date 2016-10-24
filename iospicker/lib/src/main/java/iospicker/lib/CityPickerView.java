package iospicker.lib;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;

//城市选择
public class CityPickerView<T> extends BasePickerView {
    private CityWheelOptions wheelOptions;

    public CityPickerView(Context context, ViewGroup parent) {
        super(context, parent);
        LayoutInflater.from(context).inflate(R.layout.pickerview_options, contentContainer);
        final View optionspicker = findViewById(R.id.optionspicker);
        wheelOptions = new CityWheelOptions(optionspicker);
    }

    public void setPicker(ArrayList<CityWheelModel> wheelModels) {
        wheelOptions.setPicker(wheelModels, true);
    }


    /**
     * 设置选中的item位置
     *
     * @param option1
     */
    public void setSelectOptions(int option1) {
        wheelOptions.setCurrentItems(option1, 0);
    }

    /**
     * 设置选中的item位置
     *
     * @param option1
     * @param option2
     */
    public void setSelectOptions(int option1, int option2) {
        wheelOptions.setCurrentItems(option1, option2);
    }


    /**
     * 设置选项的单位
     *
     * @param label1
     */
    public void setLabels(String label1) {
        wheelOptions.setLabels(label1, null);
    }

    /**
     * 设置选项的单位
     *
     * @param label1
     * @param label2
     */
    public void setLabels(String label1, String label2) {
        wheelOptions.setLabels(label1, label2);
    }


    /**
     * 设置是否循环滚动
     *
     * @param cyclic
     */
    public void setCyclic(boolean cyclic) {
        wheelOptions.setCyclic(cyclic);
    }

    public void setCyclic(boolean cyclic1, boolean cyclic2) {
        wheelOptions.setCyclic(cyclic1, cyclic2);
    }


    public void setOptPickerListener(OptPickerListener listener){
        wheelOptions.setOptPickerListener(listener);
    }

    public interface OptPickerListener{
        void onGetCurrent(String provinceName, int provinceId, String cityName, int cityId);
    }


}
