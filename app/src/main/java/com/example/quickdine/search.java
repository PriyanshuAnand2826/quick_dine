package com.example.quickdine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class search extends AppCompatActivity {
    ImageView Home,Profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Window window = search.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(search.this, R.color.grey_600));
        Home=findViewById(R.id.imageView7);
        Profile=findViewById(R.id.imageView9);
        Home.setOnClickListener(v->{
            Intent intent=new Intent(this,home.class);
            startActivity(intent);
            finish();

        });
        Profile.setOnClickListener(v->{
            Intent intent=new Intent(this,profile.class);
            startActivity(intent);
            finish();
        });
    }
}