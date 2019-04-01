package com.MESSiahPackage.cmp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity
{
    EditText quantity;
    TextView foodItemTV;
    int priceLastItem,quantityLastItem;
    String lastItem;
    Button doneButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        quantity=(EditText)findViewById(R.id.qty);
        foodItemTV=(TextView)findViewById(R.id.FoodItem);
        foodItemTV.setText(toStoreData.lastMenuItemClicked);
        lastItem=toStoreData.lastMenuItemClicked;
        priceLastItem=Integer.parseInt(lastItem.substring(lastItem.length()-3));
        doneButton=(Button)findViewById(R.id.doneButtonId);

        final Intent backToMenu=new Intent(Main2Activity.this,menu.class);

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((quantity.getText().toString().trim()).isEmpty()) {
                    Toast.makeText(Main2Activity.this, "Please Enter a valid quantity", Toast.LENGTH_SHORT).show();
                } else {
                    quantityLastItem = Integer.parseInt(quantity.getText().toString().trim());
                    if (quantityLastItem != 0) {


                        toStoreData.totalPrice += priceLastItem * quantityLastItem;
                        toStoreData.totalNumberOfItems += quantityLastItem;
                        String temp;
                        temp = String.valueOf(quantityLastItem);
                        temp = temp + "x(";
                        temp = temp + lastItem;
                        temp = temp + ")";
                        (toStoreData.finalOrder).add(temp);
                        startActivity(backToMenu);
                    }
                    else
                        Toast.makeText(Main2Activity.this, "Please Enter a valid quantity", Toast.LENGTH_SHORT).show();

                }


            }
        });




    }




}
