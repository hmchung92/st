package com.hominhchung.speaking.general_debates;

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
import com.hominhchung.speaking.general_debates.data.AddEditNotesDebates;
import com.hominhchung.speaking.general_debates.data.ListSpeakingSessonPictureTopicDebatesMainActivity;
public class TabSpeakingGeneralDebatesTopicActivity extends TabActivity {
	// Declare Variables: Bundle data
	int KEY_BUNDLE;
	String topic_text, question_text, hint_text;
	int[] r = { R.drawable.gd_1, R.drawable.gd_2, R.drawable.gd_3,
			R.drawable.gd_4, R.drawable.gd_5, R.drawable.gd_6, R.drawable.gd_7,
			R.drawable.gd_8, R.drawable.gd_9, R.drawable.gd_10 };

	private static TabSpeakingGeneralDebatesTopicActivity INSTANCE;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.writing_description_pictures_tab);
		INSTANCE = this;

		// Bundle data
		Bundle localBundle = getIntent().getExtras();
		KEY_BUNDLE = localBundle.getInt("data");
		topic_text = localBundle.getString("topic");
		question_text = localBundle.getString("question");
		hint_text = localBundle.getString("hint");

		TabHost host = getTabHost();

		TabSpec tab1 = host.newTabSpec("tab1");
		int num = KEY_BUNDLE + 1;
		tab1.setIndicator("Topic: " + num);
		tab1.setContent(new Intent(this, FullImageGeneralDebatesActivity.class)
				.putExtra("data", KEY_BUNDLE).putExtra("topic", topic_text)
				.putExtra("question", question_text)
				.putExtra("hint", hint_text));

		TabSpec tab2 = host.newTabSpec("tab2");
		tab2.setIndicator("Bài tập");
		tab2.setContent(new Intent(this,
				ListSpeakingSessonPictureTopicDebatesMainActivity.class)
				.putExtra("data", KEY_BUNDLE).putExtra("topic", topic_text)
				.putExtra("question", question_text)
				.putExtra("hint", hint_text));
		/*
		 * TabSpec tab3 = host.newTabSpec("tab2");
		 * tab3.setIndicator("Kiểm tra"); tab3.setContent(new Intent(this,
		 * ListVocabulary3500MainActivity.class));
		 */

		host.addTab(tab1);
		host.addTab(tab2);
		/* host.addTab(tab3); */
	}

	public static TabSpeakingGeneralDebatesTopicActivity getInstance() {
		return INSTANCE;
	}

	// Create an options menu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Menu Title
		menu.add(getString(R.string.add_new))
				.setOnMenuItemClickListener(this.AddNewNoteClickListener)
				.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

		menu.add(getString(R.string.hint))
				.setOnMenuItemClickListener(this.HintButtonClickListener)
				.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

		return super.onCreateOptionsMenu(menu);
	}

	// Capture menu item click
	OnMenuItemClickListener AddNewNoteClickListener = new OnMenuItemClickListener() {
		public boolean onMenuItemClick(MenuItem item) {

			// Open AddEditNotes activity
			Intent addnote = new Intent(TabSpeakingGeneralDebatesTopicActivity.this,
					AddEditNotesDebates.class);
			// addnote.putExtra("data", KEY_BUNDLE);
			startActivity(addnote);

			return false;

		}
	};
	// Hint Button
	OnMenuItemClickListener HintButtonClickListener = new OnMenuItemClickListener() {
		public boolean onMenuItemClick(MenuItem item) {

			actionMenuMore();
			return false;

		}
	};

	private void actionMenuMore() {
		// TODO Auto-generated method stub
		final Dialog dialog = new Dialog(this);
		dialog.setContentView(R.layout.speaking_general_debates_alertdialog_image);
		dialog.setTitle(getString(R.string.hint));

		// set the custom dialog components - text, image and button
		TextView text1 = (TextView) dialog.findViewById(R.id.textView1);
		TextView text2 = (TextView) dialog.findViewById(R.id.textView2);
		ImageView image = (ImageView) dialog.findViewById(R.id.image);

		image.setImageResource(r[KEY_BUNDLE]);
		text1.setText(topic_text);
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