package com.shahriar.xensight.simplebrowser;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ProgressBar;


/**
 * A simple {@link Fragment} subclass.
 */
public class ver_split extends Fragment {

    public ver_split() {
        // Required empty public constructor
    }


    public observable_webview webview;
    public EditText urledit;
   /* public observable_webview webview2;
    public EditText urledit2;*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ver_split, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {


        urledit = getView().findViewById(R.id.urleditver1);
        webview = getView().findViewById(R.id.verweb1);
        final ProgressBar progressbar = getView().findViewById(R.id.progressBar1);

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
                urledit.setText(webview.getUrl());

            }


            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                urledit.setText(webview.getUrl());
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        webview.getSettings().setJavaScriptEnabled(true);

        urledit.animate().alpha(0.9f);
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
                if (t > oldt) {
                    //Do stuff
                    // urledit.setVisibility(View.INVISIBLE);
                    urledit.animate().translationY(-urledit.getHeight());
                    urledit.animate().alpha(0.0f).setDuration(200);
                    //Do stuff
                } else if (t < oldt) {
                    // urledit.setVisibility(View.VISIBLE);
                    urledit.animate().translationY(0);

                    urledit.animate().alpha(0.8f).setDuration(200);
                }

            }
        });

        webview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                urledit.setText(webview.getUrl());
            }
        });
      /*  urledit2 = getView().findViewById(R.id.urleditver2);
        webview2 = getView().findViewById(R.id.verweb2);
        final ProgressBar progressbar2 = getView().findViewById(R.id.progressBar2);

        loadurl2("google.com");
        webview2.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                if (progress < 100 && progressbar2.getAlpha() == 0f) {

                    progressbar2.animate().alpha(1f);
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    progressbar2.setProgress(progress, true);
                } else { progressbar2.setProgress(progress);}
                if (progress == 100) {

                    progressbar2.animate().alpha(0f);


                }
            }
        });

        webview2.setWebViewClient(new WebViewClient() {


            @Override
            public void onPageFinished(WebView view, String url) {
                urledit2.setText(webview2.getUrl());

            }


            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                urledit2.setText(webview2.getUrl());
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        webview2.getSettings().setJavaScriptEnabled(true);

        urledit2.animate().alpha(0.9f);
        urledit2.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View view, int keyCode, KeyEvent keyevent) {
                //If the keyevent is a key-down event on the "enter" button
                if ((keyevent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    //...
                    loadurl2(urledit2.getText().toString());

                    // ...
                    return true;
                }
                return false;
            }
        });

        webview2.setOnScrollChangedCallback(new observable_webview.OnScrollChangedCallback() {
            public void onScroll(int l, int t, int oldl, int oldt) {
                if (t > oldt) {
                    //Do stuff
                    // urledit2.setVisibility(View.INVISIBLE);
                    urledit2.animate().translationY(-urledit2.getHeight());
                    urledit2.animate().alpha(0.0f).setDuration(200);
                    //Do stuff
                } else if (t < oldt) {
                    // urledit2.setVisibility(View.VISIBLE);
                    urledit2.animate().translationY(0);

                    urledit2.animate().alpha(0.8f).setDuration(200);
                }

            }
        });
        webview2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                urledit2.setText(webview2.getUrl());
            }
        });*/


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
/*
    public void loadurl2(String url) {

        if (url.startsWith("https:") || url.startsWith("http:")) {
            webview2.loadUrl(url);
        } else if (url.contains(".")) {
            url = "https://www." + url;
            webview2.loadUrl(url);
        } else {
            Uri uri = Uri.parse(url);

            webview2.loadUrl("https://www.google.com/search?q=" + uri);
        }
        urledit2.setText(webview2.getUrl());
    }*/
}

