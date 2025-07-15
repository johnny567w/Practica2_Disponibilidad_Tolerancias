package org.example.practica1_1.mapper;


import org.example.practica1_1.Model.Transaccion;
import org.example.practica1_1.dto.TransaccionDTO;

public class TransaccionMapper {

    public static TransaccionDTO toDTO(Transaccion t) {
        TransaccionDTO dto = new TransaccionDTO();
        dto.setId(t.getId());
        dto.setTipo(t.getTipo());
        dto.setMonto(t.getMonto());
        dto.setFecha(t.getFecha());

        if (t.getCuentaOrigen() != null) {
            dto.setCuentaOrigenId(t.getCuentaOrigen().getId());
            dto.setCuentaOrigenNumero(t.getCuentaOrigen().getNumero());
        }

        if (t.getCuentaDestino() != null) {
            dto.setCuentaDestinoId(t.getCuentaDestino().getId());
            dto.setCuentaDestinoNumero(t.getCuentaDestino().getNumero());
        }

        return dto;
    }

    public static Transaccion toEntity(TransaccionDTO dto) {
        Transaccion t = new Transaccion();
        t.setId(dto.getId());
        t.setTipo(dto.getTipo());
        t.setMonto(dto.getMonto());
        t.setFecha(dto.getFecha());
        return t;
    }
}