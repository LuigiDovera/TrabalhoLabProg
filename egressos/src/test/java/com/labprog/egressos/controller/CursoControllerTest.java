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
import com.labprog.egressos.model.Curso;
import com.labprog.egressos.model.CursoEgresso;
import com.labprog.egressos.model.Egresso;
import com.labprog.egressos.model.dto.CursoDTO;
import com.labprog.egressos.model.dto.EgressoDTO;
import com.labprog.egressos.service.CursoService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CursoController.class)
@AutoConfigureMockMvc
public class CursoControllerTest {

    static final String API = "/api/Cursos";

    @Autowired
    MockMvc mvc;

    @MockBean
    CursoService service;

    @Test
    public void deveSalvarCurso() throws Exception {
        // Cenário
        CursoDTO dto = CursoDTO.builder()
                .nome("Curso de teste")
                .nivel("Nivel de teste")
                .build();

        Curso retornoServ = Curso.builder()
                .nome(dto.getNome())
                .nivel(dto.getNivel())
                .build();

        Mockito.when(service.salvar(
                Mockito.any(Curso.class))).thenReturn(retornoServ);

        String json = new ObjectMapper().writeValueAsString(dto);

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
    public void deveAtualizarCurso() throws Exception {
        // Cenário
        CursoDTO dto = CursoDTO.builder()
                .id(1L)
                .nome("Curso de teste")
                .nivel("Nivel de teste")
                .build();

        Curso retornoServ = Curso.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .nivel(dto.getNivel())
                .build();

        Mockito.when(service.atualizar(Mockito.any(Curso.class))).thenReturn(retornoServ);

        String json = new ObjectMapper().writeValueAsString(dto);

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
    public void deveBuscarCursos() throws Exception {
        // Cenário
        List<Curso> retornoServLista = Arrays.asList(
                Curso.builder()
                        .id(1L)
                        .nome("Curso de teste")
                        .nivel("Nivel de teste")
                        .build(),
                Curso.builder()
                        .id(2L)
                        .nome("Curso de teste 2")
                        .nivel("Nivel de teste")
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
                                .jsonPath("$[0].nome").value(retornoServLista.get(0).getNome()))
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$[0].nivel").value(retornoServLista.get(0).getNivel().toString()))
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$[1].id").value(retornoServLista.get(1).getId()))
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$[1].nome").value(retornoServLista.get(1).getNome()))
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$[1].nivel").value(retornoServLista.get(1).getNivel().toString()));
    }

    @Test
    public void deveRemoverCurso() throws Exception {
        // Cenário
        Mockito.doNothing().when(service).remover(Mockito.any(Curso.class));

        // Ação
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete(API + "/remover/1");

        // Verificação
        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void deveBuscarCursoPorId() throws Exception {
        // Cenário
        List<Curso> retornoServLista = Arrays.asList(
                Curso.builder()
                        .id(1L)
                        .nome("Curso de teste")
                        .nivel("Nivel de teste")
                        .build());

        Mockito.when(service.buscar(Mockito.any(Curso.class))).thenReturn(retornoServLista);

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
                                .jsonPath("$.nome").value(retornoServLista.get(0).getNome()))
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$.nivel").value(retornoServLista.get(0).getNivel().toString()));
    }

    @Test
    public void deveListarEgressosPorCurso() throws Exception {
        // Cenário
        List<Curso> cursos = Arrays.asList(
                Curso.builder()
                        .id(2L)
                        .nome("Curso de teste")
                        .nivel("Nivel de teste")
                        .build(),
                Curso.builder()
                        .id(1L)
                        .nome("Curso de teste 2")
                        .nivel("Nivel de teste")
                        .build());

        List<Egresso> egressos = Arrays.asList(
                Egresso.builder()
                        .id(1L)
                        .nome("Nome")
                        .email("example@e.com")
                        .cpf("123456789")
                        .resumo("Resumo")
                        .urlFoto("urlFoto")
                        .build(),
                Egresso.builder()
                        .id(2L)
                        .nome("Nome2")
                        .email("example2@e.com")
                        .cpf("1234567892")
                        .resumo("Resumo2")
                        .urlFoto("urlFoto2")
                        .build());

        List<CursoEgresso> cursoEgressos = Arrays.asList(
                CursoEgresso.builder()
                                .curso(cursos.get(0))
                                .egresso(egressos.get(0))
                                .data_inicio(LocalDate.now())
                                .data_conclusao(LocalDate.now())
                                .build(),
                CursoEgresso.builder()
                                .curso(cursos.get(1))
                                .egresso(egressos.get(1))
                                .data_inicio(LocalDate.now())
                                .data_conclusao(LocalDate.now())
                                .build()
        );

        Mockito.when(service.listarEgressosPorCurso(Mockito.any(Curso.class))).thenReturn(egressos);

        // Ação
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(API + "/listar_egressos_por_curso/1");

        // Verificação
        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(
                        MockMvcResultMatchers.status().isOk())
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$[0].id").value(egressos.get(0).getId()))
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$[0].nome").value(egressos.get(0).getNome()))
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$[0].email").value(egressos.get(0).getEmail()))
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$[0].cpf").value(egressos.get(0).getEmail()))
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$[0].resumo").value(egressos.get(0).getResumo()))
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$[0].urlfoto").value(egressos.get(0).getUrlFoto()));
    }

    @Test
    public void deveListarQuantidadeDeEgressosPorCurso() throws Exception {
        // Cenário
        EgressoDTO dto = EgressoDTO.builder()
                .nome("Nome")
                .email("example@e.com")
                .cpf("123456789")
                .resumo("Resumo")
                .urlFoto("urlFoto")
                .cursos(
                        Arrays.asList(
                                CursoDTO.builder()
                                        .id(1L)
                                        .nome("Nome Curso")
                                        .nivel("Nivel Curso")
                                        .build(),
                                CursoDTO.builder()
                                        .id(2L)
                                        .nome("Nome Curso 2")
                                        .nivel("Nivel Curso 2")
                                        .build()))
                .build();
                
        Egresso egresso = Egresso.builder()
                .id(1l)
                .nome(dto.getNome())
                .email(dto.getEmail())
                .cpf(dto.getCpf())
                .resumo(dto.getResumo())
                .urlFoto(dto.getUrlFoto())
                .build();

        List<Curso> cursos = Arrays.asList(
                Curso.builder()
                        .id(1L)
                        .nome("Nome Curso")
                        .nivel("Nivel Curso")
                        .build(),
                Curso.builder()
                        .id(2L)
                        .nome("Nome Curso 2")
                        .nivel("Nivel Curso 2")
                        .build());
        
        List<CursoEgresso> cursoEgressos = Arrays.asList(
                CursoEgresso.builder()
                                .curso(cursos.get(0))
                                .egresso(egresso)
                                .data_inicio(LocalDate.now())
                                .data_conclusao(LocalDate.now())
                                .build(),
                CursoEgresso.builder()
                                .curso(cursos.get(1))
                                .egresso(egresso)
                                .data_inicio(LocalDate.now())
                                .data_conclusao(LocalDate.now())
                                .build()
        );
        
        int quantidade_egressos_curso = 1;

        Mockito.when(service.listarQuantidadeDeEgressosPorCurso(Mockito.any(Curso.class))).thenReturn(quantidade_egressos_curso);

        // Ação
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(API + "/listar_quantidade_egressos_por_curso/1");

        // Verificação
        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(
                        MockMvcResultMatchers.status().isOk())
                .andExpect(
                        MockMvcResultMatchers
                                .jsonPath("$[0]").value(quantidade_egressos_curso));
                
    }
}