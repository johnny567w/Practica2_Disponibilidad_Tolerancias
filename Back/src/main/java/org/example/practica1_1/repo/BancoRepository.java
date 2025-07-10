package org.example.practica1_1.repo;

import org.example.practica1_1.Model.Banco;
import org.example.practica1_1.Model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BancoRepository extends JpaRepository<Banco, Long> {
}
