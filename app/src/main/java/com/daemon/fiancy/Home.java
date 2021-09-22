package com.daemon.fiancy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.daemon.fiancy.models.Advertisements;
import com.daemon.fiancy.recyclers.RecyclerViewAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
    DatabaseReference databaseReference;
    RecyclerViewAdapter adapter;
    ArrayList<Advertisements> list;
    EditText Search;

    private static final String TAG = "MainActivity";
    public static final String SHARED_PREFS = "shared_prefs";
    public static final String EMAIL_KEY = "email_key";
    SharedPreferences sharedpreferences;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.d(TAG, "onCreate: started");

        databaseReference = FirebaseDatabase.getInstance().getReference("Advertisements");

        initRecyclerView();
        searchView();

        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        email = sharedpreferences.getString(EMAIL_KEY, null);

        DrawerLayout drawerLayout = findViewById(R.id.navDrawer);
        ImageView profileNav = findViewById(R.id.menuInflator);
        ImageView closeNav = findViewById(R.id.pro_Pic);

        profileNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDrawer(drawerLayout);
            }
        });

        closeNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeDrawer(drawerLayout);
            }
        });
    }

    private void searchView() {
        Search = findViewById(R.id.Search);
        //Search function
        Search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    private void closeDrawer(DrawerLayout drawerLayout) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: started");
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        adapter = new RecyclerViewAdapter(this, list);
        recyclerView.setAdapter(adapter);

        //get & set data from firebase
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    Advertisements advertisements = dataSnapshot.getValue(Advertisements.class);
                    advertisements.setDocumentKey(dataSnapshot.getKey());
                    list.add(advertisements);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {}
        });
    }

    public void clickedMyProfile(View view) {
        Intent intent = new Intent(Home.this, UpdateProfile.class);
        startActivity(intent);
    }
//    public void Checkprofile(View view) {
//        Intent intent = new Intent(Home.this, profile.class);
//        startActivity(intent);
//    }
    public void clickedMyAdvertisement(View view) {
        Intent intent = new Intent(Home.this, MyAdCollection.class);
        startActivity(intent);
    }

    public void clickedBecomePremium(View view) {
        Intent intent = new Intent(Home.this, PremiumLog.class);
        startActivity(intent);
    }

    public void clickedLogOut(View view) {

        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.apply();

        Intent intent1 = new Intent(Home.this, MainActivity.class);
        startActivity(intent1);
        finish();
    }

    public void Checkfilter(View view) {
        Intent intent = new Intent(Home.this, Filter.class);
        startActivity(intent);
    }

    public void postAdBtn(View view) {
        Intent intent = new Intent(Home.this, PostAdActivity.class);
        startActivity(intent);
    }
}