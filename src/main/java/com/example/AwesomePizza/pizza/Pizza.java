package com.example.AwesomePizza.pizza;

import com.example.AwesomePizza.enums.IngredientiEnum;
import jakarta.persistence.*;
import java.util.*;
import lombok.*;

@Entity
@Table(name = "pizza")
@NoArgsConstructor
@Data
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "ingredienti")
    private Set<IngredientiEnum> ingredienti;

    @Column(name = "prezzo")
    private int prezzo;

    public Pizza(Long id, String nome, Set<IngredientiEnum> ingredienti, int prezzo) {
        this.id = id;
        this.nome = nome;
        this.ingredienti = new HashSet<>(ingredienti);
        this.prezzo = prezzo;
    }

}
