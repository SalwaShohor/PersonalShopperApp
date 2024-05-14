package com.example.personalshopperapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class Login extends AppCompatActivity {

    private long backPressedTime;
    private Toast backToast;

    private Button button_login, button_createAccount;
    EditText Email, Password;

    DatabaseReference database;

    FirebaseAuth mAuth;
    Bundle bundle;


    @Override
    public void onBackPressed() {
        Toast.makeText(Login.this, "Press home to exit", Toast.LENGTH_SHORT).show();


    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        //database = FirebaseDatabase.getInstance().getReference("ClientDB");

        Email = (EditText) findViewById(R.id.email);
        Password = (EditText) findViewById(R.id.password);

        mAuth = FirebaseAuth.getInstance();


        button_login = (Button) findViewById(R.id.LoginButton);
        button_login.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                    String txt_email = Email.getText().toString().trim();
                    String txt_password = Password.getText().toString().trim();



                    if (TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)){
                        Toast.makeText(Login.this, "Empty Credentials!", Toast.LENGTH_SHORT).show();
                    } else {


                        loginUser(txt_email , txt_password);

                        Intent intent = new Intent(getBaseContext() , UserChoice.class);
                        intent.putExtra("email",txt_email);
                        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        //finish();
                    }


                }

        });

        button_createAccount = (Button) findViewById(R.id.button4);
        button_createAccount.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openActivity3();
            }
        });



    }


    private void loginUser(String email, String password) {


        mAuth.signInWithEmailAndPassword(email , password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();




                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void openActivity3(){
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    public void openActivity4(){
        Intent intent = new Intent(this, UserChoice.class);
        intent.putExtra("email",getIntent().getStringExtra("email"));
        startActivity(intent);
    }





}