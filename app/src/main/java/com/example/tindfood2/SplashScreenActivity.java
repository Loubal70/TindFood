package com.example.tindfood2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import static com.example.tindfood2.R.layout.activity_splash_screen;

public class SplashScreenActivity extends AppCompatActivity {

    private final int SPLASH_SCREEN_TIMEOUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_splash_screen);

        Runnable runnable = () -> {
            // Rediriger vers page principal après 3 secondes
            //// Démarrer une page
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        };

        // Handler - Après 3 secondes
        new Handler().postDelayed(runnable, SPLASH_SCREEN_TIMEOUT);
    }
}