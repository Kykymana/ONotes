package com.example.securenotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private Button gosite;
    private Button github;
    private Button google;
    private Button yandex;
    private Button paint;
    private EditText sitetext;
    private EditText test;
    private TextView t1;
    private Button notes;
    private Button create_note;

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
        test = findViewById(R.id.test);
        t1 = findViewById(R.id.t1);
        notes = findViewById(R.id.save);
        create_note = findViewById(R.id.load);


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
        notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent notes = new Intent(MainActivity.this, Notes.class);
                startActivity(notes);
            }
        });
        create_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent notes = new Intent(MainActivity.this, Notes.class);
                notes.putExtra("true", 1);
                startActivity(notes);
            }
        });

    }
}