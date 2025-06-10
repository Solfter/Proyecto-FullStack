// Verificar si el usuario está autenticado
window.addEventListener('load', function() {
    const token = localStorage.getItem('authToken');
    if (!token) {
        // Si no hay token, redirigir al login
        window.location.href = 'index.html';
    }
    
    // Establecer fecha mínima como hoy
    const today = new Date().toISOString().split('T')[0];
    document.getElementById('fechaReserva').min = today;
});

document.getElementById('reservaForm').addEventListener('submit', async function(e) {
    e.preventDefault();
    
    const fechaReserva = document.getElementById('fechaReserva').value;
    const horaInicio = document.getElementById('horaInicio').value;
    const horaFin = document.getElementById('horaFin').value;
    const estacionamiento = document.getElementById('estacionamiento').value;
    
    const successDiv = document.getElementById('successMessage');
    const errorDiv = document.getElementById('errorMessage');
    const loadingDiv = document.getElementById('loading');
    const crearBtn = document.getElementById('crearBtn');
    
    // Validar que la hora de fin sea posterior a la de inicio
    if (horaFin <= horaInicio) {
        showError('La hora de fin debe ser posterior a la hora de inicio.');
        return;
    }
    
    // Limpiar mensajes previos
    successDiv.style.display = 'none';
    errorDiv.style.display = 'none';
    
    // Mostrar loading
    loadingDiv.style.display = 'block';
    crearBtn.disabled = true;
    
    try {
        const token = localStorage.getItem('authToken');
        const response = await fetch('http://localhost:8001/reserva', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            },
            body: JSON.stringify({
                fechaReserva: fechaReserva,
                horaInicio: horaInicio,
                horaFin: horaFin,
                idEstacionamiento: parseInt(estacionamiento)
            })
        });
        
        loadingDiv.style.display = 'none';
        crearBtn.disabled = false;
        
        if (response.ok) {
            const data = await response.json();
            // Guardar datos de la reserva para mostrar en la página de éxito
            localStorage.setItem('reservaExitosa', JSON.stringify(data));
            // Redirigir a la página de éxito
            window.location.href = 'reserva-exitosa.html';
        } else {
            const errorData = await response.json();
            showError(errorData.message || 'Error al crear la reserva. Por favor, intenta nuevamente.');
        }
    } catch (error) {
        loadingDiv.style.display = 'none';
        crearBtn.disabled = false;
        showError('Error de conexión. Por favor, intenta nuevamente.');
        console.error('Error:', error);
    }
});

function showSuccess(message) {
    const successDiv = document.getElementById('successMessage');
    successDiv.textContent = message;
    successDiv.style.display = 'block';
    
    // Auto-hide después de 5 segundos
    setTimeout(() => {
        successDiv.style.display = 'none';
    }, 5000);
}

function showError(message) {
    const errorDiv = document.getElementById('errorMessage');
    errorDiv.textContent = message;
    errorDiv.style.display = 'block';
    
    // Auto-hide después de 5 segundos
    setTimeout(() => {
        errorDiv.style.display = 'none';
    }, 5000);
}

function logout() {
    localStorage.removeItem('authToken');
    window.location.href = 'index.html';
}

// Limpiar mensajes cuando el usuario empieza a escribir
document.querySelectorAll('input, select').forEach(input => {
    input.addEventListener('input', function() {
        document.getElementById('errorMessage').style.display = 'none';
        document.getElementById('successMessage').style.display = 'none';
    });
});