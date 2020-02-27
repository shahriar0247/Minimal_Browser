package com.shahriar.xensight.simplebrowser;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {

    public int tabitems;

    public PagerAdapter(FragmentManager fm, int tabitems) {
        super(fm);
        this.tabitems = tabitems;

    }


    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new normal();
            case 1: return new ver_split();
            case 2: return new horiz_split();
            case 3: return new win3_split();
            default: return null;

        }
    }

    @Override
    public int getCount() {
        return tabitems;
    }
}