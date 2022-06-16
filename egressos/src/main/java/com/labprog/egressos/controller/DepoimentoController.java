package com.labprog.egressos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.labprog.egressos.model.Depoimento;
import com.labprog.egressos.model.Egresso;
import com.labprog.egressos.model.dto.DepoimentoDTO;
import com.labprog.egressos.service.DepoimentoService;
import com.labprog.egressos.service.exceptions.ServiceRuntimeException;

@SuppressWarnings("rawtypes")
@RestController
@RequestMapping("/api/depoimentos")
public class DepoimentoController {

    @Autowired
    private DepoimentoService service;

    @PostMapping("/salvar")
    public ResponseEntity salvar(@RequestBody DepoimentoDTO dto) {
        Depoimento depoimento = Depoimento.builder()
                .texto(dto.getTexto())
                .data(dto.getData())
                .build();
        try {
            Depoimento salvo = service.salvar(depoimento);
            return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
        } catch (ServiceRuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity atualizar(
            @RequestBody DepoimentoDTO dto,
            @PathVariable Long id) {
        Depoimento depoimento = Depoimento.builder()
                .id(id)
                .texto(dto.getTexto())
                .data(dto.getData())
                .build();
        try {
            Depoimento salvo = service.atualizar(depoimento);
            return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
        } catch (ServiceRuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity buscar() {
        try {
            List<Depoimento> egressos = service.buscar();
            return ResponseEntity.status(HttpStatus.OK).body(egressos);
        } catch (ServiceRuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity remover(@PathVariable Long id) {
        Depoimento depoimento = Depoimento.builder()
                .id(id)
                .build();
        try {
            service.remover(depoimento);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (ServiceRuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity buscar(@PathVariable Long id) {
        Depoimento filtro = Depoimento.builder()
                .id(id)
                .build();
        try {
            Depoimento depoimento = service.buscar(filtro).get(0);
            return ResponseEntity.status(HttpStatus.OK).body(depoimento);
        } catch (ServiceRuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/buscar_ordenado")
    public ResponseEntity buscarOrdenado() {
        try {
            List<Depoimento> depoimentos = service.listarDepoimentosOrdenadosPeloMaisRecente();
            return ResponseEntity.status(HttpStatus.OK).body(depoimentos);
        } catch (ServiceRuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/buscar_por_egresso/{id}")
    public ResponseEntity buscarPorEgresso(@PathVariable Long id) {
        Egresso egresso = Egresso.builder()
                .id(id)
                .build();
        try {
            List<Depoimento> depoimentos = service.obterDepoimentosPorEgresso(egresso);
            return ResponseEntity.status(HttpStatus.OK).body(depoimentos);
        } catch (ServiceRuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
