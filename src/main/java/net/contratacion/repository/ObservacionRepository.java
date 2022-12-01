package net.contratacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.contratacion.entity.Observacion;

public interface ObservacionRepository extends JpaRepository<Observacion, Integer> {

}
