package com.labprog.egressos.service;

import com.labprog.egressos.model.FaixaSalario;

import com.labprog.egressos.model.repository.FaixaSalarioRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FaixaSalarioService {
    @Autowired
    FaixaSalarioRepo repository;

    public int quantidadeEgressoPorFaixaSalario(FaixaSalario fs){
        int quantidade = repository.quantidadeEgressoPorFaixaSalario(fs);
        return quantidade;
    }
}
