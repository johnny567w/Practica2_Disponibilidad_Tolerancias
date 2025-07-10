package org.example.practica1_1.Controlador;

import org.example.practica1_1.Model.Cliente;
import org.example.practica1_1.repo.BancoRepository;
import org.example.practica1_1.repo.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private BancoRepository bancoRepository;

    @GetMapping
    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    @PostMapping
    public Cliente crear(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @PutMapping("/{id}")
    public Cliente actualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
        Cliente existente = clienteRepository.findById(id).orElseThrow();
        existente.setNombre(cliente.getNombre());
        existente.setCorreo(cliente.getCorreo());
        return clienteRepository.save(existente);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        clienteRepository.deleteById(id);
    }
}
