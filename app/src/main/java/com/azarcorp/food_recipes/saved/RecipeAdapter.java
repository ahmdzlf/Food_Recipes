package com.azarcorp.food_recipes.saved;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.azarcorp.food_recipes.R;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    private List<Recipe> recipeList;

    public RecipeAdapter(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView recipeImage;
        public TextView recipeTitle;

        public ViewHolder(View view) {
            super(view);
            recipeImage = view.findViewById(R.id.img_recipe);
            recipeTitle = view.findViewById(R.id.tv_recipe_title);
        }
    }

    @Override
    public RecipeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recipe_saved, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Recipe recipe = recipeList.get(position);
        holder.recipeTitle.setText(recipe.getTitle());
        holder.recipeImage.setImageResource(recipe.getImageResId());
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }
}
