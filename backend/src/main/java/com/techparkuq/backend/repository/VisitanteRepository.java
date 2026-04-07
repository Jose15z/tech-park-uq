package com.techparkuq.backend.repository;

import com.techparkuq.backend.model.Visitante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitanteRepository extends JpaRepository<Visitante, Long> {
}