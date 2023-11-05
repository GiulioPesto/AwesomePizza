package com.example.AwesomePizza.ordine;

import com.example.AwesomePizza.cliente.Cliente;
import com.example.AwesomePizza.menu.Menu;
import com.example.AwesomePizza.pizza.PizzaConIngredientiDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrdineDTO {
    private Ordine ordine;
    private Long menuId;
    private List<PizzaConIngredientiDTO> pizze;
}
