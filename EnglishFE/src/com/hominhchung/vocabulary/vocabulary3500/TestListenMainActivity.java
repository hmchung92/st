package com.hominhchung.vocabulary.vocabulary3500;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hominhchung.R;
import com.hominhchung.databasehelper.DataBaseHelper;

public class TestListenMainActivity extends Activity implements
		TextToSpeech.OnInitListener {

	private TextToSpeech tts;
	Button btn_nghe, btn_xacnhan, btn_next, btn_noi, btn_kq;
	TextView tv,tv_cauhientai;
	EditText et;

	int socau = 10; // Số câu hỏi sẽ lấy ra
	int index = 0; // Số câu hỏi hiện tại

	// ProgressDialog
	private ProgressDialog mProgressDialog;

	// int[] ds_hinh={R.drawable.true1};
	
	int pos = 0;
	int caudung = 0;
	ArrayList list_question;
	protected SQLiteDatabase db;
	// Danh sách từ vựng
	DataBaseHelper dbManager;
	protected static final int RESULT_SPEECH = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vocabulary_lesson_3500words_testing_2);

		// Khai báo TextToSpeech
		tts = new TextToSpeech(getApplicationContext(), this);
		
		showDB();
		
		// Khai bao cac Controls
		getControls();

		// Truy van va hien du lieu
		
		
		capnhatCauDung();

		// Thiet dat su kien
		setEvents();

	}

	private void setEvents() {
		// TODO Auto-generated method stub
		speakOut(list_question.get(pos).toString().trim());
		btn_nghe.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (pos < list_question.size()) {
					// textView.setTextColor(Color.RED);
					speakOut(list_question.get(pos).toString().trim());
				}
			}
		});

		btn_noi.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(
						RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

				intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");

				try {
					startActivityForResult(intent, RESULT_SPEECH);
					et.setText("");
				} catch (ActivityNotFoundException a) {
					Toast t = Toast.makeText(getApplicationContext(),
							"Ops! Your device doesn't support Speech to Text",
							Toast.LENGTH_SHORT);
					t.show();
				}
			}
		});

		btn_xacnhan.setOnClickListener(new View.OnClickListener() {

			@SuppressLint("ResourceAsColor")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (pos < list_question.size()) {
					if (et.getText().toString().trim().equalsIgnoreCase(list_question.get(pos).toString().trim())) {
						caudung++;
						capnhatCauDung();
						Toast.makeText(getApplicationContext(), "Đúng!",
								Toast.LENGTH_SHORT).show();
						btn_next.setText("Tiếp theo");
						btn_xacnhan.setVisibility(View.INVISIBLE);
						et.setEnabled(false);
						// Chinh sua tu vung da hoc
						//dbManager.sua_1_tuvung(list_question.get(pos).toString().trim(), "1");
						//dbManager.sua_1_tuvung_kt("abroad", "0");
						
						
					} else {
						Toast.makeText(getApplicationContext(), "Sai!",
								Toast.LENGTH_SHORT).show();
					}
				}
				//dbManager.close();
			}
		});

		btn_next.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				pos++;
				btn_xacnhan.setVisibility(View.VISIBLE);
				btn_kq.setVisibility(View.VISIBLE);
				et.setEnabled(true); 
				tv_cauhientai.setText("Câu: " +(pos+1)+"/"+list_question.size());
				if (pos < list_question.size()) {
					speakOut(list_question.get(pos).toString().trim());
					
					et.setText("");
					//kq.setText("Hãy nghe rõ");
					btn_next.setText("Bỏ qua");
					 capnhatCauDung();
				} else {
					btn_xacnhan.setEnabled(false);
					btn_kq.setEnabled(false);
					btn_next.setVisibility(View.INVISIBLE);
					btn_xacnhan.setVisibility(View.INVISIBLE);
					btn_next.setVisibility(View.INVISIBLE);
					btn_next.setEnabled(false);
					Toast.makeText(getApplicationContext(), "Hãy học thêm từ vựng!!!", 2).show();
					tv_cauhientai.setText("Hết");
				}
				btn_xacnhan.setEnabled(true);
				btn_kq.setEnabled(true);
			}
		});

		btn_kq.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), list_question.get(pos).toString().trim(), 2).show();
				//kq.setText("Kết quả: " + list_question.get(pos).toString().trim());
				//btn_kq.setVisibility(View.INVISIBLE);
			}
		});

	}

	private void getControls() {
		// TODO Auto-generated method stub
		btn_nghe = (Button) findViewById(R.id.btn_nghe);
		btn_noi = (Button) findViewById(R.id.btn_noi);
		btn_xacnhan = (Button) findViewById(R.id.btn_xacnhan);
		btn_kq = (Button) findViewById(R.id.btn_kq);
		btn_next = (Button) findViewById(R.id.btn_next);
		et = (EditText) findViewById(R.id.editText1);
		tv = (TextView) findViewById(R.id.textView1);
		tv_cauhientai = (TextView) findViewById(R.id.textView2);
		//kq = (TextView) findViewById(R.id.kq);
	}

	public void capnhatCauDung() {
		
		tv.setText("Đúng " + caudung + "/" + (pos+1));
		
	}

	public void showDB() {
		DataBaseHelper dbManager = new DataBaseHelper(this);
		String sql = "select * from englishcommon where isread=1";
		list_question = new ArrayList<String>();
		// Chep File MYPTITDEMO.sqlite vao
		// "/data/data/com.example.democonnectdbfromasset/databases/";
		try {
			dbManager.createDataBase();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(sql.equals("")){
			Toast.makeText(getApplicationContext(), getString(R.string.learn_more), 2).show();
		}
		SQLiteDatabase db = dbManager.getReadableDatabase();
		//Cursor cursor = db.rawQuery(sql, null);
		Cursor cursor = db.query("englishcommon", null, "isread=1", null, null, null,
				"random()" );
		cursor.moveToFirst();
		String str = "";
		while (!cursor.isAfterLast()) {
			String name = cursor.getString(cursor.getColumnIndex("name"));

			list_question.add(name);
			// text_question = text_question.
			// str+=tmpId+" --- "+tmp+"\n";
			cursor.moveToNext();
		}
		if(list_question.size()<=0 ){
			list_question.add("english");
		}
		//showTxt.setText(str);
		cursor.moveToLast();
		// showTxt.setText(cursor.getString(cursor.getColumnIndex("QCONTENT")));
		cursor.close();
		db.close();
		dbManager.close();
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
				 speakOut(list_question.get(pos).toString().trim());
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
	
	
	/*-----------------------------------------------*/
	
	@Override
	protected void onResume() {
		super.onResume();
		TabVocabularyActivity.getInstance().setRequestedOrientation(
				ActivityInfo.SCREEN_ORIENTATION_SENSOR);
		//new GetDataTask().execute(new Void[1]);
		showDB();
		goilai();
	}

	private void goilai() {
		// TODO Auto-generated method stub
		getControls();
		pos=0;
		tv.setText("Đúng: 0/"+list_question.size());
		btn_next.setVisibility(View.VISIBLE);
		btn_xacnhan.setVisibility(View.VISIBLE);
		btn_xacnhan.setEnabled(true);
		btn_kq.setEnabled(true);
		et.setVisibility(View.VISIBLE);
		tv_cauhientai.setText("Câu: " +(pos+1)+"/"+list_question.size());
		speakOut(list_question.get(pos).toString().trim());
	}

	@Override
	protected void onPause() {
		super.onPause();
		TabVocabularyActivity.getInstance().setRequestedOrientation(
				ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		TabVocabularyActivity.getInstance().setRequestedOrientation(
				ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}
}
