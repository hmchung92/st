package com.hominhchung.databasehelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper {
	// Khai báo các biến static trong lớp DatabaseHandler 
	//private static String DB_PATH = "/data/data/com.example.democonnectdbfromasset/databases/";
	private static String DB_PATH = "";
	private static String DB_NAME = "mydatabase.sqlite";
	private SQLiteDatabase myDataBase;
	private final Context myContext;
	public static String DATABASE_TABLE = ""; //englishcommon
	//private static final String DATABASE_TABLE = ""; //englishcommon

	private SQLiteDatabase mDb;
	// Danh sách các field của table englishcommon
	static final String KEY_ID = "_id";
	static final String KEY_NAME = "name";
	static final String KEY_CATAGORY = "catagory";
	static final String KEY_READ = "read";
	static final String KEY_VICONTENT = "vicontent";
	static final String KEY_ISREAD = "isread";
	static final String KEY_ISNEXT = "isnext";

	//phương thức: constructor
	public DataBaseHelper(Context context) {
		super(context, DB_NAME, null, 1);
		this.myContext = context;
		DB_PATH = "/data/data/" + context.getPackageName() + "/" + "databases/";
	}

	public void createDataBase() throws IOException {
		boolean dbExist = checkDataBase();
		if (dbExist) {
		} else {
			this.getReadableDatabase();
			try {
				copyDataBase();
			} catch (IOException e) {
				throw new Error("Error copying database");
			} finally {
				this.close();
			}
		}

	}

	private boolean checkDataBase() {
		SQLiteDatabase checkDB = null;

		try {
			String myPath = DB_PATH + DB_NAME;
			checkDB = SQLiteDatabase.openDatabase(myPath, null,
					SQLiteDatabase.OPEN_READONLY);

		} catch (Exception e) {
			// database does't exist yet.
		}
		if (checkDB != null) {
			checkDB.close();
		}
		return checkDB != null ? true : false;
	}

	private void copyDataBase() throws IOException {
		try {
			// Open your local db as the input stream
			InputStream myInput = myContext.getAssets().open(DB_NAME);
			// Path to the just created empty db
			String outFileName = DB_PATH + DB_NAME;
			// Open the empty db as the output stream
			OutputStream myOutput = new FileOutputStream(outFileName);
			// transfer bytes from the inputfile to the outputfile
			byte[] buffer = new byte[1024];
			int length;
			while ((length = myInput.read(buffer)) > 0) {
				myOutput.write(buffer, 0, length);
			}
			// Close the streams
			myOutput.flush();
			myOutput.close();
			myInput.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void openDataBase() throws SQLException {

		// Open the database
		String myPath = DB_PATH + DB_NAME;
		myDataBase = SQLiteDatabase.openDatabase(myPath, null,
				SQLiteDatabase.OPEN_READONLY);
	}

	@Override
	public synchronized void close() {
		if (myDataBase != null)
			myDataBase.close();
		SQLiteDatabase.releaseMemory();
		super.close();
	}
	

	// Sửa đổi giá trị (Chuyển từ vựng: chưa học > đã học)
	public int sua_1_tuvung(String name,String type) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_ISREAD, type);

		String whereClause = KEY_NAME + "=?";
		String[] whereArgs = { name };
		return db.update(DATABASE_TABLE, values, whereClause, whereArgs);
	}
	
	// Sửa đổi giá trị (Chuyển từ vựng: đã học > chưa học)
	public int sua_1_tuvung_kt(String name,String type) {
				SQLiteDatabase db = this.getWritableDatabase();
				ContentValues values = new ContentValues();
				values.put(KEY_ISNEXT, type);

				String whereClause = KEY_NAME + "=?";
				String[] whereArgs = { name };
				return db.update(DATABASE_TABLE, values, whereClause, whereArgs);
			}
	
	//phương thức: constructor
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
	}
	//phương thức: constructor
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}


}