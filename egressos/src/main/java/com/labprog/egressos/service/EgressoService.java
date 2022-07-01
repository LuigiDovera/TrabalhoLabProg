package com.labprog.egressos.service;

import java.util.ArrayList;
import java.util.List;

import com.labprog.egressos.model.Contato;
import com.labprog.egressos.model.CursoEgresso;
import com.labprog.egressos.model.CursoEgressoPK;
import com.labprog.egressos.model.Depoimento;
import com.labprog.egressos.model.Egresso;
import com.labprog.egressos.model.ProfEgresso;
import com.labprog.egressos.model.repository.EgressoRepo;
import com.labprog.egressos.service.exceptions.ServiceRuntimeException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EgressoService {

    @Autowired
    private EgressoRepo repo;

    @Autowired
    private ContatoService contatoService;

    @Autowired
    private DepoimentoService depoimentoService;

    @Autowired
    private CursoEgressoService cursoEgressoService;

    @Autowired
    private CursoService cursoService;

    @Transactional
    public Egresso salvar(Egresso egresso) {
        verificarEgresso(egresso);
        return repo.save(egresso);
    }

    @Transactional
    public Egresso atualizar(Egresso egresso) {
        verificarEgresso(egresso);
        verificarId(egresso);
        return repo.save(egresso);
    }

    @Transactional
    public void remover(Egresso egresso) {
        verificarId(egresso);
        repo.delete(egresso);
    }

    public List<Egresso> buscar(Egresso filtro) {
        Example<Egresso> example = Example.of(filtro, ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(StringMatcher.CONTAINING));

        return repo.findAll(example);
    }

    public List<Egresso> buscar() {
        return repo.findAll();
    }

    @Transactional
    public Egresso atualizarContatos(Egresso egresso, List<Contato> contatos) throws ServiceRuntimeException {
        verificarEgresso(egresso);
        verificarId(egresso);
        ArrayList<Contato> contatosValidados = new ArrayList<>();
        for (Contato contato : contatos) {
            if (contato.getId() == null) {
                contatosValidados.add(contatoService.salvar(contato));
            } else {
                contatosValidados.add(contatoService.atualizar(contato));
            }
        }
        egresso.setContatos(contatosValidados);
        return repo.save(egresso);
    }

    @Transactional
    public Egresso atualizarDepoimentos(Egresso egresso, List<Depoimento> depoimentos) throws ServiceRuntimeException {
        verificarEgresso(egresso);
        verificarId(egresso);
        ArrayList<Depoimento> depoimentosValidados = new ArrayList<>();
        for (Depoimento depoimento : depoimentos) {
            if (depoimento.getId() == null) {
                depoimentosValidados.add(depoimentoService.salvar(depoimento));
            } else {
                depoimentosValidados.add(depoimentoService.atualizar(depoimento));
            }
        }
        egresso.setDepoimentos(depoimentosValidados);
        return repo.save(egresso);
    }

    public List<Contato> buscarContatosEgresso(Egresso egresso) {
        verificarId(egresso);
        return egresso.getContatos();
    }

    @Transactional
    public Egresso atualizarCursos(Egresso egresso, List<CursoEgresso> cursos) {
        verificarEgresso(egresso);
        verificarId(egresso);
        ArrayList<CursoEgresso> cursosValidados = new ArrayList<>();
        for (CursoEgresso curso : cursos) {
            if (curso.getCurso().getId() == null) {
                throw new ServiceRuntimeException("Curso inválido");
            }
            CursoEgressoPK pk = CursoEgressoPK.builder()
                    .egresso_id(egresso.getId())
                    .curso_id(curso.getCurso().getId())
                    .build();
            curso.setId(pk);
            if (cursoEgressoService.buscarPorId(curso.getId()).isEmpty()) {
                cursosValidados.add(cursoEgressoService.salvar(curso));
            } else {
                cursosValidados.add(cursoEgressoService.atualizar(curso));
            }
        }
        egresso.setEgressoCursos(cursosValidados);
        return repo.save(egresso);
    }

    @Transactional
    public Egresso atualizarProfissoes(Egresso egresso, List<ProfEgresso> profissoes) {
        verificarEgresso(egresso);
        verificarId(egresso);
        egresso.setProfsEgressos(profissoes);
        return repo.save(egresso);
    }

    private void verificarEgresso(Egresso egresso) {
        if (egresso == null)
            throw new ServiceRuntimeException("O egresso está nulo");
        if ((egresso.getNome() == null) || (egresso.getNome().isEmpty()))
            throw new ServiceRuntimeException("Nome do egresso deve ser informado");
        if ((egresso.getEmail() == null) || (egresso.getEmail().isEmpty()))
            throw new ServiceRuntimeException("Email do egresso deve ser informado");
        if ((egresso.getCpf() == null) || (egresso.getCpf().isEmpty()))
            throw new ServiceRuntimeException("CPF do egresso deve ser informado");
        //if ((egresso.getSenha() == null) || (egresso.getSenha().isEmpty()))
            //throw new ServiceRuntimeException("Senha do egresso deve ser informada");
    }

    private void verificarId(Egresso egresso) {
        if ((egresso == null) || (egresso.getId() == null) ||
                !(repo.existsById(egresso.getId()))) {
            throw new ServiceRuntimeException("ID de egresso inválido");
        }
    }

}
