package com.hominhchung.vocabulary.video;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.hominhchung.R;

public class ListVideoVocabularyMainActivity extends Activity {

	// List view
	private ListView lv;

	// Listview Adapter
	LazyAdapterVideo adapter;

	// Search EditText

	// ArrayList for Listview
	ArrayList<HashMap<String, String>> sessionList;
	ArrayList<String> local_text;
	int i = 0;

	static final String KEY_LESSON = "item0"; // parent node
	static final String KEY_ID = "id";
	static final String KEY_LOCAL_TITLE = "local_title";
	static final String KEY_EN_TITLE = "en_title";
	static final int KEY_ITEM_IMAGE = R.drawable.dashboarde__listening;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.module_list);

		// Khai báo các control
		getControl();

		// Đưa dữ liệu từ XML file vào LISTVIEW
		setDataOnListView();

		// Sự kiện khi nhấn vào a row listview
		setOnClickListView();

	}

	/*----------------------------------Control----------------------------------*/
	private void setOnClickListView() {
		// TODO Auto-generated method stub
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				int num = arg2 + 1;
				Intent localIntent = new Intent("android.intent.action.VIEW");
				localIntent.setData(Uri.parse(local_text.get(arg2)));
				startActivity(localIntent);

			}
		});
	}

	private void setDataOnListView() {
		// TODO Auto-generated method stub
		local_text = new ArrayList<String>();
		try {
			getItemFromXML(this);
		} catch (XmlPullParserException e) {
		} catch (IOException e) {
		}

		// Adding items to listview
		adapter = new LazyAdapterVideo(this, sessionList);
		lv.setAdapter(adapter);
	}

	private void getControl() {
		// TODO Auto-generated method stub
		sessionList = new ArrayList<HashMap<String, String>>();

		lv = (ListView) findViewById(R.id.list_view);
	}

	/*----------------------------------GET XML----------------------------------*/
	public void getItemFromXML(Activity activity)
			throws XmlPullParserException, IOException {
		StringBuffer stringBuffer1 = new StringBuffer();
		StringBuffer stringBuffer2 = new StringBuffer();

		Resources res = activity.getResources();
		XmlResourceParser xpp = res.getXml(R.xml.video_lessons_vi);
		xpp.next();
		int eventType = xpp.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			if (eventType == XmlPullParser.START_TAG) {
				if (xpp.getName().equals(KEY_LESSON)) {
					HashMap<String, String> map = new HashMap<String, String>();
					map.put(KEY_LOCAL_TITLE,xpp.getAttributeValue(null, "item_en_name").toString());
					map.put(KEY_EN_TITLE,xpp.getAttributeValue(null, "item_loc_name").toString());
					local_text.add(xpp.getAttributeValue(null, "item_url").toString());
					sessionList.add(map);
				}
			}
			eventType = xpp.next();
		}
	}

}
