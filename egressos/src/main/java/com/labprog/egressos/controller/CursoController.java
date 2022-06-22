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

import com.labprog.egressos.model.Curso;
import com.labprog.egressos.model.Egresso;
import com.labprog.egressos.model.dto.CursoDTO;
import com.labprog.egressos.service.CursoService;
import com.labprog.egressos.service.exceptions.ServiceRuntimeException;

@SuppressWarnings("rawtypes")
@RestController
@RequestMapping("/api/cursos")
public class CursoController {
    
    @Autowired
    private CursoService service;

    @PostMapping("/salvar")
    public ResponseEntity salvar(@RequestBody CursoDTO dto) {
        Curso curso = Curso.builder()
                .nome(dto.getNome())
                .nivel(dto.getNivel())
                .build();
        try {
            Curso salvo = service.salvar(curso);
            return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
        } catch (ServiceRuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity atualizar(
            @RequestBody CursoDTO dto,
            @PathVariable Long id) {
        Curso curso = Curso.builder()
                .id(id)
                .nome(dto.getNome())
                .nivel(dto.getNivel())
                .build();
        try {
            Curso salvo = service.atualizar(curso);
            return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
        } catch (ServiceRuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity buscar() {
        try {
            List<Curso> egressos = service.buscar();
            return ResponseEntity.status(HttpStatus.OK).body(egressos);
        } catch (ServiceRuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity remover(@PathVariable Long id) {
        Curso curso = Curso.builder()
                .id(id)
                .build();
        try {
            service.remover(curso);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (ServiceRuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity buscar(@PathVariable Long id) {
        Curso filtro = Curso.builder()
                .id(id)
                .build();
        try {
            Curso curso = service.buscar(filtro).get(0);
            return ResponseEntity.status(HttpStatus.OK).body(curso);
        } catch (ServiceRuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/listar_egressos_por_curso/{id}")
    public ResponseEntity listarEgressosPorCurso(@PathVariable Long id) {
        Curso curso = Curso.builder()
                .id(id)
                .build();
        try {
            List<Egresso> egressos = service.listarEgressosPorCurso(curso);
            return ResponseEntity.status(HttpStatus.OK).body(egressos);
        } catch (ServiceRuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/listar_quantidade_egressos_por_curso/{id}")
    public ResponseEntity listarQuantidadeDeEgressosPorCurso(@PathVariable Long id) {
        Curso curso = Curso.builder()
                .id(id)
                .build();
        try {
            int egressos = service.listarQuantidadeDeEgressosPorCurso(curso);
            return ResponseEntity.status(HttpStatus.OK).body(egressos);
        } catch (ServiceRuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}