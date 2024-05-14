package com.example.personalshopperapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class UpdateProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_profile);

        DatabaseReference DatabaseRef;
        DatabaseReference SpecificRef;



        EditText FirstName,LastName,Gender,Age,PhoneNum,Address,CityCode,City,State,ImageURL;
        Button updateAccount;



        FirstName = (EditText) findViewById(R.id.FirstName);
        LastName = (EditText) findViewById(R.id.LastName);
        Gender = (EditText) findViewById(R.id.Gender);
        Age = (EditText) findViewById(R.id.Age);
        PhoneNum = (EditText) findViewById(R.id.PhoneNum);
        Address = (EditText) findViewById(R.id.Address);
        CityCode = (EditText) findViewById(R.id.CityCode);
        City = (EditText) findViewById(R.id.City);
        State = (EditText) findViewById(R.id.State);
        ImageURL = (EditText) findViewById(R.id.ImageURL);

        DatabaseRef = FirebaseDatabase.getInstance().getReference("ClientDB");
        DatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    //Client Client = list.get(position);

                    Client Client = dataSnapshot.getValue(Client.class);
                    if(Client.getEmail().equalsIgnoreCase(getIntent().getStringExtra("email"))) {

                        FirstName.setText(Client.getFirstName());
                        LastName.setText(Client.getLastName());
                        Gender.setText(Client.getGender());
                        Age.setText(Client.getAge());
                        PhoneNum.setText(Client.getPhoneNum());
                        Address.setText(Client.getAddress());
                        CityCode.setText(Client.getCityCode());
                        City.setText(Client.getCity());
                        State.setText(Client.getState());
                        ImageURL.setText(Client.getImageURL());


                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        updateAccount = (Button) findViewById(R.id.UpdateAccount);
        updateAccount.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                String txtFirstName = FirstName.getText().toString();
                String txtLastName = LastName.getText().toString();
                String txtGender = Gender.getText().toString();
                String txtAge = Age.getText().toString();
                String txtPhoneNum = PhoneNum.getText().toString();
                String txtAddress = Address.getText().toString();
                String txtCityCode = CityCode.getText().toString();
                String txtCity = City.getText().toString();
                String txtState = State.getText().toString();
                String txtImageURL = ImageURL.getText().toString();


                DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
                DatabaseReference additionalUserInfoRef = rootRef.child("ClientDB");
                Query userQuery = additionalUserInfoRef.orderByChild("Email").equalTo(getIntent().getStringExtra("email"));
                ValueEventListener valueEventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot ds : dataSnapshot.getChildren()) {
                            Map<String, Object> map = new HashMap<>();
                            map.put("FirstName", txtFirstName);
                            map.put("LastName", txtLastName);
                            map.put("Gender", txtGender);
                            map.put("Age", txtAge);
                            map.put("PhoneNum", txtPhoneNum);
                            map.put("Address", txtAddress);
                            map.put("CityCode", txtCityCode);
                            map.put("City", txtCity);
                            map.put("State", txtState);
                            map.put("ImageURL", txtImageURL);
                            ds.getRef().updateChildren(map);
                            Toast.makeText(getApplicationContext(),"Updated Successfully",Toast.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(UpdateProfile.this,"Your Data Fail to Update", Toast.LENGTH_SHORT).show();

                    }
                };
                userQuery.addListenerForSingleValueEvent(valueEventListener);

                Intent intent = new Intent(UpdateProfile.this, ViewProfile.class);
                intent.putExtra("email",getIntent().getStringExtra("email"));
                startActivity(intent);
            }
        });



    }
}