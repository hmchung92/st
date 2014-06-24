package com.hominhchung.listening.music;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hominhchung.R;
import com.hominhchung.adapter.ConnectionDetector;

public class LessonMusic extends FragmentActivity {

	// create object of FragmentPagerAdapter
	SectionsPagerAdapter mSectionsPagerAdapter;
	static int num = 0;
	// viewpager to display pages
	ViewPager mViewPager;
	int numTab = 4;

	// Khai bao nhan du lieu tu Bundle
	static String title_bundle = "";
	static String url_bundle = "";
	static String author_bundle = "";
	static String time_bundle = "";

	// flag cho tình trạng kết nối Internet
    static Boolean isInternetPresent = false;
	// Connectiondetector class
    static ConnectionDetector cd;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.adapter_view_pagertabstrip_layout);

		// Lay Bundle data
		getBundleData();

		// Tạo connectiondetector class 
        cd = new ConnectionDetector(getApplicationContext());
		
		// Thiet lap su kien
		setEventAdapter();// primary sections of the app.
		setEventViewPager();

	}

	/*-------------------------------------Event-----------------------------------------*/
	private void setEventViewPager() {
		// TODO Auto-generated method stub
		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
	}

	private void setEventAdapter() {
		// TODO Auto-generated method stub
		// Create the adapter that will return a fragment for each of the five
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());
	}

	private void getBundleData() {
		// TODO Auto-generated method stub
		Bundle localBundle = getIntent().getExtras();
		if (localBundle != null)
			num = localBundle.getInt("data");
		title_bundle = localBundle.getString("title");
		url_bundle = localBundle.getString("url");
		author_bundle = localBundle.getString("author");
		time_bundle = localBundle.getString("time");
		
		setTitle(title_bundle);
	}

	/*-------------------------------------Menu-----------------------------------------*/
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/*-----------------------------SectionsPagerAdapter---------------------------------*/
	/**
	 * A FragmentPagerAdapter that returns a fragment corresponding to one of
	 * the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			switch (position) {
			// Xem và download mp3
			case 0:
				Fragment fragment1 = new DummySectionFragment();
				Bundle args1 = new Bundle();
				// Truyền tham số vào biến ARG_SECTION_NUMBER
				args1.putInt(DummySectionFragment.ARG_SECTION_NUMBER,
						position + 1);
				fragment1.setArguments(args1);
				return fragment1;
				// Bài tập
			case 1:
				Fragment fragment2 = new DummySectionFragment();
				Bundle args2 = new Bundle();
				args2.putInt(DummySectionFragment.ARG_SECTION_NUMBER,
						position + 1);
				fragment2.setArguments(args2);
				return fragment2;
				// Lyric
			case 2:
				Fragment fragment3 = new DummySectionFragment();
				Bundle args3 = new Bundle();
				args3.putInt(DummySectionFragment.ARG_SECTION_NUMBER,
						position + 1);
				fragment3.setArguments(args3);
				return fragment3;
				// Lời việt
			case 3:
				Fragment fragment4 = new DummySectionFragment();
				Bundle args4 = new Bundle();
				args4.putInt(DummySectionFragment.ARG_SECTION_NUMBER,
						position + 1);
				fragment4.setArguments(args4);
				return fragment4;
			}
			return null;

		}

		@Override
		public int getCount() {
			// Show 4 total pages.
			return numTab;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			switch (position) {
			case 0:
				return "Nghe nhạc".toUpperCase();
			case 1:
				return "Bài tập".toUpperCase();
			case 2:
				return "Lyric".toUpperCase();
			case 3:
				return "Lời dịch".toUpperCase();
			}
			return null;
		}
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	/*-------------------------------------DummySectionFragment-----------------------------------------*/
	public static class DummySectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		// MediaPlayer
		MediaPlayer mediaPlayer;
		LinearLayout liner_1;

		SeekBar seek_bar;
		Handler seekHandler = new Handler();
		private int stateMediaPlayer;
		private final int stateMP_Error = 0;
		private final int stateMP_NotStarter = 1;
		private final int stateMP_Playing = 2;
		private final int stateMP_Pausing = 3;

		// get Control
		Button btn_download;
		ImageButton imageButtonPlay;
		TextView textState, text_title, text_author, text_time,text_file_name;
		TextView cur_val; // Title download

		// Englishfe folder
		String EFE_PATH = "/sdcard/EnglishFe/Music";
		File efeDirectory;
		
		// ProgressBar
		ProgressBar pb;
		Dialog dialog;

		// Download
		int downloadedSize = 0;
		int totalSize = 0;

		// String dwnload_file_path =
		// "http://anhvan123.net/datablue/tienganhquabaihat/bathu/62.mp3";
		String dwnload_file_path = url_bundle;
		
		// Tham số nhận giá trị từ Bundle
		public static final String ARG_SECTION_NUMBER = "section_number";

		public DummySectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// Create a new TextView and set its text to the fragment's section
			// number argument value.
			/*
			 * TextView textView = new TextView(getActivity());
			 * textView.setGravity(Gravity.CENTER);
			 * textView.setText(Integer.toString(getArguments().getInt(
			 * ARG_SECTION_NUMBER))); return textView;
			 */
			String DEFAULT_URL = null;
			WebView webview = new WebView(getActivity());
			if (getArguments().getInt(ARG_SECTION_NUMBER) == 1) {
				View view = inflater.inflate(R.layout.listening_music_play2,
						container, false);

				seek_bar = (SeekBar) view.findViewById(R.id.seek_bar);
				imageButtonPlay = (ImageButton) view
						.findViewById(R.id.imageButtonPlay);

				btn_download = (Button) view.findViewById(R.id.btn_download);
				textState = (TextView) view.findViewById(R.id.text_shown);
				text_title = (TextView) view.findViewById(R.id.text_title); // Tiêu đề
				text_file_name = (TextView) view.findViewById(R.id.text_file_name);
				text_author = (TextView) view.findViewById(R.id.text_author); // Ca
																				// sỹ
				text_time = (TextView) view.findViewById(R.id.text_time); // Time

				// String title_bundle1 =
				// getArguments().getString(ARG_SECTION_STRING);
				text_title.setText(title_bundle);// Tiêu đề bài hát
				text_author.setText(author_bundle);// Ca sỹ
				text_time.setText(time_bundle);// Thời gian
				text_file_name.setText(title_bundle+".mp3");// Tên file
				imageButtonPlay
						.setOnClickListener(imagebuttonPlayPauseOnClickListener);

				// initMediaPlayer1();
				createFolder();
				initMediaPlayer();
				seekUpdation();
				return view;

			} else {
				// final String DEFAULT_URL =
				// "file:///android_asset/listening/music/topic"+num+"-"+getArguments().getInt(ARG_SECTION_NUMBER)+".html";
				int k = getArguments().getInt(ARG_SECTION_NUMBER) - 1;
				DEFAULT_URL = "file:///android_asset/listening/music/topic"
						+ num + "-" + k + ".html";
				webview.getSettings().setJavaScriptEnabled(true);
				webview.getSettings().setBuiltInZoomControls(true);
				webview.loadUrl(DEFAULT_URL);
			}

			return webview;
		}

		private void createFolder() {
			// TODO Auto-generated method stub
			efeDirectory = new File(EFE_PATH); // /sdcard/EnglishFe/Music;
			if (!efeDirectory.exists()) {
				// have the object build the directory structure, if needed.
				efeDirectory.mkdirs();
			}
		}

		/*-----------------------------------MediaPlayer-------------------------------------*/
		private void initMediaPlayer() {
			String PATH_TO_FILE = "/sdcard/EnglishFe/Music/" + title_bundle
					+ ".mp3";
			mediaPlayer = new MediaPlayer();

			try {
				// Bài hát đã được tải về
				btn_download.setHeight(0);
				btn_download.setVisibility(View.INVISIBLE); // Ẩn Download
															// Button
				mediaPlayer.setDataSource(PATH_TO_FILE);
				mediaPlayer.prepare();

				stateMediaPlayer = stateMP_NotStarter;

				textState.setText("-IDLE-");
				seek_bar.setMax(mediaPlayer.getDuration());
				// Cho phép tùy chỉnh seekBar để nghe
				seek_bar.setOnTouchListener(new OnTouchListener() {
					@Override
					public boolean onTouch(View v, MotionEvent event) {
						seekChange(v);
						return false;
					}
				});
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

				stateMediaPlayer = stateMP_Error;
				textState.setText("- ERROR!!! -");
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

				stateMediaPlayer = stateMP_Error;
				textState.setText("- ERROR!!! -");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				// Bài hát chưa được tải về
				btn_download.setHeight(0);
				btn_download.setVisibility(View.VISIBLE);// Hiện Download Button
				imageButtonPlay.setVisibility(View.INVISIBLE);// Ẩn Play
																// ImageButton
				textState.setVisibility(View.INVISIBLE);// Ẩn State
				// Thông báo
				Toast.makeText(getActivity(), "Bấm Download để tải bài hát về",
						Toast.LENGTH_LONG).show();

				// Tiến hành EventDownload
				setEventDownload();

				stateMediaPlayer = stateMP_Error;
				textState.setText("- ERROR!!! -");
				textState.setVisibility(View.INVISIBLE);//Ẩn  State
			}
		}

		/*-----------------------------------Event in Fragment -------------------------------------*/
		private void setEventDownload() {
			// TODO Auto-generated method stub
			btn_download.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					
					// lấy tình trạng kết nối Internet 
	                isInternetPresent = cd.isConnectingToInternet();
	             // check for Internet status
	                if (isInternetPresent) {
	                    // Internet Connection is Present
	                    // make HTTP requests
	                	showProgress(dwnload_file_path);

	        			new Thread(new Runnable() {
	        				public void run() {
	        					downloadFile();
	        				}
	        			}).start();
	                } else {
	                    // Internet connection is not present
	                    // Ask user to connect to Internet
	                	Toast.makeText(getActivity(), getString(R.string.no_internet_connection), 1).show();
	                }
					
				}
			});
		}

		
		// Listening to buttonPlayPause button click
		ImageButton.OnClickListener imagebuttonPlayPauseOnClickListener = new ImageButton.OnClickListener() {

			@Override
			public void onClick(View v) {
				int dk = 0;
				// TODO Auto-generated method stub
				switch (stateMediaPlayer) {

				case stateMP_Error:
					break;
				case stateMP_NotStarter:
					mediaPlayer.start();
					imageButtonPlay
							.setImageResource(R.drawable.mediacontroller_pause_button);

					textState.setText("- PLAYING -");
					stateMediaPlayer = stateMP_Playing;
					break;
				case stateMP_Playing:
					mediaPlayer.pause();
					imageButtonPlay
							.setImageResource(R.drawable.mediacontroller_play_button);

					textState.setText("- PAUSING -");
					stateMediaPlayer = stateMP_Pausing;
					break;
				case stateMP_Pausing:
					mediaPlayer.start();
					imageButtonPlay
							.setImageResource(R.drawable.mediacontroller_pause_button);

					textState.setText("- PLAYING -");
					stateMediaPlayer = stateMP_Playing;
					break;

				}

			}
		};

		// Listening to buttonQuit button click
		Button.OnClickListener buttonQuitOnClickListener = new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mediaPlayer.stop();
				mediaPlayer.release();
				// finish();
			}
		};

		/*-----------------------------------Download File-------------------------------------*/
		void downloadFile() {

			try {
				URL url = new URL(dwnload_file_path);
				HttpURLConnection urlConnection = (HttpURLConnection) url
						.openConnection();
				urlConnection.setRequestMethod("GET");
				urlConnection.setDoOutput(true);

				// connect
				urlConnection.connect();

				// set the path where we want to save the file
				/*File efeDirectory = new File(EFE_PATH); // /sdcard/EnglishFe/Music;
				if (!efeDirectory.exists()) {
					// have the object build the directory structure, if needed.
					efeDirectory.mkdirs();
				}*/
				// create a new file, to save the downloaded file
				File file = new File(efeDirectory, title_bundle + ".mp3");

				// File SDCardRoot = Environment.getExternalStorageDirectory();
				// create a new file, to save the downloaded file

				FileOutputStream fileOutput = new FileOutputStream(file);

				// Stream used for reading the data from the internet
				InputStream inputStream = urlConnection.getInputStream();

				// this is the total size of the file which we are downloading
				totalSize = urlConnection.getContentLength();

				getActivity().runOnUiThread(new Runnable() {
					public void run() {
						pb.setMax(totalSize);
					}
				});

				// create a buffer...
				byte[] buffer = new byte[1024];
				int bufferLength = 0;

				while ((bufferLength = inputStream.read(buffer)) > 0) {
					fileOutput.write(buffer, 0, bufferLength);
					downloadedSize += bufferLength;
					// update the progressbar //
					getActivity().runOnUiThread(new Runnable() {
						public void run() {
							pb.setProgress(downloadedSize);
							float per = ((float) downloadedSize / totalSize) * 100;
							cur_val.setText("Downloaded " + downloadedSize
									+ "KB / " + totalSize + "KB (" + (int) per
									+ "%)");
						}
					});
				}
				// close the output stream when complete //
				fileOutput.close();
				getActivity().runOnUiThread(new Runnable() {
					public void run() {
						btn_download.setVisibility(View.INVISIBLE);
						imageButtonPlay.setVisibility(View.VISIBLE); // Hiện nút
																		// Play

						Intent ex1 = new Intent(getActivity(),
								LessonMusic.class);
						ex1.putExtra("title", title_bundle); // Ten bai hat
						ex1.putExtra("url", url_bundle); // url download nhac
						ex1.putExtra("author", author_bundle); // ca sy
						ex1.putExtra("time", time_bundle); // thoi gian bai nhac
						startActivity(ex1);

						// pb.dismiss(); // if you want close it..
					}
				});

			} catch (final MalformedURLException e) {
				showError("Error : MalformedURLException " + e);
				e.printStackTrace();
			} catch (final IOException e) {
				showError("Error : IOException " + e);
				e.printStackTrace();
			} catch (final Exception e) {
				showError("Error : Please check your internet connection " + e);
			}
		}

		// showProgress
		void showProgress(String file_path) {
			dialog = new Dialog(getActivity());
			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			dialog.setContentView(R.layout.listening_music_download_progressdialog);
			dialog.setTitle("Download Progress");

			TextView text = (TextView) dialog.findViewById(R.id.tv1);
			text.setText(title_bundle + "\n is downloading file from ... "
					+ file_path);
			cur_val = (TextView) dialog.findViewById(R.id.cur_pg_tv);
			cur_val.setText("Starting download...");
			dialog.show();

			pb = (ProgressBar) dialog.findViewById(R.id.progress_bar);
			pb.setProgress(0);
			pb.setProgressDrawable(getResources().getDrawable(
					R.drawable.green_progress));
		}

		void showError(final String err) {

			Toast.makeText(getActivity(), err, Toast.LENGTH_LONG).show();

		}

		/*-----------------------------------SeekBar-------------------------------------*/
		// Tua nhac
		// This is event handler thumb moving event
		private void seekChange(View v) {
			if (mediaPlayer.isPlaying()) {
				SeekBar sb = (SeekBar) v;
				mediaPlayer.seekTo(sb.getProgress());
			}
		}

		public void seekUpdation() {

			seek_bar.setProgress(mediaPlayer.getCurrentPosition());
			seekHandler.postDelayed(run, 1000);
		}

		Runnable run = new Runnable() {

			@Override
			public void run() {
				seekUpdation();
			}
		};
	}

}