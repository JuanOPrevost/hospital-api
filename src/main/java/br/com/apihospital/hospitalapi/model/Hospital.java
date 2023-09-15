package br.com.apihospital.hospitalapi.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity(name = "tb_Hospital")
public class Hospital {

    @Id
    private String cnpj;
    private String nome;
    private String endereco;

}
