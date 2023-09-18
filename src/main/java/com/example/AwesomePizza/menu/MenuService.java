package com.example.AwesomePizza.menu;


import com.example.AwesomePizza.pizza.Pizza;
import com.example.AwesomePizza.pizza.PizzaRepository;
import com.example.AwesomePizza.pizza.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.openmbean.InvalidKeyException;
import java.util.*;

@Service
public class MenuService {
    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private PizzaRepository pizzaRepository;

    private PizzaService pizzaService;

    public void createMenu(Menu menu) {
        menuRepository.save(menu);
    }
    public void addPizzeAlMenu(List<Pizza> pizze, Menu menu) {
        menu.setPizze(pizze);
        menuRepository.save(menu);
    }
    public Menu retrieveMenuById(Long id) {
        return menuRepository.findById(id).orElse(null);
    }

    public Collection<Menu> retrieveAllMenu() {
        return menuRepository.findAll();
    }

    public void updateMenu(Long menuId, Menu menuAggiornato) {
        Menu menuRepo = menuRepository.findById(menuId).orElseThrow(InvalidKeyException::new);
        menuRepo.setNome(menuAggiornato.getNome());
        menuRepository.save(menuRepo);
    }

    public void deleteMenu(Long menuId) {
        Menu menuRepo =  menuRepository.findById(menuId).orElseThrow(InvalidKeyException::new);
        menuRepository.delete(menuRepo);

    }
}

