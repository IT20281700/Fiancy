package com.daemon.fiancy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.daemon.fiancy.models.Advertisements;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class profile extends AppCompatActivity {
    // firebase
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    // model class
    Advertisements singleAdvertisement;
    // initializations
    ImageView profileimageinAd, Gender;
    TextView location, fullname, age, profession, religion, minEducation,
    description;

    String documentKey;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //get Instance()
        getInstance();
    }

    private void getInstance() {
        profileimageinAd = findViewById(R.id.NPImage);
        Gender = findViewById(R.id.ic_gender);
        fullname = findViewById(R.id.NPfullname);
        location = findViewById(R.id.NPlocation);
        age = findViewById(R.id.NPAge);
        profession = findViewById(R.id.NPProfession);
        religion = findViewById(R.id.NPReligion);
        minEducation = findViewById(R.id.NPMinEducationLevel);
        description = findViewById(R.id.NPDescription);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // get document key of current post
        Intent intent = getIntent();
         documentKey = intent.getExtras().getString("documetKey");

        // call functions
        getAdvertisementData(documentKey);

    }

    // get data from database to single post
    private void getAdvertisementData(String docKey) {

        //get & set data from firebase
        databaseReference.child("Advertisements").child(docKey).get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                singleAdvertisement = dataSnapshot.getValue(Advertisements.class);
                setData();
            }
        });
    }

    private void setData() {
        // set profile picture
        if(singleAdvertisement.getImage1() != null) {
            Glide.with(getApplicationContext())
                    .asBitmap().load(singleAdvertisement.getImage1())
                    .into(profileimageinAd);
        } else {
            // gender = male
            if (singleAdvertisement.getGender().equals("Male")) {
                Glide.with(getApplicationContext())
                        .asBitmap().load("https://cdn-icons-png.flaticon.com/512/2922/2922510.png")
                        .into(profileimageinAd);
            }
            // gender = female
            if (singleAdvertisement.getGender().equals("Female")) {
                Glide.with(getApplicationContext())
                        .asBitmap().load("https://cdn-icons-png.flaticon.com/512/2922/2922561.png")
                        .into(profileimageinAd);
            }
        }

        // set gender icon
        if (singleAdvertisement.getGender().equals("Male")) {
            Glide.with(getApplicationContext())
                    .asBitmap().load("https://img.icons8.com/emoji/50/000000/male-sign-emoji.png")
                    .into(Gender);
        } else if(singleAdvertisement.getGender().equals("Female")) {
            Glide.with(getApplicationContext())
                    .asBitmap().load("https://img.icons8.com/external-flatart-icons-flat-flatarticons/50/000000/external-female-womens-day-flatart-icons-flat-flatarticons.png")
                    .into(Gender);
        }

        fullname.setText(singleAdvertisement.getFullname());
        location.setText(singleAdvertisement.getAddress());
        age.setText(String.format(singleAdvertisement.getAge()+" Years old"));
        religion.setText(singleAdvertisement.getReligion());
        minEducation.setText(singleAdvertisement.getMinEducatuinLevel());
        description.setText(singleAdvertisement.getDescription());
    }

    // go to report ad activty
    public void report(View view) {
        Intent intent = new Intent(profile.this, ReportAd.class);
        intent.putExtra("ReportedAdKey",documentKey);
        startActivity(intent);
    }
}