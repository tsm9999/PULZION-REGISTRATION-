package com.pasc.tanush.registrationmodule;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText userName, passsWord;
    Button login;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.login);
        userName = findViewById(R.id.input_email);
        passsWord = findViewById(R.id.input_password);
        firebaseAuth = FirebaseAuth.getInstance();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = userName.getText().toString().trim();
                String password = passsWord.getText().toString().trim();
                if (!funuser()) {
                    Toast.makeText(LoginActivity.this, "Enter Required Fields", Toast.LENGTH_SHORT).show();
                    passsWord.setError("Mandatory");
                }
                if (!funupass()) {
                    Toast.makeText(LoginActivity.this, "Enter Required Fields", Toast.LENGTH_SHORT).show();
                    userName.setError("Mandatory");
                } else {
                    firebaseAuth.signInWithEmailAndPassword(mail, password)
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {

                                    startActivity(new Intent(LoginActivity.this, EventRegistrationActivity.class));
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(LoginActivity.this, "Incorrect Username or Password", Toast.LENGTH_SHORT).show();
                                }
                            });

                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    boolean funuser() {
        if (userName.getText().toString().isEmpty()) {
            //collegeName.setError("Mandatory");
            return false;
        } else
            return true;

    }

    boolean funupass() {
        if (passsWord.getText().toString().isEmpty()) {
            //collegeName.setError("Mandatory");
            return false;
        } else
            return true;

    }

}
