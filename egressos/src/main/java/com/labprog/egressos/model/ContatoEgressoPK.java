package com.labprog.egressos.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ContatoEgressoPK implements Serializable {
    @Column(name = "egresso_id")
    Long egresso;
    
    @Column(name = "contato_id")
    Long contato;
}
