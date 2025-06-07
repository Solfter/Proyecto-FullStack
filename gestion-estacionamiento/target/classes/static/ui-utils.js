// Utilidades para manejo de la interfaz de usuario
class UIUtils {
    
    // Mostrar mensaje de éxito
    static showSuccess(message, elementId = 'successMessage') {
        const element = document.getElementById(elementId);
        if (element) {
            element.textContent = message;
            element.style.display = 'block';
            
            setTimeout(() => {
                element.style.display = 'none';
            }, 5000);
        }
    }

    // Mostrar mensaje de error
    static showError(message, elementId = 'errorMessage') {
        const element = document.getElementById(elementId);
        if (element) {
            element.textContent = message;
            element.style.display = 'block';
            
            setTimeout(() => {
                element.style.display = 'none';
            }, 5000);
        }
    }

    // Mostrar/ocultar loading
    static showLoading(show = true, elementId = 'loading') {
        const element = document.getElementById(elementId);
        if (element) {
            element.style.display = show ? 'block' : 'none';
        }
    }

    // Habilitar/deshabilitar botón
    static toggleButton(buttonId, disabled = false) {
        const button = document.getElementById(buttonId);
        if (button) {
            button.disabled = disabled;
        }
    }

    // Limpiar mensajes
    static clearMessages() {
        this.hideElement('successMessage');
        this.hideElement('errorMessage');
    }

    // Ocultar elemento
    static hideElement(elementId) {
        const element = document.getElementById(elementId);
        if (element) {
            element.style.display = 'none';
        }
    }

    // Mostrar elemento
    static showElement(elementId) {
        const element = document.getElementById(elementId);
        if (element) {
            element.style.display = 'block';
        }
    }

    // Formatear fecha
    static formatearFecha(fecha) {
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

    // Formatear hora
    static formatearHora(hora) {
        if (!hora) return '-';
        try {
            return hora.substring(0, 5);
        } catch (error) {
            return hora;
        }
    }

    // Configurar listeners para limpiar mensajes al escribir
    static setupClearMessagesOnInput() {
        document.querySelectorAll('input, select').forEach(input => {
            input.addEventListener('input', () => {
                this.clearMessages();
            });
        });
    }
}

// Clase para manejo de formularios
class FormHandler {
    
    // Manejar estado de loading en formularios
    static setLoadingState(formId, buttonId, loading = true) {
        UIUtils.showLoading(loading);
        UIUtils.toggleButton(buttonId, loading);
    }

    // Validar formulario básico
    static validateForm(formData) {
        for (const [key, value] of Object.entries(formData)) {
            if (!value || value.trim() === '') {
                throw new Error(`El campo ${key} es requerido`);
            }
        }
        return true;
    }

    // Obtener datos del formulario
    static getFormData(formId) {
        const form = document.getElementById(formId);
        const formData = new FormData(form);
        const data = {};
        
        for (const [key, value] of formData.entries()) {
            data[key] = value;
        }
        
        return data;
    }
}