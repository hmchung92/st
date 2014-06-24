package com.hominhchung.speaking;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.hominhchung.R;
import com.hominhchung.home.Effects;
import com.hominhchung.speaking.description_pictures.ListSpeakingPictureTopicMainActivity;
import com.hominhchung.speaking.general_debates.ListSpeakingPictureTopicDebatesMainActivity;
import com.hominhchung.speaking.speechtotext.SpeechToTextMainActivity;
import com.hominhchung.speaking.topic100.ListSpeakTopicMainActivity;

public class ListSpeakingMainActivity extends Activity {

	// List view
	private ListView lv;
	LazyAdapterSpeaking adapter;
	// ArrayList for Listview
	ArrayList<HashMap<String, String>> sessionList;

	int i = 0;

	// Khai báo các giá trị cần truy vấn file XML
	static final String KEY_LESSON = "module4"; // parent node
	static final String KEY_ID = "id";
	static final String KEY_LOCAL_TITLE = "local_title";
	static final String KEY_EN_TITLE = "en_title";
	static final int KEY_ITEM_IMAGE = R.drawable.dashboarde__speaking;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.module_list);

		// gọi hàm Sound Effects
		Effects.getInstance().init(this);

		// Khai báo các Control
		getControl();

		// Truy vấn dữ liệu XML cho vào Lisview
		getData();

		// Thêm sự kiệ 
		setEvents();

	}
	
	/**
	 * Sự kiện khi nhấn vào listview
	 * */
	private void setEvents() {
		// TODO Auto-generated method stub
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				int num = arg2 + 1;
				Effects.getInstance().playSound(Effects.SOUND_1);
				switch (num) {
				/* 1. 100 chủ đề luyện nói tiếng Anh */
				case 1:
					Intent ex1 = new Intent(ListSpeakingMainActivity.this,
							ListSpeakTopicMainActivity.class);
					startActivity(ex1);
					break;
				/* 2. Miêu tả tranh qua giọng nói */
				case 2:
					Intent ex2 = new Intent(ListSpeakingMainActivity.this,
							ListSpeakingPictureTopicMainActivity.class);
					startActivity(ex2);
					break;
				/* 3. Tranh luận */
				case 3:
					Intent ex3 = new Intent(ListSpeakingMainActivity.this,
							ListSpeakingPictureTopicDebatesMainActivity.class);
					// ex4.putExtra("data","");
					Effects.getInstance().playSound(Effects.SOUND_1);
					startActivity(ex3);
					break;
				case 4:
					Intent ex4 = new Intent(ListSpeakingMainActivity.this,
							SpeechToTextMainActivity.class);
					// ex4.putExtra("data","");
					startActivity(ex4);
					break;

				default:
					break;
				}
			}
		});
	}
	/**
	 * truy vấn dữ liệu XML cho vào Lisview
	 * */
	private void getData() {
		// TODO Auto-generated method stub

		try {
			getItemFromXML(this);
		} catch (XmlPullParserException e) {
		} catch (IOException e) {
		}

		adapter = new LazyAdapterSpeaking(this, sessionList);
		lv.setAdapter(adapter);
	}
	/**
	 * Khai báo control
	 * */
	private void getControl() {
		// TODO Auto-generated method stub
		sessionList = new ArrayList<HashMap<String, String>>();
		lv = (ListView) findViewById(R.id.list_view);
	}

	
	/*-----------------------------------XML-------------------------------------*/
	/**
	 * truy vấn dữ liệu XML
	 * */
	public void getItemFromXML(Activity activity)
			throws XmlPullParserException, IOException {
		Resources res = activity.getResources();
		XmlResourceParser xpp = res.getXml(R.xml.module);
		xpp.next();
		int eventType = xpp.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			if (eventType == XmlPullParser.START_TAG) {
				if (xpp.getName().equals(KEY_LESSON)) {
					HashMap<String, String> map = new HashMap<String, String>();
					i++;
					if (i <= 9) {
						map.put(KEY_LOCAL_TITLE, "0"
								+ i
								+ ". "
								+ xpp.getAttributeValue(null, "en_title")
										.toString());
					} else {
						map.put(KEY_LOCAL_TITLE, i
								+ ". "
								+ xpp.getAttributeValue(null, "en_title")
										.toString());
					}
					map.put(KEY_EN_TITLE,
							xpp.getAttributeValue(null, "local_title")
									.toString());
					sessionList.add(map);
				}
			}
			eventType = xpp.next();
		}
	}
	/*-----------------------------------MENU-------------------------------------*/


}
