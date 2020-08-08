package com.example.registrytolessons.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.registrytolessons.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class AdminPanel extends AppCompatActivity {

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        mAuth = FirebaseAuth.getInstance();

        final DrawerLayout drawerLayout = findViewById(R.id.student_drawerLayout);

        findViewById(R.id.imageMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });


        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setItemIconTintList(null);
    }

    public void logOut(MenuItem item) {
        mAuth.signOut();
        Intent intent = new Intent(this, UserEntrance.class);
        startActivity(intent);
        finish();
    }

    public void userRegistration(MenuItem item) {
        Intent intent = new Intent(this, AdminUserRegistration.class);
        startActivity(intent);
    }

    public void userCourseRegistration(MenuItem item) {
        Intent intent = new Intent(this, AdminUserCourseRegistration.class);
        startActivity(intent);

    }
}