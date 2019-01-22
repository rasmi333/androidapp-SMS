package com.examples.smstest;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button myButton1;
    Button myButton2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myButton1= (Button) findViewById(R.id.button1);
        myButton1.setOnClickListener(this);
        myButton2 = (Button) findViewById(R.id.button2);
        myButton2.setOnClickListener(this);
    }
    @Override
    public void onClick (View v) {
        if(v==myButton1)
        Send_SMS();
        else if(v==myButton2)
            Start_SMS_App();

        //Start_SMS_App();
    }
    private void Send_SMS() {
        String phoneNo = "4094449429";
        String message = "Hello, how are you today?";

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, message, null, null);
            Toast.makeText(getApplicationContext(), "SMS sent", Toast.LENGTH_LONG).show();
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "SMS failed", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
    private void Start_SMS_App() {
        Intent smsIntent = new Intent(Intent.ACTION_VIEW);

        smsIntent.setData(Uri.parse("smsto:"));
        smsIntent.setType("vnd.android-dir/mms-sms");
        smsIntent.putExtra("address", new String ("4094449429"));
        smsIntent.putExtra("sms_body", "Hello! What's up!");

        try {
            startActivity(smsIntent);
            finish();
        }
        catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "SMS failed", Toast.LENGTH_SHORT).show();
        }
    }
}
