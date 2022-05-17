package com.example.securenotes;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Notes extends ListActivity {

    ArrayList<String> note_list = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if ((int) getIntent().getSerializableExtra("true") == 1){
            note_list.add("Заметка " + String.valueOf(note_list.size() + 1));

            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String user_uid = user.getUid();
            FirebaseDatabase database = FirebaseDatabase.getInstance("https://notes-46688-default-rtdb.firebaseio.com/");
            DatabaseReference user_data = database.getReference("User: " + user_uid);

            user_data.child("Заметка " + String.valueOf(note_list.size())).setValue("");

        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, note_list);
        setListAdapter(adapter);
    }
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent note = new Intent(Notes.this, Note.class);
        note.putExtra("Index", position);
        startActivity(note);
    }
}
