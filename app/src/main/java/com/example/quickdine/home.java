package com.example.quickdine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class home extends AppCompatActivity {
    ImageView Search,Profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Window window = home.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(home.this, R.color.grey_600));
        Search=findViewById(R.id.imageView8);
        Profile=findViewById(R.id.imageView9);
        Search.setOnClickListener(v->{
            Intent intent=new Intent(this,search.class);
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