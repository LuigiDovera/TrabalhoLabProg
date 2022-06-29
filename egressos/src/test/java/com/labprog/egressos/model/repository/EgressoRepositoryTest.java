package com.labprog.egressos.model.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.labprog.egressos.model.Egresso;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
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
                .urlFoto("teste")
                .senha("senhateste")
                .build();

        // ação
        Egresso retorno = repo.save(egresso);
        repo.delete(retorno);

        // verificação
        Assertions.assertNotNull(retorno);
        Assertions.assertNotNull(retorno.getId());
        Assertions.assertEquals(egresso.getNome(), retorno.getNome());
        Assertions.assertEquals(egresso.getEmail(), retorno.getEmail());
        Assertions.assertEquals(egresso.getCpf(), retorno.getCpf());
        Assertions.assertEquals(egresso.getResumo(), retorno.getResumo());
        Assertions.assertEquals(egresso.getUrlFoto(), retorno.getUrlFoto());
    }

    @Test
    public void deveSalvarVariosEgressos() {
        // cenário
        List<Egresso> egressos = new ArrayList<Egresso>();
        for (int i = 0; i < 3; i++) {
            egressos.add(
                    Egresso.builder()
                            .nome("tuludan" + (i + 1))
                            .email("a@a.com" + (i + 1))
                            .cpf("1234" + (i + 1))
                            .resumo("lorem ipsum lore" + (i + 1))
                            .urlFoto("teste" + (i + 1))
.senha("teste" + (i + 1))
                            .build());
        }

        // ação
        List<Egresso> retorno = repo.saveAll(egressos);
        repo.deleteAll(retorno);

        // verificação
        Assertions.assertNotNull(retorno);
        Assertions.assertEquals(egressos.size(), retorno.size());
        for (int i = 0; i < egressos.size(); i++) {
            Assertions.assertEquals(egressos.get(i).getNome(), retorno.get(i).getNome());
            Assertions.assertEquals(egressos.get(i).getEmail(), retorno.get(i).getEmail());
            Assertions.assertEquals(egressos.get(i).getCpf(), retorno.get(i).getCpf());
            Assertions.assertEquals(egressos.get(i).getResumo(), retorno.get(i).getResumo());
            Assertions.assertEquals(egressos.get(i).getUrlFoto(), retorno.get(i).getUrlFoto());
        }
    }

    @Test
    public void deveAtualizarEgresso() {
        // cenário
        Egresso egresso = Egresso.builder()
                .nome("tuludan")
                .email("a@a.com")
                .cpf("1234")
                .resumo("lorem ipsum lore")
                .urlFoto("teste")
                .senha("senhateste")
                .build();

        // ação
        Egresso salvo = repo.save(egresso);
        salvo.setNome("nadulut");
        salvo.setEmail("moc.a@a");
        salvo.setCpf("4321");
        salvo.setResumo("lore ipsum lorem");
        salvo.setUrlFoto("etset");
        salvo.setSenha("testesenha");
        Egresso retorno = repo.save(salvo);

        // verificação
        Assertions.assertNotNull(retorno);
        Assertions.assertEquals(salvo.getId(), retorno.getId());
        Assertions.assertEquals(salvo.getNome(), retorno.getNome());
        Assertions.assertEquals(salvo.getEmail(), retorno.getEmail());
        Assertions.assertEquals(salvo.getCpf(), retorno.getCpf());
        Assertions.assertEquals(salvo.getResumo(), retorno.getResumo());
        Assertions.assertEquals(salvo.getUrlFoto(), retorno.getUrlFoto());
    }

    @Test
    public void deveRemoverEgresso() {
        // cenário
        Egresso egresso = Egresso.builder()
                .nome("tuludan")
                .email("a@a.com")
                .cpf("1234")
                .resumo("lorem ipsum lore")
                .urlFoto("teste")
                .senha("senhateste")
                .build();

        // ação
        Egresso salvo = repo.save(egresso);
        Long id = salvo.getId();
        repo.deleteById(id);
        Optional<Egresso> temp = repo.findById(id);

        // verificação
        Assertions.assertFalse(temp.isPresent());
    }

    @Test
    public void deveRemoverVariosEgressos() {
        // cenário
        List<Egresso> egressos = new ArrayList<Egresso>();
        for (int i = 0; i < 3; i++) {
            egressos.add(
                    Egresso.builder()
                            .nome("tuludan" + (i + 1))
                            .email("a@a.com" + (i + 1))
                            .cpf("1234" + (i + 1))
                            .resumo("lorem ipsum lore" + (i + 1))
                            .urlFoto("teste" + (i + 1))
.senha("teste" + (i + 1))
                            .build());
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
    public void deveObterEgressosPorNome() {
        // cenário
        List<Egresso> egressos = new ArrayList<Egresso>();
        for (int i = 0; i < 3; i++) {
            egressos.add(
                    Egresso.builder()
                            .nome("tuludan" + (i + 1))
                            .email("a@a.com" + (i + 1))
                            .cpf("1234" + (i + 1))
                            .resumo("lorem ipsum lore" + (i + 1))
                            .urlFoto("teste" + (i + 1))
.senha("teste" + (i + 1))
                            .build());
        }

        // ação
        List<Egresso> salvos = repo.saveAll(egressos);
        List<Egresso> retorno = new ArrayList<Egresso>();
        for (Egresso egresso : egressos) {
            retorno.addAll(repo.findByNome(egresso.getNome()));
        }

        // verificação
        Assertions.assertNotNull(retorno);
        Assertions.assertEquals(egressos.size(), retorno.size());
        for (int i = 0; i < egressos.size(); i++) {
            Assertions.assertEquals(egressos.get(i).getNome(), retorno.get(i).getNome());
            Assertions.assertEquals(egressos.get(i).getEmail(), retorno.get(i).getEmail());
            Assertions.assertEquals(egressos.get(i).getCpf(), retorno.get(i).getCpf());
            Assertions.assertEquals(egressos.get(i).getResumo(), retorno.get(i).getResumo());
            Assertions.assertEquals(egressos.get(i).getUrlFoto(), retorno.get(i).getUrlFoto());
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
                .urlFoto("teste")
                .senha("senhateste")
                .build();

        // ação
        repo.save(egresso);
        Optional<Egresso> retorno = repo.findByCpf(egresso.getCpf());

        // verificação
        Assertions.assertTrue(retorno.isPresent());
        Assertions.assertEquals(egresso.getId(), retorno.get().getId());
        Assertions.assertEquals(egresso.getNome(), retorno.get().getNome());
        Assertions.assertEquals(egresso.getEmail(), retorno.get().getEmail());
        Assertions.assertEquals(egresso.getCpf(), retorno.get().getCpf());
        Assertions.assertEquals(egresso.getResumo(), retorno.get().getResumo());
        Assertions.assertEquals(egresso.getUrlFoto(), retorno.get().getUrlFoto());
    }

    @Test
    public void deveObterEgressoPorEmail() {
        // cenário
        Egresso egresso = Egresso.builder()
                .nome("tuludan")
                .email("a@a.com")
                .cpf("1234")
                .resumo("lorem ipsum lore")
                .urlFoto("teste")
                .senha("senhateste")
                .build();

        // ação
        repo.save(egresso);
        Optional<Egresso> retorno = repo.findByEmail(egresso.getEmail());

        // verificação
        Assertions.assertTrue(retorno.isPresent());
        Assertions.assertEquals(egresso.getNome(), retorno.get().getNome());
        Assertions.assertEquals(egresso.getEmail(), retorno.get().getEmail());
        Assertions.assertEquals(egresso.getCpf(), retorno.get().getCpf());
        Assertions.assertEquals(egresso.getResumo(), retorno.get().getResumo());
        Assertions.assertEquals(egresso.getUrlFoto(), retorno.get().getUrlFoto());
    }

}