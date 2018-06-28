package com.unizg.foi.GetInformations.services;

import com.unizg.foi.GetInformations.model.CurrencyModel;

import java.util.List;

public interface CurrencyService
{
    List<CurrencyModel> getCurrencySimbols();
    List<CurrencyModel> getCurrencyConverter(String from, String to, Double amount);
}
