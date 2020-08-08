package com.example.registrytolessons.View;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.registrytolessons.Model.User;
import com.example.registrytolessons.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;


public class AdminUserRegistration extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner lessonsSpinner;
    Button RegistrationButton;
    EditText name, surname, email, password;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String userType = "";
    private String userSection = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_registration);

        initiate();
        firstCreateSpinner();
    }

    public void initiate() {
        name = findViewById(R.id.nameRegistry);
        surname = findViewById(R.id.surnameRegistry);
        email = findViewById(R.id.emailAddressRegistry);
        password = findViewById(R.id.passwordRegistry);
        RegistrationButton = findViewById(R.id.RegistrationButton);

        lessonsSpinner = findViewById(R.id.lessons_spinner);
    }

    public void firstCreateSpinner() {
        lessonsSpinner.setOnItemSelectedListener(this);
        // Creating adapter for spinner
        ArrayAdapter<CharSequence> lessonsDataAdapter = ArrayAdapter.createFromResource(this, R.array.lessons_array, android.R.layout.simple_spinner_item);
        // Drop down layout style - list view with radio button
        lessonsDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        lessonsSpinner.setAdapter(lessonsDataAdapter);
    }

    public void logOut(View view) {
        mAuth.signOut();
        startActivity(new Intent(this, UserEntrance.class));
        finish();
    }

    public void RegistryOnClick(View view) {

        final String userName = name.getText().toString();
        final String userSurname = surname.getText().toString();
        final String EmailAddress = email.getText().toString();
        String EmailPassword = password.getText().toString();

        if (userName.equals("")) {
            Toast.makeText(AdminUserRegistration.this, "User name can not be null.", Toast.LENGTH_SHORT).show();
        } else if (userSurname.equals("")) {
            Toast.makeText(AdminUserRegistration.this, "User surname can not be null.", Toast.LENGTH_SHORT).show();
        } else if (EmailAddress.equals("")) {
            Toast.makeText(AdminUserRegistration.this, "User email address can not be null.", Toast.LENGTH_SHORT).show();
        } else if (EmailPassword.equals("") || EmailPassword.length() <= 6) {
            Toast.makeText(AdminUserRegistration.this, "User password cannot be empty or less than six.", Toast.LENGTH_SHORT).show();
        } else if (userType.equals("")) {
            Toast.makeText(AdminUserRegistration.this, "Please select user type.", Toast.LENGTH_SHORT).show();
        } else {
            mAuth.createUserWithEmailAndPassword(EmailAddress, EmailPassword)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("TAG", "createUserWithEmail:success");

                                String uid = mAuth.getUid();
                                User user;
                                // Create a new user with a first and last name
                                if (userType.equals("teacher") || userType.equals("admin")) {
                                     user = new User(uid, userName, userSurname, EmailAddress, userType);
                                } else {
                                     user = new User(uid, userName, userSurname, EmailAddress, userType, userSection, 1, 1);
                                }

                                
                                // Add a new document with a user ID
                                db.collection("users").document(Objects.requireNonNull(uid)).set(user)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(AdminUserRegistration.this, "Register success", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("TAG", "createUserWithEmail:failure", task.getException());
                                Toast.makeText(AdminUserRegistration.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        userSection = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radioButtonAdmin:
                if (checked)
                    userType = "admin";
                break;
            case R.id.radioButtonStudent:
                if (checked)
                    userType = "student";
                break;
            case R.id.radioButtonTeacher:
                if (checked)
                    userType = "teacher";
                break;
            default:
                userType = "";
                break;
        }
        if (userType.equals("student"))
            lessonsSpinner.setVisibility(View.VISIBLE);
        else
            lessonsSpinner.setVisibility(View.GONE);
    }
}