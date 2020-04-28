package com.example.rosetta_app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.example.rosetta_app.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ChatActivity extends AppCompatActivity {

    @BindView(R.id.editTextMessage)
    EditText editTextMessageInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.sendBtn)
    public void sendText() {
        if(editTextMessageInput.getText().toString().equals("/done")){
            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
        }
    }
}