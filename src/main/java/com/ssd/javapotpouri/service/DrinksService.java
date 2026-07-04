package com.ssd.javapotpouri.service;

import com.ssd.javapotpouri.entity.Drinks;
import com.ssd.javapotpouri.repository.DrinksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DrinksService {

    @Autowired
    private DrinksRepository drinksRepository;

    public List<Drinks> getAllDrinks() {
        return drinksRepository.findAll();
    }

    public Optional<Drinks> getDrinksById(String id) {
        return drinksRepository.findById(id);
    }

    public Drinks saveDrinks(Drinks drinks) {
        return drinksRepository.save(drinks);
    }

    public void deleteDrinks(String id) {
        drinksRepository.deleteById(id);
    }
}
