package org.example.practica1_1.mapper;

import org.example.practica1_1.Model.Cliente;
import org.example.practica1_1.Model.Cuenta;
import org.example.practica1_1.dto.CuentaDTO;
public class CuentaMapper {

    public static CuentaDTO toDTO(Cuenta cuenta) {
        CuentaDTO dto = new CuentaDTO();
        dto.setId(cuenta.getId());
        dto.setTipo(cuenta.getTipo());
        dto.setSaldo(cuenta.getSaldo());
        dto.setClienteId(cuenta.getCliente().getId());

        // Agrega nombre del cliente si está disponible
        if (cuenta.getCliente() != null) {
            dto.setClienteNombre(cuenta.getCliente().getNombre());
        }

        return dto;
    }

    public static Cuenta toEntity(CuentaDTO dto, Cliente cliente) {
        Cuenta cuenta = new Cuenta();
        cuenta.setTipo(dto.getTipo());
        cuenta.setSaldo(dto.getSaldo());
        cuenta.setCliente(cliente);
        return cuenta;
    }
}
