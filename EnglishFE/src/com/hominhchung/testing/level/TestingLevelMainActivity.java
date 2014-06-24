package com.hominhchung.testing.level;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.hominhchung.R;
import com.hominhchung.adapter.Cauhoi;
import com.hominhchung.databasehelper.quanlycauhoi;
import com.hominhchung.home.Effects;
import com.hominhchung.testing.ResultTesting;

public class TestingLevelMainActivity extends Activity {

	/**
	 * Khai báo dữ liệu
	 */
	int socau = 10;
	int index = 0; // câu hiện tại
	int caudung = 0;
	int soMiliGiay = 11000;

	/**
	 * Khai báo control
	 */
	TextView tvCauHoi, tvThongBao, tvThoiGian;
	RadioButton rd0, rd1, rd2, rd3;
	RadioGroup rdg;
	Button btCheck;
	ImageButton btnNext;

	CountDownTimer demThoiGian;
	List<Cauhoi> ds_cauhoi;
	Cauhoi cauhientai;
	quanlycauhoi db;
	private static String DB_TABLE = "";

	// Dữ liệu bundle
	String title_bundle;

	// Animation
	Animation anim;
	
	// Am thanh
	int amthanh[] = {R.raw.correct, R.raw.good_1,R.raw.ok};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.testing_layout);

		// Gọi hàm Sound Effects
		Effects.getInstance().init(this);

		// Animation
		anim = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.slide_in_left);
		
		// Lay du lieu ra khoi bunlde
		getBundle();

		// Khai báo các thành phần trong giao diện
		getControl();

		xethienthicauhoi();

		thoigian();

		// Thiết lập sự kiện cho các control
		setEvent();

	}

	private void getBundle() {
		// TODO Auto-generated method stub
		Bundle bundle = getIntent().getExtras();
		DB_TABLE = bundle.getString("table_bundle");
		title_bundle = bundle.getString("title_bundle");
	}

	private void xethienthicauhoi() {
		// TODO Auto-generated method stub

		// Tạo csdl
		createCSDL();
		ds_cauhoi = new ArrayList<Cauhoi>();
		ds_cauhoi = db.layNcaungaunghien(socau);
		hienthi(index);
	}

	/*--------------------------------EVENT-------------------------------*/
	private void createCSDL() {
		// TODO Auto-generated method stub
		db = new quanlycauhoi(this);
		db.TABLE_NAME = "level_a";
		try {
			db.createDatabase();
		} catch (IOException e) {
			e.printStackTrace();
			Toast.makeText(this, "Lỗi tạo cơ sỡ dữ liệu", Toast.LENGTH_SHORT)
					.show();
		}
	}

	private void setEvent() {
		// TODO Auto-generated method stub
		// -----------------------------------------------

		// Tiêu đề cho Actionbar
		setTitle(title_bundle);

		btnNext.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (rdg.getCheckedRadioButtonId() == -1) {
					Toast.makeText(getBaseContext(),
							getString(R.string.vui_long_chon_mot_cau_tra_loi), Toast.LENGTH_SHORT)
							.show();
				} else {
					Effects.getInstance().playSound(Effects.SOUND_1);
					demThoiGian.cancel();
					KiemTraCauDung();
					index++;
					if (index < socau) {
						hienthi(index);
						thoigian();
					} else {
						index = 0;
						btnNext.setVisibility(View.GONE);
						btCheck.setVisibility(View.VISIBLE);
						tvThoiGian.setVisibility(View.GONE);
					}
				}
			}
		});
		// -----------------------------------------------
		btCheck.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				demThoiGian.cancel();
				tvThoiGian.setVisibility(View.GONE);
				if (index >= socau) {
					/*
					 * tvThongBao.setText("Kết quả: ");
					 * tvCauHoi.setText("Bạn làm đúng " + caudung + " câu");
					 * rd0.setVisibility(View.GONE);
					 * rd2.setVisibility(View.GONE);
					 * rd3.setVisibility(View.GONE);
					 * rd1.setVisibility(View.GONE); index++;
					 * btCheck.setText("Exit");
					 */
					btCheck.setVisibility(View.INVISIBLE);
					Intent result = new Intent(TestingLevelMainActivity.this,
							ResultTesting.class);
					int num_result = caudung;
					result.putExtra("num_result_bundle", num_result);
					result.putExtra("title_bundle", title_bundle);
					startActivity(result);
				} else {
					hienthi(index);
					KiemTraLai();
					index++;
				}
				if (index == socau + 2) {
					System.exit(0);
				}
			}
		});
	}

	/*--------------------------------GET CONTROL-------------------------------*/
	private void getControl() {
		// TODO Auto-generated method stub
		tvCauHoi = (TextView) findViewById(R.id.textView1);
		tvThongBao = (TextView) findViewById(R.id.textView2);
		tvThoiGian = (TextView) findViewById(R.id.textView3);
		rdg = (RadioGroup) findViewById(R.id.radioGroup1);
		rd0 = (RadioButton) findViewById(R.id.radio0);
		rd1 = (RadioButton) findViewById(R.id.radio1);
		rd2 = (RadioButton) findViewById(R.id.radio2);
		rd3 = (RadioButton) findViewById(R.id.radio3);
		btnNext = (ImageButton) findViewById(R.id.btnNext);
		btCheck = (Button) findViewById(R.id.button2);
		tvThongBao.setTextColor(Color.BLUE);
		tvCauHoi.setTextColor(Color.RED);
		btCheck.setVisibility(View.GONE);
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
				tvThoiGian.setText("Thời gian: " + millisUntilFinished / 1000);
			}

			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				KiemTraCauDung();
				index++;
				if (index < socau) {
					hienthi(index);
					thoigian();
				} else {
					index = 0;
					btnNext.setVisibility(View.GONE);
					btCheck.setVisibility(View.VISIBLE);
					tvThoiGian.setVisibility(View.GONE);
					demThoiGian.cancel();
				}
			}
		};
		demThoiGian.start();
	}

	/**
	 * Hiển thị
	 */
	public void hienthi(int vitri) {
		tvThongBao.setText("Câu: " + (vitri + 1) + "/" + socau);
		cauhientai = ds_cauhoi.get(vitri);
		tvCauHoi.setText(cauhientai.cauhoi);
		rd0.setText(cauhientai.cau_a);
		rd1.setText(cauhientai.cau_b);
		rd2.setText(cauhientai.cau_c);
		rd3.setText(cauhientai.cau_d);
		// Sau khi hiển thị, xóa Checked của các Radion Button của Radion Group.
		rdg.clearCheck();
	}

	/**
	 * Kiểm tra câu đungs
	 * 
	 */
	public void KiemTraCauDung() {
		String cautraloi = "";
		if (rd0.isChecked() == true)
			cautraloi = "a";
		else if (rd1.isChecked() == true)
			cautraloi = "b";
		else if (rd2.isChecked() == true)
			cautraloi = "c";
		else if (rd3.isChecked() == true)
			cautraloi = "d";
		// Lưu trữ câu trả lời của người dùng vào List
		ds_cauhoi.get(index).cautraloi = cautraloi;
		// Kiểm tra câu trả lời và đáp án
		if (cautraloi.equalsIgnoreCase(cauhientai.dapan)) {
			caudung += 1;
			Random rd = new Random();
			int num = rd.nextInt(2);
			MediaPlayer mp_corect = MediaPlayer.create(this, amthanh[num]);
			mp_corect.start();
		}

	}

	/**
	 * Kiểm tra lại
	 * 
	 */
	public void KiemTraLai() {
		// Lưu tất cả Radion Button và màu đen mỗi khi ấn nút Check
		rd0.setTextColor(Color.BLACK);
		rd2.setTextColor(Color.BLACK);
		rd3.setTextColor(Color.BLACK);
		rd1.setTextColor(Color.BLACK);
		// Tô màu đỏ cho câu đáp án
		if (cauhientai.dapan.equalsIgnoreCase("a"))
			rd0.setTextColor(Color.RED);
		else if (cauhientai.dapan.equalsIgnoreCase("b"))
			rd1.setTextColor(Color.RED);
		else if (cauhientai.dapan.equalsIgnoreCase("c"))
			rd2.setTextColor(Color.RED);
		else if (cauhientai.dapan.equalsIgnoreCase("d"))
			rd3.setTextColor(Color.RED);
		// Checked vào câu trả lời của ngườii dùng để ngườii dùng so sanh với
		// đáp án
		if (cauhientai.cautraloi.equalsIgnoreCase("a"))
			rd0.setChecked(true);
		else if (cauhientai.cautraloi.equalsIgnoreCase("b"))
			rd1.setChecked(true);
		else if (cauhientai.cautraloi.equalsIgnoreCase("c"))
			rd2.setChecked(true);
		else if (cauhientai.cautraloi.equalsIgnoreCase("d"))
			rd3.setChecked(true);
		// Disable các Radion Button không cho ngườii dùng chọn lại
		rd0.setEnabled(false);
		rd1.setEnabled(false);
		rd2.setEnabled(false);
		rd3.setEnabled(false);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
