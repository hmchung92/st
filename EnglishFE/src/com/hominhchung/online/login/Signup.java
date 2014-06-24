package com.hominhchung.online.login;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
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
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class Signup extends Activity {
	// Declare Variables
	Button loginbutton;
	Button signup;
	String usernametxt,passwordtxt,emailtxt,nametxt;
	EditText edit_username,edit_password,edit_name,edit_email;
	EditText username;
	// ProgressDialog
    private ProgressDialog mProgressDialog;
    // ParseUser
    ParseUser user;
	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from main.xml
		setContentView(R.layout.online_resgister_layout);
		// Locate EditTexts in main.xml
		edit_username = (EditText) findViewById(R.id.edit_username);
		edit_password = (EditText) findViewById(R.id.edit_password);
		edit_name = (EditText) findViewById(R.id.edit_name);
		edit_email = (EditText) findViewById(R.id.edit_email);
		// Locate Buttons in main.xml
		signup = (Button) findViewById(R.id.signup);
		// Sign up Button Click Listener
		signup.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				
			    // Retrieve the text entered from the EditText
				usernametxt = edit_username.getText().toString();
				passwordtxt = edit_password.getText().toString();
				nametxt = edit_name.getText().toString();
				emailtxt = edit_email.getText().toString();
				// Force user to fill up the form
				if (usernametxt.equals("") || passwordtxt.equals("") || nametxt.equals("") || emailtxt.equals("")) {
					Toast.makeText(getApplicationContext(),
							"Please complete the sign up form",
							Toast.LENGTH_LONG).show();
				} else {
					// Save new user data into Parse.com Data Storage
					user = new ParseUser();
					user.setUsername(usernametxt);
					user.setPassword(passwordtxt);
					user.setEmail(emailtxt);
					user.signUpInBackground(new SignUpCallback() {

						public void done(ParseException e) {
							if (e == null) {
								// Show a simple Toast message upon successful registration
								/*Toast.makeText(getApplicationContext(),
										"Successfully Signed up, please log in.",
										Toast.LENGTH_LONG).show();*/
								btnExitClick();
							} else {
								Toast.makeText(getApplicationContext(),
										"Thông tin không hợp lệ hoạc đã có người sử dụng", Toast.LENGTH_LONG)
										.show();
							}
						}
						
					});
					
				}

			}
		});

	}
	
/*-------------------------------------------AsyncTask---------------------------------------------*/

	public class GetDataTask extends AsyncTask<Void, Void, Void>
 	  {
 	    public GetDataTask()
 	    {
 	    }

 	    protected Void doInBackground(Void[] paramArrayOfVoid)
 	    {
 	    	
			
 	      return null;
 	    }

 	    protected void onPostExecute(Void paramVoid)
 	    {
 	      super.onPostExecute(paramVoid);
 	      if ((Signup.this.mProgressDialog != null) && (Signup.this.mProgressDialog.isShowing()))
 	    	 Signup.this.mProgressDialog.dismiss();
 	     
 	    }

 	    protected void onPreExecute()
 	    {
 	      super.onPreExecute();
 	     Signup.this.mProgressDialog.setMessage("Please wait...");
 	    Signup.this.mProgressDialog.show();
 	    }
 	  }

/*-------------------------------------------Options menu---------------------------------------------*/	
	//Create an options menu
 	@SuppressLint("NewApi")
	@Override
 	public boolean onCreateOptionsMenu(Menu menu) {

 		// Menu Title
 		menu.add("NHẬP LẠI")
 				.setOnMenuItemClickListener(this.AddNewUserClickListener)
 				.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

 		menu.add("ĐĂNG NHẬP")
			.setOnMenuItemClickListener(this.LoginUserClickListener)
			.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

 		return super.onCreateOptionsMenu(menu);
 	}

 	// Capture menu item click
 	OnMenuItemClickListener AddNewUserClickListener = new OnMenuItemClickListener() {
 		public boolean onMenuItemClick(MenuItem item) {

 			// Open AddEditNotes activity
 			edit_username.setText("");
 			edit_password.setText("");
 			edit_name.setText("");
 			edit_email.setText("");
 			return false;

 		}
 	};
 	OnMenuItemClickListener LoginUserClickListener = new OnMenuItemClickListener() {
 		public boolean onMenuItemClick(MenuItem item) {

 			// Open AddEditNotes activity
 			Intent addnote = new Intent(Signup.this, LoginActivity.class);
 			startActivity(addnote);
 			return false;

 		}
 	};
 	
/*-------------------------------------------Button Click---------------------------------------------*/	
	  public void btnExitClick()
	    {
	    	AlertDialog.Builder alrt=new Builder(this);
	    	alrt.setTitle("Đăng ký thành công");
	    	alrt.setMessage("Chuyển về trang đăng nhập?");
	    	//alrt.setIcon(R.drawable.delete);
	    	alrt.setPositiveButton("Đồng ý", new DialogInterface. OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					Intent addnote = new Intent(Signup.this, LoginActivity.class);
		 			startActivity(addnote);
				}
			});
	    	alrt.setNegativeButton("Không", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					dialog.cancel();
				}
			});
	    	alrt.show();
	    }
	
}
