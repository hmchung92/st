package com.hominhchung.listening.music;

import java.io.File;
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
	String EFE_PATH = "/sdcard/EnglishFe/Music";
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
			R.drawable.m2m
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
			convertView = l_Inflater.inflate(R.layout.adapter_list_row_4_music, null);
			holder = new ViewHolder();
			holder.txt_itemName = (TextView) convertView.findViewById(R.id.name);
			holder.txt_itemDescription = (TextView) convertView.findViewById(R.id.itemDescription);
			holder.txt_itemOnline = (TextView) convertView.findViewById(R.id.online);
			holder.itemImage = (ImageView) convertView.findViewById(R.id.photo);
			holder.downImage = (ImageView) convertView.findViewById(R.id.imageDown);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.txt_itemName.setText(itemDetailsrrayList.get(position).getTitle());
		holder.txt_itemDescription.setText(itemDetailsrrayList.get(position).getAuthor());
		holder.txt_itemOnline.setText(itemDetailsrrayList.get(position).getTime());
		holder.itemImage.setImageResource(imgid[itemDetailsrrayList.get(position).getImageNumber() - 1]);
		
		String title1 = itemDetailsrrayList.get(position).getTitle();
		
		File efeDirectory = new File(EFE_PATH+"/"+title1+".mp3"); // /sdcard/EnglishFe/Music;
		if (!efeDirectory.exists()) {
			// have the object build the directory structure, if needed.
			holder.downImage.setImageResource(R.drawable.down);
		}
		else{
			holder.downImage.setImageResource(R.drawable.down_true);
		}
//		imageLoader.DisplayImage("http://192.168.1.28:8082/ANDROID/images/BEVE.jpeg", holder.itemImage);

		return convertView;
	}

	static class ViewHolder {
		TextView txt_itemName;
		TextView txt_itemDescription;
		TextView txt_itemOnline;
		ImageView itemImage;
		ImageView downImage;
	}
}
