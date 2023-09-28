package br.com.apihospital.hospitalapi;


import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.apihospital.hospitalapi.model.Cadastro;
import br.com.apihospital.hospitalapi.service.CadastroService;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;



@WebMvcTest
public class CadastroTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CadastroService cadastroService;

    @Autowired
    private ObjectMapper objectMapper;



    @Test
    public void dadoOObjetoCadastro_quandoCriarOCadastro_entaoRetornarOCadastroSalvo() throws Exception{


        // dado - configuração
        Cadastro cadastro = Cadastro.builder()
                .id(1)
                .cpf("12345678910")
                .nomeCompleto("Juan Carlos Prevost Pereira Pinto")
                .idade("18 anos")
                .sexo("Masculino")
                .numeroDeTelefone("21 951017195")
                .endereco("Rua ......")
                .build();
        given(cadastroService.adicionar(any(Cadastro.class)))
                .willAnswer((invocation)-> invocation.getArguments()[0]);



        // quando - açaõ que vamos testar
        ResultActions resultActions = mockMvc.perform(post("/cadastro/cadastrarcliente")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cadastro)));


        // entao - verifica o resultado usando instruções assert
        resultActions.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id",
                        is(cadastro.getId())))
                .andExpect(jsonPath("$.cpf",
                        is(cadastro.getCpf())))
                .andExpect(jsonPath("$.nomeCompleto",
                        is(cadastro.getNomeCompleto())))
                .andExpect(jsonPath("$.idade",
                        is(cadastro.getIdade())))
                .andExpect(jsonPath("$.sexo",
                        is(cadastro.getSexo())))
                .andExpect(jsonPath("$.numeroDeTelefone",
                        is(cadastro.getNumeroDeTelefone())))
                .andExpect(jsonPath("$.endereco",
                        is(cadastro.getEndereco())));
    }


    //Teste JUnit para API REST Obter todos os funcionários
    @Test
    public void dadalistaDeCadastros_quandoObterTodosOsCadastros_entaoRetornarAListaDeCadastros() throws Exception{


        // dada - configuração
        List<Cadastro> cadastroList = new ArrayList<>();
        cadastroList.add(Cadastro.builder()
                .id(1)
                .cpf("12345678910")
                .nomeCompleto("Juan Carlos Prevost Pereira Pinto")
                .idade("18 anos")
                .sexo("Masculino")
                .numeroDeTelefone("21 951017195")
                .endereco("Rua......").build());
                given(cadastroService.listarTodos()).willReturn(cadastroList);


        // quando - ação que vamos testar
        ResultActions resultActions = mockMvc.perform(get("/cadastro/listartodos"));


        // entao - verifique a saída
        resultActions.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(cadastroList.size())));

    }

    // cenário positivo - identificação de cadastro válido
    // Teste JUnit para pegar cadastro por ID REST API
    @Test
    public void dadoOIdDoCadastro_quandoObterOCadastroPorId_entaoRetornarObjetoCadastro() throws Exception{


        // dado - configuração
        Integer cadastroId = 1;
        Cadastro cadastro = Cadastro.builder()
                .id(1)
                .cpf("12345678910")
                .nomeCompleto("Juan Carlos Prevost Pereira Pinto")
                .idade("18 anos")
                .sexo("Masculino")
                .numeroDeTelefone("21 951017195")
                .endereco("Rua......")
                .build();
        given(cadastroService.buscarPorId(cadastroId)).willReturn(cadastro);


        // quando - ação que vamos testar
        ResultActions resultActions = mockMvc.perform(get("/cadastro/listar/{id}", cadastroId));


        // entao - verifique a saída
        resultActions.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id",
                        is(cadastro.getId())))
                .andExpect(jsonPath("$.cpf",
                        is(cadastro.getCpf())))
                .andExpect(jsonPath("$.nomeCompleto",
                        is(cadastro.getNomeCompleto())))
                .andExpect(jsonPath("$.idade",
                        is(cadastro.getIdade())))
                .andExpect(jsonPath("$.sexo",
                        is(cadastro.getSexo())))
                .andExpect(jsonPath("$.numeroDeTelefone",
                        is(cadastro.getNumeroDeTelefone())))
                .andExpect(jsonPath("$.endereco",
                        is(cadastro.getEndereco())));

    }

    // cenário negativo - identificação de cadastro válido
    // Teste JUnit para pegar cadastro por ID REST API
    @Test
    public void dadoOCadastroInvalido_quandoObterOCadastroPorId_entaoRetornarObjetoCadastro() throws Exception{


        // dado - configuração
        Integer cadastroId = 1;
        Cadastro cadastro = Cadastro.builder()
                .id(1)
                .cpf("12345678910")
                .nomeCompleto("Juan Carlos Prevost Pereira Pinto")
                .idade("18 anos")
                .sexo("Masculino")
                .numeroDeTelefone("21 951017195")
                .endereco("Rua......")
                .build();
        given(cadastroService.buscarPorId(cadastroId)).willReturn(cadastro);


        // quando - ação que vamos testar
        ResultActions resultActions = mockMvc.perform(get("/cadastro/listar/{id}", cadastroId));


        // entao - verifique a saída
        resultActions.andExpect(status().isNotFound())
                .andDo(print());

    }

    // Teste JUnit para atualização da API REST do cadastro - cenário positivo
    @Test
    public void dadoAtualizacaoDoCadastro_quandoObterAtualizacaoDoCadastro_entaoRetornarAtualizacaoDoCadastro() throws Exception{


        // dado - configuração
        Integer cadastroId = 1;
        Cadastro cadastro = Cadastro.builder()
                .id(1)
                .cpf("12345678910")
                .nomeCompleto("Juan Carlos Prevost Pereira Pinto")
                .idade("18 anos")
                .sexo("Masculino")
                .numeroDeTelefone("21 951017195")
                .endereco("Rua......")
                .build();


        Cadastro atualizarCadastro = Cadastro.builder()
                .id(1)
                .cpf("12345678911")
                .nomeCompleto("Juan Carlos Prevost Pereira")
                .idade("19 anos")
                .sexo("Masculino")
                .numeroDeTelefone("21 951017195")
                .endereco("Rua......")
                .build();
        given(cadastroService.buscarPorId(cadastroId)).willReturn(cadastro);
        given(cadastroService.atualizar(cadastro,anyInt()))
                .willAnswer((invocation)-> invocation.getArguments()[0]);


        // quando - ação que vamos testar
        ResultActions resultActions = mockMvc.perform(put("/cadastro/atualizar/{id}",cadastroId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(atualizarCadastro)));


        // entao - verificar saída
        resultActions.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id",
                        is(cadastro.getId())))
                .andExpect(jsonPath("$.cpf",
                        is(cadastro.getCpf())))
                .andExpect(jsonPath("$.nomeCompleto",
                        is(cadastro.getNomeCompleto())))
                .andExpect(jsonPath("$.idade",
                        is(cadastro.getIdade())))
                .andExpect(jsonPath("$.sexo",
                        is(cadastro.getSexo())))
                .andExpect(jsonPath("$.numeroDeTelefone",
                        is(cadastro.getNumeroDeTelefone())))
                .andExpect(jsonPath("$.endereco",
                        is(cadastro.getEndereco())));

    }

    // Teste JUnit para atualização da API REST do cadastro - cenário negativo
    @Test
    public void dadoAtualizacaoDoCadastro_quandoObterAtualizacaoDoCadastro_entaoSeguidaRetornar404() throws Exception {


        // dado - configuração
        Integer cadastroId = 1;
        Cadastro cadastro = Cadastro.builder()
                .id(1)
                .cpf("12345678910")
                .nomeCompleto("Juan Carlos Prevost Pereira Pinto")
                .idade("18 anos")
                .sexo("Masculino")
                .numeroDeTelefone("21 951017195")
                .endereco("Rua......")
                .build();


        Cadastro atualizarCadastro = Cadastro.builder()
                .id(1)
                .cpf("12345678911")
                .nomeCompleto("Juan Carlos Prevost Pereira")
                .idade("19 anos")
                .sexo("Masculino")
                .numeroDeTelefone("21 951017195")
                .endereco("Rua......")
                .build();
        given(cadastroService.buscarPorId(cadastroId)).willReturn(null);
        given(cadastroService.atualizar(cadastro,anyInt()))
                .willAnswer((invocation)-> invocation.getArguments()[0]);

        // quando - ação que vamos testar
        ResultActions resultActions = mockMvc.perform(put("/cadastro/atualizar/{id}", cadastroId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(atualizarCadastro)));


        // entao - verificar saída
        resultActions.andExpect(status().isNotFound())
                .andDo(print());

    }

    // Teste JUnit para excluir API REST de cadastro
    @Test
    public void dadoOCadastroId_quandoDeletarOCadastro_entaoSeguidaRetornar200() throws Exception{


        // dada - configuração
        Integer cadastroId = 1;
        willDoNothing().given(cadastroService).deletar(cadastroId);


        // quando - ação que vamos testar
        ResultActions resultActions = mockMvc.perform(delete("/cadastro/deletar/{id}",cadastroId));


        // entao - verificar saída
        resultActions.andExpect(status().isOk())
                .andDo(print());

    }
}