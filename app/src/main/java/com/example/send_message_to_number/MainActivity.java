package com.example.send_message_to_number;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.btnsumit);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:03033942021"));

                if (ActivityCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(callIntent);



                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/i am here ");
                i.putExtra(Intent.EXTRA_EMAIL , new String[]{"saifotho25@gmail.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, "Subject of Your Email");
                i.putExtra(Intent.EXTRA_TEXT , "The HTML/TEXT body content of the email");
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(MainActivity.this, "No email client configured. Please check.", Toast.LENGTH_SHORT).show();
                }


                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage("03033942021", null, "hi", null, null);


            }

        });


    }
}