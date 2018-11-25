package com.example.lenovocomp.password_manager;

import android.app.Dialog;
import android.app.IntentService;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.IBinder;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.example.lenovocomp.password_manager.ProfileService.Localservice;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProfileList extends AppCompatActivity implements View.OnClickListener  {
    private FirebaseAuth mAuth;
    Dialog Mydialog;
    private FirebaseAuth.AuthStateListener authStateListener;
    public DatabaseReference db;
    public String userid;
    FirebaseUser firebaseUser;
    public String iamlog="hy";
    List<String> listSt;
    String snapdata,log;
    ArrayList<String> m1arraylist = new ArrayList <String>();
    RecyclerView recyclerView;
    public ProfileService myservice;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profilelist);
        db = FirebaseDatabase.getInstance().getReference().child("users");
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button button2 = (Button) findViewById(R.id.button2);
        mAuth = FirebaseAuth.getInstance();
        userid = mAuth.getCurrentUser().getUid();
        db = db.child(userid);
        Uri uri = Uri.parse(String.valueOf(db));
        Log.e(iamlog,String.valueOf(uri));

        Intent Profileserviceint = new Intent(this, ProfileService.class);
        Profileserviceint.putExtra("dbrefuri",String.valueOf(uri));
        bindService(Profileserviceint, connection, Context.BIND_AUTO_CREATE);

    }





    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Localservice localservice = (Localservice) service;
            myservice=localservice.getservice();
            m1arraylist=myservice.getdata();
            recyclerView.setAdapter(new ProgramingAdapter(ProfileList.this,localservice.getservice().marraylist));
            Log.e(iamlog,"Profile calss "+ String.valueOf(localservice.getservice().marraylist));


        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }

        class Reciever extends BroadcastReceiver
        {

            @Override
            public void onReceive(Context context, Intent intent) {
                String marray=intent.getStringExtra("Broadcast");
                List<String> myList = new ArrayList<String>(new ArrayList(Arrays.asList(marray.split(","))));
                ;
                Log.e(iamlog,marray);

            }
        }

    };


    @Override
    public void onClick(View v) {

    }




}









