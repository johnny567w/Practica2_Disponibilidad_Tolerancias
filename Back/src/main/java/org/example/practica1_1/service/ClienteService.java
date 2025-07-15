package org.example.practica1_1.service;

import org.example.practica1_1.Model.Banco;
import org.example.practica1_1.Model.Cliente;
import org.example.practica1_1.repo.BancoRepository;
import org.example.practica1_1.repo.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private BancoRepository bancoRepository;

    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    public Cliente obtenerPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    public Cliente crear(Cliente cliente, Long bancoId) {
        Banco banco = bancoRepository.findById(bancoId)
                .orElseThrow(() -> new RuntimeException("Banco no encontrado"));
        cliente.setBanco(banco);
        return clienteRepository.save(cliente);
    }

    public Cliente actualizar(Long id, Cliente clienteActualizado) {
        Cliente cliente = obtenerPorId(id);
        cliente.setNombre(clienteActualizado.getNombre());
        cliente.setCorreo(clienteActualizado.getCorreo());
        return clienteRepository.save(cliente);
    }

    public void eliminar(Long id) {
        clienteRepository.deleteById(id);
    }
}