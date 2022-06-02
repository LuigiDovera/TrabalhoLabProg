package com.labprog.egressos.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.labprog.egressos.model.Cargo;
import com.labprog.egressos.model.FaixaSalario;
import com.labprog.egressos.model.ProfEgresso;
import com.labprog.egressos.model.Egresso;
import com.labprog.egressos.model.repository.CargoRepo;
import com.labprog.egressos.model.repository.FaixaSalarioRepo;
import com.labprog.egressos.model.repository.ProfEgressoRepo;
import com.labprog.egressos.model.repository.EgressoRepo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/*
        @TODO: Possivelmente os testes vão dar erro quando Egresso 
        for linkado com as classes ainda não implementadas.
*/

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class FaixaSalarioServiceTest {
    @Autowired
    FaixaSalarioRepo service;

    @Autowired
    FaixaSalarioRepo repository;

    @Autowired
    CargoRepo cargoRepo;

    @Autowired
    ProfEgressoRepo profEgressoRepo;

    @Autowired
    EgressoRepo egressoRepo;

    @Test
    void deveContarQuantidadeDeEgressosPorFaixaSalario(){
            //cenário
            String string = "Teste  deveContarQuantidadeDeEgressosPorFaixaSalario";
            Egresso egresso = Egresso.builder()
                            .nome(string)
                            .email(string)
                            .cpf(string)
                            .resumo(string)
                            .urlFoto(string)
                            .build();

            FaixaSalario faixaSalario = FaixaSalario.builder()
                            .descricao(string)
                            .build();

            Cargo cargo = Cargo.builder()
                            .nome(string)
                            .descricao(string)
                            .build();

            Date dataRegistro = new Date(System.currentTimeMillis());
            ProfEgresso profEgresso = ProfEgresso.builder()
                            .cargo(cargo)
                            .faixaSalario(faixaSalario)
                            .egresso(egresso)
                            .empresa(string)
                            .descricao(string)
                            .dataRegistro(dataRegistro)
                            .build();

            // ação
            egressoRepo.save(egresso);
            repository.save(faixaSalario);
            cargoRepo.save(cargo);
            profEgressoRepo.save(profEgresso);

            int quantidade = service.quantidadeEgressoPorFaixaSalario(faixaSalario);

            //verificação
            Assertions.assertEquals(1, quantidade); //A quantidade é 1 pois apenas uma faixa está sendo cadastrada no teste
    }
}
