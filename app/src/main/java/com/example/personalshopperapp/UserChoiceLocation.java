package com.example.personalshopperapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class UserChoiceLocation extends AppCompatActivity {

    Button NextButton;
    RadioGroup radioGroup_location;
    RadioButton radioButton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_choice_location);

        radioGroup_location = findViewById(R.id.radioGroup_location);

        NextButton = (Button) findViewById(R.id.NextButton);
        NextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                openActivity2();

                int radioId1 = radioGroup_location.getCheckedRadioButtonId();
                radioButton1 = findViewById(radioId1);
            }
        });
    }

    public void checkButton(View v){
        int radioId1 = radioGroup_location.getCheckedRadioButtonId();
        radioButton1 = findViewById(radioId1);
    }

    public void openActivity2(){
        String location = radioButton1.getText().toString();

        Intent intent = new Intent(this, UserChoiceGender.class);
        intent.putExtra("email",getIntent().getStringExtra("email"));
        intent.putExtra("location",location);
        startActivity(intent);
    }
}