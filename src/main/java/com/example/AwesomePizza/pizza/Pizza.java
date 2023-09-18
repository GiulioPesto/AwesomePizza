package com.example.AwesomePizza.pizza;


import com.example.AwesomePizza.ingredienti.Ingredienti;
import com.example.AwesomePizza.menu.Menu;
import com.example.AwesomePizza.ordine.Ordine;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import java.util.*;
import lombok.*;

@Entity
@Table(name = "pizza")
@NoArgsConstructor
@Data
public class Pizza {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "prezzo")
    private Double prezzo = 3.50;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ordine_id")
    private Ordine ordine;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @Nullable
    @ManyToMany
    @JoinTable(
            name = "ingredienti_pizza",
            joinColumns = @JoinColumn(name = "pizza_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Ingredienti> ingredientiAggiuntivi = new ArrayList<>();

    public Pizza( String nome, List<Ingredienti> ingredientiAggiuntivi, Double prezzo) {
        this.nome = nome;
        this.ingredientiAggiuntivi = ingredientiAggiuntivi;
        this.prezzo = prezzo;
    }
}
