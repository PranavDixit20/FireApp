package com.example.pranav.fireapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Welcome extends AppCompatActivity implements View.OnClickListener {

    private Button b;
    private FirebaseAuth fa;
    private FirebaseAuth.AuthStateListener fal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        fa=FirebaseAuth.getInstance();
        fal = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if(firebaseAuth.getCurrentUser()== null){
                    startActivity(new Intent(Welcome.this,Login.class));
                }
            }
        };


        b = (Button)findViewById(R.id.lgo);

        b.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        fa.signOut();

    }

    @Override
    protected void onStart() {
        super.onStart();

        fa.addAuthStateListener(fal);
    }
}
