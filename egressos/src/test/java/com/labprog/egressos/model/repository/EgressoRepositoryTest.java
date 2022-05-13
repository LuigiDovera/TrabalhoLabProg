package com.labprog.egressos.model.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.labprog.egressos.model.Egresso;
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
        repo.delete(retorno);

        // verificação
        Assertions.assertNotNull(retorno);
        Assertions.assertEquals(egresso.getNome(), retorno.getNome());
        Assertions.assertEquals(egresso.getEmail(), retorno.getEmail());
        Assertions.assertEquals(egresso.getCpf(), retorno.getCpf());
        Assertions.assertEquals(egresso.getResumo(), retorno.getResumo());
        Assertions.assertEquals(egresso.getUrl_foto(), retorno.getUrl_foto());
    }

    @Test
    public void deveSalvarVariosEgressos() {
        // cenário
        List<Egresso> egressos = new ArrayList<Egresso>();
        for (int i=0; i < 3; i++) {
            egressos.add(
                Egresso.builder()
                        .nome("tuludan" + (i+1))
                        .email("a@a.com" + (i+1))
                        .cpf("1234" + (i+1))
                        .resumo("lorem ipsum lore" + (i+1))
                        .url_foto("teste" + (i+1))
                        .build()
            );
        }
        
        // ação
        List<Egresso> retorno = repo.saveAll(egressos);
        repo.deleteAll(retorno);

        // verificação
        Assertions.assertNotNull(retorno);
        Assertions.assertEquals(egressos.size(), retorno.size());
        for (int i=0; i < egressos.size(); i++) {
            Assertions.assertEquals(egressos.get(i).getNome(), retorno.get(i).getNome());
            Assertions.assertEquals(egressos.get(i).getEmail(), retorno.get(i).getEmail());
            Assertions.assertEquals(egressos.get(i).getCpf(), retorno.get(i).getCpf());
            Assertions.assertEquals(egressos.get(i).getResumo(), retorno.get(i).getResumo());
            Assertions.assertEquals(egressos.get(i).getUrl_foto(), retorno.get(i).getUrl_foto());
        }
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
        Optional<Egresso> temp = repo.findById(id);
        
        //verificação        
        Assertions.assertFalse(temp.isPresent());
    }

    @Test
    public void deveRemoverVariosEgressos() {
        // cenário
        List<Egresso> egressos = new ArrayList<Egresso>();
        for (int i=0; i < 3; i++) {
            egressos.add(
                Egresso.builder()
                        .nome("tuludan" + (i+1))
                        .email("a@a.com" + (i+1))
                        .cpf("1234" + (i+1))
                        .resumo("lorem ipsum lore" + (i+1))
                        .url_foto("teste" + (i+1))
                        .build()
            );
        }
        
        // ação
        List<Egresso> salvos = repo.saveAll(egressos);
        repo.deleteAll(salvos);
        List<Egresso> retorno = repo.findAllById(egressos.stream()
                                                .map(c -> c.getId())
                                                .collect(Collectors.toList()));

        // verificação
        Assertions.assertTrue(retorno.isEmpty());
    }

    @Test
    public void deveObterEgressosPorNome(){
        // cenário
        List<Egresso> egressos = new ArrayList<Egresso>();
        for (int i=0; i < 3; i++) {
            egressos.add(
                Egresso.builder()
                        .nome("tuludan" + (i+1))
                        .email("a@a.com" + (i+1))
                        .cpf("1234" + (i+1))
                        .resumo("lorem ipsum lore" + (i+1))
                        .url_foto("teste" + (i+1))
                        .build()
            );
        }
        
        // ação
        List<Egresso> salvos = repo.saveAll(egressos);
        List<Egresso> retorno = new ArrayList<Egresso>();
        for (Egresso egresso : egressos) {
            retorno.addAll(repo.findByNome(egresso.getNome()));
        }
        repo.deleteAll(salvos);

        // verificação
        Assertions.assertNotNull(retorno);
        Assertions.assertEquals(egressos.size(), retorno.size()); 
        for (int i=0; i < egressos.size(); i++) {
            Assertions.assertEquals(egressos.get(i).getNome(), retorno.get(i).getNome());
            Assertions.assertEquals(egressos.get(i).getEmail(), retorno.get(i).getEmail());
            Assertions.assertEquals(egressos.get(i).getCpf(), retorno.get(i).getCpf());
            Assertions.assertEquals(egressos.get(i).getResumo(), retorno.get(i).getResumo());
            Assertions.assertEquals(egressos.get(i).getUrl_foto(), retorno.get(i).getUrl_foto());
        }
    }

    @Test
    public void deveObterEgressoPorCpf() {
        // cenário
        Egresso egresso = Egresso.builder()
                .nome("tuludan")
                .email("a@a.com")
                .cpf("1234")
                .resumo("lorem ipsum lore")
                .url_foto("teste")
                .build();

        // ação
        Egresso salvo = repo.save(egresso);
        Egresso retorno = repo.findByCpf(egresso.getCpf());
        repo.delete(salvo);

        // verificação
        Assertions.assertNotNull(retorno);
        Assertions.assertEquals(egresso.getNome(), retorno.getNome());
        Assertions.assertEquals(egresso.getEmail(), retorno.getEmail());
        Assertions.assertEquals(egresso.getCpf(), retorno.getCpf());
        Assertions.assertEquals(egresso.getResumo(), retorno.getResumo());
        Assertions.assertEquals(egresso.getUrl_foto(), retorno.getUrl_foto());
    }

}