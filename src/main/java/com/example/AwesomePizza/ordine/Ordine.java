package com.example.AwesomePizza.ordine;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ordine")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Ordine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
