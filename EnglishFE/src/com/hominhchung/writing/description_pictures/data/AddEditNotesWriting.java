package com.hominhchung.writing.description_pictures.data;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.EditText;
import android.widget.Toast;

import com.hominhchung.R;

public class AddEditNotesWriting extends Activity {
	
	// Declare Variables
	int KEY_BUNDLE;
	int[] r = { R.drawable.pic_1, R.drawable.pic_1, R.drawable.pic_2,
			R.drawable.pic_3, R.drawable.pic_4, R.drawable.pic_5,
			R.drawable.pic_6, R.drawable.pic_7, R.drawable.pic_8,
			R.drawable.pic_9, R.drawable.pic_10, R.drawable.pic_11,
			R.drawable.pic_13, R.drawable.pic_14, R.drawable.pic_15,
			R.drawable.pic_16, R.drawable.pic_17, R.drawable.pic_18 };
	private long rowID;
	private EditText title_edit;
	private EditText note_edit;
	private static final String TITLE = "title";
	private static final String NOTE = "note";
	protected static final int RESULT_SPEECH = 1;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.description_pictures_add_note);
		
        // Bundle data
		
        // Locate the EditText in add_note.xml
		title_edit = (EditText) findViewById(R.id.titleEdit);
		note_edit = (EditText) findViewById(R.id.noteEdit);

		// Retrieve the Row ID from ViewNote.java
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			rowID = extras.getLong("row_id");
			title_edit.setText(extras.getString(TITLE));
			note_edit.setText(extras.getString(NOTE));
		}
	}

	// Create an ActionBar menu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add("Speak")
				.setOnMenuItemClickListener(this.SpeakButtonClickListener)
				.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		

		menu.add("Save Note")
				.setOnMenuItemClickListener(this.SaveButtonClickListener)
				.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

		return super.onCreateOptionsMenu(menu);
	}

	// Capture save menu item click
	OnMenuItemClickListener SaveButtonClickListener = new OnMenuItemClickListener() {
		public boolean onMenuItemClick(MenuItem item) {

			// Passes the data into saveNote() function
			if (title_edit.getText().length() != 0) {
				AsyncTask<Object, Object, Object> saveNoteAsyncTask = new AsyncTask<Object, Object, Object>() {
					@Override
					protected Object doInBackground(Object... params) {
						saveNote();
						return null;
					}

					@Override
					protected void onPostExecute(Object result) {
						// Close this activity
						finish();
					}
				};
				// Execute the saveNoteAsyncTask AsyncTask above
				saveNoteAsyncTask.execute((Object[]) null);
			}

			else {
				// Display a simple alert dialog that forces user to put in a
				// title
				AlertDialog.Builder alert = new AlertDialog.Builder(
						AddEditNotesWriting.this);
				alert.setTitle(getString(R.string.title_is_required));
				alert.setMessage(getString(R.string.put_in_a_title_for_this_note));
				alert.setPositiveButton(getString(R.string.ok), null);
				alert.show();
			}

			return false;

		}
	};

	
	OnMenuItemClickListener SpeakButtonClickListener = new OnMenuItemClickListener() {
		public boolean onMenuItemClick(MenuItem item) {
			// Gui tap tin am thanh
			Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
			// xac nhan ung dung muon gui yeu cau
			intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getClass()
					.getPackage().getName());

			// goi y nhan dang nhung gi nguoi dung se noi
			intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
					RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

			// goi y nhung dieu nguoi dung muon noi
			intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
					getString(R.string.tell_me_what_you_want));

			try {
				startActivityForResult(intent, RESULT_SPEECH);
			} catch (ActivityNotFoundException e) {
				AlertDialog.Builder builder = new AlertDialog.Builder(AddEditNotesWriting.this);
				builder.setTitle(getString(R.string.not_available));
				builder.setMessage(getString(R.string.no_recognition_software_installed));
				builder.setPositiveButton(getString(R.string.yes),
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								Intent marketIntent = new Intent(
										Intent.ACTION_VIEW);
								marketIntent.setData(Uri
										.parse(getString(R.string.url_download_voicesearch)));
								startActivity(marketIntent);
							}
						});
				builder.setNegativeButton(getString(R.string.no), null);
				builder.create().show();
			}
			return false;

		}
	};

	// saveNote() function
	private void saveNote() {
		DatabaseConnectorWriting dbConnector = new DatabaseConnectorWriting(this);

		if (getIntent().getExtras() == null) {
			// Passes the data to InsertNote in DatabaseConnector.java
			dbConnector.InsertNote(title_edit.getText().toString(), note_edit
					.getText().toString());
		} else {
			// Passes the Row ID and data to UpdateNote in
			// DatabaseConnector.java
			dbConnector.UpdateNote(rowID, title_edit.getText().toString(),
					note_edit.getText().toString());
		}
	}

	
	/*-------------------------------------------Speed to text---------------------------------------------*/
	// Nhận kết quả từ server
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
		case RESULT_SPEECH: {
			// Trườg hợp có giá trị trả về?
			if (resultCode == RESULT_OK && null != data) {

				ArrayList<String> text = data
						.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
				// Thông báo và hiển thị kết quả
				Toast.makeText(getApplicationContext(), text.get(0),
						Toast.LENGTH_LONG).show();
				note_edit.append(text.get(0));
			} else {
				// Hiển thị thông báo thất bại
				Toast.makeText(this, "Operation Canceled", Toast.LENGTH_SHORT)
						.show();
				break;
			}
		}
		}
	}

}
