package com.hominhchung.online.login;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.hominhchung.R;
import com.parse.ParseUser;

public class Welcome extends Activity {
	
	// Declare Variable
	Button logout;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from singleitemview.xml
		setContentView(R.layout.online_registry_welcome);
		
		// Retrieve current user from Parse.com
		ParseUser currentUser = ParseUser.getCurrentUser();
		
		// Convert currentUser into String
		String struser = currentUser.getUsername().toString();
		
		// Locate TextView in welcome.xml
		TextView txtuser = (TextView) findViewById(R.id.txtuser);
		TextView txtemail = (TextView) findViewById(R.id.txtemail);
		
		// Set the currentUser String into TextView
		txtuser.setText("You are logged in as " + struser);
		txtemail.setText("Email: " + currentUser.getEmail().toString());
		// Locate Button in welcome.xml
		logout = (Button) findViewById(R.id.logout);

		// Logout Button Click Listener
		logout.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// Logout current user
				ParseUser.logOut();
				finish();
			}
		});
	}
}