package com.example.foodie;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

public class CustomerDashboard extends AppCompatActivity {
    private static final String TAG = "99999999999999";
    TextView fullName,email;
    Button b,r;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_dashboard);
        fullName = findViewById(R.id.profileName);
        email    = findViewById(R.id.profileEmail);
        b=findViewById(R.id.button2);
        r=findViewById(R.id.button4);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userId = fAuth.getCurrentUser().getUid();
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CustomerDashboard.this,Customer.class));
            }
        });
        r.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CustomerDashboard.this,RestaurantList.class));
            }
        }));

        DocumentReference documentReference = fStore.collection("Customers").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                fullName.setText(documentSnapshot.getString("fName"));
                Log.d(TAG,documentSnapshot.getString("fName")+"Hello");
                email.setText(documentSnapshot.getString("email"));
            }
        });


    }

    public void edit(View view) {
        startActivity(new Intent(getApplicationContext(),Delivery.class));
        finish();
    }
}