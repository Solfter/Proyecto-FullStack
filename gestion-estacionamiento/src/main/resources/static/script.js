document.getElementById('loginForm').addEventListener('submit', async function(e) {
    e.preventDefault();
    
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;
    const errorDiv = document.getElementById('errorMessage');
    const loadingDiv = document.getElementById('loading');
    const loginBtn = document.getElementById('loginBtn');
    
    // Limpiar mensajes de error previos
    errorDiv.style.display = 'none';
    errorDiv.textContent = '';
    
    // Mostrar loading
    loadingDiv.style.display = 'block';
    loginBtn.disabled = true;
    
    try {
        const response = await fetch('http://localhost:8001/auth/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                correo: email,
                password: password
            })
        });
        
        loadingDiv.style.display = 'none';
        loginBtn.disabled = false;
        
        if (response.ok) {
            const data = await response.json();
            // Guardar token si viene en la respuesta
            if (data.token) {
                localStorage.setItem('authToken', data.token);
            }
            // Redirigir a la página de reservas
            window.location.href = 'reservas.html';
        } else {
            const errorData = await response.json();
            showError(errorData.message || 'Credenciales incorrectas. Por favor, verifica tu correo y contraseña.');
        }
    } catch (error) {
        loadingDiv.style.display = 'none';
        loginBtn.disabled = false;
        showError('Error de conexión. Por favor, intenta nuevamente.');
        console.error('Error:', error);
    }
});

function showError(message) {
    const errorDiv = document.getElementById('errorMessage');
    errorDiv.textContent = message;
    errorDiv.style.display = 'block';
    
    // Auto-hide después de 5 segundos
    setTimeout(() => {
        errorDiv.style.display = 'none';
    }, 5000);
}

// Limpiar errores cuando el usuario empieza a escribir
document.getElementById('email').addEventListener('input', function() {
    document.getElementById('errorMessage').style.display = 'none';
});

document.getElementById('password').addEventListener('input', function() {
    document.getElementById('errorMessage').style.display = 'none';
});