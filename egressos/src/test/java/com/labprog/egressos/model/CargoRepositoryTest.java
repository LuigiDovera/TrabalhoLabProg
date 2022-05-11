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

        repo.delete(retorno); //rollback

        // verificação
        Assertions.assertNotNull(retorno);
        Assertions.assertEquals(cargo.getNome(), retorno.getNome());
        Assertions.assertEquals(cargo.getDescricao(), retorno.getDescricao());
        
    }

    @Test
    public void deveRemoverEgresso() {
        // cenário
        Cargo cargo = Cargo.builder()
                .nome("Gerente teste")
                .descricao("tuludan")
                .build();

        // ação
        Cargo salvo = repo.save(cargo);
        Long id = salvo.getId();
        repo.deleteById(id);

        // verificação
        Optional<Cargo> temp = repo.findById(id);
        Assertions.assertFalse(temp.isPresent());
    }

    @Test
    public void deveObterPorNomeCargo(){
        // cenário
        String nome = "Gerente teste";
        Cargo cargo = Cargo.builder()
                .nome(nome)
                .descricao("tuludan")
                .build();

        // ação
        Cargo retorno = repo.save(cargo);
        Cargo consulta = repo.obterCargoPorNome(nome)

        repo.delete(retorno); //rollback

        // verificação
        Assertions.assertNotNull(consulta);
        Assertions.assertEquals(cargo.getNome(), consulta.getNome());
        Assertions.assertEquals(cargo.getDescricao(), consulta.getDescricao());
    }
}
