package cl.alcoholicos.gestorestacionamiento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class GestorApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestorApplication.class, args);
		
	}

	/*public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(GestorApplication.class, args);
        
        // Obtener el servicio de migración
        PasswordMigrationService migrationService = context.getBean(PasswordMigrationService.class);
        
        try {
            System.out.println("=== INICIANDO MIGRACIÓN DE CONTRASEÑAS ===");
            
            // Verificar cuántos usuarios necesitan migración
            long usersToMigrate = migrationService.countUsersNeedingMigration();
            System.out.println("Usuarios que necesitan migración: " + usersToMigrate);
            
            if (usersToMigrate > 0) {
                System.out.println("Iniciando migración...");
                migrationService.migrateAllPasswords();
                System.out.println("✅ Migración completada exitosamente");
            } else {
                System.out.println("✅ No hay usuarios que necesiten migración");
            }
            
        } catch (Exception e) {
            System.err.println("❌ Error durante la migración: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Cerrar el contexto de Spring
            context.close();
        }
    }*/

}
