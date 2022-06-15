package com.labprog.egressos.model.dto;

import java.time.LocalDate;

import com.labprog.egressos.model.Egresso;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class DepoimentoDTO {
    private Long id;
    private String texto;
    private LocalDate data;
    private Egresso egresso;
}
