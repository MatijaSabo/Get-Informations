package com.unizg.foi.GetInformations.controller;

import com.unizg.foi.GetInformations.model.CurrencyModel;
import com.unizg.foi.GetInformations.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CurrencyController
{
    @Autowired
    private CurrencyService currencyService;

    @RequestMapping(value = "/currency", method = RequestMethod.GET)
    public String currencyPage(Model model)
    {
        model.addAttribute("currencys", currencyService.getCurrencySimbols());
        model.addAttribute("isConversion", false);
        return "currency";
    }

    @RequestMapping(value = "/currency", method = RequestMethod.POST)
    public String currencyConverterPage(HttpServletRequest request, Model model)
    {
        model.addAttribute("currencys", currencyService.getCurrencySimbols());

        List<CurrencyModel> list = currencyService.getCurrencyConverter(
                request.getParameter("from_select"),
                request.getParameter("to_select"),
                Double.valueOf(request.getParameter("amount")));

        System.out.println(list.get(0));

        model.addAttribute("from", list.get(0));
        model.addAttribute("to", list.get(1));
        model.addAttribute("isConversion", true);

        return "currency";
    }
}
