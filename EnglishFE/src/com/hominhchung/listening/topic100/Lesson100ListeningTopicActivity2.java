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
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hominhchung.R;
import com.hominhchung.adapter.Adapter;

public class Lesson100ListeningTopicActivity2 extends Activity implements
		TextToSpeech.OnInitListener {
	/**
	 * Khai báo giá trị
	 */
	// Khai báo MyCustomAdapter
	MyCustomAdapter dataAdapter = null;

	// Khai báo TextView
	TextView tv_namelocal_title, tv_enlocal_title;

	// Bundle data
	static int BUNDLE_KEY = 2;
	static String BUNDLE_ENG_TITLE = "", BUNDLE_LOCAL_TITLE = "";

	// Khai báo truy vấn xml file
	static final String KEY_LESSON = "item2"; // parent node

	String en_text, local_text, person_text; // biến nội dung truyền vào mảng
												// danh sách

	String en_title_topic = "en_title_topic", local_title_topic = "local_title_topic"; // tiêu đề chủ
																	// đề nghe

	// phrase
	int i = 0; // biến đếm thứ tự của từ vựng
	String dem = ""; // biến đếm truyền vào danh sách phrase

	// Mảng danh sách của phrase
	ArrayList<Adapter> phraseList;

	// RelativeLayout
	RelativeLayout rl_title;

	// ImageView
	//ImageView ic_media_previous, ic_media_next;

	// Mp3
	// private Button buttonPlayStop;
	private CheckBox btnPlay;
	private MediaPlayer mediaPlayer;
	private SeekBar seekBar;
	// Audio file
	// r[]={R.raw.lesson_1,R.raw.lesson_2,R.raw.lesson_3,R.raw.lesson_4,R.raw.lesson_5,R.raw.lesson_6,R.raw.lesson_7,R.raw.lesson_8,R.raw.lesson_9,R.raw.lesson_10,R.raw.lesson_11,R.raw.lesson_12,R.raw.lesson_13,R.raw.lesson_14,R.raw.lesson_15,R.raw.lesson_16,R.raw.lesson_17,R.raw.lesson_18,R.raw.lesson_19,R.raw.lesson_20,R.raw.lesson_21,R.raw.lesson_22,R.raw.lesson_23,R.raw.lesson_24,R.raw.lesson_25,R.raw.lesson_26,R.raw.lesson_27,R.raw.lesson_28,R.raw.lesson_29,R.raw.lesson_30,R.raw.lesson_31,R.raw.lesson_32,R.raw.lesson_33,R.raw.lesson_34,R.raw.lesson_35,R.raw.lesson_36,R.raw.lesson_37,R.raw.lesson_38,R.raw.lesson_39,R.raw.lesson_40,R.raw.lesson_41,R.raw.lesson_42,R.raw.lesson_43,R.raw.lesson_44,R.raw.lesson_45,R.raw.lesson_46,R.raw.lesson_47,R.raw.lesson_48,R.raw.lesson_49,R.raw.lesson_50,R.raw.lesson_51,R.raw.lesson_52,R.raw.lesson_53,R.raw.lesson_54,R.raw.lesson_55,R.raw.lesson_56,R.raw.lesson_57,R.raw.lesson_58,R.raw.lesson_59,R.raw.lesson_60,R.raw.lesson_61,R.raw.lesson_62,R.raw.lesson_63,R.raw.lesson_64,R.raw.lesson_65,R.raw.lesson_66,R.raw.lesson_67,R.raw.lesson_68,R.raw.lesson_69,R.raw.lesson_70,R.raw.lesson_71,R.raw.lesson_72,R.raw.lesson_73,R.raw.lesson_74,R.raw.lesson_75,R.raw.lesson_76,R.raw.lesson_77,R.raw.lesson_78,R.raw.lesson_79,R.raw.lesson_80,R.raw.lesson_81,R.raw.lesson_82,R.raw.lesson_83,R.raw.lesson_84,R.raw.lesson_85,R.raw.lesson_86,R.raw.lesson_87,R.raw.lesson_88,R.raw.lesson_89,R.raw.lesson_90,R.raw.lesson_91,R.raw.lesson_92,R.raw.lesson_93,R.raw.lesson_94,R.raw.lesson_95,R.raw.lesson_96,R.raw.lesson_97,R.raw.lesson_98,R.raw.lesson_99,R.raw.lesson_100};
	int r[] = { R.raw.lesson_1, R.raw.lesson_2, R.raw.lesson_3, R.raw.lesson_4};
	int key = 1;
	private final Handler handler = new Handler();

	// TextToSpeech
	private TextToSpeech mTts;
	private static final int MY_DATA_CHECK_CODE = 1234;
	String content; // Truyền nội dung nói

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listening_lesson_100topic);

		// Nhận dữ liệu Bundle
		getBundleData();

		// Khai báo các Control
		getControls();

		// Thiết lập sự kiện
		setEvents();

		// gọi hàm phát Audio
		initViews();

		// Tạo ra danh sách View từ ArrayList
		displayListView();

	}

	private void setEvents() {
		// TODO Auto-generated method stub
		tv_enlocal_title.setText(BUNDLE_ENG_TITLE);
		tv_namelocal_title.setText(BUNDLE_LOCAL_TITLE);
		// rl_title.setBackgroundColor(Color.rgb(255, 255, 2553));

		
		try {
			getItemFromXML2(this);
		} catch (XmlPullParserException e) {
		} catch (IOException e) {
		}
		// Chuyển sang bài kế tiếp
		
		// Lùi lại bài trước
		
	}

	private void getControls() {
		// TODO Auto-generated method stub
		tv_enlocal_title = (TextView) findViewById(R.id.tv_enlocal_title);
		tv_namelocal_title = (TextView) findViewById(R.id.tv_namelocal_title);
		rl_title = (RelativeLayout) findViewById(R.id.rl_title);
	}

	private void getBundleData() {
		// TODO Auto-generated method stub
		Bundle localBundle = getIntent().getExtras();
		BUNDLE_KEY = localBundle.getInt("data");
		BUNDLE_ENG_TITLE = localBundle.getString("eng_title");
		BUNDLE_LOCAL_TITLE = localBundle.getString("local_title");
		setTitle(BUNDLE_LOCAL_TITLE);
	}

	/*---------------------------------phát Audio-------------------------------*/
	// This method set the setOnClickListener and method for it (buttonClick())
	private void initViews() {
		// buttonPlayStop = (Button) findViewById(R.id.ButtonPlayStop);
		// buttonPlayStop.setOnClickListener(new OnClickListener() {@Override
		// public void onClick(View v) {buttonClick();}});
		key = BUNDLE_KEY - 1;
		mediaPlayer = MediaPlayer.create(this, r[key]);
		btnPlay = (CheckBox) findViewById(R.id.btn_play);
		btnPlay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton paramCompoundButton,
					boolean paramBoolean) {
				if (!paramBoolean) {
					if (mediaPlayer != null) {
						mediaPlayer.pause();
						startPlayProgressUpdater();
					}
				} else if (mediaPlayer != null) {
					mediaPlayer.start();
					startPlayProgressUpdater();
				}
			}
		});

		seekBar = (SeekBar) findViewById(R.id.SeekBar01);
		seekBar.setMax(mediaPlayer.getDuration());
		seekBar.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				seekChange(v);
				return false;
			}
		});

	}

	public void startPlayProgressUpdater() {
		seekBar.setProgress(mediaPlayer.getCurrentPosition());

		if (mediaPlayer.isPlaying()) {
			Runnable notification = new Runnable() {
				public void run() {
					startPlayProgressUpdater();
				}
			};
			handler.postDelayed(notification, 1000);
		} else {
			mediaPlayer.pause();
			// buttonPlayStop.setText(getString(R.string.play_str));
			seekBar.setProgress(0);
		}
	}

	// This is event handler thumb moving event
	private void seekChange(View v) {
		if (mediaPlayer.isPlaying()) {
			SeekBar sb = (SeekBar) v;
			mediaPlayer.seekTo(sb.getProgress());
		}
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
				R.layout.module_list_row_9_listening, phraseList);
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
				Adapter Adapter = (Adapter) parent.getItemAtPosition(position);

				// Truyền dữ liệu để đọc(TextToSpeech)
				content = Adapter.getEn_text().trim(); // Nội dung
				//Toast.makeText(Lesson100ListeningTopicActivity2.this, content,Toast.LENGTH_LONG).show(); // Hiển thị nội dung
				// Khởi động hàm TextToSpeech
				Intent checkIntent = new Intent();
				checkIntent
						.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
				startActivityForResult(checkIntent, MY_DATA_CHECK_CODE);
			}
		});

	}

	/*------------------------------MyCustomAdapter------------------------------*/
	private class MyCustomAdapter extends ArrayAdapter<Adapter> {
		private ArrayList<Adapter> originalList;
		private ArrayList<Adapter> phraseList;
		

		public MyCustomAdapter(Context context, int textViewResourceId,
				ArrayList<Adapter> countryList) {
			super(context, textViewResourceId, countryList);
			this.phraseList = new ArrayList<Adapter>();
			this.phraseList.addAll(countryList);
			this.originalList = new ArrayList<Adapter>();
			this.originalList.addAll(countryList);
		}

		/**
		 * Khai báo biến trong View
		 */
		private class ViewHolder {
			TextView tv_person;
			TextView en_text;
			TextView local_text;
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
				convertView = vi.inflate(R.layout.module_list_row_9_listening,
						null);

				holder = new ViewHolder();
				holder.tv_person = (TextView) convertView
						.findViewById(R.id.tv_person);
				holder.en_text = (TextView) convertView
						.findViewById(R.id.tv_enlocal);
				holder.local_text = (TextView) convertView
						.findViewById(R.id.tv_namelocal);
				convertView.setTag(holder);

			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			Adapter Adapter = phraseList.get(position);
			holder.tv_person.setText(Adapter.getId());
			holder.en_text.setText(Adapter.getEn_text());
			holder.local_text.setText(Adapter.getLocal_text());

			// Hiển thị màu cho từng dòng của Listview
			/*
			 * if (position % 2 == 0) {
			 * convertView.setBackgroundColor(Color.rgb(238, 233, 233)); } else
			 * { convertView.setBackgroundColor(Color.rgb(255, 255, 255)); }
			 */

			// Hiển thị màu cho từng dùng của Listview
			if (position % 2 == 0) {
				convertView.setBackgroundColor(Color.rgb(255, 255, 255));
			} else {
				convertView.setBackgroundColor(Color.rgb(238, 233, 233));
			}

			return convertView;

		}

	

	}

	/*------------------------------XmlPullParser------------------------------*/
	public void getItemFromXML(Activity activity)
			throws XmlPullParserException, IOException {
		Resources res = activity.getResources();
		XmlResourceParser xpp = res.getXml(R.xml.lang_vi);
		xpp.next();
		int eventType = xpp.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			if (eventType == XmlPullParser.START_TAG) {

				if (xpp.getName().equals("item" + BUNDLE_KEY)) {
					en_text = (xpp.getAttributeValue(null, "en_text")
							.toString());
					local_text = (xpp.getAttributeValue(null, "local_text")
							.toString());
					person_text = (xpp.getAttributeValue(null, "person")
							.toString());

					Adapter Adapter = new Adapter(person_text, en_text,
							local_text);
					phraseList.add(Adapter);
				}
				i++;

			}
			eventType = xpp.next();
		}

	}

	// Truy vấn lấy tiêu đề của chủ đề
	/*------------------------------XmlPullParser------------------------------*/
	public void getItemFromXML2(Activity activity)
			throws XmlPullParserException, IOException {
		Resources res = activity.getResources();
		XmlResourceParser xpp = res.getXml(R.xml.lang_vi);
		xpp.next();
		int eventType = xpp.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			if (eventType == XmlPullParser.START_TAG) {

				if (xpp.getName().equals("lesson")
						&& xpp.getAttributeValue(null, "id").toString()
								.equals(BUNDLE_KEY+"")) {
					en_title_topic = (xpp.getAttributeValue(null, "en_title")
							.toString());
					local_title_topic = (xpp.getAttributeValue(null, "local_title")
							.toString());
				}

			}
			eventType = xpp.next();
		}

	}

	/*----------------------------------Text to speak----------------------------------*/
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

			int result = mTts.setLanguage(Locale.US); // Đặt ngôn ngữ nói

			// tts.setPitch(5); // Đặt pitch level

			// tts.setSpeechRate(2); //Thiết lập tỷ lệ tốc độ bài phát

			// Nếu thiết bị không hỗ trợ
			if (result == TextToSpeech.LANG_MISSING_DATA
					|| result == TextToSpeech.LANG_NOT_SUPPORTED) {
				Log.e("TTS", "Language is not supported");
				// Nếu hỗ trợ
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