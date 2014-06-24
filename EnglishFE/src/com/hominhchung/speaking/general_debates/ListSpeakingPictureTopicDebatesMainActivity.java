package com.hominhchung.speaking.general_debates;

import java.io.IOException;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
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

import com.hominhchung.R;

public class ListSpeakingPictureTopicDebatesMainActivity extends Activity {
	/*------------------------------ Khai báo------------------------------*/
	// Khai báo MyCustomAdapter
	MyCustomAdapter dataAdapter = null;

	int[] r = { R.drawable.gd_1, R.drawable.gd_1, R.drawable.gd_2,
			R.drawable.gd_3, R.drawable.gd_4, R.drawable.gd_5, R.drawable.gd_6,
			R.drawable.gd_7, R.drawable.gd_8, R.drawable.gd_9, R.drawable.gd_10 };

	// Khai báo truy vấn xml file
	static final String KEY_LESSON = "lesson"; // lesson node
	static final String KEY_ID = "id";
	static final String KEY_LOCAL_TITLE = "local_title";
	static final String KEY_EN_TITLE = "en_title";

	String en_text, local_question, en_hint, id_text; // biến nội dung truyền
														// vào mảng danh sách
	// phrase
	int id = 0;
	int i = 0; // biến đếm thứ tự của từ vựng
	String dem = ""; // biến đếm truyền vào danh sách phrase

	// Mảng danh sách của phrase
	ArrayList<AdapterGeneralDebates> phraseList;

	// TextToSpeech
	private TextToSpeech mTts;
	private static final int MY_DATA_CHECK_CODE = 1234;
	String content; // Truyền nội dung nói

	/*------------------------------onCreate------------------------------*/
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.module_list_search);
		// Tạo ra danh sách View từ ArrayList
		displayListView();

	}

	/*-------------Tạo ra danh sách View từ ArrayList(displayListView)-----------*/
	private void displayListView() {

		// Danh sách mảng của phrase
		phraseList = new ArrayList<AdapterGeneralDebates>();
		try {
			getItemFromXML(this);
		} catch (XmlPullParserException e) {
		} catch (IOException e) {
		}
		// Tạo ra một ArrayAdaptar từ mảng String
		dataAdapter = new MyCustomAdapter(this,
				R.layout.adapter_list_row_11, phraseList);
		ListView listView = (ListView) findViewById(R.id.list_view);
		// Gán các adapter vào ListView
		listView.setAdapter(dataAdapter);

		// Cho phép lướt listview nhanh
		listView.setFastScrollEnabled(true);

		// Cho phép tìm nhanh những nội dung của ListView
		listView.setTextFilterEnabled(true);

		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// Khi nhấn vào, hiển thị một toast với các văn bản TextView
				AdapterGeneralDebates adapterGeneralDebates = (AdapterGeneralDebates) parent
						.getItemAtPosition(position);

				// Truy vấn dữ liệu
				String topic = adapterGeneralDebates.getEn_topic().trim(); // Nội
																			// dung
				String question = adapterGeneralDebates.getLocal_question()
						.trim(); // Nội dung
				String hint = adapterGeneralDebates.getEn_hint().trim(); // Nội
																			// dung

				int numInt = adapterGeneralDebates.getId()-1;

				Intent ex = new Intent(
						ListSpeakingPictureTopicDebatesMainActivity.this,
						TabSpeakingGeneralDebatesTopicActivity.class);
				ex.putExtra("data", numInt);
				ex.putExtra("topic", topic);
				ex.putExtra("question", question);
				ex.putExtra("hint", hint);
				startActivity(ex);

			}
		});

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
	private class MyCustomAdapter extends ArrayAdapter<AdapterGeneralDebates> {
		private ArrayList<AdapterGeneralDebates> originalList;
		private ArrayList<AdapterGeneralDebates> phraseList;
		private PhraseFilter filter;

		public MyCustomAdapter(Context context, int textViewResourceId,
				ArrayList<AdapterGeneralDebates> countryList) {
			super(context, textViewResourceId, countryList);
			this.phraseList = new ArrayList<AdapterGeneralDebates>();
			this.phraseList.addAll(countryList);
			this.originalList = new ArrayList<AdapterGeneralDebates>();
			this.originalList.addAll(countryList);
		}

		@Override
		public Filter getFilter() {
			if (filter == null) {
				filter = new PhraseFilter();
			}
			return filter;
		}

		private class ViewHolder {
			TextView en_text;
			TextView local_text;
			ImageView image;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			ViewHolder holder = null;
			Log.v("ConvertView", String.valueOf(position));
			if (convertView == null) {

				LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = vi.inflate(R.layout.adapter_list_row_11,
						null);

				holder = new ViewHolder();
				holder.image = (ImageView) convertView.findViewById(R.id.photo);
				holder.en_text = (TextView) convertView
						.findViewById(R.id.tv_namelocal);
				holder.local_text = (TextView) convertView
						.findViewById(R.id.tv_enlocal);

				convertView.setTag(holder);

			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			// Set các giá trị
			AdapterGeneralDebates adapterGeneralDebates = phraseList
					.get(position);

			int numInt = adapterGeneralDebates.getId();

			holder.image.setImageResource(r[numInt]);
			holder.en_text.setText(adapterGeneralDebates.getEn_topic());
			holder.local_text
					.setText(adapterGeneralDebates.getLocal_question());

			// Hiển thị màu cho từng dùng của Listview
			if (position % 2 == 0) {
				convertView.setBackgroundColor(Color.rgb(238, 233, 233));
			} else {
				convertView.setBackgroundColor(Color.rgb(255, 255, 255));
			}

			return convertView;

		}

		// Tìm kiếm nội dung
		private class PhraseFilter extends Filter {

			@Override
			protected FilterResults performFiltering(CharSequence constraint) {

				constraint = constraint.toString().toLowerCase();
				FilterResults result = new FilterResults();
				if (constraint != null && constraint.toString().length() > 0) {
					ArrayList<AdapterGeneralDebates> filteredItems = new ArrayList<AdapterGeneralDebates>();

					for (int i = 0, l = originalList.size(); i < l; i++) {
						AdapterGeneralDebates adapterGeneralDebates = originalList
								.get(i);
						if (adapterGeneralDebates.toString().toLowerCase()
								.contains(constraint))
							filteredItems.add(adapterGeneralDebates);
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

				phraseList = (ArrayList<AdapterGeneralDebates>) results.values;
				notifyDataSetChanged();
				clear();
				for (int i = 0, l = phraseList.size(); i < l; i++)
					add(phraseList.get(i));
				notifyDataSetInvalidated();
			}
		}

	}

	/*------------------------------XmlPullParser------------------------------*/
	public void getItemFromXML(Activity activity)
			throws XmlPullParserException, IOException {
		Resources res = activity.getResources();
		XmlResourceParser xpp = res.getXml(R.xml.general_debates_speak);
		xpp.next();
		int eventType = xpp.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			if (eventType == XmlPullParser.START_TAG) {

				if (xpp.getName().equals(KEY_LESSON)) {
					id_text = (xpp.getAttributeValue(null, "id").toString());
					en_text = (xpp.getAttributeValue(null, "en_topic")
							.toString());
					en_hint = (xpp.getAttributeValue(null, "en_hint")
							.toString());
					local_question = (xpp.getAttributeValue(null,
							"local_question").toString());
					id = Integer.parseInt(id_text);
					AdapterGeneralDebates adapterGeneralDebates = new AdapterGeneralDebates(
							id, en_text, local_question, en_hint);
					phraseList.add(adapterGeneralDebates);
				}
				i++;

			}
			eventType = xpp.next();
		}

	}

}