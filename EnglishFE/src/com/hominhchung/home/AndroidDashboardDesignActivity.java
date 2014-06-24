package com.hominhchung.home;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hominhchung.R;
import com.hominhchung.experience.ListExperienceMainActivity;
import com.hominhchung.grammar.ListGrammarMainActivity;
import com.hominhchung.kid.KidMainActivity;
import com.hominhchung.listening.ListListeningMainActivity;
import com.hominhchung.note.NoteList;
import com.hominhchung.online.login.RegistryMainActivity;
import com.hominhchung.pronunciation.ListPronunciationMainActivity;
import com.hominhchung.reading.ListReadingMainActivity;
import com.hominhchung.speaking.ListSpeakingMainActivity;
import com.hominhchung.testing.ListTestingMainActivity;
import com.hominhchung.vocabulary.ListVocabularyMainActivity;
import com.hominhchung.writing.ListWritingMainActivity;

public class AndroidDashboardDesignActivity extends Activity {

	Button btn_vocabulary, btn_listening, btn_speaking, btn_reading,
			btn_writing, btn_grammar, btn_pronunciation, btn_experiences,
			btn_note, btn_testing, btn_kid, btn_registration;
	/**
	 * Khai báo đường dẫn
	 */
	String EFE_PATH = "/sdcard/EnglishFe/Music";
	File efeDirectory;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_fragment);
		// Tạo folder
		addFolder();

		// Gọi hàm Sound Effects
		Effects.getInstance().init(this);

		// Khai báo các Control
		getControl();

		// Thiết lập cho các Control
		setEvent();
	}

	private void getControl() {
		// TODO Auto-generated method stub
		// 1 Dashboard Vocabulary button
		btn_vocabulary = (Button) findViewById(R.id.btn_vocabulary);
		// 2 Dashboard Listening button
		btn_listening = (Button) findViewById(R.id.btn_listening);
		// 3 Dashboard Speaking button
		btn_speaking = (Button) findViewById(R.id.btn_speaking);
		// 4 Dashboard Reading button
		btn_reading = (Button) findViewById(R.id.btn_reading);
		// 5 Dashboard Writing button
		btn_writing = (Button) findViewById(R.id.btn_writing);
		// 6 Dashboard Grammar button
		btn_grammar = (Button) findViewById(R.id.btn_grammar);
		// 7 Dashboard Events button
		btn_pronunciation = (Button) findViewById(R.id.btn_pronunciation);
		// 8 Dashboard Events button
		btn_experiences = (Button) findViewById(R.id.btn_experiences);
		// 9 Dashboard Note button
		btn_note = (Button) findViewById(R.id.btn_note);
		// 10 Dashboard Testing button
		btn_testing = (Button) findViewById(R.id.btn_testing);
		// 11 Dashboard Testing button
		btn_kid = (Button) findViewById(R.id.btn_kid);
		// 12 Dashboard Registration button
		btn_registration = (Button) findViewById(R.id.btn_registration);
	}

	private void setEvent() {
		// TODO Auto-generated method stub
		// 1 Listening to Vocabulary button click
		btn_vocabulary.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				// Launching News Feed Screen
				Effects.getInstance().playSound(Effects.SOUND_1);
				Intent i = new Intent(getApplicationContext(),
						ListVocabularyMainActivity.class);
				startActivity(i);
			}
		});
		// 2 Listening to Listening button click
		btn_listening.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				// Launching News Feed Screen
				Effects.getInstance().playSound(Effects.SOUND_1);
				Intent i = new Intent(getApplicationContext(),
						ListListeningMainActivity.class);
				startActivity(i);
			}
		});
		// 3 Listening to Speaking button click
		btn_speaking.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				// Launching News Feed Screen
				Effects.getInstance().playSound(Effects.SOUND_1);
				Intent i = new Intent(getApplicationContext(),
						ListSpeakingMainActivity.class);
				startActivity(i);
			}
		});
		// 4 Listening to Reading button click
		btn_reading.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				// Launching News Feed Screen
				Effects.getInstance().playSound(Effects.SOUND_1);
				Intent i = new Intent(getApplicationContext(),
						ListReadingMainActivity.class);
				startActivity(i);
			}
		});
		// 5 Listening to Writing button click
		btn_writing.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				// Launching News Feed Screen
				Effects.getInstance().playSound(Effects.SOUND_1);
				Intent i = new Intent(getApplicationContext(),
						ListWritingMainActivity.class);
				startActivity(i);
			}
		});
		// 6 Listening to Grammar button click
		btn_grammar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				// Launching News Feed Screen
				Effects.getInstance().playSound(Effects.SOUND_1);
				Intent i = new Intent(getApplicationContext(),
						ListGrammarMainActivity.class);
				startActivity(i);
			}
		});
		// 7 Listening to Experiences button click
		btn_pronunciation.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				// Launching News Feed Screen
				Effects.getInstance().playSound(Effects.SOUND_1);
				Intent i = new Intent(getApplicationContext(),
						ListPronunciationMainActivity.class);
				startActivity(i);
			}
		});
		// 8 Listening to Experiences button click
		btn_experiences.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				// Launching News Feed Screen
				Effects.getInstance().playSound(Effects.SOUND_1);
				Intent i = new Intent(getApplicationContext(),
						ListExperienceMainActivity.class);
				startActivity(i);
			}
		});
		// 9 Listening to Note button click
		btn_note.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				// Launching News Feed Screen
				Effects.getInstance().playSound(Effects.SOUND_1);
				Intent i = new Intent(getApplicationContext(), NoteList.class);
				startActivity(i);
			}
		});
		// 10 Listening to Testing button click
		btn_testing.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				// Launching News Feed Screen
				Effects.getInstance().playSound(Effects.SOUND_1);
				Intent i = new Intent(getApplicationContext(),
						ListTestingMainActivity.class);
				startActivity(i);
			}
		});
		// 11 Listening to Testing button click
		btn_kid.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				// Launching News Feed Screen
				Effects.getInstance().playSound(Effects.SOUND_1);
				Intent i = new Intent(getApplicationContext(),
						KidMainActivity.class);
				startActivity(i);
			}
		});
		// 12 Listening to Registry button click
		btn_registration.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				// Launching News Feed Screen
				// Effects.getInstance().playSound(Effects.SOUND_1);
				/*
				 * Intent i = new Intent(getApplicationContext(),
				 * OnlineMainActivity.class);
				 */
				Intent i = new Intent(getApplicationContext(),
						RegistryMainActivity.class);
				startActivity(i);
			}
		});
	}

	/*-----------------------------------CREATE FOLDER----------------------------------*/
	private void addFolder() {
		/*
		 * // TODO Auto-generated method stub File directory1 = new
		 * File(Environment.getExternalStorageDirectory() + File.separator +
		 * "EnglishFE" // folder name );
		 * 
		 * if (!directory1.exists()) { directory1.mkdir(); // Tạo }
		 */

		efeDirectory = new File(EFE_PATH); // /sdcard/EnglishFe/Music;
		// Kiểm tra nếu không tồn tại
		if (!efeDirectory.exists()) {
			// have the object build the directory structure, if needed.
			efeDirectory.mkdirs();
		}

	}
}