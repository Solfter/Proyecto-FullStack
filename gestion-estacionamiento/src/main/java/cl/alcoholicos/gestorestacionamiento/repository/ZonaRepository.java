package cl.alcoholicos.gestorestacionamiento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.alcoholicos.gestorestacionamiento.entity.ZonaEntity;

@Repository
public interface ZonaRepository extends JpaRepository<ZonaEntity, String>{

}
