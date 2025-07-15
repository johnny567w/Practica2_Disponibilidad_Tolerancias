package org.example.practica1_1.Controlador;

import org.example.practica1_1.Model.Banco;
import org.example.practica1_1.Model.Cliente;
import org.example.practica1_1.dto.ClienteDTO;
import org.example.practica1_1.mapper.ClienteMapper;
import org.example.practica1_1.repo.BancoRepository;
import org.example.practica1_1.repo.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private BancoRepository bancoRepository;

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listar() {
        List<ClienteDTO> listaDTO = clienteRepository.findAll().stream()
                .map(ClienteMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaDTO);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> crear(@RequestBody ClienteDTO clienteDTO) {
        Banco banco = bancoRepository.findById(clienteDTO.getBancoId())
                .orElseThrow(() -> new RuntimeException("Banco no encontrado"));

        Cliente cliente = ClienteMapper.toEntity(clienteDTO, banco);
        Cliente guardado = clienteRepository.save(cliente);
        return ResponseEntity.ok(ClienteMapper.toDTO(guardado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> actualizar(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
        Cliente existente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Banco banco = bancoRepository.findById(clienteDTO.getBancoId())
                .orElseThrow(() -> new RuntimeException("Banco no encontrado"));

        existente.setNombre(clienteDTO.getNombre());
        existente.setCorreo(clienteDTO.getCorreo());
        existente.setBanco(banco);

        Cliente actualizado = clienteRepository.save(existente);
        return ResponseEntity.ok(ClienteMapper.toDTO(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!clienteRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        clienteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

