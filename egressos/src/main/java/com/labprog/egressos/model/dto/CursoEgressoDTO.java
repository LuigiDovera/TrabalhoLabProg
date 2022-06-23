package com.labprog.egressos.model.dto;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.labprog.egressos.model.Curso;
import com.labprog.egressos.model.CursoEgressoPK;
import com.labprog.egressos.model.Egresso;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CursoEgressoDTO {
    private CursoEgressoPK id;
    private Curso curso;
    private Egresso egresso;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate data_inicio;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate data_conclusao;

}
