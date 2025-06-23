package cl.alcoholicos.gestorestacionamiento.service;

import java.util.List;

import cl.alcoholicos.gestorestacionamiento.dto.ReservaCreateDTO;
import cl.alcoholicos.gestorestacionamiento.dto.ReservaResponseDTO;
import cl.alcoholicos.gestorestacionamiento.dto.ReservaUpdateDTO;

public interface IReserva {

    ReservaResponseDTO insert(ReservaCreateDTO reserva, Integer rutUsuario);

    ReservaResponseDTO update(Integer idReserva, ReservaUpdateDTO reservaUpdate, Integer rutUsuario);

    boolean delete(Integer idReserva);

    ReservaResponseDTO getByReservaId(Integer idReserva);

    List<ReservaResponseDTO> getAll();

    List<ReservaResponseDTO> getByUserId(Integer rutUsuario);

}
