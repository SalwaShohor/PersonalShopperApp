package com.example.personalshopperapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserChoice extends AppCompatActivity {

    Button YesButton;
    Button NoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_choice);

        YesButton = (Button) findViewById(R.id.YesButton);
        YesButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openActivity2();
            }
        });

        NoButton = (Button) findViewById(R.id.NoButton);
        NoButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openActivity3();
            }
        });
    }

    public void openActivity2(){
        Intent intent = new Intent(this, UserChoiceLocation.class);
        intent.putExtra("email",getIntent().getStringExtra("email"));
        startActivity(intent);
    }

    public void openActivity3(){
        Intent intent = new Intent(this, Home2.class);
        intent.putExtra("email",getIntent().getStringExtra("email"));
        startActivity(intent);
    }
}