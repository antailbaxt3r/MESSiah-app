package com.MESSiahPackage.cmp;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements Runnable{
    int latestVersion,currentVersion=11;
    TextView loadingText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Thread versthread = new Thread(MainActivity.this);

        versthread.start();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadingText=(TextView)findViewById(R.id.ltv);

        new CountDownTimer(60000,1000)
        {
            @Override
            public void onTick(long l) {
                int secondsRemaining=(int)(l/1000);
                if(secondsRemaining>57)
                {

                }
                else if(secondsRemaining>50)
                    loadingText.setText("Checking for updates");
                else if(secondsRemaining>48)
                    loadingText.setText("Ummm.. this is taking more time that usual");
                else if(secondsRemaining>45)
                    loadingText.setText("You may have poor internet connection......");
                else if(secondsRemaining>45)
                    loadingText.setText("Your internet connection is definitely bad");
                else if(secondsRemaining>42)
                    loadingText.setText("Please don't leave a bad review :(");
                else
                    loadingText.setText("Let Ajinkya: 9819762250 or Raghav: 6387331998 know about this");



            }

            public void onFinish()
            {

            }

        }.start();




    }



    @Override
    public void run() {

        DatabaseReference vers= FirebaseDatabase.getInstance().getReference().child("MESSiahVersion");

        vers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    latestVersion = dataSnapshot.getValue(Integer.class);

                    if (latestVersion != currentVersion)
                        toStoreData.cversion = false;
                    Intent loadIntent=new Intent(MainActivity.this,khulasa.class);
                    startActivity(loadIntent);



                }
                catch (Exception e)
                {

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}


