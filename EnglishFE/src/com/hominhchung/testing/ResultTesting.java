package com.hominhchung.testing;

import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hominhchung.R;

public class ResultTesting extends Activity implements
TextToSpeech.OnInitListener {
	/** Called when the activity is first created. */
	TextView label_result;
	Button button_select_lesson;

	//Animation
	Animation slide_in_left;
	
	ImageView image_teacher;
	int result_bundle;
	String title_bundle;

	/** 
	 * TextToSpeech
	 */
	private TextToSpeech tts;
	private static final int MY_DATA_CHECK_CODE = 1234;
	String content; // Truyền nội dung nói
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result_fanfares);

		// Nhận dữ liệu bundle
		getBundle();

		// Khai báo control
		getControl();

		// Thêm sự kiện
		setEvents();
		
		// Am thanh
		mp(R.raw.quiz_time);
		
		//AndroidSDKProvider.initSDK(this); 
		tts = new TextToSpeech(this, this);
		speakOut(result_bundle+" correct answers out of 10 ");
	}

	private void getBundle() {
		// TODO Auto-generated method stub
		Bundle localBundle = getIntent().getExtras();
		result_bundle = localBundle.getInt("num_result_bundle");
		title_bundle = localBundle.getString("title_bundle");
	}

	private void setEvents() {
		// TODO Auto-generated method stub
		setTitle(title_bundle);
		label_result.setText(result_bundle+"/10");
		button_select_lesson.setOnClickListener(lessonList);
		image_teacher.setAnimation(slide_in_left);
	}

	private void mp(int mp3) {
		// TODO Auto-generated method stub

		// Xu ly am thanh
		MediaPlayer mp= MediaPlayer.create(this, mp3); 
        mp.start();
	}

	// Listening to buttonQuit button click
	Button.OnClickListener lessonList = new Button.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent testingList = new Intent(ResultTesting.this,ListTestingMainActivity.class);
			startActivity(testingList);
		}
	};
	
	

	private void getControl() {
		// TODO Auto-generated method stub
		label_result = (TextView) findViewById(R.id.label_result);
		button_select_lesson = (Button) findViewById(R.id.button_select_lesson);
		
		// ImageView
		image_teacher  = (ImageView) findViewById(R.id.image_teacher);
		
		//Animation
		slide_in_left = new AnimationUtils().loadAnimation(getApplicationContext(), R.anim.slide_in_left);
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
				 //speakOut(en_title.get(pos).toString().trim());
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
		//Toast.makeText(getApplicationContext(), text, 2).show();
		tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
	}
}
