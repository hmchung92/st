package com.hominhchung.vocabulary.vocabulary3500;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import com.hominhchung.R;

public class TabVocabularyActivity extends TabActivity {
	
	private static TabVocabularyActivity INSTANCE;
	private TextToSpeech tts;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_vocabulary);
        INSTANCE = this;
        TabHost host = getTabHost(); 
        TabSpec tab1 = host.newTabSpec("tab1");
        tab1.setIndicator("Từ vựng");
        tab1.setContent(new Intent(getApplicationContext(), Vocabulary3500MainActivity.class).putExtra("type", 0));
        
        TabSpec tab2 = host.newTabSpec("tab2");
        tab2.setIndicator("Đã học");
        tab2.setContent(new Intent(getApplicationContext(), Vocabulary3500MainActivity.class).putExtra("type", 1));
        
        TabSpec tab3 = host.newTabSpec("tab3");
        tab3.setIndicator("Kiểm tra");
        tab3.setContent(new Intent(getApplicationContext(), TestListenMainActivity.class));
        
        host.addTab(tab1);
        host.addTab(tab2);
        host.addTab(tab3);
    }
    
    public static TabVocabularyActivity getInstance(){
    	return INSTANCE;
    }
    
    
   
}