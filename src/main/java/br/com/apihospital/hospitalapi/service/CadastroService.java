package br.com.apihospital.hospitalapi.service;

import br.com.apihospital.hospitalapi.model.Cadastro;
import br.com.apihospital.hospitalapi.repository.CadastroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CadastroService {

    @Autowired
    private CadastroRepository cadastroRepository;

    public void adicionar(Cadastro cadastro) {
        cadastroRepository.save(cadastro);
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

    public void atualizar(Cadastro cadastro, Integer id) {
        cadastroRepository.existsById(id);
        cadastroRepository.save(cadastro);
    }

    public void deletar(Integer id) {
        cadastroRepository.deleteById(id);
    }
}
