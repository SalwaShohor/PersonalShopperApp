package com.example.personalshopperapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.Toast;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Home2 extends AppCompatActivity{


    ImageButton button3;
    RecyclerView recyclerView;
    DatabaseReference database;
    DatabaseReference database2;
    DatabaseReference database3;
    Adapter adapter;
    ArrayList<PersonalShopper> list;
    ArrayList<Client> list2;
    AutoCompleteTextView ClientLocation;
    //EditText SearchPS;
    FloatingActionButton profileButton;
    FloatingActionButton logOutButton;
    //ImageButton SearchButton;
    boolean clicked=false;
    String Client_location = "";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home2);

        recyclerView = findViewById(R.id.recview);
        database = FirebaseDatabase.getInstance().getReference("PersonalShopperDB");
        database2 = FirebaseDatabase.getInstance().getReference("ClientDB");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        adapter = new Adapter(this,list);
        recyclerView.setAdapter(adapter);

        ClientLocation = (AutoCompleteTextView) findViewById(R.id.ClientLocation);




        database2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Client client = dataSnapshot.getValue(Client.class);
                    if(client.getEmail().equalsIgnoreCase(getIntent().getStringExtra("email")))
                    {
                        ClientLocation.setText(client.getCity());
                    }
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    PersonalShopper personalShopper = dataSnapshot.getValue(PersonalShopper.class);
                    database2.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                                Client client = dataSnapshot.getValue(Client.class);
                                if(client.getEmail().equalsIgnoreCase(getIntent().getStringExtra("email"))
                                        && client.getCity().equalsIgnoreCase(personalShopper.getCity()))
                                {
                                    list.add(personalShopper);
                                }
                            }
                            adapter.notifyDataSetChanged();
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                }adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });



        button3 = (ImageButton) findViewById(R.id.filterButton);
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openActivity2();
            }
        });

        profileButton = findViewById(R.id.ProfileButton);
        profileButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openActivity3();
            }
        });

        logOutButton = findViewById(R.id.LogOutButton);
        logOutButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                logout();
                //openActivity4();
            }
        });

    }

    private void filter(String text) {

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){


                    PersonalShopper PersonalShopper = dataSnapshot.getValue(PersonalShopper.class);

                    if(PersonalShopper.getFirstName().equalsIgnoreCase(text) &&
                            PersonalShopper.getLastName().equalsIgnoreCase(text))
                    {
                        list.add(PersonalShopper);
                    }

                }adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }



    public void logout(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Home2.this);

        alertDialog.setTitle("Logout"); // Sets title for your alertbox

        alertDialog.setMessage("Are you sure you want to Logout ?"); // Message to be displayed on alertbox

        alertDialog.setIcon(R.drawable.ic_logout); // Icon for your alertbox

        /* When positive (yes/ok) is clicked */
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
                finish();
                Toast.makeText(Home2.this,"Successfully Logged Out", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Home2.this, Login.class);
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





    public void openActivity2(){
        Intent intent = new Intent(this, FilterLocation.class);
        intent.putExtra("email",getIntent().getStringExtra("email"));
        startActivity(intent);
    }

    public void openActivity3(){

        Intent intent = new Intent(this, ViewProfile.class);
        intent.putExtra("email",getIntent().getStringExtra("email"));
        startActivity(intent);
    }




}