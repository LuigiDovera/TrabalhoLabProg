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
import com.labprog.egressos.service.exceptions.RegraNegocioRunTime;
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
public class CargoServiceTest {
        @Autowired
        CargoService service;

        @Autowired
        CargoRepo repository;

        @Autowired
        FaixaSalarioRepo faixaSalarioRepo;

        @Autowired
        ProfEgressoRepo profEgressoRepo;

        @Autowired
        EgressoRepo egressoRepo;

        @Test
        void deveSalvarCargo() {
                // cenario
                Cargo car = Cargo.builder()
                                .nome("Teste1")
                                .descricao("Teste1")
                                .build();

                // açao
                Cargo salvo = service.salvar(car);

                // verificação
                Assertions.assertNotNull(salvo);
                Assertions.assertNotNull(salvo.getId());
        }

        @Test
        public void deveRemoverCargo() {
                // cenario
                Cargo cargo = Cargo.builder()
                                .nome("Gerente teste")
                                .descricao("tuludan")
                                .build();

                // ação
                Cargo salvo = service.salvar(cargo);
                Long id = salvo.getId();

                // verificação
                Optional<Cargo> temp = repository.findById(id);
                Assertions.assertFalse(temp.isPresent());
        }

        @Test
        public void deveBuscarCargo() {
                // cenario
                Cargo cargo = Cargo.builder()
                                .nome("Teste deveBuscarCargo")
                                .descricao("Teste deveBuscarCargo")
                                .build();

                // ação
                service.salvar(cargo);
                Cargo buscado = service.buscar(cargo).get(0);

                // verificação

        }

        @Test
        void buscaProfEgressoPorCargo() {
                Egresso egresso = Egresso.builder()
                                .nome("Teste  profEgressoPorCargo")
                                .email("Teste  profEgressoPorCargo")
                                .cpf("Teste  profEgressoPorCargo")
                                .resumo("Teste  profEgressoPorCargo")
                                .url_foto("Teste  profEgressoPorCargo")
                                .build();

                FaixaSalario faixaSalario = FaixaSalario.builder()
                                .descricao("Teste  profEgressoPorCargo")
                                .build();

                Cargo cargo = Cargo.builder()
                                .nome("Teste  profEgressoPorCargo")
                                .descricao("Teste  profEgressoPorCargo")
                                .build();

                Date dataRegistro = new Date(System.currentTimeMillis());
                ProfEgresso profEgresso = ProfEgresso.builder()
                                .cargo(cargo)
                                .faixaSalario(faixaSalario)
                                .egresso(egresso)
                                .empresa("Teste  profEgressoPorCargo")
                                .descricao("Teste  profEgressoPorCargo")
                                .dataRegistro(dataRegistro)
                                .build();

                // ação
                egressoRepo.save(egresso);
                faixaSalarioRepo.save(faixaSalario);
                repository.save(cargo);
                profEgressoRepo.save(profEgresso);

                List<ProfEgresso> profsEgressos = service.buscarPorProfEgresso(cargo);
                ProfEgresso buscado = profsEgressos.get(0);

                // verificação
                Assertions.assertNotNull(buscado);
                Assertions.assertEquals(buscado.getCargo(), profEgresso.getCargo());
                Assertions.assertEquals(buscado.getFaixaSalario(), profEgresso.getFaixaSalario());
                Assertions.assertEquals(buscado.getEgresso(), profEgresso.getEgresso());
                Assertions.assertEquals(buscado.getEmpresa(), profEgresso.getEmpresa());
                Assertions.assertEquals(buscado.getDescricao(), profEgresso.getDescricao());
                Assertions.assertEquals(buscado.getDataRegistro(), profEgresso.getDataRegistro());
        }

        @Test
        void deveBuscarCargoPorEgresso() {
                String string = "Teste  cargoPorEgresso";
                Egresso egresso = Egresso.builder()
                                .nome(string)
                                .email(string)
                                .cpf(string)
                                .resumo(string)
                                .url_foto(string)
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
                faixaSalarioRepo.save(faixaSalario);
                repository.save(cargo);
                profEgressoRepo.save(profEgresso);

                List<Cargo> cargos = service.buscarCargoPorEgresso(egresso);
                Cargo buscado = cargos.get(0);

                //verificação
                Assertions.assertNotNull(buscado);
                Assertions.assertEquals(buscado.getNome(), cargo.getNome());
                Assertions.assertEquals(buscado.getDescricao(), cargo.getDescricao());
        }

}