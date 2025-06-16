package com.azarcorp.food_recipes.profile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.azarcorp.food_recipes.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile_Activity extends AppCompatActivity {

    private CircleImageView imgProfile;
    private TextView tvUserName;
    private ImageView btnEditPhoto;
    private RecyclerView rvUserRecipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile); // Ganti jika XML kamu pakai nama berbeda

        imgProfile = findViewById(R.id.img_profile);
        tvUserName = findViewById(R.id.tv_user_name);
        btnEditPhoto = findViewById(R.id.btn_edit_photo);
        rvUserRecipes = findViewById(R.id.rv_user_recipes);

        // Ambil SharedPreferences dari Login
        SharedPreferences preferences = getSharedPreferences("UserSession", MODE_PRIVATE);
        boolean isLoggedIn = preferences.getBoolean("isLoggedIn", false);
        String userName = preferences.getString("userName", null);

        if (isLoggedIn && userName != null) {
            tvUserName.setText(userName);
        } else {
            tvUserName.setText("Login");

            tvUserName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Profile_Activity.this, com.azarcorp.food_recipes.login.login_activity.class);
                    startActivity(intent);
                }
            });
        }

        // Foto profil: default karena belum ada upload
        imgProfile.setImageResource(R.drawable.placeholders);

        // Jika pengguna klik icon edit, arahkan ke login jika belum login
        btnEditPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isLoggedIn) {
                    Intent intent = new Intent(Profile_Activity.this, com.azarcorp.food_recipes.login.login_activity.class);
                    startActivity(intent);
                } else {
                    // Nanti bisa diarahkan ke halaman ganti foto atau edit profil
                }
            }
        });

        // RecyclerView resep pengguna bisa kamu isi di sini (nanti pakai adapter)
        // rvUserRecipes.setAdapter(...);
    }
}
