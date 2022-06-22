package com.labprog.egressos.controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
import com.labprog.egressos.model.Cargo;
import com.labprog.egressos.model.Egresso;
import com.labprog.egressos.model.dto.CargoDTO;
import com.labprog.egressos.service.CargoService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CargoController.class)
@AutoConfigureMockMvc
public class CargoControllerTest {
    
    static final String API = "/api/cargos";

    @Autowired
    MockMvc mvc;

    @MockBean
    CargoService service;

    @Test
    public void deveSalvarCargo() throws Exception {
        //Cenário
        String string = "Teste tuludan controller deveSalvarCargo";
        CargoDTO dto = CargoDTO.builder()
                .nome(string)
                .descricao(string)
                .build();
        
        Cargo retornoServ = Cargo.builder()
                .nome(dto.getNome())
                .descricao(dto.getDescricao())
                .build();

        Mockito.when(
                service.salvar(Mockito.any(Cargo.class))
        ).thenReturn(retornoServ);

        String json = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .writeValueAsString(dto);

        //Ação
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(API + "/salvar")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        //Verificação
        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isCreated());
        
    }

    public void deveAtualizarCargo() throws Exception {
        //Cenário
        String string = "Teste tuludan controller deveSalvarCargo";
        CargoDTO dto = CargoDTO.builder()
                .id(1L)
                .nome(string)
                .descricao(string)
                .build();
        
        Cargo retornoServ = Cargo.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .descricao(dto.getDescricao())
                .build();

        Mockito.when(
            service.atualizar(Mockito.any(Cargo.class))
        ).thenReturn(retornoServ);

        String json = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .writeValueAsString(dto);
        
        //Ação
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put(API + "/atualizar/1");
        
        //Verificação
        mvc.perform(
                request
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        ).andExpect(MockMvcResultMatchers.status().isCreated());
         
    }

    @Test
    public void deveBuscarTodosCargos() throws Exception {
        //Cenário
        String string = "Teste tuludan controller deveBuscarTodosCargos";
        List<Cargo> retornoServLista = Arrays.asList(
                Cargo.builder()
                        .id(1L)
                        .nome(string)
                        .descricao(string)
                        .build(),
                Cargo.builder()
                        .id(2L)
                        .nome(string)
                        .descricao(string)
                        .build()
        );

        Mockito.when(
                service.buscar()
        ).thenReturn(retornoServLista);  

        //Ação
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(API + "/buscar");
        
        //Verificação
        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(
                    MockMvcResultMatchers
                        .jsonPath("$[0].id").value(retornoServLista.get(0).getId()))
                .andExpect(
                    MockMvcResultMatchers
                        .jsonPath("$[0].nome").value(retornoServLista.get(0).getNome()))
                .andExpect(
                    MockMvcResultMatchers
                        .jsonPath("$[0].descricao").value(retornoServLista.get(0).getDescricao()))
                .andExpect(
                    MockMvcResultMatchers
                        .jsonPath("$[1].id").value(retornoServLista.get(1).getId()))
                .andExpect(
                    MockMvcResultMatchers
                        .jsonPath("$[1].nome").value(retornoServLista.get(1).getNome()))
                .andExpect(
                    MockMvcResultMatchers
                        .jsonPath("$[1].descricao").value(retornoServLista.get(1).getDescricao()));
        
    }

    @Test
    public void deveBuscarCargoPorIdUsandoListar() throws Exception {
        //Cenário
        String string = "Teste tuludan controller deveBuscarCargoPorIdUsandoListar";
        List<Cargo> retornoServLista =  Arrays.asList(Cargo.builder()
                        .id(1L)
                        .nome(string)
                        .descricao(string)
                        .build()
        );


        Mockito.when(
                service.listar(Mockito.any(Cargo.class))
        ).thenReturn(retornoServLista);

        //Ação
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(API + "/buscar/1");
        
        //Verificação
        mvc.perform(request)
                .andExpect(
                        MockMvcResultMatchers.status().isOk()
                )
                .andExpect(
                    MockMvcResultMatchers
                        .jsonPath("$.id").value(retornoServLista.get(0).getId())
                )
                .andExpect(
                    MockMvcResultMatchers
                        .jsonPath("$.nome").value(retornoServLista.get(0).getNome())
                )
                .andExpect(
                    MockMvcResultMatchers
                        .jsonPath("$.descricao").value(retornoServLista.get(0).getDescricao())
                );
        
        
    }

    @Test
    public void deveRemoverCargo() throws Exception {
        //Cenário
        Mockito.doNothing().when(service).remover(Mockito.any(Cargo.class));

        //Ação
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete(API + "/remover/7");

        //Verificação
        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /* 
    @Test
    public void deveListarCargos() throws Exception {
        //Cenário
        List<Cargo> cargos = Arrays.asList(
                Cargo.builder()
                        .nome("Teste")
                        .descricao("Teste")
                        .build(),
                Cargo.builder()
                        .nome("Teste")
                        .descricao("Teste")
                        .build()
        );
        
        Mockito.when(
                service.listar(Mockito)
        ).thenReturn(cargos);
        
        //Ação
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(API + "/listar");
        
        //Verificação
        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nome").value("Teste"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].nome").value("Teste"));
    }
    */
}
