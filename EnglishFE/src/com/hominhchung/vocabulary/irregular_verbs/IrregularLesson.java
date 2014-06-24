package com.hominhchung.vocabulary.irregular_verbs;

import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.hominhchung.R;

public class IrregularLesson extends Activity implements
		TextToSpeech.OnInitListener {

	/**
	 * Khai báo
	 */
	String words_bundle, simple_past_bundle, participle_past_bundle,
			vi_content_bundle, example_bundle;
	TextView tv_words, tv_infinitive, tv_past, tv_past_participle,
			tv_vi_content, tv_example;
	ImageView ima_volum1, ima_volum2, ima_volum3;
	ImageButton imageSound;

	private TextToSpeech tts;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vocabulary_lesson_irregular);

		// Nhận dữ liệu Bundle
		getBundle();

		// Khai báo Control
		getControl();

		// Thiết lập sự kiện
		setEvents();
	}

	/**
	 * Sự kiện cho các Control
	 */
	OnClickListener myclick = new OnClickListener() {

		@Override
		public void onClick(View arg0) {

			switch (arg0.getId())
			{
			case R.id.ima_volum1:
				speakOut(words_bundle);
				break;
			case R.id.ima_volum2:
				speakOut(simple_past_bundle);
				break;
			case R.id.ima_volum3:
				speakOut(participle_past_bundle);
				break;
			case R.id.imageSound:
				speakOut(example_bundle);
				break;
			}

		}

	};



	private void getControl() {
		// TODO Auto-generated method stub
		// Khai báo các textview
		tv_words = (TextView) findViewById(R.id.tv_words);
		tv_infinitive = (TextView) findViewById(R.id.tv_infinitive);
		tv_past = (TextView) findViewById(R.id.tv_past);
		tv_past_participle = (TextView) findViewById(R.id.tv_past_participle);
		tv_vi_content = (TextView) findViewById(R.id.tv_vi_content);
		tv_example = (TextView) findViewById(R.id.tv_example);

		// Khai báo các ima_volum ImageView
		ima_volum1 = (ImageView) findViewById(R.id.ima_volum1);
		ima_volum2 = (ImageView) findViewById(R.id.ima_volum2);
		ima_volum3 = (ImageView) findViewById(R.id.ima_volum3);

		// Khai báo các ImageButton
		imageSound = (ImageButton) findViewById(R.id.imageSound);

		// Khai báo TextToSpeech
		tts = new TextToSpeech(this, this);
	}

	private void getBundle() {
		// TODO Auto-generated method stub
		
		// Khởi tạo bundle
		Bundle localBundle = getIntent().getExtras();

		// Nhận dữ liệu từ các các dữ liệu riêng lẽ
		words_bundle = localBundle.getString("words_bundle");
		simple_past_bundle = localBundle.getString("simple_past_bundle");
		participle_past_bundle = localBundle.getString("participle_past_bundle");
		vi_content_bundle = localBundle.getString("vi_content_bundle");
		example_bundle = localBundle.getString("example_bundle");
	}
	private void setEvents() {
		// TODO Auto-generated method stub

		// Đặt giá trị bundle vào các Control
		tv_words.setText("("+words_bundle+")");
		tv_infinitive.setText(words_bundle);
		tv_past.setText(simple_past_bundle);
		tv_past_participle.setText(participle_past_bundle);
		tv_vi_content.setText(vi_content_bundle);
		tv_example.setText(example_bundle);

		// Thêm sự kiện cho các Control
		ima_volum1.setOnClickListener(myclick);
		ima_volum2.setOnClickListener(myclick);
		ima_volum3.setOnClickListener(myclick);
		imageSound.setOnClickListener(myclick);
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
				//speakOut(String text);
			}

		} else {
			/*
			 * Toast.makeText(this, "Sorry! Text To Speech failed...",
			 * 1).show(); Log.e("TTS", "Initilization Failed");
			 */
			Intent marketIntent = new Intent(Intent.ACTION_VIEW);
			marketIntent
					.setData(Uri
							.parse(getString(R.string.url_download_tts)));
			startActivity(marketIntent);
		}

	}

	private void speakOut(String text) {

		tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
	}
}
