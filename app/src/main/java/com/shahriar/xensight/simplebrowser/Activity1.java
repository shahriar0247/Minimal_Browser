package com.shahriar.xensight.simplebrowser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;


public class Activity1 extends AppCompatActivity {

    private TabLayout tabLayout;
    private TabItem tab1, tab2, tab3;
    private ViewPager viewPager;
    public PagerAdapter pagerAdapter;

   /* private ConstraintLayout mConstraintLayout;
    private ConstraintSet mConstraintSet = new ConstraintSet();*/

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout1);


        tabLayout = findViewById(R.id.tablayout);
        tab1 = findViewById(R.id.tab1);
        tab2 = findViewById(R.id.tab2);
        tab3 = findViewById(R.id.tab3);
        viewPager = findViewById(R.id.viewpager);

        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(100);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

                    pagerAdapter.notifyDataSetChanged();

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

       /* mConstraintLayout = findViewById(R.id.constraintLayout);
        mConstraintSet.clone(mConstraintLayout);
        mConstraintSet.connect(R.id.viewpager,ConstraintSet.TOP,R.id.tablayout,ConstraintSet.TOP,100);*/
    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            finish();
        } else {
            viewPager.setCurrentItem(0, true);
        }
    }

    /*public void hidetablayout() {
        tabLayout.animate().translationY(-tabLayout.getHeight());
       *//* params.setMargins(0, 30, 0, 0);
        viewPager.setLayoutParams(params);*//*
        //tabLayout.animate().alpha(0.0f).setDuration(200);
    }

    public void showtablayout() {
        tabLayout.animate().translationY(0);
       *//* params.setMargins(0, 100, 0, 0);
        viewPager.setLayoutParams(params);*//*
        //tabLayout.animate().alpha(1f).setDuration(200);
    }*/


}

