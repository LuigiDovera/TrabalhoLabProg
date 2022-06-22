package com.labprog.egressos.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.labprog.egressos.model.Contato;
import com.labprog.egressos.model.Egresso;
import com.labprog.egressos.model.ProfEgresso;
import com.labprog.egressos.model.repository.ContatoRepo;
import com.labprog.egressos.model.repository.ProfEgressoRepo;
import com.labprog.egressos.service.exceptions.ServiceRuntimeException;

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

    //@Autowired
    //ProfEgressoRepo profissaoRepo;

    @Test
    @Transactional
    public void deveSalvarEgresso() {
        // cenário
        Egresso egresso = Egresso.builder()
                .nome("tuludan")
                .email("a@a.com")
                .cpf("1234")
                .resumo("deveSalvarEgresso")
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
    public void deveGerarErroAoTentarSalvarNulo() {
        // cenário
        Egresso egresso = null;

        // ação e verificação
        Throwable retorno = Assertions.assertThrows(
            ServiceRuntimeException.class, 
            () -> _sut.salvar(egresso));
        Assertions.assertEquals(retorno.getMessage(), 
        "O egresso está nulo");
    }

    @Test
    @Transactional
    public void deveGerarErroAoTentarSalvarSemNome() {
        // cenário
        Egresso egresso = Egresso.builder()
                .email("a@a.com")
                .cpf("1234")
                .resumo("deveGerarErroAoTentarSalvarSemNome")
                .urlFoto("teste")
                .build();

        // ação e verificação
        Throwable retorno = Assertions.assertThrows(
            ServiceRuntimeException.class, 
            () -> _sut.salvar(egresso));
        Assertions.assertEquals(retorno.getMessage(), 
            "Nome do egresso deve ser informado");
    }

    @Test
    @Transactional
    public void deveGerarErroAoTentarSalvarSemEmail() {
        // cenário
        Egresso egresso = Egresso.builder()
                .nome("tuludan")
                .cpf("1234")
                .resumo("deveGerarErroAoTentarSalvarSemEmail")
                .urlFoto("teste")
                .build();

        // ação e verificação
        Throwable retorno = Assertions.assertThrows(
            ServiceRuntimeException.class, 
            () -> _sut.salvar(egresso));
        Assertions.assertEquals(retorno.getMessage(), 
            "Email do egresso deve ser informado");
    }

    @Test
    @Transactional
    public void deveGerarErroAoTentarSalvarSemCpf() {
        // cenário
        Egresso egresso = Egresso.builder()
                .nome("tuludan")
                .email("a@a.com")
                .resumo("deveGerarErroAoTentarSalvarSemCpf")
                .urlFoto("teste")
                .build();

        // ação e verificação
        Throwable retorno = Assertions.assertThrows(
            ServiceRuntimeException.class, 
            () -> _sut.salvar(egresso));
        Assertions.assertEquals(retorno.getMessage(), 
            "CPF do egresso deve ser informado");
    }

    @Test
    @Transactional
    public void deveAtualizarEgresso() {
        // cenário
        Egresso egresso = Egresso.builder()
                .nome("tuludan")
                .email("a@a.com")
                .cpf("1234")
                .resumo("deveAtualizarEgresso")
                .urlFoto("teste")
                .build();

        // ação
        Egresso salvo = _sut.salvar(egresso);
        salvo.setNome("nadulut");
        salvo.setEmail("moc.a@a");
        salvo.setCpf("4321");
        salvo.setResumo("deveAtualizarEgresso");
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
    public void deveGerarErroAoTentarAtualizarNulo() {
        // cenário
        Egresso egresso = null;

        // ação e verificação
        Throwable retorno = Assertions.assertThrows(
            ServiceRuntimeException.class, 
            () -> _sut.atualizar(egresso));
        Assertions.assertEquals(retorno.getMessage(), 
        "O egresso está nulo");
    }

    @Test
    @Transactional
    public void deveGerarErroAoTentarAtualizarSemNome() {
        // cenário
        Egresso egresso = Egresso.builder()
                .nome("tuludan")
                .email("a@a.com")
                .cpf("1234")
                .resumo("deveGerarErroAoTentarAtualizarSemNome")
                .urlFoto("teste")
                .build();

        Egresso salvo = _sut.salvar(egresso);
        salvo.setNome(""); 
        salvo.setEmail("moc.a@a");
        salvo.setCpf("4321");
        salvo.setResumo("deveGerarErroAoTentarAtualizarSemNome");
        salvo.setUrlFoto("etset");

        // ação e verificação
        Throwable retorno = Assertions.assertThrows(
            ServiceRuntimeException.class, 
            () -> _sut.atualizar(salvo));
        Assertions.assertEquals(retorno.getMessage(), 
            "Nome do egresso deve ser informado");
    }

    @Test
    @Transactional
    public void deveGerarErroAoTentarAtualizarSemEmail() {
        // cenário
        Egresso egresso = Egresso.builder()
                .nome("tuludan")
                .email("a@a.com")
                .cpf("1234")
                .resumo("deveGerarErroAoTentarAtualizarSemEmail")
                .urlFoto("teste")
                .build();

        Egresso salvo = _sut.salvar(egresso);
        salvo.setNome("nadulut");
        salvo.setEmail("");
        salvo.setCpf("4321");
        salvo.setResumo("deveGerarErroAoTentarAtualizarSemEmail");
        salvo.setUrlFoto("etset");

        // ação e verificação
        Throwable retorno = Assertions.assertThrows(
            ServiceRuntimeException.class, 
            () -> _sut.atualizar(salvo));
        Assertions.assertEquals(retorno.getMessage(), 
            "Email do egresso deve ser informado");
    }

    @Test
    @Transactional
    public void deveGerarErroAoTentarAtualizarSemCpf() {
        // cenário
        Egresso egresso = Egresso.builder()
                .nome("tuludan")
                .email("a@a.com")
                .cpf("1234")
                .resumo("deveGerarErroAoTentarAtualizarSemCpf")
                .urlFoto("teste")
                .build();

        Egresso salvo = _sut.salvar(egresso);
        salvo.setNome("nadulut");
        salvo.setEmail("moc.a@a");
        salvo.setCpf("");
        salvo.setResumo("deveGerarErroAoTentarAtualizarSemCpf");
        salvo.setUrlFoto("etset");

        // ação e verificação
        Throwable retorno = Assertions.assertThrows(
            ServiceRuntimeException.class, 
            () -> _sut.atualizar(salvo));
        Assertions.assertEquals(retorno.getMessage(), 
            "CPF do egresso deve ser informado");
    }

    @Test
    @Transactional
    public void deveGerarErroAoAtualizarInexistente() {
        //cenário
        Egresso egresso = Egresso.builder()
                .nome("tuludan")
                .email("a@a.com")
                .cpf("1234")
                .resumo("deveGerarErroAoAtualizarInexistente")
                .urlFoto("teste")
                .build();
        
        //ação e verificação        
        Throwable retorno = Assertions.assertThrows(
            ServiceRuntimeException.class, 
            () -> _sut.atualizar(egresso));
        Assertions.assertEquals(retorno.getMessage(), 
            "ID de egresso inválido");
    }

    @Test
    @Transactional
    public void deveRemoverEgresso() {
        //cenário
        Egresso egresso = Egresso.builder()
                .nome("tuludan")
                .email("a@a.com")
                .cpf("1234")
                .resumo("deveRemoverEgresso")
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
    public void deveGerarErroAoRemoverInexistente() {
        //cenário
        Egresso egresso = Egresso.builder()
                .nome("tuludan")
                .email("a@a.com")
                .cpf("1234")
                .resumo("deveGerarErroAoRemoverInexistente")
                .urlFoto("teste")
                .build();
        
        //ação e verificação        
        Throwable retorno = Assertions.assertThrows(
            ServiceRuntimeException.class, 
            () -> _sut.remover(egresso));
        Assertions.assertEquals(retorno.getMessage(), 
            "ID de egresso inválido");
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
                        .resumo("deveBuscarEgressos" + (i+1))
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
    @Transactional
    public void deveRetornarVazioAoNaoEncontrarEgressos(){
        // cenário
        Egresso egresso = Egresso.builder()
                            .nome("tuludan")
                            .email("a@a.com")
                            .cpf("1234")
                            .resumo("deveRetornarVazioAoNaoEncontrarEgressos")
                            .urlFoto("teste")
                            .build();
        
        // ação
        List<Egresso> retorno = _sut.buscar(egresso);

        // verificação
        Assertions.assertTrue(retorno.isEmpty());
    }

    @Test
    public void atualizarContatosDeveInserirContatosNovos() {
        // cenário
        Egresso egresso = Egresso.builder()
                .nome("tuludan")
                .email("a@a.com")
                .cpf("1234")
                .resumo("atualizarContatosDeveInserirContatosNovos")
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

        // rollback
        _sut.remover(retorno);
        contatoRepo.deleteAll(contatosSalvos);

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
                .resumo("atualizarContatosDeveRemoverContato")
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

    @Test
    public void deveBuscarContatosEgresso() {
        // cenário
        Egresso egresso = Egresso.builder()
                .nome("tuludan")
                .email("a@a.com")
                .cpf("1234")
                .resumo("deveBuscarContatosEgresso")
                .urlFoto("teste")
                .build();
        
        List<Contato> contatos = Arrays.asList(
            Contato.builder().nome("contato1").urlLogo("logo1").build(),
            Contato.builder().nome("contato2").urlLogo("logo2").build()
        );
        
        // ação
        Egresso salvo = _sut.salvar(egresso);
        List<Contato> contatosSalvos = contatoRepo.saveAll(contatos);
        salvo = _sut.atualizarContatos(salvo, contatos);
        List<Contato> retorno = _sut.buscarContatosEgresso(salvo);

        _sut.remover(salvo);
        contatoRepo.deleteAll(contatosSalvos);

        // verificação
        Assertions.assertNotNull(retorno);
        Assertions.assertEquals(retorno.size(), contatosSalvos.size());
        for (int i=0; i < contatosSalvos.size(); i++) {
            Assertions.assertEquals(contatosSalvos.get(i).getId(), retorno.get(i).getId());
            Assertions.assertEquals(contatosSalvos.get(i).getNome(), retorno.get(i).getNome());
            Assertions.assertEquals(contatosSalvos.get(i).getUrlLogo(), retorno.get(i).getUrlLogo());
        }
    }

    /*
    @Test
    public void atualizarProfissoesDeveInserirProfissoesNovas() {
        // cenário
        Egresso egresso = Egresso.builder()
                .nome("tuludan")
                .email("a@a.com")
                .cpf("1234")
                .resumo("lorem ipsum lore")
                .urlFoto("teste")
                .build();
        
        List<ProfEgresso> profissoes = Arrays.asList(
            ProfEgresso.builder()
                .dataRegistro(LocalDate.valueOf("2022-01-01"))
                .descricao("lorem ipsum lore")
                .empresa("tuludan inc")
                .build(),
            ProfEgresso.builder()
                .dataRegistro(LocalDate.valueOf("2022-02-02"))
                .descricao("lorem ipsum lore2")
                .empresa("tuludan inc2")
                .build()
        );
        
        // ação
        Egresso salvo = _sut.salvar(egresso);
        List<ProfEgresso> profissoesSalvas = profissaoRepo.saveAll(profissoes);
        Egresso retorno = _sut.atualizarProfissoes(salvo, profissoes);

        _sut.remover(retorno);
        profissaoRepo.deleteAll(profissoesSalvas);

        // verificação
        Assertions.assertNotNull(retorno);
        Assertions.assertEquals(retorno.getProfissoes().size(), profissoesSalvas.size());
        for (int i=0; i < profissoesSalvas.size(); i++) {
            Assertions.assertEquals(profissoesSalvas.get(i).getId(), retorno.getProfissoes().get(i).getId());
            Assertions.assertEquals(profissoesSalvas.get(i).getDataRegistro(), retorno.getProfissoes().get(i).getDataRegistro());
            Assertions.assertEquals(profissoesSalvas.get(i).getDescricao(), retorno.getProfissoes().get(i).getDescricao());
            Assertions.assertEquals(profissoesSalvas.get(i).getEmpresa(), retorno.getProfissoes().get(i).getEmpresa());
        }

    }

    @Test
    public void atualizarProfissoesDeveRemoverProfissao() {
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

    @Test
    public void deveBuscarProfissoesEgresso() {
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
        salvo = _sut.atualizarContatos(salvo, contatos);
        List<Contato> retorno = _sut.buscarContatosEgresso(salvo);

        _sut.remover(salvo);
        contatoRepo.deleteAll(contatosSalvos);

        // verificação
        Assertions.assertNotNull(retorno);
        Assertions.assertEquals(retorno.size(), contatosSalvos.size());
        for (int i=0; i < contatosSalvos.size(); i++) {
            Assertions.assertEquals(contatosSalvos.get(i).getId(), retorno.get(i).getId());
            Assertions.assertEquals(contatosSalvos.get(i).getNome(), retorno.get(i).getNome());
            Assertions.assertEquals(contatosSalvos.get(i).getUrlLogo(), retorno.get(i).getUrlLogo());
        }
    }
    */

    /*
    @Test
    public void atualizarCursosDeveInserirCursosNovos() {
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
    public void atualizarCursosDeveRemoverCurso() {
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
    */

}
