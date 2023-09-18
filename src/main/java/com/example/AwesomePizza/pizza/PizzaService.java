package com.example.AwesomePizza.pizza;



import com.example.AwesomePizza.ingredienti.Ingredienti;
import com.example.AwesomePizza.ingredienti.IngredientiRepository;
import com.example.AwesomePizza.ingredienti.IngredientiService;
import com.example.AwesomePizza.menu.Menu;
import com.example.AwesomePizza.menu.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.openmbean.InvalidKeyException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {

    private final PizzaRepository pizzaRepository;
    private final IngredientiRepository ingredientiRepository;
    private final IngredientiService ingredientiService;
    private final MenuRepository menuRepository;
    @Autowired
    public PizzaService(PizzaRepository pizzaRepository, IngredientiRepository ingredientiRepository, IngredientiService ingredientiService, MenuRepository menuRepository) {
        this.pizzaRepository = pizzaRepository;
        this.ingredientiRepository = ingredientiRepository;
        this.ingredientiService = ingredientiService;
        this.menuRepository = menuRepository;
    }


    public Pizza createPizza(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    public Pizza createPizzaConIngredienti(Pizza pizza, List<String> nomiIngredienti) {
        List<Ingredienti> ingredienti = new ArrayList<>();
        for (String nomeIngrediente : nomiIngredienti) {
            Ingredienti ingrediente = ingredientiService.retrieveIngredientiByNome(nomeIngrediente);
            if (ingrediente != null) {
                ingredienti.add(ingrediente);
            }
        }
        pizza.setIngredientiAggiuntivi(ingredienti);
        return pizzaRepository.save(pizza);
    }

    public Pizza addIngredientiToPizza(Pizza pizza, List<String> nomiIngredienti) {
        List<Ingredienti> ingredienti = pizza.getIngredientiAggiuntivi();
        for (String nomeIngrediente : nomiIngredienti) {
            Ingredienti ingrediente = ingredientiService.retrieveIngredientiByNome(nomeIngrediente);
            if (ingredienti != null) {
                ingredienti.add(ingrediente);
            }
        }
        pizza.setIngredientiAggiuntivi(ingredienti);

        return pizzaRepository.save(pizza);
    }

    public Optional<Pizza> getPizza(Long pizzaId) {
        return pizzaRepository.findById(pizzaId);
    }

    public Pizza getPizzaByNome(String nome) {
        return pizzaRepository.findByNome(nome);
    }

    public Collection<Pizza> getAll() {
        return pizzaRepository.findAll();
    }

    public Pizza getPizzaDalMenu(Long menuId, String nome) {
        Menu menu = menuRepository.findById(menuId).orElseThrow(InvalidKeyException::new);
        return pizzaRepository.findByMenuAndNome(menu, nome);
    }

    public List<Pizza> getPizzeDalMenu(Long menuId) {
        Menu menu = menuRepository.findById(menuId).orElseThrow(InvalidKeyException::new);
        return menu.getPizze();
    }

    public void updatePizza(Long pizzaId, Pizza pizzaAggiornata) {
        Pizza pizzaRepo = pizzaRepository.findById(pizzaId).orElseThrow(InvalidKeyException::new);
        pizzaRepo.setNome(pizzaAggiornata.getNome());
        pizzaRepo.setIngredientiAggiuntivi(pizzaAggiornata.getIngredientiAggiuntivi());
        pizzaRepo.setPrezzo(pizzaAggiornata.getPrezzo());
        pizzaRepo.setPrezzo(pizzaAggiornata.getPrezzo());
        pizzaRepository.save(pizzaRepo);
    }

    public void deletePizza(Long pizzaId) {
        Pizza pizzaRepo =  pizzaRepository.findById(pizzaId).orElseThrow(InvalidKeyException::new);
        pizzaRepository.delete(pizzaRepo);

    }
}
