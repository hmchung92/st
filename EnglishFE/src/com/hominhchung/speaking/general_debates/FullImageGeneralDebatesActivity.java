package com.hominhchung.speaking.general_debates;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.hominhchung.R;


public class FullImageGeneralDebatesActivity extends Activity {
	// Declare Variables: Bundle data
	int KEY_BUNDLE;
	String topic_text,question_text,hint_text;
	int[] r = { R.drawable.gd_1, R.drawable.gd_2, R.drawable.gd_3,
			R.drawable.gd_4, R.drawable.gd_5, R.drawable.gd_6, R.drawable.gd_7,
			R.drawable.gd_8, R.drawable.gd_9, R.drawable.gd_10 };
	private static final OnMenuItemClickListener AddNewNoteClickListener = null;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.speaking_description_pictures_full_image);
		
		// Bundle data
		Bundle localBundle = getIntent().getExtras();
		KEY_BUNDLE = localBundle.getInt("data");
		topic_text = localBundle.getString("topic");
		question_text = localBundle.getString("question");
		hint_text = localBundle.getString("hint");
		
		/*
		// get intent data
		Intent i = getIntent();
		
		// Selected image id
		int position = i.getExtras().getInt("id");
		ImageAdapter imageAdapter = new ImageAdapter(this);
		
		int topic = position+1;
		tv.setText("Topic: 1");
		*/
		TextView text1 = (TextView) findViewById(R.id.textView1); //topic_text
		TextView text2 = (TextView) findViewById(R.id.textView2); //question_text
		
		text1.setText(topic_text);
		text2.setText(question_text);
		
		ImageView imageView = (ImageView) findViewById(R.id.full_image_view);
		imageView.setImageResource(r[KEY_BUNDLE]);
	}
/*	
	// Create an options menu
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {

			// Menu Title
			menu.add("Hint")
					.setOnMenuItemClickListener(this.ButtonHintClickListener)
					.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

			menu.add("Hint")
					.setOnMenuItemClickListener(this.ButtonHintClickListener)
					.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
	
			return super.onCreateOptionsMenu(menu);
		}

		// Question Button menu item click
		OnMenuItemClickListener ButtonQuestionClickListener = new OnMenuItemClickListener() {
			public boolean onMenuItemClick(MenuItem item) {

				// Open Dialog
				actionMenuMore(question_text);
				return false;

			}
		};
		// Hint Button menu item click
		OnMenuItemClickListener ButtonHintClickListener = new OnMenuItemClickListener() {
			public boolean onMenuItemClick(MenuItem item) {
				// Open Dialog
				actionMenuMore(hint_text);
				return false;

			}
		};

		private void actionMenuMore(String text) {
			// TODO Auto-generated method stub
			final Dialog dialog = new Dialog(this);
			dialog.setContentView(R.layout.speaking_general_debates_alertdialog_image);
			dialog.setTitle(topic_text);

			// set the custom dialog components - text, image and button
			TextView text1 = (TextView) dialog.findViewById(R.id.textView1);
			ImageView image = (ImageView) dialog.findViewById(R.id.image);
			
			image.setImageResource(r[KEY_BUNDLE]);
			text1.setText(text);
			
			Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
			// if button is clicked, close the custom dialog
			dialogButton.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					dialog.dismiss();
				}
			});

			dialog.show();
		}*/
}
