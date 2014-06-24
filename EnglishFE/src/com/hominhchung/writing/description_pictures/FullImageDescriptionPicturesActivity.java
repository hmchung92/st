package com.hominhchung.writing.description_pictures;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.hominhchung.R;

public class FullImageDescriptionPicturesActivity extends Activity {
	// Mảng hình ảnh
	int[] r = { R.drawable.pic_1, R.drawable.pic_1, R.drawable.pic_2,
			R.drawable.pic_3, R.drawable.pic_4, R.drawable.pic_5,
			R.drawable.pic_6, R.drawable.pic_7, R.drawable.pic_8,
			R.drawable.pic_9, R.drawable.pic_10, R.drawable.pic_11,
			R.drawable.pic_13, R.drawable.pic_14, R.drawable.pic_15,
			R.drawable.pic_16, R.drawable.pic_17, R.drawable.pic_18 };
	int KEY_BUNDLE;
	String topic_text = "Hãy quan sát và miêu tả bức tranh bạn được xem.",
			question_text, hint_text;

	private static final OnMenuItemClickListener AddNewNoteClickListener = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.writing_description_pictures_full_image);

		// Nhận dữ liệu
		Bundle localBundle = getIntent().getExtras();
		KEY_BUNDLE = localBundle.getInt("data");
		hint_text = localBundle.getString("hint");

		/*
		 * TextView tv = (TextView) findViewById(R.id.textView1);
		 * 
		 * // get intent data Intent i = getIntent();
		 * 
		 * // Selected image id int position = i.getExtras().getInt("id");
		 * ImageAdapter imageAdapter = new ImageAdapter(this);
		 * 
		 * int topic = position+1; tv.setText("Topic: 1");
		 */

		TextView text1 = (TextView) findViewById(R.id.textView1); // topic_text
		TextView text2 = (TextView) findViewById(R.id.textView2); // hint_text

		text1.setText(topic_text);
		text2.setText(getString(R.string.topic) + ": " + KEY_BUNDLE);

		ImageView imageView = (ImageView) findViewById(R.id.full_image_view);
		imageView.setImageResource(r[KEY_BUNDLE]);
	}
}
