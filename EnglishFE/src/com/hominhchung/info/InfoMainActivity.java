package com.hominhchung.info;

import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;
 
public class InfoMainActivity extends Activity {
 
 @Override
 protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  //setContentView(R.layout.activity_main);
  TextView infoView = new TextView(this);
  setContentView(infoView);
   
  String info = "System Info:\n";
   
  info += "BOARD: " + Build.BOARD + "\n"
     + "BOOTLOADER: " + Build.BOOTLOADER + "\n"
     + "BRAND: " + Build.BRAND + "\n"
     + "CPU_ABI: " + Build.CPU_ABI + "\n"
     + "CPU_ABI2: " + Build.CPU_ABI2 + "\n"
     + "DEVICE: " + Build.DEVICE + "\n"
     + "DISPLAY: " + Build.DISPLAY + "\n"
     + "FINGERPRINT: " + Build.FINGERPRINT + "\n"
     + "HARDWARE: " + Build.HARDWARE + "\n"
     + "HOST: " + Build.HOST + "\n"
     + "ID: " + Build.ID + "\n"
     + "MANUFACTURER: " + Build.MANUFACTURER + "\n"
     + "MODEL: " + Build.MODEL + "\n"
     + "PRODUCT: " + Build.PRODUCT + "\n"
     + "SERIAL: " + Build.SERIAL + "\n"
     + "TAGS: " + Build.TAGS + "\n"
     + "TIME: " + Build.TIME + "\n"
     + "TYPE: " + Build.TYPE + "\n"
     + "USER: " + Build.USER + "\n"
     + "RadioVersion: " + Build.getRadioVersion() + "\n"
     +" \n";
   
  infoView.setText(info);
 }
 
}