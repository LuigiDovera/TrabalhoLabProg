package com.labprog.egressos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.labprog.egressos.model.Cargo;
import com.labprog.egressos.model.Egresso;
import com.labprog.egressos.model.ProfEgresso;
import com.labprog.egressos.model.dto.CargoDTO;
import com.labprog.egressos.service.CargoService;
import com.labprog.egressos.service.exceptions.ServiceRuntimeException;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(maxAge = 3600)
@SuppressWarnings("rawtypes")
@RestController
@RequestMapping("/api/cargos")
public class CargoController {
    @Autowired
    private CargoService service;

    @PostMapping("/salvar")
    public ResponseEntity salvar(@RequestBody CargoDTO dto){
        Cargo cargo = Cargo.builder()
                .nome(dto.getNome())
                .descricao(dto.getDescricao())
                .build();

        try{
            Cargo salvo = service.salvar(cargo);
            return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
        } catch (ServiceRuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity atualizar(
            @PathVariable Long id, 
            @RequestBody CargoDTO dto){
        Cargo cargo = Cargo.builder()
                .id(id)
                .nome(dto.getNome())
                .descricao(dto.getDescricao())
                .build();

        try{
            Cargo salvo = service.atualizar(cargo);
            return ResponseEntity.status(HttpStatus.OK).body(salvo);
        } catch (ServiceRuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity buscar(){
        try {
            List<Cargo> cargos = service.buscar();
            return ResponseEntity.status(HttpStatus.OK).body(cargos);
        } catch (ServiceRuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity buscar(@PathVariable Long id) {
        Cargo filtro = Cargo.builder()
                .id(id)
                .build();

        try {
            Cargo cargo = service.listar(filtro).get(0);
            return ResponseEntity.status(HttpStatus.OK).body(cargo);
        } catch (ServiceRuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/buscar_por_egresso/{id_egresso}")
    public ResponseEntity buscarPorEgresso(@PathVariable Long id_egresso){
        Egresso filtro = Egresso.builder()
                .id(id_egresso)
                .build();
        try {
            List<Cargo> cargos = service.buscarCargoPorEgresso(filtro);
            return ResponseEntity.status(HttpStatus.OK).body(cargos);
        } catch (ServiceRuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/buscar_por_profegresso/{id_profegresso}")
    public ResponseEntity buscarPorProfEgresso(@PathVariable Long id_profegresso){
        ProfEgresso filtro = ProfEgresso.builder()
                .id(id_profegresso)
                .build();
        try {
            Cargo cargo = service.buscarPorProfEgresso(filtro);
            return ResponseEntity.status(HttpStatus.OK).body(cargo);
        } catch (ServiceRuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/quantidade_egressos_por_cargo/{id}")
    public ResponseEntity quantidadeEgressoPorCargo(@PathVariable Long id){
        Cargo filtro = Cargo.builder()
                .id(id)
                .build();
        try {
            int quantidade = service.quantidadeEgressoPorCargo(filtro);
            return ResponseEntity.status(HttpStatus.OK).body(quantidade);
        } catch (ServiceRuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity remover(@PathVariable Long id){
        Cargo cargo = Cargo.builder()
                .id(id)
                .build();

        try{
            service.remover(cargo);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Cargo removido com sucesso!");
        } catch (ServiceRuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}

