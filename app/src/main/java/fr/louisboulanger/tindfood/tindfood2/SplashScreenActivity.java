package fr.louisboulanger.tindfood.tindfood2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Runnable runnable = () -> {
            // Rediriger vers page principal après 3 secondes
            //// Démarrer une page
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        };

        // Handler - Après 3 secondes
        new Handler().postDelayed(runnable, 3000);
    }
}