package cl.alcoholicos.gestorestacionamiento.zona.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.alcoholicos.gestorestacionamiento.zona.entity.ZonaEntity;

@Repository
public interface ZonaRepository extends JpaRepository<ZonaEntity, Character>{

}
