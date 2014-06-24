package com.hominhchung.reading.comic;

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
import com.hominhchung.home.Effects;
import com.hominhchung.reading.comic.content.LessionComic;

public class ListComicMainActivity extends Activity {
	ArrayList<ItemDetails> results;
	ItemDetails item_details;
	int i=1;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_list);
        
        //Sound Effects
      	Effects.getInstance().init(this);
        
        ArrayList<ItemDetails> image_details = GetSearchResults();
        
        final ListView lv1 = (ListView) findViewById(R.id.list_view);
        lv1.setAdapter(new ItemListBaseAdapter(this, image_details));
        
        lv1.setOnItemClickListener(new OnItemClickListener() {
        	@Override
        	public void onItemClick(AdapterView<?> a, View v, int position, long id) { 
        		Object o = lv1.getItemAtPosition(position);
            	ItemDetails obj_itemDetails = (ItemDetails)o;

				Effects.getInstance().playSound(Effects.SOUND_1);
				
            	Toast.makeText(ListComicMainActivity.this, "Topic : " + " " + obj_itemDetails.getName(), Toast.LENGTH_LONG).show();
        		Intent ex= new Intent(ListComicMainActivity.this,LessionComic.class);
        		int num=position+1;
				ex.putExtra("data",num+"");
				ex.putExtra("title",obj_itemDetails.getName());
				startActivity(ex);
				Effects.getInstance().playSound(Effects.SOUND_1);
        	}  
        });
    }
    
    private ArrayList<ItemDetails> GetSearchResults(){
    	results = new ArrayList<ItemDetails>();
    	
    	try{
			getItemFromXML(this);
		}catch (XmlPullParserException e){
		}catch (IOException e){
		}
    	return results;
    }
    public void getItemFromXML(Activity activity) throws XmlPullParserException, IOException{
		StringBuffer stringBuffer1 = new StringBuffer();
		StringBuffer stringBuffer2 = new StringBuffer();
		Resources res = activity.getResources();
		XmlResourceParser xpp = res.getXml(R.xml.comic);
		xpp.next();
		int eventType = xpp.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT){
			if (eventType == XmlPullParser.START_TAG){
				if (xpp.getName().equals("lesson")){
					item_details = new ItemDetails();
			    	item_details.setName(xpp.getAttributeValue(null, "en_title").toString());
			    	item_details.setItemDescription(xpp.getAttributeValue(null, "local_title").toString());
			    	item_details.setPrice(xpp.getAttributeValue(null, "id").toString());
			    	item_details.setImageNumber(i++);
			    	results.add(item_details);
				}
			}
			eventType = xpp.next();
		}
	}
    
}