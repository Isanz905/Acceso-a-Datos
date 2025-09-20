package Excepciones;

public class DirectorioNoValido extends RuntimeException {
    public DirectorioNoValido(String message) {
        super(message);
    }
}
