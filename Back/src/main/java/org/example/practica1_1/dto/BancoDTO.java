package org.example.practica1_1.dto;

public class BancoDTO {
    private Long id;

    private String nombre;

    // Constructor vacío
    public BancoDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Constructor con parámetros
    public BancoDTO(String nombre) {
        this.nombre = nombre;
    }

    // Getter y Setter
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
