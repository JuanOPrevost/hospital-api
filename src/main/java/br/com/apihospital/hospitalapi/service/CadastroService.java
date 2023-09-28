package br.com.apihospital.hospitalapi.service;

import br.com.apihospital.hospitalapi.model.Cadastro;
import br.com.apihospital.hospitalapi.repository.CadastroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.Optional;

@Service
public class CadastroService {

    @Autowired
    private CadastroRepository cadastroRepository;

    public Cadastro adicionar(Cadastro cadastro) {
        Optional<Cadastro> salvarCadastro = cadastroRepository.findById(cadastro.getId());
        if(salvarCadastro.isPresent()){
            throw new ResourceAccessException("O cadastro já existe com o Id fornecido:" + cadastro.getId());
        }
        return cadastroRepository.save(cadastro);
    }

    public List<Cadastro> listarTodos() {
        return cadastroRepository.findAll();
    }

    public Cadastro buscarPorId(Integer id) {
        Optional<Cadastro> optionalCadastro = cadastroRepository.findById(id);
            if (optionalCadastro.isEmpty()) {
                return null;
            }
            return optionalCadastro.get();
    }

    public Cadastro atualizar(Cadastro cadastro, Integer id) {
        cadastroRepository.existsById(id);
        return cadastroRepository.save(cadastro);
    }

    public void deletar(Integer id) {
        cadastroRepository.deleteById(id);
    }
}
