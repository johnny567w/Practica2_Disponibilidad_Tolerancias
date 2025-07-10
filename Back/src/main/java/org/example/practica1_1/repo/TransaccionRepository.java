package org.example.practica1_1.repo;

import org.example.practica1_1.Model.Persona;
import org.example.practica1_1.Model.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {
}
