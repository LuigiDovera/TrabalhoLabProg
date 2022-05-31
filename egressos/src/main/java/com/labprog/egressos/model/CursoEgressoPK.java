package com.labprog.egressos.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CursoEgressoPK implements Serializable {
    
    @Column(name = "curso_id")
    Long cursoId;

    @Column(name = "egresso_id")
    Long egressoId;
}
