package cl.alcoholicos.gestorestacionamiento.zona.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.alcoholicos.gestorestacionamiento.zona.entity.ZonaEntity;
import cl.alcoholicos.gestorestacionamiento.zona.repository.ZonaRepository;
import cl.alcoholicos.gestorestacionamiento.zona.service.IZona;

@Service
public class ZonaService implements IZona {

    @Autowired
    private ZonaRepository zonaRepository;

    @Override
    public ZonaEntity insert(ZonaEntity zona) {
        return zonaRepository.save(zona);
    }

    @Override
    public ZonaEntity update(Character idZona, ZonaEntity zona) {
        zona.setIdZona(idZona);
        return zonaRepository.save(zona);
    }

    @Override
    public ZonaEntity delete(Character idZona) {
        zonaRepository.deleteById(idZona);
        return null;
    }

    @Override
    public ZonaEntity getById(Character idZona) {
        return zonaRepository.findById(idZona).get();
    }

    @Override
    public List<ZonaEntity> getAll() {
        return (List<ZonaEntity>) zonaRepository.findAll();
    }

}
