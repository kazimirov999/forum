package com.it.forum.domain.dao.impl;

import com.it.forum.domain.dao.ICountryDao;
import com.it.forum.domain.entities.Country;
import org.springframework.stereotype.Repository;

/**
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
@Repository
public class CountryDaoImpl extends GenericDaoImpl<Country, Long> implements ICountryDao {

    public CountryDaoImpl() {
        super(Country.class);
    }
}
