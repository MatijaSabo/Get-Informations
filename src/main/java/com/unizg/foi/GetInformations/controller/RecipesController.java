package com.unizg.foi.GetInformations.controller;

import com.unizg.foi.GetInformations.model.RecipeSearchModel;
import com.unizg.foi.GetInformations.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RecipesController {

    @Autowired
    private RecipeService recipeService;

    @RequestMapping(value = "/recipes", method = RequestMethod.GET)
    public String recipesPage(Model model)
    {
        return "recipes";
    }

    @RequestMapping(value = "/recipes", method = RequestMethod.POST)
    public String recipeSearchPage(HttpServletRequest request , Model model)
    {
        RecipeSearchModel recipeSearchModel = recipeService.getRecipesByIngredients(request.getParameter("ingredients"));
        model.addAttribute("recipeSearch", recipeSearchModel);
        return "recipes";
    }

    @RequestMapping(value = "/recipes/{ingredients}/{from}/{to}", method = RequestMethod.GET)
    public String recipeSearchPageNext(@PathVariable("ingredients") String ingredients, @PathVariable("from") Integer from, @PathVariable("to") Integer to, Model model)
    {
        RecipeSearchModel recipeSearchModel = recipeService.getRecipesByIngredients(ingredients, from, to);
        model.addAttribute("recipeSearch", recipeSearchModel);
        return "recipes";
    }
}
