package com.it.forum.converters;

import com.it.forum.services.ICountryService;
import org.jboss.logging.Logger;

import java.beans.PropertyEditorSupport;

/**
 * @author : Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public class CountryEditor extends PropertyEditorSupport {

    private static final Logger log = Logger.getLogger(CountryEditor.class);

    final private ICountryService countryService;

    public CountryEditor(ICountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public void setAsText(String id) throws IllegalArgumentException {
        if (id != null && !id.isEmpty()) {
            try {
                this.setValue(countryService.findCountryById(Long.parseLong(id)));
            } catch (Exception e) {
                log.error("Error convert Long to String", e);
            }
        }
    }
}
