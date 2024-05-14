package com.example.personalshopperapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ViewProfile extends AppCompatActivity {

    private Button btn_editData;
    DatabaseReference database;
    //Adapter adapter;
    ArrayList<Client> list;

    CircleImageView img;
    private TextView tvName, tvGender, tvAge, tvPhoneNum, tvAddress, tvCityCode, tvCity, tvState;
    EditText FirstName,LastName,Age,PhoneNum,Email,Password,Address,CityCode,City,State,ImageURL;
    Spinner Gender;
    Button updateAccount;

    FloatingActionButton HomeButton;
    FloatingActionButton logOutButton;
    FirebaseAuth fAuth;
    Button Update;
    Button Delete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_profile);


        img = (CircleImageView) findViewById(R.id.img1);

        tvName = (TextView) findViewById(R.id.tv_name);
        tvGender = (TextView) findViewById(R.id.tv_gender);
        tvAge = (TextView) findViewById(R.id.tv_age);
        tvPhoneNum = (TextView) findViewById(R.id.tv_phoneNum);
        tvAddress = (TextView) findViewById(R.id.tv_address);
        tvCityCode = (TextView) findViewById(R.id.tv_cityCode);
        tvCity = (TextView) findViewById(R.id.tv_city);
        tvState = (TextView) findViewById(R.id.tv_state);

        database = FirebaseDatabase.getInstance().getReference("ClientDB");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    //Client Client = list.get(position);

                    Client Client = dataSnapshot.getValue(Client.class);

                    if (Client.getEmail().equalsIgnoreCase(getIntent().getStringExtra("email"))) {

                        Glide.with(img.getContext()).load(Client.getImageURL()).into(img);
                        tvName.setText(Client.getFirstName() + " " + Client.getLastName());
                        tvGender.setText(Client.getGender());
                        tvAge.setText(Client.getAge());
                        tvPhoneNum.setText(Client.getPhoneNum());
                        tvAddress.setText(Client.getAddress());
                        tvCityCode.setText(Client.getCityCode());
                        tvCity.setText(Client.getCity());
                        tvState.setText(Client.getState());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Update = findViewById(R.id.btn_edit);
        Update.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openActivity5();
            }
        });

        Delete = findViewById(R.id.btn_delete);
        Delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(ViewProfile.this);

                alertDialog.setTitle("Delete"); // Sets title for your alertbox

                alertDialog.setMessage("Are you sure you want to delete your account ?"); // Message to be displayed on alertbox

                alertDialog.setIcon(R.drawable.ic_delete); // Icon for your alertbox

                /* When positive (yes/ok) is clicked */
                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {
                        deleteAccount();
                        finish();
                        Toast.makeText(ViewProfile.this,"Successfully Delete Account", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ViewProfile.this, Login.class);
                        startActivity(intent);
                    }
                });

                /* When negative (No/cancel) button is clicked*/
                alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertDialog.show();
            }
        });

        HomeButton = findViewById(R.id.HomeButton);
        HomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity3();
            }
//            intent.putExtra("email",getIntent().getStringExtra("email"));
//            startActivity(intent);

        });

        logOutButton = findViewById(R.id.LogOutButton);
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
                //openActivity4();
            }
        });



    }




    public void logout(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(ViewProfile.this);

        alertDialog.setTitle("Logout"); // Sets title for your alertbox

        alertDialog.setMessage("Are you sure you want to Logout ?"); // Message to be displayed on alertbox

        alertDialog.setIcon(R.drawable.ic_logout); // Icon for your alertbox

        /* When positive (yes/ok) is clicked */
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
                finish();

                Toast.makeText(ViewProfile.this,"Successfully Logged Out", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ViewProfile.this, Login.class);
                startActivity(intent);
            }
        });

        /* When negative (No/cancel) button is clicked*/
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();
    }

    public void deleteAccount(){
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference additionalUserInfoRef = rootRef.child("ClientDB");
        additionalUserInfoRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    for (DataSnapshot ds : task.getResult().getChildren()) {

                        Client Client = ds.getValue(Client.class);

                        //String value = ds.getValue(String.class);
                        if(Client.getEmail().equals(getIntent().getStringExtra("email"))) {
                            ds.getRef().removeValue();
                            Toast.makeText(getApplicationContext(),"Delete Account Successful",Toast.LENGTH_LONG).show();
                        }
                    }
                } else {
                    Toast.makeText(getApplicationContext(),"Delete Account Unsuccessful",Toast.LENGTH_LONG).show();
                }
            }
        });
    }



    public void openActivity3(){
        Intent intent = new Intent(this, Home2.class);
        intent.putExtra("email",getIntent().getStringExtra("email"));
        startActivity(intent);
    }


    public void openActivity5(){
        Intent intent = new Intent(this, UpdateProfile.class);
        intent.putExtra("email",getIntent().getStringExtra("email"));
        startActivity(intent);
    }




}