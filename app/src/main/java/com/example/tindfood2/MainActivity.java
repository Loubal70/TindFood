package com.example.tindfood2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerCategories;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            recyclerCategories = findViewById(R.id.recycler_categories);
            setCategories();
    }


    private void setCategories() {
        List<Categories> data = new ArrayList<>();

        Categories foodCategory = new Categories("Burger",R.drawable.ic_chicken_food);
        Categories foodCategory2 = new Categories("Chicken",R.drawable.ic_chicken_food);
        Categories foodCategory3 = new Categories("Pizza",R.drawable.ic_chicken_food);
        Categories foodCategory4 = new Categories("Burger",R.drawable.ic_chicken_food);

        data.add(foodCategory);
        data.add(foodCategory2);
        data.add(foodCategory3);
        data.add(foodCategory4);

        CategoriesAdapter CategoriesAdapter = new CategoriesAdapter(data, MainActivity.this);

        recyclerCategories.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.HORIZONTAL,false));
        recyclerCategories.setAdapter(CategoriesAdapter);
        CategoriesAdapter.notifyDataSetChanged();
    }
}