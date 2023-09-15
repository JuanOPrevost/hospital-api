package br.com.apihospital.hospitalapi.repository;

import br.com.apihospital.hospitalapi.model.Cadastro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CadastroRepository extends JpaRepository<Cadastro, Integer>{
}
