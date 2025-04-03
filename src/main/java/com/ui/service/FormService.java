package com.ui.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class FormService {

    public Map<String, String> getAllCountries() {
        Map<String, String> countries = new HashMap<>();
        countries.put("UK", "United Kingdom");
        countries.put("USA", "United States");
        countries.put("NZ", "New Zealand");
        countries.put("CA", "Canada");
        countries.put("AU", "Australia");
        return countries;
    }

    public Map<String, String> getAllTitles() {

        Map<String, String> titles = new HashMap<>();
        titles.put("Mr", "Mr");
        titles.put("Mrs", "Mrs");
        titles.put("Miss", "Miss");
        return titles;
    }

    public Map<String, String> getAllRegions() {

        Map<String, String> regions = new HashMap<>();
        regions.put("BERK", "Berkshire");
        regions.put("HERT", "Hertfordshire");
        regions.put("OXFD", "Oxfordshire");
        return regions;
    }

    public Map<String, String> getAllShippedVia() {

        Map<String, String> regions = new HashMap<>();
        regions.put("11", "Center1");
        regions.put("22", "Center2");
        regions.put("33", "Center3");
        return regions;
    }

}
