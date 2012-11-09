package com.tianyl.android.easyInject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

public class InjectActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void setContentView(int layoutResID) {
		super.setContentView(layoutResID);
		InjectUtil.inject(this);
	}

	@Override
	public void setContentView(View view, LayoutParams params) {
		super.setContentView(view, params);
		InjectUtil.inject(this);
	}

	@Override
	public void setContentView(View view) {
		super.setContentView(view);
		InjectUtil.inject(this);
	}

}
