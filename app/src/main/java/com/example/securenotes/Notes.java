package com.example.securenotes;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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

import java.util.ArrayList;

public class Notes extends ListActivity {







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String user_uid = user.getUid();
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://notes-46688-default-rtdb.firebaseio.com/");
        DatabaseReference user_data = database.getReference("User: " + user_uid);

        user_data.child("Counter").child("Count").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int count = Integer.parseInt(dataSnapshot.getValue(String.class)) + 1;

                ArrayList<String> note_list = new ArrayList<String>();

                for (int i = 1; i < count; i++) {
                    note_list.add("Заметка " + String.valueOf(i));

                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, note_list);
                setListAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent note = new Intent(Notes.this, Note.class);
        note.putExtra("Index", position);
        startActivity(note);
    }
}
