package org.example.practica1_1.Controlador;

import org.example.practica1_1.Model.Cuenta;
import org.example.practica1_1.Model.Transaccion;
import org.example.practica1_1.repo.CuentaRepository;
import org.example.practica1_1.repo.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private TransaccionRepository transaccionRepository;

    public void depositar(Long id, double monto) {
        Cuenta cuenta = cuentaRepository.findById(id).orElseThrow();
        cuenta.setSaldo(cuenta.getSaldo() + monto);
        cuentaRepository.save(cuenta);

        registrarTransaccion("DEPOSITO", monto, null, cuenta);
    }

    public void retirar(Long id, double monto) {
        Cuenta cuenta = cuentaRepository.findById(id).orElseThrow();
        if (cuenta.getSaldo() < monto) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }
        cuenta.setSaldo(cuenta.getSaldo() - monto);
        cuentaRepository.save(cuenta);

        registrarTransaccion("RETIRO", monto, cuenta, null);
    }

    public void transferir(Long origenId, Long destinoId, double monto) {
        Cuenta origen = cuentaRepository.findById(origenId).orElseThrow();
        Cuenta destino = cuentaRepository.findById(destinoId).orElseThrow();

        if (origen.getSaldo() < monto) {
            throw new IllegalArgumentException("Saldo insuficiente para transferir");
        }

        origen.setSaldo(origen.getSaldo() - monto);
        destino.setSaldo(destino.getSaldo() + monto);

        cuentaRepository.saveAll(List.of(origen, destino));

        registrarTransaccion("TRANSFERENCIA", monto, origen, destino);
    }

    private void registrarTransaccion(String tipo, double monto, Cuenta origen, Cuenta destino) {
        Transaccion tx = new Transaccion();
        tx.setTipo(tipo);
        tx.setMonto(monto);
        tx.setFecha(LocalDateTime.now());
        tx.setCuentaOrigen(origen);
        tx.setCuentaDestino(destino);
        transaccionRepository.save(tx);
    }
}
