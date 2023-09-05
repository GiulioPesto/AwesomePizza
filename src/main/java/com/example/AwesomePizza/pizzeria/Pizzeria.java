package com.example.AwesomePizza.pizzeria;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pizzeria")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pizzeria {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "indirizzo")
    private String indirizzo;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "codice_fiscale")
    private String codiceFiscale;


}