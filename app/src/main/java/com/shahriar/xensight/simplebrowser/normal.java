package com.shahriar.xensight.simplebrowser;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 */

public class normal extends Fragment {

    public normal() {
        // Required empty public constructor
    }

    public observable_webview webview;
    public EditText urledit;
    public ImageView url_favicon;
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_normal, container, false);

        return view;


    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {


        urledit = getView().findViewById(R.id.urledit);
        webview = getView().findViewById(R.id.webView);
        final ProgressBar progressbar = getView().findViewById(R.id.progressBar);
        url_favicon = getView().findViewById(R.id.favicon);


        loadurl("google.com");

        webview.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                if (progress < 100 && progressbar.getAlpha() == 0f) {

                    progressbar.animate().alpha(1f);
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    progressbar.setProgress(progress, true);
                } else { progressbar.setProgress(progress);}
                if (progress == 100) {

                    progressbar.animate().alpha(0f);


                }
            }
        });
        webview.setWebViewClient(new WebViewClient() {


            @Override
            public void onPageFinished(WebView view, String url) {


            }


            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                urledit.setText(webview.getUrl());
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        webview.getSettings().setJavaScriptEnabled(true);

        urledit.animate().alpha(0.8f);
        urledit.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View view, int keyCode, KeyEvent keyevent) {
                //If the keyevent is a key-down event on the "enter" button
                if ((keyevent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    //...
                    loadurl(urledit.getText().toString());

                    // ...
                    return true;
                }
                return false;
            }
        });

        webview.setOnScrollChangedCallback(new observable_webview.OnScrollChangedCallback() {
            public void onScroll(int l, int t, int oldl, int oldt) {
                //int oldt2 = oldt -50;
                if (t > oldt) {
                    //Do stuff
                    // urledit.setVisibility(View.INVISIBLE);
                    urledit.animate().translationY(urledit.getHeight());
                    urledit.animate().alpha(0.0f).setDuration(200);
                   /* ((Activity1)getActivity()).hidetablayout();*/
                    //Do stuff
                } else if (t < oldt) {
                    // urledit.setVisibility(View.VISIBLE);
                    urledit.animate().translationY(0);
                    urledit.animate().alpha(0.9f).setDuration(200);
                 /*   ((Activity1)getActivity()).showtablayout();*/
                }
               /* if(t <= 5){
                    ((Activity1)getActivity()).showtablayout();

                }*/
                Log.d(TAG, "onScroll: t=" + t + " l=" + l);

            }
        });
        webview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                urledit.setText(webview.getUrl());
            }
        });
        progressbar.animate().alpha(0.5f);
    }

    public void loadurl(String url) {

        if (url.startsWith("https:") || url.startsWith("http:")) {
            webview.loadUrl(url);
        } else if (url.contains(".")) {
            url = "https://www." + url;
            webview.loadUrl(url);
        } else {
            Uri uri = Uri.parse(url);

            webview.loadUrl("https://www.google.com/search?q=" + uri);
        }
        urledit.setText(webview.getUrl());
    }
}

