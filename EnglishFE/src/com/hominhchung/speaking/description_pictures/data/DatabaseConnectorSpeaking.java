package com.hominhchung.speaking.description_pictures.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseConnectorSpeaking {

	// Declare Variables
	private static final String DB_NAME = "SpeakTopic";
	private static final String TABLE_NAME = "tablenotes";
	private static final String TITLE = "title";
	private static final String ID = "_id";
	private static final String NOTE = "note";
	private static final int DATABASE_VERSION = 1;
	private SQLiteDatabase database;
	private DatabaseHelperSpeaking dbOpenHelper;

	public DatabaseConnectorSpeaking(Context context) {
		dbOpenHelper = new DatabaseHelperSpeaking(context, DB_NAME, null,
				DATABASE_VERSION);

	}

	// Open Database function
	public void open() throws SQLException {
		// Allow database to be in writable mode
		database = dbOpenHelper.getWritableDatabase();
	}

	// Close Database function
	public void close() {
		if (database != null)
			database.close();
	}

	// Create Database function
	public void InsertNote(String title, String note) {
		ContentValues newCon = new ContentValues();
		newCon.put(TITLE, title);
		newCon.put(NOTE, note);

		open();
		database.insert(TABLE_NAME, null, newCon);
		close();
	}

	// Update Database function
	public void UpdateNote(long id, String title, String note) {
		ContentValues editCon = new ContentValues();
		editCon.put(TITLE, title);
		editCon.put(NOTE, note);

		open();
		database.update(TABLE_NAME, editCon, ID + "=" + id, null);
		close();
	}

	// Delete Database function
	public void DeleteNote(long id) {
		open();
		database.delete(TABLE_NAME, ID + "=" + id, null);
		close();
	}

	// List all data function
	public Cursor ListAllNotes() {
		return database.query(TABLE_NAME, new String[] { ID, TITLE }, null,
				null, null, null, TITLE);
	}

	// Capture single data by ID
	public Cursor GetOneNote(long id) {
		return database.query(TABLE_NAME, null, ID + "=" + id, null, null,
				null, null);
	}

}
