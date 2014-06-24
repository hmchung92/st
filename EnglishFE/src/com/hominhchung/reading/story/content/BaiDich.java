package com.hominhchung.reading.story.content;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.hominhchung.R;

public class BaiDich extends Fragment {
	ProgressDialog mProgress;
	@Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
		Bundle bundle = this.getArguments();
        String myValue = bundle.getString("message");
        final String DEFAULT_URL = "file:///android_asset/reading/story/topic"+myValue+"-2.html";
		View android = inflater.inflate(R.layout.module_webview_layout, container, false);
		((WebView)android.findViewById(R.id.webView1)).getSettings().setJavaScriptEnabled(true);
		((WebView)android.findViewById(R.id.webView1)).getSettings().setBuiltInZoomControls(true);
        ((WebView)android.findViewById(R.id.webView1)).loadUrl(DEFAULT_URL);
	        return android;
}}

