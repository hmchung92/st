package com.hominhchung.writing.description_pictures;

import android.app.Dialog;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.hominhchung.R;
import com.hominhchung.writing.description_pictures.data.AddEditNotesWriting;
import com.hominhchung.writing.description_pictures.data.ListWritingSessonPictureTopicMainActivity;

public class TabReadingDescriptionPicturesTopicActivity extends TabActivity {
	// Declare Variables: Bundle data
	int KEY_BUNDLE;
	String topic_text, hint_text;
	int[] r = { R.drawable.pic_1, R.drawable.pic_1, R.drawable.pic_2,
			R.drawable.pic_3, R.drawable.pic_4, R.drawable.pic_5,
			R.drawable.pic_6, R.drawable.pic_7, R.drawable.pic_8,
			R.drawable.pic_9, R.drawable.pic_10, R.drawable.pic_11,
			R.drawable.pic_13, R.drawable.pic_14, R.drawable.pic_15,
			R.drawable.pic_16, R.drawable.pic_17, R.drawable.pic_18 };

	private static TabReadingDescriptionPicturesTopicActivity INSTANCE;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.writing_description_pictures_tab);
		INSTANCE = this;

		// Bundle data
		Bundle localBundle = getIntent().getExtras();
		KEY_BUNDLE = localBundle.getInt("data");
		hint_text = localBundle.getString("hint");

		TabHost host = getTabHost();

		TabSpec tab1 = host.newTabSpec("tab1");
		tab1.setIndicator("Chủ đề: " + KEY_BUNDLE);
		tab1.setContent(new Intent(this,
				FullImageDescriptionPicturesActivity.class).putExtra("data",
				KEY_BUNDLE).putExtra("hint", hint_text));

		TabSpec tab2 = host.newTabSpec("tab2");
		tab2.setIndicator("Bài tập");
		tab2.setContent(new Intent(this,
				ListWritingSessonPictureTopicMainActivity.class).putExtra(
				"data", KEY_BUNDLE).putExtra("hint", hint_text));
		
		 /* TabSpec tab3 = host.newTabSpec("tab2");
		 tab3.setIndicator("Kiểm tra"); tab3.setContent(new Intent(this,
		 ListVocabulary3500MainActivity.class));*/
		 
		host.addTab(tab1);
		host.addTab(tab2);
		/* host.addTab(tab3); */
	}

	public static TabReadingDescriptionPicturesTopicActivity getInstance() {
		return INSTANCE;
	}

	// Create an options menu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Menu Title
		menu.add("Add New")
				.setOnMenuItemClickListener(this.AddNewNoteClickListener)
				.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

		menu.add("Hint")
				.setOnMenuItemClickListener(this.InfoTopicClickListener)
				.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

		return super.onCreateOptionsMenu(menu);
	}

	// Capture menu item click
	OnMenuItemClickListener AddNewNoteClickListener = new OnMenuItemClickListener() {
		public boolean onMenuItemClick(MenuItem item) {

			// Open AddEditNotes activity
			Intent addnote = new Intent(
					TabReadingDescriptionPicturesTopicActivity.this,
					AddEditNotesWriting.class);
			// addnote.putExtra("data",KEY_BUNDLE);
			startActivity(addnote);

			return false;

		}
	};

	OnMenuItemClickListener InfoTopicClickListener = new OnMenuItemClickListener() {
		public boolean onMenuItemClick(MenuItem item) {

			actionMenuMore();
			return false;

		}
	};

	private void actionMenuMore() {
		// TODO Auto-generated method stub
		final Dialog dialog = new Dialog(this);
		dialog.setContentView(R.layout.alertdialog_writing_description_pictures);
		dialog.setTitle("Hint");

		// set the custom dialog components - text, image and button
		TextView text2 = (TextView) dialog.findViewById(R.id.textView2);
		ImageView image = (ImageView) dialog.findViewById(R.id.image);

		image.setImageResource(r[KEY_BUNDLE]);
		text2.setText(hint_text);

		Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
		// if button is clicked, close the custom dialog
		dialogButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				dialog.dismiss();
			}
		});

		dialog.show();
	}

}