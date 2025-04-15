package cl.alcoholicos.gestorestacionamiento.zona.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.alcoholicos.gestorestacionamiento.zona.dto.ZonaDTO;
import cl.alcoholicos.gestorestacionamiento.zona.repository.ZonaRepository;
import cl.alcoholicos.gestorestacionamiento.zona.service.IZona;

@Service
public class ZonaService implements IZona {

    @Autowired
    private ZonaRepository zonaRepository;

    @Override
    public ZonaDTO insert(ZonaDTO zona) {
        return zonaRepository.save(zona);
    }

    @Override
    public ZonaDTO update(Character idZona, ZonaDTO zona) {
        zona.setIdZona(idZona);
        return zonaRepository.save(zona);
    }

    @Override
    public ZonaDTO delete(Character idZona) {
        zonaRepository.deleteById(idZona);
        return null;
    }

    @Override
    public ZonaDTO getById(Character idZona) {
        return zonaRepository.findById(idZona).get();
    }

    @Override
    public List<ZonaDTO> getAll() {
        return (List<ZonaDTO>) zonaRepository.findAll();
    }

}
