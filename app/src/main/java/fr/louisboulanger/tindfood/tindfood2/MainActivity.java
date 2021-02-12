package fr.louisboulanger.tindfood.tindfood2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.appli.tindfood2.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerCategories;
    RecyclerView recyclerItems;

    // Pop-UP
    //Button popupBTN;
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
        setItem("Burger");


        // POP-UP
        ingredientView = (TextView) findViewById( R.id.textView5 );
        databaseManager = new DatabaseManager( this );

        //databaseManager.insertingredient("name", "ingredient");

        List<Data> ingredient = databaseManager.read();

        for (Data ingredients : ingredient){
            ingredientView.append(ingredient.toString() + "\n\n");
            Log.e("Indications", "Ingrédient effectué");
        }




        databaseManager.close(); // Ferme l'accès à la BDD

//        popupBTN = findViewById(R.id.popupBTN);
//        popupBTN.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent productdetails = new Intent(getApplicationContext(), Productdetails.class);
//                startActivity(productdetails);
//                finish();
//            }
//        });
        mDialog = new Dialog(this);
        //popupBTN.setOnClickListener(v -> {
        mDialog.setContentView(R.layout.popup);

        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        //mDialog.show();

        //Log.e( "POPUP", "Click effectué");

        //});

    }


    private void setCategories() {
        List<Categories> data = new ArrayList<>();

        Categories foodCategory = new Categories("Burger",R.drawable.ic_burger_food);
        Categories foodCategory2 = new Categories("Poulet",R.drawable.ic_chicken_food);
        Categories foodCategory3 = new Categories("Pizza",R.drawable.ic_pizza_food);

        data.add(foodCategory);
        data.add(foodCategory2);
        data.add(foodCategory3);

        CategoriesAdapter CategoriesAdapter = new CategoriesAdapter(data, MainActivity.this, pos -> setItem(pos.getName()));

        recyclerCategories.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.HORIZONTAL,false));
        recyclerCategories.setAdapter(CategoriesAdapter);
        CategoriesAdapter.notifyDataSetChanged();
    }

    private void setItem(String name){
        List<Item> Item = new ArrayList<>();
        switch (name){
            case "Pizza":
                Item Item1 = new Item("Pizza 1", 4.5f, 8, R.drawable.pizza_1);
                Item Item2 = new Item("Pizza 2", 2f, 10, R.drawable.pizza_2);
                Item Item3 = new Item("Pizza 3", 3.5f, 11, R.drawable.pizza_3);
                Item Item4 = new Item("Pizza 4", 4.5f, 13, R.drawable.pizza_4);
                Item Item5 = new Item("Pizza 5", 5f, 14, R.drawable.pizza_5);

                Item.add(Item1);
                Item.add(Item2);
                Item.add(Item3);
                Item.add(Item4);
                Item.add(Item5);
                break;
            case "Poulet":
                Item Item6 = new Item("Poulet 1", 4.5f, 4, R.drawable.grill_chicken_1);
                Item Item7 = new Item("Poulet 2", 2f, 6, R.drawable.grill_chicken_2);
                Item Item8 = new Item("Poulet 3", 3.5f, 8, R.drawable.grill_chicken_3);

                Item.add(Item6);
                Item.add(Item7);
                Item.add(Item8);
                break;
            case "Burger":
                Item Item9 = new Item("Burger 1", 4.5f, 7, R.drawable.burger);
                Item Item10 = new Item("Burger 2", 2f, 9, R.drawable.burger_two);

                Item.add(Item9);
                Item.add(Item10);
                break;
        }

        Adapter Adapter = new Adapter(Item, item -> {
            //mDialog.show();
            Intent productdetails = new Intent(getApplicationContext(), Productdetails.class);
            productdetails.putExtra("name", item.getName());
            productdetails.putExtra("price", item.getPrice()+ " €");
            productdetails.putExtra("image", item.getImage());
            startActivity(productdetails);
            finish();
        });

        recyclerItems.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.HORIZONTAL, false));

        recyclerItems.setAdapter(Adapter);
    }

}