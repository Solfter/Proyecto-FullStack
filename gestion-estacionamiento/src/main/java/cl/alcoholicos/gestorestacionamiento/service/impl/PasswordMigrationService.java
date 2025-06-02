package cl.alcoholicos.gestorestacionamiento.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import cl.alcoholicos.gestorestacionamiento.entity.UsuarioEntity;
import cl.alcoholicos.gestorestacionamiento.repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class PasswordMigrationService {
    
    @Autowired
    private UsuarioRepository userRepository;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    public void migrateAllPasswords() {
        System.out.println("Obteniendo usuarios de la base de datos...");
        List<UsuarioEntity> users = userRepository.findAll();
        
        int totalUsers = users.size();
        int migratedCount = 0;
        
        System.out.println("Total de usuarios encontrados: " + totalUsers);
        
        for (UsuarioEntity user : users) {
            if (!isPasswordEncrypted(user.getPassword())) {
                String plainPassword = user.getPassword();
                String encryptedPassword = passwordEncoder.encode(plainPassword);
                
                user.setPassword(encryptedPassword);
                userRepository.save(user);
                
                migratedCount++;
                System.out.println("✓ Usuario migrado: " + user.getCorreo() + " (" + migratedCount + "/" + totalUsers + ")");
            }
        }
        
        System.out.println("Migración completada: " + migratedCount + " usuarios migrados");
    }
    
    public long countUsersNeedingMigration() {
        return userRepository.findAll().stream()
                .filter(user -> !isPasswordEncrypted(user.getPassword()))
                .count();
    }
    
    private boolean isPasswordEncrypted(String password) {
        return password != null && password.matches("^\\$2[abyxy]\\$\\d+\\$.*");
    }
}
