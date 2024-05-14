package com.example.personalshopperapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class FilterGender extends AppCompatActivity {

    ImageButton CancelButton;
    Button button_location_17;
    Button button_gender_17;
    Button button_specialty_17;
    RadioGroup radioGroup_gender;
    RadioButton radioButton1;
    Button button_findPS_17;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter_gender);

        radioGroup_gender = findViewById(R.id.radioGroup_gender);

        CancelButton = (ImageButton) findViewById(R.id.CancelButton);
        CancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openActivity2();
            }
        });

        button_location_17 = (Button) findViewById(R.id.button_location_17);
        button_location_17.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                openActivity3();

            }
        });

        button_gender_17 = (Button) findViewById(R.id.button_gender_17);
        button_gender_17.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openActivity4();
            }
        });

        button_specialty_17 = (Button) findViewById(R.id.button_specialty_17);
        button_specialty_17.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openActivity5();
            }
        });

        button_findPS_17 = (Button) findViewById(R.id.button_findPS_17);
        button_findPS_17.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openActivity6();
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
        Intent intent = new Intent(this, Home2.class);
        intent.putExtra("email",getIntent().getStringExtra("email"));
        startActivity(intent);
    }

    public void openActivity3(){
        Intent intent = new Intent(this, FilterLocation.class);
        intent.putExtra("email",getIntent().getStringExtra("email"));
        startActivity(intent);
    }

    public void openActivity4(){
        Intent intent = new Intent(this, FilterGender.class);
        intent.putExtra("email",getIntent().getStringExtra("email"));
        startActivity(intent);
    }

    public void openActivity5(){
        Intent intent = new Intent(this, FilterSpecialty.class);
        intent.putExtra("email",getIntent().getStringExtra("email"));
        startActivity(intent);
    }

    public void openActivity6(){
        String gender = radioButton1.getText().toString();
        Intent intent = new Intent(this, Home4.class);
        intent.putExtra("gender",gender);
        intent.putExtra("email",getIntent().getStringExtra("email"));
        startActivity(intent);
    }
}