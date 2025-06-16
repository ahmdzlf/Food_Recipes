package com.azarcorp.food_recipes.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


import com.azarcorp.food_recipes.R;

public class splash_login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Button loginButton = findViewById(R.id.loginButton);
        TextView createAccount = findViewById(R.id.createAccount);
        TextView laterButton = findViewById(R.id.laterButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(splash_login.this, login_activity.class);
                startActivity(intent);
            }
        });

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(splash_login.this, register_activity.class);
                startActivity(intent);
            }
        });

        laterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(splash_login.this, com.azarcorp.food_recipes.MainActivity.class);
                startActivity(intent);
                finish(); // opsional: supaya user tidak bisa kembali ke login dengan tombol back
            }
        });
    }
}
