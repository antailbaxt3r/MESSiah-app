package com.MESSiahPackage.cmp;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class PastOrders extends AppCompatActivity {
    FirebaseDatabase fd;
    DatabaseReference pr,or,dr;
    SharedPreferences sp;
    SharedPreferences.Editor spe;
    TextView name,orderid,amount,order,statusTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_orders);
        name= findViewById(R.id.Name);
        orderid= findViewById(R.id.ID);
        amount= findViewById(R.id.Amount);
        order= findViewById(R.id.OrderDetails);
        statusTv= findViewById(R.id.OrderStatus);
        //sp= PreferenceManager.getDefaultSharedPreferences(this);
        //spe=sp.edit();
        final String orderId=toStoreData.pastOrdersOrderId;
        final String orderName=toStoreData.pastOrdersName;
       // Toast.makeText(PastOrders.this,orderId,Toast.LENGTH_SHORT).show();
        fd=FirebaseDatabase.getInstance();
        pr=fd.getReference();
        pr=pr.child("ANC");
        pr=pr.child("ORDERS");
        Calendar cr=Calendar.getInstance();
        cr.add(Calendar.HOUR,-4);
        String dateinformat=String.valueOf(cr.get(Calendar.YEAR))+"-"+String.valueOf(cr.get(Calendar.MONTH)+1)+"-"+String.valueOf(cr.get(Calendar.DATE));
        pr=pr.child(dateinformat);
        or=pr.child("ONGOING");
        dr=pr.child("DONE");
        pr=pr.child("PENDING");
        pr.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                pushTemplate toCheck=dataSnapshot.getValue(pushTemplate.class);
               // Toast.makeText(PastOrders.this,toCheck.order_id,Toast.LENGTH_SHORT).show();
                if(toCheck.order_id.equals(orderId)&&toCheck.name.equals(orderName))
                {
                    PastOrders.this.display(toCheck,"PENDING");
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        or.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                pushTemplate toCheck=dataSnapshot.getValue(pushTemplate.class);
                if(toCheck.order_id.equals(orderId)&&toCheck.name.equals(orderName))
                {
                    PastOrders.this.display(toCheck,"ONGOING");
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        dr.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                pushTemplate toCheck=dataSnapshot.getValue(pushTemplate.class);
                if(toCheck.order_id.equals(orderId)&&toCheck.name.equals(orderName))
                {
                    PastOrders.this.display(toCheck,"COMPLETED");
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }



    public void display(pushTemplate pt,String status)
    {
        name.setText(pt.name);
        amount.setText(String.valueOf(pt.paid_Rs));
        orderid.setText(pt.order_id);
        statusTv.setText(status);
        order.setText(pt.order);

     }







}
