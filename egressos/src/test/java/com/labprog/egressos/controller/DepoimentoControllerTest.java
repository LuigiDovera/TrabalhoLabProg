package com.labprog.egressos.controller;

import java.time.LocalDate;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.labprog.egressos.model.Depoimento;
import com.labprog.egressos.model.Egresso;
import com.labprog.egressos.model.dto.DepoimentoDTO;
import com.labprog.egressos.service.DepoimentoService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = DepoimentoController.class)
@AutoConfigureMockMvc
public class DepoimentoControllerTest {

    static final String API = "/api/depoimentos";

    @Autowired
    MockMvc mvc;

    @MockBean
    DepoimentoService service;

    @Test
    public void deveSalvarDepoimento() throws Exception {
        // Cenário
        DepoimentoDTO dto = DepoimentoDTO.builder()
                .texto("Depoimento de teste")
                .data(LocalDate.now())
                .build();

        Depoimento retornoServ = Depoimento.builder()
                .texto(dto.getTexto())
                .data(dto.getData())
                .build();

        Mockito.when(service.salvar(
                Mockito.any(Depoimento.class))).thenReturn(retornoServ);

        String json = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .writeValueAsString(dto);

        // Ação
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(API + "/salvar")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        // Verificação
        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void deveAtualizarDepoimento() throws Exception {
        // Cenário
        DepoimentoDTO dto = DepoimentoDTO.builder()
                .id(1L)
                .texto("Depoimento de teste")
                .data(LocalDate.now())
                .build();

        Depoimento retornoServ = Depoimento.builder()
                .id(dto.getId())
                .texto(dto.getTexto())
                .data(dto.getData())
                .build();

        Mockito.when(service.atualizar(Mockito.any(Depoimento.class))).thenReturn(retornoServ);

        String json = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .writeValueAsString(dto);

        // Ação
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put(API + "/atualizar/1");

        // Verificação
        mvc.perform(request
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void deveBuscarDepoimentos() throws Exception {
        // Cenário
        List<Depoimento> retornoServLista = Arrays.asList(
                Depoimento.builder()
                        .id(1L)
                        .texto("Depoimento de teste")
                        .data(LocalDate.now())
                        .build(),
                Depoimento.builder()
                        .id(2L)
                        .texto("Depoimento de teste 2")
                        .data(LocalDate.now().plusDays(3))
                        .build());

        Mockito.when(service.buscar()).thenReturn(retornoServLista);

        // Ação
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(API + "/buscar");

        // Verificação
        mvc.perform(request)
                .andExpect(
                        MockMvcResultMatchers.status().isOk())
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$[0].id").value(retornoServLista.get(0).getId()))
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$[0].texto").value(retornoServLista.get(0).getTexto()))
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$[0].data").value(retornoServLista.get(0).getData().toString()))
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$[1].id").value(retornoServLista.get(1).getId()))
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$[1].texto").value(retornoServLista.get(1).getTexto()))
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$[1].data").value(retornoServLista.get(1).getData().toString()));
    }

    @Test
    public void deveRemoverDepoimento() throws Exception {
        // Cenário
        Mockito.doNothing().when(service).remover(Mockito.any(Depoimento.class));

        // Ação
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete(API + "/remover/1");

        // Verificação
        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void deveBuscarDepoimentoPorId() throws Exception {
        // Cenário
        List<Depoimento> retornoServLista = Arrays.asList(
                Depoimento.builder()
                        .id(1L)
                        .texto("Depoimento de teste")
                        .data(LocalDate.now())
                        .build());

        Mockito.when(service.buscar(Mockito.any(Depoimento.class))).thenReturn(retornoServLista);

        // Ação
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(API + "/buscar/1");

        // Verificação
        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$.id").value(retornoServLista.get(0).getId()))
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$.texto").value(retornoServLista.get(0).getTexto()))
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$.data").value(retornoServLista.get(0).getData().toString()));
    }

    @Test
    public void deveBuscarDepoimentosOrdenados() throws Exception {
        // Cenário
        List<Depoimento> retornoServLista = Arrays.asList(
                Depoimento.builder()
                        .id(2L)
                        .texto("Depoimento de teste")
                        .data(LocalDate.now().plusDays(4))
                        .build(),
                Depoimento.builder()
                        .id(1L)
                        .texto("Depoimento de teste 2")
                        .data(LocalDate.now().plusDays(3))
                        .build());

        Mockito.when(service.listarDepoimentosOrdenadosPeloMaisRecente())
                .thenReturn(retornoServLista);

        // Ação
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(API + "/buscar_ordenado");

        // Verificação
        mvc.perform(request)
                .andExpect(
                        MockMvcResultMatchers.status().isOk())
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$[0].id").value(retornoServLista.get(0).getId()))
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$[0].texto").value(retornoServLista.get(0).getTexto()))
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$[0].data").value(retornoServLista.get(0).getData().toString()))
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$[1].id").value(retornoServLista.get(1).getId()))
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$[1].texto").value(retornoServLista.get(1).getTexto()))
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$[1].data").value(retornoServLista.get(1).getData().toString()));
    }

    @Test
    public void deveBuscarDepoimentoPorEgresso() throws Exception {
        // Cenário
        List<Depoimento> retornoServLista = Arrays.asList(
                Depoimento.builder()
                        .id(2L)
                        .texto("Depoimento de teste")
                        .data(LocalDate.now().plusDays(4))
                        .build(),
                Depoimento.builder()
                        .id(1L)
                        .texto("Depoimento de teste 2")
                        .data(LocalDate.now().plusDays(3))
                        .build());

        Mockito.when(service.obterDepoimentosPorEgresso(Mockito.any(Egresso.class))).thenReturn(retornoServLista);

        // Ação
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(API + "/buscar_por_egresso/1");

        // Verificação
        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(
                        MockMvcResultMatchers.status().isOk())
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$[0].id").value(retornoServLista.get(0).getId()))
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$[0].texto").value(retornoServLista.get(0).getTexto()))
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$[0].data").value(retornoServLista.get(0).getData().toString()))
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$[1].id").value(retornoServLista.get(1).getId()))
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$[1].texto").value(retornoServLista.get(1).getTexto()))
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$[1].data").value(retornoServLista.get(1).getData().toString()));
    }

}