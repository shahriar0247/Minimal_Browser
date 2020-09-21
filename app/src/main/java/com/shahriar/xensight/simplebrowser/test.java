package com.shahriar.xensight.simplebrowser;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        // Creating a new RelativeLayout
        RelativeLayout relativeLayout = new RelativeLayout(this);

        // Defining the RelativeLayout layout parameters.
        // In this case I want to fill its parent
        RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);

        // Creating a new TextView



        // Defining the layout parameters of the TextView
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                400,
                400);

        lp.setMargins(100,100,100,100);
        // Setting the parameters on the TextView


        // Adding the TextView to the RelativeLayout as a child
        RelativeLayout tv = new RelativeLayout(this);
        tv.setLayoutParams(lp);
        tv.setBackgroundResource(R.drawable.rounded_corners2);
        relativeLayout.addView(tv);





        lp.setMargins(100,100,100,100);
        // Setting the RelativeLayout as our content view
        setContentView(relativeLayout, rlp);
    }




}
