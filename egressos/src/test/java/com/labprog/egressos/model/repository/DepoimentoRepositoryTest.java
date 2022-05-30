package com.labprog.egressos.model.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import com.labprog.egressos.model.Cargo;
import com.labprog.egressos.model.Depoimento;
import com.labprog.egressos.model.Egresso;
import com.labprog.egressos.model.repository.CargoRepo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DepoimentoRepositoryTest {
    @Autowired
    DepoimentoRepo depoimentoRepo;

    @Autowired
    EgressoRepo egressoRepo;

    @Test
    public void deveSalvarDepoimento() {
        // cenário
        Date data = new Date(1);

        Egresso egresso = Egresso.builder()
                .nome("tuludan")
                .email("a@a.com")
                .cpf("1234")
                .resumo("lorem ipsum lore")
                .urlFoto("teste")
                .build();
        Egresso retornoEgresso = egressoRepo.save(egresso);

        Depoimento depoimento = Depoimento.builder()
                .egresso(retornoEgresso)
                .texto("Depoimento teste")
                .data(data)
                .build();

        // ação
        Depoimento retornoDepoimento = depoimentoRepo.save(depoimento);

        // verificação
        Assertions.assertNotNull(retornoDepoimento);
        Assertions.assertEquals(depoimento.getTexto(), retornoDepoimento.getTexto());
        Assertions.assertEquals(depoimento.getData(), retornoDepoimento.getData());

        // rollback
        depoimentoRepo.delete(retornoDepoimento);
        egressoRepo.delete(retornoEgresso);
    }

    @Test
    public void deveAtualizarDepoimento() {
        // cenário
        Date data = new Date(1);

        Egresso egresso = Egresso.builder()
                .nome("tuludan")
                .email("a@a.com")
                .cpf("1234")
                .resumo("lorem ipsum lore")
                .urlFoto("teste")
                .build();
        Egresso retornoEgresso = egressoRepo.save(egresso);

        Depoimento depoimento = Depoimento.builder()
                .egresso(retornoEgresso)
                .texto("Depoimento teste")
                .data(data)
                .build();

        // ação
        Depoimento retornoDepoimento = depoimentoRepo.save(depoimento);
        retornoDepoimento.setTexto("teste depoimento");
        retornoDepoimento.setData(new Date(2));
        Depoimento depoimentoAtualizado = depoimentoRepo.save(retornoDepoimento);

        // verificação
        Assertions.assertNotNull(retornoDepoimento);
        Assertions.assertNotNull(retornoDepoimento.getId());
        Assertions.assertEquals(depoimentoAtualizado.getTexto(), retornoDepoimento.getTexto());
        Assertions.assertEquals(depoimentoAtualizado.getData(), retornoDepoimento.getData());
        Assertions.assertEquals(depoimentoAtualizado.getEgresso().getNome(), retornoDepoimento.getEgresso().getNome());

        // rollback
        depoimentoRepo.delete(depoimentoAtualizado);
        egressoRepo.delete(retornoEgresso);
    }

    @Test
    public void deveRemoverCargo() {
        // cenário
        Date data = new Date(1);

        Egresso egresso = Egresso.builder()
                .nome("tuludan")
                .email("a@a.com")
                .cpf("1234")
                .resumo("lorem ipsum lore")
                .urlFoto("teste")
                .build();
        Egresso retornoEgresso = egressoRepo.save(egresso);

        Depoimento depoimento = Depoimento.builder()
                .egresso(retornoEgresso)
                .texto("Depoimento teste")
                .data(data)
                .build();

        // ação
        Depoimento retornoDepoimento = depoimentoRepo.save(depoimento);
        depoimentoRepo.delete(retornoDepoimento);
        Optional<Depoimento> retorno = depoimentoRepo.findById(retornoDepoimento.getId());

        // verificação
        Assertions.assertNotNull(retornoDepoimento);
        Assertions.assertEquals(depoimento.getTexto(), retornoDepoimento.getTexto());
        Assertions.assertEquals(depoimento.getData(), retornoDepoimento.getData());

        // rollback
        depoimentoRepo.delete(retornoDepoimento);
        egressoRepo.delete(retornoEgresso);
    }

    @Test
    public void deveListarDepoimentosOrdenadosPeloMaisRecente() {
        // cenário
        Egresso egresso = Egresso.builder()
                .nome("tuludan")
                .email("a@a.com")
                .cpf("1234")
                .resumo("lorem ipsum lore")
                .urlFoto("teste")
                .build();
        Egresso retornoEgresso = egressoRepo.save(egresso);

        List<Depoimento> depoimentos = new ArrayList<Depoimento>();
        for (int i = 0; i < 3; i++) {
            depoimentos.add(
                    Depoimento.builder().egresso(retornoEgresso)
                            .texto("Depoimento teste")
                            .data(new Date(ThreadLocalRandom.current().nextInt() * 1000L))
                            .build());
        }

        // ação
        List<Depoimento> salvos = depoimentoRepo.saveAll(depoimentos);

        List<Depoimento> retorno = new ArrayList<Depoimento>();
        retorno.addAll(depoimentoRepo.obterDepoimentosOrdenadosPeloMaisRecente());
        depoimentoRepo.deleteAll(salvos);

        Collections.sort(depoimentos, new Comparator<Depoimento>() {
            public int compare(Depoimento d1, Depoimento d2) {
                return d2.getData().compareTo(d1.getData());
            }
        });

        // verificação
        Assertions.assertNotNull(retorno);
        Assertions.assertEquals(depoimentos.size(), retorno.size());
        for (int i = 0; i < retorno.size(); i++) {
            Assertions.assertEquals(depoimentos.get(i).getTexto(), retorno.get(i).getTexto());
            Assertions.assertEquals(depoimentos.get(i).getData().toString(),
                    retorno.get(i).getData().toString());
        }
    }

    @Test
    public void deveListarDepoimentosPorEgresso() {
        //
        List<Egresso> egressos = new ArrayList<Egresso>();
        for (int i = 0; i < 2; i++) {
            egressos.add(Egresso.builder()
                    .nome("tuludan" + i)
                    .email("a@a.com")
                    .cpf("1234")
                    .resumo("lorem ipsum lore")
                    .urlFoto("teste")
                    .build());
        }

        List<Egresso> retornoEgresso = egressoRepo.saveAll(egressos);

        List<Depoimento> depoimentos = new ArrayList<Depoimento>();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                depoimentos.add(
                        Depoimento.builder().egresso(retornoEgresso.get(i))
                                .texto("Depoimento teste" + j)
                                .data(new Date(ThreadLocalRandom.current().nextInt()
                                        * 1000L))
                                .build());
            }
        }

        // ação
        List<Depoimento> retornoDepoimentos = depoimentoRepo.saveAll(depoimentos);

        List<Depoimento> retorno = new ArrayList<Depoimento>();
        for (Egresso egresso : egressos) {
            retorno.addAll(depoimentoRepo.obterDepoimentosPorEgresso(egresso));
        }
        depoimentoRepo.deleteAll(retornoDepoimentos);
        egressoRepo.deleteAll(retornoEgresso);

        // verificação
        Assertions.assertNotNull(retorno);
        Assertions.assertEquals(depoimentos.size(), retorno.size());
        for (int i = 0; i < retorno.size(); i++) {
            Assertions.assertEquals(depoimentos.get(i).getTexto(), retorno.get(i).getTexto());
            Assertions.assertEquals(depoimentos.get(i).getData().toString(),
                    retorno.get(i).getData().toString());
        }
    }

}