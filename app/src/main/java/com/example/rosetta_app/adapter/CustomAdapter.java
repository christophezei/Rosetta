package com.example.rosetta_app.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rosetta_app.R;
import com.example.rosetta_app.activity.ChatActivity;
import com.example.rosetta_app.model.itemModel;

import java.util.ArrayList;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import static android.Manifest.permission.CALL_PHONE;
import static androidx.core.app.ActivityCompat.requestPermissions;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.viewHolder> {

    private Context context;
    private ArrayList<itemModel> arrayList;

    public CustomAdapter(Context context, ArrayList<itemModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public  CustomAdapter.viewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list, viewGroup, false);
        return new viewHolder(view);
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public  void onBindViewHolder(CustomAdapter.viewHolder viewHolder,int position) {
        viewHolder.translatorName.setText(arrayList.get(position).getTranslatorName());
        viewHolder.translatorIV.setImageResource(arrayList.get(position).getTranslatorImg());
        viewHolder.translatorDescription.setText(arrayList.get(position).getTranslatorDescription());
        viewHolder.translatorRating.setText(arrayList.get(position).getTranslatorRating());
        viewHolder.callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:000000000"));
                if (ContextCompat.checkSelfPermission(context.getApplicationContext(), CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    context.startActivity(callIntent);
                } else {
                    Activity activity = (Activity) context;
                    requestPermissions(activity ,new String[]{CALL_PHONE}, 1);
                }
            }
        });
        viewHolder.chatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(), ChatActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView translatorIV;
        TextView translatorName;
        TextView translatorDescription;
        TextView translatorRating;
        Button callBtn;
        Button chatBtn;

        public viewHolder(View itemView) {
            super(itemView);
            translatorIV = (ImageView) itemView.findViewById(R.id.translatorIVManual);
            translatorName = (TextView) itemView.findViewById(R.id.translatorName);
            translatorDescription = (TextView) itemView.findViewById(R.id.translatorDescription);
            translatorRating = (TextView) itemView.findViewById(R.id.translatorRating);
            callBtn = (Button) itemView.findViewById(R.id.callBtnManual);
            chatBtn = (Button) itemView.findViewById(R.id.chatBtnManual);
        }

    }

}
