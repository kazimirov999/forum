package com.it.forum.services.impl;

import com.it.forum.domain.dao.ICountryDao;
import com.it.forum.domain.entities.Country;
import com.it.forum.services.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
@Transactional
@Service
public class CountryServiceImpl implements ICountryService {

    @Autowired
    private ICountryDao countryDao;

    @Transactional(readOnly = true)
    @Override
    public Country findCountryById(Long countryId) {

        return countryDao.findById(countryId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Country> findAllCountries() {

        return countryDao.findAll();
    }

}
