package com.unizg.foi.GetInformations.services.impl;

import com.unizg.foi.GetInformations.constants.ApiKeys;
import com.unizg.foi.GetInformations.model.CurrencyModel;
import com.unizg.foi.GetInformations.services.CurrencyService;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service("currencyService")
public class CurrencyServiceImpl implements CurrencyService
{
    public static final String CURRENCY_CONVERTER_API_CURRENCIES = "https://free.currencyconverterapi.com/api/v5/currencies";
    public static final String CURRENCY_CONVERTER_API = "https://free.currencyconverterapi.com/api/v5/convert";
    public static final String QUERY_PARAM = "q";

    public static final String RESULTS_KEY = "results";
    public static final String CURRENCY_SYMBOL_KEY = "currencySymbol";
    public static final String CURRENCY_NAME_KEY = "currencyName";
    public static final String CURRENCY_ID_KEY = "id";
    public static final String FROM_KEY = "fr";
    public static final String TO_KEY = "to";
    public static final String VALUE_KEY = "val";

    @Override
    public List<CurrencyModel> getCurrencySimbols()
    {
        List<CurrencyModel> symbols = new ArrayList<CurrencyModel>();

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(CURRENCY_CONVERTER_API_CURRENCIES);

        RestTemplate request = new RestTemplate();
        String result = request.getForObject(builder.toUriString(), String.class);

        JSONObject jsonObject = new JSONObject(result);
        Iterator<String> keys = jsonObject.getJSONObject(RESULTS_KEY).keys();

        while(keys.hasNext()){
            JSONObject currencySymbolObject = jsonObject.getJSONObject(RESULTS_KEY).getJSONObject(keys.next());

            CurrencyModel currency = new CurrencyModel();

            if(currencySymbolObject.has(CURRENCY_SYMBOL_KEY))
            {
                currency.setSymbol(currencySymbolObject.getString(CURRENCY_SYMBOL_KEY));
            }

            currency.setName(currencySymbolObject.getString(CURRENCY_NAME_KEY));
            currency.setId(currencySymbolObject.getString(CURRENCY_ID_KEY));

            symbols.add(currency);
        }

        return symbols;
    }

    @Override
    public List<CurrencyModel> getCurrencyConverter(String from, String to, Double amount)
    {
        List<CurrencyModel> currencys = new ArrayList<CurrencyModel>();

        String query = from + "_" + to;

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(CURRENCY_CONVERTER_API)
                .queryParam(QUERY_PARAM, query);

        RestTemplate request = new RestTemplate();
        String result = request.getForObject(builder.toUriString(), String.class);

        JSONObject jsonObject = new JSONObject(result);
        Iterator<String> keys = jsonObject.getJSONObject(RESULTS_KEY).keys();
        JSONObject currencySymbolObject = jsonObject.getJSONObject(RESULTS_KEY).getJSONObject(keys.next());

        CurrencyModel fromCurrency = new CurrencyModel();
        fromCurrency.setId(currencySymbolObject.getString(FROM_KEY));
        fromCurrency.setAmount(amount);
        currencys.add(fromCurrency);

        CurrencyModel toCurrency = new CurrencyModel();
        toCurrency.setId(currencySymbolObject.getString(TO_KEY));
        toCurrency.setAmount(amount * currencySymbolObject.getDouble(VALUE_KEY));
        currencys.add(toCurrency);

        return currencys;
    }
}
