package Excepciones;

public class FalloEjecucion extends RuntimeException {
    public FalloEjecucion(String message) {
        super(message);
    }
}
