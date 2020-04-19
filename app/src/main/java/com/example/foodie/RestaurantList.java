package com.example.foodie;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;

import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class RestaurantList extends AppCompatActivity {
    ArrayList<Restaurant> restaurantList;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RestaurantAdapter mAdaptor;
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    ImageButton bt;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_list);
        fStore = FirebaseFirestore.getInstance();
        restaurantList = new ArrayList<>();
        mRecyclerView = findViewById(R.id.restaurantsName);
        mRecyclerView.setHasFixedSize(true);
        bt = findViewById(R.id.imgButton);
        mLayoutManager = new LinearLayoutManager(this);
        //fillRestName();
        if (restaurantList.size()>0){
            restaurantList.clear();
        }
        fStore.collection("RestaurantList").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (DocumentSnapshot documentSnapshot: task.getResult()){
                    Restaurant restaur = new Restaurant(R.drawable.ic_free_breakfast_black_24dp,documentSnapshot.getString("Name"), documentSnapshot.getString("Description"));
                    restaurantList.add(restaur);
                }
                mAdaptor= new RestaurantAdapter(restaurantList);
                mRecyclerView.setAdapter(mAdaptor);
                mAdaptor.setOnItemClickListener(new RestaurantAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        changeDescriptionA(position, "Clicked:Opening Menu");
                    }
                });
            }
        })
                .addOnFailureListener(new OnFailureListener(){
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RestaurantList.this, "", Toast.LENGTH_SHORT).show();
                        Log.v("Failed", e.getMessage());
                        fillRestName();
                        setUpRecyclerView();

                    }
                });
    }
    public void fillRestName(){
        restaurantList = new ArrayList<>();
        restaurantList.add(new Restaurant(R.drawable.ic_free_breakfast_black_24dp, "new", "food" ));
        restaurantList.add(new Restaurant(R.drawable.ic_free_breakfast_black_24dp, "new", "food again" ));
        restaurantList.add(new Restaurant(R.drawable.ic_free_breakfast_black_24dp, "new", "food finally" ));
        restaurantList.add(new Restaurant(R.drawable.ic_free_breakfast_black_24dp, "can't wait", "ghar ka food" ));
        restaurantList.add(new Restaurant(R.drawable.ic_free_breakfast_black_24dp, "new spiciya", "again" ));
        restaurantList.add(new Restaurant(R.drawable.ic_free_breakfast_black_24dp, "mast khana", "food " ));
        restaurantList.add(new Restaurant(R.drawable.ic_free_breakfast_black_24dp, "kal khana", "food" ));
        restaurantList.add(new Restaurant(R.drawable.ic_free_breakfast_black_24dp, "quarrantine", "food" ));
        restaurantList.add(new Restaurant(R.drawable.ic_free_breakfast_black_24dp, "not dominos", "food" ));
        restaurantList.add(new Restaurant(R.drawable.ic_free_breakfast_black_24dp, "dhaba", "food" ));
        restaurantList.add(new Restaurant(R.drawable.ic_free_breakfast_black_24dp, "tapri", "food again" ));
        restaurantList.add(new Restaurant(R.drawable.ic_free_breakfast_black_24dp, "only Maggi", "food finally" ));
        restaurantList.add(new Restaurant(R.drawable.ic_free_breakfast_black_24dp, "eat and sleep", "food" ));
        restaurantList.add(new Restaurant(R.drawable.ic_free_breakfast_black_24dp, "drugged", "again" ));
        restaurantList.add(new Restaurant(R.drawable.ic_free_breakfast_black_24dp, "yes health", "vegan again"));
    }
    public void setUpRecyclerView(){
        mAdaptor= new RestaurantAdapter(restaurantList);
        mRecyclerView.setAdapter(mAdaptor);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdaptor.setOnItemClickListener(new RestaurantAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                changeDescriptionA(position, "Clicked:Opening Menu");
            }
        });
    }
    public void changeDescriptionA(int position, String text){
        restaurantList.get(position).changeDescrip(text);
        mAdaptor.notifyItemChanged(position);
    }

}
