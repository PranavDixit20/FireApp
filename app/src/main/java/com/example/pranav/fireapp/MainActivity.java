package com.example.pranav.fireapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button b;
    private EditText e,e1;
    private TextView tv;

    private ProgressDialog pd;
    private FirebaseAuth fa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fa = FirebaseAuth.getInstance();


        pd = new ProgressDialog(this);

        b = (Button)findViewById(R.id.reg);
        e = (EditText)findViewById(R.id.email);
        e1 = (EditText)findViewById(R.id.pass);
        tv = (TextView)findViewById(R.id.lg);

        b.setOnClickListener(this);
        tv.setOnClickListener(this);
    }

    private void registerUser()
    {
        String email = e.getText().toString().trim();
        String pass = e1.getText().toString().trim();

        if(TextUtils.isEmpty(email))
        {
            //email is empty
            Toast.makeText(this,"please enter email", Toast.LENGTH_SHORT).show();
            //stopping execution function
            return;
        }

        if (TextUtils.isEmpty(pass))
        {
            //password is empty
            Toast.makeText(this,"please enter password", Toast.LENGTH_SHORT).show();
            //stopping execution function
            return;
        }

        pd.setMessage("Registering user....");
        pd.show();

        fa.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(this,new OnCompleteListener<AuthResult>(){
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful())
                        {
                            //task is successful register and login
                            // we will start the profile activity here
                            //right now lets display a toast only
                            Toast.makeText(MainActivity.this,"Resisted successfully ", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this,Login.class));
                        }else {
                            Toast.makeText(MainActivity.this,"could not register. please try again. ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    @Override
    public void onClick(View view) {

        if(view==b)
        {
            registerUser();
        }
        if (view==tv)
        {
            startActivity(new Intent(MainActivity.this,Login.class));
        }
    }
}
