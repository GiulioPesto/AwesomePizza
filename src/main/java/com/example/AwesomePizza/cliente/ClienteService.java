package com.example.AwesomePizza.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.openmbean.InvalidKeyException;
import java.util.Collection;
import java.util.Optional;

@Service
public class ClienteService {

        @Autowired
        ClienteRepository clienteRepository;

        public Cliente createCliente(String nome, String cognome, String indirizzo, String telefono, String email, String infoAggiuntive) {
            Cliente cliente = new Cliente();
            cliente.setNome(nome);
            cliente.setCognome(cognome);
            cliente.setIndirizzo(indirizzo);
            cliente.setTelefono(telefono);
            cliente.setEmail(email);
            cliente.setInfoAggiuntive(infoAggiuntive);
            return clienteRepository.save(cliente);
        }

        public Optional<Cliente> retrieveCliente(Long clienteId) {
            return clienteRepository.findById(clienteId);
        }

        public Collection<Cliente> retrieveAllClienti() {
            return clienteRepository.findAll();
        }



        public void updateCliente(Long clienteId, Cliente clienteAggiornato) {
            Cliente clienteRepo = clienteRepository.findById(clienteId).orElseThrow(InvalidKeyException::new);
            clienteRepo.setNome(clienteAggiornato.getNome());
            clienteRepo.setCognome(clienteAggiornato.getCognome());
            clienteRepo.setTelefono(clienteAggiornato.getTelefono());
            clienteRepo.setEmail(clienteAggiornato.getEmail());
            clienteRepo.setInfoAggiuntive(clienteAggiornato.getInfoAggiuntive());
            clienteRepository.save(clienteRepo);
        }

        public void deleteCliente(Long clienteId) {
            Cliente clienteRepo =  clienteRepository.findById(clienteId).orElseThrow(InvalidKeyException::new);
            clienteRepository.delete(clienteRepo);

        }
    }

