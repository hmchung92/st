package com.hominhchung.speaking.general_debates.data;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.hominhchung.R;

public class ListSpeakingSessonPictureTopicDebatesMainActivity extends
		ListActivity {
	// Declare Variables: Bundle data
	int KEY_BUNDLE;
	String hint_text;
	int[] r = { R.drawable.gd_1, R.drawable.gd_2, R.drawable.gd_3,
			R.drawable.gd_4, R.drawable.gd_5, R.drawable.gd_6, R.drawable.gd_7,
			R.drawable.gd_8, R.drawable.gd_9, R.drawable.gd_10 };

	// Declare Variables
	public static final String ROW_ID = "row_id";
	private static final String TITLE = "title";
	private ListView noteListView;
	private CursorAdapter noteAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Bundle data
		Bundle localBundle = getIntent().getExtras();
		KEY_BUNDLE = localBundle.getInt("data");
		hint_text = localBundle.getString("hint");
		// Locate ListView
		noteListView = getListView();

		// Prepare ListView Item Click Listener
		noteListView.setOnItemClickListener(viewNoteListener);

		// Map all the titles into the ViewTitleNotes TextView
		String[] from = new String[] { TITLE };
		int[] to = new int[] { R.id.ViewTitleNotes };

		// Create a SimpleCursorAdapter
		noteAdapter = new SimpleCursorAdapter(
				ListSpeakingSessonPictureTopicDebatesMainActivity.this,
				R.layout.description_pictures_list_note, null, from, to);

		// Set the Adapter into SimpleCursorAdapter
		setListAdapter(noteAdapter);
	}

	// Capture ListView item click
	OnItemClickListener viewNoteListener = new OnItemClickListener() {
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {

			// Open ViewNote activity
			Intent viewnote = new Intent(
					ListSpeakingSessonPictureTopicDebatesMainActivity.this,
					ViewNoteDebates.class);

			// Pass the ROW_ID to ViewNote activity
			viewnote.putExtra(ROW_ID, arg3);
			startActivity(viewnote);
		}
	};

	@Override
	protected void onResume() {
		super.onResume();

		// Execute GetNotes Asynctask on return to MainActivity
		new GetNotes().execute((Object[]) null);
	}

	@Override
	protected void onStop() {
		Cursor cursor = noteAdapter.getCursor();

		// Deactivates the Cursor
		if (cursor != null)
			cursor.deactivate();

		noteAdapter.changeCursor(null);
		super.onStop();
	}

/*	// Create an options menu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Menu Title
		menu.add("Add New Notes")
				.setOnMenuItemClickListener(this.AddNewNoteClickListener)
				.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

		menu.add("View Topic")
				.setOnMenuItemClickListener(this.ViewTopicClickListener)
				.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

		return super.onCreateOptionsMenu(menu);
	}

	// Capture menu item click
	OnMenuItemClickListener AddNewNoteClickListener = new OnMenuItemClickListener() {
		public boolean onMenuItemClick(MenuItem item) {

			// Open AddEditNotes activity
			Intent addnote = new Intent(
					ListSpeakingSessonPictureTopicDebatesMainActivity.this,
					AddEditNotesDebates.class);
			// addnote.putExtra("data",KEY_BUNDLE);
			startActivity(addnote);

			return false;

		}
	};

	// Capture menu item click
	OnMenuItemClickListener ViewTopicClickListener = new OnMenuItemClickListener() {
		public boolean onMenuItemClick(MenuItem item) {

			// Open AddEditNotes activity
			actionMenuMore();

			return false;

		}
	};

	private void actionMenuMore() {
		// TODO Auto-generated method stub
		final Dialog dialog = new Dialog(this);
		dialog.setContentView(R.layout.speaking_general_debates_alertdialog_image);
		dialog.setTitle(hint_text);

		// set the custom dialog components - text, image and button
		ImageView image = (ImageView) dialog.findViewById(R.id.image);

		image.setImageResource(r[KEY_BUNDLE]);
		Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
		// if button is clicked, close the custom dialog
		dialogButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				dialog.dismiss();
			}
		});

		dialog.show();
	}
*/
	// GetNotes AsyncTask
	private class GetNotes extends AsyncTask<Object, Object, Cursor> {
		DatabaseConnectorDebates dbConnector = new DatabaseConnectorDebates(
				ListSpeakingSessonPictureTopicDebatesMainActivity.this);

		@Override
		protected Cursor doInBackground(Object... params) {
			// Open the database
			dbConnector.open();

			return dbConnector.ListAllNotes();
		}

		@Override
		protected void onPostExecute(Cursor result) {
			noteAdapter.changeCursor(result);

			// Close Database
			dbConnector.close();
		}
	}
}
