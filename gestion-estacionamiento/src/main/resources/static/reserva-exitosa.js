window.addEventListener('load', function() {
    // Verificar autenticación
    const token = localStorage.getItem('authToken');
    if (!token) {
        window.location.href = 'index.html';
        return;
    }

    // Obtener datos de la reserva
    const reservaData = localStorage.getItem('reservaExitosa');
    if (!reservaData) {
        // Si no hay datos, redirigir a reservas
        window.location.href = 'reservas.html';
        return;
    }

    try {
        const reserva = JSON.parse(reservaData);
        mostrarDetallesReserva(reserva);
        
        // Limpiar datos del localStorage después de mostrarlos
        localStorage.removeItem('reservaExitosa');
    } catch (error) {
        console.error('Error al parsear datos de reserva:', error);
        window.location.href = 'reservas.html';
    }
});

function mostrarDetallesReserva(reserva) {
    document.getElementById('loading').style.display = 'none';
    document.getElementById('reservaDetails').style.display = 'block';

    // Llenar datos de la reserva
    document.getElementById('idReserva').textContent = reserva.idReserva || '-';
    document.getElementById('fechaCreacion').textContent = formatearFecha(reserva.fechaCreacionReserva) || '-';
    document.getElementById('fechaReserva').textContent = formatearFecha(reserva.fechaReserva) || '-';
    document.getElementById('horaInicio').textContent = formatearHora(reserva.horaInicio) || '-';
    document.getElementById('horaFin').textContent = formatearHora(reserva.horaFin) || '-';
    document.getElementById('nroEstacionamiento').textContent = 
        reserva.estacionamiento ? `Estacionamiento ${reserva.estacionamiento.nroEstacionamiento}` : '-';

    // Llenar datos del usuario
    if (reserva.usuario) {
        const usuario = reserva.usuario;
        document.getElementById('rutUsuario').textContent = 
            `${usuario.rut}-${usuario.dv}` || '-';
        
        const nombreCompleto = [
            usuario.primerNombre,
            usuario.segundoNombre,
            usuario.apPaterno,
            usuario.apMaterno
        ].filter(nombre => nombre).join(' ');
        
        document.getElementById('nombreCompleto').textContent = nombreCompleto || '-';
        document.getElementById('correoUsuario').textContent = usuario.correo || '-';
        document.getElementById('celularUsuario').textContent = usuario.nroCelular || '-';
    }
}

function formatearFecha(fecha) {
    if (!fecha) return '-';
    try {
        const date = new Date(fecha + 'T00:00:00');
        return date.toLocaleDateString('es-CL', {
            year: 'numeric',
            month: 'long',
            day: 'numeric'
        });
    } catch (error) {
        return fecha;
    }
}

function formatearHora(hora) {
    if (!hora) return '-';
    try {
        // Si la hora viene en formato HH:mm:ss, tomar solo HH:mm
        return hora.substring(0, 5);
    } catch (error) {
        return hora;
    }
}

function logout() {
    localStorage.removeItem('authToken');
    localStorage.removeItem('reservaExitosa');
    window.location.href = 'index.html';
}