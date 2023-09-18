package com.example.AwesomePizza.ingredienti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingredienti")
public class IngredientiController {
    private final IngredientiService ingredientiService;

    @Autowired
    public IngredientiController(IngredientiService ingredientiService) {
        this.ingredientiService = ingredientiService;
    }


    @PostMapping
    public ResponseEntity<Ingredienti> createIngredienti(@RequestBody Ingredienti ingredienti) {
        Ingredienti ingredienteCreato = ingredientiService.createIngredienti(ingredienti);
        return new ResponseEntity<>(ingredienteCreato, HttpStatus.CREATED);
    }


    @GetMapping("/{ingredientiId}")
    public ResponseEntity<Ingredienti> getIngredientiById(@PathVariable Long ingredientiId) {
        Ingredienti ingredienti = ingredientiService.retrieveIngredientiById(ingredientiId);
        if (ingredienti != null) {
            return new ResponseEntity<>(ingredienti, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<Ingredienti>> getAllIngredienti() {
        List<Ingredienti> ingredienti = ingredientiService.retrieveAllIngredienti();
        if (ingredienti != null) {
            return new ResponseEntity<>(ingredienti, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/{ingredientiId}")
    public ResponseEntity<?> updateIngredienti(@PathVariable Long ingredientiId, @RequestBody Ingredienti updatedIngredienti) {
            ingredientiService.updateIngredienti(ingredientiId, updatedIngredienti);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{ingredientiId}")
    public ResponseEntity<Void> deleteIngredienti(@PathVariable Long ingredientiId) {
            ingredientiService.deleteIngredient(ingredientiId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
