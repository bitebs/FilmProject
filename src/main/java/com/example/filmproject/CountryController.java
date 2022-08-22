package com.example.filmproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CountryController {

    @Autowired
    CountryService countryService;

    @GetMapping(value="/country")
    public List<Country> findAll(){
        return countryService.findAll();
    }

    @GetMapping(value="/country/{id}")
    public Optional<Country> findById(@PathVariable Long id){
        return countryService.findById(id);
    }

    @PostMapping(value="/country")
    Country createOrSave(@RequestBody Country country)
    {return countryService.save(country);
    }

    @PutMapping(value="/country/{id}")
    Country update(@RequestBody Country newCountry, @PathVariable Long id) {
        return countryService.findById(id).map(country -> {
            country.setCountry_name(newCountry.getCountry_name());
            return countryService.save(country);
        }).orElse(null);

    }

    @DeleteMapping(value="/country/{id}")
    void deleteById(@PathVariable Long id) {
        countryService.deleteById(id);
        System.out.println("delete");
    }
}
