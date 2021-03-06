package com.labprog.egressos.model.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.labprog.egressos.model.Contato;
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
public class ContatoRepositoryTest {
    @Autowired
    ContatoRepo repo;

    @Autowired
    EgressoRepo egressoRepo;

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
        for (int i = 0; i < 3; i++) {
            contatos.add(
                    Contato.builder()
                            .nome("Instagram" + (i + 1))
                            .urlLogo("@instagram" + (i + 1))
                            .build());
        }

        // ação
        List<Contato> retorno = repo.saveAll(contatos);
        repo.deleteAll(retorno);

        // verificação
        Assertions.assertNotNull(retorno);
        Assertions.assertEquals(contatos.size(), retorno.size());
        for (int i = 0; i < contatos.size(); i++) {
            Assertions.assertEquals(contatos.get(i).getNome(), retorno.get(i).getNome());
            Assertions.assertEquals(contatos.get(i).getUrlLogo(), retorno.get(i).getUrlLogo());
        }
    }

    @Test
    public void deveAtualizarContato() {
        // cenário
        Contato contato = Contato.builder()
                .nome("Instagram")
                .urlLogo("@instagram")
                .build();

        // ação
        Contato salvo = repo.save(contato);
        salvo.setNome("Twitter");
        salvo.setUrlLogo("@twitter");
        Contato retorno = repo.save(salvo);

        // verificação
        Assertions.assertNotNull(retorno);
        Assertions.assertEquals(salvo.getId(), retorno.getId());
        Assertions.assertEquals(salvo.getNome(), retorno.getNome());
        Assertions.assertEquals(salvo.getUrlLogo(), retorno.getUrlLogo());
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
        for (int i = 0; i < 3; i++) {
            contatos.add(
                    Contato.builder()
                            .nome("Instagram" + (i + 1))
                            .urlLogo("@instagram" + (i + 1))
                            .build());
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
        for (int i = 0; i < 3; i++) {
            contatos.add(
                    Contato.builder()
                            .nome("Instagram" + (i + 1))
                            .urlLogo("@instagram" + (i + 1))
                            .build());
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
        for (int i = 0; i < contatos.size(); i++) {
            Assertions.assertEquals(contatos.get(i).getNome(), retorno.get(i).getNome());
            Assertions.assertEquals(contatos.get(i).getUrlLogo(), retorno.get(i).getUrlLogo());
        }
    }

    @Test
    public void deveAcharContatosPorEgresso() {
        // cenário
        Egresso egresso = Egresso.builder()
                .nome("tuludan")
                .email("a@a.com")
                .cpf("1234")
                .resumo("deveBuscarContatosEgresso")
                .urlFoto("teste")
                .build();
        List<Contato> contatos = new ArrayList<Contato>();
        for (int i = 0; i < 3; i++) {
            contatos.add(
                    Contato.builder()
                            .nome("Instagram" + (i + 1))
                            .urlLogo("@instagram" + (i + 1))
                            .build());
        }

        // ação
        egresso.setContatos(contatos);
        Egresso salvo = egressoRepo.save(egresso);
        List<Contato> retorno = repo.findByEgressos(salvo);
        egressoRepo.delete(salvo);
        repo.deleteAll(retorno);

        // verificação
        Assertions.assertNotNull(retorno);
        Assertions.assertEquals(contatos.size(), retorno.size());
        for (int i = 0; i < contatos.size(); i++) {
            Assertions.assertEquals(contatos.get(i).getNome(), retorno.get(i).getNome());
            Assertions.assertEquals(contatos.get(i).getUrlLogo(), retorno.get(i).getUrlLogo());
        }
    }

    @Test
    public void deveEncontrarContatosPorUrlLogo() {
        // cenário
        List<Contato> contatos = new ArrayList<Contato>();
        for (int i = 0; i < 3; i++) {
            contatos.add(
                    Contato.builder()
                            .nome("Instagram" + (i + 1))
                            .urlLogo("@instagram" + (i + 1))
                            .build());
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
        for (int i = 0; i < contatos.size(); i++) {
            Assertions.assertEquals(contatos.get(i).getNome(), retorno.get(i).getNome());
            Assertions.assertEquals(contatos.get(i).getUrlLogo(), retorno.get(i).getUrlLogo());
        }
    }

}
