package com.example.AwesomePizza.pizzaiolo;

import com.example.AwesomePizza.ordine.OrdineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/pizzaiolo/")
public class PizzaioloController {

    private final PizzaioloService pizzaioloService;
    private final OrdineService ordineService;

    @Autowired
    public PizzaioloController(PizzaioloService pizzaioloService, OrdineService ordineService) {
        this.pizzaioloService = pizzaioloService;
        this.ordineService = ordineService;
    }

    @PutMapping("/ordine_completato/{ordineId}")
    public ResponseEntity<?> setOrdineCompletato(@PathVariable("ordineId") Long ordineId) {
        pizzaioloService.setOrdineCompletato(ordineId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
