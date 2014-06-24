package com.hominhchung.online.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.parse.ParseAnonymousUtils;
import com.parse.ParseUser;

public class RegistryMainActivity extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Determine whether the current user is an anonymous user
		if (ParseAnonymousUtils.isLinked(ParseUser.getCurrentUser())) {
			// If user is anonymous, send the user to LoginSignupActivity.class
			Intent intent = new Intent(RegistryMainActivity.this,
					LoginActivity.class);
			startActivity(intent);
			finish();
		} else {
			// If current user is NOT anonymous user
			// Get current user data from Parse.com
			ParseUser currentUser = ParseUser.getCurrentUser();
			if (currentUser != null) {
				// Send logged in users to Welcome.class
				Intent intent = new Intent(RegistryMainActivity.this, Welcome.class);
				startActivity(intent);
				finish();
			} else {
				// Send user to LoginSignupActivity.class
				Intent intent = new Intent(RegistryMainActivity.this,
						LoginActivity.class);
				startActivity(intent);
				finish();
			}
		}

	}
}
