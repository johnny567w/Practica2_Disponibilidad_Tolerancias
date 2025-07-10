package org.example.practica1_1.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo;
    private Double saldo;

    @ManyToOne
    private Cliente cliente;

    @OneToMany(mappedBy = "cuentaOrigen")
    private List<Transaccion> transaccionesOrigen;

    @OneToMany(mappedBy = "cuentaDestino")
    private List<Transaccion> transaccionesDestino;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Transaccion> getTransaccionesOrigen() {
        return transaccionesOrigen;
    }

    public void setTransaccionesOrigen(List<Transaccion> transaccionesOrigen) {
        this.transaccionesOrigen = transaccionesOrigen;
    }

    public List<Transaccion> getTransaccionesDestino() {
        return transaccionesDestino;
    }

    public void setTransaccionesDestino(List<Transaccion> transaccionesDestino) {
        this.transaccionesDestino = transaccionesDestino;
    }
}
