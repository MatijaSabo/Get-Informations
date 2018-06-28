package com.unizg.foi.GetInformations.controller;

import com.unizg.foi.GetInformations.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.unizg.foi.GetInformations.services.LocationService;
import com.unizg.foi.GetInformations.services.PlaceService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController
{
    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String homePage(Model model) throws Exception
    {
        return "index";
    }
}
