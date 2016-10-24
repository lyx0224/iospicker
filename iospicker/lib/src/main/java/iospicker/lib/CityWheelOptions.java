package iospicker.lib;

import android.view.View;


import java.util.ArrayList;

import iospicker.lib.adapter.ArrayWheelAdapter;
import iospicker.lib.lib.WheelView;


public class CityWheelOptions {
	private View view;
	private WheelView wv_option1;
	private WheelView wv_option2;

	private ArrayList<CityWheelModel> provinces;

    private boolean linkage = false;
    private OnItemSelectedListener wheelListener_option1;

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

	public CityWheelOptions(View view) {
		super();
		this.view = view;
		setView(view);
	}


	public void setPicker(ArrayList<CityWheelModel> data, boolean linkage) {
        this.linkage = linkage;
		this.provinces = data;
		if (data == null || data.size() == 0) return;

		int len = ArrayWheelAdapter.DEFAULT_LENGTH;
		// 选项1
		wv_option1 = (WheelView) view.findViewById(R.id.options1);
		wv_option1.setAdapter(new ArrayWheelAdapter(provinces, len));// 设置显示数据
		wv_option1.setCurrentItem(0);// 初始化时显示的数据
		// 选项2
		wv_option2 = (WheelView) view.findViewById(R.id.options2);
		wv_option2.setAdapter(new ArrayWheelAdapter(provinces.get(0).getSecondModel()));// 设置显示数据
		wv_option2.setCurrentItem(wv_option1.getCurrentItem());// 初始化时显示的数据

		int textSize = 25;

		wv_option1.setTextSize(textSize);
		wv_option2.setTextSize(textSize);

		// 联动监听器
        wheelListener_option1 = new OnItemSelectedListener() {

			@Override
			public void onItemSelected(int index) {
				int opt2Select = 0;
				opt2Select = wv_option2.getCurrentItem();//上一个opt2的选中位置
				//新opt2的位置，判断如果旧位置没有超过数据范围，则沿用旧位置，否则选中最后一项
				opt2Select = opt2Select >= provinces.get(index).getSecondModel().size() - 1 ? provinces.get(index).getSecondModel().size() - 1 : opt2Select;

				wv_option2.setAdapter(new ArrayWheelAdapter(provinces.get(index).getSecondModel()));
				wv_option2.setCurrentItem(opt2Select);
//				if (mOptions3Items != null) {
//                    wheelListener_option2.onItemSelected(opt2Select);
//				}
			}
		};
//        wheelListener_option2 = new OnItemSelectedListener() {
//
//			@Override
//			public void onItemSelected(int index) {
//				if (mOptions3Items != null) {
//                    int opt1Select = wv_option1.getCurrentItem();
//                    opt1Select = opt1Select >= mOptions3Items.size() - 1 ? mOptions3Items.size() - 1 : opt1Select;
//                    index = index >= mOptions2Items.get(opt1Select).size() - 1 ?  mOptions2Items.get(opt1Select).size() - 1 : index;
//					int opt3 = wv_option3.getCurrentItem();//上一个opt3的选中位置
//                    //新opt3的位置，判断如果旧位置没有超过数据范围，则沿用旧位置，否则选中最后一项
//                    opt3 = opt3 >= mOptions3Items.get(opt1Select).get(index).size() - 1 ? mOptions3Items.get(opt1Select).get(index).size() - 1 : opt3;
//
//					wv_option3.setAdapter(new ArrayWheelAdapter(mOptions3Items
//							.get(wv_option1.getCurrentItem()).get(
//                                    index)));
//					wv_option3.setCurrentItem(opt3);
//
//				}
//			}
//		};

//		// 添加联动监听
		if (linkage){
			wv_option1.addOnItemSelectedListener(wheelListener_option1);
		}
	}

	public void setOptPickerListener(final CityPickerView.OptPickerListener listener){
		if (listener == null) return;
		wv_option1.addOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(int index) {
				CityWheelModel province = wv_option1.getArrayWheelAdapterCurrent();
				CityWheelModel city = wv_option2.getArrayWheelAdapterCurrent();
				if (province != null && city != null){
					listener.onGetCurrent(province.getName(), province.getId(), city.getName(), city.getId());
				}
			}
		});

		wv_option2.addOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(int index) {
				CityWheelModel province = wv_option1.getArrayWheelAdapterCurrent();
				CityWheelModel city = wv_option2.getArrayWheelAdapterCurrent();
				if (province != null && city != null){
					listener.onGetCurrent(province.getName(), province.getId(), city.getName(), city.getId());
				}
			}
		});
	}

	/**
	 * 设置选项的单位
	 * 
	 * @param label1
	 * @param label2
	 */
	public void setLabels(String label1, String label2) {
		if (label1 != null)
			wv_option1.setLabel(label1);
		if (label2 != null)
			wv_option2.setLabel(label2);
	}

	/**
	 * 设置是否循环滚动
	 * 
	 * @param cyclic
	 */
	public void setCyclic(boolean cyclic) {
		wv_option1.setCyclic(cyclic);
		wv_option2.setCyclic(cyclic);
	}

	/**
	 * 分别设置第一二三级是否循环滚动
	 *
	 * @param cyclic1,cyclic2,cyclic3
	 */
	public void setCyclic(boolean cyclic1,boolean cyclic2) {
        wv_option1.setCyclic(cyclic1);
        wv_option2.setCyclic(cyclic2);
	}
    /**
     * 设置第二级是否循环滚动
     *
     * @param cyclic
     */
    public void setOption2Cyclic(boolean cyclic) {
        wv_option2.setCyclic(cyclic);
    }

	/**
	 * 返回当前选中的结果对应的位置数组 因为支持三级联动效果，分三个级别索引，0，1，2
	 * 
	 * @return
	 */
	public int[] getCurrentItems() {
		int[] currentItems = new int[2];
		currentItems[0] = wv_option1.getCurrentItem();
		currentItems[1] = wv_option2.getCurrentItem();
		return currentItems;
	}

	public void setCurrentItems(int option1, int option2) {
        if(linkage){
            itemSelected(option1, option2);
        }
        wv_option1.setCurrentItem(option1);
        wv_option2.setCurrentItem(option2);
	}

	private void itemSelected(int opt1Select, int opt2Select) {
			wv_option2.setAdapter(new ArrayWheelAdapter(provinces.get(opt1Select).getSecondModel()));
			wv_option2.setCurrentItem(opt2Select);
	}


}
