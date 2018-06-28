package com.unizg.foi.GetInformations.controller;

import com.unizg.foi.GetInformations.model.WeatherModel;
import com.unizg.foi.GetInformations.model.LocationModel;
import com.unizg.foi.GetInformations.services.LocationService;
import com.unizg.foi.GetInformations.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
@Controller
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private LocationService locationService;

    @RequestMapping(value = "/weather", method = RequestMethod.GET)
    public String weatherPage(Model model)
    {
        return "weather";
    }

    @RequestMapping(value = "/weather", method = RequestMethod.POST)
    public String weatherDataPage(HttpServletRequest request, Model model) throws IOException
    {
        LocationModel locationModel = locationService.getLocationByAddress(request.getParameter("address"));

        if(StringUtils.equals(request.getParameter("type"), "current"))
        {
            WeatherModel weatherModel = weatherService.getCurrentWeather(locationModel);
            model.addAttribute("current", weatherModel);
        }
        else
        {
            List<WeatherModel> weatherList = weatherService.getFiveDaysWeather(locationModel);
            model.addAttribute("list", weatherList);
        }

        return "weather";
    }
}
