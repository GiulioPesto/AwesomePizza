package com.example.AwesomePizza.ingredienti;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientiRepository extends JpaRepository<Ingredienti, Long> {
    Ingredienti findByNome(String nome);
}
