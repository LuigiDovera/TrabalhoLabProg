package com.labprog.egressos.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EgressoDTO {
    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private String resumo;
    private String urlFoto;
    private String senha;
    private List<ContatoDTO> contatos;
    private List<CursoDTO> cursos;
    private List<DepoimentoDTO> depoimentos;
}