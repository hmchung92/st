package com.hominhchung.writing.essay;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.hominhchung.R;

public class LessonEssay extends FragmentActivity {

    //create object of FragmentPagerAdapter
    SectionsPagerAdapter mSectionsPagerAdapter;
    static int num=0;
    //viewpager to display pages
    ViewPager mViewPager;
    int numTab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adapter_view_pagertabstrip_layout);
        
        Bundle localBundle = getIntent().getExtras();
        if (localBundle != null)
        	num = localBundle.getInt("data");
        	setTitle(localBundle.getString("title"));
        if(num<=30){
        	numTab=3;
        }
        // Create the adapter that will return a fragment for each of the five
        // primary sections of the app.
        mSectionsPagerAdapter = new SectionsPagerAdapter(
                getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * A  FragmentPagerAdapter that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
        	switch (position) {
            case 0:
            	// getItem is called to instantiate the fragment for the given page.
                // Return a DummySectionFragment (defined as a static inner class
                // below) with the page number as its lone argument.
                Fragment fragment1 = new DummySectionFragment();
                Bundle args1 = new Bundle();
                args1.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
                fragment1.setArguments(args1);
                return fragment1;
            case 1:
                Fragment fragment2 = new DummySectionFragment();
                Bundle args2 = new Bundle();
                args2.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
                fragment2.setArguments(args2);
                return fragment2;
            case 2:
                Fragment fragment3 = new DummySectionFragment();
                Bundle args3 = new Bundle();
                args3.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
                fragment3.setArguments(args3);
                return fragment3;
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
            switch (position) {
            case 0:
                return "BÀI MẪU";
            case 1:
                return "BÀI DỊCH";
            case 2:
                return "TỪ VỰNG";
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
        public static final String ARG_SECTION_NUMBER = "section_number";

        public DummySectionFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            // Create a new TextView and set its text to the fragment's section
            // number argument value.
            /*TextView textView = new TextView(getActivity());
            textView.setGravity(Gravity.CENTER);
            textView.setText(Integer.toString(getArguments().getInt(
                    ARG_SECTION_NUMBER)));
            return textView;*/
        	 final String DEFAULT_URL = "file:///android_asset/writing/essays/topic"+num+"-"+getArguments().getInt(ARG_SECTION_NUMBER)+".html";
     		WebView webview = new WebView(getActivity());
     		webview.getSettings().setJavaScriptEnabled(true);
     		webview.getSettings().setBuiltInZoomControls(true);
     		webview.loadUrl(DEFAULT_URL);
     	    return webview;
        }
    }
    

}