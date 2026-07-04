package com.ssd.javapotpouri.controller;

import com.ssd.javapotpouri.entity.Drinks;
import com.ssd.javapotpouri.service.DrinksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/drinks")
public class DrinksController {

    @Autowired
    private DrinksService drinksService;

    @GetMapping
    public List<Drinks> getAllDrinks() {
        return drinksService.getAllDrinks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Drinks> getDrinksById(@PathVariable String id) {
        Optional<Drinks> drinks = drinksService.getDrinksById(id);
        return drinks.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Drinks createDrinks(@RequestBody Drinks drinks) {
        return drinksService.saveDrinks(drinks);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDrinksById(@PathVariable String id) {
        drinksService.deleteDrinks(id);
        return ResponseEntity.ok().build();
    }

}
