package br.com.apihospital.hospitalapi.repository;

import br.com.apihospital.hospitalapi.model.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital , String> {
}
