package com.example.pranav.fireapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private EditText et,et1;
    private Button b;

    FirebaseAuth fa;
    FirebaseAuth.AuthStateListener fal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fa = FirebaseAuth.getInstance();

        et = (EditText)findViewById(R.id.email);
        et1 = (EditText)findViewById(R.id.pass);
        b = (Button)findViewById(R.id.ln);

        b.setOnClickListener(this);

        fal = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if(firebaseAuth.getCurrentUser() == null){

                }else{
                    startActivity(new Intent(Login.this,Welcome.class));
                }


            }
        };
    }

    @Override
    public void onClick(View view) {

        startSignIn();
    }

    @Override
    protected void onStart() {
        super.onStart();

        fa.addAuthStateListener(fal);
    }

    private void startSignIn(){
        String email = et.getText().toString().trim();
        String pass = et1.getText().toString().trim();

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(pass))
        {
            //email is empty
            Toast.makeText(this,"fields are empty", Toast.LENGTH_SHORT).show();
            //stopping execution function
        }else {
            fa.signInWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(Login.this, "Sign in problem", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }
}

