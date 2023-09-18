package com.example.AwesomePizza.pizzaiolo;

import com.example.AwesomePizza.enums.StatoOrdine;
import com.example.AwesomePizza.ordine.Ordine;
import com.example.AwesomePizza.ordine.OrdineRepository;
import com.example.AwesomePizza.ordine.OrdineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.openmbean.InvalidKeyException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Service
public class PizzaioloService {

    private final PizzaioloRepository pizzaioloRepository;
    private final OrdineRepository ordineRepository;
    private final OrdineService ordineService;

    @Autowired
    public PizzaioloService(PizzaioloRepository pizzaioloRepository, OrdineRepository ordineRepository, OrdineService ordineService) {
        this.pizzaioloRepository = pizzaioloRepository;
        this.ordineRepository = ordineRepository;
        this.ordineService = ordineService;
    }


    public void createPizzaiolo(Pizzaiolo pizzaiolo) {
        pizzaioloRepository.save(pizzaiolo);
    }

    public Optional<Pizzaiolo> retrievePizzaiolo(Long pizzaioloId) {
        return pizzaioloRepository.findById(pizzaioloId);
    }

    public Collection<Pizzaiolo> retrieveAllPizzaiolo() {
        return pizzaioloRepository.findAll();
    }

    public void setOrdineCompletato(Long ordineId) {
        Ordine ordineRepo = ordineRepository.findById(ordineId).orElseThrow(InvalidKeyException::new);
        Ordine updatedOrdine = ordineRepository.findById(ordineId).orElseThrow(InvalidKeyException::new);
        ordineRepo.setDataOrdine(updatedOrdine.getDataOrdine());
        ordineRepo.setCliente(updatedOrdine.getCliente());
        ordineRepo.setPizze(updatedOrdine.getPizze());
        ordineRepo.setStatoOrdine(StatoOrdine.COMPLETATO.getStato());
        ordineRepo.setPrezzoTot(updatedOrdine.getPrezzoTot());
        ordineRepository.save(ordineRepo);
    }

    public void updatePizzaiolo(Long pizzaioloId, Pizzaiolo pizzaioloAggiornato) {
        Pizzaiolo pizzaioloRepo = pizzaioloRepository.findById(pizzaioloId).orElseThrow(InvalidKeyException::new);
        pizzaioloRepo.setNome(pizzaioloAggiornato.getNome());
        pizzaioloRepo.setCognome(pizzaioloAggiornato.getCognome());
        pizzaioloRepo.setTelefono(pizzaioloAggiornato.getTelefono());
        pizzaioloRepo.setCodiceFiscale(pizzaioloAggiornato.getCodiceFiscale());
        pizzaioloRepository.save(pizzaioloRepo);
    }

    public void deletePizzaiolo(Long pizzaioloId) {
        Pizzaiolo pizzaioloRepo = pizzaioloRepository.findById(pizzaioloId).orElseThrow(InvalidKeyException::new);
        pizzaioloRepository.delete(pizzaioloRepo);

    }
}
