package com.example.personalshopperapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {


    EditText txtFirstName,txtLastName,txtAge,txtPhoneNum,txtEmail,txtPassword,txtAddress,txtCityCode,txtCity,txtState,txtImageURL;
    Spinner txtGender;
    Button createAccount;
    FirebaseAuth fAuth;
    String Email="";
    String Password="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);



        txtFirstName     = findViewById(R.id.FirstName);
        txtLastName     = findViewById(R.id.LastName);
        txtGender    = findViewById(R.id.Gender);
        txtAge  = findViewById(R.id.Age);
        txtPhoneNum     = findViewById(R.id.PhoneNum);
        txtEmail     = findViewById(R.id.Email);
        txtPassword    = findViewById(R.id.Password);
        txtAddress  = findViewById(R.id.Address);
        txtCityCode     = findViewById(R.id.CityCode);
        txtCity     = findViewById(R.id.City);
        txtState    = findViewById(R.id.State);
        txtImageURL = findViewById(R.id.ImageURL);

        ArrayAdapter<String>myAdapter = new ArrayAdapter<String>(Register.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.txtGender));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        txtGender.setAdapter(myAdapter);

        fAuth = FirebaseAuth.getInstance();

        createAccount = (Button) findViewById(R.id.CreateAccount);
        createAccount.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                openActivity2();


                Map<String,Object> map=new HashMap<>();
                map.put("FirstName",txtFirstName.getText().toString());
                map.put("LastName",txtLastName.getText().toString());
                map.put("Gender",txtGender.getSelectedItem().toString());
                map.put("Age",txtAge.getText().toString());
                map.put("PhoneNum",txtPhoneNum.getText().toString());
                map.put("Email",txtEmail.getText().toString());
                map.put("Password",txtPassword.getText().toString());
                map.put("Address",txtAddress.getText().toString());
                map.put("CityCode",txtCityCode.getText().toString());
                map.put("City",txtCity.getText().toString());
                map.put("State",txtState.getText().toString());
                map.put("ImageURL",txtImageURL.getText().toString());



                FirebaseDatabase.getInstance().getReference().child("ClientDB").push()
                        .setValue(map)
                        .addOnSuccessListener(new OnSuccessListener<Void>(){
                            @Override
                            public void onSuccess(Void aVoid){
                                txtFirstName.setText("");
                                txtLastName.setText("");
                                txtGender.setAdapter(myAdapter);
                                txtAge.setText("");
                                txtPhoneNum.setText("");
                                txtEmail.setText("");
                                txtPassword.setText("");
                                txtAddress.setText("");
                                txtCityCode.setText("");
                                txtCity.setText("");
                                txtState.setText("");
                                txtImageURL.setText("");


                                Toast.makeText(getApplicationContext(),"Inserted Successfully",Toast.LENGTH_LONG).show();

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e){
                                Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_LONG).show();


                            }
                        });

                Email = txtEmail.getText().toString();
                Password = txtPassword.getText().toString();


                fAuth.createUserWithEmailAndPassword(Email,Password)
                        .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(getApplicationContext(),"Authentication Successful",Toast.LENGTH_LONG).show();

                                if (!task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(),"Authentication Failed",Toast.LENGTH_LONG).show();
                                }
                            }
                        });



            }
        });



    }



    public void openActivity2(){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


}
