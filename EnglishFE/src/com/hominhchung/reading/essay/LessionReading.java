package com.hominhchung.reading.essay;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class LessionReading extends Activity {
	 /** Called when the activity is first created. */
	WebView mWeb;
    ProgressDialog mProgress;
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	/*super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_layout);
        
        
        WebView localWebView = (WebView)findViewById(R.id.webView1);
        Bundle localBundle = getIntent().getExtras();
        String str1 = null;
        String type = null;
        if (localBundle != null)
        	str1 = localBundle.getString("data");
        if (str1 != null);
        try
        {
          String str2 = "grammar/a"+str1+".htm";
          InputStream localInputStream = getAssets().open(str2, 1);
          byte[] arrayOfByte = new byte[localInputStream.available()];
          localInputStream.read(arrayOfByte);
          localInputStream.close();
          localWebView.loadDataWithBaseURL(null, new String(arrayOfByte), "text/html", "utf-8", null);
          return;
        }
        catch (IOException localIOException)
        {
          while (true)
            localIOException.printStackTrace();
        }
        //String str2 = "essays/"+str1+".html";
        final String DEFAULT_URL = "file:///android_asset/"+str1;
        //final String DEFAULT_URL1 = "file:///android_asset/essays/topic1.html";
        localWebView.getSettings().setJavaScriptEnabled(true);
        localWebView.getSettings().setBuiltInZoomControls(true);
        localWebView.loadUrl(DEFAULT_URL);*/
        
        super.onCreate(savedInstanceState);
        
        // Set webview as main content only
        mWeb = new WebView(this);
        setContentView(mWeb);
       
        // set Javascript
        WebSettings settings = mWeb.getSettings();
        settings.setJavaScriptEnabled(true);
       
       // the init state of progress dialog
        mProgress = ProgressDialog.show(this, "Loading", "Please wait for a moment...");
        Bundle localBundle = getIntent().getExtras();
        String str1 = null;
        String type = null;
        if (localBundle != null)
        	str1 = localBundle.getString("data");
        	setTitle(localBundle.getString("title"));
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
        mWeb.loadUrl(DEFAULT_URL);
}
        
        /*super.onCreate(savedInstanceState);
        Bundle localBundle = getIntent().getExtras();
        String str1 = null;
        String type = null;
        if (localBundle != null)
        	str1 = localBundle.getString("data");
        if (str1 != null);
        WebView webView = new WebView(this);
        webView.setClickable(true);
        webView.setFocusableInTouchMode(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        final String DEFAULT_URL = "file:///android_asset/"+str1;
        webView.loadUrl(DEFAULT_URL);
        WebClientClass webViewClient = new WebClientClass();
        webView.setWebViewClient(webViewClient);
        WebChromeClient webChromeClient=new WebChromeClient();
        webView.setWebChromeClient(webChromeClient);
        setContentView(webView);
    }
    public class WebClientClass extends WebViewClient {
  	  ProgressDialog pd = null;

  	  @Override
  	  public void onPageStarted(WebView view, String url, Bitmap favicon) {
  	   super.onPageStarted(view, url, favicon);
  	   pd = new ProgressDialog(LessionReading.this);
  	   pd.setTitle("Please wait");
  	   pd.setMessage("Page is loading..");
  	   pd.show();
  	  }

  	  @Override
  	  public void onPageFinished(WebView view, String url) {
  	   super.onPageFinished(view, url);
  	   pd.dismiss();
  	  }
  	 }
  	 
  	 public class WebChromeClass extends WebChromeClient{
  	 }*/
}
