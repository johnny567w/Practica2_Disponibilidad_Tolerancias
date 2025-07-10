package org.example.practica1_1.service;

import org.example.practica1_1.Model.Banco;
import org.example.practica1_1.repo.BancoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BancoService {

    @Autowired
    private BancoRepository bancoRepository;

    public List<Banco> listar() {
        return bancoRepository.findAll();
    }

    public Banco obtenerPorId(Long id) {
        return bancoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Banco no encontrado"));
    }

    public Banco crear(Banco banco) {
        return bancoRepository.save(banco);
    }

    public Banco actualizar(Long id, Banco bancoActualizado) {
        Banco banco = obtenerPorId(id);
        banco.setNombre(bancoActualizado.getNombre());
        return bancoRepository.save(banco);
    }

    public void eliminar(Long id) {
        bancoRepository.deleteById(id);
    }
}
