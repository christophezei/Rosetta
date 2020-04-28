package com.example.rosetta_app.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import butterknife.ButterKnife;
import butterknife.OnClick;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import com.example.rosetta_app.R;

import static android.Manifest.permission.CALL_PHONE;

public class AutomaticMatchActivity extends AppCompatActivity {
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_automatic_match);
        ButterKnife.bind(this);
        this.showAlertDialogWithAutoDismiss();

    }

    @OnClick(R.id.chatBtn)
    public void openChat() {
        Intent intent = new Intent(this, ChatActivity.class);
        startActivity(intent);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @OnClick(R.id.callBtn)
    public void call() {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:000000000"));
        if (ContextCompat.checkSelfPermission(getApplicationContext(), CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            startActivity(callIntent);
        } else {
            requestPermissions(new String[]{CALL_PHONE}, 1);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void showAlertDialogWithAutoDismiss() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AutomaticMatchActivity.this,android.R.style.Theme_Material_Light_NoActionBar_Fullscreen);
        builder.setTitle("Searching for available translator")
                .setMessage("Loading...")
                .setCancelable(false).setCancelable(false);

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (alertDialog.isShowing()){
                    alertDialog.dismiss();
                }
            }
        }, 1500);
    }
}