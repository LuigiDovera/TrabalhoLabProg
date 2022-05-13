package com.labprog.egressos.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CursoEgressoPK implements Serializable {
    
    @Column(name = "curso_id")
    private long curso_id;

    @Column(name = "egresso_id")
    private long egresso_id;
}
