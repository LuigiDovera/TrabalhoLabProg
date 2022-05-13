package com.labprog.egressos.model.repository;
import com.labprog.egressos.model.Curso;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CursoRepositoryTest {
    @Autowired
    CursoRepo repo;

    @Test
    public void deveSalvarCurso() {
        // cenário
        Curso curso = Curso.builder()
                .nome("curso teste")
                .nivel("nivel teste")
                .build();

        // ação
        Curso retorno = repo.save(curso);

        // verificação
        Assertions.assertNotNull(retorno);
        Assertions.assertEquals(curso.getNome(), retorno.getNome());
        Assertions.assertEquals(curso.getNivel(), retorno.getNivel());

        // rollback
        repo.delete(retorno);
    }
}
