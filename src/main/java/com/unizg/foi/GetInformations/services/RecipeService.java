package com.unizg.foi.GetInformations.services;

import com.unizg.foi.GetInformations.model.RecipeSearchModel;

public interface RecipeService
{
    RecipeSearchModel getRecipesByIngredients(String ingredients);
    RecipeSearchModel getRecipesByIngredients(String ingredients, Integer from, Integer to);
}
