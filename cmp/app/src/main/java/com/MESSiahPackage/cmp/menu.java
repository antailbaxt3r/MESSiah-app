package com.MESSiahPackage.cmp;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class menu extends AppCompatActivity {
    FirebaseDatabase fd;
    DatabaseReference dr;
    ListView menuListView;
    ArrayList<String> myArrayList;
    ArrayAdapter<String> myArrayAdapter;
    TextView totalPrice;
    Button proceedButton,checkPrvOrder,clrorder;
    private ProgressBar pgsbar;
   public EditText search;



    @Override
    public void onBackPressed(){

        Intent backintent=new Intent(menu.this,khulasa.class);
        startActivity(backintent);
        finish();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        search= findViewById(R.id.SearchBar);
        search.setFocusable(false);
        checkPrvOrder= findViewById(R.id.ViewCurrentOrder);
        checkPrvOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent currentOrderIntent=new Intent(menu.this,Orders.class);
                startActivity(currentOrderIntent);
            }
        });
        clrorder=(Button)findViewById(R.id.clearId);
        clrorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toStoreData.totalPrice=0;
                toStoreData.finalOrder.clear();
                toStoreData.totalNumberOfItems=0;
                totalPrice=(TextView)findViewById(R.id.TotalPrice);
                String tp="Total amount = Rs. ";
                tp=tp+String.valueOf(toStoreData.totalPrice);
                totalPrice.setText(tp);


            }
        });
        proceedButton=(Button)findViewById(R.id.proceed);
        pgsbar=(ProgressBar)findViewById(R.id.pBar);
        proceedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent proceedIntent=new Intent(menu.this,checkout.class);
                startActivity(proceedIntent);
            }

        });
        totalPrice=(TextView)findViewById(R.id.TotalPrice);
        String tp="Total amount = Rs. ";
        tp=tp+String.valueOf(toStoreData.totalPrice);
        totalPrice.setText(tp);
        myArrayList=new ArrayList<String>();
        fd = FirebaseDatabase.getInstance();
        dr = fd.getReference();
        dr=dr.child("A NC");
        dr=dr.child("MENU");
        dr=dr.child("AVAILABLE");
        menuListView=(ListView)findViewById(R.id.listViewId);
        myArrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,myArrayList);
        menuListView.setAdapter(myArrayAdapter);
        dr.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String temp=dataSnapshot.getValue(String.class);
                myArrayList.add(temp);
                myArrayAdapter.notifyDataSetChanged();
                pgsbar.setVisibility(View.GONE);
                search.setFocusableInTouchMode(true);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                String temp=dataSnapshot.getValue(String.class);
                myArrayList.remove(temp);
                myArrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

        menuListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){


            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                onMenuItemClick(view);
            }
        });
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                menu.this.myArrayAdapter.getFilter().filter(s);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }




    public void onMenuItemClick(View v)
    {
        TextView tv=(TextView)v;
        String temp;
        temp=tv.getText().toString().trim();
        toStoreData.lastMenuItemClicked=temp;
        Intent quantityPage=new Intent(menu.this,Main2Activity.class);
        startActivity(quantityPage);

    }

}
