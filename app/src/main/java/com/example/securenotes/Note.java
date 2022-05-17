package com.example.securenotes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Note extends AppCompatActivity {

    private EditText note;
    private int index;
    private Button save;
    private String note_value;

    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        note = findViewById(R.id.note);
        save = findViewById(R.id.save);



        index = (int) getIntent().getSerializableExtra("Index");

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String user_uid = user.getUid();
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://notes-46688-default-rtdb.firebaseio.com/");
        DatabaseReference user_data = database.getReference("User: " + user_uid);

        user_data.child("Заметка " + String.valueOf(index + 1)).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    note.setText(task.getResult().getValue().toString().replace("[", "").replace("]", ""));
                }
                else {
                }

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tittle = String.valueOf(note.getText());
                user_data.child("Заметка " + String.valueOf(index + 1)).setValue(tittle);
                //Intent notes = new Intent(Note.this, Notes.class);
                //startActivity(notes);
            }
        });

    }

}
