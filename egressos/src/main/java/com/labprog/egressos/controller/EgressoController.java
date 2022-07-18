package com.labprog.egressos.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.labprog.egressos.model.Contato;
import com.labprog.egressos.model.Curso;
import com.labprog.egressos.model.Depoimento;
import com.labprog.egressos.model.Egresso;
import com.labprog.egressos.model.dto.ContatoDTO;
import com.labprog.egressos.model.dto.CursoDTO;
import com.labprog.egressos.model.dto.DepoimentoDTO;
import com.labprog.egressos.model.dto.EgressoDTO;
import com.labprog.egressos.service.EgressoService;
import com.labprog.egressos.service.exceptions.ServiceRuntimeException;

import org.springframework.web.bind.annotation.CrossOrigin;

//@CrossOrigin(maxAge = 3600)
@SuppressWarnings("rawtypes")
@RestController
@RequestMapping("/api/egressos")
public class EgressoController {

    @Autowired
    private EgressoService service;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/salvar")
    public ResponseEntity salvar(@RequestBody EgressoDTO dto) {
        Egresso egresso = Egresso.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .cpf(dto.getCpf())
                .resumo(dto.getResumo())
                .urlFoto(dto.getUrlFoto())
                .senha(passwordEncoder.encode(dto.getSenha()))
                .build();
        try {
            Egresso salvo = service.salvar(egresso);
            return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
        } catch (ServiceRuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/atualizar/{id}")
    public ResponseEntity atualizar(
            @RequestBody EgressoDTO dto,
            @PathVariable Long id) {
        Egresso egresso = Egresso.builder()
                .id(id)
                .nome(dto.getNome())
                .email(dto.getEmail())
                .cpf(dto.getCpf())
                .resumo(dto.getResumo())
                .urlFoto(dto.getUrlFoto())
                .senha(dto.getSenha())
                .build();
        if (dto.getSenha() != null && !dto.getSenha().isEmpty()) {
            egresso.setSenha(passwordEncoder.encode(dto.getSenha()));
        }
        try {
            Egresso salvo = service.atualizar(egresso);
            return ResponseEntity.status(HttpStatus.OK).body(salvo);
        } catch (ServiceRuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/atualizar_contatos/{id}")
    public ResponseEntity atualizarContatos(
            @RequestBody EgressoDTO dto,
            @PathVariable Long id) {
        Egresso egresso = Egresso.builder()
                .id(id)
                .nome(dto.getNome())
                .email(dto.getEmail())
                .cpf(dto.getCpf())
                .resumo(dto.getResumo())
                .urlFoto(dto.getUrlFoto())
                .senha(dto.getSenha())
                .build();
        List<Contato> contatos = new ArrayList<Contato>();
        for (ContatoDTO contatoDto : dto.getContatos()) {
            contatos.add(Contato.builder()
                    .id(contatoDto.getId())
                    .nome(contatoDto.getNome())
                    .urlLogo(contatoDto.getUrlLogo())
                    .build());
        }
        try {
            Egresso salvo = service.atualizarContatos(egresso, contatos);
            return ResponseEntity.status(HttpStatus.OK).body(salvo);
        } catch (ServiceRuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/atualizar_cursos/{id}")
    public ResponseEntity atualizarCursos(
            @RequestBody EgressoDTO dto,
            @PathVariable Long id) {
        Egresso egresso = Egresso.builder()
                .id(id)
                .nome(dto.getNome())
                .email(dto.getEmail())
                .cpf(dto.getCpf())
                .resumo(dto.getResumo())
                .urlFoto(dto.getUrlFoto())
                .senha(dto.getSenha())
                .build();
        List<Curso> cursos = new ArrayList<Curso>();
        for (CursoDTO cursoDto : dto.getCursos()) {
            cursos.add(
                    Curso.builder()
                            .id(cursoDto.getId())
                            .nome(cursoDto.getNome())
                            .nivel(cursoDto.getNivel())
                            .build());
        }
        try {
            Egresso salvo = service.atualizarCursos(egresso, cursos);
            return ResponseEntity.status(HttpStatus.OK).body(salvo);
        } catch (ServiceRuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/atualizar_depoimentos/{id}")
    public ResponseEntity atualizarDepoimentos(
            @RequestBody EgressoDTO dto,
            @PathVariable Long id) {
        Egresso egresso = Egresso.builder()
                .id(id)
                .nome(dto.getNome())
                .email(dto.getEmail())
                .cpf(dto.getCpf())
                .resumo(dto.getResumo())
                .urlFoto(dto.getUrlFoto())
                .senha(dto.getSenha())
                .build();
        List<Depoimento> depoimentos = new ArrayList<Depoimento>();
        for (DepoimentoDTO depoimentoDTO : dto.getDepoimentos()) {
            depoimentos.add(Depoimento.builder()
                    .id(depoimentoDTO.getId())
                    .texto(depoimentoDTO.getTexto())
                    .data(depoimentoDTO.getData())
                    .egresso(egresso)
                    .build());
        }
        try {
            Egresso salvo = service.atualizarDepoimentos(egresso, depoimentos);
            return ResponseEntity.status(HttpStatus.OK).body(salvo);
        } catch (ServiceRuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity buscar() {
        try {
            List<Egresso> egressos = service.buscar();
            return ResponseEntity.status(HttpStatus.OK).body(egressos);
        } catch (ServiceRuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/buscar_id/{id}")
    public ResponseEntity buscar(@PathVariable Long id) {
        Egresso filtro = Egresso.builder()
                .id(id)
                .build();

        try {
            Egresso egresso = service.buscar(filtro).get(0);
            return ResponseEntity.status(HttpStatus.OK).body(egresso);
        } catch (ServiceRuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity remover(@PathVariable Long id) {
        Egresso egresso = Egresso.builder()
                .id(id)
                .build();
        try {
            service.remover(egresso);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (ServiceRuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/buscar/{email}")
    public ResponseEntity buscar(@PathVariable String email) {
        Egresso filtro = Egresso.builder()
                .email(email)
                .build();
        try {
            Egresso egresso = service.buscar(filtro).get(0);
            return ResponseEntity.status(HttpStatus.OK).body(egresso);
        } catch (ServiceRuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}