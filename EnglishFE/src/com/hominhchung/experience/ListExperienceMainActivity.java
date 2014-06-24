package com.hominhchung.experience;
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
import com.hominhchung.experience.document.ListLessonExperienceMainActivity;
import com.hominhchung.experience.video.ListVideoExperienceMainActivity;
import com.hominhchung.home.Effects;
 
public class ListExperienceMainActivity extends Activity {
	
	// List view
	private ListView lv;
	
	// Listview Adapter
	LazyAdapterExperience adapter;
	
	// ArrayList for Listview
	ArrayList<HashMap<String, String>> sessionList;

	int i=0;
	
	static final String KEY_LESSON = "module7"; // parent node
	static final String KEY_ID = "id";
	static final String KEY_LOCAL_TITLE = "local_title";
	static final String KEY_EN_TITLE = "en_title";
	static final int KEY_ITEM_IMAGE = R.drawable.dashboarde__experiences;
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
        adapter=new LazyAdapterExperience(this, sessionList);
        lv.setAdapter(adapter);
        
        lv.setOnItemClickListener(
				new AdapterView.OnItemClickListener() {
					public void onItemClick(AdapterView<?> arg0,
							View arg1, 
							int arg2,
							long arg3) {
						int num=arg2+1;
						switch (num) {
						/*Kinh nghiệm học anh văn*/
						case 1:
							Intent ex1 = new Intent(ListExperienceMainActivity.this,ListLessonExperienceMainActivity.class);
							startActivity(ex1);
							Effects.getInstance().playSound(Effects.SOUND_1);
							break;
						/*Kinh nghiệm học anh văn qua Video*/
						case 2:
							Effects.getInstance().playSound(Effects.SOUND_1);
							Intent ex2 = new Intent(ListExperienceMainActivity.this,ListVideoExperienceMainActivity.class);
							startActivity(ex2);
							break;

						default:
							Effects.getInstance().playSound(Effects.SOUND_1);
							break;
						}
						/*Intent ex= new Intent(ListLessonListenMainActivity.this,LessonListen.class);
						ex.putExtra("data","a"+num);
						startActivity(ex);*/
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
