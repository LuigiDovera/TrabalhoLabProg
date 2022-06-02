package com.labprog.egressos.model.repository;

import com.labprog.egressos.model.CursoEgresso;
import com.labprog.egressos.model.CursoEgressoPK;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoEgressoRepo extends JpaRepository<CursoEgresso, CursoEgressoPK> {
}
