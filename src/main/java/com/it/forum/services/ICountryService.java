package com.it.forum.services;

import com.it.forum.domain.entities.Country;

import java.util.List;

/**
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public interface ICountryService {

    Country findCountryById(Long countryId);

    List<Country> findAllCountries();
}
