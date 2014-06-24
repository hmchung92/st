package com.hominhchung.databasehelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hominhchung.adapter.Cauhoi;

public class quanlycauhoi extends SQLiteOpenHelper {

	/**
	 * Đường dẫn tới csdl
	 */
	private static String DB_PATH = "/data/data/com.hominhchung/databases/";
	/**
	 * Tên csdl
	 */
	private static String DB_NAME = "mydatabase.sqlite";
	/**
	 * Phiên bản
	 */
	private static final int DB_VERSION = 1;
	/**
	 * Tên bảng và các trường
	 */
	public static String TABLE_NAME = "";

	private SQLiteDatabase myDatabase;
	private final Context myContext;

	/**
	 * Quản lý câu hỏi
	 */
	public quanlycauhoi(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		// TODO Auto-generated constructor stub
		myContext = context;
	}

	/**
	 * Mỡ csdl
	 */
	public void openDatabase() throws SQLException {
		String myPath = DB_PATH + DB_NAME;
		myDatabase = SQLiteDatabase.openDatabase(myPath, null,
				SQLiteDatabase.OPEN_READONLY);

	}

	/**
	 * Đóng csdl
	 */
	public synchronized void close() {
		if (myDatabase != null)
			myDatabase.close();
		super.close();
	}

	/**
	 * Kiểm tra csdl
	 */
	private boolean checkDatabase() {
		SQLiteDatabase checkDB = null;
		try {
			String myPath = DB_PATH + DB_NAME;
			checkDB = SQLiteDatabase.openDatabase(myPath, null,
					SQLiteDatabase.OPEN_READONLY);
		} catch (SQLException e) {

		}
		if (checkDB != null)
			checkDB.close();
		return checkDB != null ? true : false;
	}

	/**
	 * Sao chép csdl từ asset vào tập tin gốc
	 */
	private void copyDatabase() throws IOException {
		InputStream myInput = myContext.getAssets().open(DB_NAME);
		String outFileName = DB_PATH + DB_NAME;
		OutputStream myOutput = new FileOutputStream(outFileName);
		// sao chép dữ liệu để tập tin rỗng
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer)) > 0) {
			myOutput.write(buffer, 0, length);
		}
		
		// Đóng streams  
		myOutput.flush();
		myOutput.close();
		myInput.close();
	}

	/**
	 * Tạo csdl
	 */
	public void createDatabase() throws IOException {
		boolean dbExist = checkDatabase();

		if (dbExist) {

		} else {
			this.getReadableDatabase();
			try {
				copyDatabase();
			} catch (IOException e) {
				throw new Error("Error copy database");
			}

		}
	}

	/**
	 * Truy vấn: Lấy câu hỏi
	 */
	public Cursor laytatcacauhoi() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor contro = db.rawQuery("SELECT * FROM "+TABLE_NAME, null);
		return contro;
	}

	/**
	 * Truy vấn: lấy ngẫu nhiêu n câu hỏi
	 */
	public List<Cauhoi> layNcaungaunghien(int socau) {
		List<Cauhoi> ds_cauhoi = new ArrayList<Cauhoi>();
		String limit = "0, " + socau;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor contro = db.query(TABLE_NAME, null, null, null, null, null,
				"random()", limit);
		contro.moveToFirst();
		do {
			Cauhoi x = new Cauhoi();
			x._id = Integer.parseInt(contro.getString(0));
			x.cauhoi = contro.getString(1);
			x.cau_a = contro.getString(2);
			x.cau_b = contro.getString(3);
			x.cau_c = contro.getString(4);
			x.cau_d = contro.getString(5);
			x.dapan = contro.getString(6);
			x.cautraloi = "";
			ds_cauhoi.add(x);
		} while (contro.moveToNext());
		return ds_cauhoi;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
