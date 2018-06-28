package com.unizg.foi.GetInformations.model;

import java.util.List;

public class RecipeModel {
    private String title;
    private String image_url;
    private String source;
    private String source_url;
    private String recipe_url;
    private Integer number_of_meals;
    private Integer cooking_time;
    private List<String> ingredients;

    public RecipeModel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSource_url() {
        return source_url;
    }

    public void setSource_url(String source_url) {
        this.source_url = source_url;
    }

    public String getRecipe_url() {
        return recipe_url;
    }

    public void setRecipe_url(String recipe_url) {
        this.recipe_url = recipe_url;
    }

    public Integer getNumber_of_meals() {
        return number_of_meals;
    }

    public void setNumber_of_meals(Integer number_of_meals) {
        this.number_of_meals = number_of_meals;
    }

    public Integer getCooking_time() {
        return cooking_time;
    }

    public void setCooking_time(Integer cooking_time) {
        this.cooking_time = cooking_time;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }
}
