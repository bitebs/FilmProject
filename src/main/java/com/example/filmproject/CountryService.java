package com.example.filmproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CountryService {

    @Autowired
    CountryRepository countryRepository;

    public List<Country> findAll(){
        return countryRepository.findAll();
    }

    public Optional<Country> findById(Long id){
        return countryRepository.findById(id);
    }

    public Country save(Country country){return countryRepository.save(country);}

    public void deleteById(Long id){
        countryRepository.deleteById(id);
    }
}
