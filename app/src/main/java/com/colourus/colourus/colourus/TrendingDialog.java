package com.colourus.colourus.colourus;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by michael on 23/01/16.
 */
public class TrendingDialog extends DialogFragment{


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        Bundle args = getArguments();
        String title = args.getString("title", "");
        String message = args.getString("message", "");

        //-----Initializes string with sample data for now-----
        String url = "https://subdomain.domain.com?ayy,lmao,jonathan,is,the,best";

        //int index = url.indexOf("?");
        url = url.substring(url.indexOf("?"));
        Log.d("asdf", "Parsed url is: " + url);

        String outList[] = url.split(",");

        return new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setMessage(message)
                .setItems(outList, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create();
    }
}
