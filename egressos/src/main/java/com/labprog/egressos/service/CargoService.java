package com.labprog.egressos.service;

import java.util.List;
import java.util.Optional;

import com.labprog.egressos.model.Cargo;
import com.labprog.egressos.model.Egresso;
import com.labprog.egressos.model.ProfEgresso;

import com.labprog.egressos.model.repository.CargoRepo;
import com.labprog.egressos.model.repository.ProfEgressoRepo;
import com.labprog.egressos.service.exceptions.RegraNegocioRunTime;

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

    public List<Cargo> buscar (Cargo filtro){
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
            throw new RegraNegocioRunTime("Cargo informado tem relação com Profissão de Egresso");
    }

    private void verificarId(Cargo car) {
        if ((car == null) || (car.getId() == null))
            throw new RegraNegocioRunTime("Cargo sem id");
    }

    private void verificarCargo(Cargo car){
        if(car == null)
            throw new RegraNegocioRunTime("Um cargo válido deve ser informado");

        if((car.getNome() == null)
                || (car.getNome().equals("")))
            throw new RegraNegocioRunTime("Um cargo precisa ter nome");

        if((car.getDescricao() == null)
                || (car.getDescricao().equals("")))
            throw new RegraNegocioRunTime("Um cargo precisa ter descricao");
    }

    
}
