package com.example.lenovocomp.password_manager;

import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class DataActivity extends AppCompatActivity {
    public  String username;
    public String st_showusername,password,st_password;
    private FirebaseAuth mAuth;
    FirebaseUser firebaseUser;
    public  String titles,Username;

    private DatabaseReference  databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        databaseReference=FirebaseDatabase.getInstance().getReference("users");
        mAuth=FirebaseAuth.getInstance();
        firebaseUser=mAuth.getCurrentUser();
        final EditText Usernametxt = (EditText) findViewById(R.id.username);
        final EditText Passwordtxt= (EditText) findViewById(R.id.passwordbox);
        final TextView showusername = (TextView) findViewById(R.id.showusername);
        final TextView title = (TextView) findViewById(R.id.titleview);
        final TextView showpassword = (TextView) findViewById(R.id.showpassword);

        Intent  intent2 = getIntent();
        titles =intent2.getStringExtra("title");



        int pics =intent2.getIntExtra("pics",1);
        Button savebt =(Button) findViewById(R.id.savebt);
        Button deletebt =(Button) findViewById(R.id.Delete);
        Button Updatebt =(Button) findViewById(R.id.update);

        Updatebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()) {
                    case R.id.update:
                    {
                        if(TextUtils.isEmpty(Usernametxt.getText().toString())  || TextUtils.isEmpty(Passwordtxt.getText().toString()))
                        {
                            Toast toast = Toast.makeText(DataActivity.this, "Email and Password must be filled ", Toast.LENGTH_LONG);
                            toast.show();
                        }
                        else
                            databaseReference.child(firebaseUser.getUid()).child(titles).child("username").setValue(Usernametxt.getText().toString());
                        databaseReference.child(firebaseUser.getUid()).child(titles).child("password").setValue(Passwordtxt.getText().toString());
                        databaseReference.addValueEventListener(new ValueEventListener() {

                            public void onDataChange(DataSnapshot dataSnapshot) {

                                Username= dataSnapshot.child(firebaseUser.getUid()).child(titles).child("username").getValue(String.class);

                                password= dataSnapshot.child(firebaseUser.getUid()).child(titles).child("password").getValue(String.class);

                                st_showusername="your user name for"+titles+"is" + Username;

                                st_password="your pass for"+titles+"is" + password;
                                showusername.setText(st_showusername);
                                showpassword.setText(st_password);
                                showusername.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                Toast toast =  Toast.makeText(DataActivity.this,"Retry or Check internet Connection",Toast.LENGTH_LONG);
                            toast.show();
                            }
                        });

                    }

                }


            }
        });


        savebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId())
                {
                    case R.id.savebt:{
                       if(TextUtils.isEmpty(Usernametxt.getText().toString())  || TextUtils.isEmpty(Passwordtxt.getText().toString()))
                        {
                            Toast toast = Toast.makeText(DataActivity.this, "Email and Password must be filled ", Toast.LENGTH_LONG);
                        toast.show();
                        }
                       else
                           {
                            addnewuser(titles, Usernametxt.getText().toString(), Passwordtxt.getText().toString());
                            databaseReference.addValueEventListener(new ValueEventListener() {

                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    Username = dataSnapshot.child(firebaseUser.getUid()).child(titles).child("username").getValue(String.class);

                                    password = dataSnapshot.child(firebaseUser.getUid()).child(titles).child("password").getValue(String.class);

                                    st_showusername = "your user name for" + titles + "is" + Username;

                                    st_password = "your pass for" + titles + "is" + password;
                                    showusername.setText(st_showusername);
                                    showpassword.setText(st_password);
                                    showusername.setVisibility(View.VISIBLE);
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                }
                            });
                        }
                    }
                }

            }
        });


        showusername.setVisibility(View.GONE);




        title.setText(titles);

        setImage(pics);



    }

    private void setImage(int imageUrl){

        ImageView imageView = (ImageView) findViewById(R.id.image);
        Glide.with(this)
                .load(imageUrl)
                .into(imageView);
    }
    public void addnewuser( String title, String usernametxt, String passwordtxt)
    {
        User user = new User(usernametxt,passwordtxt);

        databaseReference.child(firebaseUser.getUid()).child(title).setValue(user);
    }

}
