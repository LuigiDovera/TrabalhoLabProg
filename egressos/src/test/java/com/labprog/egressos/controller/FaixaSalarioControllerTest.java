package com.labprog.egressos.controller;

import java.util.Arrays;
import java.util.List;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jayway.jsonpath.Option;
import com.labprog.egressos.model.FaixaSalario;
import com.labprog.egressos.model.dto.FaixaSalarioDTO;
import com.labprog.egressos.service.FaixaSalarioService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CargoController.class)
@AutoConfigureMockMvc
public class FaixaSalarioControllerTest {
    
    static final String API = "/api/faixa_salario";

    @Autowired
    MockMvc mvc;

    @MockBean
    FaixaSalarioService service;

    
    @Test
    public void deveRetornarQuantidadeEgressoPorFaixaSalario() throws Exception {
        //Cenario
        String faixaSalarioDescricao = "3000 - 4000";

        FaixaSalarioDTO dto = FaixaSalarioDTO.builder()
                .id(7L)
                .descricao(faixaSalarioDescricao)
                .build();

        FaixaSalario retornoServ = FaixaSalario.builder()
                .id(dto.getId())
                .descricao(dto.getDescricao())
                .build();
        

        Mockito.when(
            service.quantidadeEgressoPorFaixaSalario(Mockito.any(FaixaSalario.class))
        ).thenReturn(1);

        //Ação
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
            .get(API + "/quantidade_egresso_por_faixa_salario/" + faixaSalarioDescricao);

        //Verificação
        mvc.perform(request)
            .andExpect(
                MockMvcResultMatchers.status().isOk()
            );


    }

}
