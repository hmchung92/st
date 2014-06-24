package com.hominhchung.listening.topic100;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Color;
import android.net.Uri;
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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hominhchung.R;
import com.hominhchung.adapter.Adapter;

public class List100ListeningTopicMainActivity extends Activity implements
		TextToSpeech.OnInitListener {
	/*------------------------------ Khai báo------------------------------*/
	// Khai báo MyCustomAdapter
	MyCustomAdapter dataAdapter = null;

	// Khai báo truy vấn xml file
	static final String KEY_LESSON = "lesson"; // lesson node
	static final String KEY_ID = "id";
	static final String KEY_LOCAL_TITLE = "local_title";
	static final String KEY_EN_TITLE = "en_title";

	String en_text, local_text; // biến nội dung truyền vào mảng danh sách phrase
	int i = 0; // biến đếm thứ tự của từ vựng
	String dem = ""; // biến đếm truyền vào danh sách phrase

	// Mảng danh sách của phrase
	ArrayList<Adapter> phraseList;

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
		phraseList = new ArrayList<Adapter>();
		try {
			getItemFromXML(this);
		} catch (XmlPullParserException e) {
		} catch (IOException e) {
		}
		// Tạo ra một ArrayAdaptar từ mảng String
		dataAdapter = new MyCustomAdapter(this,
				R.layout.adapter_list_row_2, phraseList);
		ListView listView = (ListView) findViewById(R.id.list_view);
		// Gán các adapter vào ListView
		listView.setAdapter(dataAdapter);

		// Cho phép lướt listview nhanh
		listView.setFastScrollEnabled(true);
		
		// Cho phép lọc về những nội dung của ListView
		listView.setTextFilterEnabled(true);

		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// Khi nhấn vào, hiển thị một toast với các văn bản TextView
				Adapter adapter = (Adapter) parent.getItemAtPosition(position);

				// Truyền dữ liệu để đọc(TextToSpeech)
				content = adapter.getEn_text().trim(); // Nội dung
				Toast.makeText(List100ListeningTopicMainActivity.this, content,
						Toast.LENGTH_LONG).show(); // Hiển thị nội dung
				// Khởi động hàm TextToSpeech
				/*Intent checkIntent = new Intent();
				checkIntent
						.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
				startActivityForResult(checkIntent, MY_DATA_CHECK_CODE);*/
				int num=position+1;
				String eng_title = adapter.getEn_text();
				String local_title = adapter.getLocal_text();
				Intent ex= new Intent(List100ListeningTopicMainActivity.this,Lesson100ListeningTopicActivity2.class);
				ex.putExtra("data",num);
				ex.putExtra("lesson","item"+num);
				ex.putExtra("eng_title",eng_title);
				ex.putExtra("local_title",local_title);
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
	private class MyCustomAdapter extends ArrayAdapter<Adapter> {
		private ArrayList<Adapter> originalList;
		private ArrayList<Adapter> phraseList;
		private PhraseFilter filter;

		public MyCustomAdapter(Context context, int textViewResourceId,
				ArrayList<Adapter> countryList) {
			super(context, textViewResourceId, countryList);
			this.phraseList = new ArrayList<Adapter>();
			this.phraseList.addAll(countryList);
			this.originalList = new ArrayList<Adapter>();
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
			TextView id;
			TextView en_text;
			TextView local_text;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			ViewHolder holder = null;
			Log.v("ConvertView", String.valueOf(position));
			if (convertView == null) {

				LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = vi.inflate(R.layout.adapter_list_row_2,
						null);

				holder = new ViewHolder();
				holder.id = (TextView) convertView.findViewById(R.id.tv_num);
				holder.en_text = (TextView) convertView
						.findViewById(R.id.tv_namelocal);
				holder.local_text = (TextView) convertView
						.findViewById(R.id.tv_enlocal);

				convertView.setTag(holder);

			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			Adapter adapter = phraseList.get(position);
			holder.id.setText(adapter.getId());
			holder.en_text.setText(adapter.getEn_text());
			holder.local_text.setText(adapter.getLocal_text());

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
					ArrayList<Adapter> filteredItems = new ArrayList<Adapter>();

					for (int i = 0, l = originalList.size(); i < l; i++) {
						Adapter adapter = originalList.get(i);
						if (adapter.toString().toLowerCase()
								.contains(constraint))
							filteredItems.add(adapter);
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

				phraseList = (ArrayList<Adapter>) results.values;
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
		XmlResourceParser xpp = res.getXml(R.xml.listen);
		xpp.next();
		int eventType = xpp.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			if (eventType == XmlPullParser.START_TAG) {
				
				if (xpp.getName().equals(KEY_LESSON)) {
					en_text = (xpp.getAttributeValue(null, "en_title")
							.toString());
					local_text = (xpp.getAttributeValue(null, "local_title")
							.toString());
					if (i <= 9)
						dem = "0" + i;
					else dem = "" + i;
					Adapter adapter = new Adapter(dem + "", en_text, local_text);
					phraseList.add(adapter);
				}i++;
				
			}
			eventType = xpp.next();
		}

	}

	/*-------------------------------------------Text to speed---------------------------------------------*/
	public void speakClicked(View v) {
		// lấy nội dung của hộp văn bản.

		mTts.speak("Hello", TextToSpeech.QUEUE_FLUSH, // Bỏ tất cả các mục trong
														// khi chờ trong hàng
														// đợi phát lại.
				null);
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == MY_DATA_CHECK_CODE) {
			if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
				// thành công, tạo ra trường hợp TTS
				mTts = new TextToSpeech(this, this);
			} else {
				// thiếu dữ liệu, cài đặt nó
				Intent installIntent = new Intent();
				installIntent
						.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
				startActivity(installIntent);
			}
		}
	}

	@Override
	public void onInit(int status) {
		if (status == TextToSpeech.SUCCESS) {

			int result = mTts.setLanguage(Locale.US);

			// tts.setPitch(5); // set pitch level

			// tts.setSpeechRate(2); //Thiết lập tỷ lệ tốc độ bài phát

			if (result == TextToSpeech.LANG_MISSING_DATA
					|| result == TextToSpeech.LANG_NOT_SUPPORTED) {
				Log.e("TTS", "Language is not supported");
			} else {
				mTts.speak(content, TextToSpeech.QUEUE_FLUSH, null);
			}

		} else {
			/*
			 * Toast.makeText(this, "Sorry! Text To Speech failed...",
			 * 1).show(); Log.e("TTS", "Initilization Failed");
			 */
			// Cài đặt tts nếu nếu máy chưa cài đặt 
			Intent marketIntent = new Intent(Intent.ACTION_VIEW);
			marketIntent
					.setData(Uri
							.parse(getString(R.string.url_download_tts)));
			startActivity(marketIntent);
		}
		/*
		 * mTts.speak("Hello folks, welcome to my little demo on Text To Speech."
		 * , TextToSpeech.QUEUE_FLUSH,null);
		 */

	}

	@Override
	public void onDestroy() {
		// Don't forget to shutdown!
		if (mTts != null) {
			mTts.stop();
			mTts.shutdown();
		}
		super.onDestroy();
	}

}