package com.kastenoriginal.TourOfHeroesBE.controllers;

import com.kastenoriginal.TourOfHeroesBE.entities.Hero;
import com.kastenoriginal.TourOfHeroesBE.repositories.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PutMapping(path = "heroes/{id}")
    public @ResponseBody
    ResponseEntity<String>udpateHero(@PathVariable(value = "id") Integer id, @Valid @RequestBody Hero heroDetails) {
        Hero hero = heroRepository.findById(id).orElseThrow(() -> new MissingResourceException("Hero with id: " + id + " not found", "MainController", "Key"));

        hero.setName(heroDetails.getName());
        hero.setHeroType(heroDetails.getHeroType());
        hero.setImageName(heroDetails.getImageName());
        hero.setBio(heroDetails.getBio());

        heroRepository.save(hero);
        return new ResponseEntity<>("Sprava", HttpStatus.OK);
    }

    @DeleteMapping("heroes/{id}")
    public ResponseEntity<String> deleteHero(@PathVariable(value = "id") Integer id) {
        Hero hero = heroRepository.findById(id).orElseThrow(() -> new MissingResourceException("Hero with id: " + id + " not found", "Main Controller", "Key"));

        heroRepository.delete(hero);

        return ResponseEntity.ok().build();
    }
}
