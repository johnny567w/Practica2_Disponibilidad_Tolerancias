package org.example.practica1_1.mapper;

import org.example.practica1_1.Model.Banco;
import org.example.practica1_1.dto.BancoDTO;

public class BancoMapper {

    public static BancoDTO toDTO(Banco banco) {
        BancoDTO dto = new BancoDTO();
        dto.setId(banco.getId());
        dto.setNombre(banco.getNombre());
        return dto;
    }

    public static Banco toEntity(BancoDTO dto) {
        Banco banco = new Banco();
        banco.setId(dto.getId());
        banco.setNombre(dto.getNombre());
        return banco;
    }
}
