package com.hominhchung.speaking.topic100;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
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
import android.widget.TextView;
import android.widget.Toast;

import com.hominhchung.R;

@SuppressLint("ResourceAsColor")
public class CopyOfLessonSpeakTopic extends Activity implements
		TextToSpeech.OnInitListener {
	// Control
	ImageButton imageLeft, imageRight, imageSpeak, imageSound;
	TextView txtYourSpeak, txtContent, txtTitle, txtLocalTitle, txtNum;// txtLocal,

	private final Handler handler = new Handler();

	// Bundle data
	static int KEY_BUNDLE = 0;
	static String EN_TITLE_BUNDLE = "";
	static String LOCAL_TITLE_BUNDLE = "";

	static final String KEY_ID = "id";
	static final String KEY_LOCAL_TITLE = "local_title";
	static final String KEY_EN_TITLE = "en_title";
	static final String KEY_PERSON_NAME = "person";

	ArrayList<String> en_title;// local_title

	// TextToSpeech
	private TextToSpeech tts;
	private static final int MY_DATA_CHECK_CODE = 1234;
	String content; // Truyền nội dung nói

	// SpeechToText
	protected static final int RESULT_SPEECH = 1;

	int pos = 0, key, max = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.speaking_lesson_100topic);

		// Lấy dữ liệu Bundle
		getDataBundle();

		// Khai báo TextToSpeech
		tts = new TextToSpeech(getApplicationContext(), this);

		// Khai báo control
		getControl();
		// Sự kiện

		imageLeft.setVisibility(View.INVISIBLE);
		setEvent();

		en_title = new ArrayList<String>();
		// local_title = new ArrayList<String>();
		try {
			getItemFromXML(this);
		} catch (XmlPullParserException e) {
		} catch (IOException e) {
		}

		if (KEY_BUNDLE < 10) {
			txtNum.setText("0" + KEY_BUNDLE);
		} else {
			txtNum.setText(KEY_BUNDLE + " l");
		}

		txtContent.setText(en_title.get(0));
		// txtLocal.setText(local_title.get(0));
		txtTitle.setText(EN_TITLE_BUNDLE);
		txtLocalTitle.setText(LOCAL_TITLE_BUNDLE);
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
		imageSpeak.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Speed to text
				// Gọi hàm Recognizer
				Intent intent = new Intent(
						RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
				// xac nhan ung dung muon gui yeu cau
				intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,
						getClass().getPackage().getName());
				// goi y nhan dang nhung gi nguoi dung se noi
				intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
						RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
				// goi y nhung dieu nguoi dung muon noi
				intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
						"Tell me what you want");
				try {
					startActivityForResult(intent, RESULT_SPEECH);
				} catch (ActivityNotFoundException e) {
					// Máy không hỗ trơ
					AlertDialog.Builder builder = new AlertDialog.Builder(
							CopyOfLessonSpeakTopic.this);
					builder.setTitle("Not Available");
					builder.setMessage("No recognition software installed. Download?");
					builder.setPositiveButton("Yes",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									Intent marketIntent = new Intent(
											Intent.ACTION_VIEW);
									marketIntent.setData(Uri
											.parse("https://play.google.com/store/apps/details?id=com.google.android.voicesearch"));
									startActivity(marketIntent);
								}
							});
					builder.setNegativeButton("No", null);
					builder.create().show();
				}

			}

		});

		imageSound.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (pos < max) {
					content = en_title.get(pos).toString().trim();
					Toast.makeText(CopyOfLessonSpeakTopic.this, content,
							Toast.LENGTH_LONG).show(); // Hiển thị nội dung
					speakOut(content);
				}
			}
		});

	}

	private void setSound(int pos) {
		// TODO Auto-generated method stub
		txtContent.setText(en_title.get(pos));
		// txtLocal.setText(local_title.get(pos));
		content = en_title.get(pos).toString().trim();
		Toast.makeText(CopyOfLessonSpeakTopic.this, content, Toast.LENGTH_LONG)
				.show(); 

		speakOut(content);
	}

	private void getControl() {
		// TODO Auto-generated method stub
		imageLeft = (ImageButton) findViewById(R.id.imageLeft);
		imageRight = (ImageButton) findViewById(R.id.imageRight);
		imageSpeak = (ImageButton) findViewById(R.id.imageSpeak);
		imageSound = (ImageButton) findViewById(R.id.imageSound);

		// Nội dung your speak text của bạn
		txtYourSpeak = (TextView) findViewById(R.id.txtYourSpeak);

		txtNum = (TextView) findViewById(R.id.txtNum);
		txtTitle = (TextView) findViewById(R.id.txtTitle); // Tieu de chu de
		txtLocalTitle = (TextView) findViewById(R.id.txtLocalTitle);

		txtContent = (TextView) findViewById(R.id.txtContent);// Một câu T.Anh
		// txtLocal = (TextView) findViewById(R.id.txtLocal); // Nghĩa của câu
	}

	/*----------------------------------------Event--------------------------------------*/
	private void getDataBundle() {
		// TODO Auto-generated method stub
		Bundle localBundle = getIntent().getExtras();
		KEY_BUNDLE = localBundle.getInt("num");
		EN_TITLE_BUNDLE = localBundle.getString("en_title").trim();
		LOCAL_TITLE_BUNDLE = localBundle.getString("local_title").trim();
	}

	/*---------------------------------------XML--------------------------------------*/
	public void getItemFromXML(Activity activity)
			throws XmlPullParserException, IOException {
		StringBuffer stringBuffer1 = new StringBuffer();
		StringBuffer stringBuffer2 = new StringBuffer();

		Resources res = activity.getResources();
		XmlResourceParser xpp = res.getXml(R.xml.lang_vi);
		xpp.next();
		int eventType = xpp.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			if (eventType == XmlPullParser.START_TAG) {
				if (xpp.getName().equals("item1")) {

					en_title.add(xpp.getAttributeValue(null, "en_text")
							.toString());
					/*
					 * local_title.add(xpp.getAttributeValue(null, "local_text")
					 * .toString());
					 */
					max++;
				}

			}
			eventType = xpp.next();
		}

	}

	/*--------------------------------------Menu--------------------------------------*/

	/*
	 * @Override public boolean onCreateOptionsMenu(Menu menu) {
	 * getMenuInflater().inflate(R.menu.listening_topic_menu, menu); return
	 * true; }
	 * 
	 * @Override public boolean onOptionsItemSelected(MenuItem item) { // Take
	 * appropriate action for each action item click switch (item.getItemId()) {
	 * case R.id.action_search: // search action return true; case
	 * R.id.action_play:
	 * 
	 * return true; default: return super.onOptionsItemSelected(item); } }
	 */

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
				//speakOut(en_title.get(0).toString().trim());
				speakOut(en_title.get(pos).toString().trim());
			}

		} else {
			Toast.makeText(this, "Sorry! Text To Speech failed...", 1).show();
			Log.e("TTS", "Initilization Failed");

			/*
			 * Intent marketIntent = new Intent(Intent.ACTION_VIEW);
			 * marketIntent .setData(Uri .parse(
			 * "https://play.google.com/store/apps/details?id=com.google.android.tts"
			 * )); startActivity(marketIntent);
			 */
		}

	}

	private void speakOut(String text) {
		// Toast.makeText(getApplicationContext(), text, 2).show();
		tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
	}

}
