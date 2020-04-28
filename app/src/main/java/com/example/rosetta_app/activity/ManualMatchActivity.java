package com.example.rosetta_app.activity;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import com.example.rosetta_app.R;
import com.example.rosetta_app.adapter.CustomAdapter;
import com.example.rosetta_app.model.itemModel;

import java.util.ArrayList;

public class ManualMatchActivity extends AppCompatActivity {
    private ArrayList<itemModel> arrayList;
    private RecyclerView recyclerView;
    private int translatorImg[] = {R.drawable.sample, R.drawable.sample2};
    private String translatorNames[] = {"John", "Lea"};
    private String translatorDescription[] = {"Lorem ipsum dolor sit amet, in sed, non id neque, consectetuer semper sit sollicitudin in feugiat. Nunc vehicula rhoncus turpis mi amet, lacus vel magnis dapibus feugiat suscipit, ac wisi maecenas porta, et pellentesque nec nulla eu pellentesque, metus eros distinctio bibendum. Dignissim ullamcorper, vehicula ante mauris, tortor libero anim non blandit vel mus, vitae sed ips",
            "Lorem ipsum dolor sit amet, in sed, non id neque, consectetuer semper sit sollicitudin in feugiat. Nunc vehicula rhoncus turpis mi amet, lacus vel magnis dapibus feugiat suscipit, ac wisi maecenas porta, et pellentesque nec nulla eu pellentesque, metus eros distinctio bibendum. Dignissim ullamcorper, vehicula ante mauris, tortor libero anim non blandit vel mus, vitae sed ips"};
    private String translatorRatings[] = {"3.5/5", "4.0/5"};

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_match);
        this.showAlertDialogWithAutoDismiss();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        arrayList = new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        for (int i = 0; i < translatorImg.length; i++) {
            itemModel itemModel = new itemModel();
            itemModel.setTranslatorImg(translatorImg[i]);
            itemModel.setTranslatorName(translatorNames[i]);
            itemModel.setTranslatorRating(translatorRatings[i]);
            itemModel.setTranslatorDescription(translatorDescription[i]);

            //add in array list
            arrayList.add(itemModel);
        }

        CustomAdapter adapter = new CustomAdapter(this, arrayList);
        recyclerView.setAdapter(adapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void showAlertDialogWithAutoDismiss() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ManualMatchActivity.this,android.R.style.Theme_Material_Light_NoActionBar_Fullscreen);
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