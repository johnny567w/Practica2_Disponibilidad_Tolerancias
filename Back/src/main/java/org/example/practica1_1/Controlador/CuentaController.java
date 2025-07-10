package org.example.practica1_1.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

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
