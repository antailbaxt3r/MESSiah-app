package com.example.messapp2;

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
                pending pen=new pending();
                return pen;
            case 1:
                ongoing ong=new ongoing();
                return ong;
            case 2:
                completed comp=new completed();
                return comp;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNoOfTabs;
    }
}
