package com.azarcorp.food_recipes.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.azarcorp.food_recipes.MainActivity;
import com.azarcorp.food_recipes.R;

public class login_activity extends AppCompatActivity {

    EditText emailInput, passwordInput;
    Button loginButton;
    TextView CreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login2); // Pastikan file XML kamu benar

        emailInput = findViewById(R.id.etEmail);
        passwordInput = findViewById(R.id.etPassword);
        loginButton = findViewById(R.id.btnLogin);
        CreateAccount = findViewById(R.id.tvCreateAccount);

        // Ambil data email & password dari SharedPreferences register
        SharedPreferences credentials = getSharedPreferences("USER_CREDENTIALS", MODE_PRIVATE);
        String savedEmail = credentials.getString("email", null);
        String savedPassword = credentials.getString("password", null);

        loginButton.setOnClickListener(v -> {
            String inputEmail = emailInput.getText().toString().trim();
            String inputPassword = passwordInput.getText().toString().trim();

            if (inputEmail.isEmpty() || inputPassword.isEmpty()) {
                Toast.makeText(login_activity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            } else if (inputEmail.equals(savedEmail) && inputPassword.equals(savedPassword)) {
                Toast.makeText(login_activity.this, "Login successful", Toast.LENGTH_SHORT).show();

                // Simpan status login dan nama/email ke UserSession
                SharedPreferences sessionPrefs = getSharedPreferences("UserSession", MODE_PRIVATE);
                SharedPreferences.Editor editor = sessionPrefs.edit();
                editor.putBoolean("isLoggedIn", true);
                editor.putString("userName", inputEmail); // Atau simpan nama lengkap kalau tersedia
                editor.apply();

                // Redirect ke halaman utama
                Intent intent = new Intent(login_activity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(login_activity.this, "Incorrect email or password", Toast.LENGTH_SHORT).show();
            }
        });

        // Arahkan ke halaman register jika klik "Create Account"
        CreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login_activity.this, register_activity.class);
                startActivity(intent);
            }
        });
    }
}
