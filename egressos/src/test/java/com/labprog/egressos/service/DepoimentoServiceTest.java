package com.labprog.egressos.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import com.labprog.egressos.model.Depoimento;
import com.labprog.egressos.model.Egresso;
import com.labprog.egressos.service.DepoimentoService;
import com.labprog.egressos.service.EgressoService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DepoimentoServiceTest {
    @Autowired
    DepoimentoService _sut;

    @Autowired
    EgressoService egressoService;

    @Test
    @Transactional
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
        Egresso retornoEgresso = egressoService.salvar(egresso);

        Depoimento depoimento = Depoimento.builder()
                .egresso(retornoEgresso)
                .texto("Depoimento teste")
                .data(data)
                .build();

        // ação
        Depoimento retornoDepoimento = _sut.salvar(depoimento);

        // verificação
        Assertions.assertNotNull(retornoDepoimento);
        Assertions.assertNotNull(retornoDepoimento.getId());
        Assertions.assertEquals(depoimento.getTexto(), retornoDepoimento.getTexto());
        Assertions.assertEquals(depoimento.getData(), retornoDepoimento.getData());
        Assertions.assertEquals(depoimento.getEgresso(), retornoDepoimento.getEgresso());

        // rollback
        _sut.remover(retornoDepoimento);
        egressoService.remover(retornoEgresso);
    }

    @Test
    @Transactional
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
        Egresso retornoEgresso = egressoService.salvar(egresso);

        Depoimento depoimento = Depoimento.builder()
                .egresso(retornoEgresso)
                .texto("Depoimento teste")
                .data(data)
                .build();

        // ação
        Depoimento retornoDepoimento = _sut.salvar(depoimento);
        retornoDepoimento.setTexto("teste depoimento");
        retornoDepoimento.setData(new Date(2));
        Depoimento depoimentoAtualizado = _sut.atualizar(retornoDepoimento);

        // verificação
        Assertions.assertNotNull(retornoDepoimento);
        Assertions.assertNotNull(retornoDepoimento.getId());
        Assertions.assertEquals(depoimentoAtualizado.getTexto(), retornoDepoimento.getTexto());
        Assertions.assertEquals(depoimentoAtualizado.getData(), retornoDepoimento.getData());
        Assertions.assertEquals(depoimentoAtualizado.getEgresso(), retornoDepoimento.getEgresso());

        // rollback
        _sut.remover(depoimentoAtualizado);
        egressoService.remover(retornoEgresso);
    }

    @Test
    @Transactional
    public void deveRemoverDepoimento() {
        // cenário
        Date data = new Date(1);

        Egresso egresso = Egresso.builder()
                .nome("tuludan")
                .email("a@a.com")
                .cpf("1234")
                .resumo("lorem ipsum lore")
                .urlFoto("teste")
                .build();
        Egresso retornoEgresso = egressoService.salvar(egresso);

        Depoimento depoimento = Depoimento.builder()
                .egresso(retornoEgresso)
                .texto("Depoimento teste")
                .data(data)
                .build();
        Depoimento retornoDepoimento = _sut.salvar(depoimento);

        // ação
        _sut.remover(retornoDepoimento);
        List<Depoimento> temp = _sut.listar(retornoDepoimento);

        // verificação
        Assertions.assertTrue(temp.isEmpty());
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
        Egresso retornoEgresso = egressoService.salvar(egresso);

        List<Depoimento> depoimentos = new ArrayList<Depoimento>();
        for (int i = 0; i < 3; i++) {
            depoimentos.add(
                    Depoimento.builder().egresso(retornoEgresso)
                            .texto("Depoimento teste")
                            .data(new Date(ThreadLocalRandom.current().nextInt() * 1000L))
                            .build());
        }

        // ação
        List<Depoimento> salvos = new ArrayList<Depoimento>();

        for (Depoimento depoimento : depoimentos) {
            salvos.add(_sut.salvar(depoimento));
        }

        List<Depoimento> retorno = new ArrayList<Depoimento>();
        retorno.addAll(_sut.listarDepoimentosOrdenadosPeloMaisRecente());

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

        // rollback
        for (Depoimento depoimento : depoimentos) {
            _sut.remover(depoimento);
        }

        egressoService.remover(retornoEgresso);
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

        List<Egresso> retornoEgresso = new ArrayList<Egresso>();
        for (Egresso egresso : egressos) {
            retornoEgresso.add(egressoService.salvar(egresso));
        }

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
        List<Depoimento> retornoDepoimentos = new ArrayList<Depoimento>();

        for (Depoimento depoimento : depoimentos) {
            retornoDepoimentos.add(_sut.salvar(depoimento));
        }

        List<Depoimento> retorno = new ArrayList<Depoimento>();
        for (Egresso egresso : egressos) {
            retorno.addAll(_sut.obterDepoimentosPorEgresso(egresso));
        }

        // verificação
        Assertions.assertNotNull(retorno);
        Assertions.assertEquals(depoimentos.size(), retorno.size());
        for (int i = 0; i < retorno.size(); i++) {
            Assertions.assertEquals(depoimentos.get(i).getTexto(), retorno.get(i).getTexto());
            Assertions.assertEquals(depoimentos.get(i).getData().toString(),
                    retorno.get(i).getData().toString());
        }

        // rollback
        for (Depoimento depoimento : retornoDepoimentos) {
            _sut.remover(depoimento);
        }
        for (Egresso egresso : retornoEgresso) {
            egressoService.remover(egresso);
        }
    }
}