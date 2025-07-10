package org.example.practica1_1.Controlador;

import org.example.practica1_1.Model.Transaccion;
import org.example.practica1_1.repo.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transacciones")
public class TransaccionController {

    @Autowired
    private TransaccionRepository transaccionRepository;

    @GetMapping
    public List<Transaccion> listar() {
        return transaccionRepository.findAll();
    }
}
