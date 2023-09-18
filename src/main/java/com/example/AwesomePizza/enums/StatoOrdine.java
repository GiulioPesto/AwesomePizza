package com.example.AwesomePizza.enums;


public enum StatoOrdine {

    IN_FASE_DI_ELABORAZIONE("ORDINE ACCETTATO, Il tuo ordine è in elaborazione"),
    COMPLETATO("COMPLETATO");

    private String stato;

    StatoOrdine(String stato) {
        this.stato = stato;
    }

    public String getStato() {
        return stato;
    }
}
