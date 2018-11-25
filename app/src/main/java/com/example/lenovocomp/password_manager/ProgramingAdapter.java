package com.example.lenovocomp.password_manager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class ProgramingAdapter extends RecyclerView.Adapter<ProgramingAdapter.ProgrammingViewHolder>{
    private ArrayList<String> arrayList;
    private static final String TAG = "hi";
    public Context mcontext1;




    public ProgramingAdapter(ProfileList profileList, ArrayList<String> marraylist) {

     arrayList=   marraylist;
     mcontext1=profileList;
    }


    public class ProgrammingViewHolder extends RecyclerView.ViewHolder  {

        ImageView Img;
        TextView Title;
        public RelativeLayout parentlayout1;

        public ProgrammingViewHolder(View itemView) {
            super(itemView);

          Img= (ImageView) itemView.findViewById(R.id.imageView);
           Title=(TextView) itemView.findViewById(R.id.title);
           parentlayout1= (RelativeLayout) itemView.findViewById(R.id.parentlayout1);
            Log.i( TAG,"ONBIND");
        }

    }

    @Override
    public ProgrammingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater =LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.view,parent,false);
        return new ProgrammingViewHolder(view);


    }

    @Override
    public void onBindViewHolder(final ProgrammingViewHolder holder, final int position) {
       //holder.Img.setImageResource(pics[position]);
        holder.Title.setText(arrayList.get(position));



  /*      holder.parentlayout1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(mcontext1,DataActivity.class);
                intent1.putExtra("title",titles[position]);
                intent1.putExtra("pics",pics[position]);
                Log.d(TAG, "onClick: ");
                intent1.putExtra("username",username1);


                 mcontext1.startActivity(intent1);
            }
        });

*/
    }



    @Override
    public int getItemCount()
    {
        return arrayList.size() ;
    }
}
