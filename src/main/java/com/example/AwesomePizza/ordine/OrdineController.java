package com.example.AwesomePizza.ordine;

import com.example.AwesomePizza.cliente.Cliente;
import com.example.AwesomePizza.cliente.ClienteService;
import com.example.AwesomePizza.enums.StatoOrdine;
import com.example.AwesomePizza.ingredienti.Ingredienti;
import com.example.AwesomePizza.ingredienti.IngredientiService;
import com.example.AwesomePizza.pizza.Pizza;
import com.example.AwesomePizza.pizza.PizzaConIngredientiDTO;
import com.example.AwesomePizza.pizza.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/api/ordine/")
public class OrdineController {

    private final OrdineService ordineService;
    private final PizzaService pizzaService;
    private final ClienteService clienteService;
    private final IngredientiService ingredientiService;

    @Autowired
    public OrdineController(OrdineService ordineService,
                            PizzaService pizzaService,
                            ClienteService clienteService,
                            IngredientiService ingredientiService) {
        this.ordineService = ordineService;
        this.pizzaService = pizzaService;
        this.clienteService = clienteService;
        this.ingredientiService = ingredientiService;
    }

    @PostMapping
    public ResponseEntity<?> addOrdine(@RequestParam String nome,
                                       @RequestParam String cognome,
                                       @RequestParam String telefono,
                                       @RequestParam String indirizzo,
                                       @RequestParam String email,
                                       @RequestParam String infoAggiuntive,
                                            @RequestParam Long menuId,
                                            @RequestBody List<PizzaConIngredientiDTO> pizzaConIngredientiDTO) {

        if (!ordineService.retrieveAllOrdiniNonCompletati().isEmpty()) {
            return new ResponseEntity<>("Ci sono ancora ordini da completare", HttpStatus.BAD_REQUEST);
        }

        List<Pizza> pizze = new ArrayList<>();

        for (PizzaConIngredientiDTO pizzaDTO : pizzaConIngredientiDTO) {
            Pizza pizzaDalMenu = pizzaService.getPizzaDalMenu(menuId, pizzaDTO.getPizza().getNome());


            List<String> nomiIngredienti = pizzaDTO.getIngredientiAggiuntivi();
            if (nomiIngredienti == null || nomiIngredienti.isEmpty()) {
                return new ResponseEntity<>("Invalid request data", HttpStatus.BAD_REQUEST);
            }

            Double prezzo = nomiIngredienti.stream()
                    .map(ingredientiService::retrieveIngredientiByNome)
                    .filter(Objects::nonNull)
                    .mapToDouble(Ingredienti::getPrezzo)
                    .sum();
            Pizza pizza = new Pizza();
            pizza.setNome(pizzaDalMenu.getNome());
            pizza.setPrezzo(pizzaDalMenu.getPrezzo() + prezzo);
            pizzaService.addIngredientiToPizza(pizza, nomiIngredienti);
            pizze.add(pizza);
        }


        Cliente cliente = clienteService.createCliente(nome, cognome, indirizzo, telefono,email, infoAggiuntive);
        Ordine ordine = new Ordine();
        ordine.setPizze(pizze);
        ordine.setPrezzoTot(ordineService.prezzoTot(pizze));
        ordine.setCliente(cliente);
        ordineService.addOrdine(ordine);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Ordine> viewOrdine(@PathVariable("id") Long ordineid) {
        Ordine ordine = ordineService.retrieveOrdine(ordineid);
        if (ordine != null) {
            return ResponseEntity.ok(ordine);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("getAll")
    public ResponseEntity<Collection<Ordine>> viewAllOrdine() {
        return ResponseEntity.ok(ordineService.retrieveAllOrdiniNonCompletati());
    }

    @PutMapping("{id}")
    public ResponseEntity<Ordine> updateOrdine(@PathVariable("id") Long ordineId,
                                                   @RequestBody Ordine ordine) {
        ordineService.setOrdineCompletato(ordineId, ordine);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Ordine> removeOrdine(@PathVariable("id") Long ordineId) {
        ordineService.deleteOrdine(ordineId);
        return ResponseEntity.ok().build();
    }
}
