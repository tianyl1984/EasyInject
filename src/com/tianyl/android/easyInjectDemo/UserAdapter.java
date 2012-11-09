package com.tianyl.android.easyInjectDemo;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class UserAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater inflater;
	private List<String> users;

	public UserAdapter(Context context, List<String> users) {
		this.context = context;
		inflater = LayoutInflater.from(this.context);
		this.users = users;
	}

	@Override
	public int getCount() {
		return users.size();
	}

	@Override
	public String getItem(int position) {
		return users.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		if (view == null) {
			view = inflater.inflate(R.layout.text_item, null);
		}
		TextView textView = (TextView) view.findViewById(R.id.textView);
		textView.setText(getItem(position));
		return view;
	}

}
