package com.hominhchung.vocabulary.irregular_verbs;

import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hominhchung.R;
import com.hominhchung.adapter.Irregular_Verb;
import com.hominhchung.databasehelper.DataBaseHelper;

public class ListIrregularLessonMainActivity extends Activity{
	/**
	 * Khai báo giá trị
	 */
	// ProgressDialog
	private ProgressDialog mProgressDialog;
	
	// DataBaseHelper
	DataBaseHelper dbManager;
	protected SQLiteDatabase db;
	private static final String DB_TABLE = "irregular_verbs";
	
	// Khai báo MyCustomAdapter
	MyCustomAdapter dataAdapter = null;
	
	// Khai báo TextToSpeech
	private TextToSpeech tts;
	
	// Mảng danh sách của Irregular_Verb
	ArrayList<Irregular_Verb> irregular_verbList;

	// Khai báo Control
	ListView listView;
	
	private static final int MY_DATA_CHECK_CODE = 1234;
	String content; // Truyền nội dung nói

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.module_list_search);

		// Tạo ra danh sách View từ ArrayList
		displayListView();

	}

	/*-------------Tạo ra danh sách View từ ArrayList(displayListView)-----------*/
	private void displayListView() {

		listView = (ListView) findViewById(R.id.list_view);
		// Danh sách mảng của Irregular_Verb
		irregular_verbList = new ArrayList<Irregular_Verb>();
		//showDB();
		this.mProgressDialog = new ProgressDialog(this);
		this.mProgressDialog.setIndeterminate(true);
		this.mProgressDialog.setCancelable(false);
		new GetDataTask().execute(new Void[1]);
		//new GetDataTask().execute(new Void[1]);
		
		// Tạo ra một ArrayAdaptar từ mảng String
		/*
		 dataAdapter = new MyCustomAdapter(this,
				R.layout.module_list_row_7_vocabulary, vocabularyList);
		
		// Gán các adapter vào ListView
		listView.setAdapter(dataAdapter);
		*/

		// Cho phép lọc về những nội dung của ListView
		listView.setTextFilterEnabled(true);

		// Enabling Fast Scrolling
		listView.setFastScrollEnabled(true);

		// Thiết lập sự kiện khi nhấn vào listview
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// Khi nhấn vào, hiển thị một toast với các văn bản TextView
				Irregular_Verb tuvung_dong_hien_tai = irregular_verbList.get(position);
				String words_bundle = tuvung_dong_hien_tai.getInfinitive().toString();
				String simple_past_bundle = tuvung_dong_hien_tai.getPast_simple().toString();
				String participle_past_bundle = tuvung_dong_hien_tai.getPast_participle().toString();
				String vi_content_bundle = tuvung_dong_hien_tai.getVicontent().toString();
				String example_bundle = tuvung_dong_hien_tai.getExample().toString();

				Toast.makeText(getApplicationContext(), words_bundle,
						Toast.LENGTH_LONG).show();
				// Gọi hàm chuyển Activity
				Intent ex = new Intent(
						ListIrregularLessonMainActivity.this,
						IrregularLesson.class);
				// Thêm dữ liệu vào bundle
				ex.putExtra("words_bundle", words_bundle);
				ex.putExtra("simple_past_bundle", simple_past_bundle);
				ex.putExtra("participle_past_bundle", participle_past_bundle);
				ex.putExtra("vi_content_bundle", vi_content_bundle);
				ex.putExtra("example_bundle", example_bundle);
				startActivity(ex);
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
		dbManager.DATABASE_TABLE=DB_TABLE;
		String sql = "select * from "+DB_TABLE;

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
			String infinitive = cursor.getString(cursor.getColumnIndex("infinitive")); // từ
																			// vựng
			String past_simple = cursor.getString(cursor
					.getColumnIndex("past_simple")); // từ loại
			String past_participle = cursor.getString(cursor.getColumnIndex("past_participle")); // cách
																			// đọc
			String vicontent = cursor.getString(cursor
					.getColumnIndex("vi_content")); // nghĩa
			
			String example = cursor.getString(cursor
					.getColumnIndex("example")); // nghĩa
			Irregular_Verb irregular_verb = new Irregular_Verb(infinitive, past_simple, past_participle,
					vicontent,example);
			irregular_verbList.add(irregular_verb);
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
	private class MyCustomAdapter extends ArrayAdapter<Irregular_Verb> {
		private ArrayList<Irregular_Verb> originalList;
		private ArrayList<Irregular_Verb> vocabulary_List;
		private VocabularyFilter filter;

		public MyCustomAdapter(Context context, int textViewResourceId,
				ArrayList<Irregular_Verb> countryList) {
			super(context, textViewResourceId, countryList);
			this.vocabulary_List = new ArrayList<Irregular_Verb>();
			this.vocabulary_List.addAll(countryList);
			this.originalList = new ArrayList<Irregular_Verb>();
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
		}

		/**
		 * Thiết lập View cho từng dòng của listview
		 */
		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			// dùng LayoutInFlatter lấy đọc cấu trúc và nội dung của từng hàng
			// ListView
			LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			final View rowView = inflater.inflate(
					R.layout.adapter_list_row_6_iregular, parent, false);
			// đi tìm trong từng hàng ListView, cập nhật thông tin
			final RelativeLayout relativeLayout = (RelativeLayout) rowView
					.findViewById(R.id.line);
			final TextView tv_entitle = (TextView) rowView
					.findViewById(R.id.tv_entitle);
			final TextView tv_infinitive = (TextView) rowView
					.findViewById(R.id.tv_infinitive);
			final TextView tv_past_simple = (TextView) rowView
					.findViewById(R.id.tv_past_simple);
			final TextView tv_past_participle = (TextView) rowView
					.findViewById(R.id.tv_past_participle);
			final TextView tv_vicontent = (TextView) rowView
					.findViewById(R.id.tv_vicontent);

			ImageView iv_next = (ImageView) rowView.findViewById(R.id.iv_next);
			//ImageButton btn_close = (ImageButton) rowView.findViewById(R.id.btn_close);
			

			Irregular_Verb vocabulary = vocabulary_List.get(position);

			tv_entitle.setText(vocabulary.getInfinitive());
			tv_infinitive.setText(vocabulary.getInfinitive());
			tv_past_simple.setText(vocabulary.getPast_simple());
			tv_past_participle.setText(vocabulary.getPast_participle());
			tv_vicontent.setText(vocabulary.getVicontent());
			// imageView.setImageResource(R.drawable.important);

			iv_next.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {

					/*// Khi nhấn vào, hiển thị một toast với các văn bản TextView
					String words_bundle = tv_entitle.getText().toString();
					String simple_past_bundle = tv_infinitive.getText().toString();
					String participle_past_bundle = tv_past_simple.getText().toString();
					String vi_content_bundle = tv_past_participle.getText().toString();
					String example_bundle = tv_vicontent.getText().toString();

					Toast.makeText(getApplicationContext(), words_bundle,
							Toast.LENGTH_LONG).show();
					// Gọi hàm chuyển Activity
					Intent ex = new Intent(
							ListIrregularLessonMainActivity.this,
							IrregularLesson.class);
					// Thêm dữ liệu vào bundle
					ex.putExtra("words_bundle", words_bundle);
					ex.putExtra("simple_past_bundle", simple_past_bundle);
					ex.putExtra("participle_past_bundle", participle_past_bundle);
					ex.putExtra("vi_content_bundle", vi_content_bundle);
					ex.putExtra("example_bundle", example_bundle);
					startActivity(ex);*/
					// Khi nhấn vào, hiển thị một toast với các văn bản TextView
					Irregular_Verb tuvung_dong_hien_tai = irregular_verbList.get(position);
					String words_bundle = tuvung_dong_hien_tai.getInfinitive().toString();
					String simple_past_bundle = tuvung_dong_hien_tai.getPast_simple().toString();
					String participle_past_bundle = tuvung_dong_hien_tai.getPast_participle().toString();
					String vi_content_bundle = tuvung_dong_hien_tai.getVicontent().toString();
					String example_bundle = tuvung_dong_hien_tai.getExample().toString();

					Toast.makeText(getApplicationContext(), words_bundle,
							Toast.LENGTH_LONG).show();
					// Gọi hàm chuyển Activity
					Intent ex = new Intent(
							ListIrregularLessonMainActivity.this,
							IrregularLesson.class);
					// Thêm dữ liệu vào bundle
					ex.putExtra("words_bundle", words_bundle);
					ex.putExtra("simple_past_bundle", simple_past_bundle);
					ex.putExtra("participle_past_bundle", participle_past_bundle);
					ex.putExtra("vi_content_bundle", vi_content_bundle);
					ex.putExtra("example_bundle", example_bundle);
					startActivity(ex);
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
					ArrayList<Irregular_Verb> filteredItems = new ArrayList<Irregular_Verb>();

					for (int i = 0, l = originalList.size(); i < l; i++) {
						Irregular_Verb vocabulary = originalList.get(i);
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

				vocabulary_List = (ArrayList<Irregular_Verb>) results.values;
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
			if ((ListIrregularLessonMainActivity.this.mProgressDialog != null)
					&& (ListIrregularLessonMainActivity.this.mProgressDialog
							.isShowing()))
				ListIrregularLessonMainActivity.this.mProgressDialog.dismiss();
			
			// Tạo ra một ArrayAdaptar từ mảng String
			tao_ArrayAdaptar();
			
			// Gán các adapter vào ListView
			listView.setAdapter(dataAdapter);
		}

		protected void onPreExecute() {
			super.onPreExecute();
			ListIrregularLessonMainActivity.this.mProgressDialog
					.setMessage(getString(R.string.please_wait_for_a_moment));
			ListIrregularLessonMainActivity.this.mProgressDialog.show();
		}
	}
	
	public void tao_ArrayAdaptar(){
		dataAdapter = new MyCustomAdapter(this,
				R.layout.adapter_list_row_5_vocabulary, irregular_verbList);
	}

	

	
	@Override
	protected void onResume() {
		super.onResume();
		
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onRestart() {
		super.onRestart();
	}

}