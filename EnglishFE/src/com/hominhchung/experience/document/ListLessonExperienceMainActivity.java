package com.hominhchung.experience.document;

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
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.hominhchung.R;

public class ListLessonExperienceMainActivity extends Activity {
	
	// List view
	private ListView lv;
	
	// Listview Adapter
	ArrayAdapter<String> adapter;
	
	// Search EditText
	EditText inputSearch;
	
	int i=0;
	
	// ArrayList for Listview
	ArrayList<HashMap<String, String>> productList;

	ArrayList<String> data_text;
	ArrayList<String> local_text;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_list_search);
        
        
        lv = (ListView) findViewById(R.id.list_view);
        // Enabling Fast Scrolling
     	lv.setFastScrollEnabled(true);
        
        inputSearch = (EditText) findViewById(R.id.inputSearch);
        
        data_text = new ArrayList<String>();
        local_text = new ArrayList<String>();
        
        try{
			getItemFromXML(this);
		}catch (XmlPullParserException e){
		}catch (IOException e){
		} 
        
        // Adding items to listview
        adapter = new ArrayAdapter<String>(this, R.layout.module_list_row_4, R.id.tv_namelocal, local_text);
        lv.setAdapter(adapter);
        
        lv.setOnItemClickListener(
				new AdapterView.OnItemClickListener() {
					public void onItemClick(AdapterView<?> arg0,
							View arg1, 
							int arg2,
							long arg3) {
						//đối số arg2 là vị trí phần tử trong Data Source (arr)
						Toast.makeText(getApplicationContext(), local_text.get(arg2), Toast.LENGTH_LONG).show();
						Intent ex= new Intent(ListLessonExperienceMainActivity.this,ExperienceActivity.class);
						int u = arg2+1;
						ex.putExtra("data",data_text.get(arg2));
						startActivity(ex);
						
					}
				});
		
        /**
         * Enabling Search Filter
         * */
        inputSearch.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
				// When user changed the Text
				ListLessonExperienceMainActivity.this.adapter.getFilter().filter(cs);	
			}
			
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub							
			}
		});
    }
    public void getItemFromXML(Activity activity) throws XmlPullParserException, IOException{
		StringBuffer stringBuffer1 = new StringBuffer();
		StringBuffer stringBuffer2 = new StringBuffer();
		Resources res = activity.getResources();
		XmlResourceParser xpp = res.getXml(R.xml.experience);
		xpp.next();
		int eventType = xpp.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT){
			if (eventType == XmlPullParser.START_TAG){
				if (xpp.getName().equals("lesson")){
					i++;
					if(i<10){
						local_text.add("0"+i+". "+xpp.getAttributeValue(null, "local_text").toString());
					}
					else{
						local_text.add(i+". "+xpp.getAttributeValue(null, "local_text").toString());
					}
					data_text.add(xpp.getAttributeValue(null, "data_text").toString());
				}
			}
			eventType = xpp.next();
		}
	}
}
