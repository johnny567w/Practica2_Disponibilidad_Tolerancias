package org.example.practica1_1.Controlador;

import org.example.practica1_1.Model.Cuenta;
import org.example.practica1_1.Model.Transaccion;
import org.example.practica1_1.dto.TransaccionDTO;
import org.example.practica1_1.mapper.TransaccionMapper;
import org.example.practica1_1.repo.CuentaRepository;
import org.example.practica1_1.repo.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/transacciones")
@CrossOrigin(origins = "*")
public class TransaccionController {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private TransaccionRepository transaccionRepository;

    @GetMapping
    public ResponseEntity<List<TransaccionDTO>> listar() {
        List<Transaccion> transacciones = transaccionRepository.findAll();
        List<TransaccionDTO> dtos = transacciones.stream()
                .map(TransaccionMapper::toDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    // 🟢 Depósito
    @PostMapping("/deposito")
    public ResponseEntity<TransaccionDTO> depositar(@RequestBody TransaccionDTO dto) {
        Transaccion transaccion = TransaccionMapper.toEntity(dto);
        transaccion.setTipo("DEPOSITO");
        transaccion.setFecha(LocalDateTime.now());

        Cuenta cuentaOrigen = cuentaRepository.findById(dto.getCuentaOrigenId())
                .orElseThrow(() -> new RuntimeException("Cuenta origen no encontrada"));

        transaccion.setCuentaOrigen(cuentaOrigen);

        Transaccion guardada = transaccionRepository.save(transaccion);
        return ResponseEntity.ok(TransaccionMapper.toDTO(guardada));
    }

    // 🟢 Retiro
    @PostMapping("/retiro")
    public ResponseEntity<TransaccionDTO> retirar(@RequestBody TransaccionDTO dto) {
        Transaccion transaccion = TransaccionMapper.toEntity(dto);
        transaccion.setTipo("RETIRO");
        transaccion.setFecha(LocalDateTime.now());

        Cuenta cuentaOrigen = cuentaRepository.findById(dto.getCuentaOrigenId())
                .orElseThrow(() -> new RuntimeException("Cuenta origen no encontrada"));

        transaccion.setCuentaOrigen(cuentaOrigen);

        Transaccion guardada = transaccionRepository.save(transaccion);
        return ResponseEntity.ok(TransaccionMapper.toDTO(guardada));
    }

    // 🟢 Transferencia
    @PostMapping("/transferencia")
    public ResponseEntity<TransaccionDTO> transferir(@RequestBody TransaccionDTO dto) {
        Transaccion transaccion = TransaccionMapper.toEntity(dto);
        transaccion.setTipo("TRANSFERENCIA");
        transaccion.setFecha(LocalDateTime.now());

        Cuenta origen = cuentaRepository.findById(dto.getCuentaOrigenId())
                .orElseThrow(() -> new RuntimeException("Cuenta origen no encontrada"));

        Cuenta destino = cuentaRepository.findById(dto.getCuentaDestinoId())
                .orElseThrow(() -> new RuntimeException("Cuenta destino no encontrada"));

        transaccion.setCuentaOrigen(origen);
        transaccion.setCuentaDestino(destino);

        Transaccion guardada = transaccionRepository.save(transaccion);
        return ResponseEntity.ok(TransaccionMapper.toDTO(guardada));
    }
}
