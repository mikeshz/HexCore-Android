package com.colourus.colourus.colourus;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mkyong.android.R;

public class WebViewActivity extends Activity {

    private WebView webView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);

        webView = (WebView) findViewById(R.id.webView1);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                Log.d("asdf", url);
                url = url.substring(6);
                Log.d("asdf", url);

                return true;
            }
        });
        //webView.loadUrl("https://www.google.ca");
        webView.loadUrl("http://webspace.ocad.ca/~ml13mo/Test.html");

    }

/*    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.app_name)
                .setItems(R.array.colors_array, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                        Log.d("WVA", "wedidit");
                    }
                });
        return builder.create();
    }*/


/*    @Override
    protected void onNewIntent (Intent intent){
        Log.d("asdf", intent.getDataString());
    }*/
}