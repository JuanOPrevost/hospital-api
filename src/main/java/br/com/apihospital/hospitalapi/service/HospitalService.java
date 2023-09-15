package br.com.apihospital.hospitalapi.service;

import br.com.apihospital.hospitalapi.model.Hospital;
import br.com.apihospital.hospitalapi.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    public void adicionar(Hospital hospital) {
        hospitalRepository.save(hospital);
    }

    public Hospital buscarPorCnpj(String cnpj) {
        Optional<Hospital> optionalHospital = hospitalRepository.findById(cnpj);
        if (optionalHospital.isEmpty()) {
            return null;
        }
        return optionalHospital.get();
    }

    public void atualizar(Hospital hospital , String cnpj) {
        hospitalRepository.existsById(cnpj);
        hospitalRepository.save(hospital);
    }

    public void deletar(String cnpj) {
        hospitalRepository.deleteById(cnpj);
    }
}
