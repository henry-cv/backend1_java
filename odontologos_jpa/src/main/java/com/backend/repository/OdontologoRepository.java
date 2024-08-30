package com.backend.repository;

import com.backend.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OdontologoRepository extends JpaRepository<Odontologo, Long> {
  Odontologo findByMatricula(int matricula);

  @Override
  Optional<Odontologo> findById(Long id);
}
