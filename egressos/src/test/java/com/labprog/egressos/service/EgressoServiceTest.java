package com.labprog.egressos.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.labprog.egressos.model.Contato;
import com.labprog.egressos.model.Egresso;
import com.labprog.egressos.model.repository.ContatoRepo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EgressoServiceTest {
    @Autowired
    EgressoService _sut;

    @Autowired
    ContatoRepo contatoRepo;

    @Test
    @Transactional
    public void deveSalvarEgresso() {
        // cenário
        Egresso egresso = Egresso.builder()
                .nome("tuludan")
                .email("a@a.com")
                .cpf("1234")
                .resumo("lorem ipsum lore")
                .urlFoto("teste")
                .build();

        // ação
        Egresso retorno = _sut.salvar(egresso);

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
    @Transactional
    public void deveAtualizarEgresso() {
        // cenário
        Egresso egresso = Egresso.builder()
                .nome("tuludan")
                .email("a@a.com")
                .cpf("1234")
                .resumo("lorem ipsum lore")
                .urlFoto("teste")
                .build();

        // ação
        Egresso salvo = _sut.salvar(egresso);
        salvo.setNome("nadulut");
        salvo.setEmail("moc.a@a");
        salvo.setCpf("4321");
        salvo.setResumo("lore ipsum lorem");
        salvo.setUrlFoto("etset");
        Egresso retorno = _sut.atualizar(salvo);

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
    @Transactional
    public void deveRemoverEgresso() {
        //cenário
        Egresso egresso = Egresso.builder()
                .nome("tuludan")
                .email("a@a.com")
                .cpf("1234")
                .resumo("lorem ipsum lore")
                .urlFoto("teste")
                .build();
        
        //ação
        Egresso salvo = _sut.salvar(egresso);
        _sut.remover(salvo);
        List<Egresso> temp = _sut.buscar(salvo);
        
        //verificação        
        Assertions.assertTrue(temp.isEmpty());
    }

    @Test
    @Transactional
    public void deveBuscarEgressos(){
        // cenário
        List<Egresso> egressos = new ArrayList<Egresso>();
        for (int i=0; i < 3; i++) {
            egressos.add(
                Egresso.builder()
                        .nome("tuludan" + (i+1))
                        .email("a@a.com" + (i+1))
                        .cpf("1234" + (i+1))
                        .resumo("lorem ipsum lore" + (i+1))
                        .urlFoto("teste" + (i+1))
                        .build()
            );
        }
        List<Egresso> exemplos = new ArrayList<Egresso>();
        exemplos.addAll(
            Arrays.asList(
                Egresso.builder().nome("tuludan1").build(),
                Egresso.builder().email("a@a.com2").build(),
                Egresso.builder().cpf("12343").build())    
        );
        
        // ação
        for (Egresso egresso : egressos) {
            _sut.salvar(egresso);
        }
        List<Egresso> retorno = new ArrayList<Egresso>();
        for (Egresso exemplo : exemplos) {
            retorno.addAll(_sut.buscar(exemplo));
        }

        // verificação
        Assertions.assertNotNull(retorno);
        Assertions.assertEquals(egressos.size(), retorno.size()); 
        for (int i=0; i < egressos.size(); i++) {
            Assertions.assertEquals(egressos.get(i).getNome(), retorno.get(i).getNome());
            Assertions.assertEquals(egressos.get(i).getEmail(), retorno.get(i).getEmail());
            Assertions.assertEquals(egressos.get(i).getCpf(), retorno.get(i).getCpf());
            Assertions.assertEquals(egressos.get(i).getResumo(), retorno.get(i).getResumo());
            Assertions.assertEquals(egressos.get(i).getUrlFoto(), retorno.get(i).getUrlFoto());
        }
    }


    @Test
    public void atualizarContatosDeveInserirContatosNovos() {
        // cenário
        Egresso egresso = Egresso.builder()
                .nome("tuludan")
                .email("a@a.com")
                .cpf("1234")
                .resumo("lorem ipsum lore")
                .urlFoto("teste")
                .build();
        
        List<Contato> contatos = Arrays.asList(
            Contato.builder().nome("contato1").urlLogo("logo1").build(),
            Contato.builder().nome("contato2").urlLogo("logo2").build()
        );
        
        // ação
        Egresso salvo = _sut.salvar(egresso);
        List<Contato> contatosSalvos = contatoRepo.saveAll(contatos);
        Egresso retorno = _sut.atualizarContatos(salvo, contatos);

        _sut.remover(retorno);
        contatoRepo.deleteAll(contatos);

        // verificação
        Assertions.assertNotNull(retorno);
        Assertions.assertEquals(retorno.getContatos().size(), contatosSalvos.size());
        for (int i=0; i < contatosSalvos.size(); i++) {
            Assertions.assertEquals(contatosSalvos.get(i).getId(), retorno.getContatos().get(i).getId());
            Assertions.assertEquals(contatosSalvos.get(i).getNome(), retorno.getContatos().get(i).getNome());
            Assertions.assertEquals(contatosSalvos.get(i).getUrlLogo(), retorno.getContatos().get(i).getUrlLogo());
        }

    }

    @Test
    public void atualizarContatosDeveRemoverContato() {
        // cenário
        Egresso egresso = Egresso.builder()
                .nome("tuludan")
                .email("a@a.com")
                .cpf("1234")
                .resumo("lorem ipsum lore")
                .urlFoto("teste")
                .build();
        
        List<Contato> contatos = Arrays.asList(
            Contato.builder().nome("contato1").urlLogo("logo1").build(),
            Contato.builder().nome("contato2").urlLogo("logo2").build()
        );
         
        // ação
        Egresso salvo = _sut.salvar(egresso);
        List<Contato> contatosSalvos = contatoRepo.saveAll(contatos);
        salvo = _sut.atualizarContatos(egresso, Arrays.asList(contatos.get(0)));
        List<Contato> retorno = salvo.getContatos();

        _sut.remover(salvo);
        contatoRepo.deleteAll(contatosSalvos);

        // verificação
        Assertions.assertNotNull(retorno);
        Assertions.assertEquals(retorno.size(), 1);
        Assertions.assertEquals(retorno.get(0).getId(), contatosSalvos.get(0).getId());
        Assertions.assertEquals(retorno.get(0).getNome(), contatosSalvos.get(0).getNome());
        Assertions.assertEquals(retorno.get(0).getUrlLogo(), contatosSalvos.get(0).getUrlLogo());

    }

}
