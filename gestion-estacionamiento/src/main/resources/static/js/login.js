// Configuración de la API
const API_CONFIG = {
    BASE_URL: 'http://localhost:8001',
    ENDPOINTS: {
        LOGIN: '/auth/login',
        USER: '/auth/user'
    }
};

// Clase para manejar la autenticación
class AuthService {
    constructor() {
        this.baseUrl = API_CONFIG.BASE_URL;
    }

    async login(email, password) {
        try {
            const response = await fetch(`${this.baseUrl}${API_CONFIG.ENDPOINTS.LOGIN}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    correo: email,
                    password: password
                })
            });

            if (!response.ok) {
                const errorData = await response.json();
                throw new Error(errorData.message || 'Credenciales incorrectas');
            }

            return await response.json();
        } catch (error) {
            throw error;
        }
    }

    async getUserInfo(token) {
        try {
            const response = await fetch(`${this.baseUrl}${API_CONFIG.ENDPOINTS.USER}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`
                }
            });

            if (!response.ok) {
                throw new Error('Error al obtener información del usuario');
            }

            return await response.json();
        } catch (error) {
            throw error;
        }
    }

    saveToken(token) {
        localStorage.setItem('authToken', token);
    }

    getToken() {
        return localStorage.getItem('authToken');
    }

    clearToken() {
        localStorage.removeItem('authToken');
    }
}

// Clase para manejar la UI del login
class LoginUI {
    constructor() {
        this.authService = new AuthService();
        this.initializeElements();
        this.bindEvents();
    }

    initializeElements() {
        this.form = document.getElementById('loginForm');
        this.emailInput = document.getElementById('email');
        this.passwordInput = document.getElementById('password');
        this.errorDiv = document.getElementById('errorMessage');
        this.loadingDiv = document.getElementById('loading');
        this.loginBtn = document.getElementById('loginBtn');
    }

    bindEvents() {
        this.form.addEventListener('submit', (e) => this.handleSubmit(e));
        this.emailInput.addEventListener('input', () => this.clearError());
        this.passwordInput.addEventListener('input', () => this.clearError());
    }

    async handleSubmit(e) {
        e.preventDefault();

        const email = this.emailInput.value;
        const password = this.passwordInput.value;

        this.clearError();
        this.showLoading(true);

        try {
            // Realizar login
            const loginData = await this.authService.login(email, password);
            
            // Guardar token
            if (loginData.token) {
                this.authService.saveToken(loginData.token);
            }

            // Obtener información del usuario
            const userData = await this.authService.getUserInfo(loginData.token);
            
            // Redirigir según el tipo de usuario
            this.redirectUser(userData);

        } catch (error) {
            this.showError(error.message || 'Error de conexión. Por favor, intenta nuevamente.');
            console.error('Error:', error);
        } finally {
            this.showLoading(false);
        }
    }

    redirectUser(userData) {
        if (userData.tipoUsuario && userData.tipoUsuario.descTipoUsuario === "Seguridad") {
            window.location.href = 'menu-seguridad.html';
        } else {
            window.location.href = 'menu.html';
        }
    }

    showLoading(show) {
        this.loadingDiv.style.display = show ? 'block' : 'none';
        this.loginBtn.disabled = show;
    }

    showError(message) {
        this.errorDiv.textContent = message;
        this.errorDiv.style.display = 'block';

        // Auto-hide después de 5 segundos
        setTimeout(() => {
            this.errorDiv.style.display = 'none';
        }, 5000);
    }

    clearError() {
        this.errorDiv.style.display = 'none';
    }
}

// Clase para manejar la validación de formularios
class FormValidator {
    static validateEmail(email) {
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return emailRegex.test(email);
    }

    static validatePassword(password) {
        return password.length >= 6;
    }

    static validateForm(email, password) {
        const errors = [];

        if (!email) {
            errors.push('El correo electrónico es requerido');
        } else if (!this.validateEmail(email)) {
            errors.push('El correo electrónico no es válido');
        }

        if (!password) {
            errors.push('La contraseña es requerida');
        } else if (!this.validatePassword(password)) {
            errors.push('La contraseña debe tener al menos 6 caracteres');
        }

        return errors;
    }
}

// Inicializar la aplicación cuando el DOM esté listo
document.addEventListener('DOMContentLoaded', () => {
    new LoginUI();
}); 