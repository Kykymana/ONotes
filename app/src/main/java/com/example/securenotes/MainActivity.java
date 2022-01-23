package com.example.securenotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private Button gosite;
    private Button github;
    private Button google;
    private Button yandex;
    private Button paint;
    private EditText sitetext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gosite = findViewById(R.id.gosite);
        github = findViewById(R.id.github);
        google = findViewById(R.id.google);
        yandex = findViewById(R.id.yandex);
        paint  = findViewById(R.id.paint);
        sitetext = findViewById(R.id.sitetext);

        gosite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gosite = new Intent(Intent.ACTION_VIEW);
                gosite.setData(Uri.parse("https://" + sitetext.getText().toString() + ".com"));
                startActivity(gosite);
            }
        });
        github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent github = new Intent(Intent.ACTION_VIEW);
                github.setData(Uri.parse("https://github.com"));
                startActivity(github);
            }
        });
        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent google = new Intent(Intent.ACTION_VIEW);
                google.setData(Uri.parse("https://google.com"));
                startActivity(google);
            }
        });
        yandex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent yandex = new Intent(Intent.ACTION_VIEW);
                yandex.setData(Uri.parse("https://yandex.com"));
                startActivity(yandex);
            }
        });
        paint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent paint = new Intent(MainActivity.this, Painting.class);
                startActivity(paint);
            }
        });
    }
}