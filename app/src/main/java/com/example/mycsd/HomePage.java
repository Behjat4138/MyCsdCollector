package com.example.mycsd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class HomePage extends AppCompatActivity {

    //Variables
    TextView fullnameLabel, usernameLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        //Textview
        fullnameLabel = findViewById(R.id.fullname_field);
        usernameLabel = findViewById(R.id.username_field);

    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent i = new Intent(HomePage.this, MyLoginActivity.class);
        startActivity(i);
        finish();
    }

    public void profile(View view) {
        Intent i = new Intent(HomePage.this, UserProfile.class);
        startActivity(i);
    }

    // change UserProfile.class to your respective classes
    public void mycsd(View view) {
        Intent i = new Intent(HomePage.this, UserProfile.class);
        startActivity(i);
    }

    public void addevent(View view) {
        Intent i = new Intent(HomePage.this, UserProfile.class);
        startActivity(i);
    }

    public void upcomingevent(View view) {
        Intent i = new Intent(HomePage.this, UserProfile.class);
        startActivity(i);
    }



}