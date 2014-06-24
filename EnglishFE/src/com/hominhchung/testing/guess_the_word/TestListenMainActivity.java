package com.hominhchung.testing.guess_the_word;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hominhchung.R;

public class TestListenMainActivity extends Activity implements TextToSpeech.OnInitListener{
	
	private TextToSpeech tts;
	ImageView img;
	Button btn_nghe,btn_xacnhan,btn_next,btn_noi,btn_kq;
	TextView tv,kq;
	EditText et;
	ArrayList<String> en_title;
	int[] ds_hinh = { R.drawable.clothes_coat, R.drawable.clothes_dress, R.drawable.clothes_gloves,
			R.drawable.clothes_jacket, R.drawable.clothes_jeans, R.drawable.clothes_jumper,
			R.drawable.clothes_pajamas, R.drawable.clothes_shirt, R.drawable.clothes_shoes,
			R.drawable.clothes_skirt, R.drawable.clothes_slacks, R.drawable.clothes_uniform };
	//String[] text={"panda","bear","elephant","hippo","monkey","rhino","tiger"};
	int pos = 0, key, max = 0,caudung=0;
	protected static final int RESULT_SPEECH = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.testing_guesstheword_layout);
		
		tts = new TextToSpeech(this, this);
		
		img = (ImageView) findViewById(R.id.imageView2);
		btn_nghe = (Button) findViewById(R.id.btn_nghe);
		btn_noi = (Button) findViewById(R.id.btn_noi);
		btn_xacnhan = (Button) findViewById(R.id.btn_xacnhan);
		btn_kq = (Button) findViewById(R.id.btn_kq);
		btn_next = (Button) findViewById(R.id.btn_next);
		et = (EditText) findViewById(R.id.editText1);
		tv = (TextView) findViewById(R.id.textView1);
		kq = (TextView) findViewById(R.id.kq);

		en_title = new ArrayList<String>();
		try {
			getItemFromXML(this);
		} catch (XmlPullParserException e) {
		} catch (IOException e) {
		}
		
		capnhatCauDung();
		
		btn_nghe.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(pos<ds_hinh.length)
				{
					docTuVung(en_title.get(pos).toString().trim());
					
				}	
			}
		});
		
		btn_noi.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(
						RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

				intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");

				try {
					startActivityForResult(intent, RESULT_SPEECH);
					et.setText("");
				} catch (ActivityNotFoundException a) {
					Toast t = Toast.makeText(getApplicationContext(),
							"Ops! Your device doesn't support Speech to Text",
							Toast.LENGTH_SHORT);
					t.show();
				}	
			}
		});
		
		btn_xacnhan.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(pos<ds_hinh.length)
				{
					if(et.getText().toString().trim().equalsIgnoreCase(en_title.get(pos).toString().trim()))
					{
						caudung++;
						capnhatCauDung();
						Toast.makeText(getApplicationContext(), "ĐÚNG!", Toast.LENGTH_SHORT).show();
						btn_next.setText("TIẾP THEO");
						btn_xacnhan.setEnabled(false);
					}
					else{
						Toast.makeText(getApplicationContext(), "SAI!", Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
		
		btn_next.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				pos++;				
				if(pos<ds_hinh.length)
				{

					img.setImageResource(ds_hinh[pos]);
					et.setText("");
					kq.setText("HÃY NGHE RÕ");
					btn_next.setText("BỎ QUA");
				}
				else
				{
					btn_next.setEnabled(false);					
				}
				btn_xacnhan.setEnabled(true);
				btn_kq.setEnabled(true);
			}
		});
		
		btn_kq.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kq.setText("KẾ QUẢ: "+en_title.get(pos).toString().trim());
				btn_kq.setEnabled(false);
			}
		});
		
	}
	
	public void capnhatCauDung(){		
		tv.setText("CÂU ĐÚNG "+caudung+"/"+ds_hinh.length);
	}
	

	@Override
    public void onDestroy() {
        // Don't forget to shutdown tts!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
 
    @Override
    public void onInit(int status) {
 
        if (status == TextToSpeech.SUCCESS) {
 
            int result = tts.setLanguage(Locale.US);
 
            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {                
            	//docTuVung("Welcome A B C bakery!");
            }
 
        } else {
            Log.e("TTS", "Initilization Failed!");
        }
 
    }
 
    private void docTuVung(String tuvung){
        tts.speak(tuvung, TextToSpeech.QUEUE_FLUSH, null);    	
    }
    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
		case RESULT_SPEECH: {
			if (resultCode == RESULT_OK && null != data) {

				ArrayList<String> text = data
						.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

				et.setText(text.get(0));
			}
			break;
		}

		}
	}
    
    /*---------------------------------------XML--------------------------------------*/
	public void getItemFromXML(Activity activity)
			throws XmlPullParserException, IOException {
		StringBuffer stringBuffer1 = new StringBuffer();
		StringBuffer stringBuffer2 = new StringBuffer();

		Resources res = activity.getResources();
		XmlResourceParser xpp = res.getXml(R.xml.vocabulary_picture);
		xpp.next();
		int eventType = xpp.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			if (eventType == XmlPullParser.START_TAG) {
				if (xpp.getName().equals("item1")) {

					en_title.add(xpp.getAttributeValue(null, "name").toString());
					max++;
				}

			}
			eventType = xpp.next();
		}

	}
}
