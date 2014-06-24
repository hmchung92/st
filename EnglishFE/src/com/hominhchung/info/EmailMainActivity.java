package com.hominhchung.info;
 
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.hominhchung.R;
 
public class EmailMainActivity extends Activity implements OnClickListener {
/** Called when the activity is first created. */
   @Override
   public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.email_layout);
    Button mail = (Button)findViewById(R.id.button1);
    mail.setOnClickListener(this);
    Button mail2 = (Button)findViewById(R.id.button2);
    mail2.setOnClickListener(this);
   }
 
   @Override
   public void onClick(View v) {
    switch(v.getId())
    {
           case R.id.button1:
              Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
              String[] recipients = new String[]{"mymail@email.com", "",};
              emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, recipients);
              emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Sample mail");
              emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "This is a sample mail..");
              emailIntent.setType("text/plain");
              startActivity(Intent.createChooser(emailIntent, "Send mail client :"));
              finish();
          break;
 
           case R.id.button2:
              Intent emailIntent2 = new Intent(android.content.Intent.ACTION_SEND);
              String[] recipients2 = new String[]{"mymail@email.com", "",};
              emailIntent2.putExtra(android.content.Intent.EXTRA_EMAIL, recipients2);
              emailIntent2.putExtra(android.content.Intent.EXTRA_SUBJECT, "Sample mail");
              emailIntent2.putExtra(android.content.Intent.EXTRA_TEXT,Html.fromHtml("<b><i>"+"new"+"</i></b><br><br><b><i>"+"html data"+"</i></b><br>"));
              emailIntent2.setType("text/html");
              startActivity(Intent.createChooser(emailIntent2, "Send mail client :"));
              finish();
          break;
    }
    }
}