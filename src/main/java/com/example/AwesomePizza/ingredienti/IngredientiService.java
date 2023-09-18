package com.example.AwesomePizza.ingredienti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.openmbean.InvalidKeyException;
import java.util.List;

@Service
public class IngredientiService {
    private final IngredientiRepository ingredientiRepository;

    @Autowired
    public IngredientiService(IngredientiRepository ingredientiRepository) {
        this.ingredientiRepository = ingredientiRepository;
    }

    public Ingredienti createIngredienti(Ingredienti ingredienti) {
        return ingredientiRepository.save(ingredienti);
    }

    public List<Ingredienti> retrieveAllIngredienti() {
        return ingredientiRepository.findAll();
    }

    public Ingredienti retrieveIngredientiById(Long ingredientiId) {
        return ingredientiRepository.findById(ingredientiId).orElseThrow(InvalidKeyException::new);
    }

    public Ingredienti retrieveIngredientiByNome(String nome) {
        return ingredientiRepository.findByNome(nome);
    }

    public void updateIngredienti(Long ingredientiId, Ingredienti ingredienteAggiornato) {
        Ingredienti ingredientiRepo = ingredientiRepository.findById(ingredientiId).orElseThrow(InvalidKeyException::new);
        ingredientiRepo.setNome(ingredienteAggiornato.getNome());
        ingredientiRepo.setPrezzo(ingredienteAggiornato.getPrezzo());
        ingredientiRepository.save(ingredientiRepo);

    }

    public void deleteIngredient(Long ingredientId) {
        Ingredienti ingrediente = ingredientiRepository.findById(ingredientId).orElseThrow(InvalidKeyException::new);
        ingredientiRepository.delete(ingrediente);

    }
}
