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
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class reg extends AppCompatActivity {
    EditText ipemail,pass,ccpass;
    Button btn;
    String emailpattern="[a-zA-Z0-9,_-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser muser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        ipemail=findViewById(R.id.tf2);
        pass=findViewById(R.id.tf3);
        ccpass=findViewById(R.id.tf4);
        btn=findViewById(R.id.tf6);
        progressDialog=new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        muser=mAuth.getCurrentUser();
        btn.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View view) {
                                       performAuth();
                                   }
                               });
    }
    private void performAuth() {
        String email=ipemail.getText().toString();
        String password=pass.getText().toString();
        String confirm=ccpass.getText().toString();
        if(!email.matches(emailpattern)){
            ipemail.setError("enter correct email");
        }else if(password.isEmpty() || password.length()<6){
            pass.setError("enter proper password");
        }else if(!password.equals(confirm)){
            ccpass.setError("password doen`t match");
        }else{
            progressDialog.setMessage("Please wait while Registration....");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        progressDialog.dismiss();
                        sendUserToNextActivity();
                        Toast.makeText(reg.this,"Registration successfull",Toast.LENGTH_LONG).show();
                    }else {
                        progressDialog.dismiss();
                        Toast.makeText(reg.this,"Registration failed",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
    private void sendUserToNextActivity() {
        Intent intent=new Intent(reg.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}