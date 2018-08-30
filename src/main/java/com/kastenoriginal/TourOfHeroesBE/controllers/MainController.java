package com.kastenoriginal.TourOfHeroesBE.controllers;

import com.kastenoriginal.TourOfHeroesBE.entities.Hero;
import com.kastenoriginal.TourOfHeroesBE.repositories.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.MissingResourceException;

@Controller
@RequestMapping(path = "/tourofheroes")
public class MainController {

    @Autowired
    private HeroRepository heroRepository;

    @GetMapping(path = "/heroes")
    public @ResponseBody
    Iterable<Hero> getAllHeroes() {
        return heroRepository.findAll();
    }

    @GetMapping(path = "/heroes/{id}")
    public @ResponseBody
    Hero getHeroById(@PathVariable Integer id) {
        return heroRepository.findById(id).orElseThrow(() -> new MissingResourceException("Hero with id: " + id + " not found.", "MainController", "Key"));
    }

    @PostMapping(path = "/add")
    public @ResponseBody
    Hero addHero(@Valid @RequestBody Hero hero) {
        return heroRepository.save(hero);
    }
}
