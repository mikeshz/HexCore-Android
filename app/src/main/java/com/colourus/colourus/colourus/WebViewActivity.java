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
                //Upon selecting a country, the web server sends us its unique url which we pass to the ShowAlertDialog for parsing

                //-----Initializes string with sample data for now-----
                //url = "https://subdomain.domain.com?Canada,ayy,lmao,jonathan,is,the,best";

                rawTrending = url;


                ShowAlertDialogWithListview();

                return true;
            }
        });
        //Loads the webpage, forcing a reload if it's already cached
        //Todo: Find a way to prevent the caching in the first place?
        webView.loadUrl( "javascript:window.location.reload( true )" );
        webView.loadUrl("http://webspace.ocad.ca/~ml13mo/Test.html");

    }

    public void ShowAlertDialogWithListview()
    {
        //A raw url would look like this: https://subdomain.domain.com?Canada&ayy&lmao&jonathan&is&the&best
        //The first variable after the question mark is the country name, followed by the top trending hashtags separated by &

        //We cut off all text before the question mark
        rawTrending = rawTrending.substring(rawTrending.indexOf("?") + 1);

        //Splits hashtags by & into an array
        String outList[] = rawTrending.split("&");

        //Todo: Quite inefficient here as we split it into an array then immediately copy it into an array list
        //Adds stuff to the array list
        List<String> mTrending = new ArrayList<String>();
        for (int i = 1; i < outList.length; i++) {
            //Adds a hashtag in front of all array elements
            mTrending.add("#" + outList[i]);
            Log.d("asdf", "url split: " + outList[i]);
        }

        //Create sequence of items
        final CharSequence[] trendingTweets = mTrending.toArray(new String[mTrending.size()]);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Trending Tweets in " + outList[0]);
        dialogBuilder.setItems(trendingTweets, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                //Gets the selected item in the listview
                String selectedText = trendingTweets[item].toString();
                Log.d("asdf", selectedText + " was selected");

                //Do a .replace for # to nothing in selectedText
                //Search twitter for https://twitter.com/search?q=%23selectedText
                //%23 is the hashtag
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/search?q=%23" + selectedText.replace("#", "")));
                startActivity(browserIntent);
            }
        });
        //Create alert dialog object via builder
        AlertDialog alertDialogObject = dialogBuilder.create();
        //Show the dialog
        alertDialogObject.show();
    }  }

