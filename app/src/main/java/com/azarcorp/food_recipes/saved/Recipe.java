package com.azarcorp.food_recipes.saved;

public class Recipe {
    private String title;
    private int imageResId;

    public Recipe(String title, int imageResId) {
        this.title = title;
        this.imageResId = imageResId;
    }

    public String getTitle() {
        return title;
    }

    public int getImageResId() {
        return imageResId;
    }
}