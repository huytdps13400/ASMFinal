package com.example.asmfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog = ProgressDialog.show(LoginActivity.this,"Thông Báo","Loading...");
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                        Intent intent =new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                    }
                },5000);

            }
        });
    }
}
