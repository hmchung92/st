package com.hominhchung.listening.music;

import java.io.IOException;
import java.util.ArrayList;

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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.hominhchung.R;

public class ListLessonMusic extends Activity {
	ArrayList<ItemDetails> results;
	ItemDetails item_details;
	int i = 1;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.module_list);

		// Sound Effects
		//Effects.getInstance().init(this);

		ArrayList<ItemDetails> image_details = GetSearchResults();

		final ListView lv1 = (ListView) findViewById(R.id.list_view);
		lv1.setAdapter(new ItemListBaseAdapter(this, image_details));

		lv1.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> a, View v, int position,
					long id) {
				Object o = lv1.getItemAtPosition(position);

				// Music
				//Effects.getInstance().playSound(Effects.SOUND_1);

				ItemDetails obj_itemDetails = (ItemDetails) o;

				// Hien thi ten bai hat da chon
				Toast.makeText(
						ListLessonMusic.this,
						"You have chosen : " + " " + obj_itemDetails.getTitle(),
						Toast.LENGTH_LONG).show();
				// Chuyen Activity
				Intent ex = new Intent(ListLessonMusic.this, LessonMusic.class);
				// Gui Bundle data
				int num = position + 1;
				ex.putExtra("data", num);
				ex.putExtra("title", obj_itemDetails.getTitle()); // Ten bai hat
				ex.putExtra("url", obj_itemDetails.getUrl()); // url download nhac
				ex.putExtra("author", obj_itemDetails.getAuthor()); // ca sy
				ex.putExtra("time", obj_itemDetails.getTime()); // thoi gian bai nhac
				startActivity(ex);
			}
		});
	}

	private ArrayList<ItemDetails> GetSearchResults() {
		results = new ArrayList<ItemDetails>();

		try {
			getItemFromXML(this);
		} catch (XmlPullParserException e) {
		} catch (IOException e) {
		}
		return results;
	}

	public void getItemFromXML(Activity activity)
			throws XmlPullParserException, IOException {
		StringBuffer stringBuffer1 = new StringBuffer();
		StringBuffer stringBuffer2 = new StringBuffer();
		Resources res = activity.getResources();
		XmlResourceParser xpp = res.getXml(R.xml.music);
		xpp.next();
		int eventType = xpp.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			if (eventType == XmlPullParser.START_TAG) {
				if (xpp.getName().equals("item1")) {
					item_details = new ItemDetails();
					// Ten bai hat
					item_details.setTitle(xpp.getAttributeValue(null,
							"sing_name").toString());
					// Nghe sy 
					item_details.setAuthor(xpp.getAttributeValue(null,
							"author_name").toString());
					// Thoi gian bai hat
					item_details.setTime(xpp.getAttributeValue(null,
							"sing_time").toString());
					//Url download hat
					item_details.setUrl(xpp
							.getAttributeValue(null, "mp3_file_url").toString());
					item_details.setImageNumber(i++);
					results.add(item_details);
				}
			}
			eventType = xpp.next();
		}
	}

}