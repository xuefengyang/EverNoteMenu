package com.example.metailandroid;

import java.util.ArrayList;
import java.util.List;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class NoteMenu extends FrameLayout implements OnClickListener {
	private Button mHandleBtn;
	private boolean isOpen;
	private float mStartRadius;
	private float mMaxRaduis;
	private Paint mPaint;
	private int mMinWidthAndHeight;
	private int mMaxWidthAndHeight; 
	private LinearLayout mMenuWrap; 
	private MyLinearLayout [] mMenuItems; 
	private RectF r;
	public OnMenuItemClickListener  mMenuItemClickListener;
	public interface OnMenuItemClickListener{
			void  onMenuItemClick(int index);
	}	
	public void  setOnMenuItemClickListener(OnMenuItemClickListener listener){
			this.mMenuItemClickListener = listener; 
	}	
	public NoteMenu(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
			
		mPaint = new Paint();
		mPaint.setColor(getResources().getColor(
				android.R.color.holo_orange_light));
		r = new RectF(0, 0, getMeasuredWidth(), getMeasuredHeight());
		setWillNotDraw(false);
			
	}
	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		mMinWidthAndHeight = Math.min(getMeasuredWidth(), getMeasuredHeight());
		mMaxWidthAndHeight = Math.max(getMeasuredWidth(), getMeasuredHeight());
		mStartRadius = mMinWidthAndHeight / 4;
		mMaxRaduis = (float) Math
				.sqrt(getMeasuredHeight() * getMeasuredHeight()
						+ getMeasuredWidth() * getMeasuredWidth()) + 0.5f;
	}	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Log.d("TAG", "onDraw--------");
		if (isOpen) {
			mPaint.setColor(Color.parseColor("#80ffffff"));
			canvas.drawCircle(getWidth(), getHeight(), mStartRadius, mPaint);
			if (mStartRadius < mMaxRaduis) {
				if (mStartRadius < mMaxWidthAndHeight / 2) {
					// 叠加 参数稍 小
					mStartRadius += mMinWidthAndHeight / 8;
				} else { 
					// 叠加参数 稍大
					mStartRadius += mMinWidthAndHeight / 6;
				} 
				postInvalidateDelayed(16);
			}	
		} else {
			mStartRadius = mMinWidthAndHeight / 4;
			mPaint.setColor(Color.parseColor("#00000000"));
			canvas.drawRect(r, mPaint);
		}		

	}

	private void open() {		
			
	}	
	private void animateMenuIcon(){
		AnimatorSet animatorSet =new AnimatorSet();
		List<Animator> animaList=new ArrayList<Animator>();
		for (int i = 0; i < mMenuItems.length; i++){
			animaList.add(getScaleAnimator(mMenuItems[i].getMenuIconBtn()));			
		}		
		animatorSet.playSequentially(animaList);
		animatorSet.start(); 
	}		
	private  ObjectAnimator  getScaleAnimator(View target){ 	
		PropertyValuesHolder pvhScaleX=PropertyValuesHolder.ofFloat("scaleX", 0.5f,1.0f);
		PropertyValuesHolder pvhScaleY=PropertyValuesHolder.ofFloat("scaleY", 0.5f,1.0f);
		
		ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(target, pvhScaleX,pvhScaleY);
		animator.setDuration(150);  
		return animator;
		
	}
	private  ObjectAnimator getAlphaAnimator(View target){  		
		ObjectAnimator animator = ObjectAnimator.ofFloat(target, "alpha", 0,1);	  
		animator.setDuration(300);							
		return animator;       
	}	
	private void init() {	
		View view = LayoutInflater.from(getContext()).inflate(
				R.layout.note_menu, this);	
		mMenuWrap =(LinearLayout)view.findViewById(R.id.menu_wrap);	
		mHandleBtn = (Button) view.findViewById(R.id.menu_handle); 	
		mHandleBtn.setOnClickListener(this);
		int count = mMenuWrap.getChildCount();
		mMenuItems =new MyLinearLayout [count];
		for (int i = 0; i <count; i++) { 
			View child = mMenuWrap.getChildAt(i);
			if(child instanceof MyLinearLayout){	  
				mMenuItems[i]=(MyLinearLayout)child;	 
			}else{		
				throw new ClassCastException("MenuWrap's child is only MyLinearLayout ");
			}				
		}
	}
	@Override
	public void onClick(View v) { 
		switch (v.getId()) {
		case R.id.menu_handle:
			isOpen = !isOpen;
			invalidate();
			break;
		
		}
		animateMenuIcon();
	}

}
