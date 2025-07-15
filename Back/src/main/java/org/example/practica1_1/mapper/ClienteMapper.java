package org.example.practica1_1.mapper;

import org.example.practica1_1.Model.Cliente;
import org.example.practica1_1.Model.Banco;
import org.example.practica1_1.dto.ClienteDTO;

public class ClienteMapper {

    public static ClienteDTO toDTO(Cliente cliente) {
        ClienteDTO dto = new ClienteDTO();
        dto.setId(cliente.getId());
        dto.setNombre(cliente.getNombre());
        dto.setCorreo(cliente.getCorreo());
        if (cliente.getBanco() != null) {
            dto.setBancoId(cliente.getBanco().getId());
            dto.setBancoNombre(cliente.getBanco().getNombre());
        }
        return dto;
    }

    public static Cliente toEntity(ClienteDTO dto, Banco banco) {
        Cliente cliente = new Cliente();
        cliente.setId(dto.getId()); // útil si es para actualizar
        cliente.setNombre(dto.getNombre());
        cliente.setCorreo(dto.getCorreo());
        cliente.setBanco(banco);
        return cliente;
    }
}
