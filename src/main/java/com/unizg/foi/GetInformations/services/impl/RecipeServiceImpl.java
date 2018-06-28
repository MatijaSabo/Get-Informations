package com.unizg.foi.GetInformations.services.impl;

import com.unizg.foi.GetInformations.constants.ApiKeys;
import com.unizg.foi.GetInformations.model.RecipeModel;
import com.unizg.foi.GetInformations.model.RecipeSearchModel;
import com.unizg.foi.GetInformations.services.RecipeService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Service("recipeService")
public class RecipeServiceImpl implements RecipeService
{
    private static final String EDAMAM_RECIPES_SEARCH_BASE_URL = "https://api.edamam.com/search";

    private static final String SEARCH_PARAM = "q";
    private static final String APP_ID_PARAM = "app_id";
    private static final String APP_KEY_PARAM = "app_key";
    private static final String FROM_PARAM = "from";
    private static final String TO_PARAM = "to";

    @Override
    public RecipeSearchModel getRecipesByIngredients(String ingredients)
    {
        RecipeSearchModel search = new RecipeSearchModel();

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(EDAMAM_RECIPES_SEARCH_BASE_URL)
                .queryParam(APP_ID_PARAM, ApiKeys.EDAMAM_APP_ID)
                .queryParam(APP_KEY_PARAM, ApiKeys.EDAMAM_APP_KEY)
                .queryParam(SEARCH_PARAM, ingredients);

        RestTemplate request = new RestTemplate();

        String result = request.getForObject(builder.toUriString(), String.class);

        JSONObject jsonObject = new JSONObject(result);

        search.setQuery(jsonObject.getString("q"));
        search.setFrom(jsonObject.getInt("from"));
        search.setTo(jsonObject.getInt("to"));
        search.setTotal(jsonObject.getInt("count"));
        search.setHas_more_recipes(jsonObject.getBoolean("more"));

        List<RecipeModel> recipes = new ArrayList<RecipeModel>();
        JSONArray jsonArray = jsonObject.getJSONArray("hits");

        for(int i = 0; i < jsonArray.length(); i++)
        {
            JSONObject recipeObject = jsonArray.getJSONObject(i).getJSONObject("recipe");
            RecipeModel recipe = new RecipeModel();

            recipe.setTitle(recipeObject.getString("label"));
            recipe.setImage_url(recipeObject.getString("image"));
            recipe.setSource(recipeObject.getString("source"));
            recipe.setSource_url(recipeObject.getString("url"));
            recipe.setRecipe_url(recipeObject.getString("shareAs"));
            recipe.setNumber_of_meals(recipeObject.getInt("yield"));
            recipe.setCooking_time(recipeObject.getInt("totalTime"));

            List<String> ingredientsList = new ArrayList<String>();
            JSONArray ingredientsArray = recipeObject.getJSONArray("ingredientLines");

            for(int j = 0; j < ingredientsArray.length(); j++)
            {
                ingredientsList.add(ingredientsArray.getString(j));
            }

            recipe.setIngredients(ingredientsList);
            recipes.add(recipe);
        }

        search.setRecipes(recipes);

        return search;
    }

    @Override
    public RecipeSearchModel getRecipesByIngredients(String ingredients, Integer from, Integer to) {
        RecipeSearchModel search = new RecipeSearchModel();

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(EDAMAM_RECIPES_SEARCH_BASE_URL)
                .queryParam(APP_ID_PARAM, ApiKeys.EDAMAM_APP_ID)
                .queryParam(APP_KEY_PARAM, ApiKeys.EDAMAM_APP_KEY)
                .queryParam(SEARCH_PARAM, ingredients)
                .queryParam(FROM_PARAM, from)
                .queryParam(TO_PARAM, to);

        RestTemplate request = new RestTemplate();

        String result = request.getForObject(builder.toUriString(), String.class);

        JSONObject jsonObject = new JSONObject(result);

        search.setQuery(jsonObject.getString("q"));
        search.setFrom(jsonObject.getInt("from"));
        search.setTo(jsonObject.getInt("to"));
        search.setTotal(jsonObject.getInt("count"));
        search.setHas_more_recipes(jsonObject.getBoolean("more"));

        List<RecipeModel> recipes = new ArrayList<RecipeModel>();
        JSONArray jsonArray = jsonObject.getJSONArray("hits");

        for(int i = 0; i < jsonArray.length(); i++)
        {
            JSONObject recipeObject = jsonArray.getJSONObject(i).getJSONObject("recipe");
            RecipeModel recipe = new RecipeModel();

            recipe.setTitle(recipeObject.getString("label"));
            recipe.setImage_url(recipeObject.getString("image"));
            recipe.setSource(recipeObject.getString("source"));
            recipe.setSource_url(recipeObject.getString("url"));
            recipe.setRecipe_url(recipeObject.getString("shareAs"));
            recipe.setNumber_of_meals(recipeObject.getInt("yield"));
            recipe.setCooking_time(recipeObject.getInt("totalTime"));

            List<String> ingredientsList = new ArrayList<String>();
            JSONArray ingredientsArray = recipeObject.getJSONArray("ingredientLines");

            for(int j = 0; j < ingredientsArray.length(); j++)
            {
                ingredientsList.add(ingredientsArray.getString(j));
            }

            recipe.setIngredients(ingredientsList);
            recipes.add(recipe);
        }

        search.setRecipes(recipes);

        return search;
    }
}
