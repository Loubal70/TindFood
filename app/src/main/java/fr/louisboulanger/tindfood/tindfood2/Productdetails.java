package fr.louisboulanger.tindfood.tindfood2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.appli.tindfood2.R;

import static com.appli.tindfood2.R.*;

public class Productdetails extends AppCompatActivity {

    private ImageView imageView;
    private Button button2;

    ImageView imageView2;
    TextView Itemname, ItemPrice, ItemDescription;
    String name, price;
    int imageUrl;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_productdetails);

        Intent productdetails = getIntent();
        name = productdetails.getStringExtra("name");
        price = productdetails.getStringExtra("price");
        imageUrl = productdetails.getIntExtra("image", -1);

        Itemname = findViewById(R.id.textView11);
        ItemPrice = findViewById(R.id.textView12);
        imageView2 = findViewById(R.id.imageView7);

        Itemname.setText(name);
        ItemPrice.setText(price);
        imageView2.setImageResource(imageUrl);

        this.imageView = (ImageView) findViewById(id.imageView);

        imageView.setOnClickListener(v -> {
            Intent mainpage = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(mainpage);
            finish();
        });

        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity();
            }
        });


    }

    private void openActivity() {
        Intent intent = new Intent(this, slide_screen.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }

        return false;
    }

}