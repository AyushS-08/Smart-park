package com.example.smartpark;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class home extends AppCompatActivity {
    Button help,pay,status,check;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        help=findViewById(R.id.tf3);
        pay=findViewById(R.id.tf4);
        check=findViewById(R.id.tf1);
        status=findViewById(R.id.tf2);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    sendUserToNextActivity2();
                }
        });
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    private void sendUserToNextActivity2() {
        Intent intent=new Intent(home.this,BlankFragment.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    }
