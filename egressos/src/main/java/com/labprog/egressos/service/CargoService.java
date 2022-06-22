package com.labprog.egressos.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.TransactionScoped;
import javax.transaction.Transactional;

import com.labprog.egressos.model.Cargo;
import com.labprog.egressos.model.Egresso;
import com.labprog.egressos.model.ProfEgresso;

import com.labprog.egressos.model.repository.CargoRepo;
import com.labprog.egressos.model.repository.ProfEgressoRepo;
import com.labprog.egressos.service.exceptions.ServiceRuntimeException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

@Service
public class CargoService {
    @Autowired
    CargoRepo repository;

    @Autowired
    ProfEgressoRepo profEgressoRep;

    public Cargo salvar(Cargo car){
        verificarCargo(car);
        return repository.save(car);
    }

    public Cargo atualizar(Cargo car){
        verificarId(car);
        return salvar(car);
    }

    public void remover(Cargo car){
        verificarId(car);
        verificarProfEgresso(car);
        repository.delete(car);
    }

    public void remover(Long idCargo) {
        Optional<Cargo> car = repository.findById(idCargo);
        remover(car.get());
    }

    @Transactional
    public Optional<Cargo> buscar(Cargo car){
        verificarId(car);
        return repository.findById(car.getId());
    }

    @Transactional
    public List<Cargo> buscar(){
        return repository.findAll();
    }

    public List<Cargo> listar (Cargo filtro){
        Example<Cargo> example = 
                Example.of(filtro, ExampleMatcher.matching()
                        .withIgnoreCase()
                        .withStringMatcher(StringMatcher.CONTAINING)
                );

        return repository.findAll(example);
    }


    
    
    public List<ProfEgresso> buscarPorProfEgresso (Cargo car){
        List<ProfEgresso> profsEgressos = profEgressoRep.findByCargo(car);
        return profsEgressos;
    }
    /*
        Percebi depois de fazer que não era essa a funcionalidade pedida >:^)
    */

    public List<Cargo> buscarCargoPorEgresso (Egresso egr){
        List<Cargo> cargos = repository.acharPorEgresso(egr);
        return cargos;
    } 

    public int quantidadeEgressoPorCargo (Cargo car){
        int quantidade = repository.quantidadeEgressoPorCargo(car);
        return quantidade;
    }

    private void verificarProfEgresso(Cargo car){
        List<ProfEgresso> res = profEgressoRep.findByCargo(car);
        if(!res.isEmpty())
            throw new ServiceRuntimeException("Cargo informado tem relação com Profissão de Egresso");
    }

    private void verificarId(Cargo car) {
        if ((car == null) || (car.getId() == null))
            throw new ServiceRuntimeException("Cargo sem id");
    }

    private void verificarCargo(Cargo car){
        if(car == null)
            throw new ServiceRuntimeException("Um cargo válido deve ser informado");

        if((car.getNome() == null)
                || (car.getNome().equals("")))
            throw new ServiceRuntimeException("Um cargo precisa ter nome");

        if((car.getDescricao() == null)
                || (car.getDescricao().equals("")))
            throw new ServiceRuntimeException("Um cargo precisa ter descricao");
    }

    
}
