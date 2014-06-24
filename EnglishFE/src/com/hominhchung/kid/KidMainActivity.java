package com.hominhchung.kid;

import android.app.ProgressDialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.hominhchung.R;

public class KidMainActivity extends FragmentActivity {

	// create object of FragmentPagerAdapter
	SectionsPagerAdapter mSectionsPagerAdapter;
	static int num = 0;
	// viewpager to display pages
	ViewPager mViewPager;
	int numTab = 4;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.kid_layout);

		Bundle localBundle = getIntent().getExtras();
		if (localBundle != null)
			num = localBundle.getInt("data");

		// Create the adapter that will return a fragment for each of the five
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
	}

	

	/**
	 * A FragmentPagerAdapter that returns a fragment corresponding to one of
	 * the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			switch (position) {
			//Sing
			case 0:
				// getItem is called to instantiate the fragment for the given
				// page.
				// Return a DummySectionFragment (defined as a static inner
				// class
				// below) with the page number as its lone argument.
				Fragment fragment1 = new DummySectionFragment();
				Bundle args1 = new Bundle();
				args1.putString(DummySectionFragment.ARG_SECTION_NUMBER,
						"www1/B01.html");
				fragment1.setArguments(args1);
				return fragment1;
			//Grammar
			case 1:
				Fragment fragment2 = new DummySectionFragment();
				Bundle args2 = new Bundle();
				args2.putString(DummySectionFragment.ARG_SECTION_NUMBER,
						"www/");
				fragment2.setArguments(args2);
				return fragment2;
			//Learning
			case 2:
				Fragment fragment3 = new DummySectionFragment();
				Bundle args3 = new Bundle();
				args3.putString(DummySectionFragment.ARG_SECTION_NUMBER,
						"www/learning.html");
				fragment3.setArguments(args3);
				return fragment3;
			//Stories	
			case 3:
				Fragment fragment4 = new DummySectionFragment();
				Bundle args4 = new Bundle();
				args4.putString(DummySectionFragment.ARG_SECTION_NUMBER,
						"www/fairy tales.html");
				fragment4.setArguments(args4);
				return fragment4;
			//Poems
			case 4:
				Fragment fragment5 = new DummySectionFragment();
				Bundle args5 = new Bundle();
				args5.putString(DummySectionFragment.ARG_SECTION_NUMBER,
						"www/poemschild.html");
				fragment5.setArguments(args5);
				return fragment5;
			}
			return null;

		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return numTab;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Resources res = getResources();
			String[] title_section = res.getStringArray(R.array.title_section);
			
			switch (position) {
			case 0:
				return title_section[0].toUpperCase();
			case 1:
				return title_section[1].toUpperCase();
			case 2:
				return title_section[2].toUpperCase();
			case 3:
				return title_section[3].toUpperCase();
			case 4:
				return title_section[4].toUpperCase();
			}
			return null;
		}
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class DummySectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		
		WebView mWeb;
		ProgressDialog mProgress;
		public static final String ARG_SECTION_NUMBER = "section_number";

		public DummySectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			
			View view = inflater.inflate(
					R.layout.module_webview_layout, container, false);
			mWeb = (WebView) view.findViewById(R.id.webView1);
			
			
			// set Javascript
	        WebSettings settings = mWeb.getSettings();
	        settings.setJavaScriptEnabled(true);
	       
	        // the init state of progress dialog
	        mProgress = ProgressDialog.show(getActivity(), "Loading", "Please wait for a moment...");
	        
	        String url = getArguments().getString(ARG_SECTION_NUMBER);
	        
	        final String DEFAULT_URL = "file:///android_asset/"+url;
	        // Add a WebViewClient, which actually handles loading data from web
	        mWeb.setWebViewClient(new WebViewClient() {
	        	// Load the url
	            public boolean shouldOverrideUrlLoading(WebView view, String url) {
	                view.loadUrl(url);
	                return true;
	            }
	            // When finish loading page
	            public void onPageFinished(WebView view, String url) {
	                if(mProgress.isShowing()) {
	                    mProgress.dismiss();
	                }
	            }
	        });
	        // Set url for webview to load
	        mWeb.getSettings().setJavaScriptEnabled(true);
	        mWeb.getSettings().setBuiltInZoomControls(true);
	        mWeb.loadUrl(DEFAULT_URL);
			
			return view;
		}
		
	}

}