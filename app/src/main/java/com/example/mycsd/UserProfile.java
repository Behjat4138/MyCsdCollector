package com.example.mycsd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class UserProfile extends AppCompatActivity {

    //Variables
    TextInputLayout fullname, email, username;
    TextView fullnameLabel, usernameLabel;

    //Firebase
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();
    String userID = user.getUid();
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        //Textview
        fullnameLabel = findViewById(R.id.fullname_field);
        usernameLabel = findViewById(R.id.username_field);

        //TextInputLayout
        fullname = findViewById(R.id.full_name_profile);
        email = findViewById(R.id.email_profile);
        username = findViewById(R.id.username_profile);

        //Method to show all data
        showAllUserData();
    }

    private void showAllUserData() {
        DatabaseReference reference = database.getReference("Users");
        //get data from real time database
        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserClass userProfile = snapshot.getValue(UserClass.class);

                if (userProfile != null) {
                    String name = userProfile.name;
                    String em = userProfile.emailadd;
                    String un = userProfile.username;

                    fullnameLabel.setText(name);
                    usernameLabel.setText(un);

                    fullname.getEditText().setText(name);
                    email.getEditText().setText(em);
                    username.getEditText().setText(un);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UserProfile.this, "Something went wrong!", Toast.LENGTH_LONG).show();
            }
        });

    }


    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent i = new Intent(UserProfile.this, MyLoginActivity.class);
        startActivity(i);
        finish();
    }

}