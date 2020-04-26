package com.example.rosetta_app.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;

import com.example.rosetta_app.R;

public class AutomaticMatchActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_automatic_match);
        this.showAlertDialogWithAutoDismiss();

    }
    public void showAlertDialogWithAutoDismiss() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AutomaticMatchActivity.this);
        builder.setTitle("Searching for available translator")
                .setMessage("Loading...")
                .setCancelable(false).setCancelable(false);
                /*.setPositiveButton("SKIP", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //this for skip dialog
                        dialog.cancel();
                    }
                });*/
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (alertDialog.isShowing()){
                    alertDialog.dismiss();
                }
            }
        }, 1000);
    }
}