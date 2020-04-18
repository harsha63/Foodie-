package com.example.foodie;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import androidx.appcompat.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class RestaurantList extends AppCompatActivity {
    ArrayList<Restaurant> restaurantList;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdaptor;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_list);
        fillRestName();
        setUpRecyclerView();
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
        mRecyclerView = findViewById(R.id.restaurantsName);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdaptor = new RestaurantAdapter(restaurantList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdaptor);
    }
    /*@Override
    public boolean onCreateOptionsMenu (Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
        return true;
    }*/
}
