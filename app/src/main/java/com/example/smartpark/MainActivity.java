package com.example.smartpark;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    Button btn,btn2;
    EditText ipemail,pass;
    String emailpattern="[a-zA-Z0-9,_-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser muser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ipemail=findViewById(R.id.tf1);
        pass=findViewById(R.id.tf2);
        btn=findViewById(R.id.tf3);
        btn2=findViewById(R.id.tf4);
        progressDialog=new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        muser=mAuth.getCurrentUser();
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(performLogin()){
                sendUserToNextActivity2();
            }}
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendUserToNextActivity();
            }
        });
    }

    private boolean performLogin() {
        String email=ipemail.getText().toString();
        String password=pass.getText().toString();
        if(!email.matches(emailpattern)){
            ipemail.setError("enter correct email");
            return false;
        }else if(password.isEmpty() || password.length()<6){
            pass.setError("enter proper password");
            return false;
        }else{
            progressDialog.setMessage("Please wait while Login....");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                 if(task.isSuccessful()){
                     progressDialog.dismiss();
                     sendUserToNextActivity();
                     Toast.makeText(MainActivity.this,"Login successfull",Toast.LENGTH_LONG).show();
                 }else {
                     progressDialog.dismiss();
                     Toast.makeText(MainActivity.this,"Login failed",Toast.LENGTH_LONG).show();
                 }
                }
            });
    } return true;}

    private void sendUserToNextActivity() {
        Intent intent=new Intent(MainActivity.this,reg.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    private void sendUserToNextActivity2() {
        Intent intent=new Intent(MainActivity.this,home.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}