package com.MESSiahPackage.cmp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class receipt extends AppCompatActivity {
    ListView finalPageOrder;
    ArrayAdapter<String> finalPageOrderAdapter;
    TextView name,finalprice,orderIdTextView;
    String nameeplusname,orderidplusorderid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);
        finalPageOrderAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,toStoreData.finalOrder);
        finalPageOrder=(ListView)findViewById(R.id.FinalOrder);
        finalPageOrder.setAdapter(finalPageOrderAdapter);
        name=(TextView)findViewById(R.id.OrderName);
        finalprice=(TextView)findViewById(R.id.OrderAmountFinal);
        nameeplusname="Name:- "+toStoreData.name;
        name.setText(nameeplusname);
        String lastpgtemp="Paid successfully: Rs. "+String.valueOf(toStoreData.totalPrice);
        finalprice.setText(lastpgtemp);
        orderidplusorderid="Order id:- "+toStoreData.orderId;
        orderIdTextView=(TextView)findViewById(R.id.OrderNo);
        orderIdTextView.setText(orderidplusorderid);
        toStoreData.name="";
        toStoreData.totalPrice=0;
        toStoreData.orderId="";
        toStoreData.finalOrder=new ArrayList<String>();
        toStoreData.lastMenuItemClicked="";
        toStoreData.totalNumberOfItems=0;
        Button goback=(Button)findViewById(R.id.GoBackToMenuButton);
        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goBackIntent=new Intent(receipt.this,menu.class);
                startActivity(goBackIntent);
    }});
}

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent goBackIntent=new Intent(receipt.this,menu.class);
        startActivity(goBackIntent);
    }
}
