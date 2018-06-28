package com.unizg.foi.GetInformations.model;

import java.util.List;

public class RecipeSearchModel {
    private String query;
    private Integer from;
    private Integer to;
    private Integer total;
    private Boolean has_more_recipes;
    private List<RecipeModel> recipes;

    public RecipeSearchModel() {
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getTo() {
        return to;
    }

    public void setTo(Integer to) {
        this.to = to;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Boolean getHas_more_recipes() {
        return has_more_recipes;
    }

    public void setHas_more_recipes(Boolean has_more_recipes) {
        this.has_more_recipes = has_more_recipes;
    }

    public List<RecipeModel> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<RecipeModel> recipes) {
        this.recipes = recipes;
    }
}
