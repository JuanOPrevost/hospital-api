package br.com.apihospital.hospitalapi.controller;

import br.com.apihospital.hospitalapi.model.Hospital;
import br.com.apihospital.hospitalapi.service.CadastroService;
import br.com.apihospital.hospitalapi.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/hospital")
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private CadastroService cadastroService;

    @PostMapping(value = "/criarhospital")
    public ResponseEntity criarHospital(@RequestBody Hospital hospital) {
        try {
            hospitalService.adicionar(hospital);
        }catch (NullPointerException e) {
            return new ResponseEntity("Algo deu errado!" , HttpStatus.BAD_REQUEST);
        }
            return new ResponseEntity("Hospital criado com sucesso" , HttpStatus.CREATED);
    }

    @GetMapping(value = "/listar/{cnpj}")
    public ResponseEntity ListarPorCnpj(@PathVariable String cnpj) {
        try {
            return new ResponseEntity(hospitalService.buscarPorCnpj(cnpj) , HttpStatus.OK);
        }catch (NoSuchElementException e) {
            return new ResponseEntity("CNPJ não encontrado!" , HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/atualizar/{cnpj}")
    public ResponseEntity atualizarHospital(@PathVariable String cnpj , @RequestBody Hospital hospital) {
        try {
            hospitalService.atualizar(hospital, cnpj);
        }catch (NoSuchElementException e) {
            return new ResponseEntity("CNPJ não encontrado" , HttpStatus.NOT_FOUND);
        }
            return new ResponseEntity("Hospital atualizado com sucesso!" , HttpStatus.OK);
    }

    @DeleteMapping(value = "/deletar/{cnpj}")
    public ResponseEntity deletarHospital(@PathVariable String cnpj) {
        try {
            hospitalService.deletar(cnpj);
        }catch (NoSuchElementException e) {
            return new ResponseEntity("CNPJ não encontrado!" , HttpStatus.NOT_FOUND);
        }
            return new ResponseEntity("Hospital Deletado com sucesso!" , HttpStatus.OK);
    }
}
