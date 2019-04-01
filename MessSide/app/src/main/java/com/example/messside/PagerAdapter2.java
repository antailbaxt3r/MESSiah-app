package com.example.messside;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter2 extends FragmentStatePagerAdapter {

    int mNoOfTabs;
    public PagerAdapter2(FragmentManager fm , int noOfTabs){
        super(fm);
        this.mNoOfTabs=noOfTabs;
    }


    @Override
    public Fragment getItem(int i) {
        switch(i)
        {
            case 0:
                availaible pen=new availaible();
                return pen;
            case 1:
                not_available com=new not_available();
                return com;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNoOfTabs;
    }
}
