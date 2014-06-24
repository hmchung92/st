package com.hominhchung.online.login;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hominhchung.R;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;


@SuppressLint("NewApi")
public class LoginActivity extends Activity {
	// Declare Variables
	Button loginbutton;
	Button signup;
	String usernametxt;
	String passwordtxt;
	EditText password;
	EditText username;

	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from main.xml
		setContentView(R.layout.online_registry_login);
		// Locate EditTexts in main.xml
		username = (EditText) findViewById(R.id.username);
		password = (EditText) findViewById(R.id.password);

		// Locate Buttons in main.xml
		loginbutton = (Button) findViewById(R.id.login);

		// Login Button Click Listener
		loginbutton.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// Retrieve the text entered from the EditText
				usernametxt = username.getText().toString();
				passwordtxt = password.getText().toString();

				// Send data to Parse.com for verification
				ParseUser.logInInBackground(usernametxt, passwordtxt,
						new LogInCallback() {
							public void done(ParseUser user, ParseException e) {
								if (user != null) {
									// If user exist and authenticated, send user to Welcome.class
									Intent intent = new Intent(
											LoginActivity.this,
											Welcome.class);
									startActivity(intent);
									Toast.makeText(getApplicationContext(),
											"Successfully Logged in!!!",
											Toast.LENGTH_LONG).show();
									finish();
								} else {
									Toast.makeText(
											getApplicationContext(),
											"No such user exist, please signup",
											Toast.LENGTH_LONG).show();
								}
							}
						});
			}
		});
	}
	
/*-------------------------------------------Options menu---------------------------------------------*/	
	//Create an options menu
 	@Override
 	public boolean onCreateOptionsMenu(Menu menu) {

 		// Menu Title
 		menu.add("ĐĂNG KÝ")
 				.setOnMenuItemClickListener(this.AddNewUserClickListener)
 				.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
 		

 		return super.onCreateOptionsMenu(menu);
 	}

 	// Capture menu item click
 	OnMenuItemClickListener AddNewUserClickListener = new OnMenuItemClickListener() {
 		public boolean onMenuItemClick(MenuItem item) {

 			// Open AddEditNotes activity
 			Intent addnote = new Intent(LoginActivity.this, Signup.class);
 			startActivity(addnote);
 			return false;

 		}
 	};
}
