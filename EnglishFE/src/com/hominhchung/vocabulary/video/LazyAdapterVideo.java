package com.hominhchung.vocabulary.video;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hominhchung.R;

public class LazyAdapterVideo extends BaseAdapter {

	private final Activity activity;
	private final ArrayList<HashMap<String, String>> data;
	private static LayoutInflater inflater=null;
	public LazyAdapterVideo(Activity a, ArrayList<HashMap<String, String>> d) {
		activity = a;
		data=d;
		inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View vi=convertView; 
		if(convertView==null)
		vi = inflater.inflate(R.layout.adapter_list_row_1, null);
		TextView tv_namelocal = (TextView)vi.findViewById(R.id.tv_namelocal); // tv_namelocal
		TextView tv_enlocal = (TextView)vi.findViewById(R.id.tv_enlocal); // tv_enlocal
		//TextView tv_num = (TextView)vi.findViewById(R.id.tv_num); // tv_num
		ImageView itemImage = (ImageView) vi.findViewById(R.id.photo);
		HashMap<String, String> lesson = new HashMap<String, String>();
		lesson = data.get(position);
		// Setting all values in listview
		itemImage.setImageResource(ListVideoVocabularyMainActivity.KEY_ITEM_IMAGE);
		tv_namelocal.setText(lesson.get(ListVideoVocabularyMainActivity.KEY_LOCAL_TITLE));
		tv_enlocal.setText(lesson.get(ListVideoVocabularyMainActivity.KEY_EN_TITLE));
		return vi;
	}

}