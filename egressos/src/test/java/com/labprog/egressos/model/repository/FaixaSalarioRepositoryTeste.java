package com.labprog.egressos.model.repository;

import java.util.Optional;

import com.labprog.egressos.model.FaixaSalario;
import com.labprog.egressos.model.repository.FaixaSalarioRepo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FaixaSalarioRepositoryTeste {
    @Autowired
    FaixaSalarioRepo repo;

    @Test
    public void deveSalvarFaixaSalario(){
        // cenário
        FaixaSalario faixaSalario = FaixaSalario.builder()
                .descricao("2000 - 3000")
                .build();

        // ação
        FaixaSalario retorno = repo.save(faixaSalario);

        repo.delete(retorno); //rolback

        // verificação
        Assertions.assertNotNull(retorno);
        Assertions.assertEquals(faixaSalario.getDescricao(), retorno.getDescricao());
    }

    @Test
    public void deveRemoverFaixaSalario() {
        // cenário
        FaixaSalario faixaSalario = FaixaSalario.builder()
                .descricao("2000 - 3000")
                .build();

        // ação
        FaixaSalario salvo = repo.save(faixaSalario);
        Long id = salvo.getId();
        repo.deleteById(id);

        // verificação
        Optional<FaixaSalario> temp = repo.findById(id);
        Assertions.assertFalse(temp.isPresent());
    }

    @Test
    public void deveObterPorDescricaoFaixaSalario(){
        // cenário
        String descricao = "2000 - 3000";
        FaixaSalario faixaSalario = FaixaSalario.builder()
                .descricao(descricao)
                .build();

        // ação
        FaixaSalario retorno = repo.save(faixaSalario);
        FaixaSalario consulta = repo.obterFaixaSalarioPorDescricao(descricao);

        repo.delete(retorno); //rollback

        // verificação
        Assertions.assertNotNull(consulta);
        Assertions.assertEquals(faixaSalario.getDescricao(), faixaSalario.getDescricao());
    }
}
