package com.hominhchung.speaking.description_pictures;

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
import com.hominhchung.speaking.description_pictures.data.AddEditNotesSpeaking;
import com.hominhchung.speaking.description_pictures.data.ListSpeakingSessonPictureTopicMainActivity;
public class TabSpeakingTopicActivity extends TabActivity {
	// Declare Variables: Bundle data
	int KEY_BUNDLE;
	String topic_text, 
	question_text="Trong bài này, bạn sẽ được xem một bức tranh. Hãy miêu tả bức tranh bạn được xem.",
	hint_text;
	int[] r = { R.drawable.p1,R.drawable.p1, R.drawable.p2, R.drawable.p3, R.drawable.p4,
			R.drawable.p5, R.drawable.p6, R.drawable.p7, R.drawable.p8,
			R.drawable.p9, R.drawable.p10 };

	private static TabSpeakingTopicActivity INSTANCE;

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
		tab1.setIndicator(getString(R.string.topic)+": " + KEY_BUNDLE);
		tab1.setContent(new Intent(this, FullImageActivity.class)
				.putExtra("data", KEY_BUNDLE)
				.putExtra("hint", hint_text));

		TabSpec tab2 = host.newTabSpec("tab2");
		tab2.setIndicator("Bài tập");
		tab2.setContent(new Intent(this,
				ListSpeakingSessonPictureTopicMainActivity.class)
				.putExtra("data", KEY_BUNDLE)
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

	public static TabSpeakingTopicActivity getInstance() {
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
			Intent addnote = new Intent(TabSpeakingTopicActivity.this,
					AddEditNotesSpeaking.class);
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
		dialog.setTitle(getString(R.string.topic)+": "+KEY_BUNDLE);

		// set the custom dialog components - text, image and button
		TextView text1 = (TextView) dialog.findViewById(R.id.textView1);
		TextView text2 = (TextView) dialog.findViewById(R.id.textView2);
		ImageView image = (ImageView) dialog.findViewById(R.id.image);

		image.setImageResource(r[KEY_BUNDLE]);
		
		text1.setText(question_text);
		text2.setText(getString(R.string.hint)+": "+hint_text);
		
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