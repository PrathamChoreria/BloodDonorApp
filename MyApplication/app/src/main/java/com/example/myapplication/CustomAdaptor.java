package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdaptor extends RecyclerView.Adapter<CustomAdaptor.MyViewHolder> {

   private android.content.Context context;
   Activity activity;
   private ArrayList blood_id,name,phone,blood_group;

   int position;
 Animation translate_anim;
    CustomAdaptor(Activity activity,android.content.Context context,
                  ArrayList blood_id,
                  ArrayList name,
                  ArrayList phone,
                  ArrayList blood_group){
        this.activity=activity;
        this.context=context;
        this.blood_id=blood_id;
        this.name=name;
        this.phone=phone;
        this.blood_group=blood_group;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {


        holder.blood_id_txt.setText(String.valueOf(blood_id.get(position)));
        holder.blood_name_txt.setText(String.valueOf(name.get(position)));
        holder.blood_phone_txt.setText(String.valueOf(phone.get(position)));
        holder.blood_group_txt.setText(String.valueOf(blood_group.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,UpdateActivity.class);
                intent.putExtra("id",String.valueOf(blood_id.get(holder.getAdapterPosition())));
                intent.putExtra("name",String.valueOf(name.get(holder.getAdapterPosition())));
                intent.putExtra("phone",String.valueOf(phone.get(holder.getAdapterPosition())));
                intent.putExtra("bloodgroup",String.valueOf(blood_group.get(holder.getAdapterPosition())));
                activity.startActivityForResult(intent,1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return blood_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView blood_id_txt,blood_name_txt,blood_phone_txt,blood_group_txt;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            blood_id_txt=itemView.findViewById(R.id.blood_id_txt);
            blood_name_txt=itemView.findViewById(R.id.blood_name_txt);
            blood_phone_txt=itemView.findViewById(R.id.blood_phone_txt);
            blood_group_txt=itemView.findViewById(R.id.blood_group_txt);
            mainLayout=itemView.findViewById(R.id.mainLayout);
            translate_anim= AnimationUtils.loadAnimation(context,R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }
    }
}
