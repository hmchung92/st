package com.hominhchung.experience.document;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.hominhchung.R;

public class ExperienceActivity extends Activity {
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
        final String DEFAULT_URL = "file:///android_asset/experience/"+str1;
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

   /* private void startWebView(String url) {
	    
		//Create new webview Client to show progress dialog
		//When opening a url or click on link
		
		webView.setWebViewClient(new WebViewClient() {      
	        ProgressDialog progressDialog;
	     
	        //If you will not use this method url links are opeen in new brower not in webview
	        public boolean shouldOverrideUrlLoading(WebView view, String url) {              
	            view.loadUrl(url);
	            return true;
	        }
	   
	        //Show loader on url load
	        public void onLoadResource (WebView view, String url) {
	            if (progressDialog == null) {
	                // in standard case YourActivity.this
	                progressDialog = new ProgressDialog(ExperienceActivity.this);
	                progressDialog.setMessage("Loading...");
	                progressDialog.show();
	            }
	        }
	        public void onPageFinished(WebView view, String url) {
	            try{
	            if (progressDialog.isShowing()) {
	                progressDialog.dismiss();
	                progressDialog = null;
	            }
	            }catch(Exception exception){
	                exception.printStackTrace();
	            }
	        }
	        
	    }); 
	     
	     // Javascript inabled on webview  
	    webView.getSettings().setJavaScriptEnabled(true); 
	    
	    // Other webview options
	    
	    webView.getSettings().setLoadWithOverviewMode(true);
	    webView.getSettings().setUseWideViewPort(true);
	    webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
	    webView.setScrollbarFadingEnabled(false);
	    webView.getSettings().setBuiltInZoomControls(true);
	    
	    
	    
	     String summary = "<html><body>You scored <b>192</b> points.</body></html>";
         webview.loadData(summary, "text/html", null); 
	     
	    
	    //Load url in webview
	    webView.loadUrl(url);
	     
	     
	}
	
	// Open previous opened link from history on webview when back button pressed
	
	@Override
    // Detect when the back button is pressed
    public void onBackPressed() {
        if(webView.canGoBack()) {
            webView.goBack();
        } else {
            // Let the system handle the back button
            super.onBackPressed();
        }
    }*/
}
