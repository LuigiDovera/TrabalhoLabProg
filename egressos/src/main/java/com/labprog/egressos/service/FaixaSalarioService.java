package com.labprog.egressos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labprog.egressos.model.FaixaSalario;
import com.labprog.egressos.model.repository.FaixaSalarioRepo;

@Service
public class FaixaSalarioService {
    @Autowired
    FaixaSalarioRepo repository;

    public List<FaixaSalario> buscar() {
        return repository.findAll();
    }

    public int quantidadeEgressoPorFaixaSalario(FaixaSalario fs){
        int quantidade = repository.quantidadeEgressoPorFaixaSalario(fs);
        return quantidade;
    }

    public FaixaSalario obterFaixaSalarioPorDescricao(String descricao){
        return repository.obterFaixaSalarioPorDescricao(descricao);
    }
}
