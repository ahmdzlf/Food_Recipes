package com.azarcorp.food_recipes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.azarcorp.food_recipes.Resep;
import com.azarcorp.food_recipes.profile.Profile_Activity;
import com.azarcorp.food_recipes.saved.SavedRecipesActivity;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerFeatured, recyclerPopular;
    private MaterialToolbar toolbar;
    private BottomNavigationView bottomNavigationView;
    private FloatingActionButton fabAdd;

    private FirebaseFirestore db;
    private CollectionReference resepRef;

    private FeaturedAdapter featuredAdapter;
    private PopularAdapter popularAdapter;

    private List<Resep> featuredResepList = new ArrayList<>();
    private List<Resep> popularResepList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Toolbar setup
        toolbar = findViewById(R.id.topAppBar);
        setSupportActionBar(toolbar);

        SharedPreferences sessionPrefs = getSharedPreferences("UserSession", MODE_PRIVATE);
        String userName = sessionPrefs.getString("userName", "Pengguna");
        getSupportActionBar().setTitle("Hai, " + userName + "!");

        // Init RecyclerViews
        recyclerFeatured = findViewById(R.id.recycler_recommendation);
        recyclerPopular = findViewById(R.id.recycler_grid);
        recyclerFeatured.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerPopular.setLayoutManager(new GridLayoutManager(this, 2));

        // Init Adapters
        featuredAdapter = new FeaturedAdapter(this, featuredResepList);
        popularAdapter = new PopularAdapter(this, popularResepList);
        recyclerFeatured.setAdapter(featuredAdapter);
        recyclerPopular.setAdapter(popularAdapter);

        // Item click
        featuredAdapter.setOnItemClickListener(this::bukaDetailResep);
        popularAdapter.setOnItemClickListener(this::bukaDetailResep);

        // Firebase setup
        db = FirebaseFirestore.getInstance();
        resepRef = db.collection("resep");

        ambilDataResep();

        // Bottom Navigation
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        fabAdd = findViewById(R.id.fab_center);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_home) {
                return true;
            } else if (item.getItemId() == R.id.nav_profile) {
                startActivity(new Intent(MainActivity.this, Profile_Activity.class));
                return true;
            }
            return false;
        });

        // FAB action
        fabAdd.setOnClickListener(v ->
                Toast.makeText(MainActivity.this, "Fitur tambah resep belum tersedia", Toast.LENGTH_SHORT).show()
        );

        Toast.makeText(this, "Selamat datang, " + userName + "!", Toast.LENGTH_SHORT).show();
    }

    private void ambilDataResep() {
        resepRef.get().addOnSuccessListener(queryDocumentSnapshots -> {
            List<Resep> allResep = new ArrayList<>();
            for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                Resep resep = doc.toObject(Resep.class);
                resep.setId(doc.getId());
                allResep.add(resep);
            }

            // Batasi jumlah yang ditampilkan untuk featured
            featuredResepList.clear();
            popularResepList.clear();
            for (int i = 0; i < allResep.size(); i++) {
                if (i < 5) {
                    featuredResepList.add(allResep.get(i));
                }
                popularResepList.add(allResep.get(i));
            }

            featuredAdapter.notifyDataSetChanged();
            popularAdapter.notifyDataSetChanged();
        }).addOnFailureListener(e ->
                Toast.makeText(MainActivity.this, "Gagal mengambil data: " + e.getMessage(), Toast.LENGTH_SHORT).show()
        );
    }

    private void bukaDetailResep(Resep resep) {
        Intent intent = new Intent(MainActivity.this, DetailResepActivity.class);
        intent.putExtra("idResep", resep.getId());
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_app_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_favorite) {
            startActivity(new Intent(MainActivity.this, SavedRecipesActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
