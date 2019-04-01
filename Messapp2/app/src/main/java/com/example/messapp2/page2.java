package com.example.messapp2;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class page2 extends FragmentActivity implements pending.OnFragmentInteractionListener,ongoing.OnFragmentInteractionListener,completed.OnFragmentInteractionListener{

    CustomViewPager viewPager1;
    PagerAdapter2 adapter1;
    public TabLayout tabLayout1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);

        tabLayout1=(TabLayout)findViewById(R.id.TabLay2);
        tabLayout1.addTab(tabLayout1.newTab().setText("pending"));
        tabLayout1.addTab(tabLayout1.newTab().setText("ongoing"));
        tabLayout1.addTab(tabLayout1.newTab().setText("completed"));
        tabLayout1.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager1=(CustomViewPager)findViewById(R.id.ViewPage2);

        adapter1=new PagerAdapter2(getSupportFragmentManager(),tabLayout1.getTabCount());
        viewPager1.setAdapter(adapter1);


        tabLayout1.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager1.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }



}
