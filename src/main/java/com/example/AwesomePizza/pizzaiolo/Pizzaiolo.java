package com.example.AwesomePizza.pizzaiolo;

import com.example.AwesomePizza.ordine.Ordine;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "pizzaiolo")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pizzaiolo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cognome")
    private String cognome;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "codice_fiscale")
    private String codiceFiscale;
}
