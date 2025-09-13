import java.io.File;
import java.util.Arrays;

public class Ejercicio8 extends File {


    public Ejercicio8(String pathname) {
        super(pathname);
    }

    public String showInfo() {
        if(!isDirectory()){
            return "Nombre: " + getName() + "\n" +
                    "Ruta: " + getPath() + "\n" +
                    "Ruta absoluta: " + getAbsolutePath() + "\n" +
                    "¿Se puede leer? " + canRead() + "\n" +
                    "¿Se puede escribir? " + canWrite() + "\n" +
                    "Tamaño: " + length() + "\n" +
                    "¿Es un directorio? " + isDirectory() + "\n" +
                    "¿Es un fichero?" + isFile();
        }

        return "Nombre: " + getName() + "\n" +
                "Ruta: " + getPath() + "\n" +
                "Ruta absoluta: " + getAbsolutePath() + "\n" +
                "¿Se puede leer? " + canRead() + "\n" +
                "¿Se puede escribir? " + canWrite() + "\n" +
                "Tamaño: " + length() + "\n" +
                "¿Es un directorio? " + isDirectory() + "\n" +
                "Contenidos: " + Arrays.toString(list()) + "\n" +
                "¿Es un fichero?" + isFile();

    }
}
