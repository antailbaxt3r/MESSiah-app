package com.example.messapp2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button page1,page2butt;
    TextView earnings;
    DatabaseReference dr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseMessaging.getInstance().subscribeToTopic("new_order");
        earnings=(TextView)findViewById(R.id.amount_mess_app);
        dr= FirebaseDatabase.getInstance().getReference();
        Calendar cr=Calendar.getInstance();
        cr.add(Calendar.HOUR,-4);
        String dateinformat=String.valueOf(cr.get(Calendar.YEAR))+"-"+String.valueOf(cr.get(Calendar.MONTH)+1)+"-"+String.valueOf(cr.get(Calendar.DATE));
        dr=dr.child("ANC").child("ORDERS").child(dateinformat).child("TOTAL EARNINGS");
        dr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Integer temp=dataSnapshot.getValue(Integer.class);
                earnings.setText("Today's earning: Rs."+String.valueOf(temp));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        page1=(Button)findViewById(R.id.p1);
        page1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent messIntent = new Intent(MainActivity.this, PAGE1.class);
                startActivity(messIntent);
            }
            });
        page2butt=(Button)findViewById(R.id.p2);
        page2butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ordersIntent=new Intent(MainActivity.this,page2.class);
                startActivity(ordersIntent);
            }
        });
    }
}
