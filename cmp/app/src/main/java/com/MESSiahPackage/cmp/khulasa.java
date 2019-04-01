package com.MESSiahPackage.cmp;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

//import com.crashlytics.android.Crashlytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class khulasa extends AppCompatActivity {

    CardView aMessButton,cMessButton,dMessButton;
    Button contactsbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khulasa);
        //Crashlytics.log("khulasa has been opened");

        if(!toStoreData.cversion)
        {
            new AlertDialog.Builder(khulasa.this)
                    .setMessage("Please UPDATE THE APP to the latest version")
                    .setCancelable(false)
                    .setPositiveButton("Update now", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                            try {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                            } catch (ActivityNotFoundException anfe) {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                            }
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                finishAffinity();
                            }
                            ActivityCompat.finishAffinity(khulasa.this);


                        }
                    })
                    .setNegativeButton("Update Later", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                finishAffinity();
                            }
                            ActivityCompat.finishAffinity(khulasa.this);

                        }
                    })
                    .show();
        }
        aMessButton= findViewById(R.id.aMessButtonId);
        cMessButton= findViewById(R.id.CNCid);
        dMessButton= findViewById(R.id.DNCid);
        contactsbutton= findViewById(R.id.contacts);
        cMessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(khulasa.this,"coming soon",Toast.LENGTH_SHORT).show();
            }
        });
        dMessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(khulasa.this,"coming soon",Toast.LENGTH_SHORT).show();
            }
        });
        contactsbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent con = new Intent(khulasa.this, contacts.class);
                startActivity(con);

            }
        });
        aMessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal=Calendar.getInstance();
                if(cal.get(Calendar.HOUR_OF_DAY)>=23 || (cal.get(Calendar.HOUR_OF_DAY)<2&&cal.get(Calendar.MINUTE)<50)||cal.get(Calendar.HOUR_OF_DAY)<1||true) {


                    Intent aMessIntent = new Intent(khulasa.this, menu.class);
                    startActivity(aMessIntent);


                }
                else
                    Toast.makeText(khulasa.this,"NC not open right now!!",Toast.LENGTH_SHORT).show();
            }});

    }

    @Override
    public void onBackPressed()
    {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            finishAffinity();
                        }
                        ActivityCompat.finishAffinity(khulasa.this);
                    }
                })
                .setNegativeButton("No", null)
                .show();

    }


   /* public boolean isOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int     exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        }
        catch (IOException e)          { e.printStackTrace(); }
        catch (InterruptedException e) { e.printStackTrace(); }

        return false;
    }*/
}
