package com.MESSiahPackage.cmp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class Orders extends AppCompatActivity {

    ListView previousOrdersList;
    ArrayAdapter<String> myArr;
    ArrayList<String> myArrayList=new ArrayList<>();
    SharedPreferences sp;
    int numberOfPastOrders;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        previousOrdersList=(ListView)findViewById(R.id.List_View_Orders);
        myArr=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,myArrayList);
        previousOrdersList.setAdapter(myArr);
        sp= PreferenceManager.getDefaultSharedPreferences(Orders.this);
        Calendar cr = Calendar.getInstance();
        cr.add(Calendar.HOUR, -4);

        String dateinformat = String.valueOf(cr.get(Calendar.YEAR)) + "-" + String.valueOf(cr.get(Calendar.MONTH)+1) + "-" + String.valueOf(cr.get(Calendar.DATE));
        if(sp.getString("DATE","default").equals(dateinformat))
            numberOfPastOrders=sp.getInt("NORDERS",0);
        else
            numberOfPastOrders=0;
        Toast.makeText(Orders.this, String.valueOf(numberOfPastOrders), Toast.LENGTH_LONG).show();
        String orderIdTemp,orderId="Order";
        for(int tempo=1;tempo<=numberOfPastOrders;tempo++)
        {
            orderIdTemp=orderId+String.valueOf(tempo);
            myArrayList.add(sp.getString(orderIdTemp,"phaltu button"));
        }
        myArr.notifyDataSetChanged();

        previousOrdersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tempTV=(TextView)view;
                toStoreData.pastOrdersOrderId=tempTV.getText().toString().substring(tempTV.getText().toString().length()-4);
                toStoreData.pastOrdersName=tempTV.getText().toString().substring(0,tempTV.getText().toString().length()-5);
                Intent LastOrderPage=new Intent(Orders.this,PastOrders.class);
                startActivity(LastOrderPage);

            }




        });



    }
}
