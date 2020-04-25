package com.example.foodie;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;

public class OrderTracking extends AppCompatActivity {
    private static final String TAG = "RRREEEEEE";
    TextView Menu,Restaurant,jabla;
    TextView first,second,third,fourth;
    FirebaseAuth mFAuth = FirebaseAuth.getInstance();
    FirebaseFirestore fStore = FirebaseFirestore.getInstance();
    String Item, Restro;
    Double status;
    String DelID;
    String DeliveryName;
    String Phone;
    private ListenerRegistration orderListener;
    String userId = mFAuth.getUid();
    DocumentReference documentReference = fStore.collection("Orders").document(userId);

    @Override
    protected void onStart(){
        super.onStart();
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot document, @Nullable FirebaseFirestoreException e) {
                if (e!=null){
                    Toast.makeText(OrderTracking.this, "ERROR while loading!", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, e.toString());
                    return;
                }

                if (document.exists()) {
                    Restro = document.getString("Restaurant");
                    Item = document.getString("MenuItem");
                    status = document.getDouble("Status");
                    DelID = document.getString("DeliveryId");
                    Restaurant.setText(Restro);
                    Menu.setText(Item);

                    if(status==1)
                    {
                        first.setBackgroundColor(Color.parseColor("#00FF00"));
                        first.setText("Order Confirmed(Done)");
                    }
                    if(status==2)
                    {
                        first.setBackgroundColor(Color.parseColor("#00FF00"));
                        first.setText("Order Confirmed(Done)");
                        second.setBackgroundColor(Color.parseColor("#00FF00"));
                        second.setText("Delivery guy Assigned");
                    }
                    if(status==3)
                    {
                        first.setBackgroundColor(Color.parseColor("#00FF00"));
                        first.setText("Order Confirmed(Done)");
                        second.setBackgroundColor(Color.parseColor("#00FF00"));
                        second.setText("Delivery guy Assigned");
                        third.setBackgroundColor(Color.parseColor("#00FF00"));
                        third.setText("Order at your Doorstep");
                    }
                    if(status==4)
                    {
                        first.setBackgroundColor(Color.parseColor("#00FF00"));
                        first.setText("Order Confirmed(Done)");
                        second.setBackgroundColor(Color.parseColor("#00FF00"));
                        second.setText("Delivery guy Assigned");
                        third.setBackgroundColor(Color.parseColor("#00FF00"));
                        third.setText("Order at your doorstep");
                        fourth.setBackgroundColor(Color.parseColor("#00FF00"));
                        fourth.setText("Order Arrived(Complete)");
                    }
                    DocumentReference documents = fStore.collection("Customers").document(DelID);
                    documents.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                DeliveryName = document.getString("fName");
                                Phone = document.getString("Phone number");
                                jabla.setText("Delivery Name is "+DeliveryName +"\n"+"Phone number: "+Phone);

                            }
                        }
                    });


                } else {
                    Log.d(TAG, "No such document");
                }

            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_tracking);
        Menu = findViewById(R.id.MenuItem);
        Restaurant = findViewById(R.id.Resto);
        jabla = findViewById(R.id.Info);
        first = findViewById(R.id.Confirmed);
        second = findViewById(R.id.Assign);
        third = findViewById(R.id.pickup);
        fourth = findViewById(R.id.otp);



        /*documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Restro = document.getString("Restaurant");
                        Item = document.getString("MenuItem");
                        status = document.getDouble("Status");
                        DelID = document.getString("DeliveryId");
                        Restaurant.setText(Restro);
                        Menu.setText(Item);
                        if(status==1)
                        {
                            first.setBackgroundColor(Color.parseColor("#00FF00"));
                            first.setText("Order Confirmed(Done)");
                        }
                        if(status==2)
                        {
                            first.setBackgroundColor(Color.parseColor("#00FF00"));
                            first.setText("Order Confirmed(Done)");
                            second.setBackgroundColor(Color.parseColor("#00FF00"));
                            second.setText("Delivery guy Assigned");
                        }
                        if(status==3)
                        {
                            first.setBackgroundColor(Color.parseColor("#00FF00"));
                            first.setText("Order Confirmed(Done)");
                            second.setBackgroundColor(Color.parseColor("#00FF00"));
                            second.setText("Delivery guy Assigned");
                            third.setBackgroundColor(Color.parseColor("#00FF00"));
                            third.setText("Order picked up(Done)");
                        }
                        if(status==4)
                        {
                            first.setBackgroundColor(Color.parseColor("#00FF00"));
                            first.setText("Order Confirmed(Done)");
                            second.setBackgroundColor(Color.parseColor("#00FF00"));
                            second.setText("Delivery guy Assigned");
                            third.setBackgroundColor(Color.parseColor("#00FF00"));
                            third.setText("Order picked up(Done)");
                            fourth.setBackgroundColor(Color.parseColor("#00FF00"));
                            fourth.setText("Order Arrived(Complete)");
                        }
                        DocumentReference documents = fStore.collection("Customers").document(DelID);
                        documents.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    DeliveryName = document.getString("fName");
                                    Phone = document.getString("Phone number");
                                    jabla.setText("Delivery Name is "+DeliveryName +"\n"+"Phone number: "+Phone);

                                }
                            }
                        });
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });*/


    }
}

