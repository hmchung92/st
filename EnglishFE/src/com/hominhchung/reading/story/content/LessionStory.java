package com.hominhchung.reading.story.content;

import android.app.ActionBar;
import android.content.BroadcastReceiver;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.hominhchung.R;

public class LessionStory extends FragmentActivity {
	
	private ViewPager pager;
	private TabsAdapter mTabsAdapter;
	private BroadcastReceiver updateReceiver;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		pager = new ViewPager(this);
		pager.setId(R.id.pager);
		setContentView(pager);
		
		final ActionBar bar = getActionBar();
		bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		//NAVIGATION_MODE_TABS
		Bundle localBundle = getIntent().getExtras();
        String str1 = null;
        if (localBundle != null)
        	str1 = localBundle.getString("data");
        if (str1 != null);
        //final String DEFAULT_URL = "file:///android_asset/"+str1;
        //localWebView.loadUrl(DEFAULT_URL);
		
		Bundle bundle = new Bundle();
		// Dua du lieu rieng le vao bundle
		
		
		bundle.putString("message", str1);
		mTabsAdapter = new TabsAdapter(this, pager);
		mTabsAdapter.addTab(bar.newTab().setText("Bài mẫu"), BaiMau.class, bundle);
		mTabsAdapter.addTab(bar.newTab().setText("Bài dịch"), BaiDich.class, bundle);
		
	}

}
