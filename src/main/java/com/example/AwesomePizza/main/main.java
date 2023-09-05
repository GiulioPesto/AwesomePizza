package com.example.AwesomePizza.main;

import com.example.AwesomePizza.enums.IngredientiEnum;
import com.example.AwesomePizza.pizza.Pizza;

import java.util.Set;

public class main {
    public static void main(String[] args){

        Pizza pi = new Pizza(1L, "margherita", Set.of(IngredientiEnum.POMODORO, IngredientiEnum.MOZZARELLA_FIORDILATTE), 5);
        System.out.println(pi);
    }
}
