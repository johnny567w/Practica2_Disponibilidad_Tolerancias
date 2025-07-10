package org.example.practica1_1.repo;

import org.example.practica1_1.Model.Cliente;
import org.example.practica1_1.Model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
