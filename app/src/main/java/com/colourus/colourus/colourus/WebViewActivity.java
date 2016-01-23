package com.colourus.colourus.colourus;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mkyong.android.R;

import java.util.ArrayList;
import java.util.List;

public class WebViewActivity extends Activity{

    private WebView webView;
    private String rawTrending;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);

        webView = (WebView) findViewById(R.id.webView1);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
/*                Log.d("asdf", url);
                url = url.substring(6);
                Log.d("asdf", url);*/

                //-----Initializes string with sample data for now-----
                //url = "https://subdomain.domain.com?Canada,ayy,lmao,jonathan,is,the,best";

                rawTrending = url;


                ShowAlertDialogWithListview();

                return true;
            }
        });
        //webView.loadUrl("https://www.google.ca");
        webView.loadUrl( "javascript:window.location.reload( true )" );
        webView.loadUrl("http://webspace.ocad.ca/~ml13mo/Test.html");

    }

    public void ShowAlertDialogWithListview()
    {
        //int index = url.indexOf("?");
        rawTrending = rawTrending.substring(rawTrending.indexOf("?") + 1);
        Log.d("asdf", "Parsed url is: " + rawTrending);

        String outList[] = rawTrending.split(",");


        List<String> mTrending = new ArrayList<String>();
        for (int i = 1; i < outList.length; i++) {
            mTrending.add("#" + outList[i]);
            Log.d("asdf", "url split: " + outList[i]);
        }


/*        List<String> mAnimals = new ArrayList<String>();
        mAnimals.add("Cat");
        mAnimals.add("Dog");
        mAnimals.add("Horse");
        mAnimals.add("Elephant");
        mAnimals.add("Rat");
        mAnimals.add("Lion");*/
        //Create sequence of items
        final CharSequence[] trendingTweets = mTrending.toArray(new String[mTrending.size()]);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Trending Tweets in " + outList[0]);
        dialogBuilder.setItems(trendingTweets, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                String selectedText = trendingTweets[item].toString();  //Selected item in listview
                Log.d("asdf", selectedText + " was selected");

                //Do a .replace for # to nothing in selectedText
                //Search twitter for https://twitter.com/search?q=%23selectedText
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/search?q=%23" + selectedText.replace("#", "")));
                startActivity(browserIntent);
            }
        });
        //Create alert dialog object via builder
        AlertDialog alertDialogObject = dialogBuilder.create();
        //Show the dialog
        alertDialogObject.show();
    }  }

