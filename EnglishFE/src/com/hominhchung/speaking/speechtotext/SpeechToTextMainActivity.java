package com.hominhchung.speaking.speechtotext;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;
import com.hominhchung.R;
import com.hominhchung.home.Effects;
import com.google.ads.*;

public class SpeechToTextMainActivity extends Activity {

	protected static final int RESULT_SPEECH = 1;
	private AdView adView;
	private ImageButton btnSpeak;
	private TextView txtText;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.speaking_speaktotext_layout);

		// Sound Effects
		Effects.getInstance().init(this);

		// Look up the AdView as a resource and load a request.
		AdView adView = (AdView) this.findViewById(R.id.adView);
		adView.loadAd(new AdRequest());

		txtText = (TextView) findViewById(R.id.txtText);

		btnSpeak = (ImageButton) findViewById(R.id.btnSpeak);

		btnSpeak.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				// Intent để khởi động việc bắt nội dung thông qua giọng nói.
				Intent intent = new Intent(
						RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
				Effects.getInstance().playSound(Effects.SOUND_1);
				intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
						RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
				// Intent này sẽ được đưa vào một Extra chính là nội dung khi
				// hiển thị Voice Recognition
				intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
						getString(R.string.tell_me_what_you_want));

				// Truyền thêm một RESULT_SPEECH, để giúp nhận
				// nội dung khi Intent này trả về cho Activity hiện tại. 
				// try ... catch để bắt trường hợp thiết bị không hỗ trợ chức năng này.
				try {
					// chạy Intent
					startActivityForResult(intent, RESULT_SPEECH);
					txtText.setText("");
				} catch (ActivityNotFoundException a) {
					Toast t = Toast.makeText(getApplicationContext(),
							getString(R.string.ops),
							Toast.LENGTH_SHORT);
					t.show();
				}
			}
		});

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
		case RESULT_SPEECH: {
			if (resultCode == RESULT_OK && null != data) {

				ArrayList<String> text = data
						.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

				txtText.setText(text.get(0));
			}
			break;
		}

		}
	}

	@Override
	public void onDestroy() {
		if (adView != null) {
			adView.destroy();
		}
		super.onDestroy();
	}

}
