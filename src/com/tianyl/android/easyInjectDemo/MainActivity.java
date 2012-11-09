package com.tianyl.android.easyInjectDemo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.tianyl.android.easyInject.InjectUtil;
import com.tianyl.android.easyInject.InjectView;

public class MainActivity extends Activity {

	@InjectView(id = R.id.saveBtn, click = "save", longClick = "longClick")
	Button saveBtn;

	@InjectView(id = R.id.delBtn)
	Button delBtn;

	@InjectView(id = R.id.listView, itemClick = "itemClick", itemLongClick = "longClick")
	ListView listView;

	private List<String> users;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		InjectUtil.inject(this);
		users = new ArrayList<String>();
		users.add("张三");
		users.add("李四");
		users.add("王五");
		users.add("哈哈");
		users.add("嘿嘿");
		users.add("呵呵");
		listView.setAdapter(new UserAdapter(this, users));
		InjectUtil.bindClick(this, delBtn, "delete");
	}

	public void save(View view) {
		Toast.makeText(this, "保存", Toast.LENGTH_SHORT).show();
	}

	public void delete(View view) {
		Toast.makeText(this, "删除", Toast.LENGTH_SHORT).show();
	}

	public boolean longClick(View view) {
		Toast.makeText(this, "longClick", Toast.LENGTH_SHORT).show();
		return false;
	}

	public void itemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Toast.makeText(MainActivity.this, "onItemClick:" + users.get(arg2), Toast.LENGTH_SHORT).show();
	}

	public boolean longClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Toast.makeText(MainActivity.this, "onLongItemClick:" + users.get(arg2), Toast.LENGTH_SHORT).show();
		return true;
	}
}
