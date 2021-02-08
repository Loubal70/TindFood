package com.appli.tindfood.tindfood2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import static com.appli.tindfood2.R.*;

public class Productdetails extends AppCompatActivity {

    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_productdetails);
        this.imageView = (ImageView) findViewById(id.imageView);

        imageView.setOnClickListener(v -> {
            Intent mainpage = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(mainpage);
            finish();
        });

    }

}