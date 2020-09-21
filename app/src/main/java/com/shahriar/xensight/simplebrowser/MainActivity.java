package com.shahriar.xensight.simplebrowser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import net.yslibrary.android.keyboardvisibilityevent.util.UIUtil;

import java.net.URISyntaxException;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class MainActivity extends AppCompatActivity {

    public observable_webview webview;
    public EditText urledit;
    public ImageView url_favicon;
    public LinearLayout ll;

    public ImageView back;
    public ImageView forward;
    public ImageView reload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        urledit = findViewById(R.id.urledit);
        webview = findViewById(R.id.webView);
        final ProgressBar progressbar = findViewById(R.id.progressBar);
        url_favicon = findViewById(R.id.favicon);
        ll = findViewById(R.id.ll);

        back = findViewById(R.id.back);
        forward = findViewById(R.id.forward);
        reload = findViewById(R.id.reload);

        loadurl("google.com");

        webview.setWebChromeClient(new ChromeClient() {
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
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                if (webview.canGoBack()){
                    back.setImageResource(R.drawable.back);

                }
                else{
                    back.setImageResource(R.drawable.back2);
                }
                if (webview.canGoForward()){
                    forward.setImageResource(R.drawable.forward);

                }
                else{
                    forward.setImageResource(R.drawable.forward2);
                }
                super.onPageStarted(view, url, favicon);
                urledit.setText(url);
            }



            @Override
            public void onPageFinished(WebView view, String url) {
                if (webview.canGoBack()){
                    back.setImageResource(R.drawable.back);
                    urledit.setText(url);

                }
                else{
                    back.setImageResource(R.drawable.back2);
                }
                if (webview.canGoForward()){
                    forward.setImageResource(R.drawable.forward);

                }
                else{
                    forward.setImageResource(R.drawable.forward2);
                }

            }




            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                urledit.setText(webview.getUrl());
                if (url.startsWith("intent://")) {
                    try {
                        Context context = view.getContext();
                        Intent intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);

                        if (intent != null) {
                            view.stopLoading();

                            PackageManager packageManager = context.getPackageManager();
                            ResolveInfo info = packageManager.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
                            if (info != null) {
                                context.startActivity(intent);
                            } else {
                                String fallbackUrl = intent.getStringExtra("browser_fallback_url");
                                view.loadUrl(fallbackUrl);

                                // or call external broswer
//                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(fallbackUrl));
//                    context.startActivity(browserIntent);
                            }

                            return true;
                        }
                    } catch (URISyntaxException e) {

                            Log.e(TAG, "Can't resolve intent://", e);

                    }
                }
                return false;

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
       if (urledit.hasFocus())
       {

       }
       else{
           urledit.selectAll();
       }
     webview.setOnTouchListener(new View.OnTouchListener() {
         @Override
         public boolean onTouch(View v, MotionEvent event) {
             urledit.clearFocus();
             UIUtil.hideKeyboard(MainActivity.this);
             return false;
         }
     });
        webview.setOnScrollChangedCallback(new observable_webview.OnScrollChangedCallback() {
            public void onScroll(int l, int t, int oldl, int oldt) {
                //int oldt2 = oldt -50;
                if (t > oldt) {
                    //Do stuff
                    // urledit.setVisibility(View.INVISIBLE);
                   /* urledit.animate().translationY(urledit.getHeight());
                    urledit.animate().alpha(0.0f).setDuration(200);*/
                    ll.animate().translationY(urledit.getHeight());
                    ll.animate().alpha(0.0f).setDuration(200);
                    /* ((Activity1)getActivity()).hidetablayout();*/
                    //Do stuff
                } else if (t < oldt) {
                    // urledit.setVisibility(View.VISIBLE);
                   /* urledit.animate().translationY(0);
                    urledit.animate().alpha(0.9f).setDuration(200);*/
                    ll.animate().translationY(0);
                    ll.animate().alpha(0.8f).setDuration(200);
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

        ll.setAlpha(0.8f);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (webview.canGoBack()){
                    webview.goBack();

                }
            }
        });

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (webview.canGoForward()){
                    webview.goForward();

                }
            }
        });

        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webview.reload();
            }
        });


    }

    public void loadurl(String url) {
        UIUtil.hideKeyboard(MainActivity.this);
        if (url.startsWith("https:") || url.startsWith("http:")) {
            webview.loadUrl(url);
        } else if (url.contains(".")) {
            url = "https://www." + url;
            webview.loadUrl(url);
        } else {
            Uri uri = Uri.parse(url);

            webview.loadUrl("https://www.google.com/search?q=" + uri);
        }


    }



    @Override
    protected void onPause() {

        super.onPause();
        webview.onResume();
    }

    @Override
    public void onBackPressed() {
       if (webview.canGoBack()){
           webview.goBack();
       }
       else{finish();}
    }
    private class ChromeClient extends WebChromeClient {
        private View mCustomView;
        private WebChromeClient.CustomViewCallback mCustomViewCallback;
        protected FrameLayout mFullscreenContainer;
        private int mOriginalOrientation;
        private int mOriginalSystemUiVisibility;

        ChromeClient() {}

        public Bitmap getDefaultVideoPoster()
        {
            if (mCustomView == null) {
                return null;
            }
            return BitmapFactory.decodeResource(getApplicationContext().getResources(), 2130837573);
        }

        public void onHideCustomView()
        {
            ((FrameLayout)getWindow().getDecorView()).removeView(this.mCustomView);
            this.mCustomView = null;
            getWindow().getDecorView().setSystemUiVisibility(this.mOriginalSystemUiVisibility);
            setRequestedOrientation(this.mOriginalOrientation);
            this.mCustomViewCallback.onCustomViewHidden();
            this.mCustomViewCallback = null;
        }

        public void onShowCustomView(View paramView, WebChromeClient.CustomViewCallback paramCustomViewCallback)
        {
            if (this.mCustomView != null)
            {
                onHideCustomView();
                return;
            }
            this.mCustomView = paramView;
            this.mOriginalSystemUiVisibility = getWindow().getDecorView().getSystemUiVisibility();
            this.mOriginalOrientation = getRequestedOrientation();
            this.mCustomViewCallback = paramCustomViewCallback;
            ((FrameLayout)getWindow().getDecorView()).addView(this.mCustomView, new FrameLayout.LayoutParams(-1, -1));
            getWindow().getDecorView().setSystemUiVisibility(3846 | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
    }
}

