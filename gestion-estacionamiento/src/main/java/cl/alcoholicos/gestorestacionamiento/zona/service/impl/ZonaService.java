package cl.alcoholicos.gestorestacionamiento.zona.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import cl.alcoholicos.gestorestacionamiento.zona.dto.ZonaCreateDTO;
import cl.alcoholicos.gestorestacionamiento.zona.dto.ZonaResponseDTO;
import cl.alcoholicos.gestorestacionamiento.zona.dto.ZonaUpdateDTO;
import cl.alcoholicos.gestorestacionamiento.zona.entity.ZonaEntity;
import cl.alcoholicos.gestorestacionamiento.zona.mapper.ZonaMapper;
import cl.alcoholicos.gestorestacionamiento.zona.repository.ZonaRepository;
import cl.alcoholicos.gestorestacionamiento.zona.service.IZona;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ZonaService implements IZona {

    private final ZonaRepository zonaRepository;
    private final ZonaMapper zonaMapper;

    @Override
    public ZonaResponseDTO insert(ZonaCreateDTO zonaCreateDTO) {
        ZonaEntity zona = zonaMapper.toEntity(zonaCreateDTO);
        ZonaEntity zonaGuardada = zonaRepository.save(zona);
        ZonaResponseDTO responseDTO = zonaMapper.toResponseDTO(zonaGuardada);
        return responseDTO;
    }

    @Override
    public ZonaResponseDTO update(String idZona, ZonaUpdateDTO zonaUpdateDTO) {
        return zonaRepository.findById(idZona)
                .map(zonaExistente -> {
                    zonaMapper.updateFromUpdateDTO(zonaUpdateDTO, zonaExistente);
                    ZonaEntity zonaActuailizada = zonaRepository.save(zonaExistente);
                    return zonaMapper.toResponseDTO(zonaActuailizada);
                })
                .orElse(null);
    }

    @Override
    public boolean delete(String idZona) {
        if (zonaRepository.existsById(idZona)) {
            zonaRepository.deleteById(idZona);
            return true;
        }
        return false;
    }

    @Override
    public ZonaResponseDTO getById(String idZona) {
        return zonaRepository.findById(idZona)
            .map(zonaMapper::toResponseDTO)
            .orElse(null);
    }

    @Override
    public List<ZonaResponseDTO> getAll() {
        return zonaRepository.findAll().stream()
                .map(zonaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

}
