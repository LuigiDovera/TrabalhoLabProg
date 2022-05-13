package com.labprog.egressos.model;

import java.util.Optional;

import com.labprog.egressos.model.repository.FaixaSalarioRepo;
import com.labprog.egressos.model.repository.EgressoRepo;
import com.labprog.egressos.model.repository.CargoRepo;
import com.labprog.egressos.model.repository.ProfEgressoRepo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ProfEgressoRepositoryTest {
    @Autowired
    ProfEgresso repo;

    @Test
    public void deveSalvarProfEgresso() {
        
    }
}
