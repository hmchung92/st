package com.hominhchung.vocabulary.lookupwords;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hominhchung.R;
import com.hominhchung.adapter.Vocabulary;
import com.hominhchung.databasehelper.DataBaseHelper;

public class LookUpWordsMainActivity extends Activity implements
		TextToSpeech.OnInitListener {
	/**
	 * Khai báo giá trị
	 */
	// ProgressDialog
	private ProgressDialog mProgressDialog;

	// DataBaseHelper
	DataBaseHelper dbManager;
	protected SQLiteDatabase db;
	private static final String DB_TABLE = "englishcommon";

	// Khai báo MyCustomAdapter
	MyCustomAdapter dataAdapter = null;

	// Khai báo TextToSpeech
	private TextToSpeech tts;

	// Mảng danh sách của vocabulary
	ArrayList<Vocabulary> vocabularyList;

	// Khai báo Control
	ListView listView;

	// Dữ liêu Bundle
	int type = 0; // 0: từ vựng chưa học, 1: từ vựng đã học
	int state = 0; // trạng thái(0: ko có sự kiện xảy ra, 1: có sự kiện)

	private static final int MY_DATA_CHECK_CODE = 1234;
	String content; // Truyền nội dung nói

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.module_list_search);

		// Khai báo TextToSpeech
		tts = new TextToSpeech(getApplicationContext(), this);

		// Tạo ra danh sách View từ ArrayList
		displayListView();

	}


	/*-------------Tạo ra danh sách View từ ArrayList(displayListView)-----------*/
	private void displayListView() {

		listView = (ListView) findViewById(R.id.list_view);
		// Danh sách mảng của vocabulary
		vocabularyList = new ArrayList<Vocabulary>();
		// showDB();
		this.mProgressDialog = new ProgressDialog(this);
		this.mProgressDialog.setIndeterminate(true);
		this.mProgressDialog.setCancelable(false);
		new GetDataTask().execute(new Void[1]);
		// new GetDataTask().execute(new Void[1]);

		// Tạo ra một ArrayAdaptar từ mảng String
		/*
		 * dataAdapter = new MyCustomAdapter(this,
		 * R.layout.module_list_row_7_vocabulary, vocabularyList);
		 * 
		 * // Gán các adapter vào ListView listView.setAdapter(dataAdapter);
		 */

		// Cho phép lọc về những nội dung của ListView
		listView.setTextFilterEnabled(true);

		// Enabling Fast Scrolling
		listView.setFastScrollEnabled(true);

		// Thiết lập sự kiện khi nhấn vào listview
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

			}
		});

		// Thiết lập sự kiện cho phép tìm kiếm giá trị theo từng dòng trong
		// listview

		EditText myFilter = (EditText) findViewById(R.id.inputSearch);
		myFilter.addTextChangedListener(new TextWatcher() {

			public void afterTextChanged(Editable s) {
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				dataAdapter.getFilter().filter(s.toString());
			}
		});
	}

	public void showDB() {
		dbManager = new DataBaseHelper(this);
		dbManager.DATABASE_TABLE = DB_TABLE;
		String sql = "select * from " + DB_TABLE;

		// Chep File MYPTITDEMO.sqlite vao
		// "/data/data/com.hominhchung/databases/";
		try {
			dbManager.createDataBase();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		SQLiteDatabase db = dbManager.getReadableDatabase();
		Cursor cursor = db.rawQuery(sql, null);
		cursor.moveToFirst();
		String str = "";
		while (!cursor.isAfterLast()) {

			String id = cursor.getString(cursor.getColumnIndex("_id"));
			String name = cursor.getString(cursor.getColumnIndex("name")); // từ
																			// vựng
			String category = cursor.getString(cursor
					.getColumnIndex("category")); // từ loại
			String read = cursor.getString(cursor.getColumnIndex("read")); // cách
																			// đọc
			String vicontent = cursor.getString(cursor
					.getColumnIndex("vicontent")); // nghĩa
			Vocabulary vocabulary = new Vocabulary(id, name, category, read,
					vicontent);
			vocabularyList.add(vocabulary);
			cursor.moveToNext();

		}

		// showTxt.setText(str);
		cursor.moveToLast();
		// showTxt.setText(cursor.getString(cursor.getColumnIndex("QCONTENT")));
		cursor.close();
		db.close();
		dbManager.close();
	}

	/*------------------------------MyCustomAdapter------------------------------*/
	private class MyCustomAdapter extends ArrayAdapter<Vocabulary> {
		private ArrayList<Vocabulary> originalList;
		private ArrayList<Vocabulary> vocabulary_List;
		private VocabularyFilter filter;

		public MyCustomAdapter(Context context, int textViewResourceId,
				ArrayList<Vocabulary> countryList) {
			super(context, textViewResourceId, countryList);
			this.vocabulary_List = new ArrayList<Vocabulary>();
			this.vocabulary_List.addAll(countryList);
			this.originalList = new ArrayList<Vocabulary>();
			this.originalList.addAll(countryList);
		}

		/**
		 * Tìm kiếm từng dòng trong listview
		 */
		@Override
		public Filter getFilter() {
			if (filter == null) {
				filter = new VocabularyFilter();
			}
			return filter;
		}

		/**
		 * Khai báo biến trong View
		 */
		private class ViewHolder {
			TextView tv_entitle, tv_category, tv_read, tv_vicontent;
			ImageButton btn_sound, btn_close;
			RelativeLayout relativeLayout;
		}

		/**
		 * Thiết lập View cho từng dòng của listview
		 */
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// dùng LayoutInFlatter lấy đọc cấu trúc và nội dung của từng hàng
			// ListView
			LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			final View rowView = inflater.inflate(
					R.layout.adapter_list_row_5_vocabulary, parent, false);
			// đi tìm trong từng hàng ListView, cập nhật thông tin
			final RelativeLayout relativeLayout = (RelativeLayout) rowView
					.findViewById(R.id.line);
			final TextView tv_entitle = (TextView) rowView
					.findViewById(R.id.tv_entitle);
			final TextView tv_category = (TextView) rowView
					.findViewById(R.id.tv_category);
			final TextView tv_read = (TextView) rowView
					.findViewById(R.id.tv_read);
			final TextView tv_vicontent = (TextView) rowView
					.findViewById(R.id.tv_vicontent);

			ImageButton btn_sound = (ImageButton) rowView
					.findViewById(R.id.btn_sound);
			ImageButton btn_close = (ImageButton) rowView
					.findViewById(R.id.btn_close);
			// textView.setTextSize(14);

			Vocabulary vocabulary = vocabulary_List.get(position);

			tv_entitle.setText(vocabulary.getName());
			tv_category.setText(vocabulary.getCatagory());
			tv_read.setText(vocabulary.getRead());
			tv_vicontent.setText(vocabulary.getVicontent());

			// imageView.setImageResource(R.drawable.important);

			btn_sound.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {

					content = tv_entitle.getText().toString().trim();
					speakOut(content);
				}
			});
			btn_close.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {

					relativeLayout.removeAllViews();
					rowView.clearFocus();
					rowView.clearAnimation();

					String name = tv_entitle.getText().toString();

					Toast.makeText(getApplicationContext(), name.trim(),
							Toast.LENGTH_LONG).show();

					dbManager.sua_1_tuvung(name, "1");

					dbManager.close();

				}
			});

			// Hiển thị màu cho từng dùng của Listview
			if (position % 2 == 0) {
				rowView.setBackgroundColor(Color.rgb(238, 233, 233));
			} else {
				rowView.setBackgroundColor(Color.rgb(255, 255, 255));
			}

			return rowView;
		}

		/**
		 * Tìm kiếm nội dung
		 */
		/*--------------------------VocabularyFilter---------------------*/
		private class VocabularyFilter extends Filter {

			@Override
			protected FilterResults performFiltering(CharSequence constraint) {

				constraint = constraint.toString().toLowerCase();
				FilterResults result = new FilterResults();
				if (constraint != null && constraint.toString().length() > 0) {
					ArrayList<Vocabulary> filteredItems = new ArrayList<Vocabulary>();

					for (int i = 0, l = originalList.size(); i < l; i++) {
						Vocabulary vocabulary = originalList.get(i);
						if (vocabulary.toString().toLowerCase()
								.contains(constraint))
							filteredItems.add(vocabulary);
					}
					result.count = filteredItems.size();
					result.values = filteredItems;
				} else {
					synchronized (this) {
						result.values = originalList;
						result.count = originalList.size();
					}
				}
				return result;
			}

			@SuppressWarnings("unchecked")
			@Override
			protected void publishResults(CharSequence constraint,
					FilterResults results) {

				vocabulary_List = (ArrayList<Vocabulary>) results.values;
				notifyDataSetChanged();
				clear();
				for (int i = 0, l = vocabulary_List.size(); i < l; i++)
					add(vocabulary_List.get(i));
				notifyDataSetInvalidated();
			}
		}

	}

	/*-------------------------------------------AsyncTask---------------------------------------------*/
	public class GetDataTask extends AsyncTask<Void, Void, Void> {
		public GetDataTask() {
		}

		protected Void doInBackground(Void[] paramArrayOfVoid) {

			showDB();

			return null;
		}

		protected void onPostExecute(Void paramVoid) {
			super.onPostExecute(paramVoid);
			if ((LookUpWordsMainActivity.this.mProgressDialog != null)
					&& (LookUpWordsMainActivity.this.mProgressDialog
							.isShowing()))
				LookUpWordsMainActivity.this.mProgressDialog.dismiss();

			// Tạo ra một ArrayAdaptar từ mảng String
			tao_ArrayAdaptar();

			// Gán các adapter vào ListView
			listView.setAdapter(dataAdapter);
		}

		protected void onPreExecute() {
			super.onPreExecute();
			LookUpWordsMainActivity.this.mProgressDialog
					.setMessage("Please wait...");
			LookUpWordsMainActivity.this.mProgressDialog.show();
		}
	}

	public void tao_ArrayAdaptar() {
		dataAdapter = new MyCustomAdapter(this,
				R.layout.adapter_list_row_5_vocabulary, vocabularyList);
	}

	/*--------------------------TextToSpeech.OnInitListener---------------------*/

	@Override
	public void onDestroy() {
		// Don't forget to shutdown!
		if (tts != null) {
			tts.stop();
			tts.shutdown();
		}
		super.onDestroy();
	}

	@Override
	public void onInit(int status) {
		// TODO Auto-generated method stub

		if (status == TextToSpeech.SUCCESS) {

			int result = tts.setLanguage(Locale.US);

			// tts.setPitch(5); // set pitch level

			// tts.setSpeechRate(2); // set speech speed rate

			if (result == TextToSpeech.LANG_MISSING_DATA
					|| result == TextToSpeech.LANG_NOT_SUPPORTED) {
				Log.e("TTS", "Language is not supported");
			} else {
				// speakOut(String text);
			}

		} else {
			Intent marketIntent = new Intent(Intent.ACTION_VIEW);
			marketIntent.setData(Uri.parse(getString(R.string.url_download_tts)));
			startActivity(marketIntent);

		}

	}

	private void speakOut(String text) {
		Toast.makeText(getApplicationContext(), text, 2).show();
		tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
	}

}