package com.example.AwesomePizza.pizza;

import com.example.AwesomePizza.ingredienti.Ingredienti;
import com.example.AwesomePizza.ingredienti.IngredientiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(value = "/api/pizza/")
public class PizzaController {

    private final PizzaService pizzaService;
    private final IngredientiService ingredientiService;

    @Autowired
    public PizzaController(PizzaService pizzaService, IngredientiService ingredientiService) {
        this.pizzaService = pizzaService;
        this.ingredientiService = ingredientiService;
    }

    @PostMapping
    public ResponseEntity<?> addPizzeConIngredienti(@RequestBody PizzaConIngredientiDTO pizzaConIngredientiDTO) {
        Pizza pizza = pizzaConIngredientiDTO.getPizza();
        List<String> nomiIngredienti = pizzaConIngredientiDTO.getIngredientiAggiuntivi();

        if (pizza == null || nomiIngredienti == null || nomiIngredienti.isEmpty()) {
            return new ResponseEntity<>("Invalid request data", HttpStatus.BAD_REQUEST);
        }
        pizzaService.createPizza(pizza);

        Double prezzo = 0.0;

        for (String nomeIngrediente : nomiIngredienti) {
            Ingredienti ingrediente = ingredientiService.retrieveIngredientiByNome(nomeIngrediente);
            if (ingrediente != null) {
                pizzaService.addIngredientiToPizza(pizza, nomiIngredienti);
                prezzo += ingrediente.getPrezzo();
            }
        }

        pizza.setPrezzo(pizza.getPrezzo() + prezzo);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Pizza> getPizza(@PathVariable("id") Long pizzaId) {
        if (pizzaService.getPizza(pizzaId).isPresent()) {
            return ResponseEntity.ok(pizzaService.getPizza(pizzaId).get());
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("dal_menu/{menuId}/{nome}")
    public ResponseEntity<Pizza> getPizzeDalMenu(@PathVariable("menuId") Long menuId, @PathVariable("nome") String nome) {
        Pizza pizza = pizzaService.getPizzaDalMenu(menuId, nome);
        return new ResponseEntity<>(pizza, HttpStatus.OK);
    }

    @GetMapping("/dal_nome/{nome}")
    public ResponseEntity<Pizza> getPizzaDalNome(@PathVariable("nome") String nome) {
        Pizza pizza = pizzaService.getPizzaByNome(nome);
        return new ResponseEntity<>(pizza, HttpStatus.OK);
    }

    @GetMapping("getAll")
    public ResponseEntity<Collection<Pizza>> getAllPizze() {
        return ResponseEntity.ok(pizzaService.getAll());
    }

    @PutMapping("{id}")
    public ResponseEntity<Pizza> updatePizza(@PathVariable("id") Long pizzaId,
                                             @RequestBody Pizza pizza) {
        pizzaService.updatePizza(pizzaId, pizza);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Pizza> removePizza(@PathVariable("id") Long pizzaId) {
        pizzaService.deletePizza(pizzaId);
        return ResponseEntity.ok().build();
    }
}
