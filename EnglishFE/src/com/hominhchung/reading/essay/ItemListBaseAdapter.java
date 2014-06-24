package com.hominhchung.reading.essay;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hominhchung.R;

public class ItemListBaseAdapter extends BaseAdapter {
	private static ArrayList<ItemDetails> itemDetailsrrayList;
	
	private Integer[] imgid = {
			R.drawable.abba,
			R.drawable.the_eagles,
			R.drawable.celine_dion,
			R.drawable.george_benson,
			R.drawable.michael_learns_to_rock,
			R.drawable.richard_marx,
			R.drawable.capenters,
			R.drawable.michael_learns_to_rock,
			R.drawable.westlife,
			R.drawable.westlife,
			R.drawable.michael_learns_to_rock,
			};
	
	private LayoutInflater l_Inflater;

	public ItemListBaseAdapter(Context context, ArrayList<ItemDetails> results) {
		itemDetailsrrayList = results;
		l_Inflater = LayoutInflater.from(context);
	}

	public int getCount() {
		return itemDetailsrrayList.size();
	}

	public Object getItem(int position) {
		return itemDetailsrrayList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = l_Inflater.inflate(R.layout.adapter_list_row_12, null);
			holder = new ViewHolder();
			holder.txt_itemName = (TextView) convertView.findViewById(R.id.name);
			holder.txt_itemDescription = (TextView) convertView.findViewById(R.id.itemDescription);
			holder.txt_itemPrice = (TextView) convertView.findViewById(R.id.price);
			holder.itemImage = (ImageView) convertView.findViewById(R.id.photo);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.txt_itemName.setText(itemDetailsrrayList.get(position).getName());
		holder.txt_itemDescription.setText(itemDetailsrrayList.get(position).getItemDescription());
		holder.txt_itemPrice.setText(itemDetailsrrayList.get(position).getPrice());
		//holder.itemImage.setImageResource(imgid[itemDetailsrrayList.get(position).getImageNumber() - 1]);
		holder.itemImage.setImageResource(R.drawable.dashboarde__reading);
		//		imageLoader.DisplayImage("https://fbcdn-profile-a.akamaihd.net/hprofile-ak-ash2/t5/1118032_100002227996914_217338759_q.jpg", holder.itemImage);

		return convertView;
	}

	static class ViewHolder {
		TextView txt_itemName;
		TextView txt_itemDescription;
		TextView txt_itemPrice;
		ImageView itemImage;
	}
}
