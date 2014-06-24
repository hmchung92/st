package com.hominhchung.vocabulary;

import java.io.IOException;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hominhchung.R;
import com.hominhchung.adapter.ListAdapter;
import com.hominhchung.adapter.Webview;
import com.hominhchung.vocabulary.image_topic.ListLessonVocabularyImageMainActivity;
import com.hominhchung.vocabulary.irregular_verbs.ListIrregularLessonMainActivity;
import com.hominhchung.vocabulary.lookupwords.LookUpWordsMainActivity;
import com.hominhchung.vocabulary.phrases1000.ListLessonPhrasesMainActivity;
import com.hominhchung.vocabulary.qoutations.QoutationMainActivity;
import com.hominhchung.vocabulary.topic.ListTopicVocabularyMainActivity;
import com.hominhchung.vocabulary.video.ListVideoVocabularyMainActivity;
import com.hominhchung.vocabulary.vocabulary3500.TabVocabularyActivity;

public class ListVocabularyMainActivity extends Activity {
	/**
	 * Khai báo giá trị
	 */
	// Khai báo MyCustomAdapter
	MyCustomAdapter dataAdapter = null;

	// Khai báo truy vấn xml file
	static final String KEY_LESSON = "module1"; // parent node

	String en_text, local_text; // biến nội dung truyền vào mảng danh sách

	int i = 0; // biến đếm thứ tự của từ vựng
	String dem = ""; // biến đếm truyền vào danh sách

	// Mảng danh sách của simpleList
	ArrayList<ListAdapter> simpleList;

