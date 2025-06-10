// Servicio para manejo de reservas
class ReservaService {
    static API_BASE_URL = 'http://localhost:8001';

    // Crear una nueva reserva
    static async crearReserva(fechaReserva, horaInicio, horaFin, idEstacionamiento) {
        const token = AuthService.getToken();
        
        if (!token) {
            throw new Error('Token de autenticación no encontrado');
        }

        const response = await fetch(`${this.API_BASE_URL}/reserva`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            },
            body: JSON.stringify({
                fechaReserva: fechaReserva,
                horaInicio: horaInicio,
                horaFin: horaFin,
                idEstacionamiento: parseInt(idEstacionamiento)
            })
        });

        if (!response.ok) {
            const errorData = await response.json();
            throw new Error(errorData.message || 'Error al crear la reserva');
        }

        return await response.json();
    }

    // Guardar datos de reserva exitosa
    static guardarReservaExitosa(reservaData) {
        localStorage.setItem('reservaExitosa', JSON.stringify(reservaData));
    }

    // Obtener datos de reserva exitosa
    static obtenerReservaExitosa() {
        const data = localStorage.getItem('reservaExitosa');
        return data ? JSON.parse(data) : null;
    }

    // Limpiar datos de reserva exitosa
    static limpiarReservaExitosa() {
        localStorage.removeItem('reservaExitosa');
    }

    // Validar horarios de reserva
    static validarHorarios(horaInicio, horaFin) {
        if (horaFin <= horaInicio) {
            throw new Error('La hora de fin debe ser posterior a la hora de inicio');
        }
        return true;
    }

    // Obtener fecha mínima para reservas (hoy)
    static getFechaMinima() {
        return new Date().toISOString().split('T')[0];
    }
}