package cl.alcoholicos.gestorestacionamiento.zona.service;

import java.util.List;

import cl.alcoholicos.gestorestacionamiento.zona.entity.ZonaEntity;

public interface IZona {

    ZonaEntity insert(ZonaEntity zona);

    ZonaEntity update(Character idZona, ZonaEntity zona);

    ZonaEntity delete(Character idZona);

    ZonaEntity getById(Character idZona);

    List<ZonaEntity> getAll();

}
