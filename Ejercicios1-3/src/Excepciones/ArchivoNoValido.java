package Excepciones;

public class ArchivoNoValido extends RuntimeException {
    public ArchivoNoValido(String message) {
        super(message);
    }
}
