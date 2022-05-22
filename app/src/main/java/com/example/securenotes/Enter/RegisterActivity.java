package com.example.securenotes.Enter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.securenotes.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private EditText email_register;
    private EditText password_register;
    private Button btn_register;
    private FirebaseAuth fAuth;
    private TextView help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        fAuth = FirebaseAuth.getInstance();
        email_register = findViewById(R.id.email_register);
        password_register = findViewById(R.id.password_register);
        btn_register = findViewById(R.id.btn_register);
        help = findViewById(R.id.help);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(password_register.getText().toString().isEmpty() || email_register.getText().toString().isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Введите данные!", Toast.LENGTH_LONG).show();
                }
                else {
                    fAuth.createUserWithEmailAndPassword(email_register.getText().toString(), password_register.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Intent login = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(login);
                            }
                            else {
                                Toast.makeText(RegisterActivity.this, "Попробуйте позже...", Toast.LENGTH_LONG);
                            }
                        }
                    });
                }
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(RegisterActivity.this, "Пароль должен состоять минимум из 6 символов", Toast.LENGTH_LONG).show();
            }
        });


    }
}