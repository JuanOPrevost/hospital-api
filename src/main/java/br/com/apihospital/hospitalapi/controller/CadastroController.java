package br.com.apihospital.hospitalapi.controller;

import br.com.apihospital.hospitalapi.model.Cadastro;
import br.com.apihospital.hospitalapi.model.Hospital;
import br.com.apihospital.hospitalapi.service.CadastroService;
import br.com.apihospital.hospitalapi.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/cadastro")
public class CadastroController {

    @Autowired
    private CadastroService cadastroService;

    @Autowired
    private HospitalService hospitalService;

    @PostMapping(value = "/cadastrarcliente")
    public ResponseEntity cadastrarcadastro(@RequestBody Cadastro cadastro,
                                           @RequestParam String cnpj) {

        Hospital hospital = hospitalService.buscarPorCnpj(cnpj);
        cadastro.setHospital(hospital);

        try {
            cadastroService.adicionar(cadastro);
        }catch(NullPointerException e) {
            return new ResponseEntity("Algo deu errado!" , HttpStatus.BAD_REQUEST);
        }
            return new ResponseEntity("cadastro cadastrado com sucesso!" , HttpStatus.CREATED);
    }

    @GetMapping(value = "/listartodos")
    public ResponseEntity listarTodos() {
        try {
            return new ResponseEntity(cadastroService.listarTodos() , HttpStatus.OK);
        }catch (NoSuchElementException e) {
            return new ResponseEntity("Não há cadastros cadastrados" , HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/listar/{id}")
    public ResponseEntity ListarPorId(@PathVariable Integer id) {
        try {
            return new ResponseEntity(cadastroService.buscarPorId(id) , HttpStatus.OK);
        }catch (NoSuchElementException e) {
            return new ResponseEntity("Não foi possivel achar esse id!" , HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/atualizar/{id}")
    public ResponseEntity atualiarcadastro(@PathVariable Integer id , @RequestBody Cadastro cadastro) {
        try {
            cadastroService.atualizar(cadastro,id);
        }catch (NoSuchElementException e) {
            return new ResponseEntity("Não foi possivel achar esse id!" , HttpStatus.NOT_FOUND);
        }
            return new ResponseEntity("cadastro atualizado com sucesso!" , HttpStatus.OK);
    }

    @DeleteMapping(value = "/deletar/{id}")
    public ResponseEntity deletarcadastro(@PathVariable Integer id) {
        try {
            cadastroService.deletar(id);
        }catch (NoSuchElementException e) {
            return new ResponseEntity("Não foi possivel achar esse id!" , HttpStatus.NOT_FOUND);
        }
            return new ResponseEntity("cadastro deletado com sucesso!" , HttpStatus.OK);
    }
}
