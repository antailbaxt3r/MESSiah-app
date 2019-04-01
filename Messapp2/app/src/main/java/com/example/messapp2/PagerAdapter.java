package com.example.messapp2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int mNoOfTabs;
    public PagerAdapter(FragmentManager fm , int noOfTabs){
        super(fm);
        this.mNoOfTabs=noOfTabs;
    }


    @Override
    public Fragment getItem(int i) {
        switch(i)
        {
            case 0:
                availaible av=new availaible();
                return av;
            case 1:
                not_available nav=new not_available();
                return nav;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNoOfTabs;
    }
}
