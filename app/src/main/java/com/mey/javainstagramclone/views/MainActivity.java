package com.mey.javainstagramclone.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.mey.javainstagramclone.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_main);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);

        FirebaseApp.initializeApp(this);


        firebaseAuth=FirebaseAuth.getInstance();

        //eğer önceden giriş yapmış kullanıcı varsa tekrar şifre sormamsı için
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();

        if (firebaseUser != null){
            Intent intent=new Intent(MainActivity.this, FeedActivity.class);
            startActivity(intent);
            finish();


        }

    }

    public void signIn(View v) {
        String email = binding.mailTxt.getText().toString();
        String password = binding.passwordTxt.getText().toString();

        if (email.equals("") || password.equals("")) {
            Toast.makeText(this, "Please enter email and password", Toast.LENGTH_LONG).show();
        } else {
            firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            // Giriş başarılıysa FeedActivity'e yönlendirme
                            Intent intent = new Intent(MainActivity.this, FeedActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Giriş başarısız ise hata mesajını gösterme
                            Toast.makeText(MainActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }


    public void signUp(View v){

        String email=binding.mailTxt.getText().toString();
        String password=binding.passwordTxt.getText().toString();
        if (email.equals("") || password.equals("")){
            Toast.makeText(this,"enter the mail and password",Toast.LENGTH_LONG).show();
        }
        else {
            firebaseAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {

                    Intent intent=new Intent(MainActivity.this,FeedActivity.class);
                    startActivity(intent);
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this,e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                }
            });

        }



    }
}