package com.azarcorp.food_recipes.saved;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.azarcorp.food_recipes.R;

import java.util.ArrayList;
import java.util.List;

public class SavedRecipesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecipeAdapter adapter;
    private List<Recipe> recipeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_recipes);

        recyclerView = findViewById(R.id.rv_saved_recipes);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        recipeList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            recipeList.add(new Recipe("Japanese-style Pancakes Recipe", R.drawable.baso));
        }

        adapter = new RecipeAdapter(recipeList);
        recyclerView.setAdapter(adapter);
    }
}
