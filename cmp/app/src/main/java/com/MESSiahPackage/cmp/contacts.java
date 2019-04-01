package com.MESSiahPackage.cmp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;


public class contacts extends AppCompatActivity {
 CardView callAjinkya, callRaghav, callArjun, callRishabh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        callAjinkya = findViewById(R.id.callAjinkya);
        callRaghav = findViewById(R.id.callRaghav);
        callArjun = findViewById(R.id.callArjun);
        callRishabh = findViewById(R.id.callRishabh);

        callAjinkya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String posted_by = "981-976-225-0";

                String uri = "tel:" + posted_by.trim() ;
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(uri));
                startActivity(intent);
            }
        });

        callRaghav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String posted_by = "638-733-199-8";

                String uri = "tel:" + posted_by.trim() ;
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(uri));
                startActivity(intent);
            }
        });

        callArjun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String posted_by = "752-283-333-8";

                String uri = "tel:" + posted_by.trim() ;
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(uri));
                startActivity(intent);
            }
        });

        callRishabh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String posted_by = "965-773-606-9";

                String uri = "tel:" + posted_by.trim() ;
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(uri));
                startActivity(intent);
            }
        });
    }


}
