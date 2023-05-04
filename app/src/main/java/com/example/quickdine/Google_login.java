package com.example.quickdine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Google_login extends AppCompatActivity {
    LinearLayout signin;
    FirebaseAuth mAuth;
    Dialog dialog;
    private static final int RC_SIGN_IN = 100;
    GoogleSignInClient mGoogleSignInClient;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_login);
        signin=findViewById(R.id.linearLayout) ;
        signin.setOnClickListener(v->{
            if (reference == null) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityIfNeeded(signInIntent, RC_SIGN_IN);
                dialog = new Dialog(this);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.loading_blue);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dialog.show();
            } else {
                updateUI();
            }



        });
        mAuth = FirebaseAuth.getInstance();
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        //realtime firebase
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);

                firebaseAuthWithGoogle(account.getIdToken());
                SharedPreferences userDetails =getSharedPreferences("userdetails", MODE_PRIVATE);
                SharedPreferences.Editor edit = userDetails.edit();
                edit.clear();
                edit.putString("username", "");
                edit.putString("email", "");
                edit.commit();

            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately

            }
        }
    }
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            reference= FirebaseDatabase.getInstance().getReference().child("users");
                            reference.child(user.getUid()).child("name").setValue(user.getDisplayName());
                            reference.child(user.getUid()).child("email").setValue(user.getEmail());
                            reference.child(user.getUid()).child("UID").setValue(user.getUid());


                            updateUI();


                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Google_login.this, "login Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void updateUI() {
        dialog.dismiss();
        Intent intent=new Intent(this,login.class);
        startActivity(intent);
        finish();
    }
    /*@Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            Intent intent = new Intent(Google_login.this,login.class);
            startActivity(intent);
            finish();
        }
    }*/
}