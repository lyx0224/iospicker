package iospicker.lib;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;


public class BasePickerView
{
	private final FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM);

	private Context context;
	protected ViewGroup contentContainer;
	private ViewGroup rootView;// 附加View 的 根View
	protected ViewGroup mParent;


	public BasePickerView(Context context, ViewGroup mParent)
	{
		this.context = context;
		this.mParent = mParent;

		initViews();
		init();
		initEvents();
	}

	protected void initViews()
	{
		LayoutInflater layoutInflater = LayoutInflater.from(context);
		rootView = (ViewGroup) layoutInflater.inflate(R.layout.layout_basepickerview, mParent, false);
		rootView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
		contentContainer = (ViewGroup) rootView.findViewById(R.id.content_container);
		contentContainer.setLayoutParams(params);
	}

	protected void init()
	{
//		inAnim = getInAnimation();
//		outAnim = getOutAnimation();
	}

	protected void initEvents()
	{
	}

	/**
	 * show的时候调用
	 * 
	 * @param view
	 *            这个View
	 */
	private void onAttached(View view)
	{
		mParent.addView(view);
//		contentContainer.startAnimation(inAnim);
	}

	/**
	 * 添加这个View到Activity的根视图
	 */
	public void show()
	{
		onAttached(rootView);
	}


	public View findViewById(int id)
	{
		return contentContainer.findViewById(id);
	}
}