	// Hình ảnh đại diện cho từng bài học
	static final int KEY_ITEM_IMAGE = R.drawable.dashboarde__vocabulary;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.module_list_search);
		// Tạo ra danh sách View từ ArrayList
		displayListView();

	}

	/*-------------Tạo ra danh sách View từ ArrayList(displayListView)-----------*/
	private void displayListView() {

		// Danh sách mảng của simpleList
		simpleList = new ArrayList<ListAdapter>();
		try {
			getItemFromXML(this);
		} catch (XmlPullParserException e) {
		} catch (IOException e) {
		}
		// Tạo ra một ArrayAdaptar từ mảng String
		dataAdapter = new MyCustomAdapter(this,
				R.layout.adapter_list_row_3_phrases, simpleList);
		ListView listView = (ListView) findViewById(R.id.list_view);
		// Gán các adapter vào ListView
		listView.setAdapter(dataAdapter);

		// Cho phép lọc về những nội dung của ListView
		listView.setTextFilterEnabled(true);

		// Enabling Fast Scrolling
		listView.setFastScrollEnabled(true);

		// Thiết lập sự kiện khi nhấn vào listview
				listView.setOnItemClickListener(new OnItemClickListener() {
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {

						// Khi nhấn vào, hiển thị một toast với các văn bản TextView

						ListAdapter list_adapter = (ListAdapter) parent
								.getItemAtPosition(position);
						// Truyền dữ liệu để đọc(TextToSpeech) 
						int content = list_adapter.get_Id(); // Vị trí thật của listview

						switch (content) {
						case 1:
							Intent ex1 = new Intent(
									ListVocabularyMainActivity.this,
									LookUpWordsMainActivity.class);
							// đưa dữ liệu riêng lẻ vào Bundle
							ex1.putExtra("type", 0);
							startActivity(ex1);
							break;
						// Học 1500 từ vựng(Learning 1500 vocabulary)
						case 2:
							// Tạo Intent để mở new Activity
							Intent ex2 = new Intent(
									ListVocabularyMainActivity.this,
									TabVocabularyActivity.class);
							startActivity(ex2);

							break;
						// Động từ bất quy tắc(Irregular Verbs)
						case 3:

							Intent ex3 = new Intent(
									ListVocabularyMainActivity.this,
									ListIrregularLessonMainActivity.class);
							startActivity(ex3);
							break;
						// Danh ngôn(Qoutation)
						case 4:
							Intent ex4 = new Intent(
									ListVocabularyMainActivity.this,
									QoutationMainActivity.class);
							startActivity(ex4);
							break;
						// Từ viết tắt(Abbreviate)
						case 5:
							Intent ex5 = new Intent(
									ListVocabularyMainActivity.this,
									LessonVocabulary.class);
							ex5.putExtra("title",getString(R.string.abbreviation));
							ex5.putExtra("data",
									"vocabulary/Nhung tu viet tat thong dung trong Tieng Anh.html");
							startActivity(ex5);
							break;
						// 1000 Cụm từ thông dụng(1000 Regular expressions)
						case 6:
							Intent ex6 = new Intent(
									ListVocabularyMainActivity.this,
									ListLessonPhrasesMainActivity.class);
							startActivity(ex6);

							break;
						// Thơ(Poem)
						case 7:
							Intent ex7 = new Intent(
									ListVocabularyMainActivity.this,
									LessonVocabulary.class);
							ex7.putExtra("title",getString(R.string.poem));
							ex7.putExtra("data",
									"vocabulary/Hoc 3000 tu vung Tieng Anh bang tho.html");
							
							startActivity(ex7);
							break;

						// Từ điển tiếng Anh bằng hình ảnh(English dictionary with
						// pictures)

						case 8:
							Intent ex8 = new Intent(
									ListVocabularyMainActivity.this,
									ListLessonVocabularyImageMainActivity.class);
							startActivity(ex8);
							break;
						// Từ vựng tiếng Anh qua video(English Vocabulary through video)
						case 9:
							Intent ex9 = new Intent(
									ListVocabularyMainActivity.this,
									ListVideoVocabularyMainActivity.class);
							ex9.putExtra("data", "");
							startActivity(ex9);
							break;
						// Học từ vựng theo chủ đề(Learning vocabulary by topic)
						case 10:
							Intent ex10 = new Intent(
									ListVocabularyMainActivity.this,
									ListTopicVocabularyMainActivity.class);
							ex10.putExtra("data", "");
							startActivity(ex10);
							break;
						default:
							Toast.makeText(getApplicationContext(),
									"" + (position + 1), 2).show();
							break;
						}

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

	/*------------------------------MyCustomAdapter------------------------------*/
	private class MyCustomAdapter extends ArrayAdapter<ListAdapter> {
		private ArrayList<ListAdapter> originalList;
		private ArrayList<ListAdapter> simple_List;
		private PhraseFilter filter;

		public MyCustomAdapter(Context context, int textViewResourceId,
				ArrayList<ListAdapter> countryList) {
			super(context, textViewResourceId, countryList);
			this.simple_List = new ArrayList<ListAdapter>();
			this.simple_List.addAll(countryList);
			this.originalList = new ArrayList<ListAdapter>();
			this.originalList.addAll(countryList);
		}

		/**
		 * Tìm kiếm từng dòng trong listview
		 */
		@Override
		public Filter getFilter() {
			if (filter == null) {
				filter = new PhraseFilter();
			}
			return filter;
		}

		/**
		 * Khai báo biến trong View
		 */
		private class ViewHolder {

			TextView en_text;
			TextView local_text;
			ImageView itemImage;
		}

		/**
		 * Thiết lập View cho từng dòng của listview
		 */
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			ViewHolder holder = null;
			Log.v("ConvertView", String.valueOf(position));
			if (convertView == null) {

				LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = vi.inflate(R.layout.adapter_list_row_1, null);

				holder = new ViewHolder();
				holder.en_text = (TextView) convertView
						.findViewById(R.id.tv_enlocal);
				holder.local_text = (TextView) convertView
						.findViewById(R.id.tv_namelocal);
				holder.itemImage = (ImageView) convertView
						.findViewById(R.id.photo);

				convertView.setTag(holder);

			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			ListAdapter list_Adapter = simple_List.get(position);
			holder.en_text.setText(list_Adapter.getEn_text());
			holder.local_text.setText(list_Adapter.getLocal_text());
			holder.itemImage.setImageResource(KEY_ITEM_IMAGE);
			// Hiển thị màu cho từng dòng của Listview
			/*
			 * if (position % 2 == 0) {
			 * convertView.setBackgroundColor(Color.rgb(238, 233, 233)); } else
			 * { convertView.setBackgroundColor(Color.rgb(255, 255, 255)); }
			 */

			return convertView;

		}

		/**
		 * Tìm kiếm nội dung
		 */
		private class PhraseFilter extends Filter {

			@Override
			protected FilterResults performFiltering(CharSequence constraint) {

				constraint = constraint.toString().toLowerCase();
				FilterResults result = new FilterResults();
				if (constraint != null && constraint.toString().length() > 0) {
					ArrayList<ListAdapter> filteredItems = new ArrayList<ListAdapter>();

					for (int i = 0, l = originalList.size(); i < l; i++) {
						ListAdapter phrases = originalList.get(i);
						if (phrases.toString().toLowerCase()
								.contains(constraint))
							filteredItems.add(phrases);
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

				simple_List = (ArrayList<ListAdapter>) results.values;
				notifyDataSetChanged();
				clear();
				for (int i = 0, l = simple_List.size(); i < l; i++)
					add(simple_List.get(i));
				notifyDataSetInvalidated();
			}
		}

	}

	/*------------------------------XmlPullParser------------------------------*/
	public void getItemFromXML(Activity activity)
			throws XmlPullParserException, IOException {
		Resources res = activity.getResources();
		XmlResourceParser xpp = res.getXml(R.xml.module);
		xpp.next();
		int eventType = xpp.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			if (eventType == XmlPullParser.START_TAG) {

				if (xpp.getName().equals(KEY_LESSON)) {
					en_text = (xpp.getAttributeValue(null, "en_title")
							.toString());
					local_text = (xpp.getAttributeValue(null, "local_title")
							.toString());
					/*
					 * if (i <= 9) dem = "00" + i; else if (10 <= i && i <= 100)
					 * dem = "0" + i; else dem = "" + i;
					 */
					ListAdapter list_englishfe = new ListAdapter(i, en_text,
							local_text);
					simpleList.add(list_englishfe);
					
				}
				i++;

			}
			eventType = xpp.next();
		}

	}

}