package com.hominhchung.vocabulary.image_topic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hominhchung.R;

@SuppressLint("ResourceAsColor")
public class LessonVocabularyImageTopic extends Activity implements
		TextToSpeech.OnInitListener {
	/**
	 * Khai báo giá trị
	 */
	ImageButton imageLeft, imageRight, imageSound;
	ImageView imageContent;
	TextView txtContent,txtLocal,txtRead;
	
	// Bundle data
	static int KEY_BUNDLE = 0; 
	static String TITLE_BUNDLE = ""; 
	
	// Hình 
	int[] image_clothes = { R.drawable.clothes_coat, R.drawable.clothes_dress, R.drawable.clothes_gloves,
			R.drawable.clothes_jacket, R.drawable.clothes_jeans, R.drawable.clothes_jumper,
			R.drawable.clothes_pajamas, R.drawable.clothes_shirt, R.drawable.clothes_shoes,
			R.drawable.clothes_skirt, R.drawable.clothes_slacks, R.drawable.clothes_uniform };
	private final Handler handler = new Handler();

	/**
	 * Node và giá trị truy vấn của file XML
	 */
	
	static final String KEY_ID = "id";
	static final String KEY_LOCAL_TITLE = "local_title";
	static final String KEY_EN_TITLE = "en_title";
	static final String KEY_PERSON_NAME = "person";

	/**
	 * Mảng lưu dữ liệu từ file XML
	 */
	ArrayList<String> en_title; // Từ vựng tiếng anh
	ArrayList<String> local_title; // Nghĩa của từ vựng
	ArrayList<String> local_read; // Phát âm
	
	/** 
	 * TextToSpeech
	 */
	private TextToSpeech tts;
	private static final int MY_DATA_CHECK_CODE = 1234;
	String content; // Truyền nội dung nói

	/**
	 * khai báo giá trị SpeechToText 
	 */
	protected static final int RESULT_SPEECH = 1;

	int pos = 0, max = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vocabulary_lesson_pictures);
		
		// Lấy dữ liệu Bundle
		getDataBundle();
		
		//AndroidSDKProvider.initSDK(this); 
		tts = new TextToSpeech(this, this);
		
		// Khai báo control
		getControl();
		// Sự kiện
		setTitle(TITLE_BUNDLE);
		
		imageLeft.setVisibility(View.INVISIBLE);
		
		setEvent();

		en_title = new ArrayList<String>();
		local_title = new ArrayList<String>();
		local_read = new ArrayList<String>();
		try {
			getItemFromXML(this);
		} catch (XmlPullParserException e) {
		} catch (IOException e) {
		}
		txtContent.setText(en_title.get(0));
		txtLocal.setText(local_title.get(0));
		txtRead.setText(local_read.get(0));
		imageContent.setImageResource(image_clothes[pos]);

	}

	/*-----------------------------------Event------------------------------------*/
	private void setEvent() {
		// TODO Auto-generated method stub
		imageLeft.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				pos--;
				if (pos > 0) {
					imageLeft.setVisibility(View.VISIBLE);
					imageRight.setVisibility(View.VISIBLE);
					// Text to speak
					setSound(pos);

				} else {
					imageLeft.setVisibility(View.INVISIBLE);
					// Text to speak
					setSound(pos);
				}
			}

		});
		imageRight.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				pos++;
				if (pos != max) {
					imageLeft.setVisibility(View.VISIBLE);
					imageRight.setVisibility(View.VISIBLE);
					// Text to speak
					setSound(pos);
				} else {
					imageRight.setVisibility(View.INVISIBLE);

				}

			}
		});

		imageSound.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (pos < max) {
					content = en_title.get(pos).toString().trim();
					/*Toast.makeText(LessonVocabularyImageTopic.this, content,
							Toast.LENGTH_LONG).show(); // Hiển thị nội dung
*/					speakOut(content);
				}
			}
		});

	}

	private void setSound(int pos) {
		// TODO Auto-generated method stub
		txtContent.setText(en_title.get(pos));
		txtLocal.setText(local_title.get(pos));
		txtRead.setText(local_read.get(pos));
		content = en_title.get(pos).toString().trim();
		imageContent.setImageResource(image_clothes[pos]);
		/*Toast.makeText(LessonVocabularyImageTopic.this, content,
				Toast.LENGTH_LONG).show();*/
		// Khởi động hàm TextToSpeech
		speakOut(content);
	}

	private void getControl() {
		// TODO Auto-generated method stub
		imageLeft = (ImageButton) findViewById(R.id.imageLeft);
		imageRight = (ImageButton) findViewById(R.id.imageRight);
		imageSound = (ImageButton) findViewById(R.id.imageSound);
		imageContent = (ImageView) findViewById(R.id.imageContent);
		// Nội dung your speak text của bạn
		txtContent = (TextView) findViewById(R.id.txtContent);
		txtLocal = (TextView) findViewById(R.id.txtLocal);
		txtRead = (TextView) findViewById(R.id.txtRead);
	}

	/*----------------------------------------Event--------------------------------------*/
	private void getDataBundle() {
		// TODO Auto-generated method stub
		Bundle localBundle = getIntent().getExtras();

		KEY_BUNDLE = localBundle.getInt("key_bundle");
		TITLE_BUNDLE = localBundle.getString("title_bundle");
		if((KEY_BUNDLE+1)!=1){
			KEY_BUNDLE=1;
			Toast.makeText(getApplicationContext(), "Dữ liệu đang cập nhật...\nBạn vui lòng xem Topic 1.", 1).show();
		}
		//Toast.makeText(getApplicationContext(), KEY_BUNDLE+"", 1).show(); //Kiểm tra lại giá trị
	}

	/*---------------------------------------XML--------------------------------------*/
	public void getItemFromXML(Activity activity)
			throws XmlPullParserException, IOException {
		StringBuffer stringBuffer1 = new StringBuffer();
		StringBuffer stringBuffer2 = new StringBuffer();

		Resources res = activity.getResources();
		XmlResourceParser xpp = res.getXml(R.xml.vocabulary_picture);
		xpp.next();
		int eventType = xpp.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			if (eventType == XmlPullParser.START_TAG) {
				if (xpp.getName().equals("item"+(KEY_BUNDLE+1))) {
					// Lấy giá trị đưa vào ArrayList từ vựng
					en_title.add(xpp.getAttributeValue(null, "en_title").toString());
					// Lấy giá trị đưa vào ArrayList nghĩa
					local_title.add(xpp.getAttributeValue(null, "local_title").toString());
					// Lấy giá trị đưa vào ArrayList phát âm
					local_read.add(xpp.getAttributeValue(null, "local_read").toString());
					max++;
				}

			}
			eventType = xpp.next();
		}

	}

	/*--------------------------------------Menu--------------------------------------*/

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.listening_topic_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Take appropriate action for each action item click
		switch (item.getItemId()) {
		case R.id.action_search:
			// search action
			return true;
		case R.id.action_play:

			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}*/

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
				 speakOut(en_title.get(pos).toString().trim());
			}

		} else {
			Intent marketIntent = new Intent(Intent.ACTION_VIEW);
			marketIntent.setData(Uri.parse(getString(R.string.url_download_tts)));
			startActivity(marketIntent);
		}

	}

	private void speakOut(String text) {
		//Toast.makeText(getApplicationContext(), text, 2).show();
		tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
	}
	

}
