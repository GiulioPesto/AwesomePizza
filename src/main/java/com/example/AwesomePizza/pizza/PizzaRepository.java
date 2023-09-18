package com.example.AwesomePizza.pizza;

import com.example.AwesomePizza.menu.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {
    Pizza findByMenuAndNome(Menu menu, String nome);
    Pizza findByNome(String nome);
}
