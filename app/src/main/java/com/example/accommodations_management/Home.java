package com.example.accommodations_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.widget.ImageView;
import android.view.View;

import android.os.Bundle;

public class Home extends AppCompatActivity {
    ImageView imageView_1,imageView_2,imageView_3,imageView_4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        imageView_1 = findViewById(R.id.imageView7);
        imageView_2 = findViewById(R.id.imageView9);
        imageView_3 = findViewById(R.id.imageView11);
        imageView_4 = findViewById(R.id.imageView10);

        imageView_1 .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, FristActivity.class);
                startActivity(intent); }


        });
        imageView_2 .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, SecondActivity.class);
                startActivity(intent); }


        });

        imageView_3 .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, ThirdActivity.class);
                startActivity(intent); }


        });
        imageView_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, FristActivity.class);
                startActivity(intent); }


        });

        imageView_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, FristActivity.class);
                startActivity(intent); }


        });




    }
}


