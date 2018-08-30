package com.kastenoriginal.TourOfHeroesBE.repositories;

import com.kastenoriginal.TourOfHeroesBE.entities.Hero;
import org.springframework.data.repository.CrudRepository;

public interface HeroRepository extends CrudRepository<Hero, Integer> {

}
