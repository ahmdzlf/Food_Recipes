package com.azarcorp.food_recipes.login;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.azarcorp.food_recipes.R;

public class register_activity extends AppCompatActivity {

    EditText fullName, email, password, confirmPassword;
    CheckBox termsCheckbox;
    Button getStartedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        fullName = findViewById(R.id.fullName);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);
        termsCheckbox = findViewById(R.id.termsCheckbox);
        getStartedButton = findViewById(R.id.getStartedButton);

        getStartedButton.setOnClickListener(v -> {
            String name = fullName.getText().toString().trim();
            String userEmail = email.getText().toString().trim();
            String pass = password.getText().toString().trim();
            String confirmPass = confirmPassword.getText().toString().trim();

            if (name.isEmpty() || userEmail.isEmpty() || pass.isEmpty() || confirmPass.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else if (!pass.equals(confirmPass)) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            } else if (!termsCheckbox.isChecked()) {
                Toast.makeText(this, "You must agree to the terms", Toast.LENGTH_SHORT).show();
            } else {
                // Simpan data sementara ke SharedPreferences
                getSharedPreferences("USER_CREDENTIALS", MODE_PRIVATE).edit()
                        .putString("email", userEmail)
                        .putString("password", pass)
                        .apply();

                Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show();

                // Arahkan ke login2 layout
                Intent intent = new Intent(register_activity.this, login_activity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
