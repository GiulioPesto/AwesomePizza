package com.example.AwesomePizza.menu;

import com.example.AwesomePizza.ingredienti.Ingredienti;
import com.example.AwesomePizza.ingredienti.IngredientiService;
import com.example.AwesomePizza.pizza.Pizza;
import com.example.AwesomePizza.pizza.PizzaConIngredientiDTO;
import com.example.AwesomePizza.pizza.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/api/menu/")
public class MenuController {

    private final MenuService menuService;
    private final PizzaService pizzaService;
    private final IngredientiService ingredientiService;
    @Autowired
    public MenuController(MenuService menuService, PizzaService pizzaService, IngredientiService ingredientiService) {
        this.menuService = menuService;
        this.pizzaService = pizzaService;
        this.ingredientiService = ingredientiService;
    }


    @PostMapping("/{menuId}/pizze")
    public ResponseEntity<?> addPizzeAlMenu(
            @PathVariable Long menuId,
            @RequestBody List<PizzaConIngredientiDTO> pizzaConIngredientiDTO
    ) {
        Menu menu = menuService.retrieveMenuById(menuId);
        if (menu == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Pizza> pizze = new ArrayList<>();

        for (PizzaConIngredientiDTO pizzaDTO : pizzaConIngredientiDTO) {
            Pizza pizza = new Pizza();
            pizza.setNome(pizzaDTO.getPizza().getNome());
            pizza.setMenu(menu);

            List<String> nomiIngredienti = pizzaDTO.getIngredientiAggiuntivi();
            if (nomiIngredienti == null || nomiIngredienti.isEmpty()) {
                return new ResponseEntity<>("Invalid request data", HttpStatus.BAD_REQUEST);
            }


            Double prezzo = nomiIngredienti.stream()
                    .map(ingredientiService::retrieveIngredientiByNome)
                    .filter(Objects::nonNull)
                    .mapToDouble(Ingredienti::getPrezzo)
                    .sum();

            pizza.setPrezzo(pizza.getPrezzo() + prezzo);
            pizze.add(pizza);
        }

        menuService.addPizzeAlMenu(pizze, menu);

        return new ResponseEntity<>(menu, HttpStatus.OK);
    }

    @GetMapping("/{menuId}/pizze")
    public ResponseEntity<List<Pizza>> getPizzeInMenu(@PathVariable Long menuId) {
        Menu menu = menuService.retrieveMenuById(menuId);
        if (menu != null) {
            List<Pizza> pizze = pizzaService.getPizzeDalMenu(menuId);
            return new ResponseEntity<>(pizze, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{menu_id}")
    public ResponseEntity<Menu> getMenuById(@PathVariable("menu_id") Long menuId) {
        Menu menu = menuService.retrieveMenuById(menuId);
        if (menu != null) {
            return new ResponseEntity<>(menu, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("getAll")
    public ResponseEntity<Collection<Menu>> viewAllOrdine() {
        return ResponseEntity.ok(menuService.retrieveAllMenu());
    }

    @PutMapping("{menu_id}")
    public ResponseEntity<Menu> updateMenu(@PathVariable("menu_id") Long menuId,
                                               @RequestBody Menu menu) {
        menuService.updateMenu(menuId, menu);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Menu> removeMenu(@PathVariable("id") Long menuId) {
        menuService.deleteMenu(menuId);
        return ResponseEntity.ok().build();
    }
}
