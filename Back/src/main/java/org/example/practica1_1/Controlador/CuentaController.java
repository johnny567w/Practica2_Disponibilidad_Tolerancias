package org.example.practica1_1.Controlador;

import org.example.practica1_1.Model.Cliente;
import org.example.practica1_1.Model.Cuenta;
import org.example.practica1_1.dto.CuentaDTO;
import org.example.practica1_1.mapper.CuentaMapper;
import org.example.practica1_1.repo.ClienteRepository;

import org.example.practica1_1.repo.CuentaRepository;
import org.example.practica1_1.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public ResponseEntity<List<CuentaDTO>> listarCuentas() {
        List<CuentaDTO> cuentasDTO = cuentaRepository.findAll().stream()
                .map(CuentaMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(cuentasDTO);
    }

    @PostMapping
    public ResponseEntity<CuentaDTO> crear(@RequestBody CuentaDTO cuentaDTO) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(cuentaDTO.getClienteId());

        if (!clienteOpt.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        Cuenta cuenta = CuentaMapper.toEntity(cuentaDTO, clienteOpt.get());
        Cuenta nueva = cuentaRepository.save(cuenta);
        return ResponseEntity.ok(CuentaMapper.toDTO(nueva));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        cuentaService.eliminarCuenta(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/deposito")
    public ResponseEntity<String> depositar(@PathVariable Long id, @RequestParam double monto) {
        cuentaService.depositar(id, monto);
        return ResponseEntity.ok("Depósito exitoso");
    }

    @PostMapping("/{id}/retiro")
    public ResponseEntity<String> retirar(@PathVariable Long id, @RequestParam double monto) {
        cuentaService.retirar(id, monto);
        return ResponseEntity.ok("Retiro exitoso");
    }

    @PostMapping("/transferir")
    public ResponseEntity<String> transferir(@RequestParam Long origenId,
                                             @RequestParam Long destinoId,
                                             @RequestParam double monto) {
        cuentaService.transferir(origenId, destinoId, monto);
        return ResponseEntity.ok("Transferencia exitosa");
    }
}