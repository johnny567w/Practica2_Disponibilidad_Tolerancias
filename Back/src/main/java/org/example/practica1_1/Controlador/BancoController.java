package org.example.practica1_1.Controlador;

import org.example.practica1_1.Model.Banco;
import org.example.practica1_1.dto.BancoDTO;
import org.example.practica1_1.mapper.BancoMapper;
import org.example.practica1_1.repo.BancoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bancos")
public class BancoController {

    @Autowired
    private BancoRepository bancoRepository;

    @GetMapping
    public List<BancoDTO> listar() {
        return bancoRepository.findAll().stream()
                .map(BancoMapper::toDTO)
                .collect(Collectors.toList());
    }


    @PostMapping
    public Banco crear(@RequestBody BancoDTO bancoDTO) {
        Banco banco = new Banco();
        banco.setNombre(bancoDTO.getNombre());
        return bancoRepository.save(banco);
    }

    @PutMapping("/{id}")
    public Banco actualizar(@PathVariable Long id, @RequestBody BancoDTO bancoDTO) {
        Banco existente = bancoRepository.findById(id).orElseThrow();
        existente.setNombre(bancoDTO.getNombre());
        return bancoRepository.save(existente);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        bancoRepository.deleteById(id);
    }
}
