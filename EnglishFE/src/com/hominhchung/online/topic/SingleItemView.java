package com.hominhchung.online.topic;

import java.util.ArrayList;
import java.util.Locale;

import com.hominhchung.R;
import com.hominhchung.R.id;
import com.hominhchung.R.layout;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SingleItemView extends Activity implements
		TextToSpeech.OnInitListener {
	// Declare Variables
	String rank;
	String country;
	String population;
	String flag;
	String position;
	ImageLoader imageLoader = new ImageLoader(this);
	ImageButton imageSound;
	private TextToSpeech tts;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from singleitemview.xml
		setContentView(R.layout.online_singleitemview);
		tts = new TextToSpeech(this, this);
		Intent i = getIntent();
		// Get the result of rank
		rank = i.getStringExtra("rank");
		// Get the result of country
		country = i.getStringExtra("country");
		// Get the result of population
		population = i.getStringExtra("population");
		// Get the result of flag
		flag = i.getStringExtra("flag");

		// Locate the TextViews in singleitemview.xml
		TextView txtContent = (TextView) findViewById(R.id.txtContent);
		/*
		 * TextView txtcountry = (TextView) findViewById(R.id.country); TextView
		 * txtpopulation = (TextView) findViewById(R.id.population);
		 */
		imageSound = (ImageButton) findViewById(R.id.imageSound);
		// Locate the ImageView in singleitemview.xml
		ImageView imgflag = (ImageView) findViewById(R.id.flag);

		// Set results to the TextViews
		txtContent.setText(country);
		/*
		 * txtcountry.setText(country); txtpopulation.setText(population);
		 */

		// Capture position and set results to the ImageView
		// Passes flag images URL into ImageLoader.class
		imageLoader.REQUIRED_SIZE = 200;
		imageLoader.DisplayImage(flag, imgflag);

		imageSound.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				docTuVung(country.trim());
			}
		});
	}

	private void docTuVung(String tuvung) {
		tts.speak(tuvung, TextToSpeech.QUEUE_FLUSH, null);
	}

	@Override
	public void onDestroy() {
		// Don't forget to shutdown tts!
		if (tts != null) {
			tts.stop();
			tts.shutdown();
		}
		super.onDestroy();
	}

	@Override
	public void onInit(int status) {

		if (status == TextToSpeech.SUCCESS) {

			int result = tts.setLanguage(Locale.US);

			if (result == TextToSpeech.LANG_MISSING_DATA
					|| result == TextToSpeech.LANG_NOT_SUPPORTED) {
				Log.e("TTS", "This Language is not supported");
			} else {
				// docTuVung("Welcome A B C bakery!");
			}

		} else {
			Log.e("TTS", "Initilization Failed!");
		}

	}
}