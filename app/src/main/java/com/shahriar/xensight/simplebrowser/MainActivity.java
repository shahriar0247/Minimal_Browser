package com.shahriar.xensight.simplebrowser;

import androidx.appcompat.app.AppCompatActivity;


import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    public WebView webview;
    public EditText urledit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        urledit = findViewById(R.id.urledit);
        webview = findViewById(R.id.webView);


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


        loadurl("google.com");


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

    protected void onSaveInstanceState(Bundle outstate) {
        outstate.putString("saved_url", webview.getUrl().toString());
        super.onSaveInstanceState(outstate);
    }

    protected void onRestoreInstanceState(Bundle instate) {
        super.onRestoreInstanceState(instate);
        loadurl(instate.getString("saved_url"));
    }

    @Override
    public void onBackPressed() {
       if (webview.canGoBack()){
           webview.goBack();
       }
       else{finish();}
    }
}
