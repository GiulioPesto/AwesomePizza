package com.example.AwesomePizza.pizza;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PizzaConIngredientiDTO {
    private Pizza pizza;
    private List<String> ingredientiAggiuntivi;
}
