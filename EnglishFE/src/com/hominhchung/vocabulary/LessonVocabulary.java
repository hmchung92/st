package com.hominhchung.vocabulary;

import com.hominhchung.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class LessonVocabulary extends Activity {
	/** Called when the activity is first created. */
	/**
	 * Khai báo giá trị
	 */
	WebView mWeb;
	ProgressDialog mProgress;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mWeb = new WebView(this);
		setContentView(mWeb);

		// cho phép sự dụng Javascript
		WebSettings settings = mWeb.getSettings();
		settings.setJavaScriptEnabled(true);

		// Hiển thị trạng thái trong khi load dữ liệus
		mProgress = ProgressDialog.show(this, getString(R.string.loading),
				getString(R.string.please_wait_for_a_moment));

		// Khai báo Bundle
		Bundle localBundle = getIntent().getExtras();
		String str1 = null;
		String type = null;
		if (localBundle != null)
			// Lấy dữ liệu riêng lẻ ra khỏi Bundle
			str1 = localBundle.getString("data");
			setTitle(localBundle.getString("title"));
		if (str1 != null);
		
		// Khai báo đường dẫn để đọc file 
		final String DEFAULT_URL = "file:///android_asset/" + str1;
		
		// Thêm một WebViewClient, xử lý tải dữ liệu từ web
		mWeb.setWebViewClient(new WebViewClient() {
			// Load đường dẫn url
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}

			// Khi kết thúc trang tải
			public void onPageFinished(WebView view, String url) {
				if (mProgress.isShowing()) {
					mProgress.dismiss();
				}
			}
		});

		// Thiết lập địa chỉ cho webview tải
		mWeb.getSettings().setJavaScriptEnabled(true); // Dùng JavaScript
		mWeb.getSettings().setBuiltInZoomControls(true);// Cho phép zoom 
		mWeb.loadUrl(DEFAULT_URL);
	}

}
