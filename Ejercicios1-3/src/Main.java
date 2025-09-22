import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        // ArchivoTXT arch = new ArchivoTXT("C:/Users/ignac/Documents/Lorem ipsum.txt");
        // System.out.println(arch.aVerso());
        // arch.codifica("C:/Users/ignac/Documents/nuevo.txt");
        // arch.codificaBuffer("C:/Users/ignac/Documents/nuevo.txt");
        // arch.codificaFiles("C:/Users/ignac/Documents/nuevo.txt");
        // arch.mover("C:/Users/ignac/Documents/pruebas/Lorem ipsum.txt");
        // System.out.println(arch.contar());
        // System.out.println(arch.contarLetras());
        // System.out.println(arch.contarPuntuacion());
        // System.out.println(arch.cuentaLineas());
        // System.out.println(arch.cuentaPalabra());
        // System.out.println(arch.cuentaVocales("C:/Users/ignac/Documents/numVocales.txt"));
        // System.out.println(arch.cuentaVocales2("C:/Users/ignac/Documents/numVocales.txt"));
        // arch.frecuenciaLetras();

        //--------------------------------------------Ejercicio 11-------------------------------------------------------------------

       /** List<Personaje> personajes = new ArrayList<>();
        personajes.add(new Personaje("pepe", "martillo", 35, 80.0, true));
        personajes.add(new Personaje("ana", "espada", 28, 70.5, false));
        personajes.add(new Personaje("luis", "arco", 40, 65.0, true));
        personajes.add(new Personaje("maria", "lanza", 22, 55.3, false));
        personajes.add(new Personaje("carlos", "hacha", 37, 82.7, true));
        personajes.add(new Personaje("sofia", "baston", 30, 60.0, true));

        Personaje pj = new Personaje();
        pj.toCSV(personajes, "personas.csv");*/

        // Personaje persona = new Personaje("Fernando", "maza", 40, 100.0, false);
        // persona.escribirFinalCSV(persona,"personas.csv");

        Personaje pj = new Personaje();

        System.out.println(pj.leerCSV("personas.csv"));


    }
}
