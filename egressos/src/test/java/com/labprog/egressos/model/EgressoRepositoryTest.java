package com.labprog.egressos.model;

import com.labprog.egressos.model.repository.EgressoRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EgressoRepositoryTest {
    @Autowired
    EgressoRepo repo;

    @Test
    public void deveSalvarEgresso() {
        // cenário
        Egresso novo = Egresso.builder()
                .nome("tuludan")
                .email("a@a.com")
                .cpf("1234")
                .resumo("lorem ipsum lore")
                .url_foto("teste")
                .build();

        // ação
        Egresso retorno = repo.save(novo);

        // verificação
        Assertions.assertNotNull(retorno);
        Assertions.assertEquals(novo.getNome(), retorno.getNome());
        Assertions.assertEquals(novo.getEmail(), retorno.getEmail());
        Assertions.assertEquals(novo.getCpf(), retorno.getCpf());
        Assertions.assertEquals(novo.getResumo(), retorno.getResumo());
        Assertions.assertEquals(novo.getUrl_foto(), retorno.getUrl_foto());

        // rollback
        repo.delete(retorno);
    }

}