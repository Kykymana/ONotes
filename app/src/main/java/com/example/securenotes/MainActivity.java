package com.example.securenotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.securenotes.Note.Notes;
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
    private EditText sitetext;
    private Button notes;
    private Button create_note;

    private int counter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String user_uid = user.getUid();
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://notes-46688-default-rtdb.firebaseio.com/");
        DatabaseReference user_data = database.getReference("User: " + user_uid);

        gosite = findViewById(R.id.gosite);
        github = findViewById(R.id.github);
        google = findViewById(R.id.google);
        yandex = findViewById(R.id.yandex);
        sitetext = findViewById(R.id.sitetext);
        notes = findViewById(R.id.save);
        create_note = findViewById(R.id.load);

        user_data.child("Counter").child("Count").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue(String.class) != null){
                    int count = Integer.parseInt(dataSnapshot.getValue(String.class)) + 1;
                    counter = count;
                }
                else {
                    user_data.child("Counter").child("Count").setValue("0");
                }
                 }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        gosite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gosite = new Intent(Intent.ACTION_VIEW);
                gosite.setData(Uri.parse("https://" + sitetext.getText().toString()));
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
                Intent create_notes = new Intent(MainActivity.this, Notes.class);

                user_data.child("Counter").child("Count").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int count = Integer.parseInt(dataSnapshot.getValue(String.class)) + 1;
                        counter = count;
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        user_data.child("Counter").child("Count").setValue("1");
                    }
                });

                user_data.child("Заметка " + String.valueOf(counter)).setValue("");
                user_data.child("Counter").child("Count").setValue(String.valueOf(counter));

                startActivity(create_notes);
            }
        });

    }
}