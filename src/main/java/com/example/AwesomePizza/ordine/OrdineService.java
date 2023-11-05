package com.example.AwesomePizza.ordine;

import com.example.AwesomePizza.enums.StatoOrdine;
import com.example.AwesomePizza.pizza.Pizza;
import com.example.AwesomePizza.pizza.PizzaRepository;
import com.example.AwesomePizza.pizza.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.management.openmbean.InvalidKeyException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrdineService {
    @Autowired
    private OrdineRepository ordineRepository;

    @Autowired
    private PizzaRepository pizzaRepository;

    private PizzaService pizzaService;

    public void addOrdine(Ordine ordine) {
        ordine.setStatoOrdine(StatoOrdine.IN_FASE_DI_ELABORAZIONE.getStato());
        ordine.setDataOrdine(ordine.getDataOrdineSemplificata(new Date()));
        ordineRepository.save(ordine);
    }

    public Double prezzoTot(List<Pizza> pizze) {
        Double prezzi = 0d;
        for (Pizza p : pizze) {
            Double prezzo = p.getPrezzo();
            prezzi += prezzo;
        }
        return prezzi;
    }

    public Ordine retrieveOrdine(Long ordineId) {
        return ordineRepository.findById(ordineId).orElse(null);
    }

    public List<Ordine> retrieveAllOrdine() {
        return ordineRepository.findAll();
        }

        public List<Ordine> retrieveAllOrdiniNonCompletati() {
            return ordineRepository.findByStatoOrdine(StatoOrdine.IN_FASE_DI_ELABORAZIONE.getStato());
        }

    public void updateOrdine(Long ordineId, Ordine ordineAggiornato) {
        Ordine ordineRepo = ordineRepository.findById(ordineId).orElseThrow(InvalidKeyException::new);
        ordineRepo.setId(ordineAggiornato.getId());
        ordineRepo.setDataOrdine(ordineAggiornato.getDataOrdine());
        ordineRepo.setCliente(ordineAggiornato.getCliente());
        ordineRepo.setPizze(ordineAggiornato.getPizze());
        ordineRepo.setStatoOrdine(ordineAggiornato.getStatoOrdine());
        ordineRepo.setPrezzoTot(ordineAggiornato.getPrezzoTot());
        ordineRepository.save(ordineRepo);
    }

    public void setOrdineCompletato(Long ordineId, Ordine ordineAggiornato) {
        Ordine ordineRepo = ordineRepository.findById(ordineId).orElseThrow(InvalidKeyException::new);
        ordineRepo.setId(ordineAggiornato.getId());
        ordineRepo.setDataOrdine(ordineAggiornato.getDataOrdine());
        ordineRepo.setCliente(ordineAggiornato.getCliente());
        ordineRepo.setPizze(ordineAggiornato.getPizze());
        ordineRepo.setStatoOrdine(StatoOrdine.COMPLETATO.getStato());
        ordineRepo.setPrezzoTot(ordineAggiornato.getPrezzoTot());
        ordineRepository.save(ordineRepo);
    }

    public void deleteOrdine(Long ordineId) {
        Ordine ordineRepo =  ordineRepository.findById(ordineId).orElseThrow(InvalidKeyException::new);
        ordineRepository.delete(ordineRepo);

    }
}
