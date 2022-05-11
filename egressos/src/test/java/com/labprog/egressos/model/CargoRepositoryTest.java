package com.labprog.egressos.model;

import java.util.Optional;

import com.labprog.egressos.model.repository.CargoRepo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CargoRepositoryTest {
    @Autowired
    CargoRepo repo;

    @Test
    public void deveSalvarCargo(){
        // cenário
        Cargo cargo = Cargo.builder()
                .nome("Gerente teste")
                .descricao("tuludan")
                .build();

        // ação
        Cargo retorno = repo.save(cargo);

        // verificação
        Assertions.assertNotNull(retorno);
        Assertions.assertEquals(cargo.getNome(), retorno.getNome());
        Assertions.assertEquals(cargo.getDescricao(), retorno.getDescricao());

        // rollback
        repo.delete(retorno);
    }
}
