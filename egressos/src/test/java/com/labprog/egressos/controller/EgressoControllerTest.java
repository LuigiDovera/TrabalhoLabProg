package com.labprog.egressos.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.labprog.egressos.model.Egresso;
import com.labprog.egressos.model.dto.EgressoDTO;
import com.labprog.egressos.service.EgressoService;

@ExtendWith(SpringExtension.class)
@WebMvcTest (controllers =  EgressoController.class)
@AutoConfigureMockMvc
public class EgressoControllerTest {
    
    static final String API = "/api/egressos";

    @Autowired
    MockMvc mvc;

    @MockBean
    EgressoService service;

    @Test
    public void deveSalvarEgresso() throws Exception {
        // Cenário
        EgressoDTO dto = EgressoDTO.builder()
                .nome("Nome")
                .email("email@e.com")
                .cpf("123456789")
                .resumo("Resumo")
                .urlFoto("urlFoto")
                .build();

        Egresso egresso = Egresso.builder()
                .id(1l)
                .nome(dto.getNome())
                .email(dto.getEmail())
                .cpf(dto.getCpf())
                .resumo(dto.getResumo())
                .urlFoto(dto.getUrlFoto())
                .build();

        Mockito.when(service.salvar(
            Mockito.any(Egresso.class))).thenReturn(egresso);

        String json = new ObjectMapper().writeValueAsString(dto);

        // Ação
        MockHttpServletRequestBuilder request = 
            MockMvcRequestBuilders.post(API + "/salvar")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json);

        // Verificação
        mvc.perform(request)
            .andExpect(MockMvcResultMatchers.status().isCreated());
    }

}