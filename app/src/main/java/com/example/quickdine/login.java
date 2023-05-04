package com.example.quickdine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import soup.neumorphism.NeumorphButton;

public class login extends AppCompatActivity {
    ImageView foodie, restaurant;
    NeumorphButton next;
    FirebaseAuth auth;
    FirebaseUser user;
    Boolean foodie_var=false,restaurant_var=false;
    DatabaseReference reference;
    DatabaseReference reference1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Window window = login.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(login.this, R.color.grey_600));
        auth= FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference().child("users");
        reference1= FirebaseDatabase.getInstance().getReference().child("restaurants");
        foodie = findViewById(R.id.imageView1);
        restaurant = findViewById(R.id.imageView);
        next = findViewById(R.id.btn_next);
        foodie.setOnClickListener(v->{
            foodie.setBackgroundResource(R.drawable.bg_tap_login);
            restaurant.setBackgroundResource(R.drawable.bg_restaurant_login);
            foodie_var=true;
            restaurant_var=false;
        });
        restaurant.setOnClickListener(v->{
            restaurant.setBackgroundResource(R.drawable.bg_tap_login);
            foodie.setBackgroundResource(R.drawable.bg_restaurant_login);
            restaurant_var=true;
            foodie_var=false;
        });
        next.setOnClickListener(v->{
            if (foodie_var==false && restaurant_var==false){
                Toast.makeText(this, "Please Select any one !!", Toast.LENGTH_SHORT).show();
            }
            if (foodie_var){
                reference.child(user.getUid()).child("foodie").setValue(foodie_var);
                reference.child(user.getUid()).child("restaurant").setValue(restaurant_var);
                Intent intent=new Intent(this,home.class);
                startActivity(intent);
                finish();
            }
            else if (restaurant_var){
                reference1.child(user.getUid()).child("restaurant").setValue(restaurant_var);
                reference1.child(user.getUid()).child("name").setValue(user.getDisplayName());
                reference1.child(user.getUid()).child("email").setValue(user.getEmail());
                Intent intent=new Intent(this,home.class);
                startActivity(intent);
                finish();
            }
        });



    }
}