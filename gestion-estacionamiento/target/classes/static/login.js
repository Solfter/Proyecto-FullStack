// Lógica específica de la página de login
document.addEventListener('DOMContentLoaded', function() {
    // Verificar si ya está autenticado
    AuthService.redirectIfAuthenticated();
    
    // Configurar eventos
    setupLoginForm();
    UIUtils.setupClearMessagesOnInput();
});

function setupLoginForm() {
    const loginForm = document.getElementById('loginForm');
    
    loginForm.addEventListener('submit', async function(e) {
        e.preventDefault();
        
        try {
            const formData = FormHandler.getFormData('loginForm');
            
            // Validar datos básicos
            FormHandler.validateForm(formData);
            
            // Mostrar estado de carga
            FormHandler.setLoadingState('loginForm', 'loginBtn', true);
            UIUtils.clearMessages();
            
            // Intentar login
            await AuthService.login(formData.email, formData.password);
            
            // Redirigir en caso de éxito
            window.location.href = 'reservas.html';
            
        } catch (error) {
            // Manejar errores
            FormHandler.setLoadingState('loginForm', 'loginBtn', false);
            UIUtils.showError(error.message || 'Error de conexión. Por favor, intenta nuevamente.');
            console.error('Error en login:', error);
        }
    });
}