package com.example.rosetta_app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.rosetta_app.R;

import android.content.Intent;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.loginBtn)
    public void login() {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.signupTv)
    public void signUp() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.helpSignInTv)
    public void requestPassword() {
        Intent intent = new Intent(this, HelpWithSignInActivity.class);
        startActivity(intent);
    }
}
