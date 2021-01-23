package com.example.tindfood2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerCategories;
    RecyclerView recyclerItems;

    // Pop-UP
    Button popupBTN;
    Dialog mDialog;

    private TextView ingredientView;
    private DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);


            recyclerCategories = findViewById(R.id.recycler_categories);
            recyclerItems = findViewById(R.id.recycler_nourriture);
            setCategories();
            setItem(0);


            // POP-UP
            ingredientView = (TextView) findViewById( R.id.textView5 );
            databaseManager = new DatabaseManager( this );

             //databaseManager.insertingredient("name", "ingredient");

            List<Data> ingredient = databaseManager.read();

            for (Data ingredients : ingredient){
                ingredientView.append(ingredient.toString() + "\n\n");
                Log.e("pd", "pd effectué");
            }




            databaseManager.close(); // Ferme l'accès à la BDD

            popupBTN = findViewById(R.id.popupBTN);
            mDialog = new Dialog(this);
            popupBTN.setOnClickListener(v -> {
                mDialog.setContentView(R.layout.popup);

                mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                mDialog.show();

                Log.e( "POPUP", "Click effectué");

            });

    }


    private void setCategories() {
        List<Categories> data = new ArrayList<>();

        Categories foodCategory = new Categories("Burger",R.drawable.ic_burger_food);
        Categories foodCategory2 = new Categories("Poulet",R.drawable.ic_chicken_food);
        Categories foodCategory3 = new Categories("Pizza",R.drawable.ic_pizza_food);

        data.add(foodCategory);
        data.add(foodCategory2);
        data.add(foodCategory3);

        CategoriesAdapter CategoriesAdapter = new CategoriesAdapter(data, MainActivity.this, new CategoriesAdapter.OnCategoryClick() {
            @Override
            public void onClick(int pos) {
                setItem(pos);
            }
        });

        recyclerCategories.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.HORIZONTAL,false));
        recyclerCategories.setAdapter(CategoriesAdapter);
        CategoriesAdapter.notifyDataSetChanged();
    }

    private void setItem(int pos){
        List<Item> Item = new ArrayList<>();
        switch (pos){
            case 2:
                Item Item1 = new Item("Pizza 1", 4.5f, 14, R.drawable.pizza_1);
                Item Item2 = new Item("Pizza 2", 2f, 14, R.drawable.pizza_2);
                Item Item3 = new Item("Pizza 3", 3.5f, 14, R.drawable.pizza_3);
                Item Item4 = new Item("Pizza 4", 4.5f, 14, R.drawable.pizza_4);
                Item Item5 = new Item("Pizza 5", 5f, 14, R.drawable.pizza_5);

                Item.add(Item1);
                Item.add(Item2);
                Item.add(Item3);
                Item.add(Item4);
                Item.add(Item5);
                break;
            case 1:
                Item Item6 = new Item("Poulet 1", 4.5f, 14, R.drawable.grill_chicken_1);
                Item Item7 = new Item("Poulet 2", 2f, 14, R.drawable.grill_chicken_2);
                Item Item8 = new Item("Poulet 3", 3.5f, 14, R.drawable.grill_chicken_3);

                Item.add(Item6);
                Item.add(Item7);
                Item.add(Item8);
                break;
            case 0:
                Item Item9 = new Item("Burger 1", 4.5f, 14, R.drawable.burger);
                Item Item10 = new Item("Burger 2", 2f, 14, R.drawable.burger_two);

                Item.add(Item9);
                Item.add(Item10);
                break;
        }

        Adapter Adapter = new Adapter(Item);
        recyclerItems.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.HORIZONTAL, false));

        recyclerItems.setAdapter(Adapter);
    }

}