package org.example.practica1_1.repo;

import org.example.practica1_1.Model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

    @Query("SELECT c FROM Cuenta c JOIN FETCH c.cliente")
    List<Cuenta> findAllConCliente();
}
