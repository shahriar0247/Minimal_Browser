package com.shahriar.xensight.simplebrowser;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class ver_split extends Fragment {

    public ver_split() {
        // Required empty public constructor
    }


    public WebView webview;
    public EditText urledit;
    public WebView webview2;
    public EditText urledit2;
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


        loadurl1("google.com");

        webview.setWebChromeClient(new WebChromeClient());
        webview.setWebViewClient(new WebViewClient() {


            @Override
            public void onPageFinished(WebView view, String url) {

                urledit.setText(url);
            }


            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        webview.getSettings().setJavaScriptEnabled(true);


        urledit.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View view, int keyCode, KeyEvent keyevent) {
                //If the keyevent is a key-down event on the "enter" button
                if ((keyevent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    //...
                    loadurl1(urledit.getText().toString());

                    // ...
                    return true;
                }
                return false;
            }
        });

        urledit2 = getView().findViewById(R.id.urleditver2);
        webview2 = getView().findViewById(R.id.verweb2);


        loadurl1("google.com");

        webview2.setWebChromeClient(new WebChromeClient());
        webview2.setWebViewClient(new WebViewClient() {


            @Override
            public void onPageFinished(WebView view, String url) {

                urledit2.setText(url);
            }


            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        webview2.getSettings().setJavaScriptEnabled(true);


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
        loadurl2("google.com");
    }

    public void loadurl1(String url) {

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
    }

}
