package com.hominhchung.vocabulary.qoutations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hominhchung.R;

public class List_View extends ListFragment implements TextToSpeech.OnInitListener {
	String[] list_items;
	ArrayList<HashMap<String, String>> sessionList;
	LazyAdapterWriting adapter;
	int i = 0;
	String item;
	static String KEY_LESSON = "quotations"; // parent node
	static final String KEY_ID = "id";
	static final String KEY_LOCAL_TITLE = "local_title";
	static final String KEY_EN_TITLE = "en_title";
	static int CATEGORY_ID = 1;

	// Khai báo TextToSpeech
	private TextToSpeech tts;
	//AdapterTextToSpeech adapterTextToSpeech;
	String content; // Truyền nội dung nói
	private static final int MY_DATA_CHECK_CODE = 1234;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.adapter_list_apophthegm,
				container, false);

		// Khai báo TextToSpeech
		tts = new TextToSpeech(getActivity(), this);
		//adapterTextToSpeech = new AdapterTextToSpeech(getActivity());
		
		sessionList = new ArrayList<HashMap<String, String>>();

		// Lay du lieu ta khoi bundle 
		getBundle();

		// Lay du lieu tu file xml vao ArrayList
		try {
			item = getItemFromXML(this);
		} catch (XmlPullParserException e) {
		} catch (IOException e) {
		}
		
		adapter = new LazyAdapterWriting(getActivity(), sessionList);
		
		// setListAdapter(new ArrayAdapter<String>(getActivity(),
		// android.R.layout.simple_list_item_1, items));
		
		// gan adapter vao listview
		
		
		setListAdapter(adapter);
		
		return rootView;
	}

	private void getBundle() {
		// TODO Auto-generated method stub
		Bundle bundle = this.getArguments();
		CATEGORY_ID = bundle.getInt("type_bundle");
	}

	public String getItemFromXML(List_View list_View)
			throws XmlPullParserException, IOException {
		StringBuffer stringBuffer = new StringBuffer();
		Resources res = list_View.getResources();
		XmlResourceParser xpp = res.getXml(R.xml.quotations);
		xpp.next();
		int eventType = xpp.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			if (eventType == XmlPullParser.START_TAG) {
				if (xpp.getName().equals(KEY_LESSON)
						&& xpp.getAttributeValue(null, "category_id")
								.toString().equals(CATEGORY_ID + "")) {
					HashMap<String, String> map = new HashMap<String, String>();
					i++;
					if (i <= 9) {
						map.put(KEY_LOCAL_TITLE, "0"
								+ i
								+ ". "
								+ xpp.getAttributeValue(null, "en_text")
										.toString());
					} else {
						map.put(KEY_LOCAL_TITLE, i
								+ ". "
								+ xpp.getAttributeValue(null, "en_text")
										.toString());
					}
					map.put(KEY_EN_TITLE, xpp
							.getAttributeValue(null, "vi_text").toString());
					sessionList.add(map);
				}
			}
			eventType = xpp.next();
		}
		return stringBuffer.toString();

	}

	public class LazyAdapterWriting extends BaseAdapter {

		private final Activity activity;
		private final ArrayList<HashMap<String, String>> data;
		private LayoutInflater inflater = null;

		public LazyAdapterWriting(Activity a,
				ArrayList<HashMap<String, String>> d) {
			activity = a;
			data = d;
			inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		@Override
		public int getCount() {
			return data.size();
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View vi = convertView;
			if (convertView == null)
				vi = inflater.inflate(R.layout.adapter_list_row_7_apophthegm, null);

			final TextView tv_namelocal = (TextView) vi
					.findViewById(R.id.tv_en_text); // tv_namelocal
			TextView tv_enlocal = (TextView) vi.findViewById(R.id.tv_vi_text); // tv_enlocal
			Button btn_sound_offline = (Button) vi
					.findViewById(R.id.btn_sound_offline); // btn_sound_offline
			Button btn_sound_online = (Button) vi
					.findViewById(R.id.btn_sound_online); // btn_sound_online

			HashMap<String, String> lesson = new HashMap<String, String>();
			lesson = data.get(position);
			// Setting all values in listview
			tv_namelocal.setText(lesson.get(List_View.KEY_LOCAL_TITLE));
			tv_enlocal.setText(lesson.get(List_View.KEY_EN_TITLE));

			btn_sound_offline.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {

					content = tv_namelocal.getText().toString().trim();
					speakOut(content);
					//adapterTextToSpeech.speakOut(content);
				}
			});
			
			btn_sound_online.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					Toast.makeText(getActivity(), "Đang cập nhật...\n Vui lòng nghe offline!!!", 1).show();
				}
			});

			return vi;
		}

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
			// Toast.makeText(this, "Sorry! Text To Speech failed...",
			// 1).show();
			Log.e("TTS", "Initilization Failed");

			 
		}

	}

	private void speakOut(String text) {
		// Toast.makeText(getApplicationContext(), text, 2).show();
		tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
	}
}
