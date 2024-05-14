package com.example.personalshopperapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class UserChoiceSpecialty extends AppCompatActivity {

    Button button_findPS;
    RadioGroup radioGroup_specialty;
    RadioButton radioButton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_choice_specialty);

        radioGroup_specialty = findViewById(R.id.radioGroup_specialty);

        button_findPS = (Button) findViewById(R.id.button_findPS);
        button_findPS.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openActivity2();
                int radioId1 = radioGroup_specialty.getCheckedRadioButtonId();
                radioButton1 = findViewById(radioId1);
            }
        });
    }

    public void checkButton(View v){
        int radioId1 = radioGroup_specialty.getCheckedRadioButtonId();
        radioButton1 = findViewById(radioId1);
    }

    public void openActivity2(){
        String specialty = radioButton1.getText().toString();
        Intent intent = new Intent(this, Home1.class);
        intent.putExtra("email",getIntent().getStringExtra("email"));
        intent.putExtra("location",getIntent().getStringExtra("location"));
        intent.putExtra("gender",getIntent().getStringExtra("gender"));
        intent.putExtra("specialty",specialty);
        startActivity(intent);
    }
}