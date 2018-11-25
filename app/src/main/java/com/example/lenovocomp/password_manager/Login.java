package com.example.lenovocomp.password_manager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity  implements View.OnClickListener {
    public String email = "email";
    public String pass = "password";
    private FirebaseAuth mAuth;
    public EditText Emailtext, Passwordtxt;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String TAG = "messagelog";
    String emailtopass= null;

public ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_login);
        Button Loginbt = (Button) findViewById(R.id.Loginbt);
        Button Signupbt = (Button) findViewById(R.id.Signupbt);
         progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        Loginbt.setOnClickListener(this);
        Signupbt.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in


                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());

                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        progressBar.setVisibility(View.GONE);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Loginbt: {


                Emailtext = (EditText) findViewById(R.id.Emailtxt);
                Passwordtxt = (EditText) findViewById(R.id.passwordbox);

                email = Emailtext.getText().toString();

emailtopass=email;
                pass = Passwordtxt.getText().toString();

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)) {
                    Toast.makeText(this, "Enter you email and Password", Toast.LENGTH_LONG).show();
                } else {

                    progressBar.setVisibility(View.VISIBLE);
                    mAuth.signInWithEmailAndPassword(email, pass)
                            .addOnCompleteListener(  new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {



                            if (task.isSuccessful()) {


                                Intent intent = new Intent(Login.this, ProfileList.class);
                                intent.putExtra("Username",emailtopass);


                                startActivity(intent);
                                progressBar.setVisibility(View.GONE);


                            } else {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(), " Incorrect", Toast.LENGTH_LONG).show();
                            }

                        }
                    });


                }
            }
            break;

            case R.id.Signupbt:

            {

                Intent i = new Intent(Login.this,Register.class);
                startActivity(i);

                break;
            }
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        progressBar.setVisibility(View.GONE);

    }
}
