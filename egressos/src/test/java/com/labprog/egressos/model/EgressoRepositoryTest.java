package com.labprog.egressos.model;

import java.util.Optional;

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
        Egresso egresso = Egresso.builder()
                .nome("tuludan")
                .email("a@a.com")
                .cpf("1234")
                .resumo("lorem ipsum lore")
                .url_foto("teste")
                .build();

        // ação
        Egresso retorno = repo.save(egresso);

        // verificação
        Assertions.assertNotNull(retorno);
        Assertions.assertEquals(egresso.getNome(), retorno.getNome());
        Assertions.assertEquals(egresso.getEmail(), retorno.getEmail());
        Assertions.assertEquals(egresso.getCpf(), retorno.getCpf());
        Assertions.assertEquals(egresso.getResumo(), retorno.getResumo());
        Assertions.assertEquals(egresso.getUrl_foto(), retorno.getUrl_foto());

        // rollback
        repo.delete(retorno);
    }

    @Test
    public void deveRemoverEgresso() {
        //cenário
        Egresso egresso = Egresso.builder()
                .nome("tuludan")
                .email("a@a.com")
                .cpf("1234")
                .resumo("lorem ipsum lore")
                .url_foto("teste")
                .build();
        
        //ação
        Egresso salvo = repo.save(egresso);  //salva
        Long id = salvo.getId();
        repo.deleteById(id);
        
        
        //verificação
        Optional<Egresso> temp = repo.findById(id);        
        Assertions.assertFalse(temp.isPresent());
    }

    @Test
    public void deveObterPorNomeEgresso(){
        //cenário
        String nome = "tuludan";
        Egresso egresso = Egresso.builder()
                .nome(nome)
                .email("a@a.com")
                .cpf("1234")
                .resumo("lorem ipsum lore")
                .url_foto("teste")
                .build();
        
        // ação
        Egresso retorno = repo.save(egresso); //salvar
        Egresso consulta = repo.obterEgressoPorNome(nome); //consulta

        //verificação
        Assertions.assertNotNull(consulta);
        Assertions.assertEquals(egresso.getNome(), consulta.getNome());
        Assertions.assertEquals(egresso.getEmail(), consulta.getEmail());
        Assertions.assertEquals(egresso.getCpf(), consulta.getCpf());
        Assertions.assertEquals(egresso.getResumo(), consulta.getResumo());
        Assertions.assertEquals(egresso.getUrl_foto(), consulta.getUrl_foto()); 
        
        //rollback
        repo.delete(retorno);
        
    }

    

}