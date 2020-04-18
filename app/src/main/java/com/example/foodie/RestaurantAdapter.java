package com.example.foodie;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {
    private ArrayList<Restaurant> mRestList;
    FirebaseFirestore fStore;



    public static class RestaurantViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mText1;
        public TextView mText2;

        public RestaurantViewHolder( View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image1);
            mText1 =itemView.findViewById(R.id.text1);
            mText2 =itemView.findViewById(R.id.text2);
        }
    }
    public RestaurantAdapter(ArrayList<Restaurant> restList) {
        mRestList=restList;
    }
    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_card, parent, false);
        RestaurantViewHolder rvh;
        rvh = new RestaurantViewHolder(v);
        return rvh;
    }
    @Override
    public void onBindViewHolder( RestaurantViewHolder holder, int position) {
        Restaurant currentRest = mRestList.get(position);

        holder.mImageView.setImageResource(currentRest.getImage());
        holder.mText1.setText(currentRest.getNameRest());
        holder.mText2.setText(currentRest.getDescription());
    }
    @Override
    public int getItemCount() {
        return mRestList.size();
    }

    /*
    @Override
    public Filter getFilter() {
        return restFilter;
    }
    private Filter restFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Restaurant> filterList = new ArrayList<>();
            if(constraint==null || constraint.length()==0){
                filterList.addAll(mRestListFull);
            } else{
                String filterPattern = constraint.toString().toLowerCase().trim();
                for(Restaurant item : mRestListFull ){
                    if ((item.getNameRest().toLowerCase().contains(filterPattern))){
                        filterList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values=filterList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mRestList.clear();
            mRestList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };*/
}
