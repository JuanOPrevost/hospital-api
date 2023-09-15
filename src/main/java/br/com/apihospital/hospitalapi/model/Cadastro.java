package br.com.apihospital.hospitalapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "tb_cliente")
public class Cadastro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 11)
    private String cpf;
    private String nomeCompleto;
    private String idade;
    private String sexo;
    private String endereco;
    private String numeroDeTelefone;
    @ManyToOne
    private Hospital hospital;
}
