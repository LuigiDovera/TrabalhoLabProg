package com.labprog.egressos.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class EgressoDTO {
    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private String resumo;
    private String urlFoto;
    private List<ContatoDTO> contatos;
    private List<CursoDTO> cursos;
    private List<DepoimentoDTO> depoimentos;
}