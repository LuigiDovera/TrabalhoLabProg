package com.labprog.egressos.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ContatoDTO {
    private Long id;
    private String nome;
    private String urlLogo;
}