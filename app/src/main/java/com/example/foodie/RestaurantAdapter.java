package com.example.foodie;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.ArrayList;
import java.util.List;

import static androidx.core.content.ContextCompat.startActivity;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {
    private ArrayList<Restaurant> mRestList;
    FirebaseFirestore fStore;
    FirebaseAuth mFAuth;
    String UserID,name,phone;
    private OnItemClickListener mListener;

    private static final String TAG = "ADAPTER";
    private Context context;
    //Orders driverOrders;
    List<String> IDs;

    public RestaurantAdapter(ArrayList<Restaurant> restList) {
        mRestList=restList;

        mFAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        UserID = mFAuth.getUid();
        DocumentReference documentReference = fStore.collection("Customers").document(UserID);
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        name = document.getString("fName");
                        phone = document.getString("Phone number");
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        mListener=listener;
    }

    public static class RestaurantViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImageView;
        public TextView mText1;
        public TextView mText2;
        public ImageButton mNext;

        public RestaurantViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image1);
            mText1 =itemView.findViewById(R.id.text1);
            mText2 =itemView.findViewById(R.id.text2);
            mNext = itemView.findViewById(R.id.imgButton);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
            //itemView.setOnClickListener();
            mNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //startActivity(new Intent( getApplicationContext(),MenuList.class));
                }
            });
        }
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_card, parent, false);
        RestaurantViewHolder rvh;
        rvh = new RestaurantViewHolder(v, mListener);
        return rvh;
    }
    @Override
    public void onBindViewHolder( RestaurantViewHolder holder, int position) {
        Restaurant currentRest = mRestList.get(position);

        holder.mImageView.setImageResource(currentRest.getImage());
        holder.mText1.setText(currentRest.getName());
        holder.mText2.setText(currentRest.getDescription());
    }
    @Override
    public int getItemCount() {
        return mRestList.size();
    }


}
