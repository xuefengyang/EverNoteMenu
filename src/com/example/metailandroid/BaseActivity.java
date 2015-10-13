package com.example.metailandroid;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

public class BaseActivity extends ActionBarActivity {
		
	Toolbar mToolbar;
	public  Toolbar  getSupportToolbar(){
		if(mToolbar==null){
			mToolbar=(Toolbar)findViewById(R.id.toobar_actionbar);
			mToolbar.setNavigationIcon(R.drawable.ic_up);	
		}	
		return mToolbar;
	}
}
