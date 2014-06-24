package com.hominhchung.adapter.actionbar;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hominhchung.R;
import com.hominhchung.adapter.SpinnerNavItem;

public class TitleNavigationAdapter extends BaseAdapter {
	/**
	 * Khai báo giá trị
	 */
	private ImageView imgIcon;
	private TextView txtTitle;
	private ArrayList<SpinnerNavItem> spinnerNavItem;
	private Context context;

	public TitleNavigationAdapter(Context context,
			ArrayList<SpinnerNavItem> spinnerNavItem) {
		this.spinnerNavItem = spinnerNavItem;
		this.context = context;
	}

	@Override
	public int getCount() {
		return spinnerNavItem.size();
	}

	@Override
	public Object getItem(int index) {
		return spinnerNavItem.get(index);
	}

	/**
	 * lấy vị trí
	 */
	@Override
	public long getItemId(int position) {
		return position;
	}

	/**
	 * chỉnh một View (hiển thị)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// Nếu không tồn tại convertView
		if (convertView == null) {
			LayoutInflater mInflater = (LayoutInflater) context
					.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			convertView = mInflater.inflate(
					R.layout.adapter_list_item_title_navigation, null);
		}

		imgIcon = (ImageView) convertView.findViewById(R.id.imgIcon);
		txtTitle = (TextView) convertView.findViewById(R.id.txtTitle);

		imgIcon.setImageResource(spinnerNavItem.get(position).getIcon());
		imgIcon.setVisibility(View.GONE);
		txtTitle.setText(spinnerNavItem.get(position).getTitle());
		return convertView;
	}

	/**
	 * chỉnh một DropDownView (hiển thị)
	 */
	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater mInflater = (LayoutInflater) context
					.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			convertView = mInflater.inflate(
					R.layout.adapter_list_item_title_navigation, null);
		}

		imgIcon = (ImageView) convertView.findViewById(R.id.imgIcon);
		txtTitle = (TextView) convertView.findViewById(R.id.txtTitle);

		imgIcon.setImageResource(spinnerNavItem.get(position).getIcon());
		txtTitle.setText(spinnerNavItem.get(position).getTitle());
		return convertView;
	}

}
