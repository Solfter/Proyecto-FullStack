-- Insertar usuario de prueba (ajusta según tu esquema de base de datos)
INSERT INTO usuario (rut_usuario, dv, pnombre_usuario, snombre_usuario, apellido_paterno_usuario, apellido_materno_usuario, correo, nro_celular, password, id_tipo_usuario) 
VALUES (19423324, '8', 'Sebastián', 'Rodrigo', 'González', 'Pino', 'se.gonzalez2@duocuc.cl', 962327029,
        '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.', -- BCrypt hash de "test_fullstack"
        2);