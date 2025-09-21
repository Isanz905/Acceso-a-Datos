import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ArchivoTXT arch = new ArchivoTXT("C:/Users/ignac/Documents/Lorem ipsum.txt");
        // System.out.println(arch.aVerso());
        // arch.codifica("C:/Users/ignac/Documents/nuevo.txt");
        // arch.codificaBuffer("C:/Users/ignac/Documents/nuevo.txt");
         arch.codificaFiles("C:/Users/ignac/Documents/nuevo.txt");
        // arch.mover("C:/Users/ignac/Documents/pruebas/Lorem ipsum.txt");
        // System.out.println(arch.contar());
        // System.out.println(arch.contarLetras());
        // System.out.println(arch.contarPuntuacion());
        // System.out.println(arch.cuentaLineas());
        // System.out.println(arch.cuentaPalabra());
        // System.out.println(arch.cuentaVocales("C:/Users/ignac/Documents/numVocales.txt"));
        // System.out.println(arch.cuentaVocales2("C:/Users/ignac/Documents/numVocales.txt"));
        // arch.frecuenciaLetras();

    }
}
