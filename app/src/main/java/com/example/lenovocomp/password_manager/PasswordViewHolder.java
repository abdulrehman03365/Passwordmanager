package com.example.lenovocomp.password_manager;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public  class PasswordViewHolder extends RecyclerView.ViewHolder {
    public View view;
    public PasswordViewHolder(View itemView) {
        super(itemView);
      view=  itemView;
    }

    ImageView    Img= (ImageView) itemView.findViewById(R.id.imageView);
    TextView Title=(TextView) itemView.findViewById(R.id.title);
    RelativeLayout parentlayout1= (RelativeLayout) itemView.findViewById(R.id.parentlayout1);

   public  void setTitle(String title)
   {
       Title.setText(title);
   }


}
