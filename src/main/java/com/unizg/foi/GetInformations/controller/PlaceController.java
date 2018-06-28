package com.unizg.foi.GetInformations.controller;

import com.unizg.foi.GetInformations.model.PlaceDetailsModel;
import com.unizg.foi.GetInformations.model.LocationModel;
import com.unizg.foi.GetInformations.model.PlaceSearchModel;
import com.unizg.foi.GetInformations.services.LocationService;
import com.unizg.foi.GetInformations.services.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Controller
public class PlaceController {

    @Autowired
    private LocationService locationService;

    @Autowired
    private PlaceService placeService;

    @RequestMapping(value = "/place", method = RequestMethod.GET)
    public String placePage(Model model)
    {
        return "place";
    }

    @RequestMapping(value = "/place", method = RequestMethod.POST)
    public String placeSearchPage(HttpServletRequest request, Model model) throws UnsupportedEncodingException
    {
        LocationModel locationModel = locationService.getLocationByAddress(request.getParameter("place"));
        PlaceSearchModel result = placeService.getPlacesByCategoryInRange(locationModel,
                request.getParameter("category").toLowerCase().replaceAll(" ", "_"),
                Integer.valueOf(request.getParameter("radius")));

        model.addAttribute("result", result);
        model.addAttribute("places", result.getPlaces());

        return "place";
    }

    @RequestMapping(value = "/place/{next_page_token}", method = RequestMethod.GET)
    public String placeSearchPageNextPage(@PathVariable("next_page_token") String next_page_token, Model model)
    {
        PlaceSearchModel result = placeService.getPlacesByCategoryInRange(next_page_token);

        model.addAttribute("result", result);
        model.addAttribute("places", result.getPlaces());

        return "place";
    }

    @RequestMapping(value = "/placeDetails/{placeId}", method = RequestMethod.GET)
    public String placeDetailsPage(@PathVariable("placeId") String placeId, Model model)
    {
        PlaceDetailsModel result = placeService.getPlaceDetails(placeId);
        model.addAttribute("placeDetails", result);

        return "placeDetails";
    }

}
