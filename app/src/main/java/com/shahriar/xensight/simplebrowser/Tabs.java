package com.shahriar.xensight.simplebrowser;

import androidx.appcompat.app.AppCompatActivity;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.shahriar.xensight.simplebrowser.observable_webview;

public class Tabs extends AppCompatActivity {

    public LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tabs);


        layout = findViewById(R.id.layout);
        int size = 1; // total number of TextViews to add

        observable_webview[] webviews = new observable_webview[size];
        observable_webview temp;

        for (int i = 0; i < size; i++)
        {

            addwebview();

        }

        Button addwebview = findViewById(R.id.addwebview);
        addwebview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addwebview();
            }
        });
    }

    void addwebview(){

        final observable_webview temp;
        temp = new observable_webview(this);

        temp.loadUrl("https://www.google.com");
        temp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
        maximize_webview(temp);
                return true;
            }
        });
        layout.addView(temp);
    }

    void maximize_webview(WebView webview){

// Gets the layout params that will allow you to resize the layout
        ViewGroup.LayoutParams params = webview.getLayoutParams();
// Changes the height and width to the specified *pixels*
        if (WebView.LayoutParams.MATCH_PARENT == params.height){}
        else{
        params.height = WebView.LayoutParams.FILL_PARENT;
        params.width = WebView.LayoutParams.FILL_PARENT;
        webview.setLayoutParams(params);}


        webview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

    }
}