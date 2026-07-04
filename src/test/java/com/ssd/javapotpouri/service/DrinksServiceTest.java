package com.ssd.javapotpouri.service;

import com.ssd.javapotpouri.entity.Drinks;
import com.ssd.javapotpouri.repository.DrinksRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DrinksServiceTest {

    @Mock
    private DrinksRepository drinksRepository;

    @InjectMocks
    private DrinksService drinksService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllDrinks() {
        // Arrange
        Drinks mockDrinks1 = new Drinks(1,"Coco Cola", 0.99F);
        Drinks mockDrinks2 = new Drinks(2,"Corona", 0.98F);
        when(drinksRepository.findAll()).thenReturn(Arrays.asList(mockDrinks1,mockDrinks2));

        // Act
        List<Drinks> drinks = drinksService.getAllDrinks();

        // Assert
        assertEquals(2, drinks.size());
        assertEquals("Corona",drinks.get(1).getName());
    }

    @Test
    void getDrinksById() {
        // Arrange
        Drinks mockDrinks = new Drinks(1,"Coco Cola", 0.99F);
        when(drinksRepository.findById("1")).thenReturn(Optional.of(mockDrinks));

        // Act
        Optional<Drinks> drinks = drinksService.getDrinksById("1");

        // Assert
        assertTrue(drinks.isPresent());
        assertEquals("Coco Cola", drinks.get().getName());
    }

    @Test
    void saveDrinks() {
        // Arrange
        Drinks mockDrinks = new Drinks(1,"Crush", 0.99F);
        when(drinksRepository.save(mockDrinks)).thenReturn(mockDrinks);

        // Act
        Drinks savedDrinks = drinksService.saveDrinks(mockDrinks);

        // Assert
        assertNotNull(savedDrinks);
        assertEquals("Crush", savedDrinks.getName());
        verify(drinksRepository,times(1)).save(mockDrinks);
    }

    @Test
    void deleteDrinks() {
        //Arrange
        doNothing().when(drinksRepository).deleteById("1");

        //Act
        drinksService.deleteDrinks("1");

        //Assert
        verify(drinksRepository,times(1)).deleteById("1");

    }
}