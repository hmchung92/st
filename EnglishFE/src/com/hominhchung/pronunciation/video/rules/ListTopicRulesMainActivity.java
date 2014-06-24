package com.hominhchung.pronunciation.video.rules;


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
 
public class ListTopicRulesMainActivity extends Activity {
	
	// List view
	private ListView lv;
	
	// Listview Adapter
	LazyAdapterRulesPronunciation adapter;
	
	// Search EditText
	
	
	// ArrayList for Listview
	ArrayList<HashMap<String, String>> sessionList;
	int i=0;
	
	static final String KEY_LESSON = "module61"; // parent node
	static final String KEY_ID = "id";
	static final String KEY_LOCAL_TITLE = "local_title";
	static final String KEY_EN_TITLE = "en_title";
	static final int KEY_ITEM_IMAGE = R.drawable.dashboarde__listening;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_list);
        
		//Sound Effects
      	Effects.getInstance().init(this);
        
        sessionList = new ArrayList<HashMap<String, String>>();
        
        lv = (ListView) findViewById(R.id.list_view);
        try{
			getItemFromXML(this);
		}catch (XmlPullParserException e){
		}catch (IOException e){
		} 
        
        // Adding items to listview
        //adapter = new ArrayAdapter<String>(this, R.layout.adapter_experience, R.id.tv_name, local_title);
        adapter=new LazyAdapterRulesPronunciation(this, sessionList);
        lv.setAdapter(adapter);
        
        lv.setOnItemClickListener(
				new AdapterView.OnItemClickListener() {
					public void onItemClick(AdapterView<?> arg0,
							View arg1, 
							int arg2,
							long arg3) {
						int num=arg2+1;
						Effects.getInstance().playSound(Effects.SOUND_1);
						Intent ex= new Intent(ListTopicRulesMainActivity.this,LessonTopicRulesPronunciation.class);
						ex.putExtra("data","pronuncication/topic"+num+".html");
						startActivity(ex);
					}
				});
		
        /**
         * Enabling Search Filter
         * */
    }
    public void getItemFromXML(Activity activity) throws XmlPullParserException, IOException{
		StringBuffer stringBuffer1 = new StringBuffer();
		StringBuffer stringBuffer2 = new StringBuffer();
		
		Resources res = activity.getResources();
		XmlResourceParser xpp = res.getXml(R.xml.module);
		xpp.next();
		int eventType = xpp.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT){
			if (eventType == XmlPullParser.START_TAG){
				if (xpp.getName().equals(KEY_LESSON)){
					HashMap<String, String> map = new HashMap<String, String>();
					i++;
					if(i<=9){
						map.put(KEY_LOCAL_TITLE, "0"+i+". "+xpp.getAttributeValue(null, "en_title").toString());
					}
					else{
						map.put(KEY_LOCAL_TITLE, i+". "+xpp.getAttributeValue(null, "en_title").toString());
					}
					map.put(KEY_EN_TITLE, xpp.getAttributeValue(null, "local_title").toString());
					sessionList.add(map);
				}
			}
			eventType = xpp.next();
		}
	}
    
}
