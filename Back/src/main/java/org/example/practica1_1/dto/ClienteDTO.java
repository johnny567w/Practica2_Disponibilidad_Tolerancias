package org.example.practica1_1.dto;

public class ClienteDTO {
    private Long id;
    private String nombre;
    private String correo;
    private Long bancoId;
    private String bancoNombre; // opcional para mostrar el nombre del banco directamente

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public Long getBancoId() { return bancoId; }
    public void setBancoId(Long bancoId) { this.bancoId = bancoId; }

    public String getBancoNombre() { return bancoNombre; }
    public void setBancoNombre(String bancoNombre) { this.bancoNombre = bancoNombre; }
}
