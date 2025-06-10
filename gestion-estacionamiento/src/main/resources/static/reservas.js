// Lógica específica de la página de reservas
document.addEventListener('DOMContentLoaded', function() {
    // Verificar autenticación
    if (!AuthService.requireAuth()) {
        return;
    }
    
    // Configurar fecha mínima
    const fechaInput = document.getElementById('fechaReserva');
    fechaInput.min = ReservaService.getFechaMinima();
    
    // Configurar eventos
    setupReservaForm();
    UIUtils.setupClearMessagesOnInput();
});

function setupReservaForm() {
    const reservaForm = document.getElementById('reservaForm');
    
    reservaForm.addEventListener('submit', async function(e) {
        e.preventDefault();
        
        try {
            const formData = FormHandler.getFormData('reservaForm');
            
            // Validar datos básicos
            FormHandler.validateForm(formData);
            
            // Validar horarios
            ReservaService.validarH