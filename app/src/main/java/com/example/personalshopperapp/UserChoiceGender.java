package com.example.personalshopperapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class UserChoiceGender extends AppCompatActivity {

    Button NextButton;
    RadioGroup radioGroup_gender;
    RadioButton radioButton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_choice_gender);

        radioGroup_gender = findViewById(R.id.radioGroup_gender);

        NextButton = (Button) findViewById(R.id.NextButton);
        NextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                openActivity2();
                int radioId1 = radioGroup_gender.getCheckedRadioButtonId();
                radioButton1 = findViewById(radioId1);
            }
        });
    }

    public void checkButton(View v){
        int radioId1 = radioGroup_gender.getCheckedRadioButtonId();
        radioButton1 = findViewById(radioId1);
    }

    public void openActivity2(){
        String gender = radioButton1.getText().toString();
        Intent intent = new Intent(this, UserChoiceSpecialty.class);
        intent.putExtra("email",getIntent().getStringExtra("email"));
        intent.putExtra("location",getIntent().getStringExtra("location"));
        intent.putExtra("gender",gender);
        startActivity(intent);
    }
}