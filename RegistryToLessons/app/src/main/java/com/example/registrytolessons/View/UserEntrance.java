package com.example.registrytolessons.View;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.registrytolessons.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class UserEntrance extends AppCompatActivity {

    private EditText mEmail, mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize components
        mEmail = findViewById(R.id.emailText);
        mPassword = findViewById(R.id.passwordText);
        Button mLoginBtn = findViewById(R.id.btnEntrance);

        whichUserType();

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();

                if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
                    userLogin(email, password);
                }
            }
        });


    }

    private void whichUserType() {
        FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
        final CollectionReference usersRef = rootRef.collection("users");
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            String uid = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
            usersRef.document(uid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot documentSnapshot = task.getResult();
                        assert documentSnapshot != null;
                        if (documentSnapshot.exists()) {
                            String type = documentSnapshot.getString("type");
                            assert type != null;
                            switch (type) {
                                case "student":
                                    Toast.makeText(UserEntrance.this, "Student",
                                            Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(UserEntrance.this, StudentPanel.class));
                                    break;
                                case "teacher":
                                    Toast.makeText(UserEntrance.this, "Teacher",
                                            Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(UserEntrance.this, TeacherPanel.class));
                                    break;
                                case "admin":
                                    Toast.makeText(UserEntrance.this, "ADMIN", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(UserEntrance.this, AdminUserRegistration.class));
                                    break;
                            }
                            finish();
                        }
                    }
                }
            });
        }
    }

    private void userLogin(final String email, final String password) {
        FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
        final CollectionReference usersRef = rootRef.collection("users");
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    String uid = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
                    usersRef.document(uid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot documentSnapshot = task.getResult();
                                assert documentSnapshot != null;
                                if (documentSnapshot.exists()) {
                                    String type = documentSnapshot.getString("type");
                                    assert type != null;
                                    switch (type) {
                                        case "student":
                                            Toast.makeText(UserEntrance.this, "Student",
                                                    Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(UserEntrance.this, StudentPanel.class));
                                            break;
                                        case "teacher":
                                            Toast.makeText(UserEntrance.this, "Teacher",
                                                    Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(UserEntrance.this, TeacherPanel.class));
                                            break;
                                        case "admin":
                                            Toast.makeText(UserEntrance.this, "admin",
                                                    Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(UserEntrance.this, AdminPanel.class));
                                            break;
                                    }
                                    finish();
                                }
                            }
                        }
                    });
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(UserEntrance.this, Objects.requireNonNull(task.getException()).getLocalizedMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}