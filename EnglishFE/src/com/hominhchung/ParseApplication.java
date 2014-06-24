package com.hominhchung;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;
import android.app.Application;

public class ParseApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();

		// Thêm mã khởi tạo ở đây
		Parse.initialize(this, getString(R.string.YOUR_APPLICATION_ID), getString(R.string.YOUR_CLIENT_KEY));

		ParseUser.enableAutomaticUser();
		ParseACL defaultACL = new ParseACL();

		// Các đối tượng là cá nhân theo mặc định
		defaultACL.setPublicReadAccess(true);

		ParseACL.setDefaultACL(defaultACL, true);
	}

}
