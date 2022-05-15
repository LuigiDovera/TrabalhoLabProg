package com.labprog.egressos.model.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.labprog.egressos.model.Contato;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ContatoRepositoryTest {
    @Autowired
    ContatoRepo repo;

    @Test
    public void deveSalvarContato() {
        // cenário
        Contato contato = Contato.builder()
                .nome("Instagram")
                .urlLogo("@instagram")
                .build();
        
        // ação
        Contato retorno = repo.save(contato);
        repo.delete(retorno);

        // verificação
        Assertions.assertNotNull(retorno);
        Assertions.assertEquals(contato.getNome(), retorno.getNome());
        Assertions.assertEquals(contato.getUrlLogo(), retorno.getUrlLogo());
    }

    @Test
    public void deveSalvarVariosContatos() {
        // cenário
        List<Contato> contatos = new ArrayList<Contato>();
        for (int i=0; i < 3; i++) {
            contatos.add(
                Contato.builder()
                    .nome("Instagram" + (i+1))
                    .urlLogo("@instagram" + (i+1))
                    .build()
            );
        }
        
        // ação
        List<Contato> retorno = repo.saveAll(contatos);
        repo.deleteAll(retorno);

        // verificação
        Assertions.assertNotNull(retorno);
        Assertions.assertEquals(contatos.size(), retorno.size());
        for (int i=0; i < contatos.size(); i++) {
            Assertions.assertEquals(contatos.get(i).getNome(), retorno.get(i).getNome());
            Assertions.assertEquals(contatos.get(i).getUrlLogo(), retorno.get(i).getUrlLogo());
        }
    }

    @Test
    public void deveRemoverContato() {
        // cenário
        Contato contato = Contato.builder()
                .nome("Instagram")
                .urlLogo("@instagram")
                .build();
        
        // ação
        Contato salvo = repo.save(contato);
        repo.delete(salvo);
        Optional<Contato> retorno = repo.findById(salvo.getId());

        // verificação
        Assertions.assertFalse(retorno.isPresent());
    }

    @Test
    public void deveRemoverVariosContatos() {
        // cenário
        List<Contato> contatos = new ArrayList<Contato>();
        for (int i=0; i < 3; i++) {
            contatos.add(
                Contato.builder()
                    .nome("Instagram" + (i+1))
                    .urlLogo("@instagram" + (i+1))
                    .build()
            );
        }
        
        // ação
        List<Contato> salvos = repo.saveAll(contatos);
        repo.deleteAll(salvos);
        List<Contato> retorno = repo.findAllById(contatos.stream()
                                                .map(c -> c.getId())
                                                .collect(Collectors.toList()));

        // verificação
        Assertions.assertTrue(retorno.isEmpty());
    }

    @Test
    public void deveEncontrarContatosPorNome() {
        // cenário
        List<Contato> contatos = new ArrayList<Contato>();
        for (int i=0; i < 3; i++) {
            contatos.add(
                Contato.builder()
                    .nome("Instagram" + (i+1))
                    .urlLogo("@instagram" + (i+1))
                    .build()
            );
        }
        
        // ação
        List<Contato> salvos = repo.saveAll(contatos);
        List<Contato> retorno = new ArrayList<Contato>();
        for (Contato contato : contatos) {
            retorno.addAll(repo.findByNome(contato.getNome()));
        }
        repo.deleteAll(salvos);

        // verificação
        Assertions.assertNotNull(retorno);
        Assertions.assertEquals(contatos.size(), retorno.size()); 
        for (int i=0; i < contatos.size(); i++) {
            Assertions.assertEquals(contatos.get(i).getNome(), retorno.get(i).getNome());
            Assertions.assertEquals(contatos.get(i).getUrlLogo(), retorno.get(i).getUrlLogo());
        }
    }

    @Test
    public void deveEncontrarContatosPorUrlLogo() {
        // cenário
        List<Contato> contatos = new ArrayList<Contato>();
        for (int i=0; i < 3; i++) {
            contatos.add(
                Contato.builder()
                    .nome("Instagram" + (i+1))
                    .urlLogo("@instagram" + (i+1))
                    .build()
            );
        }
        
        // ação
        List<Contato> salvos = repo.saveAll(contatos);
        List<Contato> retorno = new ArrayList<Contato>();
        for (Contato contato : contatos) {
            retorno.addAll(repo.findByUrlLogo(contato.getUrlLogo()));
        }
        repo.deleteAll(salvos);

        // verificação
        Assertions.assertNotNull(retorno);
        Assertions.assertEquals(contatos.size(), retorno.size()); 
        for (int i=0; i < contatos.size(); i++) {
            Assertions.assertEquals(contatos.get(i).getNome(), retorno.get(i).getNome());
            Assertions.assertEquals(contatos.get(i).getUrlLogo(), retorno.get(i).getUrlLogo());
        }
    }
    
}
