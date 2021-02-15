package fr.louisboulanger.tindfood.tindfood2;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

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

        /* -------------------------------- DrawerLayout -------------------------------- */
            drawerLayout = findViewById(R.id.drawer_layout);
            navigationView = findViewById(R.id.nav_view);
            toolbar = findViewById(R.id.toolbar);

        /* ------------------------------- Navigation View ------------------------------ */
        setSupportActionBar(toolbar);
        /* -------------------------------- Navigation Drawer Menu -------------------------------- */
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

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
                Item Item1 = new Item("Pizza 1", 4.5f, "8", R.drawable.pizza_1, "Gouter à l'italie, avec notre pizza fraîchement inventée par nos cuisiniers. Une pâte brisée, 500g de jambon, 3 tomates coupés en tranches, quelques olives, avec sa touche de fromage. De quoi vous donnez envie !");
                Item Item2 = new Item("Pizza 2", 2f, "10", R.drawable.pizza_2, "Pate Feuilleté à l'ancienne, 200g de poivron, une poignée de tomate cerise, 500g de champignon pendant 30 min à 180° et le tour est joué !");
                Item Item3 = new Item("Pizza 3", 3.5f, "11", R.drawable.pizza_3, "description pizza 3");
                Item Item4 = new Item("Pizza 4", 4.5f, "13", R.drawable.pizza_4, "description pizza 4");
                Item Item5 = new Item("Pizza 5", 5f, "14", R.drawable.pizza_5, "description pizza 5");

                Item.add(Item1);
                Item.add(Item2);
                Item.add(Item3);
                Item.add(Item4);
                Item.add(Item5);
                break;
            case "Poulet":
                Item Item6 = new Item("Poulet 1", 4.5f, "4,50", R.drawable.grill_chicken_1, "Qu'est qu'il est bon le poulet, fermier, à saucer tous les 5 minutes accompagnés de rondelles de citron dans la sauce.");
                Item Item7 = new Item("Poulet 2", 2f, "6,99", R.drawable.grill_chicken_2, "Poulet à la provencale, 1 citron en tranche, 300g de carottes, accompagné de riz blanc");
                Item Item8 = new Item("Poulet 3", 3.5f, "8", R.drawable.grill_chicken_3, "description poulet 3");

                Item.add(Item6);
                Item.add(Item7);
                Item.add(Item8);
                break;
            case "Burger":
                Item Item9 = new Item("Burger 1", 4.5f, "4,69", R.drawable.burger, "Poulet fris, chedar, salami avec deux rondelles de tomates, et le goût fut !");
                Item Item10 = new Item("Burger 2", 2f, "14,60", R.drawable.burger_two, "description burger 2");

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
            productdetails.putExtra("description", item.getDescription());
            startActivity(productdetails);
            finish();
        });

        recyclerItems.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.HORIZONTAL, false));

        recyclerItems.setAdapter(Adapter);
    }

}