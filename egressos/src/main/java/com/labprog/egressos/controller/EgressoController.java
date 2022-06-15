package com.labprog.egressos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.labprog.egressos.service.EgressoService;
import com.labprog.egressos.service.exceptions.ServiceRuntimeException;
import com.labprog.egressos.model.Egresso;
import com.labprog.egressos.model.dto.EgressoDTO;

@SuppressWarnings("rawtypes")
@RestController
@RequestMapping("/api/egressos")
public class EgressoController {
    @Autowired
    private EgressoService service;
    
    //@Autowired
    //private ContatoService contatoService;

    @PostMapping("/salvar")
    public ResponseEntity salvar(@RequestBody EgressoDTO dto) {
        Egresso egresso = Egresso.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .cpf(dto.getCpf())
                .resumo(dto.getResumo())
                .urlFoto(dto.getUrlFoto())
                .build();
        try {
            Egresso salvo = service.salvar(egresso);
            return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
        } catch (ServiceRuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/atualizar/{id}")
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
                .build();
        try {
            Egresso salvo = service.atualizar(egresso);
            return ResponseEntity.status(HttpStatus.OK).body(salvo);
        } catch (ServiceRuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/buscar")
    public ResponseEntity buscar() {
        try {
            List<Egresso> egressos = service.buscar();
            return ResponseEntity.status(HttpStatus.OK).body(egressos);
        } catch (ServiceRuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /*@PutMapping("/atualizarContatos/{id}")
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
                .build();
        ArrayList<Contato> contatos = new ArrayList<>();
        for (ContatoDTO contatoDto : dto.getContatos()) {
            Contato contato = Contato.builder()
                    .id(contatoDto.getId())
                    .nome(contatoDto.getNome())
                    .urlLogo(contatoDto.getUrlLogo())
                    .build();
            contatos.add(contato);
        }
        ArrayList<Contato> contatosValidados = new ArrayList<>();
        try {
            for (Contato contato : contatos) {
                if(contato.getId() == null) {
                    contatosValidados.add(contatoService.salvar(contato));
                } else {
                    contatosValidados.add(contatoService.atualizar(contato));
                }
            }
        } catch (ServiceRuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        try {
            Egresso salvo = service.atualizarContatos(egresso, contatosValidados);
            return ResponseEntity.status(HttpStatus.OK).body(salvo);
        } catch (ServiceRuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }*/

    @PostMapping("/remover/{id}")
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