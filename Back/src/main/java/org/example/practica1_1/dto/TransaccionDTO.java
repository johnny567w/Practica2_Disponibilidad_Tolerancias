package org.example.practica1_1.dto;

import java.time.LocalDateTime;

public class TransaccionDTO {
    private Long id;
    private String tipo;
    private Double monto;
    private LocalDateTime fecha;

    private Long cuentaOrigenId;
    private String cuentaOrigenNumero;

    private Long cuentaDestinoId;
    private String cuentaDestinoNumero;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Long getCuentaOrigenId() {
        return cuentaOrigenId;
    }

    public void setCuentaOrigenId(Long cuentaOrigenId) {
        this.cuentaOrigenId = cuentaOrigenId;
    }

    public String getCuentaOrigenNumero() {
        return cuentaOrigenNumero;
    }

    public void setCuentaOrigenNumero(String cuentaOrigenNumero) {
        this.cuentaOrigenNumero = cuentaOrigenNumero;
    }

    public Long getCuentaDestinoId() {
        return cuentaDestinoId;
    }

    public void setCuentaDestinoId(Long cuentaDestinoId) {
        this.cuentaDestinoId = cuentaDestinoId;
    }

    public String getCuentaDestinoNumero() {
        return cuentaDestinoNumero;
    }

    public void setCuentaDestinoNumero(String cuentaDestinoNumero) {
        this.cuentaDestinoNumero = cuentaDestinoNumero;
    }
}