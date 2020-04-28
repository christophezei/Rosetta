package com.example.rosetta_app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.rosetta_app.R;

import androidx.cardview.widget.CardView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ChatActivity extends AppCompatActivity {

    @BindView(R.id.editTextMessage)
    EditText editTextMessageInput;

    @BindView(R.id.cardViewRating)
    CardView ratingCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        ratingCardView.setVisibility(View.GONE);

    }

    @OnClick(R.id.sendBtn)
    public void sendText() {
        if(editTextMessageInput.getText().toString().equals("/done")){
            ratingCardView.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.submitRatingBtn)
    public void submitRating() {
            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
    }
}