package com.labprog.egressos.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import lombok.*;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CursoEgressoPK implements Serializable {
    
    @Column(name = "curso_id")
    Long curso_id;

    @Column(name = "egresso_id")
    Long egresso_id;
}
