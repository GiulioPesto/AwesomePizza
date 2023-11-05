package com.example.AwesomePizza.ordine;

import com.example.AwesomePizza.cliente.Cliente;
import com.example.AwesomePizza.pizza.Pizza;
import com.example.AwesomePizza.pizzaiolo.Pizzaiolo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.*;

@Entity
@Table(name = "ordine")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Ordine {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "data_ordine")
    private String dataOrdine;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente")
    private Cliente cliente;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "stato_ordine")
    private String statoOrdine;

    @ManyToMany
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JoinTable(
            name = "ordini_pizze",
            joinColumns = @JoinColumn(name = "ordine_id"),
            inverseJoinColumns = @JoinColumn(name = "pizza_id")
    )
    private List<Pizza> pizze = new ArrayList<>();

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "prezzo_tot")
    private Double prezzoTot;

    public String getDataOrdineSemplificata(Date dataOrdine) {
        SimpleDateFormat formatData = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatData.format(dataOrdine);
    }
}
