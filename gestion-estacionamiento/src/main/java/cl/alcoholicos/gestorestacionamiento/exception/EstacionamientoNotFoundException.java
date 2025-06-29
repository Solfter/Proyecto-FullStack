package cl.alcoholicos.gestorestacionamiento.exception;

public class EstacionamientoNotFoundException extends RuntimeException {
    public EstacionamientoNotFoundException(String message) {
        super(message);
    }
}
