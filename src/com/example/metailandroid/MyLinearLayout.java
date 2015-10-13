package com.example.metailandroid;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

//com.example.metailandroid.MyLinearLayout
/**
 * 
 *<P> Title: </p>
 *<P> Description: </p>
 *<P> Company: </p>
 *@author xuefengyang
 *@Date 2015年4月30日 下午4:33:15
 */
public class MyLinearLayout extends LinearLayout {
	private Button  mTextBtn;
	private Button  mIconBtn;
	public MyLinearLayout(Context context) {
		this(context, null);
	}
	public MyLinearLayout(Context context, AttributeSet attr) {
		super(context, attr);
		View view = LayoutInflater.from(getContext()).inflate(
				R.layout.note_menu_item, this);
		mTextBtn =(Button)view.findViewById(R.id.menu_item_text	);
		mIconBtn =(Button)view.findViewById(R.id.menu_item_icon);	
		
	}	
	public View getMenuTextBtn(){
		return mTextBtn ;
	}
	public View getMenuIconBtn(){
		return mIconBtn ;
	}			
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		int count = getChildCount();
		for (int i = 0; i < count; i++) {
			getChildAt(i).dispatchTouchEvent(ev);
		}
		return true;
	}
}
