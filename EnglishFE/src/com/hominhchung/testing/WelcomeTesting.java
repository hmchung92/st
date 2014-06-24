package com.hominhchung.testing;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.hominhchung.R;
import com.hominhchung.testing.level.TestingLevelMainActivity;

public class WelcomeTesting extends Activity {
	/** Called when the activity is first created. */
	TextView tv_label_result,tv_time;
	ImageButton quiz_time_next_button;

	//Animation
	Animation slide_in_left;
	int soMiliGiay = 10000;
	
	ImageView image_teacher;
	String table_bundle,title_bundle;
	int num;
	CountDownTimer demThoiGian;
	MediaPlayer mp;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz_time);

		// Nhận dữ liệu bundle
		getBundle();

		// Khai báo control
		getControl();

		// Thêm sự kiện
		setEvents();
		
		// Hien thi thoi gian
		thoigian();
	}

	private void getBundle() {
		// TODO Auto-generated method stub
		Bundle bundle = getIntent().getExtras();
		table_bundle = bundle.getString("table_bundle");
		title_bundle = bundle.getString("title_bundle");
	}

	private void setEvents() {
		// TODO Auto-generated method stub
		tv_label_result.setText(title_bundle);
		quiz_time_next_button.setOnClickListener(lessonList);
		image_teacher.setAnimation(slide_in_left);
		
		setTitle(title_bundle);
	}

	/**
	 * Hiển thị thời gian cho mỗi câu hỏi
	 * 
	 */
	public void thoigian() {
		demThoiGian = new CountDownTimer(soMiliGiay, 1000) {
			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
				tv_time.setText("0" + millisUntilFinished / 1000);
				amthanh();
			}

			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				demThoiGian.cancel();
				mp.stop();
				chuyenActivity();
			}
			
		};
		demThoiGian.start();
	}
	
	public void amthanh(){
		mp = MediaPlayer.create(this, R.raw.sound_1);
		mp.start();
	}
	
	
	public void chuyenActivity() {
		// TODO Auto-generated method stub
		Intent ex1= new Intent(WelcomeTesting.this,TestingLevelMainActivity.class);
		ex1.putExtra("table_bundle", table_bundle);
		ex1.putExtra("title_bundle", title_bundle);
		startActivity(ex1);
	}
	// Listening to buttonQuit button click
	Button.OnClickListener lessonList = new Button.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			chuyenActivity();
		}
	};
	
	

	private void getControl() {
		// TODO Auto-generated method stub
		tv_label_result = (TextView) findViewById(R.id.label_result);
		tv_time = (TextView) findViewById(R.id.tv_time);
		quiz_time_next_button = (ImageButton) findViewById(R.id.quiz_time_next_button);
		
		// ImageView
		image_teacher  = (ImageView) findViewById(R.id.image_teacher);
		
		//Animation
		slide_in_left = new AnimationUtils().loadAnimation(getApplicationContext(), R.anim.slide_in_left);
	}


}
