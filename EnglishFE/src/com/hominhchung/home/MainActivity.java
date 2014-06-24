package com.hominhchung.home;

import java.io.File;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.SearchManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.hominhchung.R;
import com.hominhchung.adapter.ConnectionDetector;
import com.hominhchung.adapter.SpinnerNavItem;
import com.hominhchung.adapter.actionbar.TitleNavigationAdapter;
import com.hominhchung.author.AuthorMainActivity;
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

@SuppressLint("NewApi")
public class MainActivity extends Activity implements
		ActionBar.OnNavigationListener {
	/**
	 * Khai báo biến cho speech to text
	 */
	protected static final int RESULT_SPEECH = 1;

	/**
	 * ActionBar
	 */
	private ActionBar actionBar;

	/**
	 * Title navigation Spinner data
	 */
	private ArrayList<SpinnerNavItem> navSpinner;

	/**
	 * Navigation adapter
	 */
	private TitleNavigationAdapter adapter;

	/**
	 * Refresh menu item
	 */
	private MenuItem refreshMenuItem;

	/**
	 * Khai báo các Control
	 */
	Button btn_vocabulary, btn_listening, btn_speaking, btn_reading,
			btn_writing, btn_grammar, btn_pronunciation, btn_experiences,
			btn_note, btn_testing, btn_kid, btn_registration;

	/**
	 * Khai báo đường dẫn
	 */
	String EFE_PATH = "/sdcard/EnglishFe/Music";
	File efeDirectory;

	/**
	 * Trạng thái kết nối Internet
	 */
	Boolean isInternetPresent = false;
	ConnectionDetector cd;// Connection detector class

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_fragment);

		// Tạo folder
		createFolder();

		// ActionBar
		getMyActionBar();

		// Gọi hàm Sound Effects
		Effects.getInstance().init(this);

		// Tạo kết nối đến hàm Internet Connection
		cd = new ConnectionDetector(getApplicationContext());
		isInternetPresent = cd.isConnectingToInternet();
		
		// Khai báo các Control
		getControl();

		// Thiết lập cho các Control
		setEvent();

	}

	/**
	 * ---------------------------------------EVENT----------------------------
	 * ---------
	 **/
	private void getMyActionBar() {
		// TODO Auto-generated method stub
		actionBar = getActionBar();

		// Ẩn tiêu đề Action bar
		actionBar.setDisplayShowTitleEnabled(false);

		// Enabling Spinner dropdown navigation
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

		// Lấy dữ liệu từ values>strings.xml
		Resources res = getResources();
		String[] menu_nav = res.getStringArray(R.array.menu_nav);

		// Gán dữ liệu vào Spinner title navigation
		navSpinner = new ArrayList<SpinnerNavItem>();
		navSpinner.add(new SpinnerNavItem(menu_nav[0], R.drawable.module_home));
		navSpinner.add(new SpinnerNavItem(menu_nav[1], R.drawable.module_help));
		navSpinner.add(new SpinnerNavItem(menu_nav[2],
				R.drawable.module_authors));
		navSpinner
				.add(new SpinnerNavItem(menu_nav[3], R.drawable.module_guide));
		navSpinner.add(new SpinnerNavItem(menu_nav[4], R.drawable.modul_email));
		navSpinner.add(new SpinnerNavItem(menu_nav[5], R.drawable.btn_close));

		// title drop down adapter
		adapter = new TitleNavigationAdapter(getApplicationContext(),
				navSpinner);

		// assigning the spinner navigation
		actionBar.setListNavigationCallbacks(adapter, this);
		// Changing the action bar icon
		// actionBar.setIcon(R.drawable.ico_actionbar);
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
		// 11 Listening to Kid button click
		btn_kid.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				// Launching News Feed Screen
				 // check for Internet status
				Effects.getInstance().playSound(Effects.SOUND_1);
				Intent i = new Intent(getApplicationContext(),
						KidMainActivity.class);
				startActivity(i);
//                if (isInternetPresent) {
//                    // Internet Connection is Present
//                    // make HTTP requests
//                	Effects.getInstance().playSound(Effects.SOUND_1);
//    				Intent i = new Intent(getApplicationContext(),
//    						KidMainActivity.class);
//    				startActivity(i);
//                } else {
//                    // Internet connection is not present
//                    // Ask user to connect to Internet
//                	Toast.makeText(getApplicationContext(),
//							getString(R.string.no_internet_connection), 1)
//							.show();
				// }

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
	private void createFolder() {
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

	/*--------------------------------CREATE OPTIONS MENU---------------------------------*/
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.activity_main_actions, menu);

		// Associate searchable configuration with the SearchView
		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		SearchView searchView = (SearchView) menu.findItem(R.id.action_search)
				.getActionView();
		searchView.setSearchableInfo(searchManager
				.getSearchableInfo(getComponentName()));

		return super.onCreateOptionsMenu(menu);
	}

	/**
	 * Thiết lập sự kiện cho các Control của Menu
	 * 
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Take appropriate action for each action item click
		switch (item.getItemId()) {
		case R.id.action_search:
			// search action
			return true;
			/**
			 * Action speech
			 * 
			 */
		case R.id.action_speed:
			// Gọi hàm Recognizer
			Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
			// xac nhan ung dung muon gui yeu cau
			intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getClass()
					.getPackage().getName());

			// goi y nhan dang nhung gi nguoi dung se noi
			intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
					RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

			// goi y nhung dieu nguoi dung muon noi
			intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
					"Tell me what you want");

			// Xac dinh ban muon bao nhieu ket qua gan dung duoc tra ve
			// intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 10);

			try {
				startActivityForResult(intent, RESULT_SPEECH);
			} catch (ActivityNotFoundException e) {
				// Máy không hỗ trơ
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setTitle(getString(R.string.not_available));
				builder.setMessage(getString(R.string.no_recognition_software_installed));
				builder.setPositiveButton(getString(R.string.yes),
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								Intent marketIntent = new Intent(
										Intent.ACTION_VIEW);
								marketIntent.setData(Uri
										.parse(getString(R.string.url_download_voicesearch)));
								startActivity(marketIntent);
							}
						});
				builder.setNegativeButton(getString(R.string.no), null);
				builder.create().show();
			}
			return true;
		case R.id.action_refresh:
			// refresh
			refreshMenuItem = item;
			// load the data from server
			new SyncData().execute();
			return true;
		case R.id.action_help:
			// help action
			return true;
		case R.id.action_check_updates:
			// check for updates action
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/*-------------------------------NavigationItemSelected------------------------------*/
	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		// Action to be taken after selecting a spinner item
		int num = itemPosition + 1;
		switch (num) {
		case 1:
			Toast.makeText(getApplicationContext(), "Trang chủ",
					Toast.LENGTH_SHORT).show();

			break;
		case 2:
			Toast.makeText(getApplicationContext(), "Trợ giúp",
					Toast.LENGTH_SHORT).show();
			/*
			 * Intent i = new Intent(getApplicationContext(),
			 * AuthorActivity.class); startActivity(i);
			 */
			break;
		case 3:
			Toast.makeText(getApplicationContext(), "Tác giả",
					Toast.LENGTH_SHORT).show();
			Intent i = new Intent(getApplicationContext(),
					AuthorMainActivity.class);
			startActivity(i);
			break;
		case 4:
			Toast.makeText(getApplicationContext(), "Hướng dẫn",
					Toast.LENGTH_SHORT).show();
			/*
			 * Intent i = new Intent(getApplicationContext(),
			 * AuthorActivity.class); startActivity(i);
			 */
			break;
		// Gửi mail
		case 5:
			String emailList[] = { getString(R.string.my_email) };
			Intent intent = new Intent(Intent.ACTION_SEND);
			intent.setType("plain/text");
			intent.putExtra(Intent.EXTRA_EMAIL, emailList);
			intent.putExtra(Intent.EXTRA_SUBJECT, "Email Subject");
			intent.putExtra(Intent.EXTRA_TEXT, "Email Text");
			startActivity(Intent.createChooser(intent, "Choice email App"));
			break;
		case 6:
			btnExitClick(this);
			break;
		default:
			break;
		}
		return false;
	}

	/*-----------------------------EVENT OF NavigationItemSelected-------------------------------*/
	public void btnExitClick(MainActivity mainActivity) {
		AlertDialog.Builder alrt = new Builder(this);
		alrt.setTitle(getString(R.string.confirm_exit));
		alrt.setMessage(getString(R.string.are_you_sure_you_want_to_exit));
		alrt.setIcon(R.drawable.delete);
		alrt.setPositiveButton(getString(R.string.yes),
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						MainActivity.this.finish();
					}
				});
		alrt.setNegativeButton(getString(R.string.no),
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.cancel();
					}
				});
		alrt.show();
	}

	private void LocationFound() {
		Intent i = new Intent(MainActivity.this, LocationFound.class);
		startActivity(i);
	}

	/*------------------------------------ASYNCTASK---------------------------------*/
	/**
	 * Async task to load the data from server
	 * **/
	private class SyncData extends AsyncTask<String, Void, String> {
		@Override
		protected void onPreExecute() {
			// set the progress bar view
			refreshMenuItem.setActionView(R.layout.action_progressbar);

			refreshMenuItem.expandActionView();
		}

		@Override
		protected String doInBackground(String... params) {
			// not making real request in this demo
			// for now we use a timer to wait for sometime
			/*
			 * try { Thread.sleep(3000); } catch (InterruptedException e) {
			 * e.printStackTrace(); }
			 */
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			refreshMenuItem.collapseActionView();
			// remove the progress bar view
			refreshMenuItem.setActionView(null);
		}
	};

	/*-------------------------------------SPEECH TO TEXT-----------------------------------*/
	// Nhận kết quả từ server
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
		case RESULT_SPEECH: {
			// Trường hợp có giá trị trả về
			if (resultCode == RESULT_OK && null != data) {

				ArrayList<String> text = data
						.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
				// Thông báo và hiển thị kết quả
				Toast.makeText(getApplicationContext(), text.get(0),
						Toast.LENGTH_LONG).show();
				// txtText.setText(text.get(0));
				// xulyketqua(text.get(0).toString().toLowerCase()+"");

				xulyketqua(text.get(0).toString().toLowerCase() + "");
			} else {
				// Hiển thị thông báo thất bại
				Toast.makeText(this, getString(R.string.operation_canceled),
						Toast.LENGTH_SHORT).show();
				break;
			}
		}
		}

	}

	private void xulyketqua(String kq) {
		// TODO Auto-generated method stub

		Toast.makeText(getApplicationContext(), kq, 1).show();
		Effects.getInstance().playSound(Effects.SOUND_1);
		if (kq.equals("vocabulary")) {
			Intent i1 = new Intent(getApplicationContext(),
					ListVocabularyMainActivity.class);
			startActivity(i1);
		} else if (kq.equals("listening")) {
			Intent i = new Intent(getApplicationContext(),
					ListListeningMainActivity.class);
			startActivity(i);
		} else if (kq.equals("speaking")) {
			Intent i = new Intent(getApplicationContext(),
					ListSpeakingMainActivity.class);
			startActivity(i);
		} else if (kq.equals("reading")) {
			Intent i = new Intent(getApplicationContext(),
					ListReadingMainActivity.class);
			startActivity(i);
		} else if (kq.equals("writing")) {
			Intent i = new Intent(getApplicationContext(),
					ListWritingMainActivity.class);
			startActivity(i);
		} else if (kq.equals("grammar")) {
			Intent i = new Intent(getApplicationContext(),
					ListGrammarMainActivity.class);
			startActivity(i);
		} else if (kq.equals("pronunciation")) {
			Intent i = new Intent(getApplicationContext(),
					ListPronunciationMainActivity.class);
			startActivity(i);
		} else if (kq.equals("expereences")) {
			Intent i = new Intent(getApplicationContext(),
					ListExperienceMainActivity.class);
			startActivity(i);
		} else if (kq.equals("notes")) {
			Intent i = new Intent(getApplicationContext(), NoteList.class);
			startActivity(i);
		} else if (kq.equals("testing")) {
			Intent i = new Intent(getApplicationContext(),
					ListTestingMainActivity.class);
			startActivity(i);
		} else if (kq.equals("kids")) {
			Intent i = new Intent(getApplicationContext(),
					KidMainActivity.class);
			startActivity(i);
		} else if (kq.equals("online")) {
			Intent i = new Intent(getApplicationContext(),
					RegistryMainActivity.class);
			startActivity(i);
		}
	}

}
