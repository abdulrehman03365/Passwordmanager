package com.example.lenovocomp.password_manager;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ProfileService extends Service {
    public DatabaseReference db;
    Intent intent;
    public String iamlog="hy";
    String dbref;

    public ArrayList<String> marraylist = new ArrayList<>() ;

    public IBinder mbinder = new Localservice();
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * Used to name the worker thread, important only for debugging.
     */


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        dbref  =intent.getStringExtra("dbrefuri");
        return  mbinder;
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     */


    public     class  Localservice extends Binder
    {
        public   ProfileService getservice()
        {
            return ProfileService.this;
        }
    }

    public  ArrayList<String> getdata()
    {
        db=  FirebaseDatabase.getInstance().getReferenceFromUrl(dbref);
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren())
                {
                    marraylist.add(dataSnapshot2.getKey());
                }
                Log.e(iamlog, "OnDatachange in service" +marraylist);
                Toast toast =  Toast.makeText(ProfileService.this ,String.valueOf(marraylist),Toast.LENGTH_LONG);
                toast.show();
                Intent broadcast = new Intent(ProfileService.this ,ProfileList.class);
                intent.putExtra("arraylisy",String.valueOf(marraylist));
                broadcast.setAction("Broadcast");
                broadcast.setFlags(Intent.FLAG_EXCLUDE_STOPPED_PACKAGES);
                sendBroadcast(broadcast);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return marraylist;
    }

}
