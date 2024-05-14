package com.example.personalshopperapp;


import android.content.Context;
import android.content.Intent;

import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerOptions;


import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    Context context;
    ArrayList<PersonalShopper> list;




    public Adapter(Context context, ArrayList<PersonalShopper> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.personal_shopper_layout,parent,false);
        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        PersonalShopper PersonalShopper = list.get(position);

        holder.FirstName.setText(PersonalShopper.getFirstName() + " " + PersonalShopper.getLastName());
        holder.Address.setText(PersonalShopper.getAddress() + ", " + PersonalShopper.getCity());
        holder.Specialists.setText(PersonalShopper.getSpecialists());
        holder.PhoneNum.setText(PersonalShopper.getPhoneNum());
        Glide.with(holder.img.getContext()).load(PersonalShopper.getImageURL()).into(holder.img);
        holder.contactPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String message = "Hi, I would like to use your Personal Shopper Service !";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+"+"+PersonalShopper.getPhoneNum() + "&text="+message));
                context.startActivity(intent);


            }
        });


    }

    //create method appInstalledOrNot



    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        CircleImageView img;
        TextView FirstName, Address, Specialists,PhoneNum;
        Button contactPS;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img = (CircleImageView) itemView.findViewById(R.id.img1);
            FirstName = (TextView) itemView.findViewById(R.id.name);
            Address = (TextView) itemView.findViewById(R.id.address);
            Specialists = (TextView) itemView.findViewById(R.id.specialists);
            PhoneNum = (TextView) itemView.findViewById(R.id.phoneNum);
            contactPS = (Button) itemView.findViewById(R.id.contactPS);

        }


    }

    public void filterList(ArrayList<PersonalShopper> filteredList) {
        list = filteredList;
        notifyDataSetChanged();
    }



}
