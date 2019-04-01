package com.MESSiahPackage.cmp;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import static android.support.constraint.Constraints.TAG;


public class checkout extends AppCompatActivity implements Runnable{

    ListView finalOrderList;
    ArrayAdapter<String> finalArrayAdapter;
    Button proceedButton;
    TextView finalTotalPrice;
    EditText nameCheckout;
    CheckBox cb;
    Integer totalEarningsToday=0;
    Random r = new Random();
    int rint = r.nextInt(899)+100;
    String upiIDstring;
    boolean bbutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_checkout);




        FirebaseDatabase fd = FirebaseDatabase.getInstance();
        DatabaseReference dr = fd.getReference();
        dr = dr.child("ANC").child("ORDERS");
        Calendar cr = Calendar.getInstance();
        cr.add(Calendar.HOUR, -4);

        String dateinformat = String.valueOf(cr.get(Calendar.YEAR)) + "-" + String.valueOf(cr.get(Calendar.MONTH)+1) + "-" + String.valueOf(cr.get(Calendar.DATE));
        dr = dr.child(dateinformat);
        final DatabaseReference upiID=fd.getReference().child("ANC").child("upiID");


        upiID.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                upiIDstring=dataSnapshot.getValue(String.class);
                if(upiID==null)
                    Toast.makeText(checkout.this,"Error! Please inform Raghav/Ajinkya - Datils on contacts page",Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        dr.child("TOTAL EARNINGS").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                totalEarningsToday=dataSnapshot.getValue(Integer.class);
                if(totalEarningsToday==null)
                    totalEarningsToday=0;


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });












        finalTotalPrice=(TextView)findViewById(R.id.finalTotalPriceId);
        cb=(CheckBox)findViewById(R.id.toBePacked);

        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                    toStoreData.totalPrice+=5*toStoreData.totalNumberOfItems;
                else
                    toStoreData.totalPrice-=5*toStoreData.totalNumberOfItems;
                String temp="Total amount: Rs. ";
                temp=temp+String.valueOf(toStoreData.totalPrice);
                finalTotalPrice.setText(temp);
            }
        });


        nameCheckout=(EditText)findViewById(R.id.EntName);
        finalArrayAdapter=new ArrayAdapter<>(checkout.this,android.R.layout.simple_list_item_1,toStoreData.finalOrder);
        finalOrderList=(ListView)findViewById(R.id.finalOrderListId);
        finalOrderList.setAdapter(finalArrayAdapter);


        final Intent myIntent=new Intent(checkout.this,menu.class);

        String temp="Total amount: Rs. ";
        temp=temp+String.valueOf(toStoreData.totalPrice);
        finalTotalPrice.setText(temp);
        proceedButton=(Button)findViewById(R.id.CheckoutButton);

        proceedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                toStoreData.name=nameCheckout.getText().toString().trim();
                if((nameCheckout.getText().toString().trim()).isEmpty()){
                    Toast.makeText(checkout.this,"Please Enter Name to Continue",Toast.LENGTH_SHORT).show();

                }
                else
                {

                    new AlertDialog.Builder(checkout.this)
                            .setMessage(" **DO NOT** PRESS THE BACK BUTTON DURING PAYMENT AT ALL OR YOUR ORDER WILL NOT BE PROCESSED!!! THE RECEIPT WILL LOAD ON ITS OWN! ")
                            .setCancelable(true)
                            .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {


                                    Thread t=new Thread(checkout.this);
                                    t.setDaemon(true);
                                    t.start();
                                  //  Intent intent = new Intent();
                                    //intent.setAction(Intent.ACTION_VIEW);
                                    //intent.setData(Uri.parse("upi://pay?pa=" + /*"8208327247@upi"*/upiIDstring + "&pn=" + "ANC caterers"
                                      //              + /*"&mc=" + payeeMCC + "&tid=" + trxnID +*/ "&tr=" + Calendar.getInstance().hashCode() + String.valueOf(rint) +"A"
                                         //           + "&tn=" + "FOOD ORDER" + "&am=" + toStoreData.totalPrice + "&cu=" + "INR"
                                           // /* + "&refUrl=" + refUrl*/));

                                    Uri uri = Uri.parse("upi://pay?pa=" + upiIDstring + "&pn=" + "ANC caterers"
                                                    + /*"&mc=" + payeeMCC + "&tid=" + trxnID +*/ "&tr=" + Calendar.getInstance().hashCode() + String.valueOf(rint) +"A"
                                                    + "&tn=" + "FOOD ORDER" + "&am=" + toStoreData.totalPrice + "&cu=" + "INR"
                                            /* + "&refUrl=" + refUrl*/);//url with http or https

                                    List<Intent> li=new ArrayList<Intent>();
                                    boolean isAppInstalled = false;
                                    if(appInstalledOrNot("com.google.android.apps.nbu.paisa.user")) {
                                        Intent intent1 = new Intent(Intent.ACTION_VIEW, uri);
                                        intent1.setPackage("com.google.android.apps.nbu.paisa.user");
                                        li.add(intent1);
                                    }

                                    if(appInstalledOrNot("in.org.npci.upiapp")) {
                                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                        intent.setPackage("in.org.npci.upiapp");
                                        li.add(intent);
                                    }

                                    if(appInstalledOrNot("net.one97.paytm")) {
                                        Intent intent2 = new Intent(Intent.ACTION_VIEW, uri);
                                        intent2.setPackage("net.one97.paytm");
                                        li.add(intent2);
                                    }

                                    if(!li.isEmpty()) {
                                        Intent chooserIntent = Intent.createChooser(li.remove(0), "Choose one these UPI apps..");
                                        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, li.toArray(new Parcelable[]{}));

                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                            startActivityForResult(chooserIntent, 1, null);
                                        }
                                    }
                                    else{
                                        new AlertDialog.Builder(checkout.this)
                                                .setMessage("We are only accepting G-Pay, BHIM and PayTM at the moment. It seems like you have none of these installed. Please install one of them and try again!")
                                                .setCancelable(true)
                                                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {

                                                    }
                                                }).show();
                                    }


                                }
                            }).show();







                }


            }
        });

    }

    public void onBackPressed(){

            cb.setChecked(false);
            Intent backintent = new Intent(checkout.this, menu.class);
            startActivity(backintent);

    }








    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        //txnId=UPI20b6226edaef4c139ed7cc38710095a3&responseCode=00&ApprovalRefNo=null&Status=SUCCESS&txnRef=undefined
        //txnId=UPI608f070ee644467aa78d1ccf5c9ce39b&responseCode=ZM&ApprovalRefNo=null&Status=FAILURE&txnRef=undefined
      /*  Bundle bundle = data.getExtras();
        if (bundle != null) {
            Log.d("values start from here","hereeeeee");
            for (String key : bundle.keySet()) {
                Object value = bundle.get(key);
                Log.d(TAG, String.format("%s %s (%s)", key,
                        value.toString(), value.getClass().getName()));
            }
        }

      */
        if(data!=null) {

            String res = data.getStringExtra("response");
            String search = "SUCCESS";

            if (res.toLowerCase().contains(search.toLowerCase())) {




                Boolean packingstatus = cb.isChecked();



                toStoreData.orderId = String.valueOf(rint) + "A";
                FirebaseDatabase fd = FirebaseDatabase.getInstance();
                DatabaseReference dr = fd.getReference();
                dr = dr.child("ANC").child("ORDERS");
                Calendar cr = Calendar.getInstance();
                cr.add(Calendar.HOUR, -4);

                String dateinformat = String.valueOf(cr.get(Calendar.YEAR)) + "-" + String.valueOf(cr.get(Calendar.MONTH)+1) + "-" + String.valueOf(cr.get(Calendar.DATE));
                dr = dr.child(dateinformat);




                totalEarningsToday=totalEarningsToday+toStoreData.totalPrice;
                dr.child("TOTAL EARNINGS").setValue(totalEarningsToday);
                dr=dr.child("PENDING");
                dr.push().setValue(new pushTemplate(toStoreData.orderId, toStoreData.name, toStoreData.finalOrder.toString(), toStoreData.totalPrice, packingstatus));

                SharedPreferences sp;
                SharedPreferences.Editor spe;
                sp = PreferenceManager.getDefaultSharedPreferences(checkout.this);
                spe = sp.edit();
                int ordersStoredInMemory;
                if(!sp.getString("DATE","default").equals(dateinformat)) {

                    ordersStoredInMemory=0;
                    spe.putString("DATE",dateinformat);
                } else
                ordersStoredInMemory=sp.getInt("NORDERS",0);

                ordersStoredInMemory++;
                String tempkey="Order"+String.valueOf(ordersStoredInMemory);

                spe.putInt("NORDERS",ordersStoredInMemory);
                spe.putString(tempkey, (toStoreData.name+" "+toStoreData.orderId));
                spe.commit();
                final String orderId = sp.getString(tempkey, "default");
                //Toast.makeText(checkout.this, orderId, Toast.LENGTH_SHORT).show();
                final String justfornow = String.valueOf(sp.getInt("NORDERS", 2345678));
                Toast.makeText(checkout.this, justfornow, Toast.LENGTH_SHORT).show();



                Intent toReceiptPage = new Intent(checkout.this, receipt.class);
                startActivity(toReceiptPage);




            } else {
                Toast.makeText(this, "Payment Failed", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public void run() {
        try {
            FirebaseMessaging.getInstance().subscribeToTopic(toStoreData.name + String.valueOf(rint) +"A")
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            String msg = "message subscribed";
                            if (!task.isSuccessful()) {
                                msg = "message not subscribed";
                            }
                            Log.d(TAG, "message displayed");
                           // Toast.makeText(checkout.this, toStoreData.name + toStoreData.orderId, Toast.LENGTH_SHORT).show();

                        }
                    });
        }
        catch (Exception e)
        {
            Log.d("error","DID NOT SUBSCRIBE");
        }
    }

    private boolean appInstalledOrNot(String uri) {
        PackageManager pm = getPackageManager();
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }






    }


}

