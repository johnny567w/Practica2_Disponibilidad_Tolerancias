package org.example.practica1_1.Controlador;

import org.example.practica1_1.Model.Banco;
import org.example.practica1_1.repo.BancoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bancos")
public class BancoController {

    @Autowired
    private BancoRepository bancoRepository;

    @GetMapping
    public List<Banco> listar() {
        return bancoRepository.findAll();
    }

    @PostMapping
    public Banco crear(@RequestBody Banco banco) {
        return bancoRepository.save(banco);
    }

    @PutMapping("/{id}")
    public Banco actualizar(@PathVariable Long id, @RequestBody Banco banco) {
        Banco existente = bancoRepository.findById(id).orElseThrow();
        existente.setNombre(banco.getNombre());
        return bancoRepository.save(existente);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        bancoRepository.deleteById(id);
    }
}
