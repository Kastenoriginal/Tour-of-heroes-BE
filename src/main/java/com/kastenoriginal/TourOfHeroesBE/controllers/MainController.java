package com.kastenoriginal.TourOfHeroesBE.controllers;

import com.kastenoriginal.TourOfHeroesBE.entities.Hero;
import com.kastenoriginal.TourOfHeroesBE.repositories.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/demo")
public class MainController {

    @Autowired
    private HeroRepository heroRepository;

    @GetMapping(path = "/add")
    public @ResponseBody
    String addHero(@RequestParam String name, @RequestParam String heroType, @RequestParam String imageName, @RequestParam String bio) {
        Hero hero = new Hero();
        hero.setName(name);
        hero.setHeroType(heroType);
        hero.setImageName(imageName);
        hero.setBio(bio);
        heroRepository.save(hero);
        return "Saved";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Hero> getAllHeroes() {
        return heroRepository.findAll();
    }
}
