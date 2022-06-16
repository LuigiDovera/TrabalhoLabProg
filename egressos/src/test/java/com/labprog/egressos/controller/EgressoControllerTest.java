package com.labprog.egressos.controller;

import static org.mockito.ArgumentMatchers.any;

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
import com.labprog.egressos.model.Contato;
import com.labprog.egressos.model.Depoimento;
import com.labprog.egressos.model.Egresso;
import com.labprog.egressos.model.dto.ContatoDTO;
import com.labprog.egressos.model.dto.DepoimentoDTO;
import com.labprog.egressos.model.dto.EgressoDTO;
import com.labprog.egressos.service.EgressoService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = EgressoController.class)
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

        Egresso retornoServ = Egresso.builder()
                .id(1l)
                .nome(dto.getNome())
                .email(dto.getEmail())
                .cpf(dto.getCpf())
                .resumo(dto.getResumo())
                .urlFoto(dto.getUrlFoto())
                .build();

        Mockito.when(service.salvar(
                Mockito.any(Egresso.class))).thenReturn(retornoServ);

        String json = new ObjectMapper().writeValueAsString(dto);

        // Ação
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(API + "/salvar")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        // Verificação
        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.id").value(retornoServ.getId()))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.nome").value(retornoServ.getNome()))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.email").value(retornoServ.getEmail()))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.cpf").value(retornoServ.getCpf()))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.resumo").value(retornoServ.getResumo()))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.urlFoto").value(retornoServ.getUrlFoto()));
    }

    @Test
    public void deveAtualizarEgresso() throws Exception {
        // Cenário
        EgressoDTO dto = EgressoDTO.builder()
                .nome("Nome")
                .email("example@e.com")
                .cpf("123456789")
                .resumo("Resumo")
                .urlFoto("urlFoto")
                .build();

        Egresso retornoServ = Egresso.builder()
                .id(1l)
                .nome(dto.getNome())
                .email(dto.getEmail())
                .cpf(dto.getCpf())
                .resumo(dto.getResumo())
                .urlFoto(dto.getUrlFoto())
                .build();

        Mockito.when(service.atualizar(
                Mockito.any(Egresso.class))).thenReturn(retornoServ);

        String json = new ObjectMapper().writeValueAsString(dto);

        // Ação
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put(API + "/atualizar/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        // Verificação
        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.id").value(retornoServ.getId()))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.nome").value(retornoServ.getNome()))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.email").value(retornoServ.getEmail()))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.cpf").value(retornoServ.getCpf()))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.resumo").value(retornoServ.getResumo()))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.urlFoto").value(retornoServ.getUrlFoto()));
    }

    @Test
    public void deveAtualizarContatosDoEgresso() throws Exception {
        // Cenário
        EgressoDTO dto = EgressoDTO.builder()
                .nome("Nome")
                .email("example@e.com")
                .cpf("123456789")
                .resumo("Resumo")
                .urlFoto("urlFoto")
                .contatos(
                        Arrays.asList(
                                ContatoDTO.builder()
                                        .id(1L)
                                        .nome("Contato")
                                        .urlLogo("url_logo")
                                        .build(),
                                ContatoDTO.builder()
                                        .id(2L)
                                        .nome("Contato2")
                                        .urlLogo("url_logo2")
                                        .build()))
                .build();
        Egresso retornoServ = Egresso.builder()
                .id(1l)
                .nome(dto.getNome())
                .email(dto.getEmail())
                .cpf(dto.getCpf())
                .resumo(dto.getResumo())
                .urlFoto(dto.getUrlFoto())
                .contatos(
                        Arrays.asList(
                                Contato.builder()
                                        .id(dto.getContatos().get(0).getId())
                                        .nome(dto.getContatos().get(0).getNome())
                                        .urlLogo(dto.getContatos().get(0).getUrlLogo())
                                        .build(),
                                Contato.builder()
                                        .id(dto.getContatos().get(1).getId())
                                        .nome(dto.getContatos().get(1).getNome())
                                        .urlLogo(dto.getContatos().get(1).getUrlLogo())
                                        .build()))
                .build();

        Mockito.when(service.atualizarContatos(
                Mockito.any(Egresso.class),
                Mockito.anyList())).thenReturn(retornoServ);

        String json = new ObjectMapper().writeValueAsString(dto);

        // Ação
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put(API + "/atualizar_contatos/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        // Verificação
        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.id").value(retornoServ.getId()))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.nome").value(retornoServ.getNome()))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.email").value(retornoServ.getEmail()))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.cpf").value(retornoServ.getCpf()))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.resumo").value(retornoServ.getResumo()))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.urlFoto").value(retornoServ.getUrlFoto()))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.contatos[0].id").value(retornoServ.getContatos().get(0).getId()))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.contatos[0].nome").value(retornoServ.getContatos().get(0).getNome()))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.contatos[0].urlLogo").value(retornoServ.getContatos().get(0).getUrlLogo()))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.contatos[1].id").value(retornoServ.getContatos().get(1).getId()))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.contatos[1].nome").value(retornoServ.getContatos().get(1).getNome()))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.contatos[1].urlLogo").value(retornoServ.getContatos().get(1).getUrlLogo()));
    }

    @Test
    public void deveAtualizarDepoimentosDoEgresso() throws Exception {
        // Cenário
        EgressoDTO dto = EgressoDTO.builder()
                .nome("Nome")
                .email("example@e.com")
                .cpf("123456789")
                .resumo("Resumo")
                .urlFoto("urlFoto")
                .depoimentos(
                        Arrays.asList(
                                DepoimentoDTO.builder()
                                        .id(1L)
                                        .texto("Depoimento")
                                        .data(LocalDate.now())
                                        .build(),
                                DepoimentoDTO.builder()
                                        .id(2L)
                                        .texto("Depoimento2")
                                        .data(LocalDate.now().plusDays(1))
                                        .build()))
                .build();

        Egresso retornoServ = Egresso.builder()
                .id(1l)
                .nome(dto.getNome())
                .email(dto.getEmail())
                .cpf(dto.getCpf())
                .resumo(dto.getResumo())
                .urlFoto(dto.getUrlFoto())
                .depoimentos(
                        Arrays.asList(
                                Depoimento.builder()
                                        .id(dto.getDepoimentos().get(0).getId())
                                        .texto(dto.getDepoimentos().get(0).getTexto())
                                        .data(dto.getDepoimentos().get(0).getData())
                                        .build(),
                                Depoimento.builder()
                                        .id(dto.getDepoimentos().get(1).getId())
                                        .texto(dto.getDepoimentos().get(1).getTexto())
                                        .data(dto.getDepoimentos().get(1).getData())
                                        .build()))
                .build();

        Mockito.when(service.atualizarDepoimentos(
                Mockito.any(Egresso.class),
                Mockito.anyList())).thenReturn(retornoServ);

        String json = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .writeValueAsString(dto);

        // Ação
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put(API + "/atualizar_depoimentos/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        // Verificação
        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.id").value(retornoServ.getId()))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.nome").value(retornoServ.getNome()))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.email").value(retornoServ.getEmail()))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.cpf").value(retornoServ.getCpf()))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.resumo").value(retornoServ.getResumo()))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.urlFoto").value(retornoServ.getUrlFoto()))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.depoimentos[0].id").value(retornoServ.getDepoimentos().get(0).getId()))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.depoimentos[0].texto").value(retornoServ.getDepoimentos().get(0).getTexto()))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.depoimentos[0].data")
                        .value(retornoServ.getDepoimentos().get(0).getData().toString()))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.depoimentos[1].id").value(retornoServ.getDepoimentos().get(1).getId()))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.depoimentos[1].texto").value(retornoServ.getDepoimentos().get(1).getTexto()))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.depoimentos[1].data")
                        .value(retornoServ.getDepoimentos().get(1).getData().toString()));
    }

    @Test
    public void deveBuscarEgressos() throws Exception {
        // Cenário
        List<Egresso> retornoServLista = Arrays.asList(
                Egresso.builder()
                        .id(1l)
                        .nome("Nome")
                        .email("example@e.com")
                        .cpf("123456789")
                        .resumo("Resumo")
                        .urlFoto("urlFoto")
                        .build(),
                Egresso.builder()
                        .id(2l)
                        .nome("Nome2")
                        .email("example2@e.com")
                        .cpf("1234567892")
                        .resumo("Resumo2")
                        .urlFoto("urlFoto2")
                        .build());

        Mockito.when(service.buscar()).thenReturn(retornoServLista);

        // Ação
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(API + "/buscar");

        // Verificação
        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$[0].id").value(retornoServLista.get(0).getId()))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$[0].nome").value(retornoServLista.get(0).getNome()))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$[0].email").value(retornoServLista.get(0).getEmail()))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$[0].cpf").value(retornoServLista.get(0).getCpf()))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$[0].resumo").value(retornoServLista.get(0).getResumo()))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$[0].urlFoto").value(retornoServLista.get(0).getUrlFoto()))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$[1].id").value(retornoServLista.get(1).getId()))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$[1].nome").value(retornoServLista.get(1).getNome()))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$[1].email").value(retornoServLista.get(1).getEmail()))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$[1].cpf").value(retornoServLista.get(1).getCpf()))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$[1].resumo").value(retornoServLista.get(1).getResumo()))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$[1].urlFoto").value(retornoServLista.get(1).getUrlFoto()));
    }

    @Test
    public void deveRemoverEgresso() throws Exception {
        // Cenário
        Mockito.doNothing().when(service).remover(Mockito.any(Egresso.class));

        // Ação
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete(API + "/remover/1");

        // Verificação
        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isNoContent());
        Mockito.verify(service, Mockito.times(1)).remover(Mockito.any(Egresso.class));
    }

    @Test
    public void deveBuscarEgressoPorEmail() throws Exception {
        // Cenário
        List<Egresso> retornoServList = Arrays.asList( 
            Egresso.builder()
                .id(1l)
                .nome("Nome")
                .email("example@e.com")
                .cpf("123456789")
                .resumo("Resumo")
                .urlFoto("urlFoto")
                .build()
        );

        Mockito.when(service.buscar(any(Egresso.class))).thenReturn(retornoServList);

        // Ação
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(API + "/buscar/example@e.com");

        // Verificação
        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.id").value(retornoServList.get(0).getId()))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.nome").value(retornoServList.get(0).getNome()))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.email").value(retornoServList.get(0).getEmail()))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.cpf").value(retornoServList.get(0).getCpf()))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.resumo").value(retornoServList.get(0).getResumo()))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.urlFoto").value(retornoServList.get(0).getUrlFoto()));
    }

}