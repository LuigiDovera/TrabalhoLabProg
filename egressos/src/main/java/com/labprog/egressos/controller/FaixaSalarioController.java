package com.labprog.egressos.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.labprog.egressos.model.FaixaSalario;
import com.labprog.egressos.service.FaixaSalarioService;
import com.labprog.egressos.service.exceptions.ServiceRuntimeException;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;


@SuppressWarnings("rawtypes")
@RestController
@RequestMapping("/api/faixa_salario")
public class FaixaSalarioController {
    @Autowired
    private FaixaSalarioService service;

    @GetMapping("/quantidade_egresso_por_faixa_salario/{descricao}")
    public ResponseEntity quantidadeEgressoPorFaixaSalario(@PathVariable String descricao) {

        try{
            FaixaSalario fs = service.obterFaixaSalarioPorDescricao(descricao);
            int quantidade = service.quantidadeEgressoPorFaixaSalario(fs);
            
            return ResponseEntity.status(HttpStatus.OK).body(quantidade);
        } catch (ServiceRuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    

}
