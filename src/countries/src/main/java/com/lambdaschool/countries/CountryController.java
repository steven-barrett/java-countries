package com.lambdaschool.countries;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController  // managed by spring boot - mapping of end points annotation
@RequestMapping("/data")  // all end points start with /data/
public class CountryController
{
//    sort((c1, c2) -> c1.getCname().compareToIgnoreCase(c2.getCname()))
    // Returns ALL countries
    // localhost:2019/data/allcountries
    @GetMapping(value = "/allcountries",
            produces = {"application/json"})
    public ResponseEntity<?> getAllCountries()
    {
        ArrayList<Country> list = CountriesApplication.countries.findCountries(e -> (e.getPopulation() > 0));
        list.sort((c1, c2) -> c1.getCname().compareToIgnoreCase(c2.getCname()));
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // Returns a country with a particular ID
    // localhost:2019/data/country/2
    @GetMapping(value = "/country/{id}",
            produces = {"application/json"})
    public ResponseEntity<?> getCountryDetail(@PathVariable long id)
    {
        Country rtnCountry = CountriesApplication.countries.findCountry(e -> (e.getId() == id));
        return new ResponseEntity<>(rtnCountry, HttpStatus.OK);
    }

    // Returns a list of countries that start with a given letter
    // localhost:2019/data/country/start/a
    @GetMapping(value = "/country/start/{letter}",
            produces = {"application/json"})
    public ResponseEntity<?> getCountriesAtGivenLetter(@PathVariable String letter)
    {
        ArrayList<Country> list = CountriesApplication.countries.findCountries(e -> (e.getCname().toLowerCase().charAt(0)) == letter.toCharArray()[0]);
        list.sort((c1, c2) -> c1.getCname().compareToIgnoreCase(c2.getCname()));
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // Returns a list of countries that have a name length greater than the length provided
    // localhost:2019/data/country/size/7
    @GetMapping(value = "/country/size/{size}",
            produces = {"application/json"})
    public ResponseEntity<?> getCountryByNameLength(@PathVariable int size)
    {
        ArrayList<Country> list = CountriesApplication.countries.findCountries(e -> (e.getCname().length() >= size));
        list.sort((c1, c2) -> c1.getCname().compareToIgnoreCase(c2.getCname()));
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // localhost:2019/data/country/size/7
    @GetMapping(value = "/country/popsize/{people}",
            produces = {"application/json"})
    public ResponseEntity<?> getCountryByPopulationSize(@PathVariable int people)
    {
        ArrayList<Country> list = CountriesApplication.countries.findCountries(e -> (e.getPopulation() >= people));
        list.sort((c1, c2) -> c1.getCname().compareToIgnoreCase(c2.getCname()));
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // Returns the country with the smallest population
    // localhost:2019/data/country/population/min
    @GetMapping(value = "/country/population/min",
            produces = {"application/json"})
    public ResponseEntity<?> getCountryWithSmallestPop()
    {
        ArrayList<Country> smallestPop = new ArrayList<>();
        ArrayList<Country> list = CountriesApplication.countries.findCountries(e -> (e.getPopulation() > 0));
        list.sort((c1, c2) -> (int)(c1.getPopulation() - c2.getPopulation()));
        smallestPop.add(list.get(0));
        return new ResponseEntity<>(smallestPop, HttpStatus.OK);
    }

    // Returns the country with the largest population
    // localhost:2019/data/country/population/min
    @GetMapping(value = "/country/population/max",
            produces = {"application/json"})
    public ResponseEntity<?> getCountryWithLargestPop()
    {
        ArrayList<Country> largestPop = new ArrayList<>();
        ArrayList<Country> list = CountriesApplication.countries.findCountries(e -> (e.getPopulation() > 1));
        list.sort((c1, c2) -> (int)(c2.getPopulation() - c1.getPopulation()));
        largestPop.add(list.get(0));
        return new ResponseEntity<>(largestPop, HttpStatus.OK);
    }

    //********************Stretch******************************
    // Returns the country with the median population
    // localhost:2019/data/country/population/min
    @GetMapping(value = "/country/population/median",
            produces = {"application/json"})
    public ResponseEntity<?> getCountryWithMedianPop()
    {
        ArrayList<Country> medianPop = new ArrayList<>();
        ArrayList<Country> list = CountriesApplication.countries.findCountries(e -> (e.getPopulation() > 2));
        list.sort((c1, c2) -> (int)(c1.getPopulation() - c2.getPopulation()));

        int medianIndex = list.size() / 2;
        medianPop.add(list.get(medianIndex));
        return new ResponseEntity<>(medianPop, HttpStatus.OK);
    }

    // Returns all countries with a median age greater than the given age
    // localhost:2019/data/country/age/30
    @GetMapping(value = "/country/age/{age}",
            produces = {"application/json"})
    public ResponseEntity<?> getCountryByMedianAge(@PathVariable int age)
    {
        ArrayList<Country> list = CountriesApplication.countries.findCountries(e -> (e.getMedianage() >= age));
        list.sort((c1, c2) -> (int)(c1.getMedianage() - c2.getMedianage()));
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // Returns the country with the smallest median age
    // localhost:2019/data/country/population/min
    @GetMapping(value = "/country/median/min",
            produces = {"application/json"})
    public ResponseEntity<?> getCountryWithSmallestMedian()
    {
        ArrayList<Country> smallestMedian = new ArrayList<>();
        ArrayList<Country> list = CountriesApplication.countries.findCountries(e -> (e.getPopulation() > 3));
        list.sort((c1, c2) -> (int)(c1.getMedianage() - c2.getMedianage()));
        smallestMedian.add(list.get(0));
        return new ResponseEntity<>(smallestMedian, HttpStatus.OK);
    }

    // Returns the country with the largest median age
    // localhost:2019/data/country/median/max
    @GetMapping(value = "/country/median/max",
            produces = {"application/json"})
    public ResponseEntity<?> getCountryWithLargestMedian()
    {
        ArrayList<Country> largestMedian = new ArrayList<>();
        ArrayList<Country> list = CountriesApplication.countries.findCountries(e -> (e.getPopulation() > 4));
        list.sort((c1, c2) -> (int)(c2.getMedianage() - c1.getMedianage()));
        largestMedian.add(list.get(0));
        return new ResponseEntity<>(largestMedian, HttpStatus.OK);
    }

    // localhost:2019/data/country/median/age
    @GetMapping(value = "/country/median/age",
            produces = {"application/json"})
    public ResponseEntity<?> getCountryWithMedianMedianAge()
    {
        ArrayList<Country> medianAge = new ArrayList<>();
        ArrayList<Country> list = CountriesApplication.countries.findCountries(e -> (e.getPopulation() > 4));
        list.sort((c1, c2) -> (int)(c1.getMedianage() - c2.getMedianage()));

        int medianIndex = list.size() / 2;
        medianAge.add(list.get(medianIndex));
        return new ResponseEntity<>(medianAge, HttpStatus.OK);
    }

}
