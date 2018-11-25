package com.example.lenovocomp.password_manager;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class Register extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;

    public Button signupbt;
    public EditText emailtxt;
    public EditText passwordtxt;

    @Override
    public void onStart() {
        super.onStart();
        mAuth= FirebaseAuth.getInstance();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sout);

        signupbt = (Button) findViewById(R.id.Signupbt);
        emailtxt = (EditText) findViewById(R.id.Emailtxt);
        passwordtxt = (EditText) findViewById(R.id.passwordbox);
        signupbt.setOnClickListener(this);
        mAuth=FirebaseAuth.getInstance();

    }






    @Override
    public void onClick(View v) {
        String email = emailtxt.getText().toString();
        String password = passwordtxt.getText().toString();

        mAuth.createUserWithEmailAndPassword( email, password).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    Toast.makeText(Register.this, "yo", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Register.this, Login.class);
                    startActivity(intent);
                }
                else
                {
                    if(!task.isSuccessful()){
                        FirebaseAuthException e = (FirebaseAuthException )task.getException();
                        Toast.makeText(Register.this, "Failed Registration: "+e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });


    }


}
