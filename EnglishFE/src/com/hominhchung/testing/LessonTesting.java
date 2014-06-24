package com.hominhchung.testing;

import com.hominhchung.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class LessonTesting extends Activity {
	 /** Called when the activity is first created. */
	WebView mWeb;
    ProgressDialog mProgress;
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        mWeb = new WebView(this);
        setContentView(mWeb);
        // set Javascript
        WebSettings settings = mWeb.getSettings();
        settings.setJavaScriptEnabled(true);
       
       // the init state of progress dialog
        mProgress = ProgressDialog.show(this, getString(R.string.loading), getString(R.string.please_wait_for_a_moment));
        Bundle localBundle = getIntent().getExtras();
        String str1 = null;
        String type = null;
        if (localBundle != null)
        	str1 = localBundle.getString("data");
        if (str1 != null);
        final String DEFAULT_URL = "file:///android_asset/"+str1;
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
    }
 
}
